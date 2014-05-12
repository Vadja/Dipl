package org.iit.dr.view.form.students;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.iit.dr.data_model.Sex;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.data_model.unit.OrganizationUnit;
import org.iit.dr.services.OrganizationUnitService;
import org.iit.dr.services.StudentsService;
import org.iit.dr.utils.MemoryManager;
import org.iit.dr.utils.UUIDUtils;
import org.iit.dr.view.component.JButtonSelect;
import org.iit.dr.view.component.JDateField;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.model.table.students.StudentsTableModel;

/**
 * Student.
 * 
 * @author Yuriy Karpovich
 */
public class StudentFrame extends JInternalFrameExt<Student>
{

  JTabbedPane jTabbedPane;

  // Общие
  JTextField personalFileField;

  JComboBox sexField;

  JTextField lastNameField;

  JTextField firstNameField;

  JTextField patrNameField;

  JDateField birthDateField;

  JTextField cityField;

  JTextField nationalityField;

  JTextField sociallyPlaceField;

  JTextField familyStateField;

  JTextField countChildrenField;

  JTextField prikazField;

  JDateField prikazDateField;

  JDateField incomingField;

  JButtonSelect groupField;

  JCheckBox budgetCheck;

  JTextField addressField;

  JTextField registrationAddressField;

  // сведения о родителях

  JTextField fioMotherField;

  JTextField workMotherField;

  JTextField positionMotherField;

  JTextField ageMotherField;

  JTextField cityMotherField;

  JTextField fioFatherField;

  JTextField workFatherField;

  JTextField positionFatherField;

  JTextField ageFatherField;

  JTextField cityFatherField;

  // Образование

  // Контакты

  JTextField skypeField;

  JTextField icqField;

  JTextField emailField;

  JTextField phoneHomeField;

  JTextField mobilePhoneField;

  Student student;

  StudentsTableModel model;

  GroupTreeFrame groupTreeFrame;

  public StudentFrame() throws HeadlessException
  {

    groupTreeFrame = ( GroupTreeFrame ) MemoryManager.getObject( GroupTreeFrame.class, true );
    groupTreeFrame.updateData();
  }

  @Override
  public boolean showFrame( Object parent, Student student )
  {

    model = ( StudentsTableModel ) parent;

    if( student != null )
    {

      this.student = student;
    }
    else
    {

      this.student = new Student();
    }
    // Общие
    personalFileField.setText( this.student.getPersonalFile() );
    sexField.setSelectedItem( this.student.getSex() );
    lastNameField.setText( this.student.getLastName() );
    firstNameField.setText( this.student.getFirstName() );
    patrNameField.setText( this.student.getPatronymicName() );
    birthDateField.setDate( this.student.getBirthDate() );
    nationalityField.setText( this.student.getNationality() );
    sociallyPlaceField.setText( this.student.getSociallyPlace() );
    familyStateField.setText( this.student.getFamilyState() );
    countChildrenField.setText( this.student.getCountChildren() );
    prikazField.setText( this.student.getPrikaz() );
    prikazDateField.setDate( this.student.getPrikazDate() );
    if( this.student.getGroup() != null && this.student.getGroup().length() != 0 )
    {
      OrganizationUnit organizationUnit = OrganizationUnitService.getOrganizationUnit( this.student.getGroup() );
      groupField.setUserObject( organizationUnit );
    }
    else
    {
      groupField.setUserObject( null );
    }
    cityField.setText( this.student.getFromCity() );
    incomingField.setDate( this.student.getIncoming() );
    budgetCheck.setSelected( this.student.getBudget() );
    addressField.setText( this.student.getAddress() );
    registrationAddressField.setText( this.student.getRegistrationAddress() );

    // сведения о родителях
    fioFatherField.setText( this.student.getFioFather() );
    workFatherField.setText( this.student.getWorkFather() );
    positionFatherField.setText( this.student.getPositionFather() );
    ageFatherField.setText( this.student.getAgeFather() );
    cityFatherField.setText( this.student.getCityFather() );
    fioMotherField.setText( this.student.getFioMother() );
    workMotherField.setText( this.student.getWorkMother() );
    positionMotherField.setText( this.student.getPositionMother() );
    ageMotherField.setText( this.student.getAgeMother() );
    cityMotherField.setText( this.student.getCityMother() );

    // контакты
    emailField.setText( this.student.getEmail() );
    skypeField.setText( this.student.getSkype() );
    icqField.setText( this.student.getIcq() );
    phoneHomeField.setText( this.student.getHomePhone() );
    mobilePhoneField.setText( this.student.getMobilePhone() );

    setVisible( true );

    return true;
  }

  @Override
  protected void init()
  {

    setTitle( "Форма редактирования: Студент" );

    setMinimumSize( new Dimension( 700, 500 ) );
  }

  private void initComponents()
  {
    // Общие
    personalFileField = createJTextField();
    sexField = createJComboBox();
    sexField.addItem( null );
    for( Sex sex : Sex.values() )
    {
      sexField.addItem( sex );
    }
    familyStateField = createJTextField();
    countChildrenField = createJTextField();
    prikazField = createJTextField();
    prikazDateField = createJDateField();
    sociallyPlaceField = createJTextField();
    nationalityField = createJTextField();
    lastNameField = createJTextField();
    firstNameField = createJTextField();
    patrNameField = createJTextField();
    birthDateField = createJDateField();
    cityField = createJTextField();
    incomingField = createJDateField();
    groupField = createJButtonSelect();

    budgetCheck = createJCheckBox( "бюджет" );
    addressField = createJTextField();
    registrationAddressField = createJTextField();

    // сведения о родителях гавнокод
    fioMotherField = createJTextField( 250 );
    workMotherField = createJTextField( 250 );
    positionMotherField = createJTextField( 250 );
    ageMotherField = createJTextField( 250 );
    cityMotherField = createJTextField( 250 );
    fioFatherField = createJTextField( 250 );
    workFatherField = createJTextField( 250 );
    positionFatherField = createJTextField( 250 );
    ageFatherField = createJTextField( 250 );
    cityFatherField = createJTextField( 250 );

    // образование

    // контакты
    phoneHomeField = createJTextField( 250 );
    mobilePhoneField = createJTextField( 250 );
    skypeField = createJTextField( 250 );
    icqField = createJTextField( 250 );
    emailField = createJTextField( 250 );

  }

  @Override
  protected void generateComponents()
  {

    initComponents();

    jTabbedPane = new JTabbedPane();

    // Общие
    JPanel leftJPane =
      createVPane( 5, createField( personalFileField, "Номер личного дела:" ), createField( sexField, "Пол:" ),
        createField( lastNameField, "Фамилия: " ), createField( firstNameField, "Имя: " ), createField( patrNameField,
          "Отчество: " ), createField( birthDateField, "Дата рождения:" ), createField( cityField, "Место рождения:" ),
        createField( addressField, "Место жительсва" ), createField( nationalityField, "Гражданство:" ) );

    JPanel rightJPane =
      createVPane( 5, createField( registrationAddressField, "Место прописки" ), createField( sociallyPlaceField,
        "Социальное положение:" ), createField( familyStateField, "Семейное положение:" ), createField(
        countChildrenField, "Количество детей:" ), createField( prikazField, "Номер приказа:" ), createField(
        prikazDateField, "Дата приказа:" ), createField( groupField, "Группа/подгруппа:" ), createField( budgetCheck,
        "Бюджет:" ) );

    JPanel allJPane = createHPane( 20, leftJPane, rightJPane );

    JPanel parentPanel =
      createVPane( 5, createField( fioMotherField, "ФИО матери" ), createField( workMotherField, "Место работы" ),
        createField( positionMotherField, "Должность" ), createField( ageMotherField, "Возраст" ), createField(
          cityMotherField, "Место проживания" ), createField( fioFatherField, "ФИО отца" ), createField(
          workFatherField, "Место работы" ), createField( positionFatherField, "Должность" ), createField(
          ageFatherField, "Возраст" ), createField( cityFatherField, "Место проживания" ) );

    JPanel contactJPane =
      createVPane( 5, createField( emailField, "E-mail: " ), createField( skypeField, "Skype: " ), createField(
        icqField, "ICQ: " ), createField( phoneHomeField, "Домашнтй телефон" ), createField( mobilePhoneField,
        "Мобильный телефон" ) );

    JButton applyButton = new JButton( "Применить" );
    applyButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        student.setPersonalFile( checkFieldNull( personalFileField ) );
        student.setSex( ( Sex ) sexField.getSelectedItem() );
        student.setLastName( checkFieldNull( lastNameField ) );
        student.setFirstName( checkFieldNull( firstNameField ) );
        student.setPatronymicName( checkFieldNull( patrNameField ) );
        student.setBirthDate( birthDateField.getDate() );
        student.setFromCity( checkFieldNull( cityField ) );
        student.setNationality( checkFieldNull( nationalityField ) );
        student.setSociallyPlace( checkFieldNull( sociallyPlaceField ) );
        student.setFamilyState( checkFieldNull( familyStateField ) );
        student.setCountChildren( checkFieldNull( countChildrenField ) );
        student.setPrikaz( checkFieldNull( prikazField ) );
        student.setPrikazDate( prikazDateField.getDate() );
        OrganizationUnit organizationUnit = ( OrganizationUnit ) groupField.getUserObject();
        student.setGroup( organizationUnit != null ? organizationUnit.getId() : null );

        student.setIncoming( incomingField.getDate() );
        student.setBudget( budgetCheck.isSelected() );
        student.setAddress( checkFieldNull( addressField ) );
        student.setRegistrationAddress( checkFieldNull( registrationAddressField ) );

        // сведения о родителях
        student.setFioFather( checkFieldNull( fioFatherField ) );
        student.setWorkFather( checkFieldNull( workFatherField ) );
        student.setPositionFather( checkFieldNull( positionFatherField ) );
        student.setAgeFather( checkFieldNull( ageFatherField ) );
        student.setCityFather( checkFieldNull( cityFatherField ) );
        student.setFioMother( checkFieldNull( fioMotherField ) );
        student.setWorkMother( checkFieldNull( workMotherField ) );
        student.setPositionMother( checkFieldNull( positionMotherField ) );
        student.setAgeMother( checkFieldNull( ageMotherField ) );
        student.setCityMother( checkFieldNull( cityMotherField ) );

        student.setSkype( checkFieldNull( skypeField ) );
        student.setIcq( checkFieldNull( icqField ) );
        student.setEmail( checkFieldNull( emailField ) );
        student.setHomePhone( checkFieldNull( phoneHomeField ) );
        student.setMobilePhone( checkFieldNull( mobilePhoneField ) );

        if( student.getId() == null )
        {

          student.setId( UUIDUtils.getUUID() );
          StudentsService.getStudentList().add( student );
        }

        if( model != null )
        {

          model.updateData();
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
    groupField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        groupTreeFrame.showFrame( StudentFrame.this, groupField );
      }
    } );
    jTabbedPane.addTab( "Общие", allJPane );
    jTabbedPane.addTab( "Сведения о родителях", parentPanel );
    jTabbedPane.addTab( "Контакты", contactJPane );

    getContentPane().add( jTabbedPane, BorderLayout.CENTER );
    getContentPane().add( createButtonPane( 10, applyButton, cancelButton ), BorderLayout.SOUTH );
  }
}
