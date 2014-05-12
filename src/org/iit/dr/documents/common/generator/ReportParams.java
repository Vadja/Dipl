package org.iit.dr.documents.common.generator;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class ReportParams
{
  // keys
  public static final String OUTPUT_FILE_PATH = "OUTPUT_FILE_PATH";

  private Map<String, Object> params;

  public ReportParams()
  {
    params = new HashMap<String, Object>();
  }

  public ReportParams( String otputFilePath )
  {
    this();
    setOtputFilePath( otputFilePath );
  }

  public void set( String key, Object value )
  {
    params.put( key, value );
  }

  public Object get( String key )
  {
    return params.get( key );
  }

  public void setOtputFilePath( String filePath )
  {
    set( OUTPUT_FILE_PATH, filePath );
  }

  public String getOtputFilePath()
  {
    return ( String ) get( OUTPUT_FILE_PATH );
  }
}
