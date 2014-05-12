package org.iit.dr.documents.word.read.graduate_work;

import com.aspose.words.Body;
import com.aspose.words.Document;
import com.aspose.words.Section;
import com.aspose.words.TableCollection;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class GraduateWorkThemeReader
{
  private String group;

  private String studentFullName;

  private String theme;

  private String managerFullName;

  private String company;

  private String postIndex;

  private String position;

  public void processDocument( String filePath ) throws Exception
  {
    Document doc = new Document( filePath );
    Section section = doc.getSections().get( 0 );
    Body body = section.getBody();
    TableCollection tc = body.getTables();

    group = tc.get( 0 ).toTxt().trim();
    studentFullName = tc.get( 1 ).toTxt().trim();
    studentFullName += " " + tc.get( 2 ).toTxt().trim();
    theme = tc.get( 5 ).toTxt().trim();
    managerFullName = tc.get( 7 ).toTxt().trim();
    managerFullName += " " + tc.get( 8 ).toTxt().trim();
    company = tc.get( 9 ).toTxt().trim();
    postIndex = tc.get( 10 ).toTxt().trim();
    position = tc.get( 12 ).toTxt().trim();

    // Iterator<Table> it = tc.iterator();
    // int i = 0;
    // while( it.hasNext() )
    // {
    // System.out.println( ( i++ ) + ": " + it.next().toTxt().trim() );
    // }
  }

  // public static void main( String[] args ) throws Exception
  // {
  // new GraduateWorkThemeReader().processDocument( "D:\\Заявление на утверждение темы ДП.doc" );
  // }

  public String getGroup()
  {
    return group;
  }

  public String getStudentFullName()
  {
    return studentFullName;
  }

  public String getTheme()
  {
    return theme;
  }

  public String getManagerFullName()
  {
    return managerFullName;
  }

  public String getCompany()
  {
    return company;
  }

  public String getPostIndex()
  {
    return postIndex;
  }

  public String getPosition()
  {
    return position;
  }

}
