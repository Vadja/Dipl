package org.iit.dr.data_model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

/**
 * Passport.
 * 
 * @author Yuriy Karpovich
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "passport" )
public class Passport
{

  @XmlElement( name = "number" )
  private String number;

  @XmlElement( name = "privateNumber" )
  private String privateNumber;

  @XmlElement( name = "dateIssue" )
  private Date dateIssue;

  @XmlElement( name = "placeIssue" )
  private String placeIssue;

  @XmlElement( name = "insuranceNumber" )
  private String insuranceNumber;

  public String getNumber()
  {
    return number;
  }

  public void setNumber( String number )
  {
    this.number = number;
  }

  public String getPrivateNumber()
  {
    return privateNumber;
  }

  public void setPrivateNumber( String privateNumber )
  {
    this.privateNumber = privateNumber;
  }

  public Date getDateIssue()
  {
    return dateIssue;
  }

  public void setDateIssue( Date dateIssue )
  {
    this.dateIssue = dateIssue;
  }

  public String getPlaceIssue()
  {
    return placeIssue;
  }

  public void setPlaceIssue( String placeIssue )
  {
    this.placeIssue = placeIssue;
  }

  public String getInsuranceNumber()
  {
    return insuranceNumber;
  }

  public void setInsuranceNumber( String insuranceNumber )
  {
    this.insuranceNumber = insuranceNumber;
  }
}
