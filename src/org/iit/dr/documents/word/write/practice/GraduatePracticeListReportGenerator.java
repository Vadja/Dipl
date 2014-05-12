package org.iit.dr.documents.word.write.practice;

import java.util.Date;

import org.iit.dr.data_model.PracticePlace;
import org.iit.dr.data_model.PracticeType;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.documents.common.generator.IReportGenerator;
import org.iit.dr.documents.common.generator.ReportParams;
import org.iit.dr.documents.common.generator.exceptions.ReportGeneratorException;
import org.iit.dr.documents.common.report.Report;
import org.iit.dr.documents.common.report.ReportType;
import org.iit.dr.services.CompaniesService;
import org.iit.dr.services.PracticeService;
import org.iit.dr.utils.DateUtils;

import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class GraduatePracticeListReportGenerator extends BasePracticeListReportGenerator implements IReportGenerator
{
  public static final String FLAG = "#%$";

  public Report generateReport( ReportParams params )
  {
    try
    {
      Document doc = new Document( ReportType.GRADUATE_PRACTICE_LIST_REPORT.getTemplatePath() );
      doc.getMailMerge().execute(new String[] {"YEAR"},new String[] {DateUtils.dateToYear(new Date())} );
      DocumentBuilder builder = new DocumentBuilder( doc );
      PracticePlaceWriter placesWriter = new PracticePlaceWriter( builder, FLAG );
      placesWriter.startWriter();
      for( PracticePlace place : PracticeService.getPracticePlacesWithStudents( PracticeType.GRADUATING ) )
      {
        placesWriter.writeCompanyName( CompaniesService.getCompany( place.getCompanyId() ).getFullNameWithCity() );
        for( String consId : place.getConsultantsId() )
        {
          for( Student stud : getStudents( place, consId ) )
          {
        	  if(stud!=null){
        		  System.out.print(getStudentGroup( stud ));
        		  placesWriter.writeStudent( stud.getFullName(), getStudentGroup( stud ) );
        	  }
          }
          placesWriter.writeConsultant( getConsultantString( consId ) );
        }
      }
      builder.getDocument().save( params.getOtputFilePath() );
    }
    catch( Exception e )
    {
      e.printStackTrace();
      throw new ReportGeneratorException( "Can't create report: "
        + ReportType.GRADUATE_PRACTICE_LIST_REPORT.getTemplateName(), e );
    }
    return new Report( params.getOtputFilePath(), ReportType.GRADUATE_PRACTICE_LIST_REPORT );
  }
}
