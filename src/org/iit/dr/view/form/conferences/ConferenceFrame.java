package org.iit.dr.view.form.conferences;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.iit.dr.data_model.Conference;
import org.iit.dr.data_model.Member;
import org.iit.dr.data_model.role.Person;
import org.iit.dr.services.ConferenceService;
import org.iit.dr.utils.MemoryManager;
import org.iit.dr.utils.UUIDUtils;
import org.iit.dr.view.component.JButtonSelect;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.form.staffers.StafferListFrame;
import org.iit.dr.view.model.table.conferences.ConferenceTableModel;

/**
 * Created by IntelliJ IDEA. User: Admin Date: 15.12.2010 Time: 0:36:42 To change this template use File | Settings |
 * File Templates.
 */
public class ConferenceFrame extends JInternalFrameExt<Member>
{

  JTextField themeField;

  // JTextField conferenceField;
  // JTextField yearField;
  JTextField authorField;

  JTextField prizeField;

  JTextField publicationArea;

  JButtonSelect confField;

  JButtonSelect teacherField;

  Member member;

  List<Person> authorList;

  ConferenceTableModel model;

  ConferenceTreeFrame conferenceTreeFrame;

  StafferListFrame stafferListFrame;

  public ConferenceFrame() throws HeadlessException
  {

    conferenceTreeFrame = ( ConferenceTreeFrame ) MemoryManager.getObject( ConferenceTreeFrame.class, true );
    stafferListFrame = ( StafferListFrame ) MemoryManager.getObject( StafferListFrame.class );
  }

  @Override
  public boolean showFrame( Object parent, Member member )
  {

    model = ( ConferenceTableModel ) parent;

    if( member != null )
    {

      this.member = member;
    }
    else
    {

      this.member = new Member();
    }

    themeField.setText( this.member.getTheme() );
    authorField.setText( "" );
    prizeField.setText( this.member.getPrize() );
    publicationArea.setText( this.member.getPublication() );

    if( ( member != null ) && ( member.getPersonList() != null ) )
    {
      authorList = member.getPersonList();
      for( Person pers : authorList )
      {
        authorField.setText( authorField.getText() + "," + pers.getShortName() );
      }
    }
    else
    {
      authorList = new ArrayList<Person>();
    }

    // OrganizationUnit organizationUnit = DataStorage.getInstance().getOrganizationUnit(this.member.getGroup());
    //
    // if (organizationUnit != null) {
    //
    // groupField.setUserObject(organizationUnit);
    // }

    setVisible( true );

    return true;
  }

  @Override
  protected void init()
  {

    setTitle( "Форма редактирования: Доклад" );

    setMinimumSize( new Dimension( 600, 200 ) );
  }

  private void initComponents()
  {

    themeField = createJTextField();
    authorField = createJTextField();
    prizeField = createJTextField();
    publicationArea = createJTextField();
    publicationArea.setSize( publicationArea.getHeight(), publicationArea.getSize().height );

    confField = createJButtonSelect();
    teacherField = createJButtonSelect();

  }

  @Override
  protected void generateComponents()
  {

    initComponents();

    JPanel leftJPane =
      createVPane( 2, createField( themeField, "Тема: " ),
        createVPane( 2, createField( authorField, "Авторы: " ), createField( teacherField, "Добавить" ) ),
        createField( confField, "Конференция: " ) );

    JPanel rightJPane =
      createVPane( 1, createField( publicationArea, "Публикация: " ), createField( prizeField, "Награды: " ) );

    JPanel fieldsPane = createHPane( 20, leftJPane, rightJPane );

    JButton applyButton = new JButton( "Применить" );
    applyButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        member.setTheme( checkFieldNull( themeField ) );
        member.setPersonList( authorList );
        member.setPrize( checkFieldNull( prizeField ) );
        member.setPublication( checkFieldNull( publicationArea ) );

        Conference conf = ConferenceService.getConferenceByTitle( confField.getUserObject().toString() );
        // OrganizationUnit organizationUnit = (OrganizationUnit) groupField.getUserObject();
        // member.setGroup(organizationUnit != null ? organizationUnit.getId() : null);

        if( member.getId() == null )
        {

          member.setId( UUIDUtils.getUUID() );
          conf.getMemberList().add( member );
          // DataStorage.getInstance().getmemberList().add(member);
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

    getContentPane().add( fieldsPane, BorderLayout.CENTER );
    getContentPane().add( createButtonPane( 10, applyButton, cancelButton ), BorderLayout.SOUTH );

    confField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        conferenceTreeFrame.showFrame( ConferenceFrame.this, confField );
      }
    } );

    teacherField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( ConferenceFrame.this, teacherField );
        Person pers = ( Person ) teacherField.getUserObject();
        if( !authorField.getText().equals( "" ) )
        {
          authorField.setText( authorField.getText() + "," + pers.getShortName() );
        }
        else
        {
          authorField.setText( pers.getShortName() );
        }
        authorList.add( pers );
        teacherField.setText( "Добавить" );
      }
    } );
  }
}
