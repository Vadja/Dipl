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

import org.iit.dr.data_model.PracticeConsultant;
import org.iit.dr.data_model.PracticeType;
import org.iit.dr.documents.common.generator.ReportParams;
import org.iit.dr.documents.common.report.ReportType;
import org.iit.dr.documents.word.write.practice.PracticeLettersGenerator;
import org.iit.dr.services.PracticeService;
import org.iit.dr.services.StudentsService;
import org.iit.dr.utils.FileUtils;
import org.iit.dr.view.action.SingleReportCreationAction;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.component.JTableExt;
import org.iit.dr.view.component.PopupMenuButton;
import org.iit.dr.view.model.table.common.TableModelConst;
import org.iit.dr.view.model.table.practice.PracticeConsultantTableModel;
import org.iit.dr.view.model.table.practice.PracticeWorksTableModel;
import org.iit.dr.view.model.table.students.SimpleStudentsTableModel;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public abstract class PracticeConsultantsListFrame extends JInternalFrameExt<Object>
{
  private static final long serialVersionUID = 2366348513613072159L;

  private PracticeWorksTableModel studentsLeftTableModel;

  private SimpleStudentsTableModel studentsRightTableModel;

  private PracticeConsultantTableModel practiceConsultantsTableModel;

  private JTableExt studentsLeftTable;

  private JTableExt studentsRightTable;

  private JTableExt practiceConsultantsTable;

  private JButton removeConsultantButton;

  private JButton addConsultantButton;

  private JButton removeStudentButton;

  private JButton addStudentButton;

  private PracticeConsultantSelectFrame stafferListFrame;

  private PopupMenuButton createDocButton;

  public abstract PracticeType getPracticeType();

  public PracticeConsultantsListFrame()
  {
    stafferListFrame = new PracticeConsultantSelectFrame( getPracticeType() );
  }

  @Override
  public boolean showFrame( Object parent, Object e )
  {
    studentsLeftTableModel.updateData();
    setVisible( true );
    return true;
  }

  @Override
  protected void init()
  {
    setSize( new Dimension( 1000, 520 ) );
    setMinimumSize( getSize() );
    studentsLeftTableModel = new PracticeWorksTableModel( getPracticeType() );
    studentsLeftTable = new JTableExt( studentsLeftTableModel );
    studentsLeftTableModel.updateData();

    practiceConsultantsTableModel = new PracticeConsultantTableModel( getPracticeType() );
    practiceConsultantsTable =
      new JTableExt( practiceConsultantsTableModel, PracticeConsultantTableModel.COLUMNS_PROPORTIONS );
    practiceConsultantsTableModel.updateData();

    studentsRightTableModel = new SimpleStudentsTableModel( getPracticeType() );
    studentsRightTable = new JTableExt( studentsRightTableModel );

    addConsultantButton = new JButton( " + Добавить преподавателя " );
    removeConsultantButton = new JButton( " - Удалить преподавателя " );
    createDocButton = new PopupMenuButton( " Формирование документов ", createPopup() );

    addStudentButton = new JButton( "Добавить студента >>>" );
    removeStudentButton = new JButton( "<<< Удалить студента" );

  }

  @Override
  protected void generateComponents()
  {
    add( createHPane(
      1,
      createLeftPanel(),
      createVPane( 1, createHPane( 1, addConsultantButton, removeConsultantButton, createDocButton ),
        createCentralPanel() ) ) );
    addListeners();
  }

  private JPanel createLeftPanel()
  {
    JPanel panel = createVPane( 1, new JScrollPane( studentsLeftTable ), createHPane( 1, addStudentButton ) );
    panel.setPreferredSize( new Dimension( getSize().width / 4, getSize().height ) );
    return panel;
  }

  private JPanel createCentralPanel()
  {
    JScrollPane consScrollPane = new JScrollPane( practiceConsultantsTable );
    consScrollPane.setPreferredSize( new Dimension( getSize().width / 3, getSize().height * 2 / 3 - 30 ) );

    JPanel panel = createVPane( 1, new JScrollPane( studentsRightTable ), createHPane( 1, removeStudentButton ) );
    panel.setPreferredSize( new Dimension( getSize().width / 4, getSize().height * 2 / 3 - 30 ) );

    return createHPane( 1, consScrollPane, panel );
  }

  private void addListeners()
  {
    addConsultantButton.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        stafferListFrame.showFrame( PracticeConsultantsListFrame.this, practiceConsultantsTableModel );
      }
    } );

    removeConsultantButton.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        String teacherId = getSelectedTeacherId();
        if( teacherId != null )
        {
          PracticeService.removePracticeConsultent( new PracticeConsultant( teacherId ), getPracticeType() );
          practiceConsultantsTableModel.updateData();
          studentsRightTableModel.updateStudents( null );
          studentsLeftTableModel.updateData();
        }
      }
    } );

    addStudentButton.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent arg0 )
      {
        String teacherId = getSelectedTeacherId();
        String studentId[] = getLeftSelectedStudentId();
        if( teacherId != null && studentId != null )
        {
          boolean result = PracticeService.setConsultant( studentId, teacherId, getPracticeType() );
          if( !result )
          {
            JOptionPane.showMessageDialog( PracticeConsultantsListFrame.this,
              "У данного преподавателя не хватает свободных мест" );
            return;
          }
          studentsRightTableModel.updateStudents( StudentsService.getStudentsByConsultantId( teacherId,
            getPracticeType() ) );
          practiceConsultantsTableModel.updateData();
          studentsLeftTableModel.updateData();
        }
      }
    } );

    removeStudentButton.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent arg0 )
      {
        String[] studentId = getRightSelectedStudentId();
        String teacherId = getSelectedTeacherId();
        if( teacherId != null && studentId != null )
        {
          PracticeService.deleteConsultantFromStudent( studentId, getPracticeType() );
          studentsRightTableModel.updateStudents( StudentsService.getStudentsByConsultantId( teacherId,
            getPracticeType() ) );
          practiceConsultantsTableModel.updateData();
          studentsLeftTableModel.updateData();
        }
      }
    } );

    practiceConsultantsTable.addMouseListener( new MouseAdapter()
    {
      @Override
      public void mouseClicked( MouseEvent arg0 )
      {
        updateRightTable();
      }
    } );

    practiceConsultantsTable.addKeyListener( new KeyAdapter()
    {
      @Override
      public void keyReleased( KeyEvent arg0 )
      {
        updateRightTable();
      }
    } );
  }

  protected String getSelectedTeacherId()
  {
    int selectedRow = practiceConsultantsTable.getSelectedRow();
    if( selectedRow != -1 )
    {
      return ( String ) practiceConsultantsTableModel.getValueAt( selectedRow, TableModelConst.ID_COLUMN );
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
    String placeId = getSelectedTeacherId();
    if( placeId != null )
    {
      studentsRightTableModel.updateStudents( StudentsService.getStudentsByConsultantId( placeId, getPracticeType() ) );
    }
  }

  private JPopupMenu createPopup()
  {
    JPopupMenu reportPopup = new JPopupMenu();
    JMenuItem item = new JMenuItem( "Списки на практику" );
    ReportType reportType =
      ( getPracticeType() == PracticeType.GRADUATING ) ? ReportType.GRADUATE_PRACTICE_LIST_REPORT
        : ReportType.PRODUCTION_PRACTICE_LIST_REPORT;

    item.addActionListener( new SingleReportCreationAction( reportType, new ReportParams( FileUtils
      .getPracticeDocumentPath( reportType.getTemplateName() ) ), this )
    {
      @Override
      public void actionPerformed( ActionEvent e )
      {
        if( studentsLeftTableModel.getRowCount() == 0 )
        {
          super.actionPerformed( e );
        }
        else
        {
          JOptionPane.showMessageDialog( PracticeConsultantsListFrame.this, "У " + studentsLeftTableModel.getRowCount()
            + " студентов не назначен руководитель практики" );
        }
      }
    } );
    reportPopup.add( item );

    item = new JMenuItem( "Письма на предприятия" );
    item.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        try
        {
          PracticeLettersGenerator.generateLetters( getPracticeType() );
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
