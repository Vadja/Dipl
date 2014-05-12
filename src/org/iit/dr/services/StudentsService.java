package org.iit.dr.services;

import java.util.ArrayList;
import java.util.List;

import org.iit.dr.data_model.DefenceGraduateWork;
import org.iit.dr.data_model.GraduateWork;
import org.iit.dr.data_model.PracticePlace;
import org.iit.dr.data_model.PracticeType;
import org.iit.dr.data_model.PracticeWork;
import org.iit.dr.data_model.role.Student;

public class StudentsService extends CommonService
{
  public static List<Student> getStudentList()
  {
    return dataConnector.getStudentList();
  }

  public static boolean removeStudent( String studentId )
  {
    for( Student studentItem : dataConnector.getStudentList() )
    {
      if( studentItem.getId().equals( studentId ) )
      {
    	dataConnector.getStudentList().remove( studentItem );
        return true;
      }
    }
    return false;
  }

  public static Student getStudent( String studentId )
  {
    if( studentId == null || studentId.length() == 0 )
    {
      return null;
    }
    for( Student studentItem : getStudentList() )
    {
      if( studentItem.getId().equals( studentId ) )
      {
        return studentItem;
      }
    }
    return null;
  }

  public static Student getStudentByFullName( String fullName )
  {
    for( Student studentItem : getStudentList() )
    {
      if( studentItem.getFullName().equals( fullName ) )
      {
        return studentItem;
      }
    }
    return null;
  }

  public static Student getStudentLastname( String lastname )
  {
    for( Student studentItem : getStudentList() )
    {
      if( studentItem.getLastName().equals( lastname ) )
      {
        return studentItem;
      }
    }
    return null;
  }
  public static List<Student> getStudentByOU( String ouID )
  {
    List<Student> studentList = new ArrayList<Student>();
    if( ouID == null || ouID.length() == 0 )
    {
      return null;
    }
    for( Student studentItem : getStudentList() )
    {
      if( studentItem.getGroup() != null && studentItem.getGroup().equals( ouID ) )
      {
        studentList.add( studentItem );
      }
    }
    return studentList;
  }

  public static List<Student> getStudentByOUList( List<String> ouIDList )
  {
    List<Student> studentList = new ArrayList<Student>();
    if( ouIDList == null || ouIDList.size() == 0 )
    {
      return null;
    }
    for( Student studentItem : getStudentList() )
    {
      if( ouIDList.contains( studentItem.getGroup() ) )
      {
        studentList.add( studentItem );
      }
    }
    return studentList;
  }

  public static List<String> getStudentIdByOUList( List<String> ouIDList )
  {
    List<String> studentList = new ArrayList<String>();
    if( ouIDList == null || ouIDList.size() == 0 )
    {
      return null;
    }
    for( Student studentItem : getStudentList() )
    {
      if( ouIDList.contains( studentItem.getGroup() ) )
      {
        studentList.add( studentItem.getId() );
      }
    }
    return studentList;
  }

  public static Student getStudentByDefenceGraduateWorkId( String defenceGraduateWorkId )
  {
    if( defenceGraduateWorkId == null || defenceGraduateWorkId.length() == 0 )
    {
      return null;
    }
    DefenceGraduateWork defenceGraduateWork = GraduateWorkService.getDefenceGraduateWork( defenceGraduateWorkId );
    if( defenceGraduateWork != null )
    {
      GraduateWork graduateWork = GraduateWorkService.getGraduateWork( defenceGraduateWork.getGraduateWorkId() );
      if( graduateWork != null )
      {
        return getStudent( graduateWork.getStudentId() );
      }
    }
    return null;
  }

  public static List<Student> getStudentsByPracticePlaceId( String placeId, PracticeType type )
  {
    List<Student> result = new ArrayList<Student>();
    List<PracticeWork> practiceWorks = PracticeService.getPracticePlace( placeId, type ).getPracticeWorks();
    for( PracticeWork pWork : practiceWorks )
    {
      result.add( getStudent( pWork.getStudentId() ) );
    }
    return result;
  }

  public static List<Student> getStudentsByConsultantId( String consultantId, PracticeType type )
  {
    List<Student> studList = new ArrayList<Student>();
    for( PracticeWork work : PracticeService.getPracticeWorksList( type ) )
    {
      if( work.getConsultantId() != null && work.getConsultantId().equals( consultantId ) )
      {
        studList.add( getStudent( work.getStudentId() ) );
      }
    }
    return studList;
  }

  public static List<Student> getStudentsWithoutPracticePlace( List<String> ouIDList, PracticeType type )
  {
    List<Student> studList = getStudentByOUList( ouIDList );
    List<PracticePlace> placesList = PracticeService.getPracticePlaceList( type );
    for( PracticePlace place : placesList )
    {
      studList.removeAll( StudentsService.getStudentsByPracticePlaceId( place.getCompanyId(), type ) );
    }
    return studList;
  }

  public static List<Student> getStudentsWithoutPracticeConsultant( PracticeType type )
  {
    List<Student> studList = new ArrayList<Student>();
    for( PracticeWork work : PracticeService.getPracticeWorksList( type ) )
    {
      if( work.getConsultantId() == null )
      {
        studList.add( getStudent( work.getStudentId() ) );
      }
    }
    return studList;
  }

  public static List<Student> getStudentsByOuIdAndStudyForm( String ouID, boolean isBudget )
  {
    List<String> ouIDList = OrganizationUnitService.getSubOrganizationUnitListByParent( ouID );
    List<Student> studList = new ArrayList<Student>();
    for( Student stud : getStudentByOUList( ouIDList ) )
    {
      if( stud.getBudget() == isBudget )
      {
        studList.add( stud );
      }
    }
    return studList;
  }
}
