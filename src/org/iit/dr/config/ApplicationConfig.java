package org.iit.dr.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.iit.dr.config.exceptions.ApplicationConfigurationException;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class ApplicationConfig
{
  public static final String CONFIG_FILE_NAME = "config/config.properties";

  // public static final String MS_WORD_PATH_KEY = "msword.path";

  // public static final String MS_EXCEL_PATH_KEY = "msexcel.path";

  private static ApplicationConfig configInstance;

  private Properties properties;

  private ApplicationConfig()
  {
    properties = new Properties();
    try
    {
      properties.load( new FileInputStream( CONFIG_FILE_NAME ) );
    }
    catch( FileNotFoundException e )
    {
      throw new ApplicationConfigurationException( "Configuration file '" + CONFIG_FILE_NAME + "' not found", e );
    }
    catch( IOException e )
    {
      throw new ApplicationConfigurationException( "Error on loading configuration file '" + CONFIG_FILE_NAME + "'" );
    }
  }

  public static ApplicationConfig getInstance()
  {
    if( configInstance == null )
    {
      configInstance = new ApplicationConfig();
    }
    return configInstance;
  }

  public String getProperty( String key )
  {
    String property = properties.getProperty( key );
    if( property == null )
    {
      throw new ApplicationConfigurationException( "Property '" + key + "' not found" );
    }
    return property;
  }

  public String getProperty( String key, String defaultValue )
  {
    return properties.getProperty( key, defaultValue );
  }

  public void setProperty( String key, String value )
  {
    properties.setProperty( key, value );
    try
    {
      properties.store( new FileOutputStream( CONFIG_FILE_NAME ), "Department reports application config" );
    }
    catch( FileNotFoundException e )
    {
      throw new ApplicationConfigurationException( "Configuration file '" + CONFIG_FILE_NAME + "' not found", e );
    }
    catch( IOException e )
    {
      throw new ApplicationConfigurationException( "Error on saving configuration file '" + CONFIG_FILE_NAME + "'" );
    }
  }

  // public void setViewProgram( ReportFormat format, String path )
  // {
  //
  // }

  // public static void main( String[] args )
  // {
  // System.out.println( ApplicationConfig.getInstance().getProperty( MS_EXCEL_PATH_KEY ) );
  // }
}
