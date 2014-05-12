package org.iit.dr.view.action;

import java.awt.Component;
import java.awt.event.ActionEvent;

import org.iit.dr.documents.common.generator.ReportParams;
import org.iit.dr.documents.common.report.ReportType;

/**
 * OpisDPAction.
 * 
 * @author Yuriy Karpovich
 */
public class ReportGekAction extends CustomAction
{
  private SingleReportCreationAction action;

  public ReportGekAction( ReportType reportType, ReportParams params, Component component )
  {
    action = new SingleReportCreationAction( reportType, params, component );
    putValue( NAME, "Сформировать отчет председателя ГЭК" );
  }

  public void actionPerformed( ActionEvent e )
  {
    action.actionPerformed( e );
  }
}