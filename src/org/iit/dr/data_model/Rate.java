package org.iit.dr.data_model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Rate.
 * 
 * @author Yuriy Karpovich
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "rate" )
public class Rate extends PersistenceObject
{

  @XmlElement( name = "id" )
  String id;

  @XmlElement( name = "rateType" )
  RateType rateType;

  @XmlElement( name = "amount" )
  Double amount;

  @XmlElement( name = "departament" )
  Departament departament;

  @XmlElement( name = "subject" )
  String subject;

  @XmlElement( name = "countHour" )
  Integer countHour;

  public Departament getDepartament()
  {
    return departament;
  }

  public void setDepartament( Departament departament )
  {
    this.departament = departament;
  }

  public String getSubject()
  {
    return subject;
  }

  public void setSubject( String subject )
  {
    this.subject = subject;
  }

  public Integer getCountHour()
  {
    return countHour;
  }

  public void setCountHour( Integer countHour )
  {
    this.countHour = countHour;
  }

  @XmlElement( name = "teacher" )
  String teacher;

  @XmlElement( name = "range" )
  DateRange range;

  public void setId( String id )
  {
    this.id = id;
  }

  @Override
  public String getId()
  {

    return id;
  }

  @Override
  public Boolean isProxy()
  {

    return false;
  }

  public RateType getRateType()
  {
    return rateType;
  }

  public void setRateType( RateType rateType )
  {
    this.rateType = rateType;
  }

  public String getTeacher()
  {
    return teacher;
  }

  public void setTeacher( String teacher )
  {
    this.teacher = teacher;
  }

  public DateRange getRange()
  {
    return range;
  }

  public void setRange( DateRange range )
  {
    this.range = range;
  }

  public Double getAmount()
  {
    return amount;
  }

  public void setAmount( Double amount )
  {
    this.amount = amount;
  }

  @Override
  public Rate clone()
  {
    Rate rate = new Rate();
    rate.setId( this.id );
    rate.setAmount( this.amount );
    rate.setRateType( this.rateType );
    rate.setCountHour( this.countHour );
    rate.setDepartament( this.departament );
    rate.setSubject( this.subject );
    if( this.range != null )
    {
      rate.setRange( new DateRange( this.range.getStart(), this.range.getEnd() ) );
    }
    else
    {
      rate.setRange( null );
    }
    rate.setTeacher( teacher );
    return rate;
  }
}
