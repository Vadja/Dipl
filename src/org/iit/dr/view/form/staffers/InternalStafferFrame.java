package org.iit.dr.view.form.staffers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.iit.dr.data_model.Degree;
import org.iit.dr.data_model.Passport;
import org.iit.dr.data_model.Position;
import org.iit.dr.data_model.Rank;
import org.iit.dr.data_model.Rate;
import org.iit.dr.data_model.RateType;
import org.iit.dr.data_model.role.InternalSecondJobStaffer;
import org.iit.dr.services.RateService;
import org.iit.dr.services.TeachersService;
import org.iit.dr.utils.UUIDUtils;
import org.iit.dr.view.component.JDateField;
import org.iit.dr.view.component.JDateRangeField;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.model.table.staffers.RateTableModel;
import org.iit.dr.view.model.table.staffers.StafferTableModel;

import com.toedter.calendar.JDateChooserCellEditor;

/**
 * StudentsFrame.
 * 
 * @author Yuriy Karpovich
 */
public class InternalStafferFrame extends JInternalFrameExt<InternalSecondJobStaffer>
{

  private boolean isNewStaffer;

  InternalSecondJobStaffer staffer;

  StafferTableModel model;

  RateTableModel rateTableModel;

  JTable rateTable;

  JTabbedPane jTabbedPane;
  
  ButtonGroup buttonGroup;

  // ------

  JTextField lastNameField;

  JTextField firstNameField;

  JTextField patrNameField;

 // JDateRangeField contractField;

 // JDateRangeField dogovorField;
  
  JDateRangeField jDateRange;

  JComboBox rankField;

  JComboBox degreeField;

  JComboBox originalPositionField;

  JComboBox secondJobPositionField;
  
  JRadioButton contract;
	
  JRadioButton dogovor;

  // ------

  JTextField passportNumberField;

  JTextField passportPrivateNumberField;

  JDateField passportDateIssueField;

  JTextField passportPlaceIssueField;

  JTextField passportInsuranceNumberField;

  @Override
  protected void init()
  {

    setTitle( "Форма редактирования: Внутренний совместитель" );

    setMinimumSize( new Dimension( 670, 350 ) );
    rateTableModel = new RateTableModel();
  }

  @Override
  protected void generateComponents()
  {

    initComponents();

    jTabbedPane = new JTabbedPane();

    JPanel leftJPane =
      createVPane( 5, createField( lastNameField, "Фамилия: " ), createField( firstNameField, "Имя: " ), createField(
        patrNameField, "Отчество: " ), 	createField(dogovor), createField(contract));

    JPanel rightJPane =
      createVPane( 5, createField(jDateRange, "Срок: "), createField( rankField, "Звание: " ), createField( degreeField, "Степень: " ), createField(
          originalPositionField, "Должность в БГУИР: " ), createField( secondJobPositionField, "Должность по совм.: " ) );

    JPanel fieldsPane = createHPane( 20, leftJPane, rightJPane );

    JButton okButton = new JButton( "OK" );
    okButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        saveFieldDataToStorage();

        setVisible( false );
      }
    } );

    JButton applyButton = new JButton( "Применить" );
    applyButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        saveFieldDataToStorage();
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

    jTabbedPane.addTab( "Общие", fieldsPane );

    JPanel passportPane =
      createVPane( 5, createField( passportNumberField, "№ паспорта: " ), createField( passportPrivateNumberField,
        "Личный номер: " ), createField( passportDateIssueField, "Дата выдачи: " ), createField(
        passportPlaceIssueField, "Место выдачи (структура): " ), createField( passportInsuranceNumberField,
        "Страховой номер: " ) );
    jTabbedPane.addTab( "Паспорт", passportPane );

    JPanel ratePanel = new JPanel( new BorderLayout() );

    rateTable = new JTable( rateTableModel );
    rateTable.setDefaultEditor( Date.class, new JDateChooserCellEditor() );

    JComboBox rateTypeBox = new JComboBox();
    rateTypeBox.addItem( null );
    for( RateType rateType : RateType.values() )
    {

      rateTypeBox.addItem( rateType );
    }
    rateTable.setDefaultEditor( RateType.class, new DefaultCellEditor( rateTypeBox ) );

    JScrollPane jScrollPane =
      new JScrollPane( rateTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );

    ratePanel.add( jScrollPane, BorderLayout.CENTER );

    JButton addButton = new JButton( "Добавить" );
    addButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {
        Rate rate = new Rate();
		rate.setId(UUIDUtils.getUUID());
		rate.setTeacher(staffer.getId());
		rate.setRange(jDateRange.getDateRange());
        rateTableModel.addRate( rate );
        rateTableModel.updateData( staffer.getId() );
        rateTable.updateUI();
      }
    } );

    JButton removeButton = new JButton( "Удалить" );
    removeButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        if( rateTable.getSelectedRow() >= 0 )
        {
          rateTableModel.removeRate( rateTable.getSelectedRow() );
          rateTableModel.updateData( staffer.getId() );
          rateTable.updateUI();
        }
      }
    } );
    JPanel jButtonPanel = createNorthButtonPane( 5, addButton, removeButton );

    ratePanel.add( jButtonPanel, BorderLayout.NORTH );

    jTabbedPane.addTab( "Штатное расписание", ratePanel );

    getContentPane().add( jTabbedPane, BorderLayout.CENTER );
    getContentPane().add( createNorthButtonPane( 10, okButton, applyButton, cancelButton ), BorderLayout.SOUTH );
  }

  private void saveFieldDataToStorage()
  {

    staffer.setLastName( checkFieldNull( lastNameField ) );
    staffer.setFirstName( checkFieldNull( firstNameField ) );
    staffer.setPatronymicName( checkFieldNull( patrNameField ) );

	staffer.setContract(contract.isSelected());
	staffer.setRange(jDateRange.getDateRange());

    Passport passport = new Passport();
    passport.setDateIssue( passportDateIssueField.getDate() );
    passport.setNumber( passportNumberField.getText() );
    passport.setPrivateNumber( passportPrivateNumberField.getText() );
    passport.setPlaceIssue( passportPlaceIssueField.getText() );
    passport.setInsuranceNumber( passportInsuranceNumberField.getText() );
    staffer.setPassport( passport );

    staffer.setRank( ( Rank ) rankField.getSelectedItem() );
    staffer.setDegree( ( Degree ) degreeField.getSelectedItem() );
    staffer.setOriginalPosition( ( Position ) originalPositionField.getSelectedItem() );
    staffer.setSecondJobPosition( ( Position ) secondJobPositionField.getSelectedItem() );

    if( this.isNewStaffer )
    {
      TeachersService.getTeacherList().add( staffer );
      isNewStaffer = false;
    }

    for( Rate item : RateService.getRateByTeacher( staffer.getId() ) )
    {
      RateService.removeRate( item.getId() );
    }
    for( Rate item : rateTableModel.getObjectList() )
    {
      RateService.getRateList().add( item.clone() );
    }
    rateTableModel.canselChange( staffer.getId() );
    if( model != null )
    {

      model.updateData();
    }
  }

  private void initComponents()
  {

    lastNameField = createJTextField();
    firstNameField = createJTextField();
    patrNameField = createJTextField();

	buttonGroup = new ButtonGroup();
	contract = new JRadioButton("Контракт");
	dogovor = new JRadioButton("Договор");
	buttonGroup.add(contract);
	buttonGroup.add(dogovor);
    jDateRange = new JDateRangeField();
	
    rankField = createJComboBox( 250 );

    rankField.addItem( null );
    for( Rank rank : Rank.values() )
    {

      rankField.addItem( rank );
    }

    degreeField = createJComboBox( 250 );
    degreeField.addItem( null );
    for( Degree degree : Degree.values() )
    {

      degreeField.addItem( degree );
    }

    originalPositionField = createJComboBox( 250 );
    originalPositionField.addItem( null );
    for( Position position : Position.values() )
    {

      originalPositionField.addItem( position );
    }

    secondJobPositionField = createJComboBox( 250 );
    secondJobPositionField.addItem( null );
    for( Position position : Position.values() )
    {

      secondJobPositionField.addItem( position );
    }

    // ----

    passportDateIssueField = createJDateField( 250 );
    passportNumberField = createJTextField( 250 );
    passportPlaceIssueField = createJTextField( 250 );
    passportPrivateNumberField = createJTextField( 250 );
    passportInsuranceNumberField = createJTextField( 250 );
  }

  @Override
  public boolean showFrame( Object parent, InternalSecondJobStaffer staffer )
  {

    model = ( StafferTableModel ) parent;

    if( staffer != null )
    {

      this.staffer = staffer;
      this.isNewStaffer = false;
    }
    else
    {

      this.staffer = new InternalSecondJobStaffer();
      this.staffer.setId( UUIDUtils.getUUID() );
      this.isNewStaffer = true;
    }

    lastNameField.setText( this.staffer.getLastName() );
    firstNameField.setText( this.staffer.getFirstName() );
    patrNameField.setText( this.staffer.getPatronymicName() );

    jDateRange.setDateRange(this.staffer.getRange());
	contract.setSelected(this.staffer.isContract());
	dogovor.setSelected(!this.staffer.isContract());
    rankField.setSelectedItem( this.staffer.getRank() );
    degreeField.setSelectedItem( this.staffer.getDegree() );
    originalPositionField.setSelectedItem( this.staffer.getOriginalPosition() );
    secondJobPositionField.setSelectedItem( this.staffer.getSecondJobPosition() );

    // ---
    if( this.staffer.getPassport() != null )
    {
      passportDateIssueField.setDate( this.staffer.getPassport().getDateIssue() );
      passportNumberField.setText( this.staffer.getPassport().getNumber() );
      passportPlaceIssueField.setText( this.staffer.getPassport().getPlaceIssue() );
      passportPrivateNumberField.setText( this.staffer.getPassport().getPrivateNumber() );
      passportInsuranceNumberField.setText( this.staffer.getPassport().getInsuranceNumber() );
    }

    rateTableModel.canselChange( this.staffer.getId() );
    setVisible( true );

    return true;
  }

  // private void updateRatePanel(){
  //
  // if (staffer != null && staffer.getId() != null && staffer.getId().length()!=0){
  //
  // }
  // }
}