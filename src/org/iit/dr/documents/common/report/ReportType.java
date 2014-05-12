package org.iit.dr.documents.common.report;

import org.iit.dr.documents.common.generator.IReportGenerator;
import org.iit.dr.documents.word.write.distribution.DistributionReportGenerator;
import org.iit.dr.documents.word.write.graduate_work.GekReportGenerator;
import org.iit.dr.documents.word.write.graduate_work.GraduateWorkThemesReportGenerator;
import org.iit.dr.documents.word.write.graduate_work.OpisDPDocumentGenerator;
import org.iit.dr.documents.word.write.practice.GraduatePracticeBeginReportGenerator;
import org.iit.dr.documents.word.write.practice.GraduatePracticeListReportGenerator;
import org.iit.dr.documents.word.write.practice.GraduatePracticeResultReportGenerator;
import org.iit.dr.documents.word.write.practice.PracticePlacesRequestGenerator;
import org.iit.dr.documents.word.write.practice.ProductionPracticeBeginReportGenerator;
import org.iit.dr.documents.word.write.practice.ProductionPracticeListReportGenerator;
import org.iit.dr.documents.word.write.practice.ProductionPracticeResultReportGenerator;
import org.iit.dr.utils.FileUtils;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public enum ReportType
{
  DISTRIBUTION_RESULT_REPORT( "отчет по распределению.doc", ReportFormat.WORD, new DistributionReportGenerator() ),

  GRADUATE_PRACTICE_BEGIN_REPORT( "Докладная о начале преддипломной практики.doc", ReportFormat.WORD,
    new GraduatePracticeBeginReportGenerator() ),

  PRODUCTION_PRACTICE_BEGIN_REPORT( "Докладная о начале производственной практики.doc", ReportFormat.WORD,
    new ProductionPracticeBeginReportGenerator() ),

  GRADUATE_PRACTICE_RESULT_REPORT( "Отчет о преддипломной практике.doc", ReportFormat.WORD,
    new GraduatePracticeResultReportGenerator() ),

  PRODUCTION_PRACTICE_RESULT_REPORT( "Отчет о производственной практике.doc", ReportFormat.WORD,
    new ProductionPracticeResultReportGenerator() ),

  GRADUATE_PRACTICE_LIST_REPORT( "Списки на преддипл практику.doc", ReportFormat.WORD,
    new GraduatePracticeListReportGenerator() ),

  PRODUCTION_PRACTICE_LIST_REPORT( "Списки на производственную практику.doc", ReportFormat.WORD,
    new ProductionPracticeListReportGenerator() ),

  GEK_REPORT( "Отчет председателя ГЭК.doc", ReportFormat.WORD, new GekReportGenerator() ), GEK_REPORT_DO(
    "Отчет председателя ГЭК с ДО.doc", ReportFormat.WORD, null ), GRADUATE_WORKS_OPIS_REPORT(
    "Опись дипломных проектов.doc", ReportFormat.WORD, new OpisDPDocumentGenerator() ),

  GRADUATE_WORKS_THEMS_REPORT( "Темы_ДП.doc", ReportFormat.WORD, new GraduateWorkThemesReportGenerator() ),

  PRACTICE_PLACES_REQUEST_REPORT( "Заявка на места практики.doc", ReportFormat.WORD,
    new PracticePlacesRequestGenerator() );

  private String templateName;

  private ReportFormat reportFormat;

  private IReportGenerator generator;

  private ReportType( String templateName, ReportFormat reportFormat, IReportGenerator generator )
  {
    this.templateName = templateName;
    this.reportFormat = reportFormat;
    this.generator = generator;
  }

  public String getTemplateName()
  {
    return templateName;
  }

  public String getTemplatePath()
  {
    return FileUtils.getTemplate( templateName );
  }

  public ReportFormat getReportFormat()
  {
    return reportFormat;
  }

  public IReportGenerator getGenerator()
  {
    return generator;
  }
}
