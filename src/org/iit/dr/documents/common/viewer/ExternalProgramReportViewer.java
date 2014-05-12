package org.iit.dr.documents.common.viewer;

import java.io.IOException;

import org.iit.dr.documents.common.report.Report;
import org.iit.dr.documents.common.viewer.exceptions.ReportViewerException;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class ExternalProgramReportViewer implements IReportViewer
{
  private static final String QUOTES = "\"";

  private String viewProgramPath;

  public ExternalProgramReportViewer( String viewProgramPath )
  {
    this.viewProgramPath = viewProgramPath;
  }

  public void viewReport( String reportPath )
  {
    try
    {
      Runtime.getRuntime().exec( QUOTES + viewProgramPath + QUOTES + " " + QUOTES + reportPath + QUOTES );
    }
    catch( IOException e )
    {
      throw new ReportViewerException(
        "Can't open report '" + reportPath + "' with programm '" + viewProgramPath + "'", e );
    }
  }

  public void viewReport( Report report )
  {
    viewReport( report.getFilePath() );
  }
}
