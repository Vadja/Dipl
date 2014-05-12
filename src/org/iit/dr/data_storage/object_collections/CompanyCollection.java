package org.iit.dr.data_storage.object_collections;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.iit.dr.data_model.Company;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
@XmlRootElement( name = "companiesList" )
@XmlAccessorType( XmlAccessType.FIELD )
public class CompanyCollection extends ListCollection<Company>
{
  @XmlElement( name = "company" )
  private List<Company> list;

  public CompanyCollection( List<Company> list )
  {
    this.list = list;
  }

  public CompanyCollection()
  {
  }

  @Override
  public void setList( List<Company> list )
  {
    this.list = list;
  }

  @Override
  public List<Company> getList()
  {
    return list;
  }
}
