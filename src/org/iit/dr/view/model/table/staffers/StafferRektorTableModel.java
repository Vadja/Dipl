package org.iit.dr.view.model.table.staffers;

import java.util.ArrayList;
import java.util.List;

import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.services.TeachersService;
import org.iit.dr.view.model.table.common.AbstractDepartmentReportsTableModel;

/**
 * StafferTableModel.
 * 
 * @author Yuriy Karpovich
 */
public class StafferRektorTableModel extends AbstractDepartmentReportsTableModel<Teacher>
{
  private static final long serialVersionUID = -5966847259654608525L;

  public static final String[] HEADERS = new String[] {"Фамилия", "Имя", "Отчество"};

  @Override
  public Object getValueAt( int rowIndex, int columnIndex )
  {
    if( rowIndex < objectList.size() )
    {
      Teacher teacher = objectList.get( rowIndex );
      switch( columnIndex )
      {
        case 0:
          return teacher.getLastName();
        case 1:
          return teacher.getFirstName();
        case 2:
          return teacher.getPatronymicName();
        case ID_COLUMN:
          return teacher.getId();
      }
    }
    return null;
  }

  public void updateData()
  {
    if( objectList == null )
    {
      objectList = new ArrayList<Teacher>();
    }
    else
    {
      objectList.clear();
    }
    List<String> stafferIdList = TeachersService.getProrektorIdList();
    List<Teacher> teacherListSource = TeachersService.getTeacherList();
    if( teacherListSource != null )
    {
      for( Teacher teacher : teacherListSource )
      {
        if( stafferIdList.contains( teacher.getId() ) )
        {
          objectList.add( teacher );
        }
      }
    }
    fireTableDataChanged();
  }

  private boolean applyFilterName( String name, Teacher teacher )
  {
    return name == null
      || ( teacher.getLastName() != null && teacher.getLastName().toLowerCase().startsWith( name.toLowerCase() ) );
  }

  private boolean applyFilterClass( List<Class> classList, Teacher teacher )
  {
    if( classList != null )
    {
      for( Class classItem : classList )
      {
        if( classItem.equals( teacher.getClass() ) )
        {
          return true;
        }
      }
      return false;
    }
    else
    {
      return true;
    }
  }

  @Override
  public String[] getHeaders()
  {
    return HEADERS;
  }
}