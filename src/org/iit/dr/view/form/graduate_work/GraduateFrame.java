package org.iit.dr.view.form.graduate_work;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.iit.dr.data_model.GraduateWork;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.services.GraduateWorkService;
import org.iit.dr.services.StudentsService;
import org.iit.dr.services.TeachersService;
import org.iit.dr.utils.MemoryManager;
import org.iit.dr.utils.UUIDUtils;
import org.iit.dr.view.component.JButtonSelect;
import org.iit.dr.view.component.JDateField;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.form.staffers.StafferListSelectButtonFrame;
import org.iit.dr.view.form.students.StudentListFrame;
import org.iit.dr.view.model.table.graduate_work.GraduateWorkTableModel;

/**
 * GraduateFrame.
 * 
 * @author Yuriy Karpovich
 */
public class GraduateFrame extends JInternalFrameExt<GraduateWork>
{
  private static final long serialVersionUID = -2655097880878423532L;

  GraduateWork graduateWork;

  GraduateWorkTableModel model;

  StafferListSelectButtonFrame stafferListFrame;

  StudentListFrame studentListFrame;

  // ---

  JTextField numberField;

  JButtonSelect studentField;

  JTextArea workTitle;

  JTextArea descField;

  JButtonSelect managerField;

  JButtonSelect consultantBySpecialityField;

  JButtonSelect consultantByEconomicsField;

  JButtonSelect consultantByProtectionOfLaborField;

  JButtonSelect consultantByNormalInspectionField;

  JButtonSelect reviewerField;

  JDateField dateOfIssueField;

  public GraduateFrame() throws HeadlessException
  {

    stafferListFrame =
      ( StafferListSelectButtonFrame ) MemoryManager.getObject( StafferListSelectButtonFrame.class, true );
    studentListFrame = ( StudentListFrame ) MemoryManager.getObject( StudentListFrame.class, true );

  }

  @Override
  public boolean showFrame( Object parent, GraduateWork graduateWork )
  {

    model = ( GraduateWorkTableModel ) parent;

    if( graduateWork != null )
    {

      this.graduateWork = graduateWork;
    }
    else
    {

      this.graduateWork = new GraduateWork();
    }

    numberField.setText( this.graduateWork.getNumber() );
    studentField.setUserObject( getStudentUserOject( this.graduateWork.getStudentId() ) );
    workTitle.setText( this.graduateWork.getTitle() );
    descField.setText( this.graduateWork.getDescription() );
    managerField.setUserObject( getTeacherUserOject( this.graduateWork.getManagerId() ) );
    consultantBySpecialityField.setUserObject( getTeacherUserOject( this.graduateWork.getConsultantIdBySpeciality() ) );
    consultantByEconomicsField.setUserObject( getTeacherUserOject( this.graduateWork.getConsultantIdByEconomics() ) );
    consultantByProtectionOfLaborField.setUserObject( getTeacherUserOject( this.graduateWork
      .getConsultantIdByProtectionOfLabor() ) );
    consultantByNormalInspectionField.setUserObject( getTeacherUserOject( this.graduateWork
      .getConsultantIdByNormalInspection() ) );
    reviewerField.setUserObject( getTeacherUserOject( this.graduateWork.getReviewerId() ) );

    dateOfIssueField.setDate( this.graduateWork.getDateOfIssue() );

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
      return TeachersService.getTeacher( teacherId );
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
    setTitle( "Форма редактирования: Дипломный проект" );
    setMinimumSize( new Dimension( 600, 480 ) );
  }

  private void initComponents()
  {
    numberField = createJTextField();
    studentField = createJButtonSelect();
    workTitle = createJTextArea( 450 );
    descField = createJTextArea( 450 );
    managerField = createJButtonSelect();
    consultantBySpecialityField = createJButtonSelect();
    consultantByEconomicsField = createJButtonSelect();
    consultantByProtectionOfLaborField = createJButtonSelect();
    consultantByNormalInspectionField = createJButtonSelect();
    reviewerField = createJButtonSelect();

    dateOfIssueField = createJDateField();
  }

  @Override
  protected void generateComponents()
  {

    initComponents();

    JPanel leftJPane =
      createVPane( 2, createField( numberField, "Номер по приказу: " ), createField( studentField, "Дипломник: " ),
        createField( createScrollPane( workTitle ), "Тема: " ),
        createField( createScrollPane( descField ), "Описание: " ), createField( managerField, "Руководитель: " ),
        createField( consultantBySpecialityField, "Консультант по специальности: " ),
        createField( consultantByEconomicsField, "Консультант по экономике: " ),
        createField( consultantByProtectionOfLaborField, "Консультант по охране труда: " ),
        createField( consultantByNormalInspectionField, "Консультант по нормоконтролю: " ),
        createField( reviewerField, "Рецензент: " ), createField( dateOfIssueField, "Дата выдачи задания: " ) );

    JButton applyButton = new JButton( "Применить" );
    applyButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        graduateWork.setNumber( checkFieldNull( numberField ) );
        graduateWork.setStudentId( getStudentFromUserOject( ( Student ) studentField.getUserObject() ) );
        graduateWork.setTitle( checkFieldNull( workTitle ) );
        graduateWork.setDescription( checkFieldNull( descField ) );
        graduateWork.setManagerId( getTeacherFromUserOject( ( Teacher ) managerField.getUserObject() ) );
        graduateWork.setConsultantIdBySpeciality( getTeacherFromUserOject( ( Teacher ) consultantBySpecialityField
          .getUserObject() ) );
        graduateWork.setConsultantIdByEconomics( getTeacherFromUserOject( ( Teacher ) consultantByEconomicsField
          .getUserObject() ) );
        graduateWork
          .setConsultantIdByProtectionOfLabor( getTeacherFromUserOject( ( Teacher ) consultantByProtectionOfLaborField
            .getUserObject() ) );
        graduateWork
          .setConsultantIdByNormalInspection( getTeacherFromUserOject( ( Teacher ) consultantByNormalInspectionField
            .getUserObject() ) );
        graduateWork.setReviewerId( getTeacherFromUserOject( ( Teacher ) reviewerField.getUserObject() ) );

        graduateWork.setDateOfIssue( dateOfIssueField.getDate() );

        if( graduateWork.getId() == null )
        {

          graduateWork.setId( UUIDUtils.getUUID() );
          GraduateWorkService.getGraduateWorkList().add( graduateWork );
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

    getContentPane().add( leftJPane, BorderLayout.CENTER );
    getContentPane().add( createButtonPane( 10, applyButton, cancelButton ), BorderLayout.SOUTH );

    studentField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        studentListFrame.showFrame( GraduateFrame.this, studentField );
      }
    } );

    managerField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( GraduateFrame.this, managerField );
      }
    } );

    consultantByEconomicsField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( GraduateFrame.this, consultantByEconomicsField );
      }
    } );

    consultantBySpecialityField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( GraduateFrame.this, consultantBySpecialityField );
      }
    } );

    consultantByProtectionOfLaborField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( GraduateFrame.this, consultantByProtectionOfLaborField );
      }
    } );

    consultantByNormalInspectionField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( GraduateFrame.this, consultantByNormalInspectionField );
      }
    } );

    reviewerField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( GraduateFrame.this, reviewerField );
      }
    } );
  }

}
