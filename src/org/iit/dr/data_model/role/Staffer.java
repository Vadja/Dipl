package org.iit.dr.data_model.role;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.iit.dr.data_model.Position;

/**
 * Staffer. (Штатный сотрудник)
 * 
 * @author Yuriy Karpovich
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "staffer" )
public class Staffer extends Teacher
{

  @XmlElement( name = "position" )
  Position position;

  @XmlElement( name = "tenderDate" )
  Date tenderDate;

  public Position getPosition()
  {
    return position;
  }

  @Override
public String getPositionName() {
	return position.getStaffScheduleName() + " осн.";
}

public Date getTenderDate()
  {
    return tenderDate;
  }

  public void setTenderDate( Date tenderDate )
  {
    this.tenderDate = tenderDate;
  }

  public void setPosition( Position position )
  {
    this.position = position;
  }

  public String getFioAndPosition()
  {
    String result = getShortName();
    if( getPosition() != null )
    {
      result += ", " + getPosition().getRusName().toLowerCase();
    }
    return result;
  }
}
