package org.iit.dr.data_model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "company" )
public class Company extends PersistenceObject
{
  @XmlElement( name = "id" )
  private String id;

  @XmlElement( name = "shortName" )
  private String shortName;

  @XmlElement( name = "fullName" )
  private String fullName;

  @XmlElement( name = "postCode" )
  private String postCode;

  @XmlElement( name = "city" )
  private String city;

  @XmlElement( name = "address" )
  private String address;

  @XmlElement( name = "phoneNumber" )
  private String phoneNumber;

  @XmlElement( name = "email" )
  private String email;

  @XmlElement( name = "director" )
  private String director;

  public String getName()
  {
    return shortName;
  }

  @Override
  public String getId()
  {
    return id;
  }

  public void setId( String id )
  {
    this.id = id;
  }

  public String getShortName()
  {
    return shortName;
  }

  public void setShortName( String name )
  {
    this.shortName = name;
  }

  public String getFullName()
  {
    return fullName;
  }

  public void setFullName( String fullName )
  {
    this.fullName = fullName;
  }

  public void setCity( String city )
  {
    this.city = city;
  }

  public String getCity()
  {
    return city;
  }

  public String getAddress()
  {
    return address;
  }

  public String getFullAddress()
  {
    if( city == null )
    {
      return address == null ? "" : address;
    }
    else
    {
      return city + ( address == null ? "" : ", " + address );
    }
  }

  public String getFullNameWithAddressAndPostCode()
  {
    String result = fullName;
    if( postCode != null )
      result += ", " + postCode;
    return result + ", " + getFullAddress();
  }

  public String getFullNameWithCity()
  {
    return fullName + ( city == null ? "" : ", " + city );
  }

  public void setAddresss( String address )
  {
    this.address = address;
  }

  public void setPostCode( String postCode )
  {
    this.postCode = postCode;
  }

  public String getPostCode()
  {
    return postCode;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public void setPhoneNumber( String phoneNumber )
  {
    this.phoneNumber = phoneNumber;
  }

  public String getDirector()
  {
    return director;
  }

  public void setDirector( String director )
  {
    this.director = director;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail( String email )
  {
    this.email = email;
  }

  @Override
  public Boolean isProxy()
  {
    return false;
  }

  @Override
  public String toString()
  {
    return shortName;
  }

  @Override
  public boolean equals( Object company )
  {
    if( company instanceof Company )
    {
      return this.id.equals( ( ( Company ) company ).getId() );
    }
    return false;
  }
}
