package org.iit.dr.data_storage.object_collections;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.iit.dr.data_model.DefenceGraduateWork;

/**
 * DefenceGraduateWorkCollection.
 * 
 * @author Yuriy Karpovich
 */

@XmlRootElement( name = "defenceGraduateWorkList" )
@XmlAccessorType( XmlAccessType.FIELD )
public class DefenceGraduateWorkCollection extends ListCollection<DefenceGraduateWork>
{
  @XmlElement( name = "defenceGraduateWork" )
  protected List<DefenceGraduateWork> list;

  public DefenceGraduateWorkCollection()
  {
  }

  public DefenceGraduateWorkCollection( List<DefenceGraduateWork> list )
  {
    this.list = list;
  }

  @Override
  public void setList( List<DefenceGraduateWork> list )
  {
    this.list = list;
  }

  @Override
  public List<DefenceGraduateWork> getList()
  {
    return list;
  }

}