package org.iit.dr.data_storage.object_collections;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.iit.dr.data_model.PracticePlace;

/**
 * PracticeWorkCollection.
 * 
 * @author Yuriy Karpovich
 */

@XmlRootElement( name = "productionPracticePlaceList" )
@XmlAccessorType( XmlAccessType.FIELD )
public class ProductionPracticePlaceCollection extends ListCollection<PracticePlace>
{

  @XmlElement( name = "practicePlace" )
  protected List<PracticePlace> list;

  public ProductionPracticePlaceCollection()
  {
  }

  public ProductionPracticePlaceCollection( List<PracticePlace> list )
  {
    this.list = list;
  }

  @Override
  public void setList( List<PracticePlace> list )
  {
    this.list = list;
  }

  @Override
  public List<PracticePlace> getList()
  {
    return list;
  }
}