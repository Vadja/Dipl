package org.iit.dr.view.form.companies;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.iit.dr.data_model.Company;
import org.iit.dr.services.CompaniesService;
import org.iit.dr.utils.UUIDUtils;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.model.table.companies.CompaniesTableModel;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class CompanyFrame extends JInternalFrameExt<Company>
{
  private static final long serialVersionUID = 5427949085154903346L;

  private JTextField shortNameField;

  private JTextField fullNameField;

  private JTextField phoneField;

  private JTextField emailField;

  private JTextField postCodeField;

  private JTextField cityField;

  private JTextField addressField;

  private JTextField directorField;

  private Company company;

  CompaniesTableModel model;

  @Override
  public boolean showFrame( Object parent, Company company )
  {
    model = ( CompaniesTableModel ) parent;
    if( company != null )
    {
      this.company = company;
    }
    else
    {
      this.company = new Company();
    }
    shortNameField.setText( this.company.getShortName() );
    fullNameField.setText( this.company.getFullName() );
    phoneField.setText( this.company.getPhoneNumber() );
    emailField.setText( this.company.getEmail() );
    postCodeField.setText( this.company.getPostCode() );
    cityField.setText( this.company.getCity() );
    addressField.setText( this.company.getAddress() );
    directorField.setText( this.company.getDirector() );
    setVisible( true );
    return true;
  }

  @Override
  protected void init()
  {
    setTitle( "Форма редактирования: Предприятие" );
    setMinimumSize( new Dimension( 600, 400 ) );
  }

  @Override
  protected void generateComponents()
  {
    initComponents();
    JPanel leftJPane =
      createVPane( 5, createField( shortNameField, "Краткое название предприятия: " ),
        createField( fullNameField, "Полное название предприятия: " ), createField( phoneField, "Телефон: " ),
        createField( emailField, "E-mail: " ), createField( postCodeField, "Почтовый индекс: " ),
        createField( cityField, "Населенный пункт: " ), createField( addressField, "Улица, дом: " ),
        createField( directorField, "Руководитель предприятия: " ) );

    JPanel fieldsPane = createHPane( 20, leftJPane );
    JButton applyButton = new JButton( "Применить" );
    applyButton.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        company.setShortName( checkFieldNull( shortNameField ) );
        company.setFullName( checkFieldNull( fullNameField ) );
        company.setPhoneNumber( checkFieldNull( phoneField ) );
        company.setEmail( checkFieldNull( emailField ) );
        company.setPostCode( checkFieldNull( postCodeField ) );
        company.setCity( checkFieldNull( cityField ) );
        company.setAddresss( checkFieldNull( addressField ) );
        company.setDirector( checkFieldNull( directorField ) );
        if( company.getId() == null )
        {
          company.setId( UUIDUtils.getUUID() );
          CompaniesService.addCompany( company );
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
  }

  private void initComponents()
  {
    shortNameField = createJTextField();
    fullNameField = createJTextField();
    phoneField = createJTextField();
    emailField = createJTextField();
    postCodeField = createJTextField();
    cityField = createJTextField();
    addressField = createJTextField();
    directorField = createJTextField();
  }
}
