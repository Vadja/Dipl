package org.iit.dr.utils;

import java.util.UUID;

/**
 * UUIDUtils.
 * 
 * @author Yuriy Karpovich
 */
public class UUIDUtils
{

  public static String getUUID()
  {

    return UUID.randomUUID().toString();
  }

  public static void main( String[] args )
  {

    for( int i = 0; i < 1000; i++ )
    {
      System.out.println( getUUID() );

    }
  }
}
