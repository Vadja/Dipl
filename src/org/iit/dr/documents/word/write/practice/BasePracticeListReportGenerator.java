package org.iit.dr.documents.word.write.practice;

import java.util.ArrayList;
import java.util.List;

import org.iit.dr.data_model.PracticePlace;
import org.iit.dr.data_model.PracticeWork;
import org.iit.dr.data_model.role.Staffer;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.services.OrganizationUnitService;
import org.iit.dr.services.StudentsService;
import org.iit.dr.services.TeachersService;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class BasePracticeListReportGenerator
{

  protected List<Student> getStudents( PracticePlace place, String consultantId )
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

  protected String getStudentGroup( Student stud )
  {
    return "гр. " + OrganizationUnitService.getOrganizationUnit( stud.getGroup() ).getName();
  }

  protected String getConsultantString( String consultantId )
  {
    Staffer teacher = ( Staffer ) TeachersService.getTeacher( consultantId );
    return "Руководитель практики: " + teacher.getPosition().getRusNameShort().toLowerCase() + " кафедры ИИТ "
      + teacher.getFullName();
  }

}
