package org.iit.dr.view.form.practice;

import org.iit.dr.data_model.PracticePlace;
import org.iit.dr.data_model.PracticeType;
import org.iit.dr.services.PracticeService;
import org.iit.dr.view.form.companies.CompanySelectFrame;
import org.iit.dr.view.model.table.practice.PracticePlacesTableModel;

public class PracticePlaceSelectFrame extends CompanySelectFrame
{
  private static final long serialVersionUID = -2746102308809802788L;

  private PracticeType practiceType;

  protected PracticePlacesTableModel practicePlacesTableModel;

  public PracticePlaceSelectFrame( PracticeType type )
  {
    practiceType = type;
  }

  @Override
  public boolean showFrame( Object parent, Object object )
  {
    if( object != null )
    {
      if( object instanceof PracticePlacesTableModel )
      {
        practicePlacesTableModel = ( PracticePlacesTableModel ) object;
        practicePlacesTableModel.updateData();
      }
    }
    return super.showFrame( parent, object );
  }

  @Override
  protected void applyButtonAction( String[] companyId )
  {
    if( practicePlacesTableModel != null && companyId != null )
    {
      for( String id : companyId )
      {
        PracticePlace place = new PracticePlace( id );
        PracticeService.addPracticePlace( place, practiceType );

      }
      practicePlacesTableModel.updateData();
    }
  }

  @Override
  protected void removeButtonAction( String[] selectedCompanyId )
  {
  }

}
