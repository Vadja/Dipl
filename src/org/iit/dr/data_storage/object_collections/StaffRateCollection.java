package org.iit.dr.data_storage.object_collections;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.iit.dr.data_model.StaffRate;

@XmlRootElement (name = "staffRateList")
@XmlAccessorType( XmlAccessType.FIELD )
public class StaffRateCollection extends ListCollection<StaffRate>
{
  @XmlElement( name = "staffRate" )
  protected List<StaffRate> list;

  public StaffRateCollection()
  {

  }

  public StaffRateCollection( List<StaffRate> list )
  {
    this.list = list;
  }

  @Override
  public void setList( List<StaffRate> list )
  {
    this.list = list;
  }

  @Override
  public List<StaffRate> getList()
  {
    return list;
  }

}
