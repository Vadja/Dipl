package org.iit.dr.data_model.role;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.iit.dr.data_model.PersistenceObject;
import org.iit.dr.data_model.Sex;

/**
 * Person.
 * 
 * @author Yuriy Karpovich
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "person" )
public class Person extends PersistenceObject implements Comparable<Person>
{

  public Person()
  {
  }

  public Person( String firstName, String lastName, String patronymicName )
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.patronymicName = patronymicName;
  }

  @XmlElement( name = "id" )
  protected String id;

  // имя
  @XmlElement( name = "firstName" )
  protected String firstName;

  // фамилия
  @XmlElement( name = "lastName" )
  protected String lastName;

  // отчество
  @XmlElement( name = "patronymicName" )
  protected String patronymicName;

  // пол
  @XmlElement( name = "sex" )
  protected Sex sex;

  // дата рождения
  @XmlElement( name = "birthDate" )
  protected Date birthDate;

  @XmlElement( name = "email" )
  protected String email;

  @XmlElement( name = "homePhone" )
  protected String homePhone;

  @XmlElement( name = "mobilePhone" )
  protected String mobilePhone;

  @XmlElement( name = "skype" )
  protected String skype;

  @XmlElement( name = "icq" )
  protected String icq;

  // место жительства
  @XmlElement( name = "address" )
  protected String address;

  // гражданство
  @XmlElement( name = "nationality" )
  protected String nationality;

  // семейное положение
  @XmlElement( name = "familyState" )
  protected String familyState;

  // количество детей
  @XmlElement( name = "countChildren" )
  protected String countChildren;

  // место прописки
  @XmlElement( name = "registrationAddress" )
  protected String registrationAddress;

  public String getRegistrationAddress()
  {
    return registrationAddress;
  }

  public void setRegistrationAddress( String registrationAddress )
  {
    this.registrationAddress = registrationAddress;
  }

  public String getFamilyState()
  {
    return familyState;
  }

  public void setFamilyState( String familyState )
  {
    this.familyState = familyState;
  }

  public String getCountChildren()
  {
    return countChildren;
  }

  public void setCountChildren( String countChildren )
  {
    this.countChildren = countChildren;
  }

  protected Boolean proxy;

  @Override
  public String getId()
  {
    return id;
  }

  public void setId( String id )
  {
    this.id = id;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName( String firstName )
  {
    this.firstName = firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setLastName( String lastName )
  {
    this.lastName = lastName;
  }

  public String getPatronymicName()
  {
    return patronymicName;
  }

  public void setPatronymicName( String patronymicName )
  {
    this.patronymicName = patronymicName;
  }

  public Sex getSex()
  {
    return sex;
  }

  public void setSex( Sex sex )
  {
    this.sex = sex;
  }

  public Date getBirthDate()
  {
    return birthDate;
  }

  public void setBirthDate( Date birthDate )
  {
    this.birthDate = birthDate;
  }

  public String getAddress()
  {
    return address;
  }

  public void setAddress( String address )
  {
    this.address = address;
  }

  public String getNationality()
  {
    return nationality;
  }

  public void setNationality( String nationality )
  {
    this.nationality = nationality;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail( String email )
  {
    this.email = email;
  }

  public String getHomePhone()
  {
    return homePhone;
  }

  public void setHomePhone( String homePhone )
  {
    this.homePhone = homePhone;
  }

  public String getMobilePhone()
  {
    return mobilePhone;
  }

  public void setMobilePhone( String mobilePhone )
  {
    this.mobilePhone = mobilePhone;
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

  public String getShortName()
  {
    StringBuilder shortName = new StringBuilder();
    shortName.append( lastName ).append( " " );
    if( firstName != null && firstName.length() > 0 )
    {
      shortName.append( firstName.substring( 0, 1 ) ).append( "." );
    }
    if( patronymicName != null && patronymicName.length() > 0 )
    {
      shortName.append( patronymicName.substring( 0, 1 ) ).append( "." );
    }

    return shortName.toString();
  }

  public String getFullName()
  {

    return new StringBuilder().append( lastName ).append( " " ).append( firstName ).append( " " ).append(
      patronymicName ).toString();
  }

  @Override
  public Boolean isProxy()
  {
    return proxy;
  }

  public void setProxy( Boolean proxy )
  {
    this.proxy = proxy;
  }

  @Override
  public String toString()
  {
    return getShortName();
  }

  public int compareTo( Person arg0 )
  {
    return getFirstName().compareTo( arg0.getFullName() );
  }
}
