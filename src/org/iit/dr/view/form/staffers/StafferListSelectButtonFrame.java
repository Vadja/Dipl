package org.iit.dr.view.form.staffers;

import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.services.TeachersService;
import org.iit.dr.view.component.JButtonSelect;

/**
 * StafferListFrame.
 * 
 * @author Yuriy Karpovich
 */
public class StafferListSelectButtonFrame extends StafferListSelectFrame
{
  private static final long serialVersionUID = 1024601933896225562L;

  JButtonSelect selectedJButtonSelect;

  public StafferListSelectButtonFrame()
  {

  }

  public StafferListSelectButtonFrame( Boolean param )
  {

  }

  public boolean showFrame( Object parent, Object jButtonSelectObject )
  {
    if( jButtonSelectObject != null )
    {
      if( jButtonSelectObject instanceof JButtonSelect )
      {
        selectedJButtonSelect = ( JButtonSelect ) jButtonSelectObject;
      }
    }
    return super.showFrame( parent, jButtonSelectObject );
  }

  @Override
  public void applyButtonAction( String[] teacherId )
  {
    if( selectedJButtonSelect != null )
    {
      Teacher teacher = TeachersService.getTeacher( teacherId[0] );
      selectedJButtonSelect.setUserObject( teacher );
    }
  }

  @Override
  public void removeButtonAction( String[] teacherId )
  {
    if( selectedJButtonSelect != null )
    {
      selectedJButtonSelect.setUserObject( null );
    }
  }
}
