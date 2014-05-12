package org.iit.dr.documents.word.write.common;

import com.aspose.words.Body;
import com.aspose.words.Cell;
import com.aspose.words.CellCollection;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.Node;
import com.aspose.words.Paragraph;
import com.aspose.words.Row;
import com.aspose.words.Section;
import com.aspose.words.Table;
import com.aspose.words.TableCollection;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class TableAppender
{
  private final DocumentBuilder builder;

  public TableAppender( DocumentBuilder builder )
  {
    this.builder = builder;
  }

  /**
   * Вставляет в таблицу, в которой в есть строка flag, данные data
   * 
   * @param flag
   * @param data
   * @throws Exception
   */
  public DocumentBuilder appendTableData( String flag, String[][] data ) throws Exception
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
          return builder;
        }
      }
    }
    return builder;
  }

  private void writeRowData( Row row, String[] data ) throws Exception
  {
    CellCollection cc = row.getCells();
    int i = 0;
    for( Cell cell : cc )
    {
      cell.getText();
      Paragraph p = cell.getFirstParagraph();
      p.removeAllChildren();
      builder.moveTo( p );
      builder.write( data[i] );
      i++;
    }
  }
}
