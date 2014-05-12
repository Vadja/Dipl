package org.iit.dr.documents.word.write.practice;

import org.iit.dr.data_model.PracticeType;
import org.iit.dr.documents.common.generator.IReportGenerator;
import org.iit.dr.documents.common.generator.ReportParams;
import org.iit.dr.documents.common.generator.exceptions.ReportGeneratorException;
import org.iit.dr.documents.common.report.Report;
import org.iit.dr.documents.common.report.ReportType;
import org.iit.dr.documents.word.write.common.WordDocBuilder;

public class ProductionPracticeResultReportGenerator extends BasePracticeResultReportGenerator implements
  IReportGenerator
{
  private WordDocBuilder builder;

  public Report generateReport( ReportParams params )
  {
    try
    {
      PracticeType practiceType = PracticeType.PRODUCTION;
      builder = new WordDocBuilder( ReportType.PRODUCTION_PRACTICE_RESULT_REPORT.getTemplatePath() );
      builder.appendTableData( FLAG1, preparePracticePlacesData( practiceType ) );
      builder.appendTableData( FLAG2, prepareMarksData( practiceType ) );
      builder.saveDoc( params.getOtputFilePath() );
    }
    catch( Exception e )
    {
      e.printStackTrace();
      throw new ReportGeneratorException( "Can't create report: "
        + ReportType.PRODUCTION_PRACTICE_RESULT_REPORT.getTemplateName(), e );
    }
    return new Report( params.getOtputFilePath(), ReportType.PRODUCTION_PRACTICE_RESULT_REPORT );
  }
}
