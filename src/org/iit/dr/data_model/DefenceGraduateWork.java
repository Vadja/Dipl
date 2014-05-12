package org.iit.dr.data_model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

/**
 * DefenceGraduateWork.
 * <p/>
 * $Id: DefenceGraduateWork.java, v 1.0 11.05.2010 18:23:51 ykarpovich Exp $ Created on 11.05.2010 18:23:51
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "defenceGraduateWork" )
public class DefenceGraduateWork extends PersistenceObject
{

  @XmlElement( name = "id" )
  String id;

  @XmlElement( name = "protocolNumber" )
  String protocolNumber;

  @XmlElement( name = "graduateWorkId" )
  String graduateWorkId;

  @XmlElement( name = "actualDate" )
  Date actualDate;

  @XmlElement( name = "startTime" )
  String startTime;

  @XmlElement( name = "endTime" )
  String endTime;

  @XmlElement( name = "presideId" )
  String presideId;

  @XmlElement( name = "commissionerSecrId" )
  String commissionerSecrId;

  @XmlElement( name = "commissioner0Id" )
  String commissioner0Id;

  @XmlElement( name = "commissioner1Id" )
  String commissioner1Id;

  @XmlElement( name = "commissioner2Id" )
  String commissioner2Id;

  @XmlElement( name = "commissioner3Id" )
  String commissioner3Id;

  @XmlElement( name = "commissioner4Id" )
  String commissioner4Id;

  @XmlElement( name = "commissioner5Id" )
  String commissioner5Id;

  @XmlElement( name = "commissioner6Id" )
  String commissioner6Id;

  @XmlElement( name = "commissioner7Id" )
  String commissioner7Id;

  @XmlElement( name = "commissioner8Id" )
  String commissioner8Id;

  @XmlElement( name = "commissioner9Id" )
  String commissioner9Id;

  @XmlElement( name = "graduateWorkInfo" )
  GraduateWorkInfo graduateWorkInfo;

  public Boolean isProxy()
  {
    return false;
  }

  public String getId()
  {
    return id;
  }

  public void setId( String id )
  {
    this.id = id;
  }

  public String getProtocolNumber()
  {
    return protocolNumber;
  }

  public void setProtocolNumber( String protocolNumber )
  {
    this.protocolNumber = protocolNumber;
  }

  public String getGraduateWorkId()
  {
    return graduateWorkId;
  }

  public void setGraduateWorkId( String graduateWorkId )
  {
    this.graduateWorkId = graduateWorkId;
  }

  public Date getActualDate()
  {
    return actualDate;
  }

  public void setActualDate( Date actualDate )
  {
    this.actualDate = actualDate;
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

  public String getPresideId()
  {
    return presideId;
  }

  public void setPresideId( String presideId )
  {
    this.presideId = presideId;
  }

  public String getCommissionerSecrId()
  {
    return commissionerSecrId;
  }

  public void setCommissionerSecrId( String commissionerSecrId )
  {
    this.commissionerSecrId = commissionerSecrId;
  }

  public String getCommissioner0Id()
  {
    return commissioner0Id;
  }

  public void setCommissioner0Id( String commissioner0Id )
  {
    this.commissioner0Id = commissioner0Id;
  }

  public String getCommissioner1Id()
  {
    return commissioner1Id;
  }

  public void setCommissioner1Id( String commissioner1Id )
  {
    this.commissioner1Id = commissioner1Id;
  }

  public String getCommissioner2Id()
  {
    return commissioner2Id;
  }

  public void setCommissioner2Id( String commissioner2Id )
  {
    this.commissioner2Id = commissioner2Id;
  }

  public String getCommissioner3Id()
  {
    return commissioner3Id;
  }

  public void setCommissioner3Id( String commissioner3Id )
  {
    this.commissioner3Id = commissioner3Id;
  }

  public String getCommissioner4Id()
  {
    return commissioner4Id;
  }

  public void setCommissioner4Id( String commissioner4Id )
  {
    this.commissioner4Id = commissioner4Id;
  }

  public String getCommissioner5Id()
  {
    return commissioner5Id;
  }

  public void setCommissioner5Id( String commissioner5Id )
  {
    this.commissioner5Id = commissioner5Id;
  }

  public String getCommissioner6Id()
  {
    return commissioner6Id;
  }

  public void setCommissioner6Id( String commissioner6Id )
  {
    this.commissioner6Id = commissioner6Id;
  }

  public String getCommissioner7Id()
  {
    return commissioner7Id;
  }

  public void setCommissioner7Id( String commissioner7Id )
  {
    this.commissioner7Id = commissioner7Id;
  }

  public String getCommissioner8Id()
  {
    return commissioner8Id;
  }

  public void setCommissioner8Id( String commissioner8Id )
  {
    this.commissioner8Id = commissioner8Id;
  }

  public String getCommissioner9Id()
  {
    return commissioner9Id;
  }

  public void setCommissioner9Id( String commissioner9Id )
  {
    this.commissioner9Id = commissioner9Id;
  }

  public GraduateWorkInfo getGraduateWorkInfo()
  {

    if( graduateWorkInfo == null )
    {
      graduateWorkInfo = new GraduateWorkInfo();
    }

    return graduateWorkInfo;
  }

  public void setGraduateWorkInfo( GraduateWorkInfo graduateWorkInfo )
  {
    this.graduateWorkInfo = graduateWorkInfo;
  }

}
