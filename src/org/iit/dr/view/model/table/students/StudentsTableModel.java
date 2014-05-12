package org.iit.dr.view.model.table.students;

import java.util.ArrayList;
import java.util.List;

import org.iit.dr.data_model.role.Student;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.data_model.unit.OrganizationUnit;
import org.iit.dr.data_model.unit.UnitType;
import org.iit.dr.services.OrganizationUnitService;
import org.iit.dr.services.StudentsService;
import org.iit.dr.view.model.table.common.AbstractDepartmentReportsTableModel;

/**
 * StudentsTableModel.
 * 
 * @author Yuriy Karpovich, Uladzimir Babkou
 */
public class StudentsTableModel extends AbstractDepartmentReportsTableModel<Student>
{
  private static final long serialVersionUID = -1631646842305725285L;

  public static final String[] HEADERS = new String[] {"Группа", "Подгруппа", "Фамилия", "Имя", "Отчество"};

  protected List<String> subOUIdList;

  @Override
  public void updateData()
  {
    if( objectList == null )
    {
      
      objectList = new ArrayList<Student>();
    }
    if( subOUIdList == null || subOUIdList.isEmpty() )
    {
      objectList = StudentsService.getStudentList();
    }
    else
    {
      objectList = StudentsService.getStudentByOUList( subOUIdList );
    }

    fireTableDataChanged();
  }

  @Override
  public Object getValueAt( int rowIndex, int columnIndex )
  {  
    if( rowIndex < objectList.size() )
    {
      Student student = objectList.get( rowIndex );
      switch( columnIndex )
      {
        case 0:
          for( OrganizationUnit item : OrganizationUnitService.getListParentOU( student.getGroup() ) )
          {
            if( item.getType() == UnitType.GROUP )
            {
              return item.getName();
            }
          }
          return "";
        case 1:
          for( OrganizationUnit item : OrganizationUnitService.getListParentOU( student.getGroup() ) )
          {
            if( item.getType() == UnitType.SUBGROUP )
            {
              return item.getName();
            }
          }
          return "";
        case 2:
          return student.getLastName();
        case 3:
          return student.getFirstName();
        case 4:
          return student.getPatronymicName();
        case ID_COLUMN:
          return student.getId();
      }
    }
    return null;
  }

  public void updateData( List<String> subOUIdList )
  {
    this.subOUIdList = subOUIdList;
    updateData();
  }

  @Override
  public String[] getHeaders()
  {
    return HEADERS;
  }
}
