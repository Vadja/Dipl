package org.iit.dr.data_storage.object_collections;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.iit.dr.data_model.unit.OrganizationUnit;

/**
 * OrganizationUnitCollection.
 * 
 * @author Yuriy Karpovich
 */
@XmlRootElement( name = "ouList" )
@XmlAccessorType( XmlAccessType.FIELD )
public class OrganizationUnitCollection extends ListCollection<OrganizationUnit>
{
  @XmlElement( name = "ou" )
  protected List<OrganizationUnit> list;

  public OrganizationUnitCollection()
  {
  }

  public OrganizationUnitCollection( List<OrganizationUnit> list )
  {
    this.list = list;
  }

  @Override
  public void setList( List<OrganizationUnit> list )
  {
    this.list = list;
  }

  @Override
  public List<OrganizationUnit> getList()
  {
    return list;
  }
}
