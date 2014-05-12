package org.iit.dr.documents.common.viewer;

import org.iit.dr.config.ApplicationConfig;
import org.iit.dr.documents.common.report.ReportFormat;
import org.iit.dr.documents.common.report.ReportType;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class ReportViewerFactory
{
  public static IReportViewer createViewer( ReportFormat reportFormat )
  {
    return new ExternalProgramReportViewer( ApplicationConfig.getInstance().getProperty( reportFormat.getPropertyKey() ) );
  }

  public static IReportViewer createViewer( ReportType reportType )
  {
    return createViewer( reportType.getReportFormat() );
  }
}
