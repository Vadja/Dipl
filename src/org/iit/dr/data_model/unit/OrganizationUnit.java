package org.iit.dr.data_model.unit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.iit.dr.data_model.PersistenceObject;
import org.iit.dr.services.OrganizationUnitService;

/**
 * OrganizationUnit.
 * 
 * @author Yuriy Karpovich
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "organizationUnit" )
public class OrganizationUnit extends PersistenceObject implements Unit
{

  @XmlElement( name = "id" )
  private String id;

  @XmlElement( name = "name" )
  private String name;

  @XmlElement( name = "type" )
  private UnitType type;

  @XmlElement( name = "parentOuId" )
  private String parentOuId;

  @XmlElement( name = "entranceYear" )
  private String entranceYear;

  @XmlElement( name = "specialization" )
  private String specialization;

  @XmlElement( name = "specializationCode" )
  private String specializationCode;

  @XmlElement( name = "departamentType" )
  private Faculty departamentType;

  public Faculty getDepartamentType()
  {
    return departamentType;
  }

  public void setDepartamentType( Faculty departamentType )
  {
    this.departamentType = departamentType;
  }

  @XmlElement( name = "proxy" )
  private Boolean proxy;

  public OrganizationUnit()
  {
  }

  public OrganizationUnit( String id, Boolean proxy )
  {
    this.id = id;
    this.proxy = proxy;
  }

  @Override
  public String getId()
  {
    return id;
  }

  public void setId( String id )
  {
    this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public UnitType getType()
  {
    return type;
  }

  public void setType( UnitType type )
  {
    this.type = type;
  }

  public String getEntranceYear()
  {
    return entranceYear;
  }

  public void setEntranceYear( String entranceYear )
  {
    this.entranceYear = entranceYear;
  }

  @Override
  public Boolean isProxy()
  {
    return proxy;
  }

  public void setProxy( Boolean proxy )
  {
    this.proxy = proxy;
  }

  public String getParentOuId()
  {
    return parentOuId;
  }

  public void setParentOuId( String parentOuId )
  {
    this.parentOuId = parentOuId;
    OrganizationUnit parent = OrganizationUnitService.getOrganizationUnit( parentOuId );
    this.departamentType = parent.getDepartamentType();
  }

  public String getSpecialization()
  {
    return specialization;
  }

  public void setSpecialization( String specialization )
  {
    this.specialization = specialization;
  }

  public String getSpecializationCode()
  {
    return specializationCode;
  }

  public void setSpecializationCode( String specializationCode )
  {
    this.specializationCode = specializationCode;
  }

  /*
   * public Course getCourseName() { return courseName; }
   * 
   * public void setCourseName( Course courseName ) { this.courseName = courseName; }
   * 
   * public SubGroup getSubGroupName() { return subGroupName; }
   * 
   * public void setSubGroupName( SubGroup subGroupName ) { this.subGroupName = subGroupName; }
   */

  @Override
  public String toString()
  {
    return type + ": " + name;
  }
}
