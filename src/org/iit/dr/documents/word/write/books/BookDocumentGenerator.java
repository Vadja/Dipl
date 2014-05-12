package org.iit.dr.documents.word.write.books;

import java.util.List;

import org.iit.dr.data_model.Book;
import org.iit.dr.data_model.GraduateWork;
import org.iit.dr.data_model.role.PartTimeStaffer;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.data_storage.DataStorage;
import org.iit.dr.services.GraduateWorkService;
import org.iit.dr.services.TeachersService;
import org.iit.dr.utils.FileUtils;

import com.aspose.words.DocumentBuilder;

/**
 * Created by IntelliJ IDEA. User: Admin Date: 14.12.2010 Time: 12:23:46 To change this template use File | Settings |
 * File Templates.
 */
public class BookDocumentGenerator
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

  public void generateDocument( String filepathOutput )
  {
    try
    {

      List<Book> bookList = DataStorage.getInstance().getBookList();

      // Document doc = new Document(FileUtils.getTemplate(templateName));

      // Document doc = new Document(FileUtils.getCommonDocumentPath(filepathOutput));
      System.out.println( "2" );
      DocumentBuilder builder = new DocumentBuilder();
      int i = 1;
      for( Book book : bookList )
      {
        System.out.println( i );
        String s =
          String.valueOf( i ) + ". " + book.getAuthor() + ", " + book.getTitle() + ": " + book.getType() + "("
            + book.getGrif() + "), " + book.getInfo() + "," + book.getPage() + ".";
        builder.writeln( s );
        i++;
      }

      // Section section = builder.getDocument().getSections().get(0);
      //
      // Body body = section.getBody();
      //
      // for (int i = 0; i < body.getChildNodes().getCount(); i++) {
      //
      // Node node = body.getChildNodes().get(i);
      //
      // if (node instanceof Paragraph) {
      //
      // Paragraph paragraph = (Paragraph) node;
      //
      // if (paragraph.getText().startsWith("#--Table")) {
      //
      // builder.moveTo(body.getChildNodes().get(i - 1));
      // node.remove();
      //
      // builder.startTable();
      //
      // builder.getParagraphFormat().setFirstLineIndent(0);
      // builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
      //
      // builder.getRowFormat().getBorders().setLineStyle(LineStyle.SINGLE);
      // builder.getRowFormat().getBorders().setColor(Color.BLACK);
      //
      // builder.insertCell();
      // builder.getCellFormat().setWidth(20);
      // builder.write("№\n№\nп/п");
      // builder.insertCell();
      // builder.getCellFormat().setWidth(250);
      // builder.write("Фамилия, имя, отчество");
      // builder.insertCell();
      // builder.getCellFormat().setWidth(30);
      // builder.write("Форма\nобучения");
      // builder.insertCell();
      // builder.getCellFormat().setWidth(30);
      // builder.write("№\nпротокола\nзащиты");
      // builder.insertCell();
      // builder.getCellFormat().setWidth(50);
      // builder.write("Оценка,\nполученная \nна защите");
      // builder.insertCell();
      // builder.getCellFormat().setWidth(50);
      // builder.write("Выдать \nдиплом");
      // builder.endRow();
      // builder.getParagraphFormat().setAlignment(ParagraphAlignment.LEFT);
      //
      // java.util.List<DefenceGraduateWork> defenceGraduateWorkList =
      // DataStorage.getInstance().
      // getDefenceGraduateWorkListOrderByProtocolNumber(gekDay.getDate());
      //
      // for (int j = 0; j < defenceGraduateWorkList.size(); j++) {
      //
      // DefenceGraduateWork defenceGraduateWork = defenceGraduateWorkList.get(j);
      //
      // Student student = DataStorage.getInstance().getStudentByDefenceGraduateWorkId(defenceGraduateWork.getId());
      //
      // builder.insertCell();
      // builder.getCellFormat().setWidth(20);
      // builder.write("" + (j + 1));
      //
      // builder.insertCell();
      // builder.getCellFormat().setWidth(250);
      // if (student != null) {
      // builder.write(student.getFullName());
      // }
      //
      // builder.insertCell();
      // builder.getCellFormat().setWidth(30);
      // builder.write("дн");
      //
      // builder.insertCell();
      // builder.getCellFormat().setWidth(30);
      // builder.write(defenceGraduateWork.getProtocolNumber() != null ? defenceGraduateWork.getProtocolNumber() : "");
      //
      // builder.insertCell();
      // builder.getCellFormat().setWidth(50);
      // builder.write("");
      //
      // builder.insertCell();
      // builder.getCellFormat().setWidth(50);
      // builder.write("");
      //
      // builder.endRow();
      // }
      // builder.endTable();
      // }
      // }
      // }

      builder.getDocument().save( FileUtils.getCommonDocumentPath( filepathOutput ) );
      System.out.println( FileUtils.getCommonDocumentPath( filepathOutput ) );
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
