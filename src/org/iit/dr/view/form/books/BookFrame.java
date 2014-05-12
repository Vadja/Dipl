package org.iit.dr.view.form.books;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.iit.dr.data_model.Book;
import org.iit.dr.data_model.role.Person;
import org.iit.dr.data_storage.DataStorage;
import org.iit.dr.utils.MemoryManager;
import org.iit.dr.utils.UUIDUtils;
import org.iit.dr.view.component.JButtonSelect;
import org.iit.dr.view.component.JDateField;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.form.staffers.StafferListFrame;
import org.iit.dr.view.form.students.GroupTreeFrame;
import org.iit.dr.view.model.table.books.BookTableModel;

/**
 * Created by IntelliJ IDEA. User: Admin Date: 14.12.2010 Time: 5:01:39 To change this template use File | Settings |
 * File Templates.
 */
public class BookFrame extends JInternalFrameExt<Book>
{

  JTextField titleField;

  JTextField authorField;

  JTextField yearField;

  JTextField typeField;

  JComboBox grifField;

  JTextField infoField;

  JTextField pageField;

  JTextField editorField;

  JComboBox stateField;

  JDateField incomingField;

  JButtonSelect groupField;

  JButtonSelect teacherField;

  StafferListFrame stafferListFrame;

  Book book;

  java.util.List<Person> authorList;

  BookTableModel model;

  GroupTreeFrame groupTreeFrame;

  public BookFrame() throws HeadlessException
  {

    groupTreeFrame = ( GroupTreeFrame ) MemoryManager.getObject( GroupTreeFrame.class, true );
    stafferListFrame = ( StafferListFrame ) MemoryManager.getObject( StafferListFrame.class );
  }

  @Override
  public boolean showFrame( Object parent, Book book )
  {

    model = ( BookTableModel ) parent;

    if( book != null )
    {

      this.book = book;
    }
    else
    {

      this.book = new Book();
    }

    titleField.setText( this.book.getTitle() );
    authorField.setText( this.book.getAuthorString() );
    typeField.setText( this.book.getType() );
    grifField.setSelectedItem( this.book.getGrif() );
    yearField.setText( String.valueOf( this.book.getYear() ) );
    pageField.setText( this.book.getPage() );
    infoField.setText( this.book.getInfo() );
    stateField.setSelectedItem( this.book.getState() );

    if( ( this.book != null ) && ( this.book.getAuthor() != null ) )
    {
      authorList = this.book.getAuthor();
      for( Person pers : authorList )
      {
        authorField.setText( authorField.getText() + "," + pers.getShortName() );
      }
    }
    else
    {
      authorList = new ArrayList<Person>();
    }

    setVisible( true );

    return true;
  }

  @Override
  protected void init()
  {

    setTitle( "Форма редактирования: Издание" );

    setMinimumSize( new Dimension( 600, 300 ) );
  }

  private void initComponents()
  {

    titleField = createJTextField();
    authorField = createJTextField();
    yearField = createJTextField();
    typeField = createJTextField();
    grifField = createJComboBox();
    grifField.addItem( "Министерство образования" );
    grifField.addItem( "Умо" );
    editorField = createJTextField();
    pageField = createJTextField();
    infoField = createJTextField();
    stateField = createJComboBox();
    stateField.addItem( "издано" );
    stateField.addItem( "в печати" );
    stateField.addItem( "готовится к изданию" );
    stateField.setEditable( true );
    incomingField = createJDateField();
    groupField = createJButtonSelect();
    teacherField = createJButtonSelect();
  }

  @Override
  protected void generateComponents()
  {

    initComponents();

    JPanel leftJPane =
      createVPane( 5, createField( titleField, "Название: " ),
        createVPane( 2, createField( authorField, "Авторы: " ), createField( teacherField, "Добавить" ) ),
        // createField(authorField, "Автор: "),
        createField( yearField, "Год: " ), createField( typeField, "Тип: " ), createField( grifField, "Гриф: " ) );

    JPanel rightJPane =
      createVPane( 5, createField( editorField, "Редактор: " ), createField( infoField, "Инфо: " ),
        createField( pageField, "Кол-во страниц: " ),

        createField( stateField, "Статус: " ) );

    JPanel fieldsPane = createHPane( 20, leftJPane, rightJPane );

    JButton applyButton = new JButton( "Применить" );
    applyButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        book.setTitle( checkFieldNull( titleField ) );
        book.setAuthor( authorList );
        book.setType( checkFieldNull( typeField ) );
        book.setYear( Integer.parseInt( checkFieldNull( yearField ) ) );
        book.setInfo( checkFieldNull( infoField ) );
        book.setPage( checkFieldNull( pageField ) );
        book.setGrif( checkFieldNull( grifField ) );

        if( book.getId() == null )
        {

          book.setId( UUIDUtils.getUUID() );
          DataStorage.getInstance().getBookList().add( book );
        }

        if( model != null )
        {

          model.updateData();
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

    getContentPane().add( fieldsPane, BorderLayout.CENTER );
    getContentPane().add( createButtonPane( 10, applyButton, cancelButton ), BorderLayout.SOUTH );

    groupField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        groupTreeFrame.showFrame( BookFrame.this, groupField );
      }
    } );
    teacherField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( BookFrame.this, teacherField );
        Person pers = ( Person ) teacherField.getUserObject();
        if( !authorField.getText().equals( "" ) )
        {
          authorField.setText( authorField.getText() + "," + pers.getShortName() );
        }
        else
        {
          authorField.setText( pers.getShortName() );
        }
        authorList.add( pers );
        teacherField.setText( "Добавить" );
      }
    } );
  }
}
