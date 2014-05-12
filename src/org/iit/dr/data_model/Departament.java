package org.iit.dr.data_model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum Departament
{
  @XmlEnumValue( "DAY" )
  DAY( "Дневное" ), @XmlEnumValue( "DO" )
  DO( "Дистанционное" );

  private String rusName;

  Departament( String rusName )
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
