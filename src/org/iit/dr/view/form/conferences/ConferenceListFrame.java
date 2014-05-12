package org.iit.dr.view.form.conferences;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.iit.dr.data_model.Book;
import org.iit.dr.data_model.Conference;
import org.iit.dr.data_model.Member;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.data_storage.DataStorage;
import org.iit.dr.documents.word.write.conferences.ConferenceDocumentGenerator;
import org.iit.dr.services.ConferenceService;
import org.iit.dr.services.StudentsService;
import org.iit.dr.view.component.JButtonSelect;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.component.model.ConferenceTreeSimplePanel;
import org.iit.dr.view.model.table.common.TableModelConst;
import org.iit.dr.view.model.table.conferences.ConferenceTableModel;

/**
 * Created by IntelliJ IDEA. User: Admin Date: 15.12.2010 Time: 0:35:59 To change this template use File | Settings |
 * File Templates.
 */
public class ConferenceListFrame extends JInternalFrameExt<Object>
{

  ConferenceTableModel model;

  JTable jTable;

  ConferenceFrame conferenceFrame;

  ConferenceTreeSimplePanel groupTreeSimplePanel;

  // ---

  JButtonSelect selectedJButtonSelect;

  JTextField yearFilter;

  public ConferenceListFrame( Boolean selectDialog ) throws HeadlessException
  {

    if( selectDialog )
    {
      addComponents();
    }
  }

  public ConferenceListFrame() throws HeadlessException
  {
  }

  protected void init()
  {

    setTitle( "Справочник: Конференции" );

    setMinimumSize( new Dimension( 900, 300 ) );
    conferenceFrame = new ConferenceFrame();
  }

  protected void generateComponents()
  {

    yearFilter = createJTextField();
    yearFilter.addKeyListener( new KeyListener()
    {
      public void keyTyped( KeyEvent e )
      {

      }

      public void keyPressed( KeyEvent e )
      {
      }

      public void keyReleased( KeyEvent e )
      {
        updateFilter();
      }
    } );

    model = new ConferenceTableModel();
    jTable = new JTable( model );
    groupTreeSimplePanel = new ConferenceTreeSimplePanel( model );

    jTable.addMouseListener( new MouseListener()
    {

      public void mouseClicked( MouseEvent e )
      {

        if( e.getButton() == MouseEvent.BUTTON3 )
        {

          if( jTable.getSelectedRow() >= 0 )
          {

            String guid = ( String ) model.getValueAt( jTable.getSelectedRow(), TableModelConst.ID_COLUMN );

            java.util.List<Conference> confList = DataStorage.getInstance().getConferenceList();
            for( Conference conf : confList )
            {
              List<Member> memList = conf.getMemberList();
              for( Member mem : memList )
              {
                if( mem.getId().equals( guid ) )
                  conferenceFrame.showFrame( model, mem );
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
    JScrollPane jScrollPaneLeft = new JScrollPane( groupTreeSimplePanel );
    jScrollPaneLeft.setMinimumSize( new Dimension( 180, 200 ) );

    add( new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, jScrollPaneLeft, jScrollPane ), BorderLayout.CENTER );

    JButton addButton = new JButton( "Добавить" );
    addButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        conferenceFrame.showFrame( model, null );

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
          ConferenceService.removeConferenceMember( guid );

          model.updateData();
        }
      }
    } );

    JButton reportButton = new JButton( "Отчет" );
    reportButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {
        List<Book> bookList = DataStorage.getInstance().getBookList();

        if( bookList != null )
        {
          System.out.println( "1" );
          ConferenceDocumentGenerator confDocumentGenerator = new ConferenceDocumentGenerator();
          confDocumentGenerator.setTemplateName( "График по дням защиты.doc" );
          confDocumentGenerator.generateDocument( "Отчет по конференциям.doc" );

        }

      }
    } );
    getContentPane().add( createNorthButtonPane( 5, addButton, removeButton, reportButton ), BorderLayout.NORTH );
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

    if( groupTreeSimplePanel != null )
    {

      groupTreeSimplePanel.updateData();
    }

    model.updateData();
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

              Student student = StudentsService.getStudent( guid );
              selectedJButtonSelect.setUserObject( student );
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

  public void updateFilter()
  {

    String textFilter = null;

    if( yearFilter.getText() != null && yearFilter.getText().length() > 0 )
    {

      textFilter = yearFilter.getText();
    }

    // java.util.List<Class> classFilter = null;

    model.updateData( textFilter, 1 );

  }

}
