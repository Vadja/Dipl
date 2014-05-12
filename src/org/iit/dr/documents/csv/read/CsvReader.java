package org.iit.dr.documents.csv.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader
{
  private final String fileName;

  private final List<String> titles = null;

  public CsvReader( String aFileName )
  {
    this.fileName = aFileName;
  }

  public String[] getTitles()
  {
    try
    {
      BufferedReader input = new BufferedReader( new FileReader( fileName ) );
      String titlesStr = input.readLine();
      input.close();
      titlesStr = titlesStr.replaceAll( "[\"\t\n\f\r]", "" );
      String[] titles = titlesStr.split( ";" );
      return titles;
    }

    catch( IOException ex )
    {
      System.out.println( "Ошибка чтения файла " + fileName + " в CsvReader" );
    }

    return null;
  }

  public List<DataLine> getData()
  {
    List<DataLine> data = new ArrayList<DataLine>();
    try
    {
      BufferedReader input = new BufferedReader( new FileReader( fileName ) );
      String titlesStr = input.readLine();
      String line = null;
      DataLine item;
      while( ( line = input.readLine() ) != null )
      {
        line = line.replaceAll( "[\"\t\n\f\r]", "" );
        item = new DataLine( line.split( ";" ) );
        data.add( item );
      }
      input.close();
    }

    catch( IOException ex )
    {
      System.out.println( "Ошибка чтения файла " + fileName + " в CsvReader" );
    }
    return data;
  }
}
