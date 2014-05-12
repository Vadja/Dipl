package org.iit.dr.view.form.graduate_work;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.iit.dr.data_model.DefenceGraduateWork;
import org.iit.dr.data_model.GraduateWork;
import org.iit.dr.data_model.GraduateWorkInfo;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.services.GraduateWorkService;
import org.iit.dr.services.TeachersService;
import org.iit.dr.utils.MemoryManager;
import org.iit.dr.utils.UUIDUtils;
import org.iit.dr.view.component.JButtonSelect;
import org.iit.dr.view.component.JDateField;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.form.staffers.StafferListSelectButtonFrame;
import org.iit.dr.view.form.students.StudentListFrame;
import org.iit.dr.view.model.table.graduate_work.DefenceGraduateWorkTableModel;

/**
 * DefenceGraduateWorkFrame.
 * 
 * @author Yuriy Karpovich
 */
public class DefenceGraduateWorkFrame extends JInternalFrameExt<DefenceGraduateWork>
{
  private static final long serialVersionUID = 2239279772331690859L;

  DefenceGraduateWork defenceGraduateWork;

  DefenceGraduateWorkTableModel model;

  DefenceGraduateWorkListFrame defenceGraduateWorkListFrame;

  JTabbedPane jTabbedPane;

  StafferListSelectButtonFrame stafferListFrame;

  StudentListFrame studentListFrame;

  GraduateWorkListFrame graduateWorkListFrame;

  // ---

  JTextField protocolNumberField;

  JButtonSelect graduateWorkField;

  JDateField actualDateField;

  JTextField startTimeField;

  JTextField endTimeField;

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

  // ---

  JTextField pageCountField;

  JTextField graphicCountField;

  JComboBox reviewMarkField;

  JTextField question1Field;

  JTextField question2Field;

  JTextField question3Field;

  JTextField question4Field;

  JTextField question5Field;

  JTextField question6Field;

  JButtonSelect question1AuthorField;

  JButtonSelect question2AuthorField;

  JButtonSelect question3AuthorField;

  JButtonSelect question4AuthorField;

  JButtonSelect question5AuthorField;

  JButtonSelect question6AuthorField;

  JTextField answerField;

  JTextField dopInfoField;

  JComboBox markField;

  JTextField opinionField;

  public DefenceGraduateWorkFrame() throws HeadlessException
  {

    stafferListFrame =
      ( StafferListSelectButtonFrame ) MemoryManager.getObject( StafferListSelectButtonFrame.class, true );
    studentListFrame = ( StudentListFrame ) MemoryManager.getObject( StudentListFrame.class, true );

    graduateWorkListFrame = ( GraduateWorkListFrame ) MemoryManager.getObject( GraduateWorkListFrame.class, true );
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

  private GraduateWork getGraduateWorkUserOject( String graduateWorkId )
  {

    if( graduateWorkId != null && graduateWorkId.length() != 0 )
    {

      GraduateWork graduateWork = GraduateWorkService.getGraduateWork( graduateWorkId );

      if( graduateWork != null )
      {

        return graduateWork;
      }
    }

    return null;
  }

  private String getTeacherFromUserOject( Teacher teacher )
  {

    return teacher != null ? teacher.getId() : null;
  }

  private String getGraduateWorkFromUserOject( GraduateWork graduateWork )
  {

    return graduateWork != null ? graduateWork.getId() : null;
  }

  @Override
  public boolean showFrame( Object parent, DefenceGraduateWork defenceGraduateWork )
  {

    defenceGraduateWorkListFrame = ( DefenceGraduateWorkListFrame ) parent;

    model = defenceGraduateWorkListFrame.getModel();

    if( defenceGraduateWork != null )
    {

      this.defenceGraduateWork = defenceGraduateWork;
    }
    else
    {

      this.defenceGraduateWork = new DefenceGraduateWork();
    }

    protocolNumberField.setText( this.defenceGraduateWork.getProtocolNumber() );
    graduateWorkField.setUserObject( getGraduateWorkUserOject( this.defenceGraduateWork.getGraduateWorkId() ) );
    actualDateField.setDate( this.defenceGraduateWork.getActualDate() );
    startTimeField.setText( this.defenceGraduateWork.getStartTime() );
    endTimeField.setText( this.defenceGraduateWork.getEndTime() );
    presideField.setUserObject( getTeacherUserOject( this.defenceGraduateWork.getPresideId() ) );
    commissionerSecrField.setUserObject( getTeacherUserOject( this.defenceGraduateWork.getCommissionerSecrId() ) );
    commissioner0Field.setUserObject( getTeacherUserOject( this.defenceGraduateWork.getCommissioner0Id() ) );
    commissioner1Field.setUserObject( getTeacherUserOject( this.defenceGraduateWork.getCommissioner1Id() ) );
    commissioner2Field.setUserObject( getTeacherUserOject( this.defenceGraduateWork.getCommissioner2Id() ) );
    commissioner3Field.setUserObject( getTeacherUserOject( this.defenceGraduateWork.getCommissioner3Id() ) );
    commissioner4Field.setUserObject( getTeacherUserOject( this.defenceGraduateWork.getCommissioner4Id() ) );
    commissioner5Field.setUserObject( getTeacherUserOject( this.defenceGraduateWork.getCommissioner5Id() ) );
    commissioner6Field.setUserObject( getTeacherUserOject( this.defenceGraduateWork.getCommissioner6Id() ) );
    commissioner7Field.setUserObject( getTeacherUserOject( this.defenceGraduateWork.getCommissioner7Id() ) );
    commissioner8Field.setUserObject( getTeacherUserOject( this.defenceGraduateWork.getCommissioner8Id() ) );
    commissioner9Field.setUserObject( getTeacherUserOject( this.defenceGraduateWork.getCommissioner9Id() ) );

    // ---

    if( this.defenceGraduateWork.getGraduateWorkInfo() == null )
    {

      this.defenceGraduateWork.setGraduateWorkInfo( new GraduateWorkInfo() );
    }
    pageCountField.setText( this.defenceGraduateWork.getGraduateWorkInfo().getPageCount() );
    graphicCountField.setText( this.defenceGraduateWork.getGraduateWorkInfo().getGraphicCount() );
    reviewMarkField.setSelectedItem( this.defenceGraduateWork.getGraduateWorkInfo().getReviewMark() );

    question1Field.setText( this.defenceGraduateWork.getGraduateWorkInfo().getQuestion1() );
    question2Field.setText( this.defenceGraduateWork.getGraduateWorkInfo().getQuestion2() );
    question3Field.setText( this.defenceGraduateWork.getGraduateWorkInfo().getQuestion3() );
    question4Field.setText( this.defenceGraduateWork.getGraduateWorkInfo().getQuestion4() );
    question5Field.setText( this.defenceGraduateWork.getGraduateWorkInfo().getQuestion5() );
    question6Field.setText( this.defenceGraduateWork.getGraduateWorkInfo().getQuestion6() );

    question1AuthorField.setUserObject( getTeacherUserOject( this.defenceGraduateWork.getGraduateWorkInfo()
      .getQuestion1AuthorId() ) );
    question2AuthorField.setUserObject( getTeacherUserOject( this.defenceGraduateWork.getGraduateWorkInfo()
      .getQuestion2AuthorId() ) );
    question3AuthorField.setUserObject( getTeacherUserOject( this.defenceGraduateWork.getGraduateWorkInfo()
      .getQuestion3AuthorId() ) );
    question4AuthorField.setUserObject( getTeacherUserOject( this.defenceGraduateWork.getGraduateWorkInfo()
      .getQuestion4AuthorId() ) );
    question5AuthorField.setUserObject( getTeacherUserOject( this.defenceGraduateWork.getGraduateWorkInfo()
      .getQuestion5AuthorId() ) );
    question6AuthorField.setUserObject( getTeacherUserOject( this.defenceGraduateWork.getGraduateWorkInfo()
      .getQuestion6AuthorId() ) );

    answerField.setText( this.defenceGraduateWork.getGraduateWorkInfo().getAnswer() );
    dopInfoField.setText( this.defenceGraduateWork.getGraduateWorkInfo().getTraining() );
    markField.setSelectedItem( this.defenceGraduateWork.getGraduateWorkInfo().getMark() );
    opinionField.setText( this.defenceGraduateWork.getGraduateWorkInfo().getOpinion() );

    setVisible( true );

    return true;
  }

  @Override
  protected void init()
  {
    setTitle( "Форма редактирования: Защита дипломного проекта" );

    setMinimumSize( new Dimension( 650, 650 ) );
    jTabbedPane = new JTabbedPane();
  }

  private void initComponents()
  {

    protocolNumberField = createJTextField();
    graduateWorkField = createJButtonSelect();
    actualDateField = createJDateField();
    startTimeField = createJTextField();
    endTimeField = createJTextField();

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

    // ---

    pageCountField = createJTextField();
    graphicCountField = createJTextField();

    reviewMarkField = createJComboBox();
    reviewMarkField.addItem( null );
    for( Integer i = 4; i <= 10; i++ )
    {

      reviewMarkField.addItem( i );
    }

    question1Field = createJTextField( 250 );
    question2Field = createJTextField( 250 );
    question3Field = createJTextField( 250 );
    question4Field = createJTextField( 250 );
    question5Field = createJTextField( 250 );
    question6Field = createJTextField( 250 );

    question1AuthorField = createJButtonSelect();
    question2AuthorField = createJButtonSelect();
    question3AuthorField = createJButtonSelect();
    question4AuthorField = createJButtonSelect();
    question5AuthorField = createJButtonSelect();
    question6AuthorField = createJButtonSelect();

    answerField = createJTextField( 250 );
    dopInfoField = createJTextField( 250 );

    markField = createJComboBox();
    markField.addItem( null );
    for( Integer i = 4; i <= 10; i++ )
    {

      markField.addItem( i );
    }

    opinionField = createJTextField( 250 );
  }

  @Override
  protected void generateComponents()
  {

    initComponents();

    jTabbedPane = new JTabbedPane();

    JPanel jPane =
      createVPane(
        5,
        createField( protocolNumberField, "№ протокола: " ),
        createField( graduateWorkField, "Дипломный проект: " ),
        createField( actualDateField, "Дата защиты: " ),
        createHPane( 0, createField( startTimeField, "Начало: " ), createField( endTimeField, "Конец: " ) ),
        createField( presideField, "Председатель комиссии: " ),
        createField( commissionerSecrField, "Член комиссии (секретарь): " ),
        createHPane( 0, createField( commissioner0Field, "Член комиссии: " ),
          createField( commissioner1Field, "Член комиссии: " ) ),
        createHPane( 0, createField( commissioner2Field, "Член комиссии: " ),
          createField( commissioner3Field, "Член комиссии: " ) ),
        createHPane( 0, createField( commissioner4Field, "Член комиссии: " ),
          createField( commissioner5Field, "Член комиссии: " ) ),
        createHPane( 0, createField( commissioner6Field, "Член комиссии: " ),
          createField( commissioner7Field, "Член комиссии: " ) ),
        createHPane( 0, createField( commissioner8Field, "Член комиссии: " ),
          createField( commissioner9Field, "Член комиссии: " ) ) );
    jTabbedPane.addTab( "Основные", jPane );

    jPane =
      createVPane( 5, createField( pageCountField, "Число страниц в дипломе: " ),
        createField( graphicCountField, "Графических материалов: " ),
        createField( reviewMarkField, "Отметка рецензента: " ),
        createHPane( 5, createField( question1Field, "Вопрос №1: " ), question1AuthorField ),
        createHPane( 5, createField( question2Field, "Вопрос №2: " ), question2AuthorField ),
        createHPane( 5, createField( question3Field, "Вопрос №3: " ), question3AuthorField ),
        createHPane( 5, createField( question4Field, "Вопрос №4: " ), question4AuthorField ),
        createHPane( 5, createField( question5Field, "Вопрос №5: " ), question5AuthorField ),
        createHPane( 5, createField( question6Field, "Вопрос №6: " ), question6AuthorField ),
        createField( answerField, "Ответ студента: " ), createField( dopInfoField, "Дополнительные навыки: " ),
        createField( markField, "Отметка: " ), createField( opinionField, "Особые мнения: " ) );
    jTabbedPane.addTab( "Дополнительные", jPane );

    getContentPane().add( jTabbedPane, BorderLayout.CENTER );

    JButton applyButton = new JButton( "Применить" );
    applyButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        defenceGraduateWork.setProtocolNumber( checkFieldNull( protocolNumberField ) );
        defenceGraduateWork.setGraduateWorkId( getGraduateWorkFromUserOject( ( GraduateWork ) graduateWorkField
          .getUserObject() ) );

        defenceGraduateWork.setActualDate( actualDateField.getDate() );
        defenceGraduateWork.setStartTime( checkFieldNull( startTimeField ) );
        defenceGraduateWork.setEndTime( checkFieldNull( endTimeField ) );
        defenceGraduateWork.setPresideId( getTeacherFromUserOject( ( Teacher ) presideField.getUserObject() ) );
        defenceGraduateWork.setCommissionerSecrId( getTeacherFromUserOject( ( Teacher ) commissionerSecrField
          .getUserObject() ) );
        defenceGraduateWork.setCommissioner0Id( getTeacherFromUserOject( ( Teacher ) commissioner0Field.getUserObject() ) );
        defenceGraduateWork.setCommissioner1Id( getTeacherFromUserOject( ( Teacher ) commissioner1Field.getUserObject() ) );
        defenceGraduateWork.setCommissioner2Id( getTeacherFromUserOject( ( Teacher ) commissioner2Field.getUserObject() ) );
        defenceGraduateWork.setCommissioner3Id( getTeacherFromUserOject( ( Teacher ) commissioner3Field.getUserObject() ) );
        defenceGraduateWork.setCommissioner4Id( getTeacherFromUserOject( ( Teacher ) commissioner4Field.getUserObject() ) );
        defenceGraduateWork.setCommissioner5Id( getTeacherFromUserOject( ( Teacher ) commissioner5Field.getUserObject() ) );
        defenceGraduateWork.setCommissioner6Id( getTeacherFromUserOject( ( Teacher ) commissioner6Field.getUserObject() ) );
        defenceGraduateWork.setCommissioner7Id( getTeacherFromUserOject( ( Teacher ) commissioner7Field.getUserObject() ) );
        defenceGraduateWork.setCommissioner8Id( getTeacherFromUserOject( ( Teacher ) commissioner8Field.getUserObject() ) );
        defenceGraduateWork.setCommissioner9Id( getTeacherFromUserOject( ( Teacher ) commissioner9Field.getUserObject() ) );

        // ---

        if( defenceGraduateWork.getGraduateWorkInfo() == null )
        {

          defenceGraduateWork.setGraduateWorkInfo( new GraduateWorkInfo() );
        }

        defenceGraduateWork.getGraduateWorkInfo().setPageCount( checkFieldNull( pageCountField ) );
        defenceGraduateWork.getGraduateWorkInfo().setGraphicCount( checkFieldNull( graphicCountField ) );
        defenceGraduateWork.getGraduateWorkInfo().setReviewMark( ( Integer ) reviewMarkField.getSelectedItem() );

        defenceGraduateWork.getGraduateWorkInfo().setQuestion1( checkFieldNull( question1Field ) );
        defenceGraduateWork.getGraduateWorkInfo().setQuestion2( checkFieldNull( question2Field ) );
        defenceGraduateWork.getGraduateWorkInfo().setQuestion3( checkFieldNull( question3Field ) );
        defenceGraduateWork.getGraduateWorkInfo().setQuestion4( checkFieldNull( question4Field ) );
        defenceGraduateWork.getGraduateWorkInfo().setQuestion5( checkFieldNull( question5Field ) );
        defenceGraduateWork.getGraduateWorkInfo().setQuestion6( checkFieldNull( question6Field ) );

        defenceGraduateWork.getGraduateWorkInfo().setQuestion1AuthorId(
          getTeacherFromUserOject( ( Teacher ) question1AuthorField.getUserObject() ) );
        defenceGraduateWork.getGraduateWorkInfo().setQuestion2AuthorId(
          getTeacherFromUserOject( ( Teacher ) question2AuthorField.getUserObject() ) );
        defenceGraduateWork.getGraduateWorkInfo().setQuestion3AuthorId(
          getTeacherFromUserOject( ( Teacher ) question3AuthorField.getUserObject() ) );
        defenceGraduateWork.getGraduateWorkInfo().setQuestion4AuthorId(
          getTeacherFromUserOject( ( Teacher ) question4AuthorField.getUserObject() ) );
        defenceGraduateWork.getGraduateWorkInfo().setQuestion5AuthorId(
          getTeacherFromUserOject( ( Teacher ) question5AuthorField.getUserObject() ) );
        defenceGraduateWork.getGraduateWorkInfo().setQuestion6AuthorId(
          getTeacherFromUserOject( ( Teacher ) question6AuthorField.getUserObject() ) );

        defenceGraduateWork.getGraduateWorkInfo().setAnswer( checkFieldNull( answerField ) );
        defenceGraduateWork.getGraduateWorkInfo().setTraining( checkFieldNull( dopInfoField ) );

        defenceGraduateWork.getGraduateWorkInfo().setMark( ( Integer ) markField.getSelectedItem() );

        defenceGraduateWork.getGraduateWorkInfo().setOpinion( checkFieldNull( opinionField ) );

        if( defenceGraduateWork.getId() == null )
        {

          defenceGraduateWork.setId( UUIDUtils.getUUID() );
          GraduateWorkService.getDefenceGraduateWorkList().add( defenceGraduateWork );
        }

        if( model != null )
        {

          model.updateData();
          defenceGraduateWorkListFrame.updateListDate();
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

    getContentPane().add( createNorthButtonPane( 10, applyButton, cancelButton ), BorderLayout.SOUTH );

    graduateWorkField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        graduateWorkListFrame.showFrame( DefenceGraduateWorkFrame.this, graduateWorkField );
      }
    } );

    presideField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( DefenceGraduateWorkFrame.this, presideField );
      }
    } );

    commissionerSecrField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( DefenceGraduateWorkFrame.this, commissionerSecrField );
      }
    } );

    commissioner0Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( DefenceGraduateWorkFrame.this, commissioner0Field );
      }
    } );
    commissioner1Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( DefenceGraduateWorkFrame.this, commissioner1Field );
      }
    } );
    commissioner2Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( DefenceGraduateWorkFrame.this, commissioner2Field );
      }
    } );
    commissioner3Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( DefenceGraduateWorkFrame.this, commissioner3Field );
      }
    } );
    commissioner4Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( DefenceGraduateWorkFrame.this, commissioner4Field );
      }
    } );
    commissioner5Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( DefenceGraduateWorkFrame.this, commissioner5Field );
      }
    } );
    commissioner6Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( DefenceGraduateWorkFrame.this, commissioner6Field );
      }
    } );
    commissioner7Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( DefenceGraduateWorkFrame.this, commissioner7Field );
      }
    } );
    commissioner8Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( DefenceGraduateWorkFrame.this, commissioner8Field );
      }
    } );
    commissioner9Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( DefenceGraduateWorkFrame.this, commissioner9Field );
      }
    } );

    question1AuthorField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( DefenceGraduateWorkFrame.this, question1AuthorField );
      }
    } );
    question2AuthorField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( DefenceGraduateWorkFrame.this, question2AuthorField );
      }
    } );
    question3AuthorField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( DefenceGraduateWorkFrame.this, question3AuthorField );
      }
    } );
    question4AuthorField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( DefenceGraduateWorkFrame.this, question4AuthorField );
      }
    } );
    question5AuthorField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( DefenceGraduateWorkFrame.this, question5AuthorField );
      }
    } );
    question6AuthorField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( DefenceGraduateWorkFrame.this, question6AuthorField );
      }
    } );

  }

}
