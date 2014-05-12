package org.iit.dr.documents.word.write.practice;

import java.util.List;

import org.iit.dr.data_model.PracticePlace;
import org.iit.dr.data_model.PracticeType;
import org.iit.dr.documents.word.write.common.TableAppender;
import org.iit.dr.services.CompaniesService;
import org.iit.dr.services.PracticeService;
import org.iit.dr.utils.FileUtils;

import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class PracticeBeginReportGenerator
{
  public static final String FLAG = "#%$";

  public static final String GRADUATE_PRACTICE_BEGIN_REPORT = "Докладная о начале преддипломной практики.doc";

  public static final String PRODUCTION_PRACTICE_BEGIN_REPORT = "Докладная о начале производственной практики.doc";

  public static void generateBeginPracticeReport( PracticeType practiceType ) throws Exception
  {
    String fileName = getFileName( practiceType );
    Document doc = new Document( FileUtils.getTemplate( fileName ) );
    DocumentBuilder builder = new DocumentBuilder( doc );
    TableAppender tAppender = new TableAppender( builder );
    builder = tAppender.appendTableData( FLAG, prepareData( practiceType ) );
    builder.getDocument().save( FileUtils.getPracticeDocumentPath( fileName ) );
  }

  private static String[][] prepareData( PracticeType practiceType )
  {
    List<PracticePlace> placesList = PracticeService.getPracticePlacesWithStudents( practiceType );
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

  private static String getFileName( PracticeType practiceType )
  {
    switch( practiceType )
    {
      case GRADUATING:
        return GRADUATE_PRACTICE_BEGIN_REPORT;
      case PRODUCTION:
        return PRODUCTION_PRACTICE_BEGIN_REPORT;
    }
    return null;
  }
}
