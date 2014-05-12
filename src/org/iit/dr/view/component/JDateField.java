package org.iit.dr.view.component;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

/**
 * JDateField.
 * 
 * @author Yuriy Karpovich
 */
public class JDateField extends JDateChooser
{

  public JDateField()
  {

    super( "dd.MM.yyyy", "##.##.####", '_' );
  }

  public JDateField( String s, String s1, char c )
  {
    super( s, s1, c );
  }
}
