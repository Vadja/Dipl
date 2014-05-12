package org.iit.dr.documents.word.write.practice;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.iit.dr.data_model.GraduateWork;
import org.iit.dr.data_model.role.PartTimeStaffer;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.services.GraduateWorkService;
import org.iit.dr.services.StudentsService;
import org.iit.dr.services.TeachersService;
import org.iit.dr.utils.FileUtils;
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
 * TableDocumentGenerator.
 * 
 * @author Yuriy Karpovich
 */
public class TableDocumentGenerator
{

  protected String templateName;

  public String getTemplateName()
  {
    return templateName;
  }

  public void setTemplateName( String templateName )
  {
    this.templateName = templateName;
  }

  public void generateDocument( java.util.List<String> selectedItemList, String filepathOutput )
  {
    try
    {
      Document doc = new Document( FileUtils.getTemplate( templateName ) );

      DocumentBuilder builder = new DocumentBuilder( doc );

      Section section = builder.getDocument().getSections().get( 0 );

      Body body = section.getBody();

      for( int i = 0; i < body.getChildNodes().getCount(); i++ )
      {

        Node node = body.getChildNodes().get( i );

        if( node instanceof Paragraph )
        {

          Paragraph paragraph = ( Paragraph ) node;

          if( paragraph.getText().startsWith( "#--students" ) )
          {

            builder.moveTo( body.getChildNodes().get( i - 1 ) );
            node.remove();
            builder.startTable();

            builder.getParagraphFormat().setFirstLineIndent( 0 );

            List<Student> studentSortList = new ArrayList<Student>();

            for( String selectedItem : selectedItemList )
            {

              GraduateWork graduateWork = getGraduateWork( selectedItem );

              if( graduateWork == null )
              {

                continue;
              }

              Student student = StudentsService.getStudent( graduateWork.getStudentId() );

              if( student != null )
              {

                studentSortList.add( student );
              }
            }

            Collections.sort( studentSortList, new Comparator<Student>()
            {
              public int compare( Student person1, Student person2 )
              {
                if( person1 == null && person2 == null )
                {

                  return 0;
                }

                if( person1 != null && person2 != null )
                {

                  if( person1.getLastName() != null && person2.getLastName() != null )
                  {

                    return person1.getLastName().compareTo( person2.getLastName() );
                  }
                }
                else if( person1 == null )
                {

                  return 1;
                }
                else
                {

                  return -1;
                }

                return 0;
              }
            } );

            for( int j = 0; j < studentSortList.size(); j++ )
            {

              Student student = studentSortList.get( j );

              builder.getCellFormat().setWidth( 100 );

              builder.insertCell();
              builder.getParagraphFormat().setAlignment( ParagraphAlignment.RIGHT );
              builder.write( "" + ( j + 1 ) );

              builder.clearCellAttrs();

              builder.insertCell();
              builder.getParagraphFormat().setAlignment( ParagraphAlignment.LEFT );
              builder.write( student.getFullName() );

              builder.endRow();
            }

            builder.endTable();
          }
          else if( paragraph.getText().startsWith( "#--reviewers" ) )
          {

            builder.moveTo( body.getChildNodes().get( i - 1 ) );
            node.remove();
            builder.startTable();

            builder.getParagraphFormat().setFirstLineIndent( 0 );
            builder.getParagraphFormat().setAlignment( ParagraphAlignment.CENTER );

            builder.getRowFormat().getBorders().setLineStyle( LineStyle.SINGLE );
            builder.getRowFormat().getBorders().setColor( Color.BLACK );

            builder.insertCell();
            builder.getCellFormat().setWidth( 200 );
            builder.write( "Ф.И.О." );
            builder.insertCell();
            builder.getCellFormat().setWidth( 100 );
            builder.write( "Место работы, должность" );
            builder.insertCell();
            builder.getCellFormat().setWidth( 100 );
            builder.write( "Кол-во студ." );
            builder.insertCell();
            builder.getCellFormat().setWidth( 200 );
            builder.write( "Ф.И.О. студента" );
            builder.endRow();
            builder.getParagraphFormat().setAlignment( ParagraphAlignment.LEFT );

            Map<String, List<GraduateWork>> graduateWorkMap = new LinkedHashMap<String, List<GraduateWork>>();

            for( String selectedItem : selectedItemList )
            {

              GraduateWork graduateWork = getGraduateWork( selectedItem );

              if( graduateWork == null || graduateWork.getReviewerId() == null )
              {

                continue;
              }

              List<GraduateWork> graduateWorkList = graduateWorkMap.get( graduateWork.getReviewerId() );
              if( graduateWorkList != null )
              {

                graduateWorkList.add( graduateWork );
              }
              else
              {

                graduateWorkList = new ArrayList<GraduateWork>();
                graduateWorkList.add( graduateWork );
                graduateWorkMap.put( graduateWork.getReviewerId(), graduateWorkList );
              }
            }

            for( Map.Entry<String, List<GraduateWork>> entry : graduateWorkMap.entrySet() )
            {

              Teacher teacher = TeachersService.getTeacher( entry.getKey() );

              if( teacher instanceof PartTimeStaffer )
              {

                PartTimeStaffer staffer = ( PartTimeStaffer ) teacher;

                builder.insertCell();
                builder.getCellFormat().setWidth( 200 );
                builder.write( staffer.getFullName() );

                builder.insertCell();
                builder.getCellFormat().setWidth( 100 );
                builder.write( getStafferInfo( staffer ) );

                builder.insertCell();
                builder.getCellFormat().setWidth( 100 );
                builder.write( "" + entry.getValue().size() );

                StringBuilder studentsFio = new StringBuilder();

                int studentCounter = 0;

                for( GraduateWork graduateWork : entry.getValue() )
                {

                  Student student = StudentsService.getStudent( graduateWork.getStudentId() );
                  if( student != null )
                  {

                    studentsFio.append( ++studentCounter );
                    studentsFio.append( ". " );
                    studentsFio.append( student.getShortName() );
                    studentsFio.append( "\n" );
                  }
                }

                if( studentCounter > 0 )
                {

                  studentsFio.delete( studentsFio.length() - 1, studentsFio.length() );
                }

                builder.insertCell();
                builder.getCellFormat().setWidth( 200 );
                builder.write( studentsFio.toString() );

                builder.endRow();
              }
            }

            builder.endTable();
          }

        }
      }

      builder.getDocument().save( FileUtils.getCommonDocumentPath( filepathOutput ) );
//	  RemoveTrialString.removeStringInDocument(FileUtils.getCommonDocumentPath( filepathOutput ));
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

    String data = getStringNotNull( staffer.getOriginalPosition() ) + " " + getStringNotNull( staffer.getWorkPlace() );

    String degree = staffer.getDegree() != null ? staffer.getDegree().getRusNameShort().toLowerCase() : "";

    if( data.length() > 1 && degree.length() > 0 )
    {

      data += ", ";
    }
    data += degree;

    String rank = staffer.getRank() != null ? staffer.getRank().getRusName().toLowerCase() : "";

    if( data.length() > 1 && rank.length() > 0 )
    {

      data += ", ";
    }
    data += rank;

    return data;
  }

  private static String getStringNotNull( String value )
  {

    return value != null ? value : "";
  }

}
