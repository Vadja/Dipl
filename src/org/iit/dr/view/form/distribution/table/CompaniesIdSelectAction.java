package org.iit.dr.view.form.distribution.table;

import org.iit.dr.view.component.combo_box_ext.AlternativeSelectAction;
import org.iit.dr.view.form.companies.CompanySelectFrameExt;

public class CompaniesIdSelectAction implements AlternativeSelectAction
{
  public Object[] selectAlternatives( Object parent )
  {
    CompanySelectFrameExt selectDialog = new CompanySelectFrameExt();
    selectDialog.showFrame( null, null );
    String[] compIds = selectDialog.getSelectedCompaniesId();
    return compIds;
  }
}
