package org.iit.dr.documents.word.write.graduate_work;

import java.awt.Color;
import java.util.List;

import org.iit.dr.data_model.DefenceGraduateWork;
import org.iit.dr.data_model.Gek;
import org.iit.dr.data_model.GekDay;
import org.iit.dr.data_model.GraduateWork;
import org.iit.dr.data_model.role.PartTimeStaffer;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.data_model.unit.Faculty;
import org.iit.dr.data_model.unit.OrganizationUnit;
import org.iit.dr.services.GekService;
import org.iit.dr.services.GraduateWorkService;
import org.iit.dr.services.OrganizationUnitService;
import org.iit.dr.services.StudentsService;
import org.iit.dr.services.TeachersService;
import org.iit.dr.utils.DateUtils;
import org.iit.dr.utils.FileUtils;
import org.iit.dr.utils.LabelUtils;
//import org.iit.dr.utils.RemoveTrialString;

import com.aspose.words.Body;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.LineStyle;
import com.aspose.words.Node;
import com.aspose.words.Paragraph;
import com.aspose.words.ParagraphAlignment;
import com.aspose.words.Section;

/**
 * GekDayDocumentGenerator.
 * 
 * @author Yuriy Karpovich
 */
public class GekDayDocumentGenerator
{

  String[] fieldNames = new String[] {"Preside", "GekDate", "Secr"};

  protected String templateName;

  public String getTemplateName()
  {
    return templateName;
  }

  public void setTemplateName( String templateName )
  {
    this.templateName = templateName;
  }

  public void generateDocumentResult( String gekDayId, String filepathOutput )
  {
    try
    {

      Gek gek = GekService.getGek();
      GekDay gekDay = null;
      for( GekDay day : gek.getGekDayList() )
      {
        if( day.getId().equals( gekDayId ) )
        {

          gekDay = day;
          break;
        }
      }

      String[] fieldValues =
        new String[] {getTeacherFull( gek.getPresideId() ), DateUtils.dateToString( gekDay.getDate() ),
          getTeacherShort( gek.getCommissionerSecrId() )};

      Document doc = new Document( FileUtils.getTemplate( templateName ) );
      doc.getMailMerge().execute( fieldNames, fieldValues );

      doc.save( FileUtils.getCommonDocumentPath( filepathOutput ) );

      doc = new Document( FileUtils.getCommonDocumentPath( filepathOutput ) );

      DocumentBuilder builder = new DocumentBuilder( doc );

      Section section = builder.getDocument().getSections().get( 0 );

      Body body = section.getBody();

      for( int i = 0; i < body.getChildNodes().getCount(); i++ )
      {

        Node node = body.getChildNodes().get( i );

        if( node instanceof Paragraph )
        {

          Paragraph paragraph = ( Paragraph ) node;

          if( paragraph.getText().startsWith( "#--Table" ) )
          {

            builder.moveTo( body.getChildNodes().get( i - 1 ) );
            node.remove();

            builder.startTable();

            builder.getParagraphFormat().setFirstLineIndent( 0 );
            builder.getParagraphFormat().setAlignment( ParagraphAlignment.CENTER );

            builder.getRowFormat().getBorders().setLineStyle( LineStyle.SINGLE );
            builder.getRowFormat().getBorders().setColor( Color.BLACK );

            builder.insertCell();
            builder.getCellFormat().setWidth( 20 );
            builder.write( "№\n№\nп/п" );
            builder.insertCell();
            builder.getCellFormat().setWidth( 250 );
            builder.write( "Фамилия, имя, отчество" );
            builder.insertCell();
            builder.getCellFormat().setWidth( 30 );
            builder.write( "Форма\nобучения" );
            builder.insertCell();
            builder.getCellFormat().setWidth( 30 );
            builder.write( "№\nпротокола\nзащиты" );
            builder.insertCell();
            builder.getCellFormat().setWidth( 50 );
            builder.write( "Оценка,\nполученная \nна защите" );
            builder.insertCell();
            builder.getCellFormat().setWidth( 50 );
            builder.write( "Выдать \nдиплом" );
            builder.endRow();
            builder.getParagraphFormat().setAlignment( ParagraphAlignment.LEFT );

            List<DefenceGraduateWork> defenceGraduateWorkList =
              GraduateWorkService.getDefenceGraduateWorkListOrderByProtocolNumber( gekDay.getDate() );

            for( int j = 0; j < defenceGraduateWorkList.size(); j++ )
            {

              DefenceGraduateWork defenceGraduateWork = defenceGraduateWorkList.get( j );

              Student student = StudentsService.getStudentByDefenceGraduateWorkId( defenceGraduateWork.getId() );

              builder.insertCell();
              builder.getCellFormat().setWidth( 20 );
              builder.write( "" + ( j + 1 ) );

              builder.insertCell();
              builder.getCellFormat().setWidth( 250 );
              if( student != null )
              {
                builder.write( student.getFullName() );
              }
              Faculty deparatament = null;
              if( student != null )
              {
                OrganizationUnit ou = OrganizationUnitService.getOrganizationUnit( student.getGroup() );
                deparatament = ou.getDepartamentType();
                if( deparatament == null )
                {
                  deparatament = OrganizationUnitService.getRootOrganizationUnit( ou ).getDepartamentType();
                }
              }
              builder.insertCell();
              builder.getCellFormat().setWidth( 30 );
              if( deparatament != null )
              {
                builder.write( deparatament.getShortName() );
              }
              else
              {
                builder.write( "дн" );
              }
              builder.insertCell();
              builder.getCellFormat().setWidth( 30 );
              builder.write( defenceGraduateWork.getProtocolNumber() != null ? defenceGraduateWork.getProtocolNumber()
                : "" );

              builder.insertCell();
              builder.getCellFormat().setWidth( 50 );
              builder
                .write( LabelUtils.getIntegerAsStringWithBr( defenceGraduateWork.getGraduateWorkInfo().getMark() ) );

              builder.insertCell();
              builder.getCellFormat().setWidth( 50 );
              builder.write( defenceGraduateWork.getGraduateWorkInfo().getOutputMark() != null ? defenceGraduateWork
                .getGraduateWorkInfo().getOutputMark() : "" );

              builder.endRow();
            }
            builder.endTable();
          }
        }
      }

      builder.getDocument().save( FileUtils.getCommonDocumentPath( filepathOutput ) );
//	    RemoveTrialString.removeStringInDocument(FileUtils.getCommonDocumentPath( filepathOutput ) );
    }
    catch( Exception e )
    {

      e.printStackTrace();
    }
  }

  public void generateDocument( String gekDayId, String filepathOutput )
  {
    try
    {

      Gek gek = GekService.getGek();
      GekDay gekDay = null;
      for( GekDay day : gek.getGekDayList() )
      {
        if( day.getId().equals( gekDayId ) )
        {

          gekDay = day;
          break;
        }
      }

      String[] fieldValues =
        new String[] {getTeacherFull( gek.getPresideId() ), DateUtils.dateToString( gekDay.getDate() ),
          getTeacherShort( gek.getCommissionerSecrId() )};

      Document doc = new Document( FileUtils.getTemplate( templateName ) );
      doc.getMailMerge().execute( fieldNames, fieldValues );

      doc.save( FileUtils.getCommonDocumentPath( filepathOutput ) );

      doc = new Document( FileUtils.getCommonDocumentPath( filepathOutput ) );

      DocumentBuilder builder = new DocumentBuilder( doc );

      Section section = builder.getDocument().getSections().get( 0 );

      Body body = section.getBody();

      for( int i = 0; i < body.getChildNodes().getCount(); i++ )
      {

        Node node = body.getChildNodes().get( i );

        if( node instanceof Paragraph )
        {

          Paragraph paragraph = ( Paragraph ) node;

          if( paragraph.getText().startsWith( "#--Table" ) )
          {

            builder.moveTo( body.getChildNodes().get( i - 1 ) );
            node.remove();

            builder.startTable();

            builder.getParagraphFormat().setFirstLineIndent( 0 );
            builder.getParagraphFormat().setAlignment( ParagraphAlignment.CENTER );

            builder.getRowFormat().getBorders().setLineStyle( LineStyle.SINGLE );
            builder.getRowFormat().getBorders().setColor( Color.BLACK );

            builder.insertCell();
            builder.getCellFormat().setWidth( 20 );
            builder.write( "№\n№\nп/п" );
            builder.insertCell();
            builder.getCellFormat().setWidth( 250 );
            builder.write( "Фамилия, имя, отчество" );
            builder.insertCell();
            builder.getCellFormat().setWidth( 30 );
            builder.write( "Форма\nобучения" );
            builder.insertCell();
            builder.getCellFormat().setWidth( 30 );
            builder.write( "№\nпротокола\nзащиты" );
            builder.insertCell();
            builder.getCellFormat().setWidth( 50 );
            builder.write( "Оценка,\nполученная \nна защите" );
            builder.insertCell();
            builder.getCellFormat().setWidth( 50 );
            builder.write( "Выдать \nдиплом" );
            builder.endRow();
            builder.getParagraphFormat().setAlignment( ParagraphAlignment.LEFT );

            List<DefenceGraduateWork> defenceGraduateWorkList =
              GraduateWorkService.getDefenceGraduateWorkListOrderByProtocolNumber( gekDay.getDate() );

            for( int j = 0; j < defenceGraduateWorkList.size(); j++ )
            {

              DefenceGraduateWork defenceGraduateWork = defenceGraduateWorkList.get( j );

              Student student = StudentsService.getStudentByDefenceGraduateWorkId( defenceGraduateWork.getId() );

              builder.insertCell();
              builder.getCellFormat().setWidth( 20 );
              builder.write( "" + ( j + 1 ) );

              builder.insertCell();
              builder.getCellFormat().setWidth( 250 );
              if( student != null )
              {
                builder.write( student.getFullName() );
              }

              Faculty deparatament = null;
              if( student != null )
              {
                OrganizationUnit ou = OrganizationUnitService.getOrganizationUnit( student.getGroup() );
                deparatament = ou.getDepartamentType();
                if( deparatament == null )
                {
                  deparatament = OrganizationUnitService.getRootOrganizationUnit( ou ).getDepartamentType();
                }
              }
              builder.insertCell();
              builder.getCellFormat().setWidth( 30 );
              if( deparatament != null )
              {
                builder.write( deparatament.getShortName() );
              }
              else
              {
                builder.write( "дн" );
              }

              builder.insertCell();
              builder.getCellFormat().setWidth( 30 );
              builder.write( defenceGraduateWork.getProtocolNumber() != null ? defenceGraduateWork.getProtocolNumber()
                : "" );

              builder.insertCell();
              builder.getCellFormat().setWidth( 50 );
              builder.write( "" );

              builder.insertCell();
              builder.getCellFormat().setWidth( 50 );
              builder.write( "" );

              builder.endRow();
            }
            builder.endTable();
          }
        }
      }

      builder.getDocument().save( FileUtils.getCommonDocumentPath( filepathOutput ) );
//	    RemoveTrialString.removeStringInDocument(FileUtils.getCommonDocumentPath( filepathOutput ) );
    }
    catch( Exception e )
    {

      e.printStackTrace();
    }
  }

  private GraduateWork getGraduateWork( String gradWork )
  {

    return GraduateWorkService.getGraduateWork( gradWork );
  }

  private String getStafferInfo( PartTimeStaffer staffer )
  {

    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append( staffer.getWorkPlace() );
    if( staffer.getOriginalPosition() != null )
    {

      stringBuilder.append( ", " );
      stringBuilder.append( staffer.getOriginalPosition() );
    }

    return stringBuilder.toString();
  }

  private String getTeacherFull( String teacherId )
  {

    if( teacherId == null || teacherId.length() == 0 )
    {

      return "";
    }

    Teacher teacher = TeachersService.getTeacher( teacherId );
    if( teacher != null )
    {

      return teacher.getFullName();
    }

    return "";
  }

  private String getTeacherShort( String teacherId )
  {

    if( teacherId == null || teacherId.length() == 0 )
    {

      return "";
    }

    Teacher teacher = TeachersService.getTeacher( teacherId );
    if( teacher != null )
    {

      return teacher.getShortName();
    }

    return "";
  }
}