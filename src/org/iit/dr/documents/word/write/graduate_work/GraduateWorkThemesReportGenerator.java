package org.iit.dr.documents.word.write.graduate_work;

import java.util.List;

import org.iit.dr.data_model.GraduateWork;
import org.iit.dr.data_model.role.PartTimeStaffer;
import org.iit.dr.data_model.role.Staffer;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.documents.common.generator.IReportGenerator;
import org.iit.dr.documents.common.generator.ReportParams;
import org.iit.dr.documents.common.generator.exceptions.ReportGeneratorException;
import org.iit.dr.documents.common.report.Report;
import org.iit.dr.documents.common.report.ReportType;
import org.iit.dr.documents.word.write.common.WordDocBuilder;
import org.iit.dr.services.GraduateWorkService;
import org.iit.dr.services.StudentsService;
import org.iit.dr.services.TeachersService;
//import org.iit.dr.utils.RemoveTrialString;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class GraduateWorkThemesReportGenerator implements IReportGenerator
{
  private WordDocBuilder builder;

  public Report generateReport( ReportParams params )
  {
    try
    {
      builder = new WordDocBuilder( ReportType.GRADUATE_WORKS_THEMS_REPORT.getTemplatePath() );
      builder.appendTableData( "$themes$", getThemesTable() );
      builder.saveDoc( params.getOtputFilePath() );
      
//      RemoveTrialString.removeStringInDocument(params.getOtputFilePath());
    }
    catch( Exception e )
    {
      e.printStackTrace();
      throw new ReportGeneratorException( "Can't create report: "
        + ReportType.GRADUATE_WORKS_THEMS_REPORT.getTemplateName(), e );
    }
    return new Report( params.getOtputFilePath(), ReportType.GRADUATE_WORKS_THEMS_REPORT );
  }

  private String[][] getThemesTable()
  {
    List<GraduateWork> worksList = GraduateWorkService.getGraduateWorkList();
    String[][] result = new String[worksList.size()][];
    for( int i = 0; i < worksList.size(); i++ )
    {
      GraduateWork work = worksList.get( i );
      result[i] = new String[5];
      result[i][0] = "" + ( i + 1 ) + ".";
      result[i][1] = StudentsService.getStudent( work.getStudentId() ).getFullName();
      result[i][2] = work.getTitle();
      result[i][3] = getManager( work );
      result[i][4] = getConsultant( work );
    }

    return result;
  }

  private String getManager( GraduateWork work )
  {
    Teacher teacher = TeachersService.getTeacher( work.getManagerId() );
    if( teacher instanceof PartTimeStaffer )
    {
      PartTimeStaffer staffer = ( PartTimeStaffer ) teacher;
      return staffer.getFioAndWorkPlace();
    }
    return ( teacher == null ) ? "" : teacher.getFullName();
  }

  private String getConsultant( GraduateWork work )
  {
    Teacher teacher = TeachersService.getTeacher( work.getConsultantIdBySpeciality() );
    if( teacher instanceof Staffer )
    {
      Staffer staffer = ( Staffer ) teacher;
      return staffer.getFioAndPosition();
    }
    return ( teacher == null ) ? "" : teacher.getFullName();

  }
}
