package org.iit.dr.data_model.test;

import org.iit.dr.data_model.role.Student;

/**
 * DPData.
 * 
 * @author Yuriy Karpovich
 */
public class DPData
{

  private Student student;

  private String theme;

  private String manager;

  private String managerData;

  private String consultant;

  private String consultantData;

  public Student getStudent()
  {
    return student;
  }

  public void setStudent( Student student )
  {
    this.student = student;
  }

  public String getManager()
  {
    return manager;
  }

  public void setManager( String manager )
  {
    this.manager = manager;
  }

  public String getManagerData()
  {
    return managerData;
  }

  public void setManagerData( String managerData )
  {
    this.managerData = managerData;
  }

  public String getConsultant()
  {
    return consultant;
  }

  public void setConsultant( String consultant )
  {
    this.consultant = consultant;
  }

  public String getConsultantData()
  {
    return consultantData;
  }

  public void setConsultantData( String consultantData )
  {
    this.consultantData = consultantData;
  }

  public String getTheme()
  {
    return theme;
  }

  public void setTheme( String theme )
  {
    this.theme = theme;
  }

  @Override
  public String toString()
  {
    return "DPData{" + "student=" + student + ", theme='" + theme + '\'' + ", manager='" + manager + '\''
      + ", managerData='" + managerData + '\'' + ", consultant='" + consultant + '\'' + ", consultantData='"
      + consultantData + '\'' + '}';
  }
}
