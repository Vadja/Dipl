package org.iit.dr.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class StringUtils
{
  public static String asString( List<Object> list, String delimiter )
  {
    String result = "";
    for( int i = 0; i < list.size(); i++ )
    {
      result += list.get( i ).toString();
      if( i != list.size() - 1 )
        result += delimiter;
    }
    return result;
  }

  public static List<Object> asList( String source, String delimiter )
  {
    List<Object> list = new ArrayList<Object>();
    for( String str : source.split( delimiter ) )
    {
      list.add( str );
    }
    return list;
  }
}
