package org.iit.dr.view.form.staffers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.iit.dr.data_model.role.PartTimeStaffer;
import org.iit.dr.data_model.role.Person;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.documents.word.write.staffers.PR10DocumentGenerator;
import org.iit.dr.services.TeachersService;
import org.iit.dr.utils.DateUtils;
import org.iit.dr.utils.FileUtils;
import org.iit.dr.utils.MemoryManager;
import org.iit.dr.view.component.JButtonSelect;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.model.table.common.TableModelConst;
import org.iit.dr.view.model.table.staffers.StafferTableModel;

/**
 * DPCreatorListFrame.
 * 
 * @author Yuriy Karpovich
 */
public class DPCreatorListFrame extends JInternalFrameExt<Object>
{

  PartTimeStafferFrame partTimeStafferFrame;

  StafferProrektorListFrame stafferProrektorListFrame;

  StafferTableModel model;

  JTable jTable;

  // ---

  JTextField lastNameFilter;

  // ---

  JButtonSelect prorektor1Select;

  JButtonSelect prorektor2Select;

  public DPCreatorListFrame() throws HeadlessException
  {

    stafferProrektorListFrame = ( StafferProrektorListFrame ) MemoryManager.getObject( StafferProrektorListFrame.class );
  }

  protected void init()
  {

    setTitle( "Отчет: Договор подряда" );

    setMinimumSize( new Dimension( 600, 500 ) );
    partTimeStafferFrame = ( PartTimeStafferFrame ) MemoryManager.getObject( PartTimeStafferFrame.class );

  }

  private void initComponents()
  {

    prorektor1Select = createJButtonSelect();
    prorektor2Select = createJButtonSelect();
    lastNameFilter = createJTextField();
  }

  protected void generateComponents()
  {

    initComponents();

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

              if( teacher instanceof PartTimeStaffer )
              {

                partTimeStafferFrame.showFrame( model, ( PartTimeStaffer ) teacher );

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

    JButton addButton = new JButton( "Добавить" );
    addButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        partTimeStafferFrame.showFrame( model, null );
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
    JPanel jButtonPanel = createNorthButtonPane( 5, lastNameFilter, addButton, removeButton );

    getContentPane().add( createVPane( 0, jButtonPanel ), BorderLayout.NORTH );

    addComponents();
  }

  public void updateFilter()
  {

    String textFilter = null;

    if( lastNameFilter.getText() != null && lastNameFilter.getText().length() > 0 )
    {

      textFilter = lastNameFilter.getText();
    }

    List<Class> classFilter = new ArrayList<Class>();

    classFilter.add( PartTimeStaffer.class );

    model.updateData( textFilter, classFilter );

  }

  public boolean showFrame( Object parent, Object jButtonSelectObject )
  {

    lastNameFilter.setText( "" );

    updateFilter();
    setVisible( true );

    return true;
  }

  private List<String> getSelectedItems()
  {

    List<String> selectedItemList = new ArrayList<String>();

    int[] selectedRows = jTable.getSelectedRows();

    if( selectedRows != null )
    {

      for( int selectedRow : selectedRows )
      {

        String guid = ( String ) model.getValueAt( selectedRow, TableModelConst.ID_COLUMN );

        if( guid != null )
        {
          selectedItemList.add( guid );
        }
      }

    }

    return selectedItemList;
  }

  private void addComponents()
  {

    JButton dpButton = new JButton( "Сформировать договор" );
    dpButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        List<String> selectedItems = getSelectedItems();

        if( selectedItems != null && selectedItems.size() > 0 )
        {

          for( String selectedItem : selectedItems )
          {
            Teacher teacher = TeachersService.getTeacher( selectedItem );
            if( teacher != null )
            {
              PartTimeStaffer staffer = ( PartTimeStaffer ) teacher;

              PR10DocumentGenerator pr10DocumentGenerator = new PR10DocumentGenerator();

              pr10DocumentGenerator.generate(
                FileUtils.getCommonDocumentPath( "Форма-ПР-10 [" + staffer.getShortName() + "].doc" ),
                new String[] {getFullName( teacher ) + getDegreeRank( teacher ), getFullName( teacher ),
                  DateUtils.dateToString( staffer.getDateOfBirthday() ),
                  getNotNullString( staffer.getPassport().getNumber() ),
                  DateUtils.dateToString( staffer.getPassport().getDateIssue() ),
                  getNotNullString( staffer.getPassport().getPlaceIssue() ),
                  getNotNullString( staffer.getPassport().getPrivateNumber() ),
                  getNotNullString( staffer.getPassport().getInsuranceNumber() ),
                  getNotNullString( staffer.getAddress() ), getNotNullString( staffer.getPhoneWork() ),
                  getNotNullString( staffer.getPhoneHome() ), getNotNullString( staffer.getPhoneMobile() ),
                  getNotNullString( staffer.getEducation() ), getNotNullString( staffer.getSpecialty() ),
                  staffer.getDegree() != null ? staffer.getDegree().getRusNameShort() : "",
                  staffer.getRank() != null ? staffer.getRank().getRusName() : "",
                  getNotNullString( staffer.getChildrenCount() ), getNotNullString( staffer.getWorkPlace() ),
                  getNotNullString( staffer.getUnpWork() ), getNotNullString( staffer.getOriginalPosition() ),
                  getProrektor( prorektor1Select ), getProrektor( prorektor2Select ),
                  getFullName( teacher ) + getDegreeRank( teacher ), getDegreeRankShort( teacher ),
                  getNotNullString( staffer.getPensionBook() ), getNotNullString( staffer.getPensionAgency() )} );
            }
          }
        }
      }
    } );

    dpButton.setMaximumSize( new Dimension( 250, DEFAULT_HIGHT ) );
    dpButton.setPreferredSize( new Dimension( 250, DEFAULT_HIGHT ) );

    getContentPane().add(
      createNorthButtonPane(
        10,
        // createHPane(0,
        createHPane( 0, createVPane( 5, createLabel( "Проректор" ), createLabel( "Проректор по учебной работе" ) ),
          createVPane( 5, prorektor1Select, prorektor2Select ) ), dpButton ), BorderLayout.SOUTH );

    prorektor1Select.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferProrektorListFrame.showFrame( DPCreatorListFrame.this, prorektor1Select );
      }
    } );

    prorektor2Select.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferProrektorListFrame.showFrame( DPCreatorListFrame.this, prorektor2Select );
      }
    } );

  }

  private String getNotNullString( String value )
  {

    return value != null ? value : "";

  }

  private String getDegreeRank( Teacher teacher )
  {

    StringBuilder builder = new StringBuilder();

    if( teacher != null )
    {

      if( teacher.getDegree() != null )
      {

        builder.append( ", " );
        if( teacher.getDegree().getRusNameShort() != null )
        {
          builder.append( teacher.getDegree().getRusNameShort() );
        }
      }

      if( teacher.getRank() != null )
      {

        builder.append( ", " );

        if( teacher.getRank().getRusName() != null )
        {
          builder.append( teacher.getRank().getRusName().toLowerCase() );
        }
      }

    }

    return builder.toString();

  }

  private String getDegreeRankShort( Teacher teacher )
  {

    StringBuilder builder = new StringBuilder();

    if( teacher != null )
    {

      if( teacher.getDegree() != null )
      {
        if( teacher.getDegree().getRusNameShort() != null )
        {
          builder.append( teacher.getDegree().getRusNameShort() );
        }
      }

      if( teacher.getRank() != null )
      {

        builder.append( ", " );

        if( teacher.getRank().getRusName() != null )
        {
          builder.append( teacher.getRank().getRusName().toLowerCase() );
        }
      }

    }

    return builder.toString();

  }

  private String getFullName( Person person )
  {

    return person != null ? person.getFullName() : "";
  }

  private String getProrektor( JButtonSelect jButtonSelect )
  {

    Teacher teacher = ( Teacher ) jButtonSelect.getUserObject();

    if( teacher != null )
    {

      return getFullName( teacher );
    }
    else
    {

      return "";
    }

  }

}