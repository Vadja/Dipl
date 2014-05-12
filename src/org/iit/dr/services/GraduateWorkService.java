package org.iit.dr.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.iit.dr.data_model.DefenceGraduateWork;
import org.iit.dr.data_model.GraduateWork;
import org.iit.dr.data_model.comparators.StudentsByFioComparator;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.utils.DateUtils;

public class GraduateWorkService extends CommonService
{
  public static List<GraduateWork> getGraduateWorkList()
  {
    return dataConnector.getGraduateWorkList();
  }

  public static GraduateWork getGraduateWork( String graduateWorkId )
  {
    if( graduateWorkId == null || graduateWorkId.length() == 0 )
    {
      return null;
    }
    for( GraduateWork graduateWorkItem : dataConnector.getGraduateWorkList() )
    {
      if( graduateWorkItem.getId().equals( graduateWorkId ) )
      {
        return graduateWorkItem;
      }
    }
    return null;
  }

  public static GraduateWork getGraduateWorkByStudentId( String studentId )
  {
    for( GraduateWork graduateWorkItem : dataConnector.getGraduateWorkList() )
    {
      if( graduateWorkItem.getStudentId().equals( studentId ) )
      {
        return graduateWorkItem;
      }
    }
    return null;
  }

  public static boolean removeGraduateWork( String graduateWorkId )
  {
    for( GraduateWork graduateWorkItem : dataConnector.getGraduateWorkList() )
    {
      if( graduateWorkItem.getId().equals( graduateWorkId ) )
      {
        dataConnector.getGraduateWorkList().remove( graduateWorkItem );
        return true;
      }
    }
    return false;
  }

  public static List<GraduateWork> getGraduateWorkListWithBestOrderByStudent()
  {

    List<GraduateWork> graduateWorkSelList = new ArrayList<GraduateWork>();

    for( DefenceGraduateWork defenceGraduateWorkItem : getDefenceGraduateWorkList() )
    {

      if( defenceGraduateWorkItem.getGraduateWorkInfo().isBestWork() != null
        && defenceGraduateWorkItem.getGraduateWorkInfo().isBestWork()
        && defenceGraduateWorkItem.getGraduateWorkId() != null )
      {

        graduateWorkSelList.add( getGraduateWork( defenceGraduateWorkItem.getGraduateWorkId() ) );
      }
    }

    Collections.sort( graduateWorkSelList, new Comparator<GraduateWork>()
    {
      public int compare( GraduateWork o1, GraduateWork o2 )
      {
        Student student1 = StudentsService.getStudent( o1.getStudentId() );
        Student student2 = StudentsService.getStudent( o2.getStudentId() );
        return comparePersons( student1, student2 );
      }
    } );
    return graduateWorkSelList;
  }

  public static DefenceGraduateWork getDefenceGraduateWork( String defenceGraduateWorkId )
  {

    if( defenceGraduateWorkId == null || defenceGraduateWorkId.length() == 0 )
    {

      return null;
    }

    for( DefenceGraduateWork defenceGraduateWorkItem : dataConnector.getDefenceGraduateWorkList() )
    {

      if( defenceGraduateWorkItem.getId().equals( defenceGraduateWorkId ) )
      {

        return defenceGraduateWorkItem;
      }
    }

    return null;
  }

  public static DefenceGraduateWork getDefenceGraduateWorkByGraduateWorkId( String graduateWorkId )
  {

    if( graduateWorkId == null || graduateWorkId.length() == 0 )
    {

      return null;
    }

    for( DefenceGraduateWork defenceGraduateWorkItem : dataConnector.getDefenceGraduateWorkList() )
    {

      if( defenceGraduateWorkItem.getGraduateWorkId() != null
        && defenceGraduateWorkItem.getGraduateWorkId().equals( graduateWorkId ) )
      {

        return defenceGraduateWorkItem;
      }
    }

    return null;
  }

  public static Integer getDefenceGraduateWorkProtokolNumber()
  {

    Integer maxProtokolNumber = 0;

    for( DefenceGraduateWork defenceGraduateWorkItem : dataConnector.getDefenceGraduateWorkList() )
    {

      if( defenceGraduateWorkItem.getProtocolNumber() != null
        && defenceGraduateWorkItem.getProtocolNumber().length() > 0 )
      {

        Integer protokolItem = Integer.valueOf( defenceGraduateWorkItem.getProtocolNumber() );

        maxProtokolNumber = Math.max( maxProtokolNumber, protokolItem );
      }

    }

    return maxProtokolNumber;
  }

  public static boolean removeDefenceGraduateWork( String defenceGraduateWorkId )
  {

    for( DefenceGraduateWork defenceGraduateWorkItem : dataConnector.getDefenceGraduateWorkList() )
    {

      if( defenceGraduateWorkItem.getId().equals( defenceGraduateWorkId ) )
      {

        dataConnector.getDefenceGraduateWorkList().remove( defenceGraduateWorkItem );
        return true;
      }
    }

    return false;
  }

  public static List<DefenceGraduateWork> getDefenceGraduateWorkList()
  {
    return dataConnector.getDefenceGraduateWorkList();
  }

  public static List<Date> getDefenceGraduateWorkDateList()
  {

    List<Date> dateList = new ArrayList<Date>();

    for( DefenceGraduateWork defenceGraduateWorkItem : dataConnector.getDefenceGraduateWorkList() )
    {

      if( defenceGraduateWorkItem.getActualDate() != null
        && !dateList.contains( defenceGraduateWorkItem.getActualDate() ) )
      {

        dateList.add( defenceGraduateWorkItem.getActualDate() );
      }
    }

    Collections.sort( dateList );

    return dateList;
  }

  public static List<DefenceGraduateWork> getDefenceGraduateWorkListOrderByProtocolNumber( Date date )
  {

    List<DefenceGraduateWork> defenceGraduateWorkSelList = new ArrayList<DefenceGraduateWork>();

    for( DefenceGraduateWork defenceGraduateWorkItem : getDefenceGraduateWorkList() )
    {

      if( DateUtils.checkDate( defenceGraduateWorkItem.getActualDate(), date ) )
      {

        defenceGraduateWorkSelList.add( defenceGraduateWorkItem );
      }
    }

    Collections.sort( defenceGraduateWorkSelList, new Comparator<DefenceGraduateWork>()
    {
      public int compare( DefenceGraduateWork o1, DefenceGraduateWork o2 )
      {

        if( o1.getProtocolNumber() == null || o2.getProtocolNumber() == null )
        {

          Student student1 = StudentsService.getStudentByDefenceGraduateWorkId( o1.getId() );
          Student student2 = StudentsService.getStudentByDefenceGraduateWorkId( o2.getId() );
          return comparePersons( student1, student2 );
        }

        if( o1.getProtocolNumber() == null )
        {

          return -1;
        }

        if( o2.getProtocolNumber() == null )
        {

          return 1;
        }

        if( o1.getProtocolNumber() != null && o2.getProtocolNumber() != null )
        {

          Integer protocolNumber1 = Integer.parseInt( o1.getProtocolNumber() );
          Integer protocolNumber2 = Integer.parseInt( o2.getProtocolNumber() );

          return protocolNumber1.compareTo( protocolNumber2 );
        }
        return 0;
      }
    } );

    return defenceGraduateWorkSelList;
  }

  public static DefenceGraduateWork getDefenceGraduateWorkListByGW( String gwId )
  {

    if( gwId == null )
    {

      return null;
    }

    for( DefenceGraduateWork defenceGraduateWorkItem : getDefenceGraduateWorkList() )
    {

      if( defenceGraduateWorkItem.getGraduateWorkId() != null
        && defenceGraduateWorkItem.getGraduateWorkId().equals( gwId ) )
      {

        return defenceGraduateWorkItem;
      }
    }
    return null;
  }

  @SuppressWarnings("deprecation")
public static List<DefenceGraduateWork> getDefenceGraduateWorkListOrderByStudent( Date date, String startTime, String endTime )
  {

    List<DefenceGraduateWork> defenceGraduateWorkSelList = new ArrayList<DefenceGraduateWork>();

    for( DefenceGraduateWork defenceGraduateWorkItem : getDefenceGraduateWorkList() )
    {

      if( DateUtils.checkDate( defenceGraduateWorkItem.getActualDate(), date ) )
      {
    	  if(defenceGraduateWorkItem.getStartTime()!=null||defenceGraduateWorkItem.getEndTime()!=null){
    		  Date newDate = new Date(defenceGraduateWorkItem.getActualDate().getYear(),
    	  				defenceGraduateWorkItem.getActualDate().getMonth(),
    	  				defenceGraduateWorkItem.getActualDate().getDate(),
    	    			  Integer.parseInt(defenceGraduateWorkItem.getStartTime().split(":")[0]),
    	    			  Integer.parseInt(defenceGraduateWorkItem.getStartTime().split(":")[1]));
    	  		
    	  		Date start = new Date(date.getYear(),date.getMonth(),date.getDate(),
    	  				Integer.parseInt(startTime.split(":")[0]),
    	  				Integer.parseInt(startTime.split(":")[1]));
    	  		
    	  		Date end = new Date(date.getYear(),date.getMonth(),date.getDate(),
    	  				Integer.parseInt(endTime.split(":")[0]),
    	  				Integer.parseInt(endTime.split(":")[1]));
    	  		
    	  		if(newDate.getTime()<end.getTime()&&newDate.getTime()>=start.getTime()){
    	  			defenceGraduateWorkSelList.add( defenceGraduateWorkItem );
    	    	  }
    	  }
      }
    }

    Collections.sort( defenceGraduateWorkSelList, new Comparator<DefenceGraduateWork>()
    {
      public int compare( DefenceGraduateWork o1, DefenceGraduateWork o2 )
      {

        Student student1 = StudentsService.getStudentByDefenceGraduateWorkId( o1.getId() );
        Student student2 = StudentsService.getStudentByDefenceGraduateWorkId( o2.getId() );

        return comparePersons( student1, student2 );
      }
    } );

    return defenceGraduateWorkSelList;
  }

  public static List<DefenceGraduateWork> getDefenceGraduateWorkListOrderByStudent()
  {

    List<DefenceGraduateWork> defenceGraduateWorkSelList = new ArrayList<DefenceGraduateWork>();

    for( DefenceGraduateWork defenceGraduateWorkItem : getDefenceGraduateWorkList() )
    {

      defenceGraduateWorkSelList.add( defenceGraduateWorkItem );
    }

    Collections.sort( defenceGraduateWorkSelList, new Comparator<DefenceGraduateWork>()
    {
      public int compare( DefenceGraduateWork o1, DefenceGraduateWork o2 )
      {

        Student student1 = StudentsService.getStudentByDefenceGraduateWorkId( o1.getId() );
        Student student2 = StudentsService.getStudentByDefenceGraduateWorkId( o2.getId() );

        return comparePersons( student1, student2 );
      }
    } );

    return defenceGraduateWorkSelList;
  }

  public static List<GraduateWork> getGraduateWorkListOrderByStudent()
  {

    List<GraduateWork> graduateWorkSelList = new ArrayList<GraduateWork>();

    for( GraduateWork graduateWorkItem : getGraduateWorkList() )
    {

      graduateWorkSelList.add( graduateWorkItem );
    }

    Collections.sort( graduateWorkSelList, new Comparator<GraduateWork>()
    {
      public int compare( GraduateWork o1, GraduateWork o2 )
      {

        Student student1 = StudentsService.getStudent( o1.getStudentId() );
        Student student2 = StudentsService.getStudent( o2.getStudentId() );

        return comparePersons( student1, student2 );
      }
    } );

    return graduateWorkSelList;
  }

  public static List<GraduateWork> getGraduateWorkListOrderByManager()
  {

    List<GraduateWork> graduateWorkSelList = new ArrayList<GraduateWork>();

    for( GraduateWork graduateWorkItem : getGraduateWorkList() )
    {

      graduateWorkSelList.add( graduateWorkItem );
    }

    Collections.sort( graduateWorkSelList, new Comparator<GraduateWork>()
    {
      public int compare( GraduateWork o1, GraduateWork o2 )
      {

        Teacher teacher1 = TeachersService.getTeacher( o1.getManagerId() );
        Teacher teacher2 = TeachersService.getTeacher( o2.getManagerId() );

        Student student1 = StudentsService.getStudent( o1.getStudentId() );
        Student student2 = StudentsService.getStudent( o2.getStudentId() );

        int teacherCompareResult = comparePersons( teacher1, teacher2 );

        return teacherCompareResult != 0 ? teacherCompareResult : comparePersons( student1, student2 );
      }
    } );

    return graduateWorkSelList;
  }

  public static List<GraduateWork> getGraduateWorkListOrderByReviewer()
  {

    List<GraduateWork> graduateWorkSelList = new ArrayList<GraduateWork>();

    for( GraduateWork graduateWorkItem : getGraduateWorkList() )
    {

      graduateWorkSelList.add( graduateWorkItem );
    }

    Collections.sort( graduateWorkSelList, new Comparator<GraduateWork>()
    {
      public int compare( GraduateWork o1, GraduateWork o2 )
      {

        Teacher teacher1 = TeachersService.getTeacher( o1.getReviewerId() );
        Teacher teacher2 = TeachersService.getTeacher( o2.getReviewerId() );

        Student student1 = StudentsService.getStudent( o1.getStudentId() );
        Student student2 = StudentsService.getStudent( o2.getStudentId() );

        int teacherCompareResult = comparePersons( teacher1, teacher2 );

        return teacherCompareResult != 0 ? teacherCompareResult : comparePersons( student1, student2 );
      }
    } );

    return graduateWorkSelList;
  }

  public static List<GraduateWork> getGraduateWorkListOrderByConsultantByEconomics()
  {

    List<GraduateWork> graduateWorkSelList = new ArrayList<GraduateWork>();

    for( GraduateWork graduateWorkItem : getGraduateWorkList() )
    {

      graduateWorkSelList.add( graduateWorkItem );
    }

    Collections.sort( graduateWorkSelList, new Comparator<GraduateWork>()
    {
      public int compare( GraduateWork o1, GraduateWork o2 )
      {

        Teacher teacher1 = TeachersService.getTeacher( o1.getConsultantIdByEconomics() );
        Teacher teacher2 = TeachersService.getTeacher( o2.getConsultantIdByEconomics() );

        Student student1 = StudentsService.getStudent( o1.getStudentId() );
        Student student2 = StudentsService.getStudent( o2.getStudentId() );

        int teacherCompareResult = comparePersons( teacher1, teacher2 );

        return teacherCompareResult != 0 ? teacherCompareResult : comparePersons( student1, student2 );
      }
    } );

    return graduateWorkSelList;
  }

  public static List<GraduateWork> getGraduateWorkListOrderByConsultantByNormalInspection()
  {

    List<GraduateWork> graduateWorkSelList = new ArrayList<GraduateWork>();

    for( GraduateWork graduateWorkItem : getGraduateWorkList() )
    {

      graduateWorkSelList.add( graduateWorkItem );
    }

    Collections.sort( graduateWorkSelList, new Comparator<GraduateWork>()
    {
      public int compare( GraduateWork o1, GraduateWork o2 )
      {

        Teacher teacher1 = TeachersService.getTeacher( o1.getConsultantIdByNormalInspection() );
        Teacher teacher2 = TeachersService.getTeacher( o2.getConsultantIdByNormalInspection() );

        Student student1 = StudentsService.getStudent( o1.getStudentId() );
        Student student2 = StudentsService.getStudent( o2.getStudentId() );

        int teacherCompareResult = comparePersons( teacher1, teacher2 );

        return teacherCompareResult != 0 ? teacherCompareResult : comparePersons( student1, student2 );
      }
    } );

    return graduateWorkSelList;
  }

  public static List<GraduateWork> getGraduateWorkListOrderByConsultantByProtectionOfLabor()
  {
    List<GraduateWork> graduateWorkSelList = new ArrayList<GraduateWork>();
    for( GraduateWork graduateWorkItem : getGraduateWorkList() )
    {
      graduateWorkSelList.add( graduateWorkItem );
    }

    Collections.sort( graduateWorkSelList, new Comparator<GraduateWork>()
    {
      public int compare( GraduateWork o1, GraduateWork o2 )
      {
        Teacher teacher1 = TeachersService.getTeacher( o1.getConsultantIdByProtectionOfLabor() );
        Teacher teacher2 = TeachersService.getTeacher( o2.getConsultantIdByProtectionOfLabor() );
        Student student1 = StudentsService.getStudent( o1.getStudentId() );
        Student student2 = StudentsService.getStudent( o2.getStudentId() );
        int teacherCompareResult = comparePersons( teacher1, teacher2 );
        return teacherCompareResult != 0 ? teacherCompareResult : comparePersons( student1, student2 );
      }
    } );
    return graduateWorkSelList;
  }

  public static List<GraduateWork> getGraduateWorkListOrderByConsultantBySpeciality()
  {
    List<GraduateWork> graduateWorkSelList = new ArrayList<GraduateWork>();
    for( GraduateWork graduateWorkItem : getGraduateWorkList() )
    {
      graduateWorkSelList.add( graduateWorkItem );
    }
    Collections.sort( graduateWorkSelList, new Comparator<GraduateWork>()
    {
      public int compare( GraduateWork o1, GraduateWork o2 )
      {
        Teacher teacher1 = TeachersService.getTeacher( o1.getConsultantIdBySpeciality() );
        Teacher teacher2 = TeachersService.getTeacher( o2.getConsultantIdBySpeciality() );
        Student student1 = StudentsService.getStudent( o1.getStudentId() );
        Student student2 = StudentsService.getStudent( o2.getStudentId() );
        int teacherCompareResult = comparePersons( teacher1, teacher2 );
        return teacherCompareResult != 0 ? teacherCompareResult : comparePersons( student1, student2 );
      }
    } );
    return graduateWorkSelList;
  }

  public static List<String> getDefenceGWAnswerList()
  {
    List<String> valueList = new ArrayList<String>();
    List<DefenceGraduateWork> defenceGraduateWorkList = getDefenceGraduateWorkList();
    for( DefenceGraduateWork defenceGraduateWork : defenceGraduateWorkList )
    {
      if( defenceGraduateWork.getGraduateWorkInfo() != null
        && defenceGraduateWork.getGraduateWorkInfo().getAnswer() != null
        && !valueList.contains( defenceGraduateWork.getGraduateWorkInfo().getAnswer().trim() ) )
      {
        valueList.add( defenceGraduateWork.getGraduateWorkInfo().getAnswer().trim() );
      }
    }
    return valueList;
  }

  public static List<String> getDefenceGWTrainingList()
  {
    List<String> valueList = new ArrayList<String>();
    List<DefenceGraduateWork> defenceGraduateWorkList = getDefenceGraduateWorkList();
    for( DefenceGraduateWork defenceGraduateWork : defenceGraduateWorkList )
    {
      if( defenceGraduateWork.getGraduateWorkInfo() != null
        && defenceGraduateWork.getGraduateWorkInfo().getTraining() != null
        && !valueList.contains( defenceGraduateWork.getGraduateWorkInfo().getTraining().trim() ) )
      {
        valueList.add( defenceGraduateWork.getGraduateWorkInfo().getTraining().trim() );
      }
    }
    return valueList;
  }

  public static List<String> getDefenceGWResultCommonList()
  {
    List<String> valueList = new ArrayList<String>();
    List<DefenceGraduateWork> defenceGraduateWorkList = getDefenceGraduateWorkList();
    for( DefenceGraduateWork defenceGraduateWork : defenceGraduateWorkList )
    {
      if( defenceGraduateWork.getGraduateWorkInfo() != null
        && defenceGraduateWork.getGraduateWorkInfo().getResultCommon() != null
        && !valueList.contains( defenceGraduateWork.getGraduateWorkInfo().getResultCommon().trim() ) )
      {
        valueList.add( defenceGraduateWork.getGraduateWorkInfo().getResultCommon().trim() );
      }
    }
    return valueList;
  }

  public static List<String> getDefenceGWManagerResultList()
  {
    List<String> valueList = new ArrayList<String>();
    List<DefenceGraduateWork> defenceGraduateWorkList = getDefenceGraduateWorkList();
    for( DefenceGraduateWork defenceGraduateWork : defenceGraduateWorkList )
    {
      if( defenceGraduateWork.getGraduateWorkInfo() != null
        && defenceGraduateWork.getGraduateWorkInfo().getManagerResult() != null
        && !valueList.contains( defenceGraduateWork.getGraduateWorkInfo().getManagerResult().trim() ) )
      {
        valueList.add( defenceGraduateWork.getGraduateWorkInfo().getManagerResult().trim() );
      }
    }
    return valueList;
  }

  public static List<Student> getBestGraduateWorkStudents()
  {
    List<DefenceGraduateWork> defenceGraduateWorkList = getDefenceGraduateWorkList();
    List<Student> result = new ArrayList<Student>();
    for( DefenceGraduateWork defenceGraduateWork : defenceGraduateWorkList )
    {
      if( defenceGraduateWork.getGraduateWorkInfo() != null && defenceGraduateWork.getGraduateWorkInfo().isBestWork() )
      {
        result.add( StudentsService.getStudentByDefenceGraduateWorkId( defenceGraduateWork.getId() ) );
      }
    }
    Collections.sort( result, new StudentsByFioComparator() );
    return result;
  }

  public static List<Student> getMagistracyStudents()
  {
    List<DefenceGraduateWork> defenceGraduateWorkList = getDefenceGraduateWorkList();
    List<Student> result = new ArrayList<Student>();
    for( DefenceGraduateWork defenceGraduateWork : defenceGraduateWorkList )
    {
      if( defenceGraduateWork.getGraduateWorkInfo() != null && defenceGraduateWork.getGraduateWorkInfo().isMagistracy() )
      {
        result.add( StudentsService.getStudentByDefenceGraduateWorkId( defenceGraduateWork.getId() ) );
      }
    }
    Collections.sort( result, new StudentsByFioComparator() );
    return result;
  }

  public static List<Student> getHounorDiplomStudents()
  {
    List<DefenceGraduateWork> defenceGraduateWorkList = getDefenceGraduateWorkList();
    List<Student> result = new ArrayList<Student>();
    for( DefenceGraduateWork defenceGraduateWork : defenceGraduateWorkList )
    {
      if( defenceGraduateWork.getGraduateWorkInfo() != null
        && "с отличием".equals( defenceGraduateWork.getGraduateWorkInfo().getOutputMark() ) )
      {
        result.add( StudentsService.getStudentByDefenceGraduateWorkId( defenceGraduateWork.getId() ) );
      }
    }
    Collections.sort( result, new StudentsByFioComparator() );
    return result;
  }
}
