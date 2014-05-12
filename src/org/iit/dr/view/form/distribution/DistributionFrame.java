package org.iit.dr.view.form.distribution;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import org.iit.dr.documents.common.generator.ReportParams;
import org.iit.dr.documents.common.report.ReportType;
import org.iit.dr.services.DistributionService;
import org.iit.dr.utils.FileUtils;
import org.iit.dr.view.action.SingleReportCreationAction;
import org.iit.dr.view.component.ComponentsMediator;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.component.JTableExt;
import org.iit.dr.view.component.PopupMenuButton;
import org.iit.dr.view.form.distribution.table.CompaniesIdSelectAction;
import org.iit.dr.view.form.distribution.table.DistributionCompaniesTable;
import org.iit.dr.view.form.distribution.table.DistributionStudentsTable;
import org.iit.dr.view.model.table.distribution.DistributionCompaniesTableModel;
import org.iit.dr.view.model.table.distribution.DistributionStudentsTableModel;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class DistributionFrame extends JInternalFrameExt<Object>
{
  private static final long serialVersionUID = 1767761738179375279L;

  public static final String TITLE = "Распределение студентов";

  private JTableExt studentsTable;

  private JTableExt companiesTable;

  private JButton addCompanyButton;

  private JButton removeCompanyButton;

  private PopupMenuButton generateDocsButton;

  private ComponentsMediator mediator;

  @Override
  public boolean showFrame( Object parent, Object e )
  {
    setTitle( TITLE );
    setVisible( true );
    return true;
  }

  @Override
  protected void init()
  {
    setMinimumSize( new Dimension( 1000, 520 ) );
    mediator = new ComponentsMediator();
    DistributionStudentsTableModel studentsModel = new DistributionStudentsTableModel( mediator );
    DistributionCompaniesTableModel companiesModel = new DistributionCompaniesTableModel( mediator );
    studentsTable = new DistributionStudentsTable( studentsModel );
    companiesTable = new DistributionCompaniesTable( companiesModel );
    addCompanyButton = new JButton( "Добавить предприятие" );
    addCompanyButton.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        String[] compIds = ( String[] ) new CompaniesIdSelectAction().selectAlternatives( null );
        DistributionService.addDistrbutionPlaces( compIds );
        mediator.updateComponents();
      }
    } );

    removeCompanyButton = new JButton( "Удалить предприятие" );
    removeCompanyButton.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        DistributionService.removeDistrbutionPlaces( companiesTable.getSelectedIds() );
        mediator.updateComponents();
      }
    } );
    generateDocsButton = new PopupMenuButton( "Формирование документов", createPopup() );
  }

  @Override
  protected void generateComponents()
  {
    add( createVPane( 1, createHPane( 1, createStudentsPanel(), createCompaniesPanel() ) ) );
  }

  private JPanel createStudentsPanel()
  {
    return createVPane( 1, createLabel( "Студенты" ), new JScrollPane( studentsTable ),
      createButtonPane( 1, generateDocsButton ) );
  }

  private JPanel createCompaniesPanel()
  {
    return createVPane( 1, createLabel( "Предприятия" ), new JScrollPane( companiesTable ),
      createButtonPane( 1, addCompanyButton, removeCompanyButton ) );
  }

  protected JPopupMenu createPopup()
  {
    JPopupMenu reportPopup = new JPopupMenu();
    JMenuItem item = new JMenuItem( "Отчет по распределению" );
    item.addActionListener( new SingleReportCreationAction( ReportType.DISTRIBUTION_RESULT_REPORT, new ReportParams(
      FileUtils.getCommonDocumentPath( ReportType.DISTRIBUTION_RESULT_REPORT.getTemplateName() ) ), this ) );
    reportPopup.add( item );
    return reportPopup;
  }
}
