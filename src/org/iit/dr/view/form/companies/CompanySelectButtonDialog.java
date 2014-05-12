package org.iit.dr.view.form.companies;

import org.iit.dr.services.CompaniesService;
import org.iit.dr.view.component.JButtonSelect;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class CompanySelectButtonDialog extends CompanySelectFrame
{
  private static final long serialVersionUID = 5548022203119761791L;

  protected JButtonSelect selectedJButtonSelect;

  @Override
  public boolean showFrame( Object parent, Object object )
  {
    if( object != null )
    {
      if( object instanceof JButtonSelect )
      {
        selectedJButtonSelect = ( JButtonSelect ) object;
      }
    }
    return super.showFrame( parent, object );
  }

  @Override
  protected void applyButtonAction( String[] companyId )
  {
    if( selectedJButtonSelect != null && companyId != null )
    {
      selectedJButtonSelect.setUserObject( CompaniesService.getCompany( companyId[0] ) );
    }
  }

  @Override
  protected void removeButtonAction( String[] selectedCompanyId )
  {
    if( selectedJButtonSelect != null )
    {
      selectedJButtonSelect.setUserObject( null );
    }
  }
}
