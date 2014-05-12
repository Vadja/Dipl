package org.iit.dr.data_storage.object_collections;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.iit.dr.data_model.DistributionPlace;

@XmlRootElement( name = "distributionPlaceList" )
@XmlAccessorType( XmlAccessType.FIELD )
public class DistributionPlaceCollection extends ListCollection<DistributionPlace>
{
  @XmlElement( name = "distributionPlace" )
  protected List<DistributionPlace> list;

  public DistributionPlaceCollection()
  {
  }

  public DistributionPlaceCollection( List<DistributionPlace> list )
  {
    this.list = list;
  }

  @Override
  public void setList( List<DistributionPlace> list )
  {
    this.list = list;
  }

  @Override
  public List<DistributionPlace> getList()
  {
    return list;
  }
}
