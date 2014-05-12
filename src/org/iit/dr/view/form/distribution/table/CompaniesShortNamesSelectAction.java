package org.iit.dr.view.form.distribution.table;

import org.iit.dr.services.CompaniesService;
import org.iit.dr.view.component.combo_box_ext.AlternativeSelectAction;
import org.iit.dr.view.form.companies.CompanySelectFrameExt;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class CompaniesShortNamesSelectAction implements AlternativeSelectAction
{
  public Object[] selectAlternatives( Object parent )
  {
    CompanySelectFrameExt selectDialog = new CompanySelectFrameExt();
    selectDialog.showFrame( null, null );
    String[] compIds = selectDialog.getSelectedCompaniesId();
    if( compIds != null )
    {
      String[] names = new String[compIds.length];
      for( int i = 0; i < compIds.length; i++ )
      {
        names[i] = CompaniesService.getCompany( compIds[i] ).getShortName();
      }
      return names;
    }
    return null;
  }

}
