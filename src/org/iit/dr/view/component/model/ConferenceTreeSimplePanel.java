package org.iit.dr.view.component.model;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.iit.dr.data_model.Conference;
import org.iit.dr.data_storage.DataStorage;
import org.iit.dr.services.ConferenceService;
import org.iit.dr.view.form.conferences.ConfEditFrame;
import org.iit.dr.view.model.table.conferences.ConferenceTableModel;

/**
 * Created by IntelliJ IDEA. User: Admin Date: 15.12.2010 Time: 1:20:36 To change this template use File | Settings |
 * File Templates.
 */
public class ConferenceTreeSimplePanel extends JPanel
{

  JTree tree;

  DefaultTreeModel treeModel;

  DefaultMutableTreeNode root;

  ConferenceTableModel confTableModel;

  public ConferenceTreeSimplePanel( ConferenceTableModel confTableModel )
  {

    this.confTableModel = confTableModel;

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

    setMinimumSize( new Dimension( 260, 200 ) );
  }

  protected void generateComponents()
  {

    tree = new JTree();
    tree.setExpandsSelectedPaths( true );

    add( tree, BorderLayout.CENTER );

    tree.addMouseListener( new MouseListener()
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

              confTableModel.updateData( null, 0 );
            }
            else
            {

              String subConfIdList = "";
              java.util.List<Conference> confList = DataStorage.getInstance().getConferenceList();
              for( Conference conf : confList )
              {
                String title = ( ( String ) activeNode.getUserObject() );
                if( title.equalsIgnoreCase( conf.getTitle() ) )
                {
                  subConfIdList = subConfIdList + ConferenceService.getConference( conf.getId() ).getId();
                }
              }
              confTableModel.updateData( subConfIdList, 0 );
            }
          }
        }
        else if( e.getButton() == MouseEvent.BUTTON3 )
        {

          DefaultMutableTreeNode activeNode = getActiveTreeNode();

          if( activeNode != null )
          {
            JPopupMenu popup = new JPopupMenu();

            JMenuItem item = new JMenuItem( "Удалить позицию" );

            item.addActionListener( new ActionListener()
            {
              public void actionPerformed( ActionEvent e )
              {

                DefaultMutableTreeNode activeNode = getActiveTreeNode();

                if( !root.equals( activeNode ) )
                {

                  ConferenceService.removeConference( ConferenceService.getConferenceByTitle(
                    ( String ) activeNode.getUserObject() ).getId() );
                  updateData();
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

                ConfEditFrame confFrame = new ConfEditFrame();
                if( !root.equals( activeNode ) )
                {
                  // найти коференцию
                  confFrame.showFrame( ConferenceTreeSimplePanel.this,
                    ConferenceService.getConferenceByTitle( ( String ) activeNode.getUserObject() ) );
                }
              }
            } );

            popup.add( item );

            item = new JMenuItem( "Добавить позицию" );

            item.addActionListener( new ActionListener()
            {
              public void actionPerformed( ActionEvent e )
              {

                DefaultMutableTreeNode activeNode = getActiveTreeNode();

                ConfEditFrame confFrame = new ConfEditFrame();

                confFrame.showFrame( ConferenceTreeSimplePanel.this, new Conference() );
                // groupFrame.showFrame(GroupTreeFrame.this, new OrganizationUnitWrapper(null, ((OrganizationUnit)
                // activeNode.getUserObject()).getId()));
              }
            } );

            popup.add( item );

            popup.show( ConferenceTreeSimplePanel.this, e.getX(), e.getY() );

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

  public void updateData()
  {
    // java.util.List<Conference> confList =
    // DataStorage.getInstance().getConferenceList();

    root = new DefaultMutableTreeNode( "Конференции" );

    treeModel = new DefaultTreeModel( root );

    fillNodeList( root );

    tree.setModel( treeModel );
  }

  private void fillNodeList( DefaultMutableTreeNode node )
  {

    String rootId = null;

    if( !root.equals( node ) )
    {

      Conference conf = ( Conference ) node.getUserObject();

      rootId = conf.getId();
    }

    java.util.List<Conference> confList = DataStorage.getInstance().getConferenceList();

    for( Conference conf : confList )
    {

      DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode( conf.getTitle() );

      treeModel.insertNodeInto( treeNode, node, 0 );

      // fillNodeList(treeNode);
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
