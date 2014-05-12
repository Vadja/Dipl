package org.iit.dr.view.form.graduate_work;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import org.iit.dr.data_model.DefenceGraduateWork;
import org.iit.dr.documents.word.write.graduate_work.ReportGeneratorUtil;
import org.iit.dr.documents.word.write.practice.TableDocumentGenerator;
import org.iit.dr.services.GekService;
import org.iit.dr.services.GraduateWorkService;
import org.iit.dr.utils.DateUtils;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.model.table.common.TableModelConst;
import org.iit.dr.view.model.table.graduate_work.DefenceGraduateWorkTableModel;

/**
 * DefenceGraduateWorkListFrame.
 * 
 * @author Yuriy Karpovich
 */
public class DefenceGraduateWorkListFrame extends JInternalFrameExt<Object>
{

  private static final long serialVersionUID = -9136287845241419763L;

  public static final String ALL_FIELD = "Все";

  DefenceGraduateWorkTableModel model;

  JTable jTable;

  DefenceGraduateWorkFrame defenceGraduateWorkFrame;

  JPopupMenu reportPopup;

  JPopupMenu bulkPopup;

  JList jListDate;

  JLabel statusLabel;

  CommissionFrame commissionFrame = new CommissionFrame();

  public DefenceGraduateWorkListFrame() throws HeadlessException
  {
  }

  @Override
  protected void init()
  {

    setTitle( "Защита дипломных проектов" );

    setMinimumSize( new Dimension( 1000, 520 ) );
    defenceGraduateWorkFrame = new DefenceGraduateWorkFrame();
  }

  private List<String> getSelectedItems()
  {

    List<String> selectedItemList = new ArrayList<String>();

    int[] selectedRows = jTable.getSelectedRows();

    if( selectedRows != null )
    {

      for( int selectedRow : selectedRows )
      {

        String guid = ( String ) model.getValueAt( selectedRow, TableModelConst.ID_COLUMN );

        if( guid != null )
        {
          selectedItemList.add( guid );
        }
      }

    }

    return selectedItemList;
  }

  private void generateProtocol( String defenceFraduateWorkId )
  {

    ReportGeneratorUtil.generateProtocol( defenceFraduateWorkId );

  }

  private void addPopup()
  {

    reportPopup = new JPopupMenu();

    JMenuItem item = new JMenuItem( "Пакета документов" );
    item.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        List<String> selectedItemList = getSelectedItems();

        if( selectedItemList != null && selectedItemList.size() > 0 )
        {

          for( String selectedGUID : selectedItemList )
          {

            generateProtocol( selectedGUID );
          }

          TableDocumentGenerator documentGenerator = new TableDocumentGenerator();
          documentGenerator.setTemplateName( "Распоряжение.doc" );
          documentGenerator.generateDocument( selectedItemList, "Распоряжение.doc" );
        }
      }
    } );
    reportPopup.add( item );

    item = new JMenuItem( "Протокола" );
    item.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        List<String> selectedItemList = getSelectedItems();
        for( String selectedGUID : selectedItemList )
        {

          generateProtocol( selectedGUID );
        }
      }
    } );
    reportPopup.add( item );

    bulkPopup = new JPopupMenu();

    item = new JMenuItem( "Назначить комиссию" );
    item.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        List<String> selectedItemList = getSelectedItems();

        if( selectedItemList != null && selectedItemList.size() > 0 )
        {

          commissionFrame.showFrame( DefenceGraduateWorkListFrame.this, selectedItemList );
        }
      }
    } );
    bulkPopup.add( item );
  }

  @Override
  protected void generateComponents()
  {

    statusLabel = new JLabel();

    jListDate = new JList();
    jListDate.addMouseListener( new MouseListener()
    {

      public void mouseClicked( MouseEvent e )
      {

        List<String> dateList = new ArrayList<String>();

        Object[] selectedValues = jListDate.getSelectedValues();

        if( selectedValues != null )
        {

          for( Object selectedValue : selectedValues )
          {

            if( !selectedValue.equals( ALL_FIELD ) )
            {

              dateList.add( ( String ) selectedValue );

            }
            else
            {

              model.updateData( null );
              return;
            }
          }

          model.updateDataTime( dateList );
        }
        else
        {
          model.updateData( null );
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

    model = new DefenceGraduateWorkTableModel();
    jTable = new JTable( model );

    jTable.addMouseListener( new MouseAdapter()
    {
      @Override
      public void mouseClicked( MouseEvent e )
      {
        if( e.getButton() == MouseEvent.BUTTON3 )
        {
          if( jTable.getSelectedRow() >= 0 )
          {
            String guid = ( String ) model.getValueAt( jTable.getSelectedRow(), TableModelConst.ID_COLUMN );
            DefenceGraduateWork defenceGraduateWork = GraduateWorkService.getDefenceGraduateWork( guid );
            defenceGraduateWorkFrame.showFrame( DefenceGraduateWorkListFrame.this, defenceGraduateWork );
          }
        }
      }
    } );

    JScrollPane jScrollPane = new JScrollPane( jTable );
    JScrollPane jScrollPaneLeft = new JScrollPane( jListDate );
    jScrollPaneLeft.setMinimumSize( new Dimension( 180, 200 ) );

    add( new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, jScrollPaneLeft, jScrollPane ), BorderLayout.CENTER );

    JButton addButton = new JButton( "Добавить" );
    addButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        defenceGraduateWorkFrame.showFrame( DefenceGraduateWorkListFrame.this, null );

      }
    } );

    JButton removeButton = new JButton( "Удалить" );
    removeButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        if( jTable.getSelectedRow() >= 0 )
        {
         
          for(int i : jTable.getSelectedRows()){
     		 String guid = ( String ) model.getValueAt( i, TableModelConst.ID_COLUMN );
              GraduateWorkService.removeDefenceGraduateWork( guid );
          }
          
          model.updateData();
        }
      }
    } );

    getContentPane().add( createNorthButtonPane( 5, addButton, removeButton ), BorderLayout.NORTH );

    JButton reportButton = new JButton( "Формирование документов" );
    reportButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        int x = DefenceGraduateWorkListFrame.this.getSize().width;
        int y = DefenceGraduateWorkListFrame.this.getSize().height;

        reportPopup.show( DefenceGraduateWorkListFrame.this, x - 203, y - 85 );
      }
    } );

    JButton bulkButton = new JButton( "Действия" );
    bulkButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        int x = DefenceGraduateWorkListFrame.this.getSize().width;
        int y = DefenceGraduateWorkListFrame.this.getSize().height;

        bulkPopup.show( DefenceGraduateWorkListFrame.this, x - 308, y - 62 );
      }
    } );

    // getContentPane().add(createVPane(10, createNorthButtonPane(5, statusLabel), createNorthButtonPane(5, bulkButton,
    // reportButton)), BorderLayout.SOUTH);
    getContentPane().add( createNorthButtonPane( 5, bulkButton, reportButton ), BorderLayout.SOUTH );

    addPopup();
  }

  @Override
  public boolean showFrame( Object parent, Object jButtonSelectObject )
  {

    updateListDate();
    model.updateData();
    setVisible( true );

    return true;
  }

  @SuppressWarnings("deprecation")
public void updateListDate()
  {

//    List<Date> dateList = GekService.getGekDateList();
    ArrayList<ArrayList<Date>> dateList = GekService.getGekDateList();
    int dateListSize = dateList.size();

    Object objectData[] = new Object[dateListSize + 1];

    objectData[0] = ALL_FIELD;

    for( int i = 0; i < dateListSize; i++ )
    {
    	String min;
    	if(dateList.get( i ).get(0).getMinutes()<10){
    		min = "0"+String.valueOf(dateList.get( i ).get(0).getMinutes());
    	}else{
    		min = String.valueOf(dateList.get( i ).get(0).getMinutes());
    	}
    	
    	String time = String.valueOf(dateList.get( i ).get(0).getHours())+":"+min;
    	
    	if(dateList.get( i ).get(1).getMinutes()<10){
    		min = "0"+String.valueOf(dateList.get( i ).get(1).getMinutes());
    	}else{
    		min = String.valueOf(dateList.get( i ).get(1).getMinutes());
    	}
    	
    	String time2 = String.valueOf(dateList.get( i ).get(1).getHours())+":"+min;
    	
    
      objectData[i + 1] = DateUtils.dateToString( dateList.get( i ).get(0) )+" / "+time+" - "+time2;
    }

    jListDate.setListData( objectData );

  }

  public DefenceGraduateWorkTableModel getModel()
  {
    return model;
  }
}