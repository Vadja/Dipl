package org.iit.dr.view.form.practice;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import org.iit.dr.data_model.PracticeType;
import org.iit.dr.documents.common.generator.ReportParams;
import org.iit.dr.documents.common.report.ReportType;
import org.iit.dr.services.PracticeService;
import org.iit.dr.utils.FileUtils;
import org.iit.dr.view.action.SingleReportCreationAction;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.component.JTableExt;
import org.iit.dr.view.component.PopupMenuButton;
import org.iit.dr.view.model.table.common.TableModelConst;
import org.iit.dr.view.model.table.practice.PracticeTableModel;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public abstract class PracticeWorkListFrame extends JInternalFrameExt<Object>
{
  private static final long serialVersionUID = 1842248753135546750L;

  PracticeTableModel model;

  JTableExt practiceWorksTable;

  PracticeFrame practiceFrame;

  @Override
  protected void init()
  {
    setMinimumSize( new Dimension( 1000, 520 ) );
    practiceFrame = new PracticeFrame();
  }

  private JPopupMenu createPopup()
  {
    JPopupMenu reportPopup = new JPopupMenu();
    JMenuItem item = new JMenuItem( "Докладная о начале практике" );

    ReportType reportType1 =
      ( getPracticeType() == PracticeType.GRADUATING ) ? ReportType.GRADUATE_PRACTICE_BEGIN_REPORT
        : ReportType.PRODUCTION_PRACTICE_BEGIN_REPORT;
    item.addActionListener( new SingleReportCreationAction( reportType1, new ReportParams( FileUtils
      .getPracticeDocumentPath( reportType1.getTemplateName() ) ), this ) );
    reportPopup.add( item );

    item = new JMenuItem( "Отчет о результатах практики" );
    ReportType reportType2 =
      ( getPracticeType() == PracticeType.GRADUATING ) ? ReportType.GRADUATE_PRACTICE_RESULT_REPORT
        : ReportType.PRODUCTION_PRACTICE_RESULT_REPORT;
    item.addActionListener( new SingleReportCreationAction( reportType2, new ReportParams( FileUtils
      .getPracticeDocumentPath( reportType2.getTemplateName() ) ), this ) );
    reportPopup.add( item );
    return reportPopup;
  }

  @Override
  protected void generateComponents()
  {

    model = new PracticeTableModel( getPracticeType() );
    practiceWorksTable = new JTableExt( model, PracticeTableModel.COLUMN_PROPORTIONS );
    JScrollPane jScrollPane = new JScrollPane( practiceWorksTable );
    add( jScrollPane, BorderLayout.CENTER );

    JButton editButton = new JButton( "Редактировать" );
    editButton.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        String workId = getSelectedPracticeWorkId();
        if( workId != null )
        {
          practiceFrame.showFrame( model, PracticeService.getPracticeWorkByStudentId( workId, getPracticeType() ) );
        }
      }
    } );
    getContentPane().add( createNorthButtonPane( 5, editButton ), BorderLayout.NORTH );
    PopupMenuButton reportButton = new PopupMenuButton( "Формирование документов", createPopup() );
    getContentPane().add( createNorthButtonPane( 5, reportButton ), BorderLayout.SOUTH );
  }

  @Override
  public boolean showFrame( Object parent, Object o )
  {
    setVisible( true );
    return true;
  }

  protected String getSelectedPracticeWorkId()
  {
    if( practiceWorksTable.getSelectedRow() >= 0 )
    {
      return ( String ) model.getValueAt( practiceWorksTable.getSelectedRow(), TableModelConst.ID_COLUMN );
    }
    return null;
  }

  public abstract PracticeType getPracticeType();
}