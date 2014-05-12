package org.iit.dr.view.form.practice;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.iit.dr.data_model.Company;
import org.iit.dr.data_model.PracticeWork;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.services.CompaniesService;
import org.iit.dr.services.StudentsService;
import org.iit.dr.services.TeachersService;
import org.iit.dr.utils.MemoryManager;
import org.iit.dr.view.component.JButtonSelect;
import org.iit.dr.view.component.JDateField;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.form.companies.CompanySelectButtonDialog;
import org.iit.dr.view.form.staffers.StafferListSelectButtonFrame;
import org.iit.dr.view.form.students.StudentListFrame;
import org.iit.dr.view.model.table.practice.PracticeTableModel;

/**
 * GraduateFrame.
 * 
 * @author Yuriy Karpovich
 */
public class PracticeFrame extends JInternalFrameExt<PracticeWork>
{
  private static final long serialVersionUID = 1048755599503451634L;

  PracticeWork practiceWork;

  PracticeTableModel model;

  StafferListSelectButtonFrame stafferListFrame;

  StudentListFrame studentListFrame;

  CompanySelectButtonDialog companiesListFrame;

  // ---

  JButtonSelect studentField;

  JTextArea workTitle;

  JTextArea descField;

  JButtonSelect managerField;

  JButtonSelect consultantField;

  JDateField dateOfIssueField;

  JDateField dateOfDefenceField;

  JButtonSelect companyField;

  JComboBox markField;

  JTextField orderNumberField;

  public PracticeFrame() throws HeadlessException
  {
    stafferListFrame =
      ( StafferListSelectButtonFrame ) MemoryManager.getObject( StafferListSelectButtonFrame.class, true );
    studentListFrame = ( StudentListFrame ) MemoryManager.getObject( StudentListFrame.class, true );
    companiesListFrame = ( CompanySelectButtonDialog ) MemoryManager.getObject( CompanySelectButtonDialog.class );
  }

  @Override
  public boolean showFrame( Object parent, PracticeWork practiceWork )
  {

    model = ( PracticeTableModel ) parent;

    if( practiceWork != null )
    {

      this.practiceWork = practiceWork;
    }
    else
    {

      this.practiceWork = new PracticeWork();
    }

    studentField.setUserObject( getStudentUserOject( this.practiceWork.getStudentId() ) );
    workTitle.setText( this.practiceWork.getTitle() );
    descField.setText( this.practiceWork.getDescription() );
    orderNumberField.setText( this.practiceWork.getOrderNumber() );
    managerField.setUserObject( getTeacherUserOject( this.practiceWork.getManagerId() ) );
    consultantField.setUserObject( getTeacherUserOject( this.practiceWork.getConsultantId() ) );

    dateOfIssueField.setDate( this.practiceWork.getDateOfIssue() );
    dateOfDefenceField.setDate( this.practiceWork.getDateOfDefence() );

    companyField.setUserObject( getCompanyUserOject( this.practiceWork.getCompanyId() ) );
    markField.setSelectedItem( this.practiceWork.getMark() );

    model.updateData();
    setVisible( true );

    return true;
  }

  private Student getStudentUserOject( String studentId )
  {

    if( studentId != null && studentId.length() != 0 )
    {

      Student student = StudentsService.getStudent( studentId );

      if( student != null )
      {

        return student;
      }
    }

    return null;
  }

  private Teacher getTeacherUserOject( String teacherId )
  {

    if( teacherId != null && teacherId.length() != 0 )
    {

      Teacher teacher = TeachersService.getTeacher( teacherId );

      if( teacher != null )
      {

        return teacher;
      }
    }

    return null;
  }

  private Company getCompanyUserOject( String companyName )
  {
    if( companyName != null && companyName.length() != 0 )
    {
      Company company = CompaniesService.getCompany( companyName );
      if( company != null )
      {
        return company;
      }
    }
    return null;
  }

  private String getTeacherFromUserOject( Teacher teacher )
  {

    return teacher != null ? teacher.getId() : null;
  }

  private String getStudentFromUserOject( Student student )
  {

    return student != null ? student.getId() : null;
  }

  private String getCompanyFromUserOject( Company company )
  {
    return company != null ? company.getId() : null;
  }

  @Override
  protected void init()
  {

    setTitle( "Форма редактирования: Преддипломная праткика" );

    setMinimumSize( new Dimension( 420, 480 ) );
  }

  private void initComponents()
  {

    studentField = createJButtonSelect();
    workTitle = createJTextArea( 250 );
    descField = createJTextArea( 250 );
    managerField = createJButtonSelect();
    consultantField = createJButtonSelect();

    dateOfIssueField = createJDateField();
    dateOfDefenceField = createJDateField();
    orderNumberField = createJTextField();
    companyField = createJButtonSelect();
    markField = createJComboBox();
    markField.addItem( null );

    for( Integer i = 4; i <= 10; i++ )
    {
      markField.addItem( i );
    }
  }

  @Override
  protected void generateComponents()
  {

    initComponents();

    JPanel leftJPane =
      createVPane( 2, createField( studentField, "Студент: " ), createField( createScrollPane( workTitle ), "Тема: " ),
        createField( createScrollPane( descField ), "Описание: " ), createField( companyField, "Предприятие: " ),
        createField( managerField, "Руководитель: " ), createField( orderNumberField, "№ приказа" ),
        createField( consultantField, "Консультант: " ), createField( dateOfIssueField, "Дата выдачи задания: " ),
        createField( dateOfDefenceField, "Дата сдачи: " ), createField( markField, "Отметка: " ) );

    JButton applyButton = new JButton( "Применить" );
    applyButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        practiceWork.setStudentId( getStudentFromUserOject( ( Student ) studentField.getUserObject() ) );
        practiceWork.setTitle( checkFieldNull( workTitle ) );
        practiceWork.setDescription( checkFieldNull( descField ) );
        practiceWork.setManagerId( getTeacherFromUserOject( ( Teacher ) managerField.getUserObject() ) );
        practiceWork.setConsultantId( getTeacherFromUserOject( ( Teacher ) consultantField.getUserObject() ) );

        practiceWork.setDateOfIssue( dateOfIssueField.getDate() );
        practiceWork.setDateOfDefence( dateOfDefenceField.getDate() );
        practiceWork.setOrderNumber( checkFieldNull( orderNumberField ) );
        practiceWork.setCompanyId( getCompanyFromUserOject( ( Company ) companyField.getUserObject() ) );
        practiceWork.setMark( ( Integer ) markField.getSelectedItem() );
        model.updateData();
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

    getContentPane().add( leftJPane, BorderLayout.CENTER );
    getContentPane().add( createButtonPane( 10, applyButton, cancelButton ), BorderLayout.SOUTH );

    studentField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        studentListFrame.showFrame( PracticeFrame.this, studentField );
      }
    } );

    managerField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( PracticeFrame.this, managerField );
      }
    } );

    consultantField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( PracticeFrame.this, consultantField );
      }
    } );

    companyField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        companiesListFrame.showFrame( PracticeFrame.this, companyField );
      }
    } );
  }
}