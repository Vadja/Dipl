package org.iit.dr.view.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.iit.dr.documents.common.generator.ReportParams;
import org.iit.dr.documents.common.report.Report;
import org.iit.dr.documents.common.report.ReportType;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class SingleReportCreationAction extends BaseReportCreationAction implements ActionListener
{
  private ReportType reportType;

  private ReportParams params;

  public SingleReportCreationAction( ReportType reportType, ReportParams params, Component component )
  {
    this.reportType = reportType;
    this.params = params;
    this.component = component;
  }

  public SingleReportCreationAction( ReportType reportType, ReportParams params )
  {
    this( reportType, params, null );
  }

  public void actionPerformed( ActionEvent event )
  {
    Report report = generateReport( reportType, params );
    if( report == null )
      return;

//    if( isNeedToOpenReport( report ) )
//    {
//      viewReport( report );
//    }
  }
}
