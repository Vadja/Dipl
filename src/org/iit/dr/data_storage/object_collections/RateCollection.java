package org.iit.dr.data_storage.object_collections;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.iit.dr.data_model.Rate;

/**
 * RateCollection.
 * 
 * @author Yuriy Karpovich
 */
@XmlRootElement( name = "rateList" )
@XmlAccessorType( XmlAccessType.FIELD )
public class RateCollection extends ListCollection<Rate>
{
  @XmlElement( name = "rate" )
  protected List<Rate> list;

  public RateCollection()
  {
  }

  public RateCollection( List<Rate> list )
  {
    this.list = list;
  }

  @Override
  public void setList( List<Rate> list )
  {
    this.list = list;
  }

  @Override
  public List<Rate> getList()
  {
    return list;
  }
}
