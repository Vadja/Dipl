package org.iit.dr.data_storage.object_collections;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.iit.dr.data_model.GraduateWork;

/**
 * GraduateWorkCollection.
 * 
 * @author Yuriy Karpovich
 */

@XmlRootElement( name = "graduateWorkList" )
@XmlAccessorType( XmlAccessType.FIELD )
public class GraduateWorkCollection extends ListCollection<GraduateWork>
{
  @XmlElement( name = "graduateWork" )
  protected List<GraduateWork> list;

  public GraduateWorkCollection( List<GraduateWork> list )
  {
    this.list = list;
  }

  public GraduateWorkCollection()
  {

  }

  @Override
  public void setList( List<GraduateWork> list )
  {
    this.list = list;
  }

  @Override
  public List<GraduateWork> getList()
  {
    return list;
  }
}
