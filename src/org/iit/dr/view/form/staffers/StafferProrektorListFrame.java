package org.iit.dr.view.form.staffers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.iit.dr.data_model.role.PartTimeStaffer;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.services.TeachersService;
import org.iit.dr.utils.MemoryManager;
import org.iit.dr.view.component.JButtonSelect;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.model.table.common.TableModelConst;
import org.iit.dr.view.model.table.staffers.StafferRektorTableModel;

/**
 * StafferListFrame.
 * 
 * @author Yuriy Karpovich
 */
public class StafferProrektorListFrame extends JInternalFrameExt<Object>
{

  PartTimeStafferProrektorFrame partTimeStafferProrektorFrame;

  StafferRektorTableModel model;

  JTable jTable;

  // ---

  JButtonSelect selectedJButtonSelect;

  public StafferProrektorListFrame() throws HeadlessException
  {
    addComponents();
  }

  protected void init()
  {

    setTitle( "Справочник" );

    setMinimumSize( new Dimension( 600, 300 ) );
    partTimeStafferProrektorFrame =
      ( PartTimeStafferProrektorFrame ) MemoryManager.getObject( PartTimeStafferProrektorFrame.class );
  }

  protected void generateComponents()
  {

    model = new StafferRektorTableModel();
    jTable = new JTable( model );

    jTable.addMouseListener( new MouseAdapter()
    {
      public void mouseClicked( MouseEvent e )
      {
        if( e.getButton() == MouseEvent.BUTTON3 )
        {
          if( jTable.getSelectedRow() >= 0 )
          {
            String guid = ( String ) model.getValueAt( jTable.getSelectedRow(), TableModelConst.ID_COLUMN );
            Teacher teacher = TeachersService.getTeacher( guid );
            if( teacher != null )
            {
              if( teacher instanceof PartTimeStaffer )
              {
                partTimeStafferProrektorFrame.showFrame( model, ( PartTimeStaffer ) teacher );
              }
            }
          }
        }
      }
    } );

    JScrollPane jScrollPane = new JScrollPane( jTable );
    getContentPane().add( jScrollPane, BorderLayout.CENTER );

    JButton addButton = new JButton( "Добавить" );
    addButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        partTimeStafferProrektorFrame.showFrame( model, null );
      }
    } );

    JButton removeButton = new JButton( "Удалить" );
    removeButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        if( jTable.getSelectedRow() >= 0 )
        {
          String guid = ( String ) model.getValueAt( jTable.getSelectedRow(), TableModelConst.ID_COLUMN );
          TeachersService.removeTeacher( guid );
          model.updateData();
        }
      }
    } );
    JPanel jButtonPanel = createNorthButtonPane( 5, addButton, removeButton );

    getContentPane().add( createVPane( 0, jButtonPanel ), BorderLayout.NORTH );
  }

  public void updateFilter()
  {

    model.updateData();
  }

  public boolean showFrame( Object parent, Object jButtonSelectObject )
  {

    if( jButtonSelectObject != null )
    {

      if( jButtonSelectObject instanceof JButtonSelect )
      {

        selectedJButtonSelect = ( JButtonSelect ) jButtonSelectObject;
      }
    }

    updateFilter();
    setVisible( true );

    return true;
  }

  private void addComponents()
  {

    JButton applyButton = new JButton( "Применить" );
    applyButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        if( selectedJButtonSelect != null )
        {

          int selectedRow = jTable.getSelectedRow();

          if( selectedRow != -1 )
          {
            String guid = ( String ) model.getValueAt( selectedRow, TableModelConst.ID_COLUMN );
            if( guid != null )
            {
              Teacher teacher = TeachersService.getTeacher( guid );
              selectedJButtonSelect.setUserObject( teacher );
              setVisible( false );
            }
          }
        }
      }
    } );

    JButton removeButton = new JButton( "Удалить выбор" );
    removeButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        if( selectedJButtonSelect != null )
        {

          selectedJButtonSelect.setUserObject( null );
        }

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