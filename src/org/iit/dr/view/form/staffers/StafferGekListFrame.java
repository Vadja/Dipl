package org.iit.dr.view.form.staffers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.iit.dr.data_model.role.ExternalSecondJobStaffer;
import org.iit.dr.data_model.role.InternalSecondJobStaffer;
import org.iit.dr.data_model.role.PartTimeStaffer;
import org.iit.dr.data_model.role.Staffer;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.services.GekService;
import org.iit.dr.services.TeachersService;
import org.iit.dr.utils.MemoryManager;
import org.iit.dr.view.component.JButtonSelect;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.model.table.common.TableModelConst;
import org.iit.dr.view.model.table.staffers.StafferTableModel;

/**
 * StafferGekListFrame.
 * 
 * @author Yuriy Karpovich
 */
public class StafferGekListFrame extends JInternalFrameExt<Object>
{
  private static final long serialVersionUID = 1525381691969552764L;

  StafferFrame stafferFrame;

  InternalStafferFrame internalStafferFrame;

  ExternalStafferFrame externalStafferFrame;

  PartTimeStafferFrame partTimeStafferFrame;

  StafferTableModel model;

  JTable jTable;

  // ---

  JButtonSelect selectedJButtonSelect;

  public StafferGekListFrame( Boolean selectDialog ) throws HeadlessException
  {

    if( selectDialog )
    {
      addComponents();
    }
  }

  public StafferGekListFrame() throws HeadlessException
  {
  }

  protected void init()
  {

    setTitle( "Справочник: Преподаватели" );

    setMinimumSize( new Dimension( 600, 300 ) );
    stafferFrame = ( StafferFrame ) MemoryManager.getObject( StafferFrame.class );
    internalStafferFrame = ( InternalStafferFrame ) MemoryManager.getObject( InternalStafferFrame.class );
    externalStafferFrame = ( ExternalStafferFrame ) MemoryManager.getObject( ExternalStafferFrame.class );
    partTimeStafferFrame = ( PartTimeStafferFrame ) MemoryManager.getObject( PartTimeStafferFrame.class );
  }

  protected void generateComponents()
  {

    model = new StafferTableModel();
    jTable = new JTable( model );

    jTable.addMouseListener( new MouseListener()
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

              if( teacher instanceof Staffer )
              {

                stafferFrame.showFrame( model, ( Staffer ) teacher );

              }
              else if( teacher instanceof PartTimeStaffer )
              {

                partTimeStafferFrame.showFrame( model, ( PartTimeStaffer ) teacher );

              }
              else if( teacher instanceof ExternalSecondJobStaffer )
              {

                externalStafferFrame.showFrame( model, ( ExternalSecondJobStaffer ) teacher );

              }
              else if( teacher instanceof InternalSecondJobStaffer )
              {

                internalStafferFrame.showFrame( model, ( InternalSecondJobStaffer ) teacher );
              }

            }
          }
        }
      }

      public void mousePressed( MouseEvent e )
      {
      }

      public void mouseReleased( MouseEvent e )
      {
      }

      public void mouseEntered( MouseEvent e )
      {
      }

      public void mouseExited( MouseEvent e )
      {
      }
    } );

    JScrollPane jScrollPane = new JScrollPane( jTable );
    getContentPane().add( jScrollPane, BorderLayout.CENTER );
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

    model.updateData( GekService.getGekStafferList() );
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