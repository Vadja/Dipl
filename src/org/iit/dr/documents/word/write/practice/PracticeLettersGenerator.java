package org.iit.dr.documents.word.write.practice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.iit.dr.data_model.PracticePlace;
import org.iit.dr.data_model.PracticeType;
import org.iit.dr.data_model.PracticeWork;
import org.iit.dr.data_model.role.Staffer;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.documents.word.write.common.WordDocBuilder;
import org.iit.dr.services.CompaniesService;
import org.iit.dr.services.OrganizationUnitService;
import org.iit.dr.services.PracticeService;
import org.iit.dr.services.StudentsService;
import org.iit.dr.services.TeachersService;
import org.iit.dr.utils.DateUtils;
import org.iit.dr.utils.FileUtils;

import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class PracticeLettersGenerator
{

  public static final String PRODUCTION_PRACTICE = "письмо ПП.doc";

  public static final String GRADUATING_PRACTICE = "письмо ДП.doc";

  public static final String FLAG1 = "#%$";

  public static final String FLAG2 = "#&$";

  public static void generateLetters( PracticeType practiceType ) throws Exception
  {
    for( PracticePlace place : PracticeService.getPracticePlaceList( practiceType ) )
    {
      generateLetter( place, practiceType );
    }
  }

  public static void generateLetter( PracticePlace place, PracticeType practiceType ) throws Exception
  {
    Document doc = new Document( FileUtils.getTemplate( getFileName( practiceType )));
    doc.getMailMerge().execute( new String[] {"YEAR"}, new String[] {DateUtils.dateToYear(new Date())});
    
    DocumentBuilder builder = new DocumentBuilder( doc );
    WordDocBuilder docBuilder = new WordDocBuilder( builder );
    docBuilder.appendTableData( FLAG1, prepareCompanyData( place ) );

    PracticePlaceWriter placesWriter = new PracticePlaceWriter( builder, FLAG2 );
    placesWriter.startWriter();
    for( String consId : place.getConsultantsId() )
    {
      for( Student stud : getStudents( place, consId ) )
      {
    	if(stud!=null){
    		placesWriter.writeStudent( stud.getFullName(), getStudentGroup( stud ) );
    	}
      }
      placesWriter.writeConsultant( getConsultantString( consId ) );
    }
    String companyName = CompaniesService.getCompany( place.getCompanyId() ).getShortName();
    builder.getDocument().save( FileUtils.getPracticeLettersPath( companyName, getFileName( practiceType ) ) );

  }

  private static String[][] prepareCompanyData( PracticePlace place )
  {
    String[][] data = new String[1][];
    data[0] = new String[1];
    data[0][0] = CompaniesService.getCompany( place.getCompanyId() ).getFullName();
    return data;
  }

  private static String getFileName( PracticeType practiceType )
  {
    switch( practiceType )
    {
      case GRADUATING:
        return GRADUATING_PRACTICE;
      case PRODUCTION:
        return PRODUCTION_PRACTICE;
    }
    return null;
  }

  private static List<Student> getStudents( PracticePlace place, String consultantId )
  {
    List<Student> result = new ArrayList<Student>();
    for( PracticeWork work : place.getPracticeWorks() )
    {
      if( work.getConsultantId() != null && work.getConsultantId().equals( consultantId ) )
      {
        result.add( StudentsService.getStudent( work.getStudentId() ) );
      }
    }
    return result;
  }

  private static String getStudentGroup( Student stud )
  {
    return "гр. " + OrganizationUnitService.getOrganizationUnit( stud.getGroup() ).getName();
  }

  private static String getConsultantString( String consultantId )
  {
    Staffer teacher = ( Staffer ) TeachersService.getTeacher( consultantId );
    if(teacher==null){
    	return "Руководитель практики: " + "____________" + " кафедры ИИТ "
    		      + "_____________";
    }
    return "Руководитель практики: " + teacher.getPosition().getRusNameShort().toLowerCase() + " кафедры ИИТ "
      + teacher.getFullName();
  }

}
