package org.iit.dr.data_model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.iit.dr.data_model.role.Student;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "practicePlace" )
public class PracticePlace
{
  @XmlElement( name = "companyId" )
  private String companyId;

  @XmlElement( name = "maxStudentsNumber" )
  private int maxStudentsNumber;

  @XmlElement( name = "practiceWork" )
  private List<PracticeWork> practiceWorks;

  public PracticePlace( String companyId )
  {
    this();
    setCompanyId( companyId );
  }

  public PracticePlace()
  {
    practiceWorks = new ArrayList<PracticeWork>();
  }

  public void setCompanyId( String companyId )
  {
    this.companyId = companyId;
  }

  public String getCompanyId()
  {
    return companyId;
  }

  public void setMaxStudentsNumber( int maxStudentsNumber )
  {
    this.maxStudentsNumber = maxStudentsNumber;
  }

  public int getMaxStudentsNumber()
  {
    return maxStudentsNumber;
  }

  public int getFreePlacesNumber()
  {
    return maxStudentsNumber - getStudentsNumber();
  }

  public int getStudentsNumber()
  {
    return practiceWorks.size();
  }

  public void setPrakticeWorks( List<PracticeWork> practiceWorks )
  {
    this.practiceWorks = practiceWorks;
  }

  public List<PracticeWork> getPracticeWorks()
  {
    return practiceWorks;
  }

  public boolean addPracticeWork( PracticeWork practiceWork )
  {
    // if( getFreePlacesNumber() > 0 )
    // {
    if( !practiceWorks.contains( practiceWork ) )
    {
      practiceWorks.add( practiceWork );
      return true;
    }
    return true;
    // }
    // return false;
  }

  public PracticeWork getPracticeWork( String studentId )
  {
    for( PracticeWork work : practiceWorks )
    {
      if( work.getStudentId().equals( studentId ) )
      {
        return work;
      }
    }
    return null;
  }

  public boolean removePracticeWork( PracticeWork practiceWork )
  {
    return practiceWorks.remove( practiceWork );
  }

  public String[] getConsultantsId()
  {
    Set<String> consultants = new HashSet<String>();
    for( PracticeWork work : practiceWorks )
    {
      consultants.add( work.getConsultantId() );
    }
    return consultants.toArray( new String[consultants.size()] );
  }

  public boolean addStudent( String studentId )
  {
    return addPracticeWork( new PracticeWork( studentId ) );
  }

  public boolean addStudent( String[] studentIds )
  {
    // if( studentIds.length <= getFreePlacesNumber() )
    // {
    for( String id : studentIds )
    {
      addStudent( id );
    }
    return true;
    // }
    // return false;
  }

  public boolean removeStudent( String studentId )
  {
    return removePracticeWork( new PracticeWork( studentId ) );
  }

  public boolean removeStudent( String[] studentIds )
  {
    for( String id : studentIds )
    {
      removeStudent( id );
    }
    return true;
  }

  public boolean containsStudent( Student student )
  {
    return getPracticeWorks().contains( new PracticeWork( student ) );
  }

  @Override
  public boolean equals( Object obj )
  {
    if( obj instanceof PracticePlace )
    {
      return this.getCompanyId().equals( ( ( PracticePlace ) obj ).getCompanyId() );
    }
    return false;
  }

  @Override
  public String toString()
  {
    return getCompanyId();
  }
}
