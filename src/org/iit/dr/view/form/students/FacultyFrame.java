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
import org.iit.dr.data_model.unit.Faculty;
import org.iit.dr.data_model.unit.OrganizationUnit;
import org.iit.dr.data_model.unit.UnitType;
import org.iit.dr.services.OrganizationUnitService;
import org.iit.dr.utils.UUIDUtils;
import org.iit.dr.view.component.JInternalFrameExt;

public class FacultyFrame extends JInternalFrameExt<OrganizationUnitWrapper>
{
  OrganizationUnitWrapper organizationUnitWrapper;

  GroupTreeFrame groupTreeFrame;

  JTextField nameField;

  JComboBox typeComboBox;

  @Override
  public boolean showFrame( Object parent, OrganizationUnitWrapper e )
  {
    groupTreeFrame = ( GroupTreeFrame ) parent;
    this.organizationUnitWrapper = e;

    if( organizationUnitWrapper.getOrganizationUnit() != null )
    {

      nameField.setText( organizationUnitWrapper.getOrganizationUnit().getName() );
      typeComboBox.setSelectedItem( organizationUnitWrapper.getOrganizationUnit().getDepartamentType() );

    }
    else
    {
      this.organizationUnitWrapper.setOrganizationUnit( new OrganizationUnit() );
      this.organizationUnitWrapper.getOrganizationUnit().setType( UnitType.FACULTY );
    }

    setVisible( true );
    return true;
  }

  @Override
  protected void init()
  {
    setTitle( "Форма редактирования: Факультет" );

    setMinimumSize( new Dimension( 300, 250 ) );
    setResizable( false );
  }

  @Override
  protected void generateComponents()
  {
    nameField = createJTextField();
    typeComboBox = createJComboBox();
    typeComboBox.addItem( Faculty.DAY );
    typeComboBox.addItem( Faculty.DO );

    JPanel fieldsPane =
      createVPane( 5, createField( nameField, "Название: " ), createField( typeComboBox, "Отделение: " ) );

    JButton applyButton = new JButton( "Применить" );
    applyButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        organizationUnitWrapper.getOrganizationUnit().setName( checkFieldNull( nameField ) );
        organizationUnitWrapper.getOrganizationUnit().setDepartamentType( ( Faculty ) typeComboBox.getSelectedItem() );

        if( organizationUnitWrapper.getOrganizationUnit().getId() == null )
        {
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
