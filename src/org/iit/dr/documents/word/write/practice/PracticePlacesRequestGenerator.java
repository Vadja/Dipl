package org.iit.dr.documents.word.write.practice;

import java.util.List;

import org.iit.dr.data_model.PracticePlace;
import org.iit.dr.data_model.PracticeType;
import org.iit.dr.documents.common.generator.IReportGenerator;
import org.iit.dr.documents.common.generator.ReportParams;
import org.iit.dr.documents.common.generator.exceptions.ReportGeneratorException;
import org.iit.dr.documents.common.report.Report;
import org.iit.dr.documents.common.report.ReportType;
import org.iit.dr.documents.word.write.common.WordDocBuilder;
import org.iit.dr.services.CompaniesService;
import org.iit.dr.services.PracticeService;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class PracticePlacesRequestGenerator implements IReportGenerator
{
  public static final String FLAG1 = "#%$";

  public static final String FLAG2 = "#&$";

  private WordDocBuilder builder;

  public Report generateReport( ReportParams params )
  {
    try
    {
      builder = new WordDocBuilder( ReportType.PRACTICE_PLACES_REQUEST_REPORT.getTemplatePath() );
      builder.appendTableData( FLAG1, prepareData( PracticeType.GRADUATING ) );
      builder.appendTableData( FLAG2, prepareData( PracticeType.PRODUCTION ) );
      builder.saveDoc( params.getOtputFilePath() );
    }
    catch( Exception e )
    {
      e.printStackTrace();
      throw new ReportGeneratorException( "Can't create report: "
        + ReportType.PRACTICE_PLACES_REQUEST_REPORT.getTemplateName(), e );
    }
    return new Report( params.getOtputFilePath(), ReportType.PRACTICE_PLACES_REQUEST_REPORT );
  }

  private String[][] prepareData( PracticeType practiceType )
  {
    List<PracticePlace> placesList = PracticeService.getPracticePlaceList( practiceType );
    String[][] data = new String[placesList.size()][];
    for( int i = 0; i < placesList.size(); i++ )
    {
      PracticePlace place = placesList.get( i );
      data[i] = new String[5];
      data[i][0] = "";
      data[i][1] = CompaniesService.getCompany( place.getCompanyId() ).getFullNameWithAddressAndPostCode();
      data[i][2] = new Integer( place.getMaxStudentsNumber() ).toString();
      data[i][3] = "";
      data[i][4] = "";
    }
    return data;
  }
}
