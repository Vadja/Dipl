package org.iit.dr.documents.word.write.conferences;

import java.util.List;

import org.iit.dr.data_model.Conference;
import org.iit.dr.data_model.GraduateWork;
import org.iit.dr.data_model.Member;
import org.iit.dr.data_model.role.PartTimeStaffer;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.data_storage.DataStorage;
import org.iit.dr.services.GraduateWorkService;
import org.iit.dr.services.TeachersService;
import org.iit.dr.utils.FileUtils;

import com.aspose.words.DocumentBuilder;

/**
 * Created by IntelliJ IDEA. User: Admin Date: 16.12.2010 Time: 1:42:14 To change this template use File | Settings |
 * File Templates.
 */
public class ConferenceDocumentGenerator
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

      List<Conference> confList = DataStorage.getInstance().getConferenceList();

      // Document doc = new Document(FileUtils.getTemplate(templateName));

      // Document doc = new Document(FileUtils.getCommonDocumentPath(filepathOutput));
      System.out.println( "2" );
      DocumentBuilder builder = new DocumentBuilder();

      for( Conference conf : confList )
      {
        int i = 1;
        builder.writeln( conf.getTitle() + " ," + conf.getCity() + ", " + conf.getYear() + "г." );
        for( Member mem : conf.getMemberList() )
        {
          String s =
            String.valueOf( i ) + ". " + mem.getTheme() + ", " + mem.getAuthor() + "," + mem.getPublication() + ".";
          builder.writeln( s );
          i++;
        }

      }

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
