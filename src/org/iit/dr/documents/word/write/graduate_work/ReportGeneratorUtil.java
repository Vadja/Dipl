package org.iit.dr.documents.word.write.graduate_work;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.iit.dr.data_model.DefenceGraduateWork;
import org.iit.dr.data_model.GraduateWork;
import org.iit.dr.data_model.role.ExternalSecondJobStaffer;
import org.iit.dr.data_model.role.InternalSecondJobStaffer;
import org.iit.dr.data_model.role.PartTimeStaffer;
import org.iit.dr.data_model.role.Person;
import org.iit.dr.data_model.role.Staffer;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.data_model.unit.OrganizationUnit;
import org.iit.dr.services.GraduateWorkService;
import org.iit.dr.services.OrganizationUnitService;
import org.iit.dr.services.StudentsService;
import org.iit.dr.services.TeachersService;
import org.iit.dr.utils.DateUtils;
import org.iit.dr.utils.FileUtils;
import org.iit.dr.utils.LabelUtils;
import org.iit.dr.utils.ProtocolData;
//import org.iit.dr.utils.RemoveTrialString;

import com.aspose.words.Body;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.LineStyle;
import com.aspose.words.Node;
import com.aspose.words.Paragraph;
import com.aspose.words.ParagraphAlignment;
import com.aspose.words.Section;

/**
 * ReportGeneratorUtil.
 * <p/>
 * $Id: ReportGeneratorUtil.java, v 1.0 09.06.2010 15:56:04 ykarpovich Exp $ Created on 09.06.2010 15:56:04
 */
public class ReportGeneratorUtil
{

  public static void generateTable( DocumentBuilder builder, String[] headers, int[] width, String[][] data )
    throws Exception
  {
    Section section = builder.getDocument().getSections().get( 0 );
    Body body = section.getBody();
    for( int i = 0; i < body.getChildNodes().getCount(); i++ )
    {
      Node node = body.getChildNodes().get( i );
      if( node instanceof Paragraph )
      {
        Paragraph paragraph = ( Paragraph ) node;
        if( paragraph.getText().startsWith( "#--odp" ) )
        {
          builder.moveTo( body.getChildNodes().get( i - 1 ) );
          node.remove();
          builder.startTable();
          builder.getParagraphFormat().setFirstLineIndent( 0 );
          builder.getParagraphFormat().setAlignment( ParagraphAlignment.CENTER );
          builder.getRowFormat().getBorders().setLineStyle( LineStyle.SINGLE );
          builder.getRowFormat().getBorders().setColor( Color.BLACK );
          appendRowToTable( builder, headers, width );
          builder.getParagraphFormat().setAlignment( ParagraphAlignment.LEFT );
          for( String[] rowData : data )
          {
            appendRowToTable( builder, rowData, width );
          }
          builder.endTable();
        }
      }
    }
  }

  public static void appendRowToTable( DocumentBuilder builder, String[] headers, int[] width ) throws Exception
  {
    for( int k = 0; k < headers.length; k++ )
    {
      builder.insertCell();
      // builder.getCellFormat().setWidth( width[k] );
      builder.write( headers[k] );
    }
    builder.endRow();
  }

  public static void generateProtocol( String defenceFraduateWorkId )
  {

    String cons_0 = "";
    String cons_1 = "";
    String cons_2 = "";
    String cons_3 = "";
    String cons_4 = "";

    DefenceGraduateWork defenceGraduateWork = GraduateWorkService.getDefenceGraduateWork( defenceFraduateWorkId );

    if( defenceGraduateWork != null )
    {

      GraduateWork graduateWork = GraduateWorkService.getGraduateWork( defenceGraduateWork.getGraduateWorkId() );

      if( graduateWork != null )
      {

        Student student = StudentsService.getStudent( graduateWork.getStudentId() );
        String faculty = "";
        if( student != null )
        {
          OrganizationUnit ou =
            OrganizationUnitService.getRootOrganizationUnit( OrganizationUnitService.getOrganizationUnit( student
              .getGroup() ) );
          if( ou != null && ou.getName() != null )
          {
            faculty = ou.getName();
          }
        }
        Teacher manger = TeachersService.getTeacher( graduateWork.getManagerId() );
        Teacher conSpec = TeachersService.getTeacher( graduateWork.getConsultantIdBySpeciality() );
        Teacher conEc0 = TeachersService.getTeacher( graduateWork.getConsultantIdByEconomics() );
        Teacher conOhr = TeachersService.getTeacher( graduateWork.getConsultantIdByProtectionOfLabor() );
        Teacher conNormcontr = TeachersService.getTeacher( graduateWork.getConsultantIdByNormalInspection() );
        Teacher rev = TeachersService.getTeacher( graduateWork.getReviewerId() );

        // ----

        Teacher pred = TeachersService.getTeacher( defenceGraduateWork.getPresideId() );
        Teacher secr = TeachersService.getTeacher( defenceGraduateWork.getCommissionerSecrId() );
        Teacher cons0 = TeachersService.getTeacher( defenceGraduateWork.getCommissioner0Id() );
        Teacher cons1 = TeachersService.getTeacher( defenceGraduateWork.getCommissioner1Id() );
        Teacher cons2 = TeachersService.getTeacher( defenceGraduateWork.getCommissioner2Id() );
        Teacher cons3 = TeachersService.getTeacher( defenceGraduateWork.getCommissioner3Id() );
        Teacher cons4 = TeachersService.getTeacher( defenceGraduateWork.getCommissioner4Id() );
        Teacher cons5 = TeachersService.getTeacher( defenceGraduateWork.getCommissioner5Id() );
        Teacher cons6 = TeachersService.getTeacher( defenceGraduateWork.getCommissioner6Id() );
        Teacher cons7 = TeachersService.getTeacher( defenceGraduateWork.getCommissioner7Id() );
        Teacher cons8 = TeachersService.getTeacher( defenceGraduateWork.getCommissioner8Id() );
        Teacher cons9 = TeachersService.getTeacher( defenceGraduateWork.getCommissioner9Id() );

        if( cons0 != null )
        {
          cons_0 += getShortName( cons0 );
        }
        if( cons1 != null )
        {
          cons_0 += ", " + getShortName( cons1 );
        }
        if( cons2 != null )
        {
          cons_1 += getShortName( cons2 );
        }
        if( cons3 != null )
        {
          cons_1 += ", " + getShortName( cons3 );
        }
        if( cons4 != null )
        {
          cons_2 += getShortName( cons4 );
        }
        if( cons5 != null )
        {
          cons_2 += ", " + getShortName( cons5 );
        }
        if( cons6 != null )
        {
          cons_3 += getShortName( cons6 );
        }
        if( cons7 != null )
        {
          cons_3 += ", " + getShortName( cons7 );
        }
        if( cons8 != null )
        {
          cons_4 += getShortName( cons8 );
        }
        if( cons9 != null )
        {
          cons_4 += ", " + getShortName( cons9 );
        }

        Teacher qAuthor1 =
          TeachersService.getTeacher( defenceGraduateWork.getGraduateWorkInfo().getQuestion1AuthorId() );
        Teacher qAuthor2 =
          TeachersService.getTeacher( defenceGraduateWork.getGraduateWorkInfo().getQuestion2AuthorId() );
        Teacher qAuthor3 =
          TeachersService.getTeacher( defenceGraduateWork.getGraduateWorkInfo().getQuestion3AuthorId() );
        Teacher qAuthor4 =
          TeachersService.getTeacher( defenceGraduateWork.getGraduateWorkInfo().getQuestion4AuthorId() );
        Teacher qAuthor5 =
          TeachersService.getTeacher( defenceGraduateWork.getGraduateWorkInfo().getQuestion5AuthorId() );
        Teacher qAuthor6 =
          TeachersService.getTeacher( defenceGraduateWork.getGraduateWorkInfo().getQuestion6AuthorId() );

        String startTime = defenceGraduateWork.getStartTime() != null ? defenceGraduateWork.getStartTime() : " : ";
        String endTime = defenceGraduateWork.getEndTime() != null ? defenceGraduateWork.getEndTime() : " : ";
        String[] startTimeParts = startTime.split( ":" );
        String[] endTimeParts = endTime.split( ":" );

        String traning = "";

        if( defenceGraduateWork.getGraduateWorkInfo().getMarkCountDistinction() != null )
        {

          traning += "отличных - " + defenceGraduateWork.getGraduateWorkInfo().getMarkCountDistinction();
        }
        if( defenceGraduateWork.getGraduateWorkInfo().getMarkCountGood() != null )
        {

          if( traning.length() > 0 )
          {
            traning += ", ";
          }
          traning += "хороших - " + defenceGraduateWork.getGraduateWorkInfo().getMarkCountGood();
        }
        if( defenceGraduateWork.getGraduateWorkInfo().getMarkCountSatisfactory() != null )
        {

          if( traning.length() > 0 )
          {
            traning += ", ";
          }
          traning += "удовлетворительных - " + defenceGraduateWork.getGraduateWorkInfo().getMarkCountSatisfactory();
        }

        String[] values =
          new String[] {
            defenceGraduateWork.getProtocolNumber() != null ? defenceGraduateWork.getProtocolNumber() : "",
            DateUtils.dateToDay( defenceGraduateWork.getActualDate() ),
            DateUtils.dateToMonth( defenceGraduateWork.getActualDate() ),
            DateUtils.dateToYear( defenceGraduateWork.getActualDate() ),
            startTimeParts[0],
            startTimeParts[1],
            endTimeParts[0],
            endTimeParts[1],
            getFullName( student ),
            graduateWork.getTitle(),
            getShortName( pred ),
            getShortName( secr ),
            getShortName( cons0 ),
            getShortName( cons1 ),
            getShortName( cons2 ),
            getShortName( cons3 ),
            getShortName( cons4 ),
            getShortName( cons5 ),
            getShortName( cons6 ),
            getShortName( cons7 ),
            getShortName( cons8 ),
            getShortName( cons9 ),
            getShortName( manger ) + ", ",
            getManagerData( manger ),
            getConsults( conSpec, conEc0, conOhr, conNormcontr ),
            defenceGraduateWork.getGraduateWorkInfo().getPageCount(),
            defenceGraduateWork.getGraduateWorkInfo().getGraphicCount(),
            LabelUtils.getIntegerAsStringWithBr( defenceGraduateWork.getGraduateWorkInfo().getReviewMark() ),
            getShortName( rev ) + ", " + getManagerData( rev ),
            defenceGraduateWork.getGraduateWorkInfo().getQuestion1(),
            defenceGraduateWork.getGraduateWorkInfo().getQuestion2(),
            defenceGraduateWork.getGraduateWorkInfo().getQuestion3(),
            defenceGraduateWork.getGraduateWorkInfo().getQuestion4(),
            defenceGraduateWork.getGraduateWorkInfo().getQuestion5(),
            defenceGraduateWork.getGraduateWorkInfo().getQuestion6(),
            getShortName( qAuthor1 ),
            getShortName( qAuthor2 ),
            getShortName( qAuthor3 ),
            getShortName( qAuthor4 ),
            getShortName( qAuthor5 ),
            getShortName( qAuthor6 ),
            defenceGraduateWork.getGraduateWorkInfo().getAnswer(),
            defenceGraduateWork.getGraduateWorkInfo().getTraining(),
            defenceGraduateWork.getGraduateWorkInfo().getOpinion(),
            LabelUtils.getIntegerAsStringWithBr( defenceGraduateWork.getGraduateWorkInfo().getMark() ),
            cons_0,
            cons_1,
            cons_2,
            cons_3,
            cons_4,
            defenceGraduateWork.getGraduateWorkInfo().getTimeCount() != null ? defenceGraduateWork
              .getGraduateWorkInfo().getTimeCount() : "",
            traning,
            defenceGraduateWork.getGraduateWorkInfo().getResultCommon() != null ? defenceGraduateWork
              .getGraduateWorkInfo().getResultCommon() : "",
            defenceGraduateWork.getGraduateWorkInfo().getOutputMark() != null ? defenceGraduateWork
              .getGraduateWorkInfo().getOutputMark() : "",
            defenceGraduateWork.getGraduateWorkInfo().getManagerResult() != null ? defenceGraduateWork
              .getGraduateWorkInfo().getManagerResult() : "",
            getShortName( student ),
            isTrue( defenceGraduateWork.getGraduateWorkInfo().isCompPresent() ) ? ProtocolData.getInstance()
              .getProperty( ProtocolData.COMP_PRESENT ) : "",
            isTrue( defenceGraduateWork.getGraduateWorkInfo().isDistribution() ) ? ProtocolData.getInstance()
              .getProperty( ProtocolData.DISTRIBUTION ) : "",
            isTrue( defenceGraduateWork.getGraduateWorkInfo().isBestWork() ) ? ProtocolData.getInstance().getProperty(
              ProtocolData.BEST_WORK ) : "",
            isTrue( defenceGraduateWork.getGraduateWorkInfo().isMagistracy() ) ? ProtocolData.getInstance()
              .getProperty( ProtocolData.MAGISTRACY ) : "",
            isTrue( defenceGraduateWork.getGraduateWorkInfo().isPrintedPublication() ) ? ProtocolData.getInstance()
              .getProperty( ProtocolData.PRINTED_PUBLICATION ) : "", faculty};

        DPProtokolDocumentGenerator dpProtokolDocumentGenerator = new DPProtokolDocumentGenerator();

        String filePath =
          FileUtils.getDocumentPath( defenceGraduateWork.getActualDate(), "Протокол [" + getShortName( student )
            + "] .doc" );
        dpProtokolDocumentGenerator.generate( filePath, values );
//        RemoveTrialString.removeStringInDocument(filePath);
      }
    }

  }

  private static boolean isTrue( Boolean value )
  {

    return value != null && value;
  }

  private static String getConsults( Teacher... teachers )
  {

    String separator = ", ";

    StringBuilder stringBuilder = new StringBuilder();

    Set<Teacher> hashSet = new HashSet<Teacher>( Arrays.asList( teachers ) );

    for( Teacher teacher : hashSet )
    {

      String teacherTitle = getShortName( teacher );

      if( teacherTitle != null && teacherTitle.length() > 0 )
      {

        stringBuilder.append( teacherTitle ).append( separator );
      }
    }

    stringBuilder.delete( stringBuilder.length() - separator.length(), stringBuilder.length() );

    return stringBuilder.toString();
  }

  private static String getManagerData( Teacher teacher )
  {

    if( teacher != null )
    {

      if( teacher instanceof Staffer )
      {

        Staffer staffer = ( Staffer ) teacher;

        return getStringRusName( staffer ) + " кафедры ИИТ";

      }
      else if( teacher instanceof PartTimeStaffer )
      {

        PartTimeStaffer partTimeStaffer = ( PartTimeStaffer ) teacher;

        String data =
          getStringNotNull( partTimeStaffer.getOriginalPosition() ) + " "
            + getStringNotNull( partTimeStaffer.getWorkPlace() );

        String degree =
          partTimeStaffer.getDegree() != null ? partTimeStaffer.getDegree().getRusNameShort().toLowerCase() : "";

        if( data.length() > 1 && degree.length() > 0 )
        {

          data += ", ";
        }
        data += degree;
        String rank = partTimeStaffer.getRank() != null ? partTimeStaffer.getRank().getRusName().toLowerCase() : "";

        if( data.length() > 1 && rank.length() > 0 )
        {

          data += ", ";

        }
        data += rank;

        return data;

      }
      else if( teacher instanceof ExternalSecondJobStaffer )
      {

        ExternalSecondJobStaffer externalSecondJobStaffer = ( ExternalSecondJobStaffer ) teacher;

        return getStringNotNull( externalSecondJobStaffer.getOriginalPosition() );

      }
      else if( teacher instanceof InternalSecondJobStaffer )
      {

        InternalSecondJobStaffer internalSecondJobStaffer = ( InternalSecondJobStaffer ) teacher;

        return getStringRusName( internalSecondJobStaffer );
      }

    }

    return "";
  }

  private static String getShortName( Person person )
  {

    return person != null ? person.getShortName() : "";
  }

  private static String getFullName( Person person )
  {

    return person != null ? person.getFullName() : "";
  }

  private static String getStringNotNull( String value )
  {
    return value != null ? value : "";
  }

  private static String getStringRusName( InternalSecondJobStaffer internalSecondJobStaffer )
  {

    return internalSecondJobStaffer != null && internalSecondJobStaffer.getOriginalPosition() != null
      ? internalSecondJobStaffer.getOriginalPosition().getRusName() : "";
  }

  private static String getStringRusName( Staffer staffer )
  {

    return staffer != null && staffer.getPosition() != null && staffer.getPosition().getRusName() != null ? staffer
      .getPosition().getRusName().toLowerCase() : "";
  }
}
