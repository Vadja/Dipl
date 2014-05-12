package org.iit.dr.documents.common.generator;

import org.iit.dr.documents.common.report.Report;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public interface IReportGenerator
{
  public Report generateReport( ReportParams params );
}
