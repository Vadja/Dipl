package org.iit.dr.documents.common.viewer.exceptions;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class ReportViewerException extends RuntimeException
{
  private static final long serialVersionUID = 3599805839570679030L;

  public ReportViewerException()
  {
    super();
  }

  public ReportViewerException( String arg0, Throwable arg1 )
  {
    super( arg0, arg1 );
  }

  public ReportViewerException( String arg0 )
  {
    super( arg0 );
  }

  public ReportViewerException( Throwable arg0 )
  {
    super( arg0 );
  }

}
