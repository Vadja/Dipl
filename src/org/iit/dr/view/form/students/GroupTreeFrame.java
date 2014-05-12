package org.iit.dr.view.form.students;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.iit.dr.data_model.OrganizationUnitWrapper;
import org.iit.dr.data_model.unit.OrganizationUnit;
import org.iit.dr.services.OrganizationUnitService;
import org.iit.dr.view.component.JButtonSelect;
import org.iit.dr.view.component.JInternalFrameExt;

/**
 * GroupTreeFrame.
 * 
 * @author Yuriy Karpovich
 */
public class GroupTreeFrame extends JInternalFrameExt<Object>
{

  JTree tree;

  DefaultTreeModel treeModel;

  DefaultMutableTreeNode root;

  JButtonSelect selectedJButtonSelect;

  public GroupTreeFrame( Boolean selectDialog ) throws HeadlessException
  {

    if( selectDialog )
    {
      addComponents();
    }
  }

  public GroupTreeFrame()
  {

  }

  @Override
  public boolean showFrame( Object parent, Object jButtonSelectObject )
  {
	  updateData();
    if( jButtonSelectObject != null )
    {

      if( jButtonSelectObject instanceof JButtonSelect )
      {

        selectedJButtonSelect = ( JButtonSelect ) jButtonSelectObject;
      }
    }

    setVisible( true );
    return true;
  }

  @Override
  protected void init()
  {

    setTitle( "Справочник: Структура подразделений" );

    setMinimumSize( new Dimension( 500, 500 ) );
  }

  @Override
  protected void generateComponents()
  {

    tree = new JTree();
    tree.setExpandsSelectedPaths( true );

    getContentPane().add( tree, BorderLayout.CENTER );

    tree.addMouseListener( new MouseListener()
    {

      public void mouseClicked( MouseEvent e )
      {

        if( e.getButton() == MouseEvent.BUTTON3 )
        {

          DefaultMutableTreeNode activeNode = getActiveTreeNode();

          if( activeNode != null )
          {
            JPopupMenu popup = new JPopupMenu();
            JMenuItem item;

            item = new JMenuItem( "Добавить позицию" );

            item.addActionListener( new ActionListener()
            {
              public void actionPerformed( ActionEvent e )
              {

                DefaultMutableTreeNode activeNode = getActiveTreeNode();

                if( !root.equals( activeNode ) )
                {
                  OrganizationUnit parent = ( OrganizationUnit ) activeNode.getUserObject();
                  switch( parent.getType() )
                  {
                    case FACULTY:
                      CourseFrame courseFrame = new CourseFrame();
                      courseFrame.showFrame( GroupTreeFrame.this, new OrganizationUnitWrapper( null, parent.getId() ) );
                      break;
                    case STREAM:
                      GroupFrame groupFrame = new GroupFrame();
                      groupFrame.showFrame( GroupTreeFrame.this, new OrganizationUnitWrapper( null, parent.getId() ) );
                      break;
                    case GROUP:
                      SubGroupFrame subFrame = new SubGroupFrame();
                      subFrame.showFrame( GroupTreeFrame.this, new OrganizationUnitWrapper( null, parent.getId() ) );
                      break;
                  }
                  /*
                   * if( ( ( OrganizationUnit ) activeNode.getUserObject() ).getType() == UnitType.STREAM ) { GroupFrame
                   * groupFrame = new GroupFrame(); groupFrame.showFrame( GroupTreeFrame.this, new
                   * OrganizationUnitWrapper( null, ( ( OrganizationUnit ) activeNode.getUserObject() ).getId() ) ); }
                   * else { SubGroupFrame subFrame = new SubGroupFrame(); subFrame.showFrame( GroupTreeFrame.this, new
                   * OrganizationUnitWrapper( null, ( ( OrganizationUnit ) activeNode.getUserObject() ).getId() ) ); }
                   */
                }
                else
                {
                  FacultyFrame facultyFrame = new FacultyFrame();
                  facultyFrame.showFrame( GroupTreeFrame.this, new OrganizationUnitWrapper( null, null ) );
//                   CourseFrame courseFrame = new CourseFrame();
//                   courseFrame.showFrame( GroupTreeFrame.this, new OrganizationUnitWrapper( null, null ) );
                }
              }
            } );

            popup.add( item );

            item = new JMenuItem( "Редактировать позицию" );

            item.addActionListener( new ActionListener()
            {
              public void actionPerformed( ActionEvent e )
              {

                DefaultMutableTreeNode activeNode = getActiveTreeNode();

                if( !root.equals( activeNode ) )
                {
                  switch( ( ( OrganizationUnit ) activeNode.getUserObject() ).getType() )
                  {
                    case STREAM:
                      CourseFrame courseFrame = new CourseFrame();
                      courseFrame.showFrame( GroupTreeFrame.this, new OrganizationUnitWrapper(
                        ( OrganizationUnit ) activeNode.getUserObject(), null ) );
                      break;
                    case GROUP:
                      GroupFrame groupFrame = new GroupFrame();
                      groupFrame.showFrame( GroupTreeFrame.this, new OrganizationUnitWrapper(
                        ( OrganizationUnit ) activeNode.getUserObject(), null ) );
                      break;
                    case SUBGROUP:
                      SubGroupFrame frame = new SubGroupFrame();
                      frame.showFrame( GroupTreeFrame.this, new OrganizationUnitWrapper(
                        ( OrganizationUnit ) activeNode.getUserObject(), null ) );
                      break;
                    case FACULTY:
                      FacultyFrame facultyFrame = new FacultyFrame();
                      facultyFrame.showFrame( GroupTreeFrame.this, new OrganizationUnitWrapper(
                        ( OrganizationUnit ) activeNode.getUserObject(), null ) );
                      break;
                  }
                }
              }
            } );

            popup.add( item );

            item = new JMenuItem( "Удалить позицию" );

            item.addActionListener( new ActionListener()
            {
              public void actionPerformed( ActionEvent e )
              {

                DefaultMutableTreeNode activeNode = getActiveTreeNode();

                if( !root.equals( activeNode ) )
                {

                  OrganizationUnitService.removeOrganizationUnit( ( ( OrganizationUnit ) activeNode.getUserObject() )
                    .getId() );
                  updateData();
                }
              }
            } );

            popup.add( item );

            popup.show( GroupTreeFrame.this, e.getX(), e.getY() );

          }
        }

      }

      public void mousePressed( MouseEvent e )
      {
      }

      public void mouseReleased( MouseEvent e )
      {
      }

      public void mouseEntered( MouseEvent e )
      {
      }

      public void mouseExited( MouseEvent e )
      {
      }
    } );

    updateData();
  }

  private void addComponents()
  {

    JButton applyButton = new JButton( "Применить" );
    applyButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        if( selectedJButtonSelect != null )
        {

          if( getActiveTreeNode() != null )
          {

            selectedJButtonSelect.setUserObject( getActiveTreeNode().getUserObject() );
            setVisible( false );
          }
        }
      }
    } );

    JButton removeButton = new JButton( "Удалить" );
    removeButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        if( selectedJButtonSelect != null )
        {

          selectedJButtonSelect.setUserObject( null );
        }

        setVisible( false );
      }
    } );

    JButton cancelButton = new JButton( "Отмена" );
    cancelButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        setVisible( false );
      }
    } );

    getContentPane().add( createNorthButtonPane( 10, applyButton, removeButton, cancelButton ), BorderLayout.SOUTH );
  }

  public void updateData()
  {
    java.util.List<OrganizationUnit> organizationUnitList = OrganizationUnitService.getOrganizationUnitList();

    root = new DefaultMutableTreeNode( "БГУИР" );

    treeModel = new DefaultTreeModel( root );

    fillNodeList( root );

    tree.setModel( treeModel );
  }

  private void fillNodeList( DefaultMutableTreeNode node )
  {

    String rootId = null;

    if( !root.equals( node ) )
    {

      OrganizationUnit unit = ( OrganizationUnit ) node.getUserObject();

      rootId = unit.getId();
    }

    List<OrganizationUnit> organizationUnitList = OrganizationUnitService.getOrganizationUnitListByRoot( rootId );

    for( OrganizationUnit organizationUnit : organizationUnitList )
    {

      DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode( organizationUnit );

      treeModel.insertNodeInto( treeNode, node, 0 );

      fillNodeList( treeNode );
    }
  }

  private DefaultMutableTreeNode getActiveTreeNode()
  {

    TreePath treePath = tree.getSelectionPath();

    if( treePath != null )
    {

      return ( DefaultMutableTreeNode ) treePath.getLastPathComponent();
    }

    return null;
  }

}
