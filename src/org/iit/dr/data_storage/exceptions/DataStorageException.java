package org.iit.dr.data_storage.exceptions;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class DataStorageException extends RuntimeException
{
  private static final long serialVersionUID = 3460184792375306401L;

  public DataStorageException()
  {
    super();
  }

  public DataStorageException( String arg0, Throwable arg1 )
  {
    super( arg0, arg1 );
  }

  public DataStorageException( String arg0 )
  {
    super( arg0 );
  }

  public DataStorageException( Throwable arg0 )
  {
    super( arg0 );
  }
}
