package org.iit.dr.view.model.table.practice;

import java.util.ArrayList;

import org.iit.dr.data_model.PracticePlace;
import org.iit.dr.data_model.PracticeType;
import org.iit.dr.services.CompaniesService;
import org.iit.dr.services.PracticeService;
import org.iit.dr.view.model.table.common.AbstractDepartmentReportsTableModel;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class PracticePlacesTableModel extends AbstractDepartmentReportsTableModel<PracticePlace>
{
  private static final long serialVersionUID = 4206041532879155949L;

  public static final String[] HEADERS = {"Предприятие", "Число мест", "Cтудентов"};

  public static final int[] COLUMNS_PROPORTIONS = {3, 1, 1};

  private PracticeType practiceType;

  public PracticePlacesTableModel( PracticeType practiceType )
  {
    this.practiceType = practiceType;
    objectList = new ArrayList<PracticePlace>();
  }

  @Override
  public Object getValueAt( int rowIndex, int columnIndex )
  {
    if( rowIndex < objectList.size() )
    {
      PracticePlace place = objectList.get( rowIndex );
      switch( columnIndex )
      {
        case 0:
          return CompaniesService.getCompany( place.getCompanyId() ).getName();
        case 1:
          return place.getMaxStudentsNumber();
        case 2:
          return place.getStudentsNumber();
        case ID_COLUMN:
          return place.getCompanyId();
      }
    }
    return null;
  }

  public void updateData()
  {
    objectList = PracticeService.getPracticePlaceList( practiceType );
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
