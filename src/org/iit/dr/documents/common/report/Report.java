package org.iit.dr.documents.common.report;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class Report
{
  private String filePath;

  private ReportType type;

  public Report()
  {

  }

  public Report( String filePath )
  {
    this.filePath = filePath;
  }

  public Report( String filePath, ReportType type )
  {
    this.filePath = filePath;
    this.type = type;
  }

  public String getFilePath()
  {
    return filePath;
  }

  public void setFilePath( String filePath )
  {
    this.filePath = filePath;
  }

  public ReportType getType()
  {
    return type;
  }

  public void setType( ReportType type )
  {
    this.type = type;
  }

}
