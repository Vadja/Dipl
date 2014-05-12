package org.iit.dr.data_model.role;

import org.iit.dr.data_model.DateRange;
import org.iit.dr.data_model.Degree;
import org.iit.dr.data_model.Passport;
import org.iit.dr.data_model.Position;
import org.iit.dr.data_model.Rank;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import java.util.ArrayList;

/**
 * Teacher.
 * 
 * @author Yuriy Karpovich
 */

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "teacher" )
public abstract class Teacher extends Person
{

  @XmlElement( name = "rank" )
  private Rank rank;

  @XmlElement( name = "degree" )
  private Degree degree;

  @XmlElement( name = "comment" )
  private String comment;

  @XmlElement( name = "passport" )
  private Passport passport;
/*
  @XmlElement( name = "contract" )
  private DateRange contract;

  @XmlElement( name = "dogovor" )
  private DateRange dogovor;
  */
  @XmlElement( name = "range")
  private DateRange range;
  
@XmlAttribute( name = "contract")
  private boolean isContract;

  public boolean isContract() {
	return isContract;
}
  
  public DateRange getRange() {
	return range;
}

public void setRange(DateRange range) {
	this.range = range;
}

public void setContract(boolean isContract) {
	this.isContract = isContract;
}
/*
public DateRange getContract()
  {
    return contract;
  }

  public void setContract( DateRange contract )
  {
    this.contract = contract;
  }

  public DateRange getDogovor()
  {
    return dogovor;
  }

  public void setDogovor( DateRange dogovor )
  {
    this.dogovor = dogovor;
  }
*/
  public Rank getRank()
  {
    return rank;
  }

  public void setRank( Rank rank )
  {
    this.rank = rank;
  }

  public Degree getDegree()
  {
    return degree;
  }

  public void setDegree( Degree degree )
  {
    this.degree = degree;
  }

  public String getComment()
  {
    return comment;
  }

  public void setComment( String comment )
  {
    this.comment = comment;
  }

  public Passport getPassport()
  {

    if( passport == null )
    {

      passport = new Passport();
    }

    return passport;
  }

  public void setPassport( Passport passport )
  {
    this.passport = passport;
  }
  
  public String getPositionName() {
	  return null;
  }
  
  public Position getPosition() {
	  return null;
  }
  }

