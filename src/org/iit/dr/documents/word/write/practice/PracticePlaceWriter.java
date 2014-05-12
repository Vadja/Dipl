package org.iit.dr.documents.word.write.practice;

import com.aspose.words.Body;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.Node;
import com.aspose.words.Paragraph;
import com.aspose.words.ParagraphAlignment;
import com.aspose.words.Section;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class PracticePlaceWriter
{
  private DocumentBuilder builder;

  private String flag;

  private static int SPACES_BEFORE_STUDENT = 5;

  public PracticePlaceWriter( DocumentBuilder builder, String flag )
  {
    this.builder = builder;
    this.flag = flag;
  }

  public void startWriter() throws Exception
  {
    Section section = builder.getDocument().getSections().get( 0 );
    Body body = section.getBody();
    for( int i = 0; i < body.getChildNodes().getCount(); i++ )
    {
      Node node = body.getChildNodes().get( i );
      if( node instanceof Paragraph )
      {
        Paragraph paragraph = ( Paragraph ) node;
        if( paragraph.getText().contains( flag ) )
        {
          builder.moveTo( body.getChildNodes().get( i - 1 ) );
          node.remove();
          return;
        }
      }
    }
  }

  public void writeCompanyName( String companyNameWithCity ) throws Exception
  {
    builder.getParagraphFormat().setAlignment( ParagraphAlignment.CENTER );
    builder.writeln( companyNameWithCity );
  }

  public void writeStudent( String studFIO, String group ) throws Exception
  {
    builder.getParagraphFormat().setAlignment( ParagraphAlignment.LEFT );
    builder.writeln( getStudentString( studFIO, group ) );
  }

  public void writeConsultant( String consultantString ) throws Exception
  {
    builder.getParagraphFormat().setAlignment( ParagraphAlignment.LEFT );
    builder.writeln( consultantString );
    builder.writeln();
  }

  private String getStudentString( String studFIO, String group )
  {
    String result = "";
    for( int i = 0; i < SPACES_BEFORE_STUDENT; i++ )
    {
      result += " ";
    }
    result += studFIO;
    result += '\t';
    result += group;
    return result;
  }
}
