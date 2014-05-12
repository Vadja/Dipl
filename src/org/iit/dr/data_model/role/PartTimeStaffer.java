package org.iit.dr.data_model.role;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * PartTimeStaffer. (Почасовик)
 * 
 * @author Yuriy Karpovich
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "partTimeStaffer" )
public class PartTimeStaffer extends Teacher
{

  /**
   * Должность основная
   */
  @XmlElement( name = "originalPosition" )
  String originalPosition;

  @XmlElement( name = "workPlace" )
  String workPlace;

  @XmlElement( name = "dateOfBirthday" )
  Date dateOfBirthday;

  @XmlElement( name = "address" )
  String address;

  @XmlElement( name = "phoneHome" )
  String phoneHome;

  @XmlElement( name = "phoneWork" )
  String phoneWork;

  @XmlElement( name = "phoneMobile" )
  String phoneMobile;

  @XmlElement( name = "education" )
  String education;

  @XmlElement( name = "specialty" )
  String specialty;

  @XmlElement( name = "childrenCount" )
  String childrenCount;

  @XmlElement( name = "unpWork" )
  String unpWork;

  @XmlElement( name = "pensionBook" )
  String pensionBook;

  @XmlElement( name = "pensionAgency" )
  String pensionAgency;

  @XmlElement( name = "prorektor" )
  Boolean prorektor;

  public String getOriginalPosition()
  {
    return originalPosition;
  }

  public void setOriginalPosition( String originalPosition )
  {
    this.originalPosition = originalPosition;
  }

  public String getWorkPlace()
  {
    return workPlace;
  }

  public void setWorkPlace( String workPlace )
  {
    this.workPlace = workPlace;
  }

  public Date getDateOfBirthday()
  {
    return dateOfBirthday;
  }

  public void setDateOfBirthday( Date dateOfBirthday )
  {
    this.dateOfBirthday = dateOfBirthday;
  }

  @Override
  public String getAddress()
  {
    return address;
  }

  @Override
  public void setAddress( String address )
  {
    this.address = address;
  }

  public String getPhoneHome()
  {
    return phoneHome;
  }

  public void setPhoneHome( String phoneHome )
  {
    this.phoneHome = phoneHome;
  }

  public String getPhoneWork()
  {
    return phoneWork;
  }

  public void setPhoneWork( String phoneWork )
  {
    this.phoneWork = phoneWork;
  }

  public String getPhoneMobile()
  {
    return phoneMobile;
  }

  public void setPhoneMobile( String phoneMobile )
  {
    this.phoneMobile = phoneMobile;
  }

  public String getEducation()
  {
    return education;
  }

  public void setEducation( String education )
  {
    this.education = education;
  }

  public String getSpecialty()
  {
    return specialty;
  }

  public void setSpecialty( String specialty )
  {
    this.specialty = specialty;
  }

  public String getChildrenCount()
  {
    return childrenCount;
  }

  public void setChildrenCount( String childrenCount )
  {
    this.childrenCount = childrenCount;
  }

  public String getUnpWork()
  {
    return unpWork;
  }

  public void setUnpWork( String unpWork )
  {
    this.unpWork = unpWork;
  }

  public String getPensionBook()
  {
    return pensionBook;
  }

  public void setPensionBook( String pensionBook )
  {
    this.pensionBook = pensionBook;
  }

  public String getPensionAgency()
  {
    return pensionAgency;
  }

  public void setPensionAgency( String pensionAgency )
  {
    this.pensionAgency = pensionAgency;
  }

  public Boolean isProrektor()
  {
    return prorektor;
  }

  public void setProrektor( Boolean prorektor )
  {
    this.prorektor = prorektor;
  }

  public String getFioAndWorkPlace()
  {
    String result = getShortName();
    if( getOriginalPosition() != null )
    {
      result += ", " + getOriginalPosition();
    }
    if( getWorkPlace() != null )
    {
      result += ", " + getWorkPlace();
    }
    if( getDegree() != null )
    {
      result += getDegree().getRusNameShort();
    }
    return result;
  }
}
