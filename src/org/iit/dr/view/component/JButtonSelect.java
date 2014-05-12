package org.iit.dr.view.component;

import org.iit.dr.data_model.unit.OrganizationUnit;

import javax.swing.*;

/**
 * JButtonSelect.
 * 
 * @author Yuriy Karpovich
 */
public class JButtonSelect extends JButton
{

  private Object userObject;

  public JButtonSelect()
  {

    setTextInner( null );
  }

  public JButtonSelect( Object userObject )
  {
    this.userObject = userObject;

    setTextInner( userObject );
  }

  public Object getUserObject()
  {
    return userObject;
  }

  public void setUserObject( Object userObject )
  {
    this.userObject = userObject;
    setTextInner( userObject );
  }

  private void setTextInner( Object userObject )
  {

    if( userObject == null )
    {

      setText( "Выбрать" );
    }
    else
    {

      setText( userObject.toString() );
    }
  }
}
