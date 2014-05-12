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
import org.iit.dr.data_model.Departament;
import org.iit.dr.data_model.Passport;
import org.iit.dr.data_model.Rank;
import org.iit.dr.data_model.Rate;
import org.iit.dr.data_model.role.PartTimeStaffer;
import org.iit.dr.data_model.role.Person;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.documents.word.write.staffers.PR10DocumentGenerator;
import org.iit.dr.services.RateService;
import org.iit.dr.services.TeachersService;
import org.iit.dr.utils.DateUtils;
import org.iit.dr.utils.FileUtils;
import org.iit.dr.utils.UUIDUtils;
import org.iit.dr.view.component.JDateField;
import org.iit.dr.view.component.JDateRangeField;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.model.table.staffers.PartTimeRateTableModel;
import org.iit.dr.view.model.table.staffers.StafferTableModel;

import com.toedter.calendar.JDateChooserCellEditor;

/**
 * StudentsFrame.
 * 
 * @author Yuriy Karpovich
 */
public class PartTimeStafferFrame extends JInternalFrameExt<PartTimeStaffer>
{

  private boolean isNewStaffer;

  PartTimeStaffer staffer;

  StafferTableModel model;

  PartTimeRateTableModel rateTableModel;

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

  @Override
  protected void init()
  {

    setTitle( "Форма редактирования: Почасовик" );

    setMinimumSize( new Dimension( 670, 520 ) );
    rateTableModel = new PartTimeRateTableModel();
  }

  @Override
  protected void generateComponents()
  {

    initComponents();

    jTabbedPane = new JTabbedPane();

    JPanel leftJPane =
      createVPane( 5, createField( lastNameField, "Фамилия: " ), createField( firstNameField, "Имя: " ), createField(
        patrNameField, "Отчество: " ), createField(dogovor), createField(contract) );

    JPanel rightJPane =
      createVPane( 5, createField( jDateRange, "Срок: " ), createField( rankField, "Звание: " ), createField( degreeField, "Степень: " ), createField(
          originalPositionField, "Должность осн.: " ), createField( workPlaceField, "Место работы: " ) );

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

    JButton dpButton = new JButton( "Сформировать договор" );
    dpButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        PR10DocumentGenerator pr10DocumentGenerator = new PR10DocumentGenerator();
        for( Rate item : RateService.getRateByTeacher( staffer.getId() ) )
        {

          pr10DocumentGenerator.generate( FileUtils.getCommonDocumentPath( "Форма-ПР-10 [" + staffer.getShortName()
            + "]" + item.getSubject() + ".doc" ), 
            new String[] 
            {getFullName( staffer ) + getDegreeRank( staffer ),
            getFullName( staffer ), 
            DateUtils.dateToString( staffer.getDateOfBirthday() ),
            getNotNullString( staffer.getPassport().getNumber() ),
            DateUtils.dateToString( staffer.getPassport().getDateIssue() ),
            getNotNullString( staffer.getPassport().getPlaceIssue() ),
            getNotNullString( staffer.getPassport().getPrivateNumber() ),
            getNotNullString( staffer.getPassport().getInsuranceNumber() ), 
            getNotNullString( staffer.getAddress() ),
            getNotNullString( staffer.getPhoneWork() ), 
            getNotNullString( staffer.getPhoneHome() ),
            getNotNullString( staffer.getPhoneMobile() ), 
            getNotNullString( staffer.getEducation() ),
            getNotNullString( staffer.getSpecialty() ),
            staffer.getDegree() != null ? staffer.getDegree().getRusNameShort() : "",
            staffer.getRank() != null ? staffer.getRank().getRusName() : "",
            getNotNullString( staffer.getChildrenCount() ), getNotNullString( staffer.getWorkPlace() ),
            getNotNullString( staffer.getUnpWork() ), getNotNullString( staffer.getOriginalPosition() ),
            getFullName( staffer ) + getDegreeRank( staffer ), getDegreeRankShort( staffer ),
            getNotNullString( staffer.getPensionBook() ), getNotNullString( staffer.getPensionAgency() ),
            getNotNullString( item.getSubject() ), getCountHours( item.getCountHour() )
           // DateUtils.dateToString( item.getRange().getStart() ), DateUtils.dateToString( item.getRange().getEnd()) 
            } );
        }
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

    JPanel dopPane =
      createVPane( 5, createField( dateOfBirthdayField, "Дата рождения: " ), createField( addressField, "Адрес: " ),
        createField( phoneHomeField, "Домашний телефон: " ), createField( phoneWorkField, "Рабочий телефон: " ),
        createField( phoneMobileField, "Мобильный телефон: " ), createField( educationField, "Образование: " ),
        createField( specialtyField, "Специальность: " ), createField( childrenCountField, "Количество детей: " ),
        createField( unpWorkField, "УНП (Осн. работа): " ), createField( pensionBookField,
          "Номер пенсионного удостоверения: " ), createField( pensionAgencyField, "Орган, выплачивающий пенсию: " ) );
    jTabbedPane.addTab( "Дополнительные", dopPane );

    JPanel ratePanel = new JPanel( new BorderLayout() );

    rateTable = new JTable( rateTableModel );
    rateTable.setDefaultEditor( Date.class, new JDateChooserCellEditor() );

    JComboBox departamentBox = new JComboBox();
    departamentBox.addItem( null );
    for( Departament item : Departament.values() )
    {

      departamentBox.addItem( item );
    }
    rateTable.setDefaultEditor( Departament.class, new DefaultCellEditor( departamentBox ) );

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
    getContentPane().add( createNorthButtonPane( 10, okButton, applyButton, cancelButton, dpButton ),
      BorderLayout.SOUTH );
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

  @Override
  public boolean showFrame( Object parent, PartTimeStaffer staffer )
  {

    model = ( StafferTableModel ) parent;

    if( staffer != null )
    {

      this.staffer = staffer;
      this.isNewStaffer = false;
    }
    else
    {

      this.staffer = new PartTimeStaffer();
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

    rateTableModel.canselChange( this.staffer.getId() );

    setVisible( true );

    return true;
  }

  private String getNotNullString( String value )
  {

    return value != null ? value : "";

  }

  private String getDegreeRank( Teacher teacher )
  {

    StringBuilder builder = new StringBuilder();

    if( teacher != null )
    {
    	
      if( teacher.getDegree() != null )
      {

        builder.append( ", " );
        if( teacher.getDegree().getRusNameShort() != null )
        {
          builder.append( teacher.getDegree().getRusNameShort() );
        }
      }

      if( teacher.getRank() != null )
      {

        builder.append( ", " );

        if( teacher.getRank().getRusName() != null )
        {
          builder.append( teacher.getRank().getRusName().toLowerCase() );
        }
      }

    }

    return builder.toString();

  }

  private String getDegreeRankShort( Teacher teacher )
  {

    StringBuilder builder = new StringBuilder();

    if( teacher != null )
    {

      if( teacher.getDegree() != null )
      {
        if( teacher.getDegree().getRusNameShort() != null )
        {
          builder.append( teacher.getDegree().getRusNameShort() );
        }
      }

      if( teacher.getRank() != null )
      {

        builder.append( ", " );

        if( teacher.getRank().getRusName() != null )
        {
          builder.append( teacher.getRank().getRusName().toLowerCase() );
        }
      }

    }

    return builder.toString();

  }

  private String getCountHours( Integer hours )
  {
    return hours != null ? hours.toString() : "";
  }

  private String getFullName( Person person )
  {

    return person != null ? person.getFullName() : "";
  }
  // private void updateRatePanel(){
  //
  // if (staffer != null && staffer.getId() != null && staffer.getId().length()!=0){
  //
  // }
  // }
}