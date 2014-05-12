package org.iit.dr.data_model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum Sex
{
  @XmlEnumValue( "MALE" )
  MALE( "Мужской" ), @XmlEnumValue( "FEMALE" )
  FEMALE( "Женский" );

  private String rusName;

  Sex( String name )
  {
    rusName = name;
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
