package org.iit.dr.data_model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

/**
 * DateRange.
 * 
 * @author Yuriy Karpovich
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "dateRange" )
public class DateRange
{

  @XmlElement( name = "start" )
  private Date start;

  @XmlElement( name = "end" )
  private Date end;

  public DateRange()
  {
  }

  public DateRange( Date start, Date end )
  {
    this.start = start;
    this.end = end;
  }

  public Date getStart()
  {
    return start;
  }

  public void setStart( Date start )
  {
    this.start = start;
  }

  public Date getEnd()
  {
    return end;
  }

  public void setEnd( Date end )
  {
    this.end = end;
  }
}
