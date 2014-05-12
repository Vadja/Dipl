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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.iit.dr.data_model.Degree;
import org.iit.dr.data_model.Passport;
import org.iit.dr.data_model.Rank;
import org.iit.dr.data_model.Rate;
import org.iit.dr.data_model.RateType;
import org.iit.dr.data_model.role.PartTimeStaffer;
import org.iit.dr.services.RateService;
import org.iit.dr.services.TeachersService;
import org.iit.dr.utils.UUIDUtils;
import org.iit.dr.view.component.JDateField;
import org.iit.dr.view.component.JDateRangeField;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.model.table.common.TableModelConst;
import org.iit.dr.view.model.table.staffers.RateTableModel;
import org.iit.dr.view.model.table.staffers.StafferRektorTableModel;

import com.toedter.calendar.JDateChooserCellEditor;

/**
 * StudentsFrame.
 * 
 * @author Yuriy Karpovich
 */
public class PartTimeStafferProrektorFrame extends JInternalFrameExt<PartTimeStaffer>
{

  PartTimeStaffer staffer;

  StafferRektorTableModel model;

  RateTableModel rateTableModel;

  JTable rateTable;

  JTabbedPane jTabbedPane;

  // ------

  JTextField lastNameField;

  JTextField firstNameField;

  JTextField patrNameField;
/*
  JDateRangeField contractField;

  JDateRangeField dogovorField;
*/
  JDateRangeField jDateRange;

	JComboBox rankField;

	JComboBox degreeField;
	
	ButtonGroup buttonGroup;
	
	JRadioButton contract;
	
	JRadioButton dogovor;

  JTextField originalPositionField;

  JTextField workPlaceField;

  // ------

  JTextField passportNumberField;

  JTextField passportPrivateNumberField;

  JDateField passportDateIssueField;

  JTextField passportPlaceIssueField;

  JTextField passportInsuranceNumberField;

  // ----

  JDateField dateOfBirthdayField;

  JTextField addressField;

  JTextField phoneHomeField;

  JTextField phoneWorkField;

  JTextField phoneMobileField;

  JTextField educationField;

  JTextField specialtyField;

  JTextField childrenCountField;

  JTextField unpWorkField;

  JTextField pensionBookField;

  JTextField pensionAgencyField;

  protected void init()
  {

    setTitle( "Форма редактирования" );

    setMinimumSize( new Dimension( 670, 520 ) );
    rateTableModel = new RateTableModel();
  }

  protected void generateComponents()
  {

    initComponents();

    jTabbedPane = new JTabbedPane();

    JPanel leftJPane =
      createVPane( 5, createField( lastNameField, "Фамилия: " ), createField( firstNameField, "Имя: " ),
        createField( patrNameField, "Отчество: " ), createField(dogovor),
		createField(contract) );

    JPanel rightJPane =
      createVPane( 5, createField( jDateRange, "Срок: " ),
        createField( rankField, "Звание: " ), createField( degreeField, "Степень: " ),
        createField( originalPositionField, "Должность осн.: " ), createField( workPlaceField, "Место работы: " ) );

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
      createVPane( 5, createField( passportNumberField, "№ паспорта: " ),
        createField( passportPrivateNumberField, "Личный номер: " ),
        createField( passportDateIssueField, "Дата выдачи: " ),
        createField( passportPlaceIssueField, "Место выдачи (структура): " ),
        createField( passportInsuranceNumberField, "Страховой номер: " ) );
    jTabbedPane.addTab( "Паспорт", passportPane );

    JPanel dopPane =
      createVPane( 5, createField( dateOfBirthdayField, "Дата рождения: " ), createField( addressField, "Адрес: " ),
        createField( phoneHomeField, "Домашний телефон: " ), createField( phoneWorkField, "Рабочий телефон: " ),
        createField( phoneMobileField, "Мобильный телефон: " ), createField( educationField, "Образование: " ),
        createField( specialtyField, "Специальность: " ), createField( childrenCountField, "Количество детей: " ),
        createField( unpWorkField, "УНП (Осн. работа): " ),
        createField( pensionBookField, "Номер пенсионного удостоверения: " ),
        createField( pensionAgencyField, "Орган, выплачивающий пенсию: " ) );
    jTabbedPane.addTab( "Дополнительные", dopPane );

    rateTable = new JTable( rateTableModel );
    rateTable.setDefaultEditor( Date.class, new JDateChooserCellEditor() );

    JComboBox rateTypeBox = new JComboBox();
    rateTypeBox.addItem( null );
    for( RateType rateType : RateType.values() )
    {

      rateTypeBox.addItem( rateType );
    }
    rateTable.setDefaultEditor( RateType.class, new DefaultCellEditor( rateTypeBox ) );

    JButton addButton = new JButton( "Добавить" );
    addButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        Rate rate = new Rate();
        rate.setId( UUIDUtils.getUUID() );
        rate.setTeacher( staffer.getId() );
        rate.setRange(jDateRange.getDateRange());
        RateService.getRateList().add( rate );

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
          String guid = ( String ) rateTableModel.getValueAt( rateTable.getSelectedRow(), TableModelConst.ID_COLUMN );
          RateService.removeRate( guid );

          rateTableModel.updateData( staffer.getId() );
          rateTable.updateUI();
        }
      }
    } );
    JPanel jButtonPanel = createNorthButtonPane( 5, addButton, removeButton );

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
    staffer.setOriginalPosition( checkFieldNull( originalPositionField ) );
    staffer.setWorkPlace( workPlaceField.getText() );

    staffer.setDateOfBirthday( dateOfBirthdayField.getDate() );

    staffer.setAddress( addressField.getText() );
    staffer.setPhoneHome( phoneHomeField.getText() );
    staffer.setPhoneWork( phoneWorkField.getText() );
    staffer.setPhoneMobile( phoneMobileField.getText() );

    staffer.setEducation( educationField.getText() );
    staffer.setSpecialty( specialtyField.getText() );
    staffer.setChildrenCount( childrenCountField.getText() );

    staffer.setUnpWork( unpWorkField.getText() );
    staffer.setPensionBook( pensionBookField.getText() );
    staffer.setPensionAgency( pensionAgencyField.getText() );

    if( staffer.getId() == null )
    {

      staffer.setId( UUIDUtils.getUUID() );
      staffer.setProrektor( true );
      TeachersService.getTeacherList().add( staffer );
    }

    if( model != null )
    {

      model.updateData();
    }
  }

  private void initComponents()
  {
	  buttonGroup = new ButtonGroup();
		contract = new JRadioButton("Контракт");
		dogovor = new JRadioButton("Договор");
		buttonGroup.add(contract);
		buttonGroup.add(dogovor);
    lastNameField = createJTextField();
    firstNameField = createJTextField();
    patrNameField = createJTextField();
    workPlaceField = createJTextField( 250 );

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

    originalPositionField = createJTextField( 250 );

    // ----

    passportDateIssueField = createJDateField( 250 );
    passportNumberField = createJTextField( 250 );
    passportPlaceIssueField = createJTextField( 250 );
    passportPrivateNumberField = createJTextField( 250 );
    passportInsuranceNumberField = createJTextField( 250 );

    // ----

    dateOfBirthdayField = createJDateField( 250 );

    addressField = createJTextField( 250 );

    phoneHomeField = createJTextField( 250 );
    phoneWorkField = createJTextField( 250 );
    phoneMobileField = createJTextField( 250 );

    educationField = createJTextField( 250 );
    specialtyField = createJTextField( 250 );
    childrenCountField = createJTextField( 250 );

    unpWorkField = createJTextField( 250 );

    pensionBookField = createJTextField( 250 );
    pensionAgencyField = createJTextField( 250 );
  }

  public boolean showFrame( Object parent, PartTimeStaffer staffer )
  {

    model = ( StafferRektorTableModel ) parent;

    if( staffer != null )
    {

      this.staffer = staffer;
    }
    else
    {

      this.staffer = new PartTimeStaffer();
    }

    lastNameField.setText( this.staffer.getLastName() );
    firstNameField.setText( this.staffer.getFirstName() );
    patrNameField.setText( this.staffer.getPatronymicName() );

	jDateRange.setDateRange(this.staffer.getRange());
	contract.setSelected(this.staffer.isContract());
	dogovor.setSelected(!this.staffer.isContract());
    rankField.setSelectedItem( this.staffer.getRank() );
    degreeField.setSelectedItem( this.staffer.getDegree() );
    originalPositionField.setText( this.staffer.getOriginalPosition() );
    workPlaceField.setText( this.staffer.getWorkPlace() );

    // ---
    if( this.staffer.getPassport() != null )
    {
      passportDateIssueField.setDate( this.staffer.getPassport().getDateIssue() );
      passportNumberField.setText( this.staffer.getPassport().getNumber() );
      passportPlaceIssueField.setText( this.staffer.getPassport().getPlaceIssue() );
      passportPrivateNumberField.setText( this.staffer.getPassport().getPrivateNumber() );
      passportInsuranceNumberField.setText( this.staffer.getPassport().getInsuranceNumber() );
    }

    dateOfBirthdayField.setDate( this.staffer.getDateOfBirthday() );

    addressField.setText( this.staffer.getAddress() );

    phoneHomeField.setText( this.staffer.getPhoneHome() );
    phoneWorkField.setText( this.staffer.getPhoneWork() );
    phoneMobileField.setText( this.staffer.getPhoneMobile() );

    educationField.setText( this.staffer.getEducation() );
    specialtyField.setText( this.staffer.getSpecialty() );
    childrenCountField.setText( this.staffer.getChildrenCount() );

    unpWorkField.setText( this.staffer.getUnpWork() );

    pensionBookField.setText( this.staffer.getPensionBook() );
    pensionAgencyField.setText( this.staffer.getPensionAgency() );

    rateTableModel.updateData( this.staffer.getId() );

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