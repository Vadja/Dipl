package org.iit.dr.view.model.table.distribution;

import java.util.List;

import org.iit.dr.data_model.Company;
import org.iit.dr.data_model.DistributionPlace;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.services.CompaniesService;
import org.iit.dr.services.DistributionService;
import org.iit.dr.view.component.ComponentsMediator;
import org.iit.dr.view.model.table.common.AbstractDepartmentReportsTableModel;

/**
 * 
 * @author Uladzimir_Babkou
 * 
 */
public class DistributionCompaniesTableModel extends AbstractDepartmentReportsTableModel<Object>
{
  private static final long serialVersionUID = 2986989396688649111L;

  public static final String[] HEADERS = {"№", "Предприятие", "Общ.", "   Персональные   ", "   Распределены   "};

  public static final int[] COLUMNS_PROPORTIONS = {1, 10, 1, 20, 20};

  private List<DistributionPlace> distributionPlacesList;

  public DistributionCompaniesTableModel()
  {
    distributionPlacesList = DistributionService.getDistributionPlacesList();
  }

  public DistributionCompaniesTableModel( ComponentsMediator mediator )
  {
    this();
    this.mediator = mediator;
    mediator.addComponent( this );
  }

  @Override
  public int getRowCount()
  {
    return distributionPlacesList == null ? 0 : distributionPlacesList.size();
  }

  @Override
  public Object getValueAt( int rowIndex, int columnIndex )
  {
    switch( columnIndex )
    {
      case 0:
        return getNumber( rowIndex );
      case 1:
        return getCompanyName( rowIndex );
      case 2:
        return getCommonRequestsNumber( rowIndex );
      case 3:
        return getStudentsRequests( rowIndex );
      case 4:
        return getDistributedStudents( rowIndex );
      case ID_COLUMN:
        return distributionPlacesList.get( rowIndex ).getCompanyId();
    }
    return null;
  }

  @Override
  public void setValueAt( Object obj, int rowIndex, int columnIndex )
  {
    switch( columnIndex )
    {
      case 2:
        setCommonRequestsNumber( rowIndex, obj );
        break;

      case 3:
        setStudentsRequests( rowIndex, obj );
        break;

      case 4:
        setDistributedStudents( rowIndex, obj );
        break;

      default:
        return;
    }
    updateData();
    updateOtherComponents();
  }

  private String getCompanyName( int rowIndex )
  {
    Company company = CompaniesService.getCompany( distributionPlacesList.get( rowIndex ).getCompanyId() );
    return company == null ? null : company.getShortName();
  }

  private Integer getNumber( int rowIndex )
  {
    return rowIndex + 1;
  }

  private Integer getCommonRequestsNumber( int rowIndex )
  {
    return distributionPlacesList.get( rowIndex ).getCommonRequestsNumber();
  }

  private void setCommonRequestsNumber( int rowIndex, Object obj )
  {
    if( obj != null )
    {
      distributionPlacesList.get( rowIndex ).setCommonRequestsNumber( ( Integer ) obj );
    }
  }

  private String getStudentsRequests( int rowIndex )
  {
    List<Student> studentsList =
      DistributionService.getRequestedStudentsByCompanyId( distributionPlacesList.get( rowIndex ).getCompanyId() );
    if( studentsList.size() == 0 )
      return null;
    String result = "";
    for( int i = 0; i < studentsList.size(); i++ )
    {
      result += studentsList.get( i ).getShortName();
      if( i != studentsList.size() - 1 )
        result += ", ";
    }
    return result;
  }

  private void setStudentsRequests( int rowIndex, Object obj )
  {

  }

  private void setDistributedStudents( int rowIndex, Object obj )
  {

  }

  private String getDistributedStudents( int rowIndex )
  {
    List<Student> studentsList =
      DistributionService.getDistributedStudentsByCompanyId( distributionPlacesList.get( rowIndex ).getCompanyId() );
    if( studentsList.size() == 0 )
      return null;
    String result = "";
    for( int i = 0; i < studentsList.size(); i++ )
    {
      result += studentsList.get( i ).getShortName();
      if( i != studentsList.size() - 1 )
        result += ", ";
    }
    return result;
  }

  @Override
  public Class<?> getColumnClass( int columnIndex )
  {
    switch( columnIndex )
    {
      case 0:
      case 2:
        return Integer.class;
      default:
        return String.class;
    }
  }

  @Override
  public boolean isCellEditable( int rowIndex, int columnIndex )
  {
    switch( columnIndex )
    {
      case 2:
      case 3:
      case 4:
        return true;
      default:
        return false;
    }
  }

  @Override
  public String[] getHeaders()
  {
    return HEADERS;
  }
}
