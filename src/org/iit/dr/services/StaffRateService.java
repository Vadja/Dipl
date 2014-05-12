package org.iit.dr.services;

import java.util.List;

import org.iit.dr.data_model.Position;
import org.iit.dr.data_model.StaffRate;

public class StaffRateService extends CommonService
{
  public static List<StaffRate> getStaffRateList()
  {
	  return dataConnector.getStaffRateList();
  }

  public static StaffRate getStaffRateByPosition( Position position )
  {
    for( StaffRate staffRateItem : getStaffRateList() )
    {
      if( staffRateItem.getPosition() == position )
      {
        return staffRateItem;
      }
    }

    return null;
  }

  public static Double getAllRateByPosition( Position position )
  {
    StaffRate staffRate = getStaffRateByPosition( position );
    Double allRate = new Double( 0 );
    allRate += staffRate.getAmountB();
    allRate += staffRate.getAmountDO();
    allRate += staffRate.getAmountP();

    return allRate;
  }

}
