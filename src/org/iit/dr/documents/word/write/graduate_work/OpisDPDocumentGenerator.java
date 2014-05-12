package org.iit.dr.documents.word.write.graduate_work;

import java.awt.Color;
import java.util.List;

import org.iit.dr.data_model.DefenceGraduateWork;
import org.iit.dr.data_model.GraduateWork;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.documents.common.generator.IReportGenerator;
import org.iit.dr.documents.common.generator.ReportParams;
import org.iit.dr.documents.common.generator.exceptions.ReportGeneratorException;
import org.iit.dr.documents.common.report.Report;
import org.iit.dr.documents.common.report.ReportType;
import org.iit.dr.services.GraduateWorkService;
import org.iit.dr.services.StudentsService;
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
 * OpisDPDocumentGenerator.
 * 
 * @author Yuriy Karpovich
 */
public class OpisDPDocumentGenerator implements IReportGenerator
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

  public void generateDocument( String filepathOutput )
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

          if( paragraph.getText().startsWith( "#--odp" ) )
          {

            builder.moveTo( body.getChildNodes().get( i - 1 ) );
            node.remove();

            builder.startTable();

            builder.getParagraphFormat().setFirstLineIndent( 0 );
            builder.getParagraphFormat().setAlignment( ParagraphAlignment.CENTER );

            builder.getRowFormat().getBorders().setLineStyle( LineStyle.SINGLE );
            builder.getRowFormat().getBorders().setColor( Color.BLACK );

            builder.insertCell();
            builder.getCellFormat().setWidth( 35 );
            builder.write( "№ п/п" );
            builder.insertCell();
            builder.getCellFormat().setWidth( 100 );
            builder.write( "Ф.И.О.\nдипломника" );
            builder.insertCell();
            builder.getCellFormat().setWidth( 180 );
            builder.write( "Наименование темы дипломного проекта" );
            builder.insertCell();
            builder.getCellFormat().setWidth( 50 );
            builder.write( "К-во листов поясн. зап." );
            builder.insertCell();
            builder.getCellFormat().setWidth( 60 );
            builder.write( "К-во листов чертежей" );
            builder.insertCell();
            builder.getCellFormat().setWidth( 60 );
            builder.write( "Примечание" );
            builder.endRow();
            builder.getParagraphFormat().setAlignment( ParagraphAlignment.LEFT );

            List<DefenceGraduateWork> defenceGraduateWorkList =
              GraduateWorkService.getDefenceGraduateWorkListOrderByStudent();

            for( int j = 0; j < defenceGraduateWorkList.size(); j++ )
            {

              DefenceGraduateWork defenceGraduateWork = defenceGraduateWorkList.get( j );

              GraduateWork graduateWork = GraduateWorkService.getGraduateWork( defenceGraduateWork.getGraduateWorkId() );

              Student student = StudentsService.getStudentByDefenceGraduateWorkId( defenceGraduateWork.getId() );

              builder.insertCell();
              builder.getCellFormat().setWidth( 35 );
              builder.write( "" + ( j + 1 ) );

              builder.insertCell();
              builder.getCellFormat().setWidth( 100 );
              if( student != null )
              {
                builder.write( student.getShortName() );
              }

              builder.insertCell();
              builder.getCellFormat().setWidth( 180 );
              if(graduateWork!=null){
                  builder.write( graduateWork.getTitle() != null ? graduateWork.getTitle() : "" );
              }

              builder.insertCell();
              builder.getCellFormat().setWidth( 50 );
              if(defenceGraduateWork!=null){
                  builder.write( defenceGraduateWork.getGraduateWorkInfo().getPageCount() != null ? defenceGraduateWork
                          .getGraduateWorkInfo().getPageCount() : "" );
              }

              builder.insertCell();
              builder.getCellFormat().setWidth( 60 );
              if(defenceGraduateWork!=null){
                  builder.write( defenceGraduateWork.getGraduateWorkInfo().getGraphicCount() != null ? defenceGraduateWork
                          .getGraduateWorkInfo().getGraphicCount() : "" );
              }

              builder.insertCell();
              builder.getCellFormat().setWidth( 60 );
              builder.write( "" );

              builder.endRow();
            }
            builder.endTable();
          }
        }
      }

      builder.getDocument().save( filepathOutput );
//	    RemoveTrialString.removeStringInDocument(filepathOutput);
    }
    catch( Exception e )
    {
      e.printStackTrace();
      throw new ReportGeneratorException( "Can't create report: "
        + ReportType.GRADUATE_WORKS_OPIS_REPORT.getTemplateName(), e );
    }
  }

  public Report generateReport( ReportParams params )
  {
    setTemplateName( ReportType.GRADUATE_WORKS_OPIS_REPORT.getTemplateName() );
    generateDocument( params.getOtputFilePath() );
    return new Report( params.getOtputFilePath(), ReportType.GRADUATE_WORKS_OPIS_REPORT );
  }
}