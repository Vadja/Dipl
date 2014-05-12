package org.iit.dr.view.component;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.iit.dr.data_model.DefenceGraduateWork;
import org.iit.dr.data_model.Gek;
import org.iit.dr.data_model.GekDay;
import org.iit.dr.documents.word.write.graduate_work.ReportGeneratorUtil;
import org.iit.dr.services.GekService;
import org.iit.dr.services.GraduateWorkService;
import org.iit.dr.view.component.model.DefenceGWTreeNode;
import org.iit.dr.view.component.model.GekDayTreeNode;
import org.iit.dr.view.form.graduate_work.CommonDefenceGWFrame;

/**
 * DefenceGWTreePanel.
 * 
 * @author Yuriy Karpovich
 */
public class DefenceGWTreePanel extends JPanel
{

  JTree tree;

  DefaultTreeModel treeModel;

  DefaultMutableTreeNode root;

  CommonDefenceGWFrame commonDefenceGWFrame;

  public DefenceGWTreePanel( CommonDefenceGWFrame commonDefenceGWFrame )
  {
    super( new BorderLayout() );

    this.commonDefenceGWFrame = commonDefenceGWFrame;

    init();
    generateComponents();
  }

  public DefenceGWTreePanel()
  {
    super( new BorderLayout() );

    init();
    generateComponents();
  }

  protected void init()
  {

    setMinimumSize( new Dimension( 160, 200 ) );

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

        DefaultMutableTreeNode activeNode = getActiveTreeNode();

        if( e.getButton() == MouseEvent.BUTTON1 )
        {

          if( commonDefenceGWFrame != null )
          {

            commonDefenceGWFrame.checkChange();

            // --
            if( activeNode != null && activeNode instanceof DefenceGWTreeNode )
            {

              commonDefenceGWFrame.updateData( ( DefenceGraduateWork ) activeNode.getUserObject() );
            }
            else
            {

              commonDefenceGWFrame.updateData( null );
            }
          }
        }
        else if( e.getButton() == MouseEvent.BUTTON3 )
        {

          if( activeNode != null && activeNode instanceof GekDayTreeNode )
          {

            JPopupMenu popup = new JPopupMenu();

            JMenuItem item = new JMenuItem( "Протоколы (.doc)" );

            item.addActionListener( new ActionListener()
            {
              public void actionPerformed( ActionEvent e )
              {

                GekDayTreeNode gekDayTreeNode = ( GekDayTreeNode ) getActiveTreeNode();
                if( gekDayTreeNode.getUserObject() != null )
                {

                  List<DefenceGraduateWork> defenceGraduateWorkList =
                    GraduateWorkService.getDefenceGraduateWorkListOrderByProtocolNumber( ( ( GekDay ) gekDayTreeNode
                      .getUserObject() ).getDate() );

                  for( DefenceGraduateWork defenceGraduateWork : defenceGraduateWorkList )
                  {

                    ReportGeneratorUtil.generateProtocol( defenceGraduateWork.getId() );
                  }
                }
              }
            } );

            popup.add( item );

            popup.show( DefenceGWTreePanel.this, e.getX(), e.getY() );
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

  private DefaultMutableTreeNode getActiveTreeNode()
  {

    TreePath treePath = tree.getSelectionPath();

    if( treePath != null )
    {

      return ( DefaultMutableTreeNode ) treePath.getLastPathComponent();
    }

    return null;
  }

  public void updateData()
  {
    Gek gek = GekService.getGek();

    root = new DefaultMutableTreeNode( "График защиты" );

    treeModel = new DefaultTreeModel( root );

    Collections.sort( gek.getGekDayList(), new Comparator<GekDay>()
    {
      public int compare( GekDay gekDay1, GekDay gekDay2 )
      {

        Date gekDayDate1 = gekDay1.getDate();
        Date gekDayDate2 = gekDay2.getDate();

        if( gekDayDate1 != null && gekDayDate2 != null )
        {

          return gekDayDate1.compareTo( gekDayDate2 );
        }
        else if( gekDayDate1 == null )
        {

          return -1;
        }
        else
        {

          return 1;
        }
      }
    } );

    fillGekDayList( root, gek.getGekDayList() );

    tree.setModel( treeModel );
  }

  private void fillGekDayList( DefaultMutableTreeNode root, List<GekDay> gekDayList )
  {

    for( GekDay gekDay : gekDayList )
    {

      GekDayTreeNode treeNode = new GekDayTreeNode( gekDay );

      treeModel.insertNodeInto( treeNode, root, root.getChildCount() );

      fillDefenceGWList( treeNode, gekDay );
    }

  }

  private void fillDefenceGWList( DefaultMutableTreeNode root, GekDay gekDay )
  {

    if( gekDay != null && gekDay.getDate() != null )
    {

      List<DefenceGraduateWork> defenceGraduateWorkList =
        GraduateWorkService.getDefenceGraduateWorkListOrderByStudent( gekDay.getDate(),gekDay.getStartTime(),gekDay.getEndTime() );

      for( DefenceGraduateWork defenceGraduateWork : defenceGraduateWorkList )
      {

        DefenceGWTreeNode treeNode = new DefenceGWTreeNode( defenceGraduateWork );
        treeModel.insertNodeInto( treeNode, root, root.getChildCount() );
      }
    }
  }

}
