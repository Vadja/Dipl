package org.iit.dr.view.form.staffers;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public abstract class StafferListSelectFrame extends StafferListFrame
{
  private static final long serialVersionUID = -7168972515782448749L;

  public abstract void applyButtonAction( String[] teacherId );

  public abstract void removeButtonAction( String[] teacherId );

  public StafferListSelectFrame()
  {
    addComponents();
  }

  private void addComponents()
  {

    JButton applyButton = new JButton( "Применить" );
    applyButton.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        applyButtonAction( StafferListSelectFrame.this.getSelectedTeacherId() );
        setVisible( false );
      }
    } );

    JButton removeButton = new JButton( "Удалить выбор" );
    removeButton.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        removeButtonAction( StafferListSelectFrame.this.getSelectedTeacherId() );
        setVisible( false );
      }
    } );

    JButton cancelButton = new JButton( "Отмена" );
    cancelButton.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        setVisible( false );
      }
    } );

    getContentPane().add( createNorthButtonPane( 10, applyButton, removeButton, cancelButton ), BorderLayout.SOUTH );
  }
}
