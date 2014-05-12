package org.iit.dr.view.component;

import org.iit.dr.data_model.DateRange;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * JDateRangeField.
 * 
 * @author Yuriy Karpovich
 */
public class JDateRangeField extends JPanel
{

  JDateField startDate;

  JDateField endDate;

  public JDateRangeField()
  {
    startDate = new JDateField();
    endDate = new JDateField();

    startDate.setMaximumSize( new Dimension( 100, 22 ) );
    startDate.setPreferredSize( new Dimension( 100, 22 ) );
    endDate.setMaximumSize( new Dimension( 100, 22 ) );
    endDate.setPreferredSize( new Dimension( 100, 22 ) );

    add( new JLabel( "c " ) );
    add( startDate );

    add( new JLabel( "по " ) );
    add( endDate );

    setMaximumSize( new Dimension( 247, 28 ) );
    setPreferredSize( new Dimension( 247, 28 ) );
    setMinimumSize( new Dimension( 247, 28 ) );
  }

  public Date getStartDate()
  {

    return startDate.getDate();
  }

  public Date getEndDate()
  {

    return endDate.getDate();
  }

  public void setStartDate( Date date )
  {

    startDate.setDate( date );
  }

  public void setEndDate( Date date )
  {

    endDate.setDate( date );
  }

  public void setDateRange( DateRange dateRange )
  {

    if( dateRange != null )
    {

      setStartDate( dateRange.getStart() );
      setEndDate( dateRange.getEnd() );
    } else{
    	setStartDate( null );
    	setEndDate( null);
    }
  }

  public DateRange getDateRange()
  {

    return new DateRange( getStartDate(), getEndDate() );
  }
}
