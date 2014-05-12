package org.iit.dr.view.action;

import java.awt.Component;
import java.awt.event.ActionEvent;

import org.iit.dr.documents.common.generator.ReportParams;
import org.iit.dr.documents.common.report.ReportType;
import org.iit.dr.utils.FileUtils;

/**
 * OpisDPAction.
 * 
 * @author Yuriy Karpovich
 */
public class OpisDPAction extends CustomAction
{
  SingleReportCreationAction action;

  public OpisDPAction( Component comp )
  {
    action =
      new SingleReportCreationAction( ReportType.GRADUATE_WORKS_OPIS_REPORT, new ReportParams(
        FileUtils.getCommonDocumentPath( ReportType.GRADUATE_WORKS_OPIS_REPORT.getTemplateName() ) ), comp );
    putValue( NAME, "Сформировать опись дипломных проектов" );
  }

  public void actionPerformed( ActionEvent e )
  {
    action.actionPerformed( e );
  }
}