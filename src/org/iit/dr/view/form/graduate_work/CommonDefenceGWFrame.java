package org.iit.dr.view.form.graduate_work;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.iit.dr.data_model.DefenceGraduateWork;
import org.iit.dr.data_model.GraduateWork;
import org.iit.dr.data_model.GraduateWorkInfo;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.documents.word.write.graduate_work.ReportGeneratorUtil;
import org.iit.dr.services.GraduateWorkService;
import org.iit.dr.services.StudentsService;
import org.iit.dr.services.TeachersService;
import org.iit.dr.utils.MemoryManager;
import org.iit.dr.view.component.DefenceGWTreePanel;
import org.iit.dr.view.component.JButtonSelect;
import org.iit.dr.view.component.JDateField;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.form.staffers.StafferGekListFrame;
import org.iit.dr.view.form.staffers.StafferListSelectButtonFrame;

/**
 * CommonDefenceGWFrame.
 * 
 * @author Yuriy Karpovich
 */
public class CommonDefenceGWFrame extends JInternalFrameExt<Object> implements WindowListener
{
  private static final long serialVersionUID = 3115660670842988810L;

  DefenceGraduateWork defenceGraduateWork;

  DefenceGWTreePanel defenceGWTreePanel;

  JTabbedPane jTabbedPane;

  JPanel jCommomPanel;

  StafferGekListFrame stafferListFrame;

  StafferListSelectButtonFrame stafferFrame;

  GraduateWorkListFrame graduateWorkListFrame;

  // ---

  JTextField protocolNumberField;

  JTextField studentField;

  // JTextField managerField;

  JButtonSelect managerField;

  JButtonSelect consultantField;

  JButtonSelect consultantEconomyField;

  JButtonSelect consultantOTField;

  JButtonSelect normoControlerField;

  JButtonSelect reviewerField;

  JTextArea defenceGWField;

  JDateField actualDateField;

  JTextField startTimeField;

  JTextField endTimeField;

  JTextField pageCountField;

  JTextField graphicCountField;

  JComboBox reviewMarkField;

  JComboBox managerResultField;

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

  JComboBox answerField;

  JComboBox trainingField;

  JComboBox markField;

  JTextArea opinionField;

  JTextField markCountDistinctionField;

  JTextField markCountGoodField;

  JTextField markCountSatisfactoryField;

  JTextField timeCountField;

  JComboBox outputMarkField;

  JComboBox resultCommonField;

  JCheckBox compPresentField;

  JCheckBox distributionField;

  JCheckBox bestWorkField;

  JCheckBox magistracyField;

  JCheckBox printedPublicationField;

  public CommonDefenceGWFrame() throws HeadlessException
  {
    stafferFrame = ( StafferListSelectButtonFrame ) MemoryManager.getObject( StafferListSelectButtonFrame.class );
  }

  @Override
  public boolean showFrame( Object parent, Object o )
  {

    if( defenceGWTreePanel != null )
    {

      defenceGWTreePanel.updateData();
    }

    setVisible( true );
    return true;
  }

  @Override
  protected void init()
  {

    setTitle( "Защита дипломных проектов" );
    setMinimumSize( new Dimension( 750, 800 ) );

    defenceGWTreePanel = new DefenceGWTreePanel( this );

    stafferListFrame = ( StafferGekListFrame ) MemoryManager.getObject( StafferGekListFrame.class, true );

    initComponents();

    addWindowListener( this );
  }

  private void initComponents()
  {

    protocolNumberField = createJTextField();
    studentField = createJTextField( 350 );
    studentField.setEditable( false );
    managerField = createJButtonSelect();
    consultantEconomyField = createJButtonSelect();
    consultantField = createJButtonSelect();
    consultantOTField = createJButtonSelect();
    normoControlerField = createJButtonSelect();
    reviewerField = createJButtonSelect();
    defenceGWField = createJTextArea( 350 );
    defenceGWField.setEditable( false );
    actualDateField = createJDateField();
    startTimeField = createJTextField();
    endTimeField = createJTextField();
    timeCountField = createJTextField();

    pageCountField = createJTextField();
    graphicCountField = createJTextField();

    managerResultField = createJComboBox( 300 );
    managerResultField.setEditable( true );

    reviewMarkField = createJComboBox();
    reviewMarkField.addItem( null );
    for( Integer i = 1; i <= 10; i++ )
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

    answerField = createJComboBox( 350 );
    answerField.setEditable( true );
    trainingField = createJComboBox( 300 );
    trainingField.setEditable( true );

    markCountDistinctionField = createJTextField();
    markCountGoodField = createJTextField();
    markCountSatisfactoryField = createJTextField();

    outputMarkField = createJComboBox();
    outputMarkField.addItem( null );
    outputMarkField.addItem( "с отличием" );
    outputMarkField.addItem( "без отличия" );
    outputMarkField.setSelectedIndex( 2 );
    outputMarkField.setEditable( true );

    resultCommonField = createJComboBox( 350 );
    resultCommonField.setEditable( true );

    markField = createJComboBox();
    markField.addItem( null );
    for( Integer i = 1; i <= 10; i++ )
    {

      markField.addItem( i );
    }

    opinionField = createJTextArea( 350 );

    compPresentField = createJCheckBox2( "Представлена компьютерная презентация" );
    distributionField = createJCheckBox2( "Акт (справка) о внедрении" );
    bestWorkField = createJCheckBox2( "Отметить работу как лучшую" );
    magistracyField = createJCheckBox2( "Рекомендация для поступления в магистратуру" );
    printedPublicationField = createJCheckBox2( "Наличие печатных публикаций" );
  }

  private String getTeacherFromUserOject( Teacher teacher )
  {

    return teacher != null ? teacher.getId() : null;
  }

  @Override
  protected void generateComponents()
  {

    JButton addButton = new JButton( "Добавить" );
    addButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        // defenceGraduateWorkFrame.showFrame(DefenceGraduateWorkListFrame.this, null);

      }
    } );

    JButton removeButton = new JButton( "Удалить" );
    removeButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {
System.out.print("olollo");
//         if (jTable.getSelectedRow() >= 0) {
//	         String guid = (String) model.getValueAt(jTable.getSelectedRow(), TableModelConst.ID_COLUMN);
//	         DataStorage.getInstance().removeDefenceGraduateWork(guid);
//	        
//	         model.updateData();
//         }
      }
    } );

    // getContentPane().add(createNorthButtonPane(5, addButton, removeButton), BorderLayout.NORTH);

    JScrollPane jScrollPane =
      new JScrollPane( defenceGWTreePanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
    jScrollPane.setMinimumSize( new Dimension( 200, 200 ) );
    jScrollPane.setPreferredSize( new Dimension( 200, 200 ) );

    getContentPane().add( jScrollPane, BorderLayout.WEST );

    jTabbedPane = new JTabbedPane();

    JPanel jPane =
      createVPane( 5, createField( protocolNumberField, "№ протокола: " ), createField( studentField, "Дипломник: " ),
        createField( createScrollPane( defenceGWField ), "Тема: " ), createField( actualDateField, "Дата защиты: " ),
        createHPane( 0, createField( startTimeField, "Начало: " ), createField( endTimeField, "Конец: " ) ),
        createField( pageCountField, "Число страниц в дипломе: " ), createField( graphicCountField,
          "Графических материалов: " ), createField( managerField, "Руководитель: " ), createField( managerResultField,
          "Отзыв руководителя: " ), createField( consultantField, "Консультант: " ), createField(
          consultantEconomyField, "Консультант ТЕО: " ), createField( consultantOTField, "Консультант ОТ: " ),
        createField( normoControlerField, "Нормоконтролер: " ), createField( reviewerField, "Рецензент: " ),
        createField( reviewMarkField, "Отметка рецензента: " ), createField( trainingField,
          "Подготовка по дисциплинам: " ), createHPane( 5, createLabel( "Отметок: " ), createField(
          markCountDistinctionField, "отличных - " ), createField( markCountGoodField, "хороших - " ), createField(
          markCountSatisfactoryField, "удовлетворительных - " ) ) );
    jTabbedPane.addTab( "Основное", jPane );

    jPane =
      createVPane( 5, createField( timeCountField, "Время на вопросы (минут): " ), createHPane( 5, createField(
        question1Field, "Вопрос №1: " ), createField( question1AuthorField ) ), createHPane( 5, createField(
        question2Field, "Вопрос №2: " ), createField( question2AuthorField ) ), createHPane( 5, createField(
        question3Field, "Вопрос №3: " ), createField( question3AuthorField ) ), createHPane( 5, createField(
        question4Field, "Вопрос №4: " ), createField( question4AuthorField ) ), createHPane( 5, createField(
        question5Field, "Вопрос №5: " ), createField( question5AuthorField ) ), createHPane( 5, createField(
        question6Field, "Вопрос №6: " ), createField( question6AuthorField ) ), createField( answerField,
        "Ответ студента: " ),

      createField( markField, "Отметка: " ), createField( outputMarkField, "Выдать диплом: " ), createField(
        resultCommonField, "Отметить, что: " ), createField( compPresentField ), createField( distributionField ),
        createField( bestWorkField ), createField( magistracyField ), createField( printedPublicationField ),
        createField( createScrollPane( opinionField ), "Особые мнения: " ) );

    jTabbedPane.addTab( "Защита", jPane );

    JButton applyButton = new JButton( "Применить" );
    applyButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        applyInputData();
      }
    } );

    JButton protokolButton = new JButton( "Протокол" );
    protokolButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        applyInputData();
        ReportGeneratorUtil.generateProtocol( defenceGraduateWork.getId() );
      }
    } );

    jCommomPanel = new JPanel( new BorderLayout() );
    jCommomPanel.setVisible( false );
    jCommomPanel.add( createNorthButtonPane( 5, protokolButton, applyButton ), BorderLayout.SOUTH );
    jCommomPanel.add( jTabbedPane, BorderLayout.CENTER );

    getContentPane().add( jCommomPanel, BorderLayout.CENTER );

    question1AuthorField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( CommonDefenceGWFrame.this, question1AuthorField );
      }
    } );
    question2AuthorField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( CommonDefenceGWFrame.this, question2AuthorField );
      }
    } );
    question3AuthorField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( CommonDefenceGWFrame.this, question3AuthorField );
      }
    } );
    question4AuthorField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( CommonDefenceGWFrame.this, question4AuthorField );
      }
    } );
    question5AuthorField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( CommonDefenceGWFrame.this, question5AuthorField );
      }
    } );
    question6AuthorField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( CommonDefenceGWFrame.this, question6AuthorField );
      }
    } );
    managerField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferFrame.showFrame( CommonDefenceGWFrame.this, managerField );
      }
    } );
    consultantEconomyField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferFrame.showFrame( CommonDefenceGWFrame.this, consultantEconomyField );
      }
    } );
    consultantField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferFrame.showFrame( CommonDefenceGWFrame.this, consultantField );
      }
    } );
    consultantOTField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferFrame.showFrame( CommonDefenceGWFrame.this, consultantOTField );
      }
    } );
    normoControlerField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferFrame.showFrame( CommonDefenceGWFrame.this, normoControlerField );
      }
    } );
    reviewerField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferFrame.showFrame( CommonDefenceGWFrame.this, reviewerField );
      }
    } );
  }

  public boolean applyInputData()
  {

    if( defenceGraduateWork == null )
    {

      return false;
    }

    defenceGraduateWork.setProtocolNumber( checkFieldNull( protocolNumberField ) );
    defenceGraduateWork.setActualDate( actualDateField.getDate() );
    defenceGraduateWork.setStartTime( checkFieldNull( startTimeField ) );
    defenceGraduateWork.setEndTime( checkFieldNull( endTimeField ) );

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

    GraduateWork graduateWork = GraduateWorkService.getGraduateWork( defenceGraduateWork.getGraduateWorkId() );
    graduateWork.setConsultantIdByEconomics( getTeacherFromUserOject( ( Teacher ) consultantEconomyField
      .getUserObject() ) );
    graduateWork.setConsultantIdByNormalInspection( getTeacherFromUserOject( ( Teacher ) normoControlerField
      .getUserObject() ) );
    graduateWork.setConsultantIdByProtectionOfLabor( getTeacherFromUserOject( ( Teacher ) consultantOTField
      .getUserObject() ) );
    graduateWork.setConsultantIdBySpeciality( getTeacherFromUserOject( ( Teacher ) consultantField.getUserObject() ) );
    graduateWork.setManagerId( getTeacherFromUserOject( ( Teacher ) managerField.getUserObject() ) );
    graduateWork.setReviewerId( getTeacherFromUserOject( ( Teacher ) reviewerField.getUserObject() ) );

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
    defenceGraduateWork.getGraduateWorkInfo().setTraining( checkFieldNull( trainingField ) );
    defenceGraduateWork.getGraduateWorkInfo().setTraining( checkFieldNull( trainingField ) );
    defenceGraduateWork.getGraduateWorkInfo().setTimeCount( checkFieldNull( timeCountField ) );

    defenceGraduateWork.getGraduateWorkInfo().setMark( ( Integer ) markField.getSelectedItem() );

    defenceGraduateWork.getGraduateWorkInfo().setOpinion( checkFieldNull( opinionField ) );
    defenceGraduateWork.getGraduateWorkInfo().setOutputMark( ( String ) outputMarkField.getSelectedItem() );
    defenceGraduateWork.getGraduateWorkInfo().setResultCommon( checkFieldNull( resultCommonField ) );
    defenceGraduateWork.getGraduateWorkInfo().setManagerResult( checkFieldNull( managerResultField ) );

    defenceGraduateWork.getGraduateWorkInfo().setMarkCountDistinction( checkFieldNull( markCountDistinctionField ) );
    defenceGraduateWork.getGraduateWorkInfo().setMarkCountGood( checkFieldNull( markCountGoodField ) );
    defenceGraduateWork.getGraduateWorkInfo().setMarkCountSatisfactory( checkFieldNull( markCountSatisfactoryField ) );

    defenceGraduateWork.getGraduateWorkInfo().setCompPresent( checkFieldNull( compPresentField ) );
    defenceGraduateWork.getGraduateWorkInfo().setDistribution( checkFieldNull( distributionField ) );
    defenceGraduateWork.getGraduateWorkInfo().setBestWork( checkFieldNull( bestWorkField ) );
    defenceGraduateWork.getGraduateWorkInfo().setMagistracy( checkFieldNull( magistracyField ) );
    defenceGraduateWork.getGraduateWorkInfo().setPrintedPublication( checkFieldNull( printedPublicationField ) );

    return true;
  }

  public void updateData( DefenceGraduateWork defenceGraduateWork )
  {

    this.defenceGraduateWork = defenceGraduateWork;

    if( defenceGraduateWork != null )
    {
      updateFieldDict();
      updateForm();
      jCommomPanel.setVisible( true );
    }
    else
    {
      jCommomPanel.setVisible( false );
    }
  }

  private void updateFieldDict()
  {

    List<String> valueList = GraduateWorkService.getDefenceGWAnswerList();

    answerField.removeAllItems();
    answerField.addItem( null );
    for( String value : valueList )
    {

      answerField.addItem( value );
    }

    valueList = GraduateWorkService.getDefenceGWResultCommonList();

    resultCommonField.removeAllItems();
    resultCommonField.addItem( null );
    for( String value : valueList )
    {

      if( !value.equalsIgnoreCase( "представлена мультимедийная презентация проекта" ) )
      {

        resultCommonField.addItem( value );
      }
    }

    valueList = GraduateWorkService.getDefenceGWTrainingList();

    trainingField.removeAllItems();
    trainingField.addItem( null );
    for( String value : valueList )
    {

      trainingField.addItem( value );
    }

    valueList = GraduateWorkService.getDefenceGWManagerResultList();

    managerResultField.removeAllItems();
    managerResultField.addItem( null );
    for( String value : valueList )
    {

      managerResultField.addItem( value );
    }

  }

  private boolean updateForm()
  {

    if( defenceGraduateWork == null )
    {

      return false;
    }

    GraduateWork graduateWork = GraduateWorkService.getGraduateWork( defenceGraduateWork.getGraduateWorkId() );

    Student student = StudentsService.getStudent( graduateWork.getStudentId() );

    Teacher manager = TeachersService.getTeacher( graduateWork.getManagerId() );
    Teacher reviewer = TeachersService.getTeacher( graduateWork.getReviewerId() );
    Teacher consultant = TeachersService.getTeacher( graduateWork.getConsultantIdBySpeciality() );
    Teacher consTEO = TeachersService.getTeacher( graduateWork.getConsultantIdByEconomics() );
    Teacher consOT = TeachersService.getTeacher( graduateWork.getConsultantIdByProtectionOfLabor() );
    Teacher normocontrl = TeachersService.getTeacher( graduateWork.getConsultantIdByNormalInspection() );

    protocolNumberField.setText( this.defenceGraduateWork.getProtocolNumber() );
    if( student != null )
    {
      studentField.setText( student.getFullName() );
    }
    if( reviewer != null )
    {
      reviewerField.setUserObject( getTeacherUserOject( reviewer.getId() ) );
    }
    if( manager != null )
    {
      managerField.setUserObject( getTeacherUserOject( manager.getId() ) );
    }
    if( consultant != null )
    {
      consultantField.setUserObject( getTeacherUserOject( consultant.getId() ) );
    }
    if( consTEO != null )
    {
      consultantEconomyField.setUserObject( getTeacherUserOject( consTEO.getId() ) );
    }
    if( consOT != null )
    {
      consultantOTField.setUserObject( getTeacherUserOject( consOT.getId() ) );
    }
    if( normocontrl != null )
    {
      normoControlerField.setUserObject( getTeacherUserOject( normocontrl.getId() ) );
    }
    defenceGWField.setText( graduateWork.getTitle() );
    actualDateField.setDate( this.defenceGraduateWork.getActualDate() );
    startTimeField.setText( this.defenceGraduateWork.getStartTime() );
    endTimeField.setText( this.defenceGraduateWork.getEndTime() );

    // ---

    if( this.defenceGraduateWork.getGraduateWorkInfo() == null )
    {

      this.defenceGraduateWork.setGraduateWorkInfo( new GraduateWorkInfo() );
    }
    pageCountField.setText( this.defenceGraduateWork.getGraduateWorkInfo().getPageCount() );

    if( this.defenceGraduateWork.getGraduateWorkInfo().getGraphicCount() != null )
    {
      graphicCountField.setText( this.defenceGraduateWork.getGraduateWorkInfo().getGraphicCount() );
    }
    else
    {
      graphicCountField.setText( "6" );
    }
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

    if( this.defenceGraduateWork.getGraduateWorkInfo().getTimeCount() != null )
    {

      timeCountField.setText( this.defenceGraduateWork.getGraduateWorkInfo().getTimeCount() );
    }
    else
    {

      timeCountField.setText( "15" );
    }

    markCountDistinctionField.setText( this.defenceGraduateWork.getGraduateWorkInfo().getMarkCountDistinction() );
    markCountGoodField.setText( this.defenceGraduateWork.getGraduateWorkInfo().getMarkCountGood() );
    markCountSatisfactoryField.setText( this.defenceGraduateWork.getGraduateWorkInfo().getMarkCountSatisfactory() );

    answerField.setSelectedItem( this.defenceGraduateWork.getGraduateWorkInfo().getAnswer() );
    trainingField.setSelectedItem( this.defenceGraduateWork.getGraduateWorkInfo().getTraining() );
    markField.setSelectedItem( this.defenceGraduateWork.getGraduateWorkInfo().getMark() );
    opinionField.setText( this.defenceGraduateWork.getGraduateWorkInfo().getOpinion() );
    if( this.defenceGraduateWork.getGraduateWorkInfo().getOutputMark() != null )
    {
      outputMarkField.setSelectedItem( this.defenceGraduateWork.getGraduateWorkInfo().getOutputMark() );
    }
    else
    {
      outputMarkField.setSelectedIndex( 2 );
    }

    if( this.defenceGraduateWork.getGraduateWorkInfo().getResultCommon() != null )
    {
      resultCommonField.setSelectedItem( this.defenceGraduateWork.getGraduateWorkInfo().getResultCommon() );
    }
    else
    {
      resultCommonField.setSelectedItem( "" );
    }

    managerResultField.setSelectedItem( this.defenceGraduateWork.getGraduateWorkInfo().getManagerResult() );

    if( this.defenceGraduateWork.getGraduateWorkInfo().getCompPresent() != null )
    {

      compPresentField.setSelected( getOnlyBoolean( this.defenceGraduateWork.getGraduateWorkInfo().isCompPresent() ) );
    }
    else
    {
      compPresentField.setSelected( true );
    }
    distributionField.setSelected( getOnlyBoolean( this.defenceGraduateWork.getGraduateWorkInfo().isDistribution() ) );
    bestWorkField.setSelected( getOnlyBoolean( this.defenceGraduateWork.getGraduateWorkInfo().isBestWork() ) );
    magistracyField.setSelected( getOnlyBoolean( this.defenceGraduateWork.getGraduateWorkInfo().isMagistracy() ) );
    printedPublicationField.setSelected( getOnlyBoolean( this.defenceGraduateWork.getGraduateWorkInfo()
      .isPrintedPublication() ) );

    return true;
  }

  private boolean getOnlyBoolean( Boolean value )
  {

    return value != null && value;
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

  public boolean checkChange()
  {

    if( isChanged() )
    {

      GraduateWork graduateWork = GraduateWorkService.getGraduateWork( defenceGraduateWork.getGraduateWorkId() );

      Student student = StudentsService.getStudent( graduateWork.getStudentId() );

      int result =
        JOptionPane.showConfirmDialog( this, "Применить изменения?", student.getShortName(), JOptionPane.YES_NO_OPTION );

      if( result == JOptionPane.YES_OPTION )
      {

        applyInputData();
      }
    }

    return true;
  }

  public boolean isChanged()
  {

    return defenceGraduateWork != null
      && ( checkObjectForChange( defenceGraduateWork.getProtocolNumber(), checkFieldNull( protocolNumberField ) )
        || checkObjectForChange( defenceGraduateWork.getActualDate(), actualDateField.getDate() )
        || checkObjectForChange( defenceGraduateWork.getStartTime(), checkFieldNull( startTimeField ) )
        || checkObjectForChange( defenceGraduateWork.getEndTime(), checkFieldNull( endTimeField ) )
        ||

        checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().getPageCount(), checkFieldNull( pageCountField ) )
        || checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().getGraphicCount(),
          checkFieldNull( graphicCountField ) )
        || checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().getReviewMark(), reviewMarkField
          .getSelectedItem() )
        ||

        checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().getQuestion1(), checkFieldNull( question1Field ) )
        || checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().getQuestion2(),
          checkFieldNull( question2Field ) )
        || checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().getQuestion3(),
          checkFieldNull( question3Field ) )
        || checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().getQuestion4(),
          checkFieldNull( question4Field ) )
        || checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().getQuestion5(),
          checkFieldNull( question5Field ) )
        || checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().getQuestion6(),
          checkFieldNull( question6Field ) )
        ||

        checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().getQuestion1AuthorId(),
          getTeacherFromUserOject( ( Teacher ) question1AuthorField.getUserObject() ) )
        || checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().getQuestion2AuthorId(),
          getTeacherFromUserOject( ( Teacher ) question2AuthorField.getUserObject() ) )
        || checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().getQuestion3AuthorId(),
          getTeacherFromUserOject( ( Teacher ) question3AuthorField.getUserObject() ) )
        || checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().getQuestion4AuthorId(),
          getTeacherFromUserOject( ( Teacher ) question4AuthorField.getUserObject() ) )
        || checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().getQuestion5AuthorId(),
          getTeacherFromUserOject( ( Teacher ) question5AuthorField.getUserObject() ) )
        || checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().getQuestion6AuthorId(),
          getTeacherFromUserOject( ( Teacher ) question6AuthorField.getUserObject() ) )
        ||

        checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().getAnswer(), checkFieldNull( answerField ) )
        || checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().getTraining(),
          checkFieldNull( trainingField ) )
        || checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().getTraining(),
          checkFieldNull( trainingField ) )
        || checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().getTimeCount(),
          checkFieldNull( timeCountField ) )
        ||

        checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().getMark(), markField.getSelectedItem() )
        ||

        checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().getOpinion(), checkFieldNull( opinionField ) )
        || checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().getOutputMark(), outputMarkField
          .getSelectedItem() )
        || checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().getResultCommon(),
          checkFieldNull( resultCommonField ) )
        || checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().getManagerResult(),
          checkFieldNull( managerResultField ) )
        ||

        checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().getMarkCountDistinction(),
          checkFieldNull( markCountDistinctionField ) )
        || checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().getMarkCountGood(),
          checkFieldNull( markCountGoodField ) )
        || checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().getMarkCountSatisfactory(),
          checkFieldNull( markCountSatisfactoryField ) )
        ||

        checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().isCompPresent(),
          checkFieldNull( compPresentField ) )
        || checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().isDistribution(),
          checkFieldNull( distributionField ) )
        || checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().isBestWork(), checkFieldNull( bestWorkField ) )
        || checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo().isMagistracy(),
          checkFieldNull( magistracyField ) ) || checkObjectForChange( defenceGraduateWork.getGraduateWorkInfo()
        .isPrintedPublication(), checkFieldNull( printedPublicationField ) ) );

  }

  private boolean checkObjectForChange( Object o1, Object o2 )
  {

    if( o1 != null && o2 != null )
    {

      return !o1.equals( o2 );
    }

    return o1 != null || o2 != null;

  }

  public void windowOpened( WindowEvent e )
  {

  }

  public void windowClosing( WindowEvent e )
  {

    checkChange();
    updateData( null );
  }

  public void windowClosed( WindowEvent e )
  {
  }

  public void windowIconified( WindowEvent e )
  {

  }

  public void windowDeiconified( WindowEvent e )
  {

  }

  public void windowActivated( WindowEvent e )
  {

  }

  public void windowDeactivated( WindowEvent e )
  {

  }
}
