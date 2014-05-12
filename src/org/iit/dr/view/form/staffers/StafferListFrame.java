package org.iit.dr.view.form.staffers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.iit.dr.data_model.role.ExternalSecondJobStaffer;
import org.iit.dr.data_model.role.InternalSecondJobStaffer;
import org.iit.dr.data_model.role.PartTimeStaffer;
import org.iit.dr.data_model.role.Staffer;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.documents.word.write.staffers.StaffScheduleDocumentGenerator;
import org.iit.dr.services.RateService;
import org.iit.dr.services.TeachersService;
import org.iit.dr.utils.FileUtils;
import org.iit.dr.utils.MemoryManager;
import org.iit.dr.view.component.JDateField;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.component.JTableExt;
import org.iit.dr.view.model.table.common.TableModelConst;
import org.iit.dr.view.model.table.staffers.StafferTableModel;

public class StafferListFrame extends JInternalFrameExt<Object>
{
  private static final long serialVersionUID = 9019290989157655654L;

  StafferFrame stafferFrame;

  InternalStafferFrame internalStafferFrame;

  ExternalStafferFrame externalStafferFrame;

  PartTimeStafferFrame partTimeStafferFrame;

  StafferTableModel model;

  JTableExt jTable;
  
  JDateField dateSchedule;

  // ---

  JCheckBox allFilter;

  JCheckBox stafferFilter;

  JCheckBox partTimeStaffer;

  JCheckBox internalStaffer;

  JCheckBox externalStaffer;

  JTextField lastNameFilter;

  // ---
  @Override
  protected void init()
  {

    setTitle( "Справочник: Преподаватели" );

    setMinimumSize( new Dimension( 600, 300 ) );
    stafferFrame = ( StafferFrame ) MemoryManager.getObject( StafferFrame.class );
    internalStafferFrame = ( InternalStafferFrame ) MemoryManager.getObject( InternalStafferFrame.class );
    externalStafferFrame = ( ExternalStafferFrame ) MemoryManager.getObject( ExternalStafferFrame.class );
    partTimeStafferFrame = ( PartTimeStafferFrame ) MemoryManager.getObject( PartTimeStafferFrame.class );
  }

  private void initComponents()
  {
	dateSchedule = createJDateField(250);
	dateSchedule.setDate(new Date());
    allFilter = createJCheckBox( "Все" );
    stafferFilter = createJCheckBox( "Штатные" );
    partTimeStaffer = createJCheckBox( "Почасовики" );
    internalStaffer = createJCheckBox( "Внутренние сов." );
    externalStaffer = createJCheckBox( "Внешние сов." );
    lastNameFilter = createJTextField();
  }

  @Override
  protected void generateComponents()
  {

    initComponents();

    allFilter.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        if( allFilter.isSelected() )
        {

          stafferFilter.setSelected( false );
          partTimeStaffer.setSelected( false );
          internalStaffer.setSelected( false );
          externalStaffer.setSelected( false );

        }
        else
        {
          allFilter.setSelected( true );
        }
        updateFilter();
      }
    } );

    stafferFilter.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        if( stafferFilter.isSelected() )
        {

          allFilter.setSelected( false );

        }
        else
        {
          if( !partTimeStaffer.isSelected() && !internalStaffer.isSelected() && !externalStaffer.isSelected() )
          {

            allFilter.setSelected( true );
          }
        }
        updateFilter();
      }
    } );

    partTimeStaffer.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        if( partTimeStaffer.isSelected() )
        {

          allFilter.setSelected( false );

        }
        else
        {
          if( !stafferFilter.isSelected() && !internalStaffer.isSelected() && !externalStaffer.isSelected() )
          {

            allFilter.setSelected( true );
          }
        }
        updateFilter();
      }
    } );

    internalStaffer.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        if( internalStaffer.isSelected() )
        {

          allFilter.setSelected( false );

        }
        else
        {
          if( !stafferFilter.isSelected() && !partTimeStaffer.isSelected() && !externalStaffer.isSelected() )
          {

            allFilter.setSelected( true );
          }
        }
        updateFilter();
      }
    } );

    externalStaffer.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        if( externalStaffer.isSelected() )
        {

          allFilter.setSelected( false );

        }
        else
        {
          if( !stafferFilter.isSelected() && !partTimeStaffer.isSelected() && !internalStaffer.isSelected() )
          {

            allFilter.setSelected( true );
          }
        }
        updateFilter();
      }
    } );

    lastNameFilter.addKeyListener( new KeyAdapter()
    {
      @Override
      public void keyReleased( KeyEvent e )
      {
        updateFilter();
      }
    } );

    model = new StafferTableModel();
    jTable = new JTableExt( model );

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

            Teacher teacher = TeachersService.getTeacher( guid );

            if( teacher != null )
            {

              if( teacher instanceof Staffer )
              {

                stafferFrame.showFrame( model, ( Staffer ) teacher );

              }
              else if( teacher instanceof PartTimeStaffer )
              {

                partTimeStafferFrame.showFrame( model, ( PartTimeStaffer ) teacher );

              }
              else if( teacher instanceof ExternalSecondJobStaffer )
              {

                externalStafferFrame.showFrame( model, ( ExternalSecondJobStaffer ) teacher );

              }
              else if( teacher instanceof InternalSecondJobStaffer )
              {

                internalStafferFrame.showFrame( model, ( InternalSecondJobStaffer ) teacher );
              }

            }
          }
        }
      }
    } );

    JScrollPane jScrollPane = new JScrollPane( jTable );
    getContentPane().add( jScrollPane, BorderLayout.CENTER );
    
        
    JButton createStaffschedule = new JButton("Сформировать связь со штатным расписанием");
    createStaffschedule.addActionListener( new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			StaffScheduleDocumentGenerator generator = new StaffScheduleDocumentGenerator();
			generator.generate(dateSchedule.getDate());
		}
	});

    JButton addButton = new JButton( "Добавить" );
    addButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        JPopupMenu popup = new JPopupMenu();

        JMenuItem item = new JMenuItem( "Штатного сотрудника" );
        item.addActionListener( new ActionListener()
        {
          public void actionPerformed( ActionEvent e )
          {

            stafferFrame.showFrame( model, null );
            updateFilter();
          }
        } );
        popup.add( item );

        item = new JMenuItem( "Внутреннего совместителя" );
        item.addActionListener( new ActionListener()
        {
          public void actionPerformed( ActionEvent e )
          {

            internalStafferFrame.showFrame( model, null );
            updateFilter();
          }
        } );
        popup.add( item );

        item = new JMenuItem( "Внешнего совместителя" );
        item.addActionListener( new ActionListener()
        {
          public void actionPerformed( ActionEvent e )
          {

            externalStafferFrame.showFrame( model, null );
            updateFilter();
          }
        } );
        popup.add( item );

        item = new JMenuItem( "Почасовика" );
        item.addActionListener( new ActionListener()
        {
          public void actionPerformed( ActionEvent e )
          {
            partTimeStafferFrame.showFrame( model, null );
            updateFilter();
          }
        } );
        popup.add( item );

        popup.show( StafferListFrame.this, ( ( JButton ) e.getSource() ).getBounds().x, ( ( JButton ) e.getSource() )
          .getBounds().y + 50 );
      }
    } );

    JButton removeButton = new JButton( "Удалить" );
    removeButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        if( jTable.getSelectedRows().length > 0 )
        {
          for( int idRow : jTable.getSelectedRows() )
          {
            String guid = ( String ) model.getValueAt( idRow, TableModelConst.ID_COLUMN );
            TeachersService.removeTeacher( guid );
            RateService.removeRateByTeacher(guid);
          }
          updateFilter();
        }
      }
    } );
    JPanel jSchedulePanel = createNorthButtonPane(5, createField(dateSchedule, "Дата: "), createStaffschedule);
    JPanel jButtonPanel = createNorthButtonPane( 5, lastNameFilter, addButton, removeButton );
    JPanel jFilterPanel =
      createNorthButtonPane( 5, allFilter, stafferFilter, partTimeStaffer, internalStaffer, externalStaffer );

    getContentPane().add( createVPane( 0, jSchedulePanel, jButtonPanel, jFilterPanel ), BorderLayout.NORTH );
  }

  public void updateFilter()
  {

    String textFilter = null;

    if( lastNameFilter.getText() != null && lastNameFilter.getText().length() > 0 )
    {

      textFilter = lastNameFilter.getText();
    }

    List<Class> classFilter = null;

    if( !allFilter.isSelected() )
    {

      classFilter = new ArrayList<Class>();

      if( stafferFilter.isSelected() )
      {

        classFilter.add( Staffer.class );
      }

      if( partTimeStaffer.isSelected() )
      {

        classFilter.add( PartTimeStaffer.class );
      }

      if( externalStaffer.isSelected() )
      {

        classFilter.add( ExternalSecondJobStaffer.class );
      }

      if( internalStaffer.isSelected() )
      {

        classFilter.add( InternalSecondJobStaffer.class );
      }
    }

    model.updateData( textFilter, classFilter );

  }

  @Override
  public boolean showFrame( Object parent, Object jButtonSelectObject )
  {
    lastNameFilter.setText( "" );
    allFilter.setSelected( true );
    stafferFilter.setSelected( false );
    partTimeStaffer.setSelected( false );
    internalStaffer.setSelected( false );
    externalStaffer.setSelected( false );
    updateFilter();
    setVisible( true );
    return true;
  }

  public String[] getSelectedTeacherId()
  {
    return jTable.getSelectedIds();
  }
}
