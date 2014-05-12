package org.iit.dr.view.form.graduate_work;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.iit.dr.data_model.GraduateWork;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.services.GraduateWorkService;
import org.iit.dr.utils.MemoryManager;
import org.iit.dr.view.component.JButtonSelect;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.form.staffers.StafferListSelectButtonFrame;

/**
 * ConsultantFrame.
 * 
 * @author Yuriy Karpovich
 */
public class ConsultantFrame extends JInternalFrameExt<Object>
{
  private static final long serialVersionUID = -7126941942548055275L;

  StafferListSelectButtonFrame stafferListFrame;

  JButtonSelect managerField;

  JButtonSelect consultantBySpecialityField;

  JButtonSelect consultantByEconomicsField;

  JButtonSelect consultantByProtectionOfLaborField;

  JButtonSelect consultantByNormalInspectionField;

  JButtonSelect reviewerField;

  java.util.List<String> selectedItemList;

  public ConsultantFrame() throws HeadlessException
  {

    stafferListFrame =
      ( StafferListSelectButtonFrame ) MemoryManager.getObject( StafferListSelectButtonFrame.class, true );
  }

  @Override
  @SuppressWarnings( "unchecked" )
  public boolean showFrame( Object parent, Object o )
  {

    selectedItemList = ( List<String> ) o;

    managerField.setUserObject( null );
    consultantBySpecialityField.setUserObject( null );
    consultantByEconomicsField.setUserObject( null );
    consultantByProtectionOfLaborField.setUserObject( null );
    consultantByNormalInspectionField.setUserObject( null );
    reviewerField.setUserObject( null );

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

    setTitle( "Добавление консультантов" );

    setMinimumSize( new Dimension( 400, 350 ) );
  }

  private void initComponents()
  {

    managerField = createJButtonSelect();
    consultantBySpecialityField = createJButtonSelect();
    consultantByEconomicsField = createJButtonSelect();
    consultantByProtectionOfLaborField = createJButtonSelect();
    consultantByNormalInspectionField = createJButtonSelect();
    reviewerField = createJButtonSelect();
  }

  @Override
  protected void generateComponents()
  {
    initComponents();

    JPanel leftJPane =
      createVPane( 5, createField( managerField, "Руководитель: " ),
        createField( consultantBySpecialityField, "Консультант по специальности: " ),
        createField( consultantByEconomicsField, "Консультант по экономике: " ),
        createField( consultantByProtectionOfLaborField, "Консультант по охране труда: " ),
        createField( consultantByNormalInspectionField, "Консультант по нормоконтролю: " ),
        createField( reviewerField, "Рецензент: " ) );

    JButton applyButton = new JButton( "Применить" );
    applyButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        String managerGUID = getTeacherFromUserOject( ( Teacher ) managerField.getUserObject() );
        String consultantBySpecialityGUID =
          getTeacherFromUserOject( ( Teacher ) consultantBySpecialityField.getUserObject() );
        String consultantByEconomicsGUID =
          getTeacherFromUserOject( ( Teacher ) consultantByEconomicsField.getUserObject() );
        String consultantByProtectionOfLaborGUID =
          getTeacherFromUserOject( ( Teacher ) consultantByProtectionOfLaborField.getUserObject() );
        String consultantByNormalInspectionGUID =
          getTeacherFromUserOject( ( Teacher ) consultantByNormalInspectionField.getUserObject() );
        String reviewerGUID = getTeacherFromUserOject( ( Teacher ) reviewerField.getUserObject() );

        if( selectedItemList != null )
        {

          for( String selectedItem : selectedItemList )
          {

            GraduateWork graduateWorkItem = GraduateWorkService.getGraduateWork( selectedItem );

            if( graduateWorkItem != null )
            {

              if( managerGUID != null )
              {

                graduateWorkItem.setManagerId( managerGUID );
              }

              if( consultantBySpecialityGUID != null )
              {

                graduateWorkItem.setConsultantIdBySpeciality( consultantBySpecialityGUID );
              }

              if( consultantByEconomicsGUID != null )
              {

                graduateWorkItem.setConsultantIdByEconomics( consultantByEconomicsGUID );
              }

              if( consultantByProtectionOfLaborGUID != null )
              {

                graduateWorkItem.setConsultantIdByProtectionOfLabor( consultantByProtectionOfLaborGUID );
              }

              if( consultantByNormalInspectionGUID != null )
              {

                graduateWorkItem.setConsultantIdByNormalInspection( consultantByNormalInspectionGUID );
              }

              if( reviewerGUID != null )
              {

                graduateWorkItem.setReviewerId( reviewerGUID );
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

    getContentPane().add( leftJPane, BorderLayout.CENTER );
    getContentPane().add( createButtonPane( 10, applyButton, cancelButton ), BorderLayout.SOUTH );

    managerField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( ConsultantFrame.this, managerField );
      }
    } );

    consultantByEconomicsField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( ConsultantFrame.this, consultantByEconomicsField );
      }
    } );

    consultantBySpecialityField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( ConsultantFrame.this, consultantBySpecialityField );
      }
    } );

    consultantByProtectionOfLaborField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( ConsultantFrame.this, consultantByProtectionOfLaborField );
      }
    } );

    consultantByNormalInspectionField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( ConsultantFrame.this, consultantByNormalInspectionField );
      }
    } );

    reviewerField.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        stafferListFrame.showFrame( ConsultantFrame.this, reviewerField );
      }
    } );
  }
}
