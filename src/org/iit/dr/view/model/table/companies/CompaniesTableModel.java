package org.iit.dr.view.model.table.companies;

import java.util.ArrayList;
import java.util.List;

import org.iit.dr.data_model.Company;
import org.iit.dr.services.CompaniesService;
import org.iit.dr.view.model.table.common.AbstractDepartmentReportsTableModel;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class CompaniesTableModel extends AbstractDepartmentReportsTableModel<Company>
{
  private static final long serialVersionUID = 8361795780853012783L;

  public static final String[] HEADERS = {"Название", "Телефон", "Адрес"};

  public CompaniesTableModel()
  {
    objectList = new ArrayList<Company>();
  }

  @Override
  public Object getValueAt( int rowIndex, int columnIndex )
  {
    if( rowIndex < objectList.size() )
    {
      Company company = objectList.get( rowIndex );
      switch( columnIndex )
      {
        case 0:
          return company.getName();
        case 1:
          return company.getPhoneNumber();
        case 2:
          return company.getFullAddress();
        case ID_COLUMN:
          return company.getId();
      }
    }
    return null;
  }

  public void updateData()
  {
    updateData( null );
  }

  public void updateData( String name )
  {
    if( objectList == null )
    {
      objectList = new ArrayList<Company>();
    }
    else
    {
      objectList.clear();
    }
    List<Company> companiesListSource = CompaniesService.getCompaniesList();
    for( Company company : companiesListSource )
    {
      if( applyFilterName( name, company ) )
      {
        objectList.add( company );
      }
    }
    fireTableDataChanged();
  }

  private boolean applyFilterName( String name, Company company )
  {
    return ( name == null )
      || ( company.getName() != null && company.getName().toLowerCase().contains( name.toLowerCase() ) );
  }

  public void addCompany( Company company )
  {
    objectList.add( company );
    fireTableDataChanged();
  }

  public void removeCompany( Company company )
  {
    objectList.remove( company );
    fireTableDataChanged();
  }

  @Override
  public String[] getHeaders()
  {
    return HEADERS;
  }
}
