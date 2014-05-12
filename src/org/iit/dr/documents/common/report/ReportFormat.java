package org.iit.dr.documents.common.report;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public enum ReportFormat
{
  WORD( "msword.path" ), EXCEL( "msexcel.path" ), CSV( "msexcel.path" );

  private String propertyKey;

  private ReportFormat( String propertyKey )
  {
    this.propertyKey = propertyKey;
  }

  public String getPropertyKey()
  {
    return propertyKey;
  }

}
