package org.iit.dr.data_storage.object_collections;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.iit.dr.data_model.PracticeConsultant;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
@XmlRootElement( name = "consultantList" )
@XmlAccessorType( XmlAccessType.FIELD )
public class GraduatingPracticeConsultantCollection extends ListCollection<PracticeConsultant>
{
  @XmlElement( name = "consultant" )
  protected List<PracticeConsultant> list;

  public GraduatingPracticeConsultantCollection( List<PracticeConsultant> list )
  {
    this.list = list;
  }

  public GraduatingPracticeConsultantCollection()
  {

  }

  @Override
  public void setList( List<PracticeConsultant> list )
  {
    this.list = list;
  }

  @Override
  public List<PracticeConsultant> getList()
  {
    return list;
  }
}
