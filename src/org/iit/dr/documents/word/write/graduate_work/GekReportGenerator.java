package org.iit.dr.documents.word.write.graduate_work;

import java.util.List;

import org.iit.dr.data_model.DefenceGraduateWork;
import org.iit.dr.data_model.GraduateWork;
import org.iit.dr.data_model.GraduateWorkInfo;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.data_model.unit.Faculty;
import org.iit.dr.data_model.unit.OrganizationUnit;
import org.iit.dr.documents.common.generator.IReportGenerator;
import org.iit.dr.documents.common.generator.ReportParams;
import org.iit.dr.documents.common.generator.exceptions.ReportGeneratorException;
import org.iit.dr.documents.common.report.Report;
import org.iit.dr.documents.common.report.ReportType;
import org.iit.dr.documents.word.write.common.WordDocBuilder;
import org.iit.dr.services.GraduateWorkService;
import org.iit.dr.services.OrganizationUnitService;
import org.iit.dr.services.StudentsService;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class GekReportGenerator implements IReportGenerator
{
  private WordDocBuilder builder;

  private int studs_number, studs_number_do, studs_number_day;

  private int m9_10, m6_8, m4_5, m1_3, m_sum, p9_10, p6_8, p4_5, p1_3, p_sum;

  private int m9_10d, m6_8d, m4_5d, m1_3d, m_sumd, p9_10d, p6_8d, p4_5d, p1_3d, p_sumd;

  private int m9_10do, m6_8do, m4_5do, m1_3do, m_sumdo, p9_10do, p6_8do, p4_5do, p1_3do, p_sumdo;

  private float m_avg, m_avgd, m_avgdo;

  private int n1, n2, n3, n4, n5, p1, p2, p3, p4, p5;

  private int n1d, n2d, n3d, n4d, n5d, p1d, p2d, p3d, p4d, p5d;

  private int n1do, n2do, n3do, n4do, n5do, p1do, p2do, p3do, p4do, p5do;

  private int mark_summ, mark_summdo, mark_summd;

  public Report generateReport( ReportParams params )
  {
    try
    {
      calculateStatistics();
      if( studs_number_do != 0 )
      {
//        builder = new WordDocBuilder( ReportType.GEK_REPORT_DO.getTemplatePath() );
        builder = new WordDocBuilder( ReportType.GEK_REPORT.getTemplatePath() );
      }
      else
      {
        builder = new WordDocBuilder( ReportType.GEK_REPORT.getTemplatePath() );
      }
      builder.insertValue( "studs_number", String.valueOf( studs_number ) );
      builder.insertValue( "studs_number_do", String.valueOf( studs_number_do ) );
      builder.insertValue( "studs_number_day", String.valueOf( studs_number_day ) );
      insertMarkStatistics();
      insertDiplomWorksInfo();
      builder.insertList( "red_diplom_students", getHonourDiplomStudents() );
      builder.appendTableData( "best_works", getBestWorks() );
      builder.insertList( "magistr_students", getMagistracyStudents() );

      builder.saveDoc( params.getOtputFilePath() );
    }
    catch( Exception e )
    {
      e.printStackTrace();
      throw new ReportGeneratorException( "Can't create report: " + ReportType.GEK_REPORT.getTemplateName(), e );
    }
    return new Report( params.getOtputFilePath(), ReportType.GEK_REPORT );
  }

  private void calculateStatistics()
  {
    clearData();
    List<DefenceGraduateWork> worksList = GraduateWorkService.getDefenceGraduateWorkList();
    studs_number = worksList.size();
    studs_number_do = 0;
    studs_number_day = 0;
    for( DefenceGraduateWork work : worksList )
    {
      GraduateWorkInfo info = work.getGraduateWorkInfo();
      Student stud = StudentsService.getStudentByDefenceGraduateWorkId( work.getId() );
      OrganizationUnit ou = OrganizationUnitService.getOrganizationUnit( stud.getGroup() );
      if(stud.getLastName().equals("Бальцевич")||stud.getLastName().equals("Борисов")){
//    	  int asda=7;
      }
      Faculty deparatament = ou.getDepartamentType();
      if( deparatament == null )
      {
        deparatament = OrganizationUnitService.getRootOrganizationUnit( ou ).getDepartamentType();
      }

      if( deparatament == null ){
    	  studs_number_day++;
      }else switch( deparatament ){
        case DAY:
          studs_number_day++;
          break;
        case DO:
          studs_number_do++;
          break;
      }
      
      if( info == null )
      {
        continue;
      }
      if( info.getMark() == null )
      {
        continue;
      }
      int mark = info.getMark();
      if( mark >= 9 )
      {
        m9_10++;
        n5++;
        if( deparatament == null ){
        	 m9_10d++;
             n5d++;
        }else switch( deparatament )
        {
          case DAY:
            m9_10d++;
            n5d++;
            break;
          case DO:
            m9_10do++;
            n5do++;
            break;
        }
      }
      else if( mark >= 6 )
      {
        m6_8++;
        n5++;
        if( deparatament == null ){
            m6_8d++;
            n5d++;
       }else switch( deparatament )
        {
          case DAY:
            m6_8d++;
            n5d++;
            break;
          case DO:
            m6_8do++;
            n5do++;
            break;
        }
      }
      else if( mark >= 4 )
      {
        m4_5++;
        if( deparatament == null ){
            m4_5d++;
       }else switch( deparatament )
        {
          case DAY:
            m4_5d++;
            break;
          case DO:
            m4_5do++;
            break;
        }
      }
      else
      {
        m1_3++;
        if( deparatament == null ){
            m1_3d++;
       }else switch( deparatament )
        {
          case DAY:
            m1_3d++;
            break;
          case DO:
            m1_3do++;
            break;
        }
      }

      mark_summ += mark;
      if( deparatament == null ){
          mark_summd += mark;
     }else switch( deparatament )
      {
        case DAY:
          mark_summd += mark;
          break;
        case DO:
          mark_summdo += mark;
          break;
      }

      if( work.getGraduateWorkInfo().isCompPresent() )
      {
    	  if( deparatament == null ){
              n3d++;
         }else switch( deparatament )
        {
          case DAY:
            n3d++;
            break;
          case DO:
            n3do++;
            break;
        }
        n3++;
      }

      if( work.getGraduateWorkInfo().isDistribution() )
      {
    	  if( deparatament == null ){
              n4d++;
         }else switch( deparatament )
        {
          case DAY:
            n4d++;
            break;
          case DO:
            n4do++;
            break;
        }
        n4++;

      }
    }

    p9_10 = getPercent( m9_10, studs_number );
    p6_8 = getPercent( m6_8, studs_number );
    p4_5 = getPercent( m4_5, studs_number );
    p1_3 = getPercent( m1_3, studs_number );
    p9_10d = getPercent( m9_10d, studs_number_day );
    p6_8d = getPercent( m6_8d, studs_number_day );
    p4_5d = getPercent( m4_5d, studs_number_day );
    p1_3d = getPercent( m1_3d, studs_number_day );
    p9_10do = getPercent( m9_10do, studs_number_do );
    p6_8do = getPercent( m6_8do, studs_number_do );
    p4_5do = getPercent( m4_5do, studs_number_do );
    p1_3do = getPercent( m1_3do, studs_number_do );
    m_sum = studs_number;
    p_sum = 100;
    m_sumdo = studs_number_do;
    p_sumdo = 100;
    m_sumd = studs_number_day;
    p_sumd = 100;
    m_avg = ( ( float ) mark_summ ) / ( ( float ) studs_number );
    m_avgdo = ( ( float ) mark_summdo ) / ( ( float ) studs_number_do );
    m_avgd = ( ( float ) mark_summd ) / ( ( float ) studs_number_day );

    n1 = studs_number;
    p1 = 100;
    n2 = studs_number;
    p2 = 100;
    p3 = getPercent( n3, studs_number );
    p4 = getPercent( n4, studs_number );
    p5 = getPercent( n5, studs_number );
    n1d = studs_number_day;
    p1d = 100;
    n2d = studs_number_day;
    p2d = 100;
    p3d = getPercent( n3d, studs_number_day );
    p4d = getPercent( n4d, studs_number_day );
    p5d = getPercent( n5d, studs_number_day );
    n1do = studs_number_do;
    p1do = 100;
    n2do = studs_number_do;
    p2do = 100;
    p3do = getPercent( n3do, studs_number_do );
    p4do = getPercent( n4do, studs_number_do );
    p5do = getPercent( n5do, studs_number_do );

  }

  private void insertMarkStatistics() throws Exception
  {
    builder.insertValue( "m9_10", String.valueOf( m9_10 ) );
    builder.insertValue( "m6_8", String.valueOf( m6_8 ) );
    builder.insertValue( "m4_5", String.valueOf( m4_5 ) );
    builder.insertValue( "m1_3", String.valueOf( m1_3 ) );
    builder.insertValue( "m9_10do", String.valueOf( m9_10do ) );
    builder.insertValue( "m6_8do", String.valueOf( m6_8do ) );
    builder.insertValue( "m4_5do", String.valueOf( m4_5do ) );
    builder.insertValue( "m1_3do", String.valueOf( m1_3do ) );
    builder.insertValue( "m9_10d", String.valueOf( m9_10d ) );
    builder.insertValue( "m6_8d", String.valueOf( m6_8d ) );
    builder.insertValue( "m4_5d", String.valueOf( m4_5d ) );
    builder.insertValue( "m1_3d", String.valueOf( m1_3d ) );
    builder.insertValue( "m_sumd", String.valueOf( m_sumd ) );
    builder.insertValue( "p9_10d", String.valueOf( p9_10d ) );
    builder.insertValue( "p6_8d", String.valueOf( p6_8d ) );
    builder.insertValue( "p4_5d", String.valueOf( p4_5d ) );
    builder.insertValue( "p1_3d", String.valueOf( p1_3d ) );
    builder.insertValue( "p_sumd", String.valueOf( p_sumd ) );
    builder.insertValue( "m_avgd", String.valueOf( m_avgd ) );
    builder.insertValue( "m_sumdo", String.valueOf( m_sumdo ) );
    builder.insertValue( "p9_10do", String.valueOf( p9_10do ) );
    builder.insertValue( "p6_8do", String.valueOf( p6_8do ) );
    builder.insertValue( "p4_5do", String.valueOf( p4_5do ) );
    builder.insertValue( "p1_3do", String.valueOf( p1_3do ) );
    builder.insertValue( "p_sumdo", String.valueOf( p_sumdo ) );
    builder.insertValue( "m_avgdo", String.valueOf( m_avgdo ) );
    builder.insertValue( "m_sum", String.valueOf( m_sum ) );
    builder.insertValue( "p9_10", String.valueOf( p9_10 ) );
    builder.insertValue( "p6_8", String.valueOf( p6_8 ) );
    builder.insertValue( "p4_5", String.valueOf( p4_5 ) );
    builder.insertValue( "p1_3", String.valueOf( p1_3 ) );
    builder.insertValue( "p_sum", String.valueOf( p_sum ) );
    builder.insertValue( "m_avg", String.valueOf( m_avg ) );
  }

  private void insertDiplomWorksInfo() throws Exception
  {
    builder.insertValue( "n1", String.valueOf( n1 ) );
    builder.insertValue( "n2", String.valueOf( n2 ) );
    builder.insertValue( "n3", String.valueOf( n3 ) );
    builder.insertValue( "n4", String.valueOf( n4 ) );
    builder.insertValue( "n5", String.valueOf( n5 ) );
    builder.insertValue( "p1", String.valueOf( p1 ) );
    builder.insertValue( "p2", String.valueOf( p2 ) );
    builder.insertValue( "p3", String.valueOf( p3 ) );
    builder.insertValue( "p4", String.valueOf( p4 ) );
    builder.insertValue( "p5", String.valueOf( p5 ) );
    builder.insertValue( "n1d", String.valueOf( n1d ) );
    builder.insertValue( "n2d", String.valueOf( n2d ) );
    builder.insertValue( "n3d", String.valueOf( n3d ) );
    builder.insertValue( "n4d", String.valueOf( n4d ) );
    builder.insertValue( "n5d", String.valueOf( n5d ) );
    builder.insertValue( "p1d", String.valueOf( p1d ) );
    builder.insertValue( "p2d", String.valueOf( p2d ) );
    builder.insertValue( "p3d", String.valueOf( p3d ) );
    builder.insertValue( "p4d", String.valueOf( p4d ) );
    builder.insertValue( "p5d", String.valueOf( p5d ) );
    builder.insertValue( "n1do", String.valueOf( n1do ) );
    builder.insertValue( "n2do", String.valueOf( n2do ) );
    builder.insertValue( "n3do", String.valueOf( n3do ) );
    builder.insertValue( "n4do", String.valueOf( n4do ) );
    builder.insertValue( "n5do", String.valueOf( n5do ) );
    builder.insertValue( "p1do", String.valueOf( p1do ) );
    builder.insertValue( "p2do", String.valueOf( p2do ) );
    builder.insertValue( "p3do", String.valueOf( p3do ) );
    builder.insertValue( "p4do", String.valueOf( p4do ) );
    builder.insertValue( "p5do", String.valueOf( p5do ) );
  }

  private int getPercent( int n, int all )
  {
    return Math.round( ( ( float ) n * 100 ) / ( all ) );
  }

  private void clearData()
  {
    studs_number = 0;
    m9_10 = m6_8 = m4_5 = m1_3 = m_sum = p9_10 = p6_8 = p4_5 = p1_3 = p_sum = 0;
    m_avg = 0;
    n1 = n2 = n3 = n4 = n5 = p1 = p2 = p3 = p4 = p5 = 0;
    mark_summ = 0;
    m9_10do=m6_8do =m4_5do= m1_3do =m9_10d =m6_8d= m4_5d =m1_3d =m_sumd =p9_10d =p6_8d =p4_5d=0;
    p1_3d =p_sumd=0;
	m_avgd = m_sumdo= p9_10do= p6_8do= p4_5do=p1_3do = p_sumdo=0;
	m_avgdo =m_sum = p9_10=0;
	p6_8= p4_5=p1_3 = p_sum=0;
	m_avg=n1=n2=n3=n4=n5=p1=p2=p3=p4=p5=n1d=n2d=n3d=0;
	n4d=n5d=p1d=p2d=p3d=p4d=p5d=n1do=n2do=n3do=n4do=n5do=p1do=p2do=p3do=p4do=p5do=0;
	mark_summd = mark_summdo = mark_summ=0;
  }

  private String[][] getBestWorks()
  {
    List<GraduateWork> worksList = GraduateWorkService.getGraduateWorkListWithBestOrderByStudent();
    String[][] result = new String[worksList.size()][];
    for( int i = 0; i < worksList.size(); i++ )
    {
      result[i] = new String[3];
      result[i][0] = "" + ( i + 1 ) + ".";
      result[i][1] = StudentsService.getStudent( worksList.get( i ).getStudentId() ).getFullName();
      result[i][2] = worksList.get( i ).getTitle();
    }
    return result;
  }

  private String[] getMagistracyStudents()
  {
    List<Student> studsList = GraduateWorkService.getMagistracyStudents();
    return listToArray( studsList );
  }

  private String[] getHonourDiplomStudents()
  {
    List<Student> studsList = GraduateWorkService.getHounorDiplomStudents();
    return listToArray( studsList );
  }

  private String[] listToArray( List<Student> studsList )
  {
    String[] result = new String[studsList.size()];
    for( int i = 0; i < studsList.size(); i++ )
    {
      result[i] = "" + ( i + 1 ) + ". " + studsList.get( i ).getShortName();
    }
    return result;
  }

}
