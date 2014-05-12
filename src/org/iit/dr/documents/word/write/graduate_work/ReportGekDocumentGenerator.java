package org.iit.dr.documents.word.write.graduate_work;

import java.awt.Color;
import java.util.List;

import org.iit.dr.data_model.GraduateWork;
import org.iit.dr.data_model.StatisticaGek;
import org.iit.dr.data_model.role.PartTimeStaffer;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.services.GekService;
import org.iit.dr.services.GraduateWorkService;
import org.iit.dr.services.StudentsService;
import org.iit.dr.services.TeachersService;
import org.iit.dr.utils.FileUtils;

import com.aspose.words.Body;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.LineStyle;
import com.aspose.words.Node;
import com.aspose.words.Paragraph;
import com.aspose.words.ParagraphAlignment;
import com.aspose.words.Section;

/**
 * ReportGekDocumentGenerator.
 * 
 * @author Yuriy Karpovich
 */
public class ReportGekDocumentGenerator
{

  String[] fieldNames = new String[] {"cWork", "cWorkP", "Secr"};

  protected String templateName;

  public String getTemplateName()
  {
    return templateName;
  }

  public void setTemplateName( String templateName )
  {
    this.templateName = templateName;
  }

  public void generateDocument( String filepathOutput )
  {
    try
    {

      StatisticaGek statisticaGek = GekService.getStatisticaGek();

      //
      // String[] fieldValues = new String[]{getTeacherFull(gek.getPresideId()),
      // DateUtils.dateToString(gekDay.getDate()), getTeacherShort(gek.getCommissionerSecrId()
      // )};

      String[] fieldValues = new String[] {statisticaGek.getCWork(), statisticaGek.getCWorkP(), ""};

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

          if( paragraph.getText().startsWith( "#--WithHonoursTable" ) )
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
            builder.write( "" );
            builder.insertCell();
            builder.getCellFormat().setWidth( 200 );
            builder.write( "Фамилия, имя, отчество" );
            builder.insertCell();
            builder.getCellFormat().setWidth( 300 );
            builder.write( "Тема дипломного проекта" );
            builder.endRow();
            builder.getParagraphFormat().setAlignment( ParagraphAlignment.LEFT );

            List<GraduateWork> graduateWorkList = GraduateWorkService.getGraduateWorkListWithBestOrderByStudent();

            for( int j = 0; j < graduateWorkList.size(); j++ )
            {

              GraduateWork graduateWork = graduateWorkList.get( j );

              Student student = StudentsService.getStudent( graduateWork.getStudentId() );

              builder.insertCell();
              builder.getCellFormat().setWidth( 20 );
              builder.write( "" + ( j + 1 ) );

              builder.insertCell();
              builder.getCellFormat().setWidth( 250 );
              if( student != null )
              {
                builder.write( student.getFullName() );
              }

              builder.insertCell();
              builder.getCellFormat().setWidth( 300 );
              builder.write( graduateWork.getTitle() != null ? graduateWork.getTitle() : "" );

              builder.endRow();
            }
            builder.endTable();
          }
        }
      }

      builder.getDocument().save( FileUtils.getCommonDocumentPath( filepathOutput ) );
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