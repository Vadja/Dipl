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
public class PracticeResultReportGenerator
{
  public static final String FLAG1 = "#%$";

  public static final String FLAG2 = "#&$";

  public static void generatePracticeReportDoc( PracticeType practiceType ) throws Exception
  {
    String fileName = getFileName( practiceType );
    Document doc = new Document( FileUtils.getTemplate( fileName ) );
    DocumentBuilder builder = new DocumentBuilder( doc );
    TableAppender tAppender1 = new TableAppender( builder );
    builder = tAppender1.appendTableData( FLAG1, preparePracticePlacesData( practiceType ) );
    TableAppender tAppender2 = new TableAppender( builder );
    builder = tAppender2.appendTableData( FLAG2, prepareMarksData( practiceType ) );
    builder.getDocument().save( FileUtils.getPracticeDocumentPath( fileName ) );
  }

  private static String[][] prepareMarksData( PracticeType practiceType )
  {
    int all = PracticeService.getPracticeWorksList( practiceType ).size();
    int mark1_3 = PracticeService.getPracticeWorksWithMark( 1, 3, practiceType );
    int mark4_5 = PracticeService.getPracticeWorksWithMark( 4, 5, practiceType );
    int mark6_8 = PracticeService.getPracticeWorksWithMark( 6, 8, practiceType );
    int mark9_10 = PracticeService.getPracticeWorksWithMark( 9, 10, practiceType );
    String[][] data = new String[1][];
    data[0] = new String[9];
    data[0][0] = "" + all;
    data[0][1] = "" + all;
    data[0][2] = "" + mark9_10;
    data[0][3] = "" + mark6_8;
    data[0][4] = "" + mark4_5;
    data[0][5] = "" + mark1_3;
    data[0][6] = "";
    data[0][7] = "";
    data[0][8] = "";
    return data;
  }

  private static String[][] preparePracticePlacesData( PracticeType practiceType )
  {
    List<PracticePlace> placesList = PracticeService.getPracticePlacesWithStudents( practiceType );
    String[][] data = new String[placesList.size()][];
    for( int i = 0; i < placesList.size(); i++ )
    {
      PracticePlace place = placesList.get( i );
      data[i] = new String[5];
      data[i][0] = "" + ( i + 1 ) + ".";
      data[i][1] = CompaniesService.getCompany( place.getCompanyId() ).getFullNameWithCity();
      data[i][2] = new Integer( place.getStudentsNumber() ).toString();
      data[i][3] = "-";
      data[i][4] = "";
    }
    return data;
  }

  private static String getFileName( PracticeType practiceType )
  {
    switch( practiceType )
    {
      case GRADUATING:
        return "Отчет о преддипломной практике.doc";
      case PRODUCTION:
        return "Отчет о производственной практике.doc";
    }
    return null;
  }
}
