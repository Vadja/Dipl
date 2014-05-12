package org.iit.dr.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.iit.dr.data_model.DefenceGraduateWork;
import org.iit.dr.data_model.Gek;
import org.iit.dr.data_model.GekDay;
import org.iit.dr.data_model.StatisticaGek;
import org.iit.dr.utils.DateUtils;

public class GekService extends CommonService
{
  public static Gek getGek()
  {
    return dataConnector.getGek();
  }

  public static List<String> getGekStafferList()
  {
    Gek gek = dataConnector.getGek();
    List<String> stafferIdList = new ArrayList<String>();
    if( gek.getPresideId() != null )
    {
      stafferIdList.add( gek.getPresideId() );
    }
    if( gek.getCommissionerSecrId() != null )
    {
      stafferIdList.add( gek.getCommissionerSecrId() );
    }
    if( gek.getCommissioner0Id() != null )
    {
      stafferIdList.add( gek.getCommissioner0Id() );
    }
    if( gek.getCommissioner1Id() != null )
    {
      stafferIdList.add( gek.getCommissioner1Id() );
    }
    if( gek.getCommissioner2Id() != null )
    {
      stafferIdList.add( gek.getCommissioner2Id() );
    }
    if( gek.getCommissioner3Id() != null )
    {
      stafferIdList.add( gek.getCommissioner3Id() );
    }
    if( gek.getCommissioner4Id() != null )
    {
      stafferIdList.add( gek.getCommissioner4Id() );
    }
    if( gek.getCommissioner5Id() != null )
    {
      stafferIdList.add( gek.getCommissioner5Id() );
    }
    if( gek.getCommissioner6Id() != null )
    {
      stafferIdList.add( gek.getCommissioner6Id() );
    }
    if( gek.getCommissioner7Id() != null )
    {
      stafferIdList.add( gek.getCommissioner7Id() );
    }
    if( gek.getCommissioner8Id() != null )
    {
      stafferIdList.add( gek.getCommissioner8Id() );
    }
    if( gek.getCommissioner9Id() != null )
    {
      stafferIdList.add( gek.getCommissioner9Id() );
    }
    return stafferIdList;
  }

  public static boolean removeGekDay( String guid )
  {
    for( GekDay gekDayItem : dataConnector.getGek().getGekDayList() )
    {
      if( gekDayItem.getId().equals( guid ) )
      {
        dataConnector.getGek().getGekDayList().remove( gekDayItem );
        return true;
      }
    }
    return false;
  }

  public static boolean updateFullGek()
  {
    Gek gek = getGek();
    List<DefenceGraduateWork> defenceGraduateWorkList = GraduateWorkService.getDefenceGraduateWorkList();
    for( DefenceGraduateWork defenceGraduateWork : defenceGraduateWorkList )
    {
      defenceGraduateWork.setPresideId( gek.getPresideId() );
      defenceGraduateWork.setCommissionerSecrId( gek.getCommissionerSecrId() );
      defenceGraduateWork.setCommissioner0Id( gek.getCommissioner0Id() );
      defenceGraduateWork.setCommissioner1Id( gek.getCommissioner1Id() );
      defenceGraduateWork.setCommissioner2Id( gek.getCommissioner2Id() );
      defenceGraduateWork.setCommissioner3Id( gek.getCommissioner3Id() );
      defenceGraduateWork.setCommissioner4Id( gek.getCommissioner4Id() );
      defenceGraduateWork.setCommissioner5Id( gek.getCommissioner5Id() );
      defenceGraduateWork.setCommissioner6Id( gek.getCommissioner6Id() );
      defenceGraduateWork.setCommissioner7Id( gek.getCommissioner7Id() );
      defenceGraduateWork.setCommissioner8Id( gek.getCommissioner8Id() );
      defenceGraduateWork.setCommissioner9Id( gek.getCommissioner9Id() );
    }
    return true;
  }

  @SuppressWarnings("deprecation")
public static ArrayList<ArrayList<Date>> getGekDateList()
  {
    Gek gek = getGek();
    List<GekDay> days= gek.getGekDayList();

	  ArrayList<ArrayList<Date>> temp= new ArrayList<ArrayList<Date>>();
    for( GekDay gekDay : days )
    {
      if( gekDay != null && gekDay.getDate() != null )
      {
    	  ArrayList<Date> dateList = new ArrayList<Date>();
    	
    	  System.out.print(gekDay.getDate().getYear()+"\n");
    	  System.out.print(gekDay.getDate().getMonth()+"\n");
    	  System.out.print(gekDay.getDate().getDate()+"\n");
    	  
    	  Date newDate = new Date(gekDay.getDate().getYear(),
    			  gekDay.getDate().getMonth(),
    			  gekDay.getDate().getDate(),
    			  Integer.parseInt(gekDay.getStartTime().split(":")[0]),
    			  Integer.parseInt(gekDay.getStartTime().split(":")[1]));
    	  
    	  Date endDate = new Date(gekDay.getDate().getYear(),
    			  gekDay.getDate().getMonth(),
    			  gekDay.getDate().getDate(),
    			  Integer.parseInt(gekDay.getEndTime().split(":")[0]),
    			  Integer.parseInt(gekDay.getEndTime().split(":")[1]));
    	  
    	  
        dateList.add( newDate );
        dateList.add( endDate );
  	  	temp.add(dateList);
      }
    }
    
//    Collections.sort( temp );
    return temp;
  }

  public static boolean applyProtokolNumers( List<String> dateList )
  {
    Gek gek = getGek();
    Integer defenceGraduateWorkProtokolNumber = GraduateWorkService.getDefenceGraduateWorkProtokolNumber();
    List<GekDay> gekDayList = new ArrayList<GekDay>();
    for( GekDay gekDay : gek.getGekDayList() )
    {
      for( String gekId : dateList )
      {
        if( gekDay != null && gekDay.getId().equals( gekId ) )
        {
          gekDayList.add( gekDay );
          break;
        }
      }
    }
    for( GekDay gekDay : gekDayList )
    {
      String startTime =
        gekDay.getStartTime() != null && gekDay.getStartTime().length() != 0 ? gekDay.getStartTime() : "09:00";
      String timeForStudent =
        gekDay.getTimeForStudent() != null && gekDay.getTimeForStudent().length() != 0 ? gekDay.getTimeForStudent()
          : "30";
      for( DefenceGraduateWork defenceGraduateWorkItem : GraduateWorkService.getDefenceGraduateWorkListOrderByStudent() )
      {
        if( DateUtils.checkDate( defenceGraduateWorkItem.getActualDate(), gekDay.getDate() )
          && defenceGraduateWorkItem.getProtocolNumber() == null )
        {
          String endTime = DateUtils.nextTime( startTime, timeForStudent );
          defenceGraduateWorkProtokolNumber++;
          defenceGraduateWorkItem.setProtocolNumber( ""
            + ( defenceGraduateWorkProtokolNumber < 10 ? "0" + defenceGraduateWorkProtokolNumber
              : defenceGraduateWorkProtokolNumber ) );
          defenceGraduateWorkItem.setStartTime( startTime );
          defenceGraduateWorkItem.setEndTime( endTime );
          startTime = endTime;
        }
      }
    }
    return true;
  }

  public static StatisticaGek getStatisticaGek()
  {
    StatisticaGek statisticaGek = new StatisticaGek();
    Integer commonWork = 0;
    Integer m5Work = 0;
    Integer m4Work = 0;
    Integer m3Work = 0;
    Integer m2Work = 0;
    Integer pres = 0;
    Integer vndr = 0;
    String withHonours = "";
    String magistr = "";
    for( DefenceGraduateWork defenceGraduateWork : dataConnector.getDefenceGraduateWorkList() )
    {
      Integer mark = defenceGraduateWork.getGraduateWorkInfo().getMark();
      if( mark != null )
      {
        commonWork++;
        // mark
        switch( mark )
        {
          case 10:
          case 9:
            m5Work++;
            break;
          case 8:
          case 7:
          case 6:
            m4Work++;
            break;
          case 5:
          case 4:
            m3Work++;
            break;
          case 3:
          case 2:
          case 1:
          case 0:
            m2Work++;
            break;
        }

        //
        if( defenceGraduateWork.getGraduateWorkInfo().isCompPresent() != null
          && defenceGraduateWork.getGraduateWorkInfo().isCompPresent() )
        {
          pres++;
        }
      }
    }
    statisticaGek.setCWork( "" + commonWork );
    statisticaGek.setCWorkP( "" + commonWork / ( double ) commonWork );
    return statisticaGek;
  }
}
