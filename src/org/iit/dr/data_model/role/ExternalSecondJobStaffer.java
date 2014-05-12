package org.iit.dr.data_model.role;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;

import org.iit.dr.data_model.Position;

/**
 * ExternalSecondJobStaffer. (Внешний совместитель)
 * 
 * @author Yuriy Karpovich
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "externalSecondJobStaffer" )
public class ExternalSecondJobStaffer extends Teacher
{

  /**
   * По совместительству должность
   */
  @XmlElement( name = "secondJobPosition" )
  Position secondJobPosition;

/**
   * Должность основаня
   */
  @XmlElement( name = "originalPosition" )
  String originalPosition;

  public Position getSecondJobPosition()
  {
    return secondJobPosition;
  }
  
  @Override
	public String getPositionName() {
		return secondJobPosition.getStaffScheduleName() + " совм.";
}
  
  @Override
	public Position getPosition() {
		return secondJobPosition;
}

  public void setSecondJobPosition( Position secondJobPosition )
  {
    this.secondJobPosition = secondJobPosition;
  }

  public String getOriginalPosition()
  {
    return originalPosition;
  }

  public void setOriginalPosition( String originalPosition )
  {
    this.originalPosition = originalPosition;
  }
}
