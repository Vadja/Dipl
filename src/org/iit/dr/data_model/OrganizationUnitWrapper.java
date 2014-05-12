package org.iit.dr.data_model;

import org.iit.dr.data_model.unit.OrganizationUnit;

/**
 * OrganizationUnitWrapper.
 * 
 * @author Yuriy Karpovich
 */
public class OrganizationUnitWrapper
{

  OrganizationUnit organizationUnit;

  String parentId = null;

  public OrganizationUnitWrapper( OrganizationUnit organizationUnit, String parentId )
  {
    this.organizationUnit = organizationUnit;
    this.parentId = parentId;
  }

  public OrganizationUnit getOrganizationUnit()
  {
    return organizationUnit;
  }

  public String getParentId()
  {
    return parentId;
  }

  public void setOrganizationUnit( OrganizationUnit organizationUnit )
  {
    this.organizationUnit = organizationUnit;
  }
}
