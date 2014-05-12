package org.iit.dr.data_model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 * Degree.
 * 
 * @author Yuriy Karpovich
 */
@XmlEnum
public enum Degree
{

  @XmlEnumValue( "DOCTOR_OF_ENGINEERING" )
  DOCTOR_OF_ENGINEERING( "Доктор технических наук", "д.т.н." ), @XmlEnumValue( "CANDIDATE_OF_ENGINEERING" )
  CANDIDATE_OF_ENGINEERING( "Кандидат технических наук", "к.т.н." ), @XmlEnumValue( "CANDIDATE_OF_FIZ_MATH" )
  CANDIDATE_OF_FIZ_MATH( "Кандидат физико-математических наук", "к.ф.-м.н" ), @XmlEnumValue( "CANDIDATE_OF_PED" )
  CANDIDATE_OF_PED( "Кандидат педагогических наук", "к.пед.н" ), @XmlEnumValue( "CANDIDATE_OF_ECON" )
  CANDIDATE_OF_ECON( "Кандидат экономических наук", "к.э.н." );

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

  Degree( String rusName, String rusNameShort )
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
