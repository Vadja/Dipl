package org.iit.dr.view.form.practice;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import org.iit.dr.data_model.PracticeType;
import org.iit.dr.documents.common.generator.ReportParams;
import org.iit.dr.documents.common.report.ReportType;
import org.iit.dr.documents.word.write.practice.PracticeContractGenerator;
import org.iit.dr.services.PracticeService;
import org.iit.dr.services.StudentsService;
import org.iit.dr.utils.FileUtils;
import org.iit.dr.view.action.SingleReportCreationAction;
import org.iit.dr.view.component.GroupTreeSimplePanel;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.component.JTableExt;
import org.iit.dr.view.component.PopupMenuButton;
import org.iit.dr.view.model.table.common.TableModelConst;
import org.iit.dr.view.model.table.practice.PracticePlacesTableModel;
import org.iit.dr.view.model.table.students.SimpleStudentsTableModel;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public abstract class PracticePlacesListFrame extends JInternalFrameExt<Object>
{
  private static final long serialVersionUID = 4064311025041929125L;

  protected SimpleStudentsTableModel studentsLeftTableModel;

  protected SimpleStudentsTableModel studentsRightTableModel;

  protected PracticePlacesTableModel practicePlacesTableModel;

  protected GroupTreeSimplePanel groupTreeSimplePanel;

  protected JTableExt studentsLeftTable;

  protected JTableExt studentsRightTable;

  protected JTableExt practicePlacesTable;

  protected JButton removeCompanyButton;

  protected JButton addCompanyButton;

  protected JButton removeStudentButton;

  protected JButton addStudentButton;

  protected PopupMenuButton createDocButton;

  public abstract PracticeType getPracticeType();

  @Override
  public boolean showFrame( Object parent, Object e )
  {
    setVisible( true );
    return true;
  }

  @Override
  protected void init()
  {
    setSize( new Dimension( 1000, 520 ) );
    setMinimumSize( getSize() );
    studentsLeftTableModel = new SimpleStudentsTableModel( getPracticeType() );
    studentsLeftTable = new JTableExt( studentsLeftTableModel );
    groupTreeSimplePanel = new GroupTreeSimplePanel( studentsLeftTableModel );

    practicePlacesTableModel = new PracticePlacesTableModel( getPracticeType() );
    practicePlacesTable = new JTableExt( practicePlacesTableModel, PracticePlacesTableModel.COLUMNS_PROPORTIONS );
    practicePlacesTableModel.updateData();

    studentsRightTableModel = new SimpleStudentsTableModel( getPracticeType() );
    studentsRightTable = new JTableExt( studentsRightTableModel );

    addCompanyButton = new JButton( "+ Добавить предприятие" );
    removeCompanyButton = new JButton( "- Удалить предприятие" );
    createDocButton = new PopupMenuButton( "Формирование документов", createPopup() );

    addStudentButton = new JButton( "Добавить студентов          >>>" );
    removeStudentButton = new JButton( "<<<          Удалить студентов" );

  }

  @Override
  protected void generateComponents()
  {
    add( createHPane( 1, createLeftPanel(),
      createVPane( 1, createHPane( 1, addCompanyButton, removeCompanyButton, createDocButton ), createCentralPanel() ) ) );
    addListeners();
  }

  protected JPanel createLeftPanel()
  {
    JPanel panel =
      createVPane( 1, new JScrollPane( groupTreeSimplePanel ), new JScrollPane( studentsLeftTable ),
        createHPane( 1, addStudentButton ) );
    panel.setPreferredSize( new Dimension( getSize().width / 4, getSize().height ) );
    return panel;
  }

  protected JPanel createCentralPanel()
  {
    JPanel companiesScrollPane = createVPane( 1, new JScrollPane( practicePlacesTable ) );
    companiesScrollPane.setPreferredSize( new Dimension( getSize().width / 3, getSize().height ) );

    JPanel panel = createVPane( 1, new JScrollPane( studentsRightTable ), createHPane( 1, removeStudentButton ) );
    panel.setPreferredSize( new Dimension( getSize().width / 4, getSize().height ) );

    return createHPane( 1, companiesScrollPane, panel );
  }

  protected void addListeners()
  {
    addCompanyButton.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        new PracticePlaceSelectFrame( getPracticeType() ).showFrame( PracticePlacesListFrame.this,
          practicePlacesTableModel );
      }
    } );

    removeCompanyButton.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        PracticeService.removePracticePlace( getSelectedPlaceId(), getPracticeType() );
        practicePlacesTableModel.updateData();
        studentsRightTableModel.updateStudents( null );
        studentsLeftTableModel.updateData();
      }
    } );

    addStudentButton.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent arg0 )
      {
        String placeId = getSelectedPlaceId();
        if( placeId != null )
        {
          String studentId[] = getLeftSelectedStudentId();
          boolean result = PracticeService.getPracticePlace( placeId, getPracticeType() ).addStudent( studentId );
          if( !result )
          {
            JOptionPane.showMessageDialog( PracticePlacesListFrame.this,
              "На данном предприятии недостаточно свободных мест" );
            return;
          }
          studentsRightTableModel.updateStudents( StudentsService.getStudentsByPracticePlaceId( placeId,
            getPracticeType() ) );
          practicePlacesTableModel.updateData();
          studentsLeftTableModel.updateData();
        }
      }
    } );

    removeStudentButton.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent arg0 )
      {
        String placeId = getSelectedPlaceId();
        String studentId[] = getRightSelectedStudentId();
        if( placeId != null && studentId != null )
        {
          PracticeService.getPracticePlace( placeId, getPracticeType() ).removeStudent( studentId );
          studentsRightTableModel.updateStudents( StudentsService.getStudentsByPracticePlaceId( placeId,
            getPracticeType() ) );
          practicePlacesTableModel.updateData();
          studentsLeftTableModel.updateData();
        }
      }
    } );

    practicePlacesTable.addMouseListener( new MouseAdapter()
    {
      @Override
      public void mouseClicked( MouseEvent arg0 )
      {
        updateRightTable();
      }
    } );

    practicePlacesTable.addKeyListener( new KeyAdapter()
    {
      @Override
      public void keyReleased( KeyEvent arg0 )
      {
        updateRightTable();
      }
    } );

  }

  protected String getSelectedPlaceId()
  {
    int selectedRow = practicePlacesTable.getSelectedRow();
    if( selectedRow != -1 )
    {
      return ( String ) practicePlacesTableModel.getValueAt( selectedRow, TableModelConst.ID_COLUMN );
    }
    return null;
  }

  protected String[] getLeftSelectedStudentId()
  {
    return studentsLeftTable.getSelectedIds();
  }

  protected String[] getRightSelectedStudentId()
  {
    return studentsRightTable.getSelectedIds();
  }

  protected void updateRightTable()
  {
    String placeId = getSelectedPlaceId();
    if( placeId != null )
    {
      studentsRightTableModel
        .updateStudents( StudentsService.getStudentsByPracticePlaceId( placeId, getPracticeType() ) );
    }
  }

  protected JPopupMenu createPopup()
  {
    JPopupMenu reportPopup = new JPopupMenu();
    JMenuItem item = new JMenuItem( "Заявка на места практики" );
    item
      .addActionListener( new SingleReportCreationAction( ReportType.PRACTICE_PLACES_REQUEST_REPORT, new ReportParams(
        FileUtils.getPracticeDocumentPath( ReportType.PRACTICE_PLACES_REQUEST_REPORT.getTemplateName() ) ), this ) );
    reportPopup.add( item );

    item = new JMenuItem( "Договора на практику" );
    item.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        try
        {
          PracticeContractGenerator.generateContracts( getPracticeType() );
        }
        catch( Exception e1 )
        {
          e1.printStackTrace();
        }
      }
    } );
    reportPopup.add( item );
    return reportPopup;
  }
}
