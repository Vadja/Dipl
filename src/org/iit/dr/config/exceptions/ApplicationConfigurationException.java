package org.iit.dr.config.exceptions;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class ApplicationConfigurationException extends RuntimeException
{
  private static final long serialVersionUID = 7602725718571519389L;

  public ApplicationConfigurationException()
  {
    super();
  }

  public ApplicationConfigurationException( String arg0, Throwable arg1 )
  {
    super( arg0, arg1 );
  }

  public ApplicationConfigurationException( String arg0 )
  {
    super( arg0 );
  }

  public ApplicationConfigurationException( Throwable arg0 )
  {
    super( arg0 );
  }

}
