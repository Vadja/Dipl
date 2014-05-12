package org.iit.dr.view.form.graduate_work;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.iit.dr.data_model.DefenceGraduateWork;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.services.GraduateWorkService;
import org.iit.dr.utils.MemoryManager;
import org.iit.dr.view.component.JButtonSelect;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.form.staffers.StafferListSelectButtonFrame;

/**
 * CommissionFrame.
 * 
 * @author Yuriy Karpovich
 */
public class CommissionFrame extends JInternalFrameExt<Object>
{
  private static final long serialVersionUID = 6978496185510442008L;

  StafferListSelectButtonFrame stafferListFrame;

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

  List<String> selectedItemList;

  public CommissionFrame() throws HeadlessException
  {

    stafferListFrame =
      ( StafferListSelectButtonFrame ) MemoryManager.getObject( StafferListSelectButtonFrame.class, true );
  }

  @Override
  @SuppressWarnings( "unchecked" )
  public boolean showFrame( Object parent, Object o )
  {

    selectedItemList = ( List<String> ) o;

    presideField.setUserObject( null );
    commissionerSecrField.setUserObject( null );
    commissioner0Field.setUserObject( null );
    commissioner1Field.setUserObject( null );
    commissioner2Field.setUserObject( null );
    commissioner3Field.setUserObject( null );
    commissioner4Field.setUserObject( null );
    commissioner5Field.setUserObject( null );
    commissioner6Field.setUserObject( null );
    commissioner7Field.setUserObject( null );
    commissioner8Field.setUserObject( null );
    commissioner9Field.setUserObject( null );

    // ---

    setVisible( true );
    return true;
  }

  private String getTeacherFromUserOject( Teacher teacher )
  {

    return teacher != null ? teacher.getId() : null;
  }

  @Override
  protected void init()
  {

    setTitle( "Добавление комиссии" );

    setMinimumSize( new Dimension( 600, 350 ) );
  }

  private void initComponents()
  {

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
  }

  @Override
  protected void generateComponents()
  {
    initComponents();

    JPanel jPane =
      createVPane(
        5,
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

    JButton applyButton = new JButton( "Применить" );
    applyButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        String presideFieldGUID = getTeacherFromUserOject( ( Teacher ) presideField.getUserObject() );
        String commissionerSecrFieldGUID = getTeacherFromUserOject( ( Teacher ) commissionerSecrField.getUserObject() );
        String commissioner0FieldGUID = getTeacherFromUserOject( ( Teacher ) commissioner0Field.getUserObject() );
        String commissioner1FieldGUID = getTeacherFromUserOject( ( Teacher ) commissioner1Field.getUserObject() );
        String commissioner2FieldGUID = getTeacherFromUserOject( ( Teacher ) commissioner2Field.getUserObject() );
        String commissioner3FieldGUID = getTeacherFromUserOject( ( Teacher ) commissioner3Field.getUserObject() );
        String commissioner4FieldGUID = getTeacherFromUserOject( ( Teacher ) commissioner4Field.getUserObject() );
        String commissioner5FieldGUID = getTeacherFromUserOject( ( Teacher ) commissioner5Field.getUserObject() );
        String commissioner6FieldGUID = getTeacherFromUserOject( ( Teacher ) commissioner6Field.getUserObject() );
        String commissioner7FieldGUID = getTeacherFromUserOject( ( Teacher ) commissioner7Field.getUserObject() );
        String commissioner8FieldGUID = getTeacherFromUserOject( ( Teacher ) commissioner8Field.getUserObject() );
        String commissioner9FieldGUID = getTeacherFromUserOject( ( Teacher ) commissioner9Field.getUserObject() );

        if( selectedItemList != null )
        {

          for( String selectedItem : selectedItemList )
          {

            DefenceGraduateWork defenceGraduateWork = GraduateWorkService.getDefenceGraduateWork( selectedItem );

            if( defenceGraduateWork != null )
            {

              if( presideFieldGUID != null )
              {

                defenceGraduateWork.setPresideId( presideFieldGUID );
              }

              if( commissionerSecrFieldGUID != null )
              {

                defenceGraduateWork.setCommissionerSecrId( commissionerSecrFieldGUID );
              }

              if( commissioner0FieldGUID != null )
              {

                defenceGraduateWork.setCommissioner0Id( commissioner0FieldGUID );
              }
              if( commissioner1FieldGUID != null )
              {

                defenceGraduateWork.setCommissioner1Id( commissioner1FieldGUID );
              }
              if( commissioner2FieldGUID != null )
              {

                defenceGraduateWork.setCommissioner2Id( commissioner2FieldGUID );
              }
              if( commissioner3FieldGUID != null )
              {

                defenceGraduateWork.setCommissioner3Id( commissioner3FieldGUID );
              }
              if( commissioner4FieldGUID != null )
              {

                defenceGraduateWork.setCommissioner4Id( commissioner4FieldGUID );
              }
              if( commissioner5FieldGUID != null )
              {

                defenceGraduateWork.setCommissioner5Id( commissioner5FieldGUID );
              }
              if( commissioner6FieldGUID != null )
              {

                defenceGraduateWork.setCommissioner6Id( commissioner6FieldGUID );
              }
              if( commissioner7FieldGUID != null )
              {

                defenceGraduateWork.setCommissioner7Id( commissioner7FieldGUID );
              }
              if( commissioner8FieldGUID != null )
              {

                defenceGraduateWork.setCommissioner8Id( commissioner8FieldGUID );
              }
              if( commissioner9FieldGUID != null )
              {

                defenceGraduateWork.setCommissioner9Id( commissioner9FieldGUID );
              }
            }
          }
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

    getContentPane().add( jPane, BorderLayout.CENTER );
    getContentPane().add( createButtonPane( 10, applyButton, cancelButton ), BorderLayout.SOUTH );

    presideField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( CommissionFrame.this, presideField );
      }
    } );

    commissionerSecrField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( CommissionFrame.this, commissionerSecrField );
      }
    } );

    commissioner0Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( CommissionFrame.this, commissioner0Field );
      }
    } );
    commissioner1Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( CommissionFrame.this, commissioner1Field );
      }
    } );
    commissioner2Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( CommissionFrame.this, commissioner2Field );
      }
    } );
    commissioner3Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( CommissionFrame.this, commissioner3Field );
      }
    } );
    commissioner4Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( CommissionFrame.this, commissioner4Field );
      }
    } );
    commissioner5Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( CommissionFrame.this, commissioner5Field );
      }
    } );
    commissioner6Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( CommissionFrame.this, commissioner6Field );
      }
    } );
    commissioner7Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( CommissionFrame.this, commissioner7Field );
      }
    } );
    commissioner8Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( CommissionFrame.this, commissioner8Field );
      }
    } );
    commissioner9Field.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( CommissionFrame.this, commissioner9Field );
      }
    } );
  }
}