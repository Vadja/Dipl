package org.iit.dr.view.model.table.staffers;

import java.util.ArrayList;
import java.util.List;

import org.iit.dr.data_model.Position;
import org.iit.dr.data_model.StaffRate;
import org.iit.dr.services.StaffRateService;
import org.iit.dr.view.model.table.common.AbstractDepartmentReportsTableModel;

public class StaffRateTableModel extends AbstractDepartmentReportsTableModel<StaffRate>
{

  /**
	 * 
	 */
	private static final long serialVersionUID = 712624593596354976L;

public static final String[] HEADERS = new String[] {"Должность", "Бюджетное обучение", "Платное обучение", "Ставка"};

  Class[] classType = new Class[] {String.class, Double.class, Double.class, Double.class};

  private static final Position[] positionList;
  
  static {
	  Position[] positions = Position.values();
	  positionList = new Position[positions.length-1];
	  int i = 0;
	  for (Position position : positions) {
		if (position != Position.ENGINEER_1){
			positionList[i++] = position;
		}
	}
  }

  @Override
  public Class<?> getColumnClass( int column )
  {
    return classType[column];
  }

  @Override
  public Object getValueAt( int rowIndex, int columnIndex )
  {
    StaffRate staffRate = getStaffRateByPosition( positionList[rowIndex] );

    switch( columnIndex )
    {
      case 0:
        return positionList[rowIndex];
      case 1:
    	  return staffRate.getAmountB();
      case 2:
    	  return staffRate.getAmountP();
      case 3:
        return staffRate.getAmountB() + staffRate.getAmountDO() + staffRate.getAmountP();
 //     case 4:
 //       return staffRate.getAmountDO();
    }
    return null;
  }

  @Override
  public void setValueAt( Object aValue, int rowIndex, int columnIndex )
  {
    StaffRate staffRate = getStaffRateByPosition( positionList[rowIndex] );

    switch( columnIndex )
    {
      case 1:
        staffRate.setAmountB( ( Double ) aValue );
        break;
      case 2:
        staffRate.setAmountP( ( Double ) aValue );
        break;
 //     case 4:
 //       staffRate.setAmountDO( ( Double ) aValue );
 //       break;
    }
  }

  private StaffRate getStaffRateByPosition( Position position )
  {
    for( StaffRate item : objectList )
    {
      if( item.getPosition() == position )
      {
        return item;
      }
    }

    StaffRate staffRate = new StaffRate();
    staffRate.setPosition( position );
    objectList.add( staffRate );

    return staffRate;
  }

  @Override
  public void updateData()
  {
    if( objectList == null )
    {
      objectList = new ArrayList<StaffRate>();
      for( StaffRate item : StaffRateService.getStaffRateList() )
      {
        objectList.add( item.clone() );
      }
    }
  }

  @Override
  public boolean isCellEditable( int rowIndex, int columnIndex )
  {
    if((columnIndex > 0) || (columnIndex < 3))
    {
      return true;
    }
    return false;
  }

  public List<StaffRate> getObjectList()
  {
    return objectList;
  }

  @Override
  public int getRowCount()
  {
    return positionList.length;
  }

  @Override
  public String[] getHeaders()
  {
    return HEADERS;
  }

}
