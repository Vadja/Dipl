package org.iit.dr.documents.word.write.distribution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.iit.dr.data_model.DistributionPlace;
import org.iit.dr.data_model.comparators.StudentsByFioComparator;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.data_model.unit.Course;
import org.iit.dr.documents.common.generator.IReportGenerator;
import org.iit.dr.documents.common.generator.ReportParams;
import org.iit.dr.documents.common.generator.exceptions.ReportGeneratorException;
import org.iit.dr.documents.common.report.Report;
import org.iit.dr.documents.common.report.ReportType;
import org.iit.dr.documents.word.write.common.WordDocBuilder;
import org.iit.dr.services.CompaniesService;
import org.iit.dr.services.DistributionService;
import org.iit.dr.services.OrganizationUnitService;
import org.iit.dr.services.StudentsService;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class DistributionReportGenerator implements IReportGenerator
{
  private WordDocBuilder builder;

  private List<Student> paidStuds;

  private int distrStudsNumber;

  private int reqSumm;

  private static final String OU_ID = OrganizationUnitService.getCourseId( Course.FIFTH );

  public Report generateReport( ReportParams params )
  {
    try
    {
      int budget = StudentsService.getStudentsByOuIdAndStudyForm( OU_ID, true ).size();
      int paid = StudentsService.getStudentsByOuIdAndStudyForm( OU_ID, false ).size();

      builder = new WordDocBuilder( ReportType.DISTRIBUTION_RESULT_REPORT.getTemplatePath() );
      builder.appendTableData( "#companies", getCompaniesTableData() );
      builder.appendTableData( "#students", getStudentsTableData() );
      builder.insertList( "#paid_studs", getPaidStuds() );
      builder.insertValue( "fail_req", String.valueOf( reqSumm - distrStudsNumber ) );
      builder.insertValue( "stud_number", String.valueOf( budget + paid ) );
      builder.insertValue( "budget_number", String.valueOf( budget ) );
      builder.insertValue( "paid_number", String.valueOf( paid ) );

      builder.saveDoc( params.getOtputFilePath() );
    }
    catch( Exception e )
    {
      e.printStackTrace();
      throw new ReportGeneratorException( "Can't create report: "
        + ReportType.DISTRIBUTION_RESULT_REPORT.getTemplateName(), e );
    }
    return new Report( params.getOtputFilePath(), ReportType.DISTRIBUTION_RESULT_REPORT );
  }

  private String[][] getCompaniesTableData()
  {
    List<DistributionPlace> placesList = DistributionService.getDistributionPlacesList();
    String[][] result = new String[placesList.size() + 1][];
    reqSumm = 0;
    for( int i = 0; i < placesList.size(); i++ )
    {
      result[i] = new String[3];
      result[i][0] = "" + ( i + 1 ) + ".";
      result[i][1] = CompaniesService.getCompany( placesList.get( i ).getCompanyId() ).getFullNameWithCity();
      int reqNumber = placesList.get( i ).getRequestedStudentsIdList().size();
      result[i][2] = String.valueOf( reqNumber );
      reqSumm += placesList.get( i ).getRequestedStudentsIdList().size();
    }
    result[placesList.size()] = new String[3];
    result[placesList.size()][0] = "";
    result[placesList.size()][1] = "ИТОГО:";
    result[placesList.size()][2] = String.valueOf( reqSumm );
    return result;
  }

  private String[][] getStudentsTableData()
  {
    paidStuds = new ArrayList<Student>();
    List<Student> studsList = DistributionService.getDistributedStudents();
    Collections.sort( studsList, new StudentsByFioComparator() );
    String[][] result = new String[studsList.size()][];
    distrStudsNumber = studsList.size();
    for( int i = 0; i < studsList.size(); i++ )
    {
      result[i] = new String[5];
      Student stud = studsList.get( i );
      result[i][0] = "" + ( i + 1 ) + ".";
      result[i][1] = stud.getFullName();
      result[i][2] = OrganizationUnitService.getOrganizationUnit( stud.getGroup() ).getName();
      result[i][3] = DistributionService.getCompanyByDistributedStudent( stud.getId() ).getFullNameWithCity();
      if( stud.getBudget() )
      {
        result[i][4] = "";
      }
      else
      {
        result[i][4] = "Платная форма";
        paidStuds.add( stud );
      }
    }
    return result;
  }

  private String[] getPaidStuds()
  {
    String[] result = new String[paidStuds.size()];
    for( int i = 0; i < paidStuds.size(); i++ )
    {
      Student stud = paidStuds.get( i );
      result[i] =
        stud.getFullName() + " - "
          + DistributionService.getCompanyByDistributedStudent( stud.getId() ).getFullNameWithCity();
    }
    return result;
  }
}
