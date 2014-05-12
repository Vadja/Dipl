package org.iit.dr.loaders;

import java.lang.reflect.Field;
import java.util.List;

import org.iit.dr.data_model.GraduateWork;
import org.iit.dr.data_model.role.PartTimeStaffer;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.data_model.unit.OrganizationUnit;
import org.iit.dr.data_model.unit.UnitType;
import org.iit.dr.documents.csv.read.DataLine;
import org.iit.dr.services.GraduateWorkService;
import org.iit.dr.services.OrganizationUnitService;
import org.iit.dr.services.StudentsService;
import org.iit.dr.services.TeachersService;
import org.iit.dr.utils.UUIDUtils;

public abstract class DataLoader
{
  private final String[][] fieldsDP = { {"Руководитель", "managerId"}, {"Консультант", "consultantIdBySpeciality"},
    {"Консультант ОТ", "consultantIdByProtectionOfLabor"}, {"Консультант ТЕО", "consultantIdByEconomics"},
    {"Рецензент", "reviewerId"}, {"Нормоконтролер", "consultantIdByNormalInspection"}};

  protected String filePath;

  protected String[] titles;

  public void loadData()
  {
  }

  public int searchTitle( String title )
  {
    for( int i = 0; i < titles.length; i++ )
    {
      if( titles[i] != null && titles[i].compareTo( title ) == 0 )
      {
        return i;
      }
    }
    return -1;
  }

  protected Student loadStudent( DataLine dataLine )
  {
    String fioStudent = "";
    if( searchTitle( "Студент" ) != -1 )
    {
      fioStudent = dataLine.getField( searchTitle( "Студент" ) );
    }
    if( fioStudent == null )
      return null;
    Student student = StudentsService.getStudentByFullName( fioStudent );
    if( student == null )
    {
      student = new Student();
      student.setId( UUIDUtils.getUUID() );
      String[] fio = fioStudent.split( "[ ]" );

      switch( fio.length )
      {
        case 3:
          student.setPatronymicName( fio[2] );
        case 2:
          student.setFirstName( fio[1] );
        case 1:
          student.setLastName( fio[0] );

      }
      StudentsService.getStudentList().add( student );
    }
    return student;
  }

  protected String loadOrganizationUnit( DataLine dataLine )
  {
    String courseId = null;
    String subGroupId = null;
    if( searchTitle( "Курс" ) != -1 )
    {
      courseId =
        OrganizationUnitService.getOrganizationUnitByName( dataLine.getField( searchTitle( "Курс" ) ), UnitType.STREAM );
    }
    String groupName = searchTitle( "Группа" ) != -1 ? dataLine.getField( searchTitle( "Группа" ) ) : null;
    String groupId = OrganizationUnitService.getOrganizationUnitByName( groupName, UnitType.GROUP );
    if( courseId != null && groupId == null )
    {
      OrganizationUnit group = new OrganizationUnit();
      group.setType( UnitType.GROUP );
      group.setName( groupName );
      group.setId( UUIDUtils.getUUID() );
      group.setParentOuId( courseId );
    }
    if( groupId != null )
    {
      String subGroupName = searchTitle( "Подгруппа" ) != -1 ? dataLine.getField( searchTitle( "Подгруппа" ) ) : null;
      if( subGroupName != null )
      {
        List<String> list = OrganizationUnitService.getSubOrganizationUnitListByParent( groupId );
        for( String item : list )
        {
          if( OrganizationUnitService.getOrganizationUnit( item ).getName().toString().compareTo( subGroupName ) == 0 )
          {
            subGroupId = item;
          }
        }
      }
    }
    return subGroupId != null ? subGroupId : groupId != null ? groupId : courseId;
  }

  protected void loadGraduateWork( DataLine dataLine, Student student )
  {
    GraduateWork dp = GraduateWorkService.getGraduateWorkByStudentId( student.getId() );
    if( dp == null )
    {
      dp = new GraduateWork();
      dp.setId( UUIDUtils.getUUID() );
      dp.setStudentId( student.getId() );
      GraduateWorkService.getGraduateWorkList().add( dp );
    }
    dp.setTitle( dataLine.getField( searchTitle( "Тема" ) ) );
    if( searchTitle( "Номер по приказу" ) != -1 )
    {
      dp.setNumber( dataLine.getField( searchTitle( "Номер по приказу" ) ) );
    }
    Class<?> d = dp.getClass();
    Field f;
    for( String[] field : fieldsDP )
    {
      if( searchTitle( field[0] ) != -1 )
      {
        String rukFio = dataLine.getField( searchTitle( field[0] ) );
        if( rukFio == null )
          continue;
        String id = null;
        rukFio = rukFio.split( "[,]" )[0];
        for( Teacher teach : TeachersService.getTeacherList() )
        {
          if( teach.getFullName().compareTo( rukFio ) == 0 || teach.getShortName().compareTo( rukFio ) == 0 )
          {
            id = teach.getId();
            break;
          }
        }
        if( id == null )
        {
          String[] split_fio = rukFio.split( " " );
          PartTimeStaffer ruk = new PartTimeStaffer();
          ruk.setLastName( split_fio[0] );
          String[] split_name = split_fio[1].split( "[.| ]" );
          switch( split_name.length )
          {
            case 2:
              ruk.setPatronymicName( split_name[1] );
            case 1:
              ruk.setFirstName( split_name[0] );
          }
          id = UUIDUtils.getUUID();
          ruk.setId( id );
          TeachersService.getTeacherList().add( ruk );
        }
        try
        {
          f = d.getDeclaredField( field[1] );
          f.setAccessible( true );
          f.set( dp, id );
        }
        catch( NoSuchFieldException x )
        {

        }
        catch( IllegalAccessException x )
        {

        }
      }
    }
  }
}