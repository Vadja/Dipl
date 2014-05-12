package org.iit.dr.data_model.unit;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum SubGroup
{
  @XmlEnumValue( "FIRST" )
  FIRST( "1" ), @XmlEnumValue( "SECOND" )
  SECOND( "2" );
  private String nameSubGroup;

  SubGroup( String name )
  {
    nameSubGroup = name;
  }

  public String getNameSubGroup()
  {
    return nameSubGroup;
  }

  @Override
  public String toString()
  {
    return nameSubGroup;
  }
}
