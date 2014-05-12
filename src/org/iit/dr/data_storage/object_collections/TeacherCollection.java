package org.iit.dr.data_storage.object_collections;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.iit.dr.data_model.role.Teacher;

/**
 * TeacherCollection.
 * 
 * @author Yuriy Karpovich
 */
@XmlRootElement( name = "teacherList" )
@XmlAccessorType( XmlAccessType.FIELD )
public class TeacherCollection extends ListCollection<Teacher>
{
  @XmlElement( name = "teacher" )
  protected List<Teacher> list;

  public TeacherCollection()
  {
  }

  public TeacherCollection( List<Teacher> list )
  {
    this.list = list;
  }

  @Override
  public void setList( List<Teacher> list )
  {
    this.list = list;
  }

  @Override
  public List<Teacher> getList()
  {
    return list;
  }

}
