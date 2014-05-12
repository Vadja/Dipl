package org.iit.dr.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.iit.dr.data_model.role.PartTimeStaffer;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.data_model.role.Teacher;

public class TeachersService extends CommonService
{
  public static List<Teacher> getTeacherList()
  {
    List<Teacher> teachers = dataConnector.getTeacherList();
    Collections.sort( teachers, new Comparator<Teacher>()
    {
      public int compare( Teacher teacher1, Teacher teacher2 )
      {
        return comparePersons( teacher1, teacher2 );
      }
    } );
    return teachers;
  }
  
  public static Teacher getTeacherLastname( String lastname )
  {
    for( Teacher teacherItem : getTeacherList() )
    {
      if( teacherItem.getLastName().equals( lastname ) )
      {
        return teacherItem;
      }
    }
    return null;
  }
  
  public static List<String> getProrektorIdList()
  {
    List<String> prorektorIdList = new ArrayList<String>();
    for( Teacher teacher : dataConnector.getTeacherList() )
    {
      if( teacher instanceof PartTimeStaffer && ( ( PartTimeStaffer ) teacher ).isProrektor() != null
        && ( ( PartTimeStaffer ) teacher ).isProrektor() )
      {
        prorektorIdList.add( teacher.getId() );
      }
    }
    return prorektorIdList;
  }

  public static boolean removeTeacher( String teacherId )
  {
    for( Teacher teacherItem : dataConnector.getTeacherList() )
    {
      if( teacherItem.getId().equals( teacherId ) )
      {
        dataConnector.getTeacherList().remove( teacherItem );
        return true;
      }
    }
    return false;
  }

  public static Teacher getTeacher( String teacherId )
  {
    if( teacherId == null || teacherId.length() == 0 )
    {
      return null;
    }
    for( Teacher teacherItem : dataConnector.getTeacherList() )
    {
      if( teacherItem.getId().equals( teacherId ) )
      {
        return teacherItem;
      }
    }
    return null;
  }
}
