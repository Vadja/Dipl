package org.iit.dr.documents.common.generator.exceptions;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class ReportGeneratorException extends RuntimeException
{
  private static final long serialVersionUID = 8189432881871304833L;

  public ReportGeneratorException()
  {
    super();
  }

  public ReportGeneratorException( String arg0, Throwable arg1 )
  {
    super( arg0, arg1 );
  }

  public ReportGeneratorException( String arg0 )
  {
    super( arg0 );
  }

  public ReportGeneratorException( Throwable arg0 )
  {
    super( arg0 );
  }
}
