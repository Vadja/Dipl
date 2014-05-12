package org.iit.dr.data_model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 * Position.
 * 
 * @author Yuriy Karpovich
 */
@XmlEnum
public enum Position
{

  @XmlEnumValue( "HEAD_EPARTMENT" )
  HEAD_EPARTMENT( "Заведующий кафедрой", "Зав. каф.", "зав.каф." ), @XmlEnumValue( "PROFESSOR" )
  PROFESSOR( "Профессор", "Проф.", "профессор" ), @XmlEnumValue( "ASSOCIATE_PROFESSOR" )
  ASSOCIATE_PROFESSOR( "Доцент", "Доц.", "доцент" ), @XmlEnumValue( "SENIOR_LECTURER" )
  SENIOR_LECTURER( "Старший преподаватель", "Ст. пр.", "ст.препод." ), @XmlEnumValue( "ASSISTANT" )
  ASSISTANT( "Ассистент", "Асс.", "ассистент" ), @XmlEnumValue( "ENGINEER_1" )
  ENGINEER_1( "Инженер I категории", "Инженер I кат.", "инженер" );

  private String rusName;

  private String rusNameShort;
  
  private String staffScheduleName;

  public String getRusName()
  {
    return rusName;
  }

  public String getRusNameShort()
  {
    return rusNameShort;
  }

  Position( String rusName, String rusNameShort, String staffScheduleName )
  {
    this.rusName = rusName;
    this.rusNameShort = rusNameShort;
    this.staffScheduleName = staffScheduleName;
  }

  @Override
  public String toString()
  {
    return rusName;
  }

public String getStaffScheduleName() {
	return staffScheduleName;
}

public void setStaffScheduleName(String staffScheduleName) {
	this.staffScheduleName = staffScheduleName;
}
}
