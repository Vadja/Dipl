package org.iit.dr.view.form.practice;

import org.iit.dr.data_model.PracticeType;
import org.iit.dr.services.PracticeService;
import org.iit.dr.view.form.staffers.StafferListSelectFrame;
import org.iit.dr.view.model.table.practice.PracticeConsultantTableModel;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class PracticeConsultantSelectFrame extends StafferListSelectFrame
{
  private static final long serialVersionUID = 762575769898803000L;

  private PracticeType practiceType;

  private PracticeConsultantTableModel practiceConsultantsTableModel;

  public PracticeConsultantSelectFrame( PracticeType practiceType )
  {
    this.practiceType = practiceType;
  }

  @Override
  public boolean showFrame( Object parent, Object object )
  {
    if( object != null )
    {
      if( object instanceof PracticeConsultantTableModel )
      {
        practiceConsultantsTableModel = ( PracticeConsultantTableModel ) object;
        practiceConsultantsTableModel.updateData();
      }
    }
    return super.showFrame( parent, object );
  }

  @Override
  public void applyButtonAction( String[] teacherId )
  {
    if( practiceConsultantsTableModel != null && teacherId != null )
    {
      PracticeService.addPracticeConsultant( teacherId, practiceType );
      practiceConsultantsTableModel.updateData();
    }
  }

  @Override
  public void removeButtonAction( String[] teacherId )
  {
  }
}
