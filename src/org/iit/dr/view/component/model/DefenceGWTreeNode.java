package org.iit.dr.view.component.model;

import javax.swing.tree.DefaultMutableTreeNode;

import org.iit.dr.data_model.DefenceGraduateWork;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.services.StudentsService;

/**
 * DefenceGWTreeNode.
 * 
 * @author Yuriy Karpovich
 */
public class DefenceGWTreeNode extends DefaultMutableTreeNode
{

  public DefenceGWTreeNode()
  {
    super();
  }

  public DefenceGWTreeNode( Object userObject )
  {
    super( userObject );
  }

  public DefenceGWTreeNode( Object userObject, boolean allowsChildren )
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

      if( userObject instanceof DefenceGraduateWork )
      {

        DefenceGraduateWork defenceGraduateWork = ( DefenceGraduateWork ) userObject;

        Student student = StudentsService.getStudentByDefenceGraduateWorkId( defenceGraduateWork.getId() );

        if( student != null )
        {

          return student.getShortName();
        }

      }
      return userObject.toString();
    }
  }
}