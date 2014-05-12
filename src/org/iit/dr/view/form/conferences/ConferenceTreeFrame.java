package org.iit.dr.view.form.conferences;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.iit.dr.data_model.Conference;
import org.iit.dr.data_storage.DataStorage;
import org.iit.dr.services.ConferenceService;
import org.iit.dr.view.component.JButtonSelect;
import org.iit.dr.view.component.JInternalFrameExt;

/**
 * Created by IntelliJ IDEA. User: Admin Date: 15.12.2010 Time: 4:36:23 To change this template use File | Settings |
 * File Templates.
 */
public class ConferenceTreeFrame extends JInternalFrameExt<Object>
{

  JTree tree;

  DefaultTreeModel treeModel;

  DefaultMutableTreeNode root;

  JButtonSelect selectedJButtonSelect;

  public ConferenceTreeFrame( Boolean selectDialog ) throws HeadlessException
  {

    if( selectDialog )
    {
      addComponents();
    }
  }

  public ConferenceTreeFrame()
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

    setVisible( true );
    return true;
  }

  protected void init()
  {

    setTitle( "Справочник: Конференции" );

    setMinimumSize( new Dimension( 500, 500 ) );
  }

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

            JMenuItem item = new JMenuItem( "Добавить позицию" );

            item.addActionListener( new ActionListener()
            {
              public void actionPerformed( ActionEvent e )
              {

                DefaultMutableTreeNode activeNode = getActiveTreeNode();

                ConfCreateFrame confFrame = new ConfCreateFrame();

                confFrame.showFrame( ConferenceTreeFrame.this, new Conference() );
                // groupFrame.showFrame(GroupTreeFrame.this, new OrganizationUnitWrapper(null, ((OrganizationUnit)
                // activeNode.getUserObject()).getId()));
              }
            } );

            popup.add( item );

            item = new JMenuItem( "Редактировать позицию" );

            item.addActionListener( new ActionListener()
            {
              public void actionPerformed( ActionEvent e )
              {

                DefaultMutableTreeNode activeNode = getActiveTreeNode();

                ConfCreateFrame confFrame = new ConfCreateFrame();
                if( !root.equals( activeNode ) )
                {
                  // найти коференцию
                  confFrame.showFrame( ConferenceTreeFrame.this,
                    ConferenceService.getConferenceByTitle( ( String ) activeNode.getUserObject() ) );
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

                  ConferenceService.removeConference( ConferenceService.getConferenceByTitle(
                    ( String ) activeNode.getUserObject() ).getId() );
                  updateData();
                }
              }
            } );

            popup.add( item );

            popup.show( ConferenceTreeFrame.this, e.getX(), e.getY() );

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
            System.out.println( getActiveTreeNode().getUserObject().getClass() );
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
    java.util.List<Conference> confList = DataStorage.getInstance().getConferenceList();

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
