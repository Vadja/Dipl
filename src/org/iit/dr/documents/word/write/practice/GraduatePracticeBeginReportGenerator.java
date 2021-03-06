package org.iit.dr.documents.word.write.practice;

import org.iit.dr.data_model.PracticeType;
import org.iit.dr.documents.common.generator.IReportGenerator;
import org.iit.dr.documents.common.generator.ReportParams;
import org.iit.dr.documents.common.generator.exceptions.ReportGeneratorException;
import org.iit.dr.documents.common.report.Report;
import org.iit.dr.documents.common.report.ReportType;
import org.iit.dr.documents.word.write.common.WordDocBuilder;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class GraduatePracticeBeginReportGenerator extends BasePracticeBeginReportGenerator implements IReportGenerator
{
  private WordDocBuilder builder;

  public Report generateReport( ReportParams params )
  {
    try
    {
      builder = new WordDocBuilder( ReportType.GRADUATE_PRACTICE_BEGIN_REPORT.getTemplatePath() );
      builder.appendTableData( FLAG, prepareTableData( PracticeType.GRADUATING ) );
      builder.saveDoc( params.getOtputFilePath() );
    }
    catch( Exception e )
    {
      e.printStackTrace();
      throw new ReportGeneratorException( "Can't create report: "
        + ReportType.GRADUATE_PRACTICE_BEGIN_REPORT.getTemplateName(), e );
    }
    return new Report( params.getOtputFilePath(), ReportType.GRADUATE_PRACTICE_BEGIN_REPORT );
  }
}
