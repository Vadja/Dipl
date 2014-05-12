package org.iit.dr.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DateUtils.
 * 
 * @author Yuriy Karpovich
 */
public class DateUtils
{

  private static final String SEPARATOR_PATTERN = "\\.";

  private static final String DATE_FORMAT = "dd.MM.yyyy";

  public static Date dateFromString( String dateString )
  {

    if( dateString != null && dateString.length() != 0 )
    {

      String[] dateParts = dateString.trim().split( SEPARATOR_PATTERN );

      if( dateParts.length == 3 )
      {

        Calendar calendar = Calendar.getInstance();
        calendar.set( Integer.parseInt( dateParts[2] ), Integer.parseInt( dateParts[1] ) - 1,
          Integer.parseInt( dateParts[0] ), 0, 0, 0 );

        return calendar.getTime();
      }
    }

    return null;
  }

  public static String dateToString( Date date )
  {

    if( date != null )
    {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime( date );

      SimpleDateFormat simpleDateFormat = new SimpleDateFormat( DATE_FORMAT );
      return simpleDateFormat.format( date );
    }
    else
    {

      return "";
    }
  }

  public static String dateToFullString( Date date )
  {

    if( date != null )
    {

      return dateToDay( date ) + " " + dateToMonth( date ) + " " + dateToYear( date );
    }
    else
    {

      return "";
    }
  }

  public static String dateToDay( Date date )
  {

    if( date != null )
    {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime( date );

      SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "dd" );
      return simpleDateFormat.format( date );
    }
    else
    {

      return "";
    }
  }

  public static String dateToYear( Date date )
  {

    if( date != null )
    {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime( date );

      SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy" );
      return simpleDateFormat.format( date );
    }
    else
    {

      return "";
    }
  }

  public static String dateToMonth( Date date )
  {

    if( date != null )
    {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime( date );

      switch( calendar.get( Calendar.MONTH ) )
      {
        case 0:
          return "Января";
        case 1:
          return "Февраля";
        case 2:
          return "Марта";
        case 3:
          return "Апреля";
        case 4:
          return "Мая";
        case 5:
          return "Июня";
        case 6:
          return "Июля";
        case 7:
          return "Августа";
        case 8:
          return "Сентября";
        case 9:
          return "Октября";
        case 10:
          return "Ноября";
        case 11:
          return "Декабря";
      }

      return "";
    }
    else
    {

      return "";
    }
  }

  public static String calculateTimeForStudent( String startTime, String endTime, Integer studentsCount )
  {

    if( startTime == null || startTime.length() == 0 || endTime == null || endTime.length() == 0
      || studentsCount == null || studentsCount == 0 )
    {
      return null;
    }

    String[] startTimeParts = startTime.split( ":" );
    String[] endTimeParts = endTime.split( ":" );

    if( startTimeParts.length < 2 || endTimeParts.length < 2 )
    {

      return null;
    }

    try
    {
      Integer startTimeHour = Integer.parseInt( startTimeParts[0] );
      Integer startTimeMinute = Integer.parseInt( startTimeParts[1] );

      Integer endTimeHour = Integer.parseInt( endTimeParts[0] );
      Integer endTimeMinute = Integer.parseInt( endTimeParts[1] );

      Integer commonTime = 60 * ( endTimeHour - startTimeHour ) + ( endTimeMinute - startTimeMinute );

      return String.valueOf( commonTime / studentsCount );

    }
    catch( Exception e )
    {

    }

    return null;
  }

  public static Integer calculateStudentsOnDay( String startTime, String endTime, String timeForStudentAsString )
  {

    if( startTime == null || startTime.length() == 0 || endTime == null || endTime.length() == 0
      || timeForStudentAsString == null || timeForStudentAsString.length() == 0 )
    {

      return null;
    }

    String[] startTimeParts = startTime.split( ":" );
    String[] endTimeParts = endTime.split( ":" );

    if( startTimeParts.length < 2 || endTimeParts.length < 2 )
    {

      return null;
    }

    try
    {
      Integer startTimeHour = Integer.parseInt( startTimeParts[0] );
      Integer startTimeMinute = Integer.parseInt( startTimeParts[1] );

      Integer endTimeHour = Integer.parseInt( endTimeParts[0] );
      Integer endTimeMinute = Integer.parseInt( endTimeParts[1] );

      Integer timeForStudent = Integer.parseInt( timeForStudentAsString );

      Integer commonTime = 60 * ( endTimeHour - startTimeHour ) + ( endTimeMinute - startTimeMinute );

      return commonTime / timeForStudent;

    }
    catch( Exception e )
    {

    }

    return null;
  }

  public static boolean checkDate( Date actualDate, Date filterDate )
  {

    if( actualDate != null && filterDate != null )
    {

      Calendar actualCalendar = Calendar.getInstance();
      actualCalendar.setTime( actualDate );

      Calendar filterCalendar = Calendar.getInstance();
      filterCalendar.setTime( filterDate );

      return actualCalendar.get( Calendar.YEAR ) == filterCalendar.get( Calendar.YEAR )
        && actualCalendar.get( Calendar.MONTH ) == filterCalendar.get( Calendar.MONTH )
        && actualCalendar.get( Calendar.DAY_OF_MONTH ) == filterCalendar.get( Calendar.DAY_OF_MONTH );
    }

    return false;
  }

  public static String nextTime( String startTime, String timeForStudentAsString )
  {

    if( startTime == null || startTime.length() == 0 || timeForStudentAsString == null
      || timeForStudentAsString.length() == 0 )
    {

      return null;
    }

    try
    {

      String[] startTimeParts = startTime.split( ":" );

      if( startTimeParts.length < 2 )
      {

        return null;
      }

      Integer startTimeHour = Integer.parseInt( startTimeParts[0] );
      Integer startTimeMinute = Integer.parseInt( startTimeParts[1] );

      Integer timeForStudent = Integer.parseInt( timeForStudentAsString );

      startTimeMinute += timeForStudent;

      if( startTimeMinute >= 60 )
      {

        startTimeHour++;
        startTimeMinute -= 60;
      }

      return "" + ( startTimeHour < 10 ? "0" + startTimeHour : startTimeHour ) + ":"
        + ( startTimeMinute < 10 ? "0" + startTimeMinute : startTimeMinute );

    }
    catch( Exception e )
    {

    }

    return null;
  }
}
