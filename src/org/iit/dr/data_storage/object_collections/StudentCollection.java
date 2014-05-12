package org.iit.dr.data_storage.object_collections;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.iit.dr.data_model.role.Student;

/**
 * StudentCollection.
 * 
 * @author Yuriy Karpovich
 */
@XmlRootElement( name = "studentList" )
@XmlAccessorType( XmlAccessType.FIELD )
public class StudentCollection extends ListCollection<Student>
{
  @XmlElement( name = "student" )
  protected List<Student> list;

  public StudentCollection()
  {
  }

  public StudentCollection( List<Student> list )
  {
    this.list = list;
  }

  @Override
  public void setList( List<Student> list )
  {
    this.list = list;
  }

  @Override
  public List<Student> getList()
  {
    return list;
  }
}
