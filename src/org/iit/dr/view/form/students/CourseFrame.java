package org.iit.dr.view.form.students;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.iit.dr.data_model.OrganizationUnitWrapper;
import org.iit.dr.data_model.unit.Course;
import org.iit.dr.data_model.unit.OrganizationUnit;
import org.iit.dr.data_model.unit.UnitType;
import org.iit.dr.services.OrganizationUnitService;
import org.iit.dr.utils.UUIDUtils;
import org.iit.dr.view.component.JInternalFrameExt;

public class CourseFrame extends JInternalFrameExt<OrganizationUnitWrapper>
{
  OrganizationUnitWrapper organizationUnitWrapper;

  GroupTreeFrame groupTreeFrame;

  JTextField yearField;

  JTextField specializationField;

  JTextField specializationCodeField;

  JComboBox courseComboBox;

  @Override
  public boolean showFrame( Object parent, OrganizationUnitWrapper e )
  {
    groupTreeFrame = ( GroupTreeFrame ) parent;
    this.organizationUnitWrapper = e;

    if( organizationUnitWrapper.getOrganizationUnit() != null )
    {

      yearField.setText( organizationUnitWrapper.getOrganizationUnit().getEntranceYear() );
      specializationCodeField.setText( organizationUnitWrapper.getOrganizationUnit().getSpecializationCode() );
      specializationField.setText( organizationUnitWrapper.getOrganizationUnit().getSpecialization() );
      courseComboBox.setSelectedItem( organizationUnitWrapper.getOrganizationUnit().getName() );

    }
    else
    {
      this.organizationUnitWrapper.setOrganizationUnit( new OrganizationUnit() );
      this.organizationUnitWrapper.getOrganizationUnit().setType( UnitType.STREAM );
    }

    setVisible( true );
    return true;
  }

  @Override
  protected void init()
  {
    setTitle( "Форма редактирования: Курс" );

    setMinimumSize( new Dimension( 300, 250 ) );
    setResizable( false );

  }

  @Override
  protected void generateComponents()
  {
    yearField = createJTextField();
    specializationField = createJTextField();
    specializationCodeField = createJTextField();
    courseComboBox = createJComboBox();
    for( Course item : Course.values() )
    {
      courseComboBox.addItem( item );
    }
    JPanel fieldsPane =
      createVPane( 5, createField( courseComboBox, "Название: " ),
        createField( specializationField, "Специализация: " ), createField( specializationCodeField,
          "Код специализации: " ), createField( yearField, "* Год поступления: " ) );

    JButton applyButton = new JButton( "Применить" );
    applyButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        organizationUnitWrapper.getOrganizationUnit().setEntranceYear( checkFieldNull( yearField ) );
        organizationUnitWrapper.getOrganizationUnit().setSpecialization( checkFieldNull( specializationField ) );
        organizationUnitWrapper.getOrganizationUnit().setSpecializationCode( checkFieldNull( specializationCodeField ) );
        organizationUnitWrapper.getOrganizationUnit().setName(
          ( ( Course ) courseComboBox.getSelectedItem() ).getNameCourse() );

        if( organizationUnitWrapper.getOrganizationUnit().getId() == null )
        {

          organizationUnitWrapper.getOrganizationUnit().setParentOuId( organizationUnitWrapper.getParentId() );
          organizationUnitWrapper.getOrganizationUnit().setId( UUIDUtils.getUUID() );
          OrganizationUnitService.getOrganizationUnitList().add( organizationUnitWrapper.getOrganizationUnit() );
        }

        if( groupTreeFrame != null )
        {

          groupTreeFrame.updateData();
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
    getContentPane().add( createNorthButtonPane( 10, applyButton, cancelButton ), BorderLayout.SOUTH );

  }

}
