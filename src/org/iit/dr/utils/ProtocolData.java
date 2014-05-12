/**
 * ${NAME}.
 * <p/>
 * $Id: ${NAME}.java, v 1.0 23.06.2010 13:01:50 ykarpovich Exp $ Created on 23.06.2010 13:01:50
 * 
 */
package org.iit.dr.utils;

import java.util.Properties;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class ProtocolData
{

  public static final String COMP_PRESENT = "compPresent";

  public static final String DISTRIBUTION = "distribution";

  public static final String BEST_WORK = "bestWork";

  public static final String MAGISTRACY = "magistracy";

  public static final String PRINTED_PUBLICATION = "printedPublication";

  private static ProtocolData ourInstance = new ProtocolData();

  Properties properties = new Properties();

  public static ProtocolData getInstance()
  {
    return ourInstance;
  }

  private ProtocolData()
  {

    loadData();
  }

  private void loadData()
  {

    Scanner scanner = null;
    try
    {
      scanner = new Scanner( new File( "res/protocol.properties" ), "UTF8" );
      while( scanner.hasNextLine() )
      {
        String data = scanner.nextLine();
        String[] dataArray = data.split( "=" );
        if( dataArray.length > 1 && dataArray[0] != null && dataArray[1] != null )
        {

          properties.put( dataArray[0].trim(), dataArray[1].trim() );
        }
      }
    }
    catch( Exception e )
    {
      // ignore
    }
    finally
    {

      if( scanner != null )
      {
        scanner.close();
      }
    }
  }

  public String getProperty( String key )
  {

    String value = properties.getProperty( key );

    return value != null ? value : "";
  }

  public static void main( String[] args )
  {

    System.out.println( ProtocolData.getInstance().getProperty( "distribution" ) );
  }
}
