package org.iit.dr.data_model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 * RateType.
 * 
 * @author Yuriy Karpovich
 */
@XmlEnum
public enum RateType
{

  @XmlEnumValue( "BUDGETARY" )
  BUDGETARY( false, "Бюджетная" ), @XmlEnumValue( "OFF_BUDGETARY" )
  OFF_BUDGETARY( true, "Платное обучение" ), @XmlEnumValue( "DISTANCE_EDUCATION" )
  DISTANCE_EDUCATION( true, "Дистанционное образование" );

  boolean paid;

  String rusName;

  RateType( boolean paid, String rusName )
  {
    this.paid = paid;
    this.rusName = rusName;
  }

  public boolean isPaid()
  {
    return paid;
  }

  public String getRusName()
  {
    return rusName;
  }

  @Override
  public String toString()
  {
    return rusName;
  }
}
