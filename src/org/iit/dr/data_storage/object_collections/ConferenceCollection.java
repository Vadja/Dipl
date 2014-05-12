package org.iit.dr.data_storage.object_collections;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.iit.dr.data_model.Conference;

/**
 * Created by IntelliJ IDEA. User: Admin Date: 14.12.2010 Time: 23:40:08 To change this template use File | Settings |
 * File Templates.
 */

@XmlRootElement( name = "conferenceList" )
@XmlAccessorType( XmlAccessType.FIELD )
public class ConferenceCollection extends ListCollection<Conference>
{
  @XmlElement( name = "conference" )
  protected List<Conference> list;

  public ConferenceCollection()
  {
  }

  public ConferenceCollection( List<Conference> list )
  {
    this.list = list;
  }

  @Override
  public void setList( List<Conference> list )
  {
    this.list = list;
  }

  @Override
  public List<Conference> getList()
  {
    return list;
  }
}