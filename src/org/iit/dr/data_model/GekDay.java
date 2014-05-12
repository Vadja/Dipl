package org.iit.dr.data_model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

/**
 * GekDay.
 * 
 * @author Yuriy Karpovich
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "gekDay" )
public class GekDay extends PersistenceObject
{

  @XmlElement( name = "id" )
  String id;

  @XmlElement( name = "date" )
  Date date;

  @XmlElement( name = "startTime" )
  String startTime;

  @XmlElement( name = "endTime" )
  String endTime;

  @XmlElement( name = "timeForStudent" )
  String timeForStudent;

  @XmlElement( name = "studentsCount" )
  Integer studentsCount;

  public GekDay()
  {
  }

  public GekDay( String id )
  {
    this.id = id;
  }

  public String getId()
  {
    return id;
  }

  public Boolean isProxy()
  {
    return false;
  }

  public Date getDate()
  {
    return date;
  }

  public void setDate( Date date )
  {
    this.date = date;
  }

  public String getStartTime()
  {
    return startTime;
  }

  public void setStartTime( String startTime )
  {
    this.startTime = startTime;
  }

  public String getEndTime()
  {
    return endTime;
  }

  public void setEndTime( String endTime )
  {
    this.endTime = endTime;
  }

  public String getTimeForStudent()
  {
    return timeForStudent;
  }

  public void setTimeForStudent( String timeForStudent )
  {
    this.timeForStudent = timeForStudent;
  }

  public Integer getStudentsCount()
  {
    return studentsCount;
  }

  public void setStudentsCount( Integer studentsCount )
  {
    this.studentsCount = studentsCount;
  }
}
