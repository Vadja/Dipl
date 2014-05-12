package org.iit.dr.view.component;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.iit.dr.data_model.unit.OrganizationUnit;
import org.iit.dr.services.OrganizationUnitService;
import org.iit.dr.view.model.table.students.StudentsTableModel;

/**
 * GroupTreeSimplePanel.
 * 
 * @author Yuriy Karpovich
 */
public class GroupTreeSimplePanel extends JPanel
{

  JTree tree;

  DefaultTreeModel treeModel;

  DefaultMutableTreeNode root;

  StudentsTableModel studentsTableModel;

  public GroupTreeSimplePanel( StudentsTableModel studentsTableModel )
  {
    this.studentsTableModel = studentsTableModel;
    init();
    generateComponents();
  }

  // public boolean showFrame(Object parent, Object jButtonSelectObject) {
  //
  // setVisible(true);
  // return true;
  // }

  protected void init()
  {
    setMinimumSize( new Dimension( 160, 200 ) );
  }

  protected void generateComponents()
  {
    tree = new JTree();
    tree.setExpandsSelectedPaths( true );
    add( tree, BorderLayout.CENTER );
    tree.addMouseListener( new MouseAdapter()
    {
      public void mouseClicked( MouseEvent e )
      {
        if( e.getButton() == MouseEvent.BUTTON1 )
        {
          DefaultMutableTreeNode activeNode = getActiveTreeNode();
          if( activeNode != null )
          {
            if( root.equals( activeNode ) )
            {
              studentsTableModel.updateData( null );
            }
            else
            {
              String organizationUnitId = ( ( OrganizationUnit ) activeNode.getUserObject() ).getId();
              List<String> subOUIdList =
                OrganizationUnitService.getSubOrganizationUnitListByParent( organizationUnitId );
              studentsTableModel.updateData( subOUIdList );
            }
          }
        }
      }
    } );
    updateData();
  }

  public void updateData()
  {
    List<OrganizationUnit> organizationUnitList = OrganizationUnitService.getOrganizationUnitList();
    root = new DefaultMutableTreeNode( "БГУИР/ФИТиУ/ИИТ" );
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