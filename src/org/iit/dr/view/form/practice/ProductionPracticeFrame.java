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

import org.iit.dr.data_model.PracticeWork;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.services.StudentsService;
import org.iit.dr.services.TeachersService;
import org.iit.dr.utils.MemoryManager;
import org.iit.dr.view.component.JButtonSelect;
import org.iit.dr.view.component.JDateField;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.form.staffers.StafferListSelectButtonFrame;
import org.iit.dr.view.form.students.StudentListFrame;
import org.iit.dr.view.model.table.practice.PracticeTableModel;

/**
 * ProductionPracticeFrame.
 * 
 * @author Yuriy Karpovich
 */
public class ProductionPracticeFrame extends JInternalFrameExt<PracticeWork>
{
  private static final long serialVersionUID = -7530819708081651649L;

  PracticeWork productionPracticeWork;

  PracticeTableModel model;

  StafferListSelectButtonFrame stafferListFrame;

  StudentListFrame studentListFrame;

  // ---

  JButtonSelect studentField;

  JTextArea workTitle;

  JTextArea descField;

  JButtonSelect managerField;

  JButtonSelect consultantField;

  JDateField dateOfIssueField;

  JDateField dateOfDefenceField;

  JTextField companyField;

  JComboBox markField;

  public ProductionPracticeFrame() throws HeadlessException
  {

    studentListFrame = ( StudentListFrame ) MemoryManager.getObject( StudentListFrame.class, true );
    stafferListFrame =
      ( StafferListSelectButtonFrame ) MemoryManager.getObject( StafferListSelectButtonFrame.class, true );
  }

  @Override
  public boolean showFrame( Object parent, PracticeWork productionPracticeWork )
  {

    model = ( PracticeTableModel ) parent;

    if( productionPracticeWork != null )
    {

      this.productionPracticeWork = productionPracticeWork;
    }
    else
    {

      this.productionPracticeWork = new PracticeWork();
    }

    studentField.setUserObject( getStudentUserOject( this.productionPracticeWork.getStudentId() ) );
    workTitle.setText( this.productionPracticeWork.getTitle() );
    descField.setText( this.productionPracticeWork.getDescription() );
    managerField.setUserObject( getTeacherUserOject( this.productionPracticeWork.getManagerId() ) );
    consultantField.setUserObject( getTeacherUserOject( this.productionPracticeWork.getConsultantId() ) );

    dateOfIssueField.setDate( this.productionPracticeWork.getDateOfIssue() );
    dateOfDefenceField.setDate( this.productionPracticeWork.getDateOfDefence() );

    companyField.setText( this.productionPracticeWork.getCompanyId() );
    markField.setSelectedItem( this.productionPracticeWork.getMark() );

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

  private String getTeacherFromUserOject( Teacher teacher )
  {

    return teacher != null ? teacher.getId() : null;
  }

  private String getStudentFromUserOject( Student student )
  {

    return student != null ? student.getId() : null;
  }

  @Override
  protected void init()
  {

    setTitle( "Форма редактирования: Производственная практика" );

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

    companyField = createJTextField( 250 );
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
        createField( createScrollPane( descField ), "Описание: " ), createField( managerField, "Руководитель: " ),
        createField( consultantField, "Консультант: " ), createField( companyField, "Предприятие: " ),
        createField( dateOfIssueField, "Дата выдачи задания: " ), createField( dateOfDefenceField, "Дата сдачи: " ),
        createField( markField, "Отметка: " ) );

    JButton applyButton = new JButton( "Применить" );
    applyButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        productionPracticeWork.setStudentId( getStudentFromUserOject( ( Student ) studentField.getUserObject() ) );
        productionPracticeWork.setTitle( checkFieldNull( workTitle ) );
        productionPracticeWork.setDescription( checkFieldNull( descField ) );
        productionPracticeWork.setManagerId( getTeacherFromUserOject( ( Teacher ) managerField.getUserObject() ) );
        productionPracticeWork.setConsultantId( getTeacherFromUserOject( ( Teacher ) consultantField.getUserObject() ) );

        productionPracticeWork.setDateOfIssue( dateOfIssueField.getDate() );
        productionPracticeWork.setDateOfDefence( dateOfDefenceField.getDate() );

        productionPracticeWork.setCompanyId( checkFieldNull( companyField ) );
        productionPracticeWork.setMark( ( Integer ) markField.getSelectedItem() );

        if( productionPracticeWork.getId() == null )
        {
          // TODO
          // productionPracticeWork.setId( UUIDUtils.getUUID() );
          // DataStorage.getInstance().getProductionPracticePlaceList().add( productionPracticeWork );
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

    getContentPane().add( leftJPane, BorderLayout.CENTER );
    getContentPane().add( createButtonPane( 10, applyButton, cancelButton ), BorderLayout.SOUTH );

    studentField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        studentListFrame.showFrame( ProductionPracticeFrame.this, studentField );
      }
    } );

    managerField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( ProductionPracticeFrame.this, managerField );
      }
    } );

    consultantField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( ProductionPracticeFrame.this, consultantField );
      }
    } );
  }

}