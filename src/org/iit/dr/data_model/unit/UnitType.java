package org.iit.dr.data_model.unit;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 * UnitType.
 * 
 * @author Yuriy Karpovich
 */
@XmlEnum
public enum UnitType
{

  @XmlEnumValue( "STREAM" )
  STREAM( "Курс" ), @XmlEnumValue( "GROUP" )
  GROUP( "Группа" ), @XmlEnumValue( "SUBGROUP" )
  SUBGROUP( "Подгруппа" ), @XmlEnumValue( "FACUlTY" )
  FACULTY( "Факультет" );

  private String rusName;

  UnitType( String rusName )
  {
    this.rusName = rusName;
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
