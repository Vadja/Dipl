package org.iit.dr.view.model.table.graduate_work;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.iit.dr.data_model.DefenceGraduateWork;
import org.iit.dr.data_model.GraduateWork;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.services.GraduateWorkService;
import org.iit.dr.services.StudentsService;
import org.iit.dr.services.TeachersService;
import org.iit.dr.view.model.table.common.TableModelConst;
import org.iit.dr.view.model.table.common.UpdateDateTableModel;

/**
 * GraduateWorkTableModel.
 * 
 * @author Yuriy Karpovich
 */
public class GraduateWorkTableModel extends DefaultTableModel implements UpdateDateTableModel, TableModelConst
{

  ViewMode viewMode = ViewMode.STUDENT;

  List<GraduateWork> graduateWorkList;

  List<String> studentIdFilterList = null;

  String[] title_student = new String[] {"Номер","Дипломник", "Тема", "Руководитель", "Протокол"};

  String[] title_manager = new String[] {"Руководитель", "Дипломник","Номер", "Тема", "Протокол"};

  String[] title_cons = new String[] {"Консультант", "Дипломник","Номер", "Тема", "Протокол"};

  String[] title_reviewer = new String[] {"Рецензент", "Дипломник","Номер", "Тема", "Протокол"};

  public GraduateWorkTableModel()
  {
  }

  @Override
  public String getColumnName( int column )
  {

    switch( viewMode )
    {
      case STUDENT:
        return title_student[column];
      case MANAGER:
        return title_manager[column];
      case CONSULTANT_BY_ECONOMICS:
        return title_cons[column];
      case CONSULTANT_BY_NORMAL_INSPECTION:
        return title_cons[column];
      case CONSULTANT_BY_PROTECTION_OF_LABOR:
        return title_cons[column];
      case CONSULTANT_BY_SPECIALITY:
        return title_cons[column];
      case REVIEWER:
        return title_reviewer[column];

      default:
        return title_student[column];
    }
  }

  @Override
  public Class<?> getColumnClass( int columnIndex )
  {
    return columnIndex != 4 ? String.class : Boolean.class;
  }

  @Override
  public int getColumnCount()
  {

    switch( viewMode )
    {
      case STUDENT:
        return title_student.length;
      case MANAGER:
        return title_manager.length;
      case CONSULTANT_BY_ECONOMICS:
        return title_cons.length;
      case CONSULTANT_BY_NORMAL_INSPECTION:
        return title_cons.length;
      case CONSULTANT_BY_PROTECTION_OF_LABOR:
        return title_cons.length;
      case CONSULTANT_BY_SPECIALITY:
        return title_cons.length;
      case REVIEWER:
        return title_manager.length;

      default:
        return title_student.length;
    }
  }

  public void updateData()
  {

    if( graduateWorkList == null )
    {
      graduateWorkList = new ArrayList<GraduateWork>();
    }
    else
    {
      graduateWorkList.clear();
    }

    List<GraduateWork> graduateWorkListSource;

    switch( viewMode )
    {
      case STUDENT:
        graduateWorkListSource = GraduateWorkService.getGraduateWorkListOrderByStudent();
        break;
      case MANAGER:
        graduateWorkListSource = GraduateWorkService.getGraduateWorkListOrderByManager();
        break;
      case CONSULTANT_BY_ECONOMICS:
        graduateWorkListSource = GraduateWorkService.getGraduateWorkListOrderByConsultantByEconomics();
        break;
      case CONSULTANT_BY_NORMAL_INSPECTION:
        graduateWorkListSource = GraduateWorkService.getGraduateWorkListOrderByConsultantByNormalInspection();
        break;
      case CONSULTANT_BY_PROTECTION_OF_LABOR:
        graduateWorkListSource = GraduateWorkService.getGraduateWorkListOrderByConsultantByProtectionOfLabor();
        break;
      case CONSULTANT_BY_SPECIALITY:
        graduateWorkListSource = GraduateWorkService.getGraduateWorkListOrderByConsultantBySpeciality();
        break;
      case REVIEWER:
        graduateWorkListSource = GraduateWorkService.getGraduateWorkListOrderByReviewer();
        break;

      default:
        graduateWorkListSource = GraduateWorkService.getGraduateWorkList();
    }

    if( graduateWorkListSource != null )
    {

      for( GraduateWork graduateWork : graduateWorkListSource )
      {

        if( studentIdFilterList != null )
        {

          if( studentIdFilterList.contains( graduateWork.getStudentId() ) )
          {

            graduateWorkList.add( graduateWork );
          }

        }
        else
        {

          graduateWorkList.add( graduateWork );
        }
      }
    }

    fireTableStructureChanged();
    fireTableDataChanged();
  }

  @Override
  public int getRowCount()
  {
    return graduateWorkList != null ? graduateWorkList.size() : 0;
  }

  @Override
  public Object getValueAt( int rowIndex, int columnIndex )
  {

    if( rowIndex < graduateWorkList.size() )
    {

      GraduateWork graduateWork = graduateWorkList.get( rowIndex );

      if( columnIndex == ID_COLUMN )
      {

        return graduateWork.getId();
      }

      if( columnIndex == 4 )
      {

        DefenceGraduateWork defenceGraduateWork =
          GraduateWorkService.getDefenceGraduateWorkListByGW( graduateWork.getId() );

        return defenceGraduateWork != null && defenceGraduateWork.getId() != null;
      }

      switch( viewMode )
      {
        case STUDENT:
          switch( columnIndex )
          {
          	case 0:
          		return graduateWork.getNumber();
            case 1:
              Student student = StudentsService.getStudent( graduateWork.getStudentId() );
              return student != null ? student.getShortName() : null;
            case 2:
              return graduateWork.getTitle();
            case 3:
              Teacher teacher = TeachersService.getTeacher( graduateWork.getManagerId() );
              return teacher != null ? teacher.getShortName() : null;
          }
          break;
        case MANAGER:
          switch( columnIndex )
          {

            case 0:
              Teacher teacher = TeachersService.getTeacher( graduateWork.getManagerId() );
              return teacher != null ? teacher.getShortName() : null;
            case 1:
              Student student = StudentsService.getStudent( graduateWork.getStudentId() );
              return student != null ? student.getShortName() : null;
          	case 2:
          		return graduateWork.getNumber();
            case 3:
              return graduateWork.getTitle();
          }
          break;
        case CONSULTANT_BY_ECONOMICS:
          switch( columnIndex )
          {

            case 0:
              Teacher teacher = TeachersService.getTeacher( graduateWork.getConsultantIdByEconomics() );
              return teacher != null ? teacher.getShortName() : null;
            case 1:
              Student student = StudentsService.getStudent( graduateWork.getStudentId() );
              return student != null ? student.getShortName() : null;
          	case 2:
          		return graduateWork.getNumber();
            case 3:
              return graduateWork.getTitle();
          }
          break;
        case CONSULTANT_BY_NORMAL_INSPECTION:
          switch( columnIndex )
          {

            case 0:
              Teacher teacher = TeachersService.getTeacher( graduateWork.getConsultantIdByNormalInspection() );
              return teacher != null ? teacher.getShortName() : null;
            case 1:
              Student student = StudentsService.getStudent( graduateWork.getStudentId() );
              return student != null ? student.getShortName() : null;
          	case 2:
          		return graduateWork.getNumber();
            case 3:
              return graduateWork.getTitle();
          }
          break;
        case CONSULTANT_BY_PROTECTION_OF_LABOR:
          switch( columnIndex )
          {

            case 0:
              Teacher teacher = TeachersService.getTeacher( graduateWork.getConsultantIdByProtectionOfLabor() );
              return teacher != null ? teacher.getShortName() : null;
            case 1:
              Student student = StudentsService.getStudent( graduateWork.getStudentId() );
              return student != null ? student.getShortName() : null;
          	case 2:
          		return graduateWork.getNumber();
            case 3:
              return graduateWork.getTitle();
          }
          break;
        case CONSULTANT_BY_SPECIALITY:
          switch( columnIndex )
          {

            case 0:
              Teacher teacher = TeachersService.getTeacher( graduateWork.getConsultantIdBySpeciality() );
              return teacher != null ? teacher.getShortName() : null;
            case 1:
              Student student = StudentsService.getStudent( graduateWork.getStudentId() );
              return student != null ? student.getShortName() : null;
          	case 2:
          		return graduateWork.getNumber();
            case 3:
              return graduateWork.getTitle();
          }
          break;
        case REVIEWER:
          switch( columnIndex )
          {

            case 0:
              Teacher teacher = TeachersService.getTeacher( graduateWork.getReviewerId() );
              return teacher != null ? teacher.getShortName() : null;
            case 1:
              Student student = StudentsService.getStudent( graduateWork.getStudentId() );
              return student != null ? student.getShortName() : null;
          	case 2:
          		return graduateWork.getNumber();
            case 3:
              return graduateWork.getTitle();
          }
          break;
      }
    }
    return null;
  }

  @Override
  public boolean isCellEditable( int rowIndex, int columnIndex )
  {
    return false;
  }

  public void updateData( List<String> studentIdFilterList )
  {
    this.studentIdFilterList = studentIdFilterList;
    updateData();
  }

  public void updateData( List<String> studentIdFilterList, ViewMode viewMode )
  {
    this.studentIdFilterList = studentIdFilterList;
    this.viewMode = viewMode;
    updateData();
  }

  public void updateData( ViewMode viewMode )
  {
    this.viewMode = viewMode;
    updateData();
  }
}
