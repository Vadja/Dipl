package org.iit.dr.documents.word.write.practice;

import java.util.List;

import org.iit.dr.data_model.PracticePlace;
import org.iit.dr.data_model.PracticeType;
import org.iit.dr.services.CompaniesService;
import org.iit.dr.services.PracticeService;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class BasePracticeBeginReportGenerator
{
  public static final String FLAG = "#%$";

  protected String[][] prepareTableData( PracticeType type )
  {
    List<PracticePlace> placesList = PracticeService.getPracticePlacesWithStudents( type );
    String[][] data = new String[placesList.size()][];
    for( int i = 0; i < placesList.size(); i++ )
    {
      PracticePlace place = placesList.get( i );
      data[i] = new String[5];
      data[i][0] = CompaniesService.getCompany( place.getCompanyId() ).getFullNameWithCity();
      data[i][1] = new Integer( place.getStudentsNumber() ).toString();
      data[i][2] = new Integer( place.getStudentsNumber() ).toString();
      data[i][3] = "-";
      data[i][4] = "-";
    }
    return data;
  }
}
