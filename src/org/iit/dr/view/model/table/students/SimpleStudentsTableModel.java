package org.iit.dr.view.model.table.students;

import java.util.List;

import org.iit.dr.data_model.PracticeType;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.services.StudentsService;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class SimpleStudentsTableModel extends StudentsTableModel
{
  private static final long serialVersionUID = 5621459668566505284L;

  public static final String[] HEADERS = new String[] {"Студенты"};

  private PracticeType practiceType;

  public SimpleStudentsTableModel()
  {
  }

  public SimpleStudentsTableModel( PracticeType practiceType )
  {
    this.practiceType = practiceType;
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
          return student.getShortName();
        case ID_COLUMN:
          return student.getId();
      }
    }
    return null;
  }

  @Override
  public void updateData()
  {
    if( subOUIdList != null )
    {
      objectList = StudentsService.getStudentsWithoutPracticePlace( subOUIdList, practiceType );
      fireTableDataChanged();
    }
  }

  public void updateStudents( List<Student> students )
  {
    objectList = students;
    fireTableDataChanged();
  }

  @Override
  public String[] getHeaders()
  {
    return HEADERS;
  }
}
