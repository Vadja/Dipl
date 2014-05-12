package org.iit.dr.view.form.companies;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.iit.dr.data_model.Company;
import org.iit.dr.services.CompaniesService;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.component.JTableExt;
import org.iit.dr.view.model.table.common.TableModelConst;
import org.iit.dr.view.model.table.companies.CompaniesTableModel;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class CompaniesListFrame extends JInternalFrameExt<Object>
{
  private static final long serialVersionUID = -1973564861668476394L;

  protected CompanyFrame companyFrame;

  protected CompaniesTableModel companiesTableModel;

  protected JTextField nameFilter;

  protected JTableExt companiesTable;

  @Override
  public boolean showFrame( Object parent, Object object )
  {
    companiesTableModel.updateData();
    setVisible( true );
    return true;
  }

  @Override
  protected void init()
  {
    setTitle( "Справочник: Предприятия" );
    setMinimumSize( new Dimension( 600, 300 ) );
    companyFrame = new CompanyFrame();
  }

  @Override
  protected void generateComponents()
  {
    nameFilter = createJTextField();
    companiesTableModel = new CompaniesTableModel();
    companiesTable = new JTableExt( companiesTableModel );

    companiesTable.addMouseListener( new MouseAdapter()
    {
      public void mouseClicked( MouseEvent e )
      {
        if( e.getButton() == MouseEvent.BUTTON3 )
        {
          if( companiesTable.getSelectedRow() >= 0 )
          {
            String guid =
              ( String ) companiesTableModel.getValueAt( companiesTable.getSelectedRow(), TableModelConst.ID_COLUMN );
            Company company = CompaniesService.getCompany( guid );
            companyFrame.showFrame( companiesTableModel, company );
          }
        }
      }
    } );

    nameFilter.addKeyListener( new KeyAdapter()
    {
      public void keyReleased( KeyEvent e )
      {
        updateFilter();
      }
    } );

    JScrollPane jScrollPane = new JScrollPane( companiesTable );
    add( jScrollPane, BorderLayout.CENTER );

    JButton addButton = new JButton( "Новое предприятие" );
    addButton.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        companyFrame.showFrame( companiesTableModel, null );
      }
    } );

    JButton editButton = new JButton( "Редактировать" );
    editButton.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        if( companiesTable.getSelectedRow() > -1 )
        {
          String guid =
            ( String ) companiesTableModel.getValueAt( companiesTable.getSelectedRow(), TableModelConst.ID_COLUMN );
          Company company = CompaniesService.getCompany( guid );
          companyFrame.showFrame( companiesTableModel, company );
        }
      }
    } );

    JButton removeButton = new JButton( "Удалить" );
    removeButton.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        if( companiesTable.getSelectedRow() >= 0 )
        {
          String guid =
            ( String ) companiesTableModel.getValueAt( companiesTable.getSelectedRow(), TableModelConst.ID_COLUMN );
          CompaniesService.removeCompany( guid );
          companiesTableModel.updateData();
        }
      }
    } );

    JPanel jButtonPanel = createNorthButtonPane( 5, nameFilter, addButton, editButton, removeButton );
    getContentPane().add( createNorthButtonPane( 5, jButtonPanel ), BorderLayout.NORTH );
  }

  public void updateFilter()
  {
    String textFilter = null;
    if( nameFilter.getText() != null && !nameFilter.getText().equals( "" ) )
    {
      textFilter = nameFilter.getText();
    }
    companiesTableModel.updateData( textFilter );
  }

  public String[] getSelectedCompanyId()
  {
    return companiesTable.getSelectedIds();
  }
}
