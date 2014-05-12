package org.iit.dr.documents.word.write.practice;

import java.util.ArrayList;
import java.util.List;

import org.iit.dr.data_model.PracticePlace;
import org.iit.dr.data_model.PracticeType;
import org.iit.dr.data_model.PracticeWork;
import org.iit.dr.data_model.role.Staffer;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.services.CompaniesService;
import org.iit.dr.services.OrganizationUnitService;
import org.iit.dr.services.PracticeService;
import org.iit.dr.services.StudentsService;
import org.iit.dr.services.TeachersService;
import org.iit.dr.utils.FileUtils;

import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class PracticePlacesConsultantsReportGenerator
{
  public static final String PRODUCTION_PRACTICE = "Списки на производственную практику.doc";

  public static final String GRADUATING_PRACTICE = "Списки на преддипл практику.doc";

  public static final String FLAG = "#%$";

  public static void generatePracticePlacesConsultantReport( PracticeType practiceType ) throws Exception
  {
    String fileName = getFileName( practiceType );
    Document doc = new Document( FileUtils.getTemplate( fileName ) );
    DocumentBuilder builder = new DocumentBuilder( doc );
    PracticePlaceWriter placesWriter = new PracticePlaceWriter( builder, FLAG );
    placesWriter.startWriter();
    for( PracticePlace place : PracticeService.getPracticePlacesWithStudents( practiceType ) )
    {
      placesWriter.writeCompanyName( CompaniesService.getCompany( place.getCompanyId() ).getFullNameWithCity() );
      for( String consId : place.getConsultantsId() )
      {
        for( Student stud : getStudents( place, consId ) )
        {
          placesWriter.writeStudent( stud.getFullName(), getStudentGroup( stud ) );
        }
        placesWriter.writeConsultant( getConsultantString( consId ) );
      }
    }
    builder.getDocument().save( FileUtils.getPracticeDocumentPath( fileName ) );
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
    return "Руководитель практики: " + teacher.getPosition().getRusNameShort().toLowerCase() + " кафедры ИИТ "
      + teacher.getFullName();
  }
}