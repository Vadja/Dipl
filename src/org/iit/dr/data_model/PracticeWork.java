package org.iit.dr.data_model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.iit.dr.data_model.role.Student;

/**
 * PracticeWork.
 * <p/>
 * $Id: PracticeWork.java, v 1.0 11.05.2010 17:42:19 ykarpovich Exp $ Created on 11.05.2010 17:42:19
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "practiceWork" )
public class PracticeWork extends PersistenceObject
{
  @XmlElement( name = "title" )
  String title;

  @XmlElement( name = "description" )
  String description;

  @XmlElement( name = "studentId" )
  String studentId;

  @XmlElement( name = "managerId" )
  String managerId;

  @XmlElement( name = "consultantId" )
  String consultantId;

  @XmlElement( name = "dateOfIssue" )
  Date dateOfIssue;

  @XmlElement( name = "dateOfDefence" )
  Date dateOfDefence;

  @XmlElement( name = "companyId" )
  String companyId;

  @XmlElement( name = "orderNumber" )
  String orderNumber;

  @XmlElement( name = "mark" )
  Integer mark;

  public PracticeWork()
  {

  }

  public PracticeWork( String studentId )
  {
    this.studentId = studentId;
  }

  public PracticeWork( Student student )
  {
    this( student.getId() );
  }

  @Override
  public Boolean isProxy()
  {
    return false;
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle( String title )
  {
    this.title = title;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription( String description )
  {
    this.description = description;
  }

  public String getStudentId()
  {
    return studentId;
  }

  public void setStudentId( String studentId )
  {
    this.studentId = studentId;
  }

  public String getManagerId()
  {
    return managerId;
  }

  public void setManagerId( String managerId )
  {
    this.managerId = managerId;
  }

  public String getConsultantId()
  {
    return consultantId;
  }

  public void setConsultantId( String consultantId )
  {
    this.consultantId = consultantId;
  }

  public Date getDateOfIssue()
  {
    return dateOfIssue;
  }

  public void setDateOfIssue( Date dateOfIssue )
  {
    this.dateOfIssue = dateOfIssue;
  }

  public Date getDateOfDefence()
  {
    return dateOfDefence;
  }

  public void setDateOfDefence( Date dateOfDefence )
  {
    this.dateOfDefence = dateOfDefence;
  }

  public Integer getMark()
  {
    return mark;
  }

  public void setMark( Integer mark )
  {
    this.mark = mark;
  }

  public String getCompanyId()
  {
    return companyId;
  }

  public void setCompanyId( String companyId )
  {
    this.companyId = companyId;
  }

  @Override
  public String getId()
  {
    return studentId;
  }

  public String getOrderNumber()
  {
    return orderNumber;
  }

  public void setOrderNumber( String orderNumber )
  {
    this.orderNumber = orderNumber;
  }

  @Override
  public boolean equals( Object obj )
  {
    if( obj instanceof PracticeWork )
    {
      return this.getId().equals( ( ( PracticeWork ) obj ).getId() );
    }
    return false;
  }
}
