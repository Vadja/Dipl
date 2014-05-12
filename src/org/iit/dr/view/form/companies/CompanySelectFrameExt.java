package org.iit.dr.view.form.companies;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class CompanySelectFrameExt extends CompanySelectFrame
{
  private static final long serialVersionUID = 4978684485272109129L;

  private String[] selectedCompaniesId;

  @Override
  protected void applyButtonAction( String[] selectedCompanyId )
  {
    this.selectedCompaniesId = selectedCompanyId;
  }

  @Override
  protected void removeButtonAction( String[] selectedCompanyId )
  {
  }

  public String[] getSelectedCompaniesId()
  {
    return selectedCompaniesId;
  }
}
