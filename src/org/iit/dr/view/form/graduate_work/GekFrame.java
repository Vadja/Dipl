package org.iit.dr.view.form.graduate_work;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import org.iit.dr.data_model.DefenceGraduateWork;
import org.iit.dr.data_model.Gek;
import org.iit.dr.data_model.GekDay;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.documents.word.write.graduate_work.GekDayDocumentGenerator;
import org.iit.dr.services.GekService;
import org.iit.dr.services.GraduateWorkService;
import org.iit.dr.services.TeachersService;
import org.iit.dr.utils.DateUtils;
import org.iit.dr.utils.MemoryManager;
import org.iit.dr.utils.UUIDUtils;
import org.iit.dr.view.component.JButtonSelect;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.form.staffers.StafferListSelectButtonFrame;
import org.iit.dr.view.model.table.common.TableModelConst;
import org.iit.dr.view.model.table.graduate_work.GekDayListTableModel;

import com.toedter.calendar.JDateChooserCellEditor;

/**
 * GekFrame.
 * 
 * @author Yuriy Karpovich
 */
public class GekFrame extends JInternalFrameExt<Object>
{
  private static final long serialVersionUID = 768535575398672248L;

  JTabbedPane jTabbedPane;

  StafferListSelectButtonFrame stafferListFrame;

  JButtonSelect presideField;

  JButtonSelect commissionerSecrField;

  JButtonSelect commissioner0Field;

  JButtonSelect commissioner1Field;

  JButtonSelect commissioner2Field;

  JButtonSelect commissioner3Field;

  JButtonSelect commissioner4Field;

  JButtonSelect commissioner5Field;

  JButtonSelect commissioner6Field;

  JButtonSelect commissioner7Field;

  JButtonSelect commissioner8Field;

  JButtonSelect commissioner9Field;

  GekDayListTableModel gekDayListTableModel;

  JTable gekDayTable;

  public GekFrame() throws HeadlessException
  {

    stafferListFrame = ( StafferListSelectButtonFrame ) MemoryManager.getObject( StafferListSelectButtonFrame.class );
    stafferListFrame.updateFilter();
  }

  @Override
  protected void init()
  {
    setTitle( "Форма редактирования: ГЭК" );

    setMinimumSize( new Dimension( 720, 650 ) );
    jTabbedPane = new JTabbedPane();
  }

  private Teacher getTeacherUserOject( String teacherId )
  {
    if( teacherId != null && teacherId.length() != 0 )
    {
      return TeachersService.getTeacher( teacherId );
    }
    return null;
  }

  private String getTeacherFromUserOject( Teacher teacher )
  {

    return teacher != null ? teacher.getId() : null;
  }

  @Override
  public boolean showFrame( Object parent, Object object )
  {

    Gek gek = GekService.getGek();

    presideField.setUserObject( getTeacherUserOject( gek.getPresideId() ) );
    commissionerSecrField.setUserObject( getTeacherUserOject( gek.getCommissionerSecrId() ) );
    commissioner0Field.setUserObject( getTeacherUserOject( gek.getCommissioner0Id() ) );
    commissioner1Field.setUserObject( getTeacherUserOject( gek.getCommissioner1Id() ) );
    commissioner2Field.setUserObject( getTeacherUserOject( gek.getCommissioner2Id() ) );
    commissioner3Field.setUserObject( getTeacherUserOject( gek.getCommissioner3Id() ) );
    commissioner4Field.setUserObject( getTeacherUserOject( gek.getCommissioner4Id() ) );
    commissioner5Field.setUserObject( getTeacherUserOject( gek.getCommissioner5Id() ) );
    commissioner6Field.setUserObject( getTeacherUserOject( gek.getCommissioner6Id() ) );
    commissioner7Field.setUserObject( getTeacherUserOject( gek.getCommissioner7Id() ) );
    commissioner8Field.setUserObject( getTeacherUserOject( gek.getCommissioner8Id() ) );
    commissioner9Field.setUserObject( getTeacherUserOject( gek.getCommissioner9Id() ) );

    setVisible( true );
    return true;
  }

  private void initComponents()
  {

    gekDayListTableModel = new GekDayListTableModel();
    gekDayTable = new JTable( gekDayListTableModel );
    gekDayTable.setDefaultEditor( Date.class, new JDateChooserCellEditor() );

    presideField = createJButtonSelect();
    commissionerSecrField = createJButtonSelect();
    commissioner0Field = createJButtonSelect();
    commissioner1Field = createJButtonSelect();
    commissioner2Field = createJButtonSelect();
    commissioner3Field = createJButtonSelect();
    commissioner4Field = createJButtonSelect();
    commissioner5Field = createJButtonSelect();
    commissioner6Field = createJButtonSelect();
    commissioner7Field = createJButtonSelect();
    commissioner8Field = createJButtonSelect();
    commissioner9Field = createJButtonSelect();
  }

  @Override
  protected void generateComponents()
  {

    initComponents();

    jTabbedPane = new JTabbedPane();

    JPanel jPane =
      createVPane( 5, createField( presideField, "Председатель комиссии: " ), createField( commissionerSecrField,
        "Член комиссии (секретарь): " ), createHPane( 0, createField( commissioner0Field, "Член комиссии: " ),
        createField( commissioner1Field, "Член комиссии: " ) ), createHPane( 0, createField( commissioner2Field,
        "Член комиссии: " ), createField( commissioner3Field, "Член комиссии: " ) ), createHPane( 0, createField(
        commissioner4Field, "Член комиссии: " ), createField( commissioner5Field, "Член комиссии: " ) ),
        createHPane( 0, createField( commissioner6Field, "Член комиссии: " ), createField( commissioner7Field,
          "Член комиссии: " ) ), createHPane( 0, createField( commissioner8Field, "Член комиссии: " ), createField(
          commissioner9Field, "Член комиссии: " ) ) );
    jTabbedPane.addTab( "Комиcсия", jPane );

    JScrollPane gekDayScrollPane = new JScrollPane( gekDayTable );

    JButton addRowButton = new JButton( "Добавить" );
    addRowButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {
        GekService.getGek().getGekDayList().add( new GekDay( UUIDUtils.getUUID() ) );
        gekDayListTableModel.updateData( null );
      }
    } );

    JButton removeRowButton = new JButton( "Удалить" );
    removeRowButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        if( gekDayTable.getSelectedRow() >= 0 )
        {
          String guid =
            ( String ) gekDayListTableModel.getValueAt( gekDayTable.getSelectedRow(), TableModelConst.ID_COLUMN );
          GekService.removeGekDay( guid );

          gekDayListTableModel.updateData( null );
        }
      }
    } );

    JButton removeProtocolNumbersButton = new JButton( "Удалить № протоколов" );
    removeProtocolNumbersButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        if( gekDayTable.getSelectedRows() != null && gekDayTable.getSelectedRows().length > 0 )
        {

          for( int dayIndex : gekDayTable.getSelectedRows() )
          {

            Date dateItem = ( Date ) gekDayListTableModel.getValueAt( dayIndex, 0 );
            if( dateItem != null )
            {

              List<DefenceGraduateWork> defenceGraduateWorkList =
                GraduateWorkService.getDefenceGraduateWorkListOrderByStudent( dateItem,( String ) gekDayListTableModel.getValueAt( dayIndex, 1 ),
                		( String ) gekDayListTableModel.getValueAt( dayIndex, 2 ));
              for( DefenceGraduateWork defenceGraduateWork : defenceGraduateWorkList )
              {

                defenceGraduateWork.setProtocolNumber( null );
              }
            }
          }

        }
      }
    } );

    JButton generatePlanButton = new JButton( "График защиты" );
    generatePlanButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        if( gekDayTable.getSelectedRows() != null && gekDayTable.getSelectedRows().length > 0 )
        {

          for( int dayIndex : gekDayTable.getSelectedRows() )
          {

            Date dateItem = ( Date ) gekDayListTableModel.getValueAt( dayIndex, 0 );
            if( dateItem != null )
            {

              GekDayDocumentGenerator gekDayDocumentGenerator = new GekDayDocumentGenerator();
              gekDayDocumentGenerator.setTemplateName( "График по дням защиты.doc" );
              gekDayDocumentGenerator.generateDocument( ( String ) gekDayListTableModel.getValueAt( dayIndex,
                TableModelConst.ID_COLUMN ), "График защиты [" + DateUtils.dateToString( dateItem ) +", "+
                (( String ) gekDayListTableModel.getValueAt( dayIndex, 1 )).split(":")[0]+"."+ 
                (( String ) gekDayListTableModel.getValueAt( dayIndex, 1 )).split(":")[1]+"-"+ 
        		(( String ) gekDayListTableModel.getValueAt( dayIndex, 2 )).split(":")[0]+"."+
                (( String ) gekDayListTableModel.getValueAt( dayIndex, 2 )).split(":")[1]+
                "].doc" );
            }
          }

        }
      }
    } );

    JButton generateResultButton = new JButton( "Результаты защиты" );
    generateResultButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        if( gekDayTable.getSelectedRows() != null && gekDayTable.getSelectedRows().length > 0 )
        {

          for( int dayIndex : gekDayTable.getSelectedRows() )
          {

            Date dateItem = ( Date ) gekDayListTableModel.getValueAt( dayIndex, 0 );
            if( dateItem != null )
            {

              GekDayDocumentGenerator gekDayDocumentGenerator = new GekDayDocumentGenerator();
              gekDayDocumentGenerator.setTemplateName( "Результат защиты.doc" );
              gekDayDocumentGenerator.generateDocumentResult( ( String ) gekDayListTableModel.getValueAt( dayIndex,
                TableModelConst.ID_COLUMN ), "Результат защиты [" + DateUtils.dateToString( dateItem ) + "].doc" );
            }
          }

        }
      }
    } );

    JButton applyNumberButton = new JButton( "Сформировать № протоколов" );
    applyNumberButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        if( gekDayTable.getSelectedRows() != null && gekDayTable.getSelectedRows().length > 0 )
        {

          List<String> dateList = new ArrayList<String>();

          for( int dayIndex : gekDayTable.getSelectedRows() )
          {

            Date dateItem = ( Date ) gekDayListTableModel.getValueAt( dayIndex, 0 );

            if( dateItem != null )
            {

              dateList.add( ( String ) gekDayListTableModel.getValueAt( dayIndex, TableModelConst.ID_COLUMN ) );
            }
          }
          GekService.applyProtokolNumers( dateList );
        }
      }
    } );

    jPane =
      createVPane( 5, createNorthButtonPane( 10, addRowButton, removeRowButton ), gekDayScrollPane,
        createNorthButtonPane( 10, removeProtocolNumbersButton, generateResultButton, generatePlanButton,
          applyNumberButton ) );
    jTabbedPane.addTab( "Расписание", jPane );

    getContentPane().add( jTabbedPane, BorderLayout.CENTER );

    JButton applyButton = new JButton( "Применить" );
    applyButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        Gek gek = GekService.getGek();

        gek.setPresideId( getTeacherFromUserOject( ( Teacher ) presideField.getUserObject() ) );
        gek.setCommissionerSecrId( getTeacherFromUserOject( ( Teacher ) commissionerSecrField.getUserObject() ) );
        gek.setCommissioner0Id( getTeacherFromUserOject( ( Teacher ) commissioner0Field.getUserObject() ) );
        gek.setCommissioner1Id( getTeacherFromUserOject( ( Teacher ) commissioner1Field.getUserObject() ) );
        gek.setCommissioner2Id( getTeacherFromUserOject( ( Teacher ) commissioner2Field.getUserObject() ) );
        gek.setCommissioner3Id( getTeacherFromUserOject( ( Teacher ) commissioner3Field.getUserObject() ) );
        gek.setCommissioner4Id( getTeacherFromUserOject( ( Teacher ) commissioner4Field.getUserObject() ) );
        gek.setCommissioner5Id( getTeacherFromUserOject( ( Teacher ) commissioner5Field.getUserObject() ) );
        gek.setCommissioner6Id( getTeacherFromUserOject( ( Teacher ) commissioner6Field.getUserObject() ) );
        gek.setCommissioner7Id( getTeacherFromUserOject( ( Teacher ) commissioner7Field.getUserObject() ) );
        gek.setCommissioner8Id( getTeacherFromUserOject( ( Teacher ) commissioner8Field.getUserObject() ) );
        gek.setCommissioner9Id( getTeacherFromUserOject( ( Teacher ) commissioner9Field.getUserObject() ) );
        GekService.updateFullGek();
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

    getContentPane().add( createNorthButtonPane( 10, applyButton, cancelButton ), BorderLayout.SOUTH );
    // getContentPane().add(createVPane(5, createNorthButtonPane(10, applyNumberButton), createButtonPane(10,
    // applyButton, cancelButton)), BorderLayout.SOUTH);

    presideField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( GekFrame.this, presideField );
      }
    } );

    commissionerSecrField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( GekFrame.this, commissionerSecrField );
      }
    } );

    commissioner0Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( GekFrame.this, commissioner0Field );
      }
    } );
    commissioner1Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( GekFrame.this, commissioner1Field );
      }
    } );
    commissioner2Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( GekFrame.this, commissioner2Field );
      }
    } );
    commissioner3Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( GekFrame.this, commissioner3Field );
      }
    } );
    commissioner4Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( GekFrame.this, commissioner4Field );
      }
    } );
    commissioner5Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( GekFrame.this, commissioner5Field );
      }
    } );
    commissioner6Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( GekFrame.this, commissioner6Field );
      }
    } );
    commissioner7Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( GekFrame.this, commissioner7Field );
      }
    } );
    commissioner8Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( GekFrame.this, commissioner8Field );
      }
    } );
    commissioner9Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( GekFrame.this, commissioner9Field );
      }
    } );

  }
}
