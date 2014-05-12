package org.iit.dr.data_model.unit;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum Course
{
  @XmlEnumValue( "FIRST" )
  FIRST( "1" ), @XmlEnumValue( "SECOND" )
  SECOND( "2" ), @XmlEnumValue( "THIRD" )
  THIRD( "3" ), @XmlEnumValue( "FOURTH" )
  FOURTH( "4" ), @XmlEnumValue( "FIFTH" )
  FIFTH( "5" ), @XmlEnumValue( "SIXTH" )
  SIXTH( "6" );

  private String nameCourse;

  Course( String nameCourse )
  {
    this.nameCourse = nameCourse;
  }

  public String getNameCourse()
  {
    return nameCourse;
  }

  @Override
  public String toString()
  {
    return nameCourse;
  }
}
