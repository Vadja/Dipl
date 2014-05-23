package org.iit.dr.view;

import static org.iit.dr.utils.LabelUtilsNames.APPLICATION_NAME;
import static org.iit.dr.utils.LabelUtilsNames.MAIN_MENU_DICTIONARY;
import static org.iit.dr.utils.LabelUtilsNames.MAIN_MENU_FILE;
import static org.iit.dr.utils.LabelUtilsNames.MAIN_MENU_HELP;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
//import javax.swing.filechooser.FileNameExtensionFilter;

import org.iit.dr.documents.common.generator.ReportParams;
import org.iit.dr.documents.common.report.ReportType;
import org.iit.dr.subsystems.doc_archive.view.form.DocArchiveSystForm;
import org.iit.dr.subsystems.publication_subsyst.view.form.PublicationsSystForm;
import org.iit.dr.utils.FileUtils;
import org.iit.dr.utils.LabelUtils;
import org.iit.dr.view.action.AboutAction;
import org.iit.dr.view.action.BackupDataAction;
import org.iit.dr.view.action.OpenFrameAction;
import org.iit.dr.view.action.OpisDPAction;
import org.iit.dr.view.action.ReportGekAction;
import org.iit.dr.view.action.SaveDataAction;
import org.iit.dr.view.form.books.BookListFrame;
import org.iit.dr.view.form.common.ConfigFrame;
import org.iit.dr.view.form.companies.CompaniesListFrame;
import org.iit.dr.view.form.conferences.ConferenceListFrame;
import org.iit.dr.view.form.distribution.DistributionFrame;
import org.iit.dr.view.form.graduate_work.CommonDefenceGWFrame;
import org.iit.dr.view.form.graduate_work.DefenceGraduateWorkListFrame;
import org.iit.dr.view.form.graduate_work.GekFrame;
import org.iit.dr.view.form.graduate_work.GraduateWorkListFrame;
import org.iit.dr.view.form.practice.GraduatePracticeConsultantsListFrame;
import org.iit.dr.view.form.practice.GraduatePracticePlacesListFrame;
import org.iit.dr.view.form.practice.GraduatePracticeWorkListFrame;
import org.iit.dr.view.form.practice.ProductionPracticeConsultantsListFrame;
import org.iit.dr.view.form.practice.ProductionPracticePlacesListFrame;
import org.iit.dr.view.form.practice.ProductionPracticeWorkListFrame;
import org.iit.dr.view.form.staffers.DPCreatorListFrame;
import org.iit.dr.view.form.staffers.StaffRateFrame;
import org.iit.dr.view.form.staffers.StafferListFrame;
import org.iit.dr.view.form.students.GroupTreeFrame;
import org.iit.dr.view.form.students.StudentListFrame;

/**
 * MainWindow.
 * 
 * @author Yuriy Karpovich
 */
public class MainWindow extends JFrame
{
  private static final long serialVersionUID = -4683672294551302301L;

  public static Component activeComponent;

  public MainWindow() throws HeadlessException
  {

    setDefaultCloseOperation( EXIT_ON_CLOSE );
    setMinimumSize( new Dimension( 1200, 600 ) );
    setTitle( LabelUtils.getLabel( APPLICATION_NAME ) );

    applyBoundsSettings();
    applayMenu();

    // applayComponents();
    // applayToolBar();

    addWindowListener( new WindowAdapter()
    {

      @Override
      public void windowClosing( WindowEvent e )
      {

        StartApp.closeApplication( false, true );
      }
    } );
  }

  private void applyBoundsSettings()
  {

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();

    Dimension minimumSize = getMinimumSize();

    setBounds( ( screenSize.width - minimumSize.width ) / 2, ( screenSize.height - minimumSize.height ) / 2,
      minimumSize.width, minimumSize.height );

  }

  private void applayMenu()
  {

    JMenuBar jMenuBar = new JMenuBar();

    JMenu jMenu = new JMenu( LabelUtils.getLabel( MAIN_MENU_FILE ) );

    JMenuItem jMenuItem = null;
//     JMenuItem jMenuItem = new JMenuItem(new ImportScheduleAction(this));
//     jMenu.add(jMenuItem);
//     jMenuItem = new JMenuItem(new ImportPayloadAction(this));
//     jMenu.add(jMenuItem);
//     jMenu.addSeparator();
//     jMenuItem = new JMenuItem(generateRealLessonsAction);
//     jMenu.add(jMenuItem);
//     jMenu.addSeparator();
//     jMenuItem = new JMenuItem(exportToXlsAction);
//     jMenu.add(jMenuItem);
//     jMenu.addSeparator();
//     jMenuItem = new JMenuItem(new ExitAction(this));
//     jMenu.add(jMenuItem);

    jMenuBar.add( jMenu );

    // --------------

    jMenu = new JMenu( LabelUtils.getLabel( MAIN_MENU_DICTIONARY ) );

    jMenuItem = new JMenuItem( new OpenFrameAction( "Студенты", this, StudentListFrame.class ) );
    jMenu.add( jMenuItem );
    jMenuItem = new JMenuItem( new OpenFrameAction( "Подразделения", this, GroupTreeFrame.class ) );
    jMenu.add( jMenuItem );
    jMenuItem = new JMenuItem( new OpenFrameAction( "Преподаватели", this, StafferListFrame.class ) );
    jMenu.add( jMenuItem );
    jMenuItem = new JMenuItem( new OpenFrameAction( "Штатное расписание", this, StaffRateFrame.class ) );
    jMenu.add( jMenuItem );
    jMenuItem = new JMenuItem( new OpenFrameAction( "Предприятия", this, CompaniesListFrame.class ) );
    jMenu.add( jMenuItem );

    // //////////////////////////////////////////
    jMenuItem = new JMenuItem( new OpenFrameAction( "Издания", this, BookListFrame.class ) );
    jMenu.add( jMenuItem );

    jMenuItem = new JMenuItem( new OpenFrameAction( "Конференции", this, ConferenceListFrame.class ) );
    jMenu.add( jMenuItem );

    // /////////////////////////////////////////////

    jMenuBar.add( jMenu );

    // -----------

    jMenu = new JMenu( "Производственная практика" );

    jMenuItem =
      new JMenuItem( new OpenFrameAction( "Список мест на практику", this, ProductionPracticePlacesListFrame.class ) );
    jMenu.add( jMenuItem );
    jMenuItem =
      new JMenuItem( new OpenFrameAction( "Список руководителей практики", this,
        ProductionPracticeConsultantsListFrame.class ) );
    jMenu.add( jMenuItem );
    jMenuItem =
      new JMenuItem( new OpenFrameAction( "Список тем на практику", this, ProductionPracticeWorkListFrame.class ) );
    jMenu.add( jMenuItem );
    // jMenu.addSeparator();
    // // jMenuItem = new JMenuItem(new OpenFrameAction("Формирование пакета документов", this, new
    // // PrepareReportsFrame()));
    // jMenuItem = new JMenuItem( "Формирование пакета документов" );
    // jMenu.add( jMenuItem );
    // // jMenuItem = new JMenuItem(new OpenFrameAction("Формирование договора", this, new PrepareReportsFrame()));
    // jMenuItem = new JMenuItem( "Формирование договора" );
    // jMenu.add( jMenuItem );

    jMenuBar.add( jMenu );

    // -----------

    jMenu = new JMenu( "Преддипломная практика" );

    jMenuItem =
      new JMenuItem( new OpenFrameAction( "Список мест на практику", this, GraduatePracticePlacesListFrame.class ) );
    jMenu.add( jMenuItem );
    jMenuItem =
      new JMenuItem( new OpenFrameAction( "Список руководителей практики", this,
        GraduatePracticeConsultantsListFrame.class ) );
    jMenu.add( jMenuItem );
    jMenuItem =
      new JMenuItem( new OpenFrameAction( "Список тем на практику", this, GraduatePracticeWorkListFrame.class ) );
    jMenu.add( jMenuItem );
    // jMenu.addSeparator();
    // // jMenuItem = new JMenuItem(new OpenFrameAction("Формирование пакета документов", this, new
    // // PrepareReportsFrame()));
    // jMenuItem = new JMenuItem( "Формирование пакета документов" );
    // jMenu.add( jMenuItem );
    // // jMenuItem = new JMenuItem(new OpenFrameAction("Формирование договора", this, new PrepareReportsFrame()));
    // jMenuItem = new JMenuItem( "Формирование договора" );
    // jMenu.add( jMenuItem );

    jMenuBar.add( jMenu );

    // -----------

    jMenu = new JMenu( "Распределение" );

    jMenuItem = new JMenuItem( new OpenFrameAction( "Список мест на распределение", this, DistributionFrame.class ) );
    jMenu.add( jMenuItem );
    jMenuBar.add( jMenu );

    // -----------

    jMenu = new JMenu( "Дипломное проектирование" );

    jMenuItem = new JMenuItem( new OpenFrameAction( "Список дипломных проектов", this, GraduateWorkListFrame.class ) );
    jMenu.add( jMenuItem );
     
    jMenuItem = new JMenuItem( new OpenFrameAction( "Список защиты дипломных проектов", this, DefenceGraduateWorkListFrame.class) );
     jMenu.add( jMenuItem );
    
    jMenuItem = new JMenuItem( new OpenFrameAction( "ГЭК", this, GekFrame.class ) );
    jMenu.add( jMenuItem );
    jMenuItem = new JMenuItem( new OpenFrameAction( "Защита дипломных проектов", this, CommonDefenceGWFrame.class ) );
    jMenu.add( jMenuItem );
    jMenu.addSeparator();

    jMenuItem = new JMenuItem( new OpisDPAction( this ) );
    jMenu.add( jMenuItem );
    jMenuItem =
      new JMenuItem( new ReportGekAction( ReportType.GEK_REPORT, new ReportParams( FileUtils
        .getCommonDocumentPath( ReportType.GEK_REPORT.getTemplateName() ) ), this ) );
    jMenu.add( jMenuItem );

    // jMenuItem = new JMenuItem(new OpenFrameAction("Формирование пакета документов", this, new
    // PrepareReportsFrame()));
    // jMenuItem = new JMenuItem("Формирование пакета документов");
    // jMenu.add(jMenuItem);
    // // jMenuItem = new JMenuItem(new OpenFrameAction("Формирование титульного листа", this, new
    // PrepareReportsFrame()));
    // jMenuItem = new JMenuItem("Формирование титульного листа");
    // jMenu.add(jMenuItem);
    // // jMenuItem = new JMenuItem(new OpenFrameAction("Формирование листа задания", this, new PrepareReportsFrame()));
    // jMenuItem = new JMenuItem("Формирование листа задания");
    // jMenu.add(jMenuItem);
    // // jMenuItem = new JMenuItem(new OpenFrameAction("Формирование дневника", this, new PrepareReportsFrame()));
    // jMenuItem = new JMenuItem("Формирование дневника");
    // jMenu.add(jMenuItem);
    // // jMenuItem = new JMenuItem(new OpenFrameAction("Формирование договора", this, new PrepareReportsFrame()));
    // jMenuItem = new JMenuItem("Формирование договора");
    // jMenu.add(jMenuItem);

    jMenuBar.add( jMenu );
    
    
    // -----------
    jMenu = new JMenu( "Формирование документов" );
    jMenuItem = new JMenuItem( new OpenFrameAction( "Договор подряда", this, DPCreatorListFrame.class ) );
    jMenu.add( jMenuItem );
    jMenuBar.add( jMenu );
    
    jMenu = new JMenu( "Управление" );

    // jMenuItem = new JMenuItem(new OpenFrameAction("Преддипломная практика / Диплом", this,
    // PrepareReportsFrame.class));
    // jMenu.add(jMenuItem);

//    jMenuItem = new JMenuItem( new AbstractAction()
//    {
//
//      public void actionPerformed( ActionEvent e )
//      {
//        JFileChooser fileChooser = new JFileChooser( "Выберите файл для загрузки");
//        FileNameExtensionFilter filter = new FileNameExtensionFilter("Doc files", "doc");
//        fileChooser.setFileFilter(filter);
//        fileChooser.setAcceptAllFileFilterUsed( false );
//        int result = fileChooser.showDialog( null, "Открыть" );
//        if( result == JFileChooser.APPROVE_OPTION )
//        {
//          File file = fileChooser.getSelectedFile();
//          StudentDPLoader loader = new StudentDPLoader( file.getAbsolutePath() );
//          loader.loadData();
//        }
//
//      }
//    } );
//    jMenuItem.setText( "Загрузка тем дипломов" );
//    jMenu.add( jMenuItem );

    jMenuItem = new JMenuItem( new SaveDataAction() );
    jMenu.add( jMenuItem );

    jMenuItem = new JMenuItem( new BackupDataAction() );
    jMenu.add( jMenuItem );

    jMenuItem = new JMenuItem( new OpenFrameAction( "Настройки", this, ConfigFrame.class ) );
    jMenu.add( jMenuItem );

    jMenuBar.add( jMenu );
    // -----------

    jMenu = new JMenu( "Подсистемы" );

    jMenuItem = new JMenuItem( new OpenFrameAction( "Подсистема архивации документации выпускающей кафедры", this, DocArchiveSystForm.class ) );
    jMenu.add( jMenuItem );

    jMenuItem = new JMenuItem( new OpenFrameAction( "Подсистема для публикации", this, PublicationsSystForm.class ) );
    jMenu.add( jMenuItem );

    jMenuBar.add( jMenu );
    // -----------

    jMenu = new JMenu( LabelUtils.getLabel( MAIN_MENU_HELP ) );
    //
    jMenuItem = new JMenuItem( new AboutAction() );
    jMenu.add( jMenuItem );
    jMenuBar.add( jMenu );

    setJMenuBar( jMenuBar );
  }

  public void openAndRestore()
  {

  }
}
