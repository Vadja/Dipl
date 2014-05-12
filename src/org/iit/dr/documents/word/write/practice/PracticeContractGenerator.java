package org.iit.dr.documents.word.write.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.iit.dr.data_model.PracticePlace;
import org.iit.dr.data_model.PracticeType;
import org.iit.dr.data_model.PracticeWork;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.documents.word.write.common.WordDocBuilder;
import org.iit.dr.services.CompaniesService;
import org.iit.dr.services.PracticeService;
import org.iit.dr.services.StudentsService;
import org.iit.dr.utils.DateUtils;
import org.iit.dr.utils.FileUtils;

import com.aspose.words.Document;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class PracticeContractGenerator
{
  public static final String PRODUCTION_PRACTICE = "договор ПП.doc";

  public static final String GRADUATING_PRACTICE = "договор ДП.doc";

  public static final String SPECIALITY = "1-40 03 01 Искусственный интеллект";

  public static final String FLAG = "#%$";

  public static void generateContracts( PracticeType practiceType ) throws Exception
  {
    for( PracticePlace place : PracticeService.getPracticePlaceList( practiceType ) )
    {
      generateContract( place, practiceType );
    }
  }

  public static void generateContract( PracticePlace place, PracticeType practiceType ) throws Exception
  {
	Document doc = new Document(FileUtils.getTemplate( getFileName( practiceType ) ) );
	doc.getMailMerge().execute(new String[] {"YEAR","COMPANY"},new String[] {DateUtils.dateToYear(new Date()),CompaniesService.getCompany( place.getCompanyId() ).getFullName()} );
	
    WordDocBuilder builder = new WordDocBuilder(doc);
    builder.appendTableData( FLAG, prepareStudentsData( place ) );
    String companyName = CompaniesService.getCompany( place.getCompanyId() ).getShortName();
    builder.saveDoc( FileUtils.getPracticeContractPath( companyName, getFileName( practiceType ) ) );
  }

  private static String[][] prepareStudentsData( PracticePlace place )
  {
    List<String> studs = new ArrayList<String>();
    for( PracticeWork work : place.getPracticeWorks() )
    {
    	Student st = StudentsService.getStudent( work.getStudentId() );
    	if(st!=null){
        	studs.add( st.getFullName() );
    	}
    }
    Collections.sort( studs );

    String[][] data = new String[studs.size()][];
    for( int i = 0; i < studs.size(); i++ )
    {
      data[i] = new String[3];
      data[i][0] = "" + ( i + 1 ) + ".";
      data[i][1] = studs.get( i );
      data[i][2] = SPECIALITY;
    }
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

}
