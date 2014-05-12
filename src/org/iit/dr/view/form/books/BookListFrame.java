package org.iit.dr.view.form.books;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.iit.dr.data_model.Book;
import org.iit.dr.data_storage.DataStorage;
import org.iit.dr.documents.word.write.books.BookDocumentGenerator;
import org.iit.dr.services.BookService;
import org.iit.dr.utils.MemoryManager;
import org.iit.dr.view.component.JButtonSelect;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.model.table.books.BookTableModel;
import org.iit.dr.view.model.table.common.TableModelConst;

/**
 * Created by IntelliJ IDEA. User: Admin Date: 14.12.2010 Time: 3:45:32 To change this template use File | Settings |
 * File Templates.
 */
public class BookListFrame extends JInternalFrameExt<Object>
{

  BookFrame bookFrame;

  // InternalStafferFrame internalStafferFrame;
  // ExternalStafferFrame externalStafferFrame;
  // PartTimeStafferFrame partTimeStafferFrame;

  BookTableModel model;

  JTable jTable;

  // ---

  JTextField lastNameFilter;

  // ---

  JButtonSelect selectedJButtonSelect;

  public BookListFrame( Boolean selectDialog ) throws HeadlessException
  {

    if( selectDialog )
    {
      addComponents();
    }
  }

  public BookListFrame() throws HeadlessException
  {
  }

  protected void init()
  {

    setTitle( "Справочник:Методические пособия" );

    setMinimumSize( new Dimension( 600, 300 ) );
    bookFrame = ( BookFrame ) MemoryManager.getObject( BookFrame.class );

  }

  protected void generateComponents()
  {
    lastNameFilter = createJTextField();
    lastNameFilter.addKeyListener( new KeyListener()
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
    model = new BookTableModel();
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

            Book book = BookService.getBook( guid );

            if( book != null )
            {
              // internalStafferFrame.showFrame(model, book);

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

    JButton addButton = new JButton( "Добавить" );
    addButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        bookFrame.showFrame( model, null );

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
          BookService.removeBook( guid );

          model.updateData();
        }
      }
    } );

    JButton testButton = new JButton( "Тест" );
    testButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        // List<Conference> conf = DataStorage.getInstance().getConferenceList();
        // System.out.println(conf.get(1).getMemberList().get(1).getPersonList().get(0).getShortName());

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
          BookDocumentGenerator bookDocumentGenerator = new BookDocumentGenerator();
          bookDocumentGenerator.setTemplateName( "График по дням защиты.doc" );
          bookDocumentGenerator.generateDocument( "Отчет по изданиям.doc" );

        }

      }
    } );
    JPanel jButtonPanel = createNorthButtonPane( 5, lastNameFilter, addButton, removeButton, reportButton );
    // JPanel jFilterPanel =
    // createNorthButtonPane(5, allFilter, stafferFilter, partTimeStaffer, internalStaffer, externalStaffer);

    getContentPane().add( createVPane( 0, jButtonPanel ), BorderLayout.NORTH );
  }

  public void updateFilter()
  {

    String textFilter = null;

    if( lastNameFilter.getText() != null && lastNameFilter.getText().length() > 0 )
    {

      textFilter = lastNameFilter.getText();
    }

    java.util.List<Class> classFilter = null;

    // if (!allFilter.isSelected()) {
    //
    // classFilter = new ArrayList<Class>();
    //
    // if (stafferFilter.isSelected()) {
    //
    // classFilter.add(Staffer.class);
    // }
    //
    // if (partTimeStaffer.isSelected()) {
    //
    // classFilter.add(PartTimeStaffer.class);
    // }
    //
    // if (externalStaffer.isSelected()) {
    //
    // classFilter.add(ExternalSecondJobStaffer.class);
    // }
    //
    // if (internalStaffer.isSelected()) {
    //
    // classFilter.add(InternalSecondJobStaffer.class);
    // }
    // }
    //
    // classFilter.add(Book.class);
    model.updateData( textFilter );

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

    lastNameFilter.setText( "" );
    // allFilter.setSelected(true);
    // stafferFilter.setSelected(false);
    // partTimeStaffer.setSelected(false);
    // internalStaffer.setSelected(false);
    // externalStaffer.setSelected(false);

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

              Book book = BookService.getBook( guid );
              selectedJButtonSelect.setUserObject( book );
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
