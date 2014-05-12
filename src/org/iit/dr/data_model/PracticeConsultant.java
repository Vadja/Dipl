package org.iit.dr.data_model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "practiceConsultant" )
public class PracticeConsultant extends PersistenceObject
{
  @XmlElement( name = "teacherId" )
  private String teacherId;

  @XmlElement( name = "maxStudentsNumber" )
  private int maxStudentsNumber;

  public PracticeConsultant()
  {
  }

  public PracticeConsultant( String teacherId )
  {
    this.teacherId = teacherId;
  }

  public void setTeacherId( String teacherId )
  {
    this.teacherId = teacherId;
  }

  public String getTeacherId()
  {
    return teacherId;
  }

  public void setMaxStudentsNumber( int maxStudentsNumber )
  {
    this.maxStudentsNumber = maxStudentsNumber;
  }

  public int getMaxStudentsNumber()
  {
    return maxStudentsNumber;
  }

  @Override
  public String getId()
  {
    return teacherId;
  }

  @Override
  public Boolean isProxy()
  {
    return false;
  }

  public boolean equals( Object obj )
  {
    if( obj instanceof PracticeConsultant )
    {
      return this.getTeacherId().equals( ( ( PracticeConsultant ) obj ).getTeacherId() );
    }
    return false;
  }
}
