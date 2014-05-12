package org.iit.dr.data_model.role;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Student.
 * 
 * @author Yuriy Karpovich
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "student" )
public class Student extends Person
{

  public Student()
  {
  }

  public Student( String group, String firstName, String lastName, String patronymicName )
  {
    super( firstName, lastName, patronymicName );
    this.group = group;
  }

  @XmlElement( name = "ou" )
  private String group;

  @XmlElement( name = "fromCity" )
  private String fromCity;

  @XmlElement( name = "incoming" )
  private Date incoming;

  @XmlElement( name = "email" )
  private String email;

  @XmlElement( name = "phone" )
  private String phone;

  @XmlElement( name = "skype" )
  private String skype;

  @XmlElement( name = "icq" )
  private String icq;

  @XmlElement( name = "budget" )
  private Boolean budget;

  @XmlElement( name = "averageMark" )
  private double averageMark;

  @XmlElement( name = "distanseStudy" )
  private Boolean distanseStudy;

  @XmlElement( name = "personalFile" )
  private String personalFile;

  // СЃРѕС†РёР°Р»СЊРЅРѕРµ РїРѕР»РѕР¶РµРЅРёРµ
  @XmlElement( name = "sociallyPlace" )
  private String sociallyPlace;

  // С€РєРѕР»Р° Рё С‚.Рґ. РєРѕС‚РѕСЂСѓСЋ РѕРєРѕРЅС‡РёР»
  @XmlElement( name = "school" )
  private String school;

  // РіРѕСЂРѕРґ РІ РєРѕС‚РѕСЂРѕРј РѕРєРѕРЅС‡РёР» С€РєРѕР»Сѓ
  @XmlElement( name = "citySchool" )
  private String citySchool;

  // РіРѕРґ РѕРєРѕРЅС‡Р°РЅРёСЏ С€РєРѕР»С‹
  @XmlElement( name = "yearSchool" )
  private String yearSchool;

  // СЃРІРµРґРµРЅРёСЏ Рѕ СЂРѕРґРёС‚РµР»СЏС…

  // РјР°С‚СЊ

  private String fioMother;

  @XmlElement( name = "workMother" )
  private String workMother;

  @XmlElement( name = "positionMother" )
  private String positionMother;

  @XmlElement( name = "ageMother" )
  private String ageMother;

  @XmlElement( name = "cityMother" )
  private String cityMother;

  // РѕС‚РµС†

  @XmlElement( name = "fioFather" )
  private String fioFather;

  @XmlElement( name = "workFather" )
  private String workFather;

  @XmlElement( name = "positionFather" )
  private String positionFather;

  @XmlElement( name = "ageFather" )
  private String ageFather;

  @XmlElement( name = "cityFather" )
  private String cityFather;

  public String getPersonalFile()
  {
    return personalFile;
  }

  public void setPersonalFile( String personalFile )
  {
    this.personalFile = personalFile;
  }

  public String getSociallyPlace()
  {
    return sociallyPlace;
  }

  public void setSociallyPlace( String sociallyPlace )
  {
    this.sociallyPlace = sociallyPlace;
  }

  public String getSchool()
  {
    return school;
  }

  public void setSchool( String school )
  {
    this.school = school;
  }

  public String getCitySchool()
  {
    return citySchool;
  }

  public void setCitySchool( String citySchool )
  {
    this.citySchool = citySchool;
  }

  public String getYearSchool()
  {
    return yearSchool;
  }

  public void setYearSchool( String yearSchool )
  {
    this.yearSchool = yearSchool;
  }

  public String getFioMother()
  {
    return fioMother;
  }

  public void setFioMother( String fioMother )
  {
    this.fioMother = fioMother;
  }

  public String getWorkMother()
  {
    return workMother;
  }

  public void setWorkMother( String workMother )
  {
    this.workMother = workMother;
  }

  public String getPositionMother()
  {
    return positionMother;
  }

  public void setPositionMother( String positionMother )
  {
    this.positionMother = positionMother;
  }

  public String getAgeMother()
  {
    return ageMother;
  }

  public void setAgeMother( String ageMother )
  {
    this.ageMother = ageMother;
  }

  public String getCityMother()
  {
    return cityMother;
  }

  public void setCityMother( String cityMother )
  {
    this.cityMother = cityMother;
  }

  public String getFioFather()
  {
    return fioFather;
  }

  public void setFioFather( String fioFather )
  {
    this.fioFather = fioFather;
  }

  public String getWorkFather()
  {
    return workFather;
  }

  public void setWorkFather( String workFather )
  {
    this.workFather = workFather;
  }

  public String getPositionFather()
  {
    return positionFather;
  }

  public void setPositionFather( String positionFather )
  {
    this.positionFather = positionFather;
  }

  public String getAgeFather()
  {
    return ageFather;
  }

  public void setAgeFather( String ageFather )
  {
    this.ageFather = ageFather;
  }

  public String getCityFather()
  {
    return cityFather;
  }

  public void setCityFather( String cityFather )
  {
    this.cityFather = cityFather;
  }

  public String getPrikaz()
  {
    return prikaz;
  }

  public void setPrikaz( String prikaz )
  {
    this.prikaz = prikaz;
  }

  public Date getPrikazDate()
  {
    return prikazDate;
  }

  public void setPrikazDate( Date prikazDate )
  {
    this.prikazDate = prikazDate;
  }

  // РЅРѕРјРµСЂ РїСЂРёРєР°Р·Р° Рѕ Р·Р°С‡РёСЃР»РµРЅРёРё
  @XmlElement( name = "prikaz" )
  private String prikaz;

  // РґР°С‚Р° РїСЂРёРєР°Р·Р° Рѕ Р·Р°С‡РёСЃР»РµРЅРёРё
  @XmlElement( name = "prikazDate" )
  private Date prikazDate;

  public String getGroup()
  {
    return group;
  }

  public Boolean getDistanseStudy()
  {
    return distanseStudy;
  }

  public void setDistanseStudy( Boolean distanseStudy )
  {
    this.distanseStudy = distanseStudy;
  }

  public void setGroup( String group )
  {
    this.group = group;
  }

  public String getFromCity()
  {
    return fromCity;
  }

  public void setFromCity( String fromCity )
  {
    this.fromCity = fromCity;
  }

  public Date getIncoming()
  {
    return incoming;
  }

  public void setIncoming( Date incoming )
  {
    this.incoming = incoming;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail( String email )
  {
    this.email = email;
  }

  public String getPhone()
  {
    return phone;
  }

  public void setPhone( String phone )
  {
    this.phone = phone;
  }

  public String getSkype()
  {
    return skype;
  }

  public void setSkype( String skype )
  {
    this.skype = skype;
  }

  public String getIcq()
  {
    return icq;
  }

  public void setIcq( String icq )
  {
    this.icq = icq;
  }

  public Boolean getBudget()
  {
    if( budget == null )
    {
      return false;
    }
    return budget;
  }

  public void setBudget( Boolean budget )
  {
    this.budget = budget;
  }

  public double getAverageMark()
  {
    return averageMark;
  }

  public void setAverageMark( double averageMark )
  {
    this.averageMark = averageMark;
  }

  @Override
  public boolean equals( Object o )
  {
    if( this == o )
      return true;
    if( o == null || getClass() != o.getClass() )
      return false;

    Student student = ( Student ) o;

    return getId().equals( student.getId() );
  }
}
