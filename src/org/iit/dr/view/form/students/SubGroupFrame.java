package org.iit.dr.view.form.students;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.iit.dr.data_model.OrganizationUnitWrapper;
import org.iit.dr.data_model.unit.OrganizationUnit;
import org.iit.dr.data_model.unit.SubGroup;
import org.iit.dr.data_model.unit.UnitType;
import org.iit.dr.services.OrganizationUnitService;
import org.iit.dr.utils.UUIDUtils;
import org.iit.dr.view.component.JInternalFrameExt;

public class SubGroupFrame extends JInternalFrameExt<OrganizationUnitWrapper>
{
  OrganizationUnitWrapper organizationUnitWrapper;

  GroupTreeFrame groupTreeFrame;

  JComboBox subComboBox;

  @Override
  public boolean showFrame( Object parent, OrganizationUnitWrapper e )
  {
    groupTreeFrame = ( GroupTreeFrame ) parent;
    this.organizationUnitWrapper = e;

    if( organizationUnitWrapper.getOrganizationUnit() != null )
    {
      subComboBox.setSelectedItem( organizationUnitWrapper.getOrganizationUnit().getName() );
    }
    else
    {
      this.organizationUnitWrapper.setOrganizationUnit( new OrganizationUnit() );
      this.organizationUnitWrapper.getOrganizationUnit().setType( UnitType.SUBGROUP );
    }

    setVisible( true );
    return true;
  }

  @Override
  protected void init()
  {

    setTitle( "Форма редактирования: Подгруппа" );

    setMinimumSize( new Dimension( 300, 250 ) );
    setResizable( false );

  }

  @Override
  protected void generateComponents()
  {
    subComboBox = createJComboBox();
    subComboBox.addItem( SubGroup.FIRST );
    subComboBox.addItem( SubGroup.SECOND );
    JPanel fieldsPane = createVPane( 5, createField( subComboBox, "Название: " ) );

    JButton applyButton = new JButton( "Применить" );
    applyButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        organizationUnitWrapper.getOrganizationUnit().setName(
          ( ( SubGroup ) subComboBox.getSelectedItem() ).getNameSubGroup() );

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
