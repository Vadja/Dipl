package org.iit.dr.data_model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 * Rank.
 * 
 * @author Yuriy Karpovich
 */
@XmlEnum
public enum Rank
{

  @XmlEnumValue( "ASSOCIATE_PROFESSOR" )
  ASSOCIATE_PROFESSOR( "Доцент", "Доц." ), @XmlEnumValue( "PROFESSOR" )
  PROFESSOR( "Профессор", "Проф." );

  private String rusName;

  private String rusNameShort;

  public String getRusName()
  {
    return rusName;
  }

  public String getRusNameShort()
  {
    return rusNameShort;
  }

  Rank( String rusName, String rusNameShort )
  {
    this.rusName = rusName;
    this.rusNameShort = rusNameShort;
  }

  @Override
  public String toString()
  {
    return rusName;
  }
}
