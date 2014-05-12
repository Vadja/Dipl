package org.iit.dr.data_model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

/**
 * GraduateWork.
 * 
 * @author Yuriy Karpovich
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "graduateWork" )
public class GraduateWork extends PersistenceObject
{

  @XmlElement( name = "id" )
  String id;

  @XmlElement( name = "title" )
  String title;

  @XmlElement( name = "description" )
  String description;

  @XmlElement( name = "number" )
  String number;

  @XmlElement( name = "student" )
  String studentId;

  @XmlElement( name = "manager" )
  String managerId;

  @XmlElement( name = "specialityConsultant" )
  String consultantIdBySpeciality;

  @XmlElement( name = "economicsConsultant" )
  String consultantIdByEconomics;

  @XmlElement( name = "protectionOfLaborConsultant" )
  String consultantIdByProtectionOfLabor;

  @XmlElement( name = "normalInspectionConsultant" )
  String consultantIdByNormalInspection;

  @XmlElement( name = "reviewer" )
  String reviewerId;

  @XmlElement( name = "dateOfIssue" )
  Date dateOfIssue;

  public String getId()
  {
    return id;
  }

  public void setId( String id )
  {
    this.id = id;
  }

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

  public String getConsultantIdBySpeciality()
  {
    return consultantIdBySpeciality;
  }

  public void setConsultantIdBySpeciality( String consultantIdBySpeciality )
  {
    this.consultantIdBySpeciality = consultantIdBySpeciality;
  }

  public String getConsultantIdByEconomics()
  {
    return consultantIdByEconomics;
  }

  public void setConsultantIdByEconomics( String consultantIdByEconomics )
  {
    this.consultantIdByEconomics = consultantIdByEconomics;
  }

  public String getConsultantIdByProtectionOfLabor()
  {
    return consultantIdByProtectionOfLabor;
  }

  public void setConsultantIdByProtectionOfLabor( String consultantIdByProtectionOfLabor )
  {
    this.consultantIdByProtectionOfLabor = consultantIdByProtectionOfLabor;
  }

  public String getConsultantIdByNormalInspection()
  {
    return consultantIdByNormalInspection;
  }

  public void setConsultantIdByNormalInspection( String consultantIdByNormalInspection )
  {
    this.consultantIdByNormalInspection = consultantIdByNormalInspection;
  }

  public String getReviewerId()
  {
    return reviewerId;
  }

  public void setReviewerId( String reviewerId )
  {
    this.reviewerId = reviewerId;
  }

  public Date getDateOfIssue()
  {
    return dateOfIssue;
  }

  public void setDateOfIssue( Date dateOfIssue )
  {
    this.dateOfIssue = dateOfIssue;
  }

  public String getNumber()
  {
    return number;
  }

  public void setNumber( String number )
  {
    this.number = number;
  }

  @Override
  public String toString()
  {
    return title != null ? title.substring( 0, Math.min( 20, title.length() ) ) : "---";
  }
}
