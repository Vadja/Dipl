package org.iit.dr.view.action;

import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.iit.dr.config.ApplicationConfig;
import org.iit.dr.documents.common.generator.IReportGenerator;
import org.iit.dr.documents.common.generator.ReportParams;
import org.iit.dr.documents.common.generator.exceptions.ReportGeneratorException;
import org.iit.dr.documents.common.report.Report;
import org.iit.dr.documents.common.report.ReportFormat;
import org.iit.dr.documents.common.report.ReportType;
import org.iit.dr.documents.common.viewer.IReportViewer;
import org.iit.dr.documents.common.viewer.ReportViewerFactory;
import org.iit.dr.documents.common.viewer.exceptions.ReportViewerException;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class BaseReportCreationAction
{
  protected Component component;

  public Report generateReport( ReportType reportType, ReportParams params )
  {
    IReportGenerator generator = reportType.getGenerator();
    try
    {
      return generator.generateReport( params );
    }
    catch( ReportGeneratorException e )
    {
      JOptionPane.showMessageDialog( component, "Не удалось сгенерировать отчет: \n" + params.getOtputFilePath(),
        "Ошибка", JOptionPane.ERROR_MESSAGE );
      return null;
    }
  }

  public boolean isNeedToOpenReport( Report report )
  {
    int result =
      JOptionPane.showConfirmDialog( component, "Отчет успешно сгененрирован: \n" + report.getFilePath()
        + "\n Открыть отчет?", "Информация", JOptionPane.YES_NO_OPTION );
    if( result == JOptionPane.OK_OPTION )
      return true;

    return false;
  }

  public void viewReport( Report report )
  {
    try
    {
      IReportViewer viewer = ReportViewerFactory.createViewer( report.getType() );
      viewer.viewReport( report );
    }
    catch( ReportViewerException e )
    {
      JOptionPane.showMessageDialog( component, "Не удалось открыть отчет: \n" + report.getFilePath(), "Ошибка",
        JOptionPane.ERROR_MESSAGE );
      if( isNeedToOpenByOtherProgram( report.getType().getReportFormat() ) )
      {
        viewReport( report );
      }
    }
  }

  public boolean isNeedToOpenByOtherProgram( ReportFormat format )
  {
    JFileChooser fileChooser = new JFileChooser( "Выберите программу для открытия отчета" );
    int result = fileChooser.showDialog( component, "Открыть" );
    if( result == JFileChooser.APPROVE_OPTION )
    {
      File file = fileChooser.getSelectedFile();
      ApplicationConfig.getInstance().setProperty( format.getPropertyKey(), file.getAbsolutePath() );
      return true;
    }
    return false;
  }
}
