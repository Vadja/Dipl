package org.iit.dr.documents.word.write.common;

import com.aspose.words.Body;
import com.aspose.words.Cell;
import com.aspose.words.CellCollection;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.Node;
import com.aspose.words.Paragraph;
import com.aspose.words.ParagraphAlignment;
import com.aspose.words.Row;
import com.aspose.words.Section;
import com.aspose.words.Table;
import com.aspose.words.TableCollection;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class WordDocBuilder
{
  private final DocumentBuilder builder;

  public WordDocBuilder( String fileName ) throws Exception
  {
	  Document doc = new Document( fileName );
    builder = new DocumentBuilder( doc );
  }
  
  public WordDocBuilder( Document document ) throws Exception
  {
    builder = new DocumentBuilder( document );
  }
  
  public WordDocBuilder( DocumentBuilder builder ) throws Exception
  {
    this.builder = builder;
  }

  public void saveDoc( String outputFileName ) throws Exception
  {
    getDocument().save( outputFileName );
  }

  public Document getDocument()
  {
    return builder.getDocument();
  }

  public Node moveToTag( String tag ) throws Exception
  {
    Section section = builder.getDocument().getSections().get( 0 );
    Body body = section.getBody();
    for( int i = 0; i < body.getChildNodes().getCount(); i++ )
    {
      Node node = body.getChildNodes().get( i );
      if( node instanceof Paragraph )
      {
        Paragraph paragraph = ( Paragraph ) node;
        if( paragraph.getText().contains( tag ) )
        {
          builder.moveTo( body.getChildNodes().get( i - 1 ) );
          return node;
        }
      }
    }
    return null;
  }

  public void insertValue( String tag, String value ) throws Exception
  {
    builder.getDocument().getRange().replace( tag, value, false, true );
    // moveToTag( tag ).remove();
    // builder.write( value );
  }

  public void insertList( String tag, String[] list ) throws Exception
  {
	  if(moveToTag( tag )!=null){
		    moveToTag( tag ).remove();
	  }
    builder.writeln();
    builder.getParagraphFormat().setAlignment( ParagraphAlignment.CENTER );
    for( String listItem : list )
    {
      builder.writeln( listItem );
    }
  }

  /**
   * Вставляет в таблицу, в которой в есть строка flag, данные data
   * 
   * @param flag
   * @param data
   * @throws Exception
   */
  public void appendTableData( String flag, String[][] data ) throws Exception
  {
    Section section = builder.getDocument().getSections().get( 0 );
    Body body = section.getBody();
    TableCollection tc = body.getTables();
    for( Table table : tc )
    {
      for( Row lastRow : table.getRows() )
      {
        if( lastRow.toTxt().contains( flag ) )
        {
          if( data.length > 0 )
          {
            int i = 0;
            for( i = 0; i < data.length - 1; i++ )
            {
              lastRow = table.getLastRow();
              writeRowData( lastRow, data[i] );
              Node insRow = lastRow.deepClone( true );
              table.appendChild( insRow );
              table.insertAfter( lastRow, insRow );

            }
            lastRow = table.getLastRow();
            writeRowData( lastRow, data[i] );
          }
        }
      }
    }
  }

  private void writeRowData( Row row, String[] data ) throws Exception
  {
    CellCollection cc = row.getCells();
    int i = 0;
    for( Cell cell : cc )
    {
      Paragraph p = cell.getFirstParagraph();
      p.removeAllChildren();
      builder.moveTo( p );
      builder.write( data[i] );
      i++;
    }
  }

}
