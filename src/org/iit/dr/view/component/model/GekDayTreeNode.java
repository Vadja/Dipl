package org.iit.dr.view.component.model;

import org.iit.dr.data_model.GekDay;
import org.iit.dr.utils.DateUtils;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * GekDayTreeNode.
 * 
 * @author Yuriy Karpovich
 */
public class GekDayTreeNode extends DefaultMutableTreeNode
{

  public GekDayTreeNode()
  {
    super();
  }

  public GekDayTreeNode( Object userObject )
  {
    super( userObject );
  }

  public GekDayTreeNode( Object userObject, boolean allowsChildren )
  {
    super( userObject, allowsChildren );
  }

  @Override
  public String toString()
  {
    if( userObject == null )
    {
      return null;
    }
    else
    {

      if( userObject instanceof GekDay )
      {
        return DateUtils.dateToString( ( ( GekDay ) userObject ).getDate() )+" / "+( ( GekDay ) userObject ).getStartTime() + " - "+( ( GekDay ) userObject ).getEndTime();
      }
      else
      {
        return userObject.toString();
      }
    }
  }
}
