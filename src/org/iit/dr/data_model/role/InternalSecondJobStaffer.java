package org.iit.dr.data_model.role;

import org.iit.dr.data_model.Position;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;

/**
 * InternalSecondJobStaffer. (Внутренний совместитель)
 * 
 * @author Yuriy Karpovich
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "internalSecondJobStaffer" )
public class InternalSecondJobStaffer extends Teacher
{

  @Override
	public String getPositionName() {
		return originalPosition.getStaffScheduleName() + " совм.";
	}
  
  @Override
	public Position getPosition() {
		return originalPosition;
	}

/**
   * Должность основаня
   */
  @XmlElement( name = "originalPosition" )
  Position originalPosition;

  /**
   * По совместительству должность
   */
  @XmlElement( name = "secondJobPosition" )
  Position secondJobPosition;

  public Position getOriginalPosition()
  {
    return originalPosition;
  }

  public void setOriginalPosition( Position originalPosition )
  {
    this.originalPosition = originalPosition;
  }

  public Position getSecondJobPosition()
  {
    return secondJobPosition;
  }

  public void setSecondJobPosition( Position secondJobPosition )
  {
    this.secondJobPosition = secondJobPosition;
  }
}
