package org.iit.dr.view.model.table.practice;

import org.iit.dr.data_model.Company;
import org.iit.dr.data_model.PracticeType;
import org.iit.dr.data_model.PracticeWork;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.services.CompaniesService;
import org.iit.dr.services.PracticeService;
import org.iit.dr.services.StudentsService;
import org.iit.dr.services.TeachersService;
import org.iit.dr.view.model.table.common.AbstractDepartmentReportsTableModel;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class PracticeTableModel extends AbstractDepartmentReportsTableModel<PracticeWork>
{
  private static final long serialVersionUID = -7324004248358342173L;

  public static final String[] HEADERS = new String[] {"Студент", "Предприятие", "№ приказа", "Консультант", "Тема",
    "Оценка"};

  public static final int[] COLUMN_PROPORTIONS = new int[] {1, 1, 1, 1, 3, 1};

  private PracticeType practiceType;

  public PracticeTableModel( PracticeType practiceType )
  {
    this.practiceType = practiceType;
    objectList = PracticeService.getPracticeWorksList( practiceType );

  }

  @Override
  public Object getValueAt( int rowIndex, int columnIndex )
  {
    if( rowIndex < objectList.size() )
    {
      PracticeWork practiceWork = objectList.get( rowIndex );
      switch( columnIndex )
      {
        case 0:
          Student student = StudentsService.getStudent( practiceWork.getStudentId() );
          return student != null ? student.getShortName() : null;
        case 1:
          Company company = CompaniesService.getCompany( practiceWork.getCompanyId() );
          return company != null ? company.getShortName() : null;
        case 2:
          return practiceWork.getOrderNumber();
        case 3:
          Teacher teacher = TeachersService.getTeacher( practiceWork.getConsultantId() );
          return teacher != null ? teacher.getShortName() : null;
        case 4:
          return practiceWork.getTitle();
        case 5:
          Integer mark = practiceWork.getMark();
          return mark == null ? "" : mark;
        case ID_COLUMN:
          return practiceWork.getId();
      }
    }
    return null;
  }

  @Override
  public String[] getHeaders()
  {
    return HEADERS;
  }

  @Override
  public void updateData()
  {
    objectList = PracticeService.getPracticeWorksList( practiceType );
    fireTableDataChanged();
  }

  @Override
  public boolean isCellEditable( int rowIndex, int columnIndex )
  {
    switch( columnIndex )
    {
      case 2:
      case 4:
        return true;
    }
    return false;
  }

  @Override
  public void setValueAt( Object value, int rowIndex, int columnIndex )
  {
    if( rowIndex < objectList.size() )
    {
      PracticeWork practiceWork = objectList.get( rowIndex );
      switch( columnIndex )
      {
        case 2:
          String orderNumber = ( String ) value;
          if( orderNumber != null && !orderNumber.equals( "" ) )
          {
            practiceWork.setOrderNumber( orderNumber );
          }
          break;

        case 4:
          String title = ( String ) value;
          if( title != null && !title.equals( "" ) )
          {
            practiceWork.setTitle( title );
          }
          break;
        default:
          break;
      }
    }
  }

  @Override
  public Class<?> getColumnClass( int columnIndex )
  {
    if( columnIndex == 5 )
      return Integer.class;
    return String.class;
  }
}