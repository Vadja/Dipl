package org.iit.dr.view.form.companies;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public abstract class CompanySelectFrame extends CompaniesListFrame
{
  private static final long serialVersionUID = -8893009240944880245L;

  protected abstract void applyButtonAction( String[] selectedCompanyId );

  protected abstract void removeButtonAction( String[] selectedCompanyId );

  @Override
  protected void generateComponents()
  {
    super.generateComponents();
    addSelectDialogComponents();
  }

  protected void addSelectDialogComponents()
  {
    JButton applyButton = new JButton( "Применить" );
    applyButton.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        String[] companyId = CompanySelectFrame.this.getSelectedCompanyId();
        applyButtonAction( companyId );
        setVisible( false );
      }
    } );

    JButton removeButton = new JButton( "Удалить выбор" );
    removeButton.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        String companyId[] = CompanySelectFrame.this.getSelectedCompanyId();
        removeButtonAction( companyId );
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
    getContentPane().add( createNorthButtonPane( 10, applyButton, removeButton, cancelButton ), BorderLayout.SOUTH );
  }
}
