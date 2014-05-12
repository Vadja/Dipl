package org.iit.dr.data_model.unit;

import javax.xml.bind.annotation.XmlEnumValue;

public enum Faculty
{
  @XmlEnumValue( "DAY" )
  DAY( "Дневное", "дн" ), @XmlEnumValue( "DO" )
  DO( "Дистанционное", "до" );

  private String rusName;

  private String shortName;

  Faculty( String rusName, String shortName )
  {
    this.rusName = rusName;
    this.shortName = shortName;
  }

  public String getShortName()
  {
    return shortName;
  }

  @Override
  public String toString()
  {
    return rusName;
  }
}
