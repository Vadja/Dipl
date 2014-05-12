package org.iit.dr.view.model.table.staffers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.iit.dr.data_model.DateRange;
import org.iit.dr.data_model.Departament;
import org.iit.dr.data_model.Rate;
import org.iit.dr.services.RateService;
import org.iit.dr.view.model.table.common.AbstractDepartmentReportsTableModel;

public class PartTimeRateTableModel extends AbstractDepartmentReportsTableModel<Rate>
{
  private static final long serialVersionUID = 3068735326028478656L;

  public static final String[] HEADERS = new String[] {"Дисциплина", "Нагрузка", "Отделение", "Дата начала",
    "Дата окончания"};

  Class[] classType = new Class[] {String.class, Integer.class, Departament.class, Date.class, Date.class};

  @Override
  public Class<?> getColumnClass( int column )
  {
    return classType[column];
  }

  @Override
  public boolean isCellEditable( int rowIndex, int columnIndex )
  {
    return true;
  }

  @Override
  public Object getValueAt( int rowIndex, int columnIndex )
  {
    if( rowIndex < objectList.size() )
    {
      Rate rate = objectList.get( rowIndex );
      switch( columnIndex )
      {
        case 0:
          return rate.getSubject();
        case 1:
          return rate.getCountHour();
        case 2:
          return rate.getDepartament();
        case 3:
          return rate.getRange() != null ? rate.getRange().getStart() : null;
        case 4:
          return rate.getRange() != null ? rate.getRange().getEnd() : null;
        case ID_COLUMN:
          return rate.getId();
      }
    }
    return null;
  }

  @Override
  public void setValueAt( Object aValue, int rowIndex, int columnIndex )
  {
    if( rowIndex < objectList.size() )
    {
      Rate rate = objectList.get( rowIndex );
      switch( columnIndex )
      {
        case 0:
          rate.setSubject( ( String ) aValue );
          break;
        case 1:
          rate.setCountHour( ( Integer ) aValue );
          break;
        case 2:
          rate.setDepartament( ( Departament ) aValue );
          break;
        case 3:
          if( rate.getRange() != null )
          {
            rate.getRange().setStart( ( Date ) aValue );
          }
          else
          {
            rate.setRange( new DateRange( ( Date ) aValue, null ) );
          }
          break;
        case 4:
          if( rate.getRange() != null )
          {
            rate.getRange().setEnd( ( Date ) aValue );
          }
          else
          {
            rate.setRange( new DateRange( null, ( Date ) aValue ) );
          }
          break;
      }
    }
  }

  public void updateData( String teacherId )
  {
    if( objectList == null )
    {
      objectList = new ArrayList<Rate>();

      List<Rate> rateListSource = null;
      if( teacherId != null )
      {
        rateListSource = RateService.getRateByTeacher( teacherId );
      }
      else
      {
        rateListSource = RateService.getRateList();
      }
      if( rateListSource != null )
      {
        for( Rate rate : rateListSource )
        {
          objectList.add( rate.clone() );
        }
      }
    }
    fireTableDataChanged();
  }

  public void canselChange( String teacherId )
  {
    objectList = null;
    updateData( teacherId );
  }

  public void removeRate( int index )
  {
    objectList.remove( index );
  }

  public void addRate( Rate rate )
  {
    objectList.add( rate );
  }

  public List<Rate> getObjectList()
  {
    return objectList;
  }

  @Override
  public String[] getHeaders()
  {
    return HEADERS;
  }

}
