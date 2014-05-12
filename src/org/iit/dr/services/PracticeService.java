package org.iit.dr.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.iit.dr.data_model.DistributionPlace;
import org.iit.dr.data_model.PracticeConsultant;
import org.iit.dr.data_model.PracticePlace;
import org.iit.dr.data_model.PracticeType;
import org.iit.dr.data_model.PracticeWork;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.data_model.unit.Course;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class PracticeService extends CommonService
{
  public static List<PracticePlace> getPracticePlaceList( PracticeType type )
  {
    switch( type )
    {
      case PRODUCTION:
        return dataConnector.getProductionPracticePlaceList();
      case GRADUATING:
        return dataConnector.getPracticePlaceList();
    }
    return null;
  }

  public static List<PracticePlace> getPracticePlacesWithStudents( PracticeType type )
  {
    List<PracticePlace> result = new ArrayList<PracticePlace>();
    for( PracticePlace place : getPracticePlaceList( type ) )
    {
      if( place.getStudentsNumber() > 0 )
      {
        result.add( place );
      }
    }
    return result;
  }

  public static void addPracticePlace( PracticePlace place, PracticeType type )
  {
    List<PracticePlace> placesList = getPracticePlaceList( type );
    if( !placesList.contains( place ) )
    {
      placesList.add( place );
    }
  }

  public static PracticePlace getPracticePlace( String practicePlaceId, PracticeType type )
  {
    if( practicePlaceId == null || practicePlaceId.length() == 0 )
    {
      return null;
    }
    for( PracticePlace practiceWork : getPracticePlaceList( type ) )
    {
      if( practiceWork.getCompanyId().equals( practicePlaceId ) )
      {
        return practiceWork;
      }
    }
    return null;
  }

  public static boolean removePracticePlace( String practicePlaceId, PracticeType type )
  {
    for( PracticePlace practicePlaceItem : getPracticePlaceList( type ) )
    {
      if( practicePlaceItem.getCompanyId().equals( practicePlaceId ) )
      {
        getPracticePlaceList( type ).remove( practicePlaceItem );
        return true;
      }
    }
    return false;
  }

  public static List<PracticeWork> getPracticeWorksList( PracticeType type )
  {
    List<PracticeWork> result = new ArrayList<PracticeWork>();
    List<PracticePlace> placesList = getPracticePlaceList( type );
    for( PracticePlace place : placesList )
    {
      for( PracticeWork work : place.getPracticeWorks() )
      {
        work.setCompanyId( place.getCompanyId() );
        result.add( work );
      }
    }
    return result;
  }

  public static PracticeWork getPracticeWorkByStudentId( String studentId, PracticeType type )
  {
    for( PracticeWork work : getPracticeWorksList( type ) )
    {
      if( work.getStudentId().equals( studentId ) )
      {
        return work;
      }
    }
    return null;
  }

  public static boolean deleteConsultantFromStudent( String studentId, PracticeType type )
  {
    getPracticeWorkByStudentId( studentId, type ).setConsultantId( null );
    return true;
  }

  public static boolean deleteConsultantFromStudent( String[] studentId, PracticeType type )
  {
    for( String id : studentId )
    {
      deleteConsultantFromStudent( id, type );
    }
    return true;
  }

  public static List<PracticeWork> getPracticeWorksByStudentsId( String[] studentId, PracticeType type )
  {
    List<PracticeWork> result = new ArrayList<PracticeWork>();
    List<String> studList = Arrays.asList( studentId );
    for( PracticeWork work : getPracticeWorksList( type ) )
    {
      if( studList.contains( work.getStudentId() ) )
      {
        result.add( work );
      }
    }
    return result;
  }

  public static List<PracticeWork> getPracticeWorksWithoutConsultantList( PracticeType type )
  {
    List<PracticeWork> result = new ArrayList<PracticeWork>();
    for( PracticeWork work : getPracticeWorksList( type ) )
    {
      if( work.getConsultantId() == null )
      {
        result.add( work );
      }
    }
    return result;
  }

  public static PracticePlace getPracticePlaceByPracticeWork( PracticeWork practiceWork, PracticeType type )
  {
    for( PracticePlace place : getPracticePlaceList( type ) )
    {
      if( place.getPracticeWorks().contains( practiceWork ) )
      {
        return place;
      }
    }
    return null;
  }

  public static List<PracticeConsultant> getPracticeConsultantsList( PracticeType practiceType )
  {
    switch( practiceType )
    {
      case PRODUCTION:
        return dataConnector.getProductionPracticeConsultantList();
      case GRADUATING:
        return dataConnector.getGraduatePracticeConsultantList();
    }
    return null;
  }

  public static boolean addPracticeConsultant( PracticeConsultant consultant, PracticeType practiceType )
  {
    List<PracticeConsultant> consList = getPracticeConsultantsList( practiceType );
    if( !consList.contains( consultant ) )
    {
      consList.add( consultant );
      return true;
    }
    return false;
  }

  public static boolean addPracticeConsultant( String consultantId, PracticeType practiceType )
  {
    return addPracticeConsultant( new PracticeConsultant( consultantId ), practiceType );
  }

  public static boolean addPracticeConsultant( String[] consultantId, PracticeType practiceType )
  {
    for( String id : consultantId )
    {
      addPracticeConsultant( id, practiceType );
    }
    return true;
  }

  public static boolean removePracticeConsultent( PracticeConsultant consultant, PracticeType practiceType )
  {
    List<PracticeConsultant> consList = getPracticeConsultantsList( practiceType );
    List<PracticeWork> worksList = getPracticeWorksByTeacherId( consultant.getTeacherId(), practiceType );
    if( worksList != null )
    {
      for( PracticeWork work : worksList )
      {
        work.setConsultantId( null );
      }
    }
    return consList.remove( consultant );
  }

  public static int getPracticeConsultantMaxStudentsNumber( String teacherId, PracticeType practiceType )
  {
    for( PracticeConsultant consultant : getPracticeConsultantsList( practiceType ) )
    {
      if( consultant.getTeacherId().equals( teacherId ) )
      {
        return consultant.getMaxStudentsNumber();
      }
    }
    return 0;
  }

  public static int getPracticeConsultantFreePlaceNumber( String teacherId, PracticeType practiceType )
  {
    return getPracticeConsultantMaxStudentsNumber( teacherId, practiceType )
      - getPracticeConsultantStudentsNumber( teacherId, practiceType );
  }

  public static List<PracticeWork> getPracticeWorksByTeacherId( String teacherId, PracticeType practiceType )
  {
    List<PracticeWork> result = new ArrayList<PracticeWork>();
    for( PracticeWork work : getPracticeWorksList( practiceType ) )
    {
      if( work.getConsultantId() != null && work.getConsultantId().equals( teacherId ) )
      {
        result.add( work );
      }
    }
    return result;
  }

  public static int getPracticeConsultantStudentsNumber( String teacherId, PracticeType practiceType )
  {
    return getPracticeWorksByTeacherId( teacherId, practiceType ).size();
  }

  public static boolean setConsultant( String studentId, String teacherId, PracticeType practiceType )
  {
    if( getPracticeConsultantStudentsNumber( teacherId, practiceType ) < getPracticeConsultantMaxStudentsNumber(
      teacherId, practiceType ) )
    {
      getPracticeWorkByStudentId( studentId, practiceType ).setConsultantId( teacherId );
      return true;
    }
    return false;
  }

  public static boolean setConsultant( String[] studentId, String teacherId, PracticeType practiceType )
  {
    if( studentId.length <= getPracticeConsultantFreePlaceNumber( teacherId, practiceType ) )
    {
      for( PracticeWork work : getPracticeWorksByStudentsId( studentId, practiceType ) )
      {
        work.setConsultantId( teacherId );
      }
      return true;
    }
    return false;
  }

  public static int getPracticeWorksWithMark( int min, int max, PracticeType practiceType )
  {
    int result = 0;
    for( PracticeWork work : getPracticeWorksList( practiceType ) )
    {
      if( work.getMark() != null && work.getMark() >= min && work.getMark() <= max )
        result++;
    }
    return result;
  }

  public static void importGraduatePracticePlacesFromDistribution()
  {
    String OU_ID = OrganizationUnitService.getCourseId( Course.FIFTH );
    List<String> ouIdList = new ArrayList<String>();
    ouIdList.add( OU_ID );
    List<Student> studsList = StudentsService.getStudentsWithoutPracticePlace( ouIdList, PracticeType.GRADUATING );
    for( Student stud : studsList )
    {
      DistributionPlace distributionPlace = DistributionService.getDistributionPlaceByDistributedStudent( stud.getId() );
      if( distributionPlace != null )
      {
        addPracticePlace( new PracticePlace( distributionPlace.getCompanyId() ), PracticeType.GRADUATING );
        getPracticePlace( distributionPlace.getCompanyId(), PracticeType.GRADUATING ).addStudent( stud.getId() );
      }
    }
  }
}
