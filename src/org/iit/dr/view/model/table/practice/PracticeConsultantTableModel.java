package org.iit.dr.view.model.table.practice;

import java.util.ArrayList;

import org.iit.dr.data_model.PracticeConsultant;
import org.iit.dr.data_model.PracticeType;
import org.iit.dr.services.PracticeService;
import org.iit.dr.services.TeachersService;
import org.iit.dr.view.model.table.common.AbstractDepartmentReportsTableModel;

public class PracticeConsultantTableModel extends AbstractDepartmentReportsTableModel<PracticeConsultant>
{
  private static final long serialVersionUID = 1707639079262165515L;

  public static final String[] HEADERS = {"Преподаватель", "Число мест", "Cтудентов"};

  public static final int[] COLUMNS_PROPORTIONS = {3, 1, 1};

  private PracticeType practiceType;

  public PracticeConsultantTableModel( PracticeType practiceType )
  {
    this.practiceType = practiceType;
    objectList = new ArrayList<PracticeConsultant>();
  }

  @Override
  public Object getValueAt( int rowIndex, int columnIndex )
  {
    if( rowIndex < objectList.size() )
    {
      PracticeConsultant consultant = objectList.get( rowIndex );
      switch( columnIndex )
      {
        case 0:
          return TeachersService.getTeacher( consultant.getTeacherId() ).getShortName();
        case 1:
          return consultant.getMaxStudentsNumber();
        case 2:
          return PracticeService.getPracticeConsultantStudentsNumber( consultant.getTeacherId(), practiceType );
        case ID_COLUMN:
          return consultant.getTeacherId();
      }
    }
    return null;
  }

  public void updateData()
  {
    objectList = PracticeService.getPracticeConsultantsList( practiceType );
    fireTableDataChanged();
  }

  @Override
  public String[] getHeaders()
  {
    return HEADERS;
  }

  @Override
  public Class<?> getColumnClass( int columnIndex )
  {
    if( columnIndex == 0 )
    {
      return String.class;
    }
    return Integer.class;
  }

  @Override
  public boolean isCellEditable( int rowIndex, int columnIndex )
  {
    if( columnIndex == 1 )
    {
      return true;
    }
    return false;
  }

  @Override
  public void setValueAt( Object obj, int rowIndex, int columnIndex )
  {
    if( columnIndex == 1 )
    {
      objectList.get( rowIndex ).setMaxStudentsNumber( ( Integer ) obj );
    }
    fireTableDataChanged();
  }

}
