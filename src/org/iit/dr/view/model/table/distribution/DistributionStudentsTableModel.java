package org.iit.dr.view.model.table.distribution;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.iit.dr.data_model.Company;
import org.iit.dr.data_model.comparators.StudentsByMarkComparator;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.data_model.unit.Course;
import org.iit.dr.services.DistributionService;
import org.iit.dr.services.OrganizationUnitService;
import org.iit.dr.services.StudentsService;
import org.iit.dr.view.component.ComponentsMediator;
import org.iit.dr.view.model.table.common.AbstractDepartmentReportsTableModel;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
enum RowType
{
  BUDGET_DELIMITER, BUDGET, EMPTY_DELIMITER, PAID_DELIMITER, PAID
}

public class DistributionStudentsTableModel extends AbstractDepartmentReportsTableModel<Object>
{
  private static final long serialVersionUID = 6855802377267051423L;

  public static final String[] HEADERS = {"№", "ФИО", "Ср. бал", "Адрес", "     Заявки     ", "Распределен"};

  public static final int[] COLUMNS_PROPORTIONS = {1, 5, 2, 7, 12, 5};

  private List<Student> budgetStudentsList;

  private List<Student> paidStudentsList;

  public static final String[] BUDGET_DELIMITER_CONTENT = {"", "  с т у д е н т ы", null, "б ю д ж е т н о й",
    "         ф о р м ы   ", "о б у ч е н и я"};

  public static final String[] PAID_DELIMITER_CONTENT = {"", "  с т у д е н т ы", null, "    п л а т н о й  ",
    "         ф о р м ы   ", "о б у ч е н и я"};

  private static final String OU_ID = OrganizationUnitService.getCourseId( Course.FIFTH );

  public DistributionStudentsTableModel()
  {
    budgetStudentsList = StudentsService.getStudentsByOuIdAndStudyForm( OU_ID, true );
    paidStudentsList = StudentsService.getStudentsByOuIdAndStudyForm( OU_ID, false );
    sortStudents();
  }

  public DistributionStudentsTableModel( ComponentsMediator mediator )
  {
    this();
    this.mediator = mediator;
    mediator.addComponent( this );
  }

  @Override
  public int getRowCount()
  {
    return getBudgetStudentsNumber() + 3 + getPaidStudentsNumber();
  }

  @Override
  public void setValueAt( Object obj, int rowIndex, int columnIndex )
  {
    switch( columnIndex )
    {
      case 2:
        setMark( rowIndex, obj );
        break;

      case 4:
        setRequests( rowIndex, obj );
        break;

      case 5:
        setDistributionPlace( rowIndex, obj );
        break;

      default:
        return;
    }
    updateData();
    updateOtherComponents();
  }

  @Override
  public Object getValueAt( int rowIndex, int columnIndex )
  {
    if( isDelimiterRow( rowIndex ) )
    {
      return getDelimiterContent( getRowType( rowIndex ), columnIndex );
    }
    switch( columnIndex )
    {
      case 0:
        return getNumber( rowIndex );
      case 1:
        return getFIO( rowIndex );
      case 2:
        return getAverageMark( rowIndex );
      case 3:
        return getAddress( rowIndex );
      case 4:
        return getRequests( rowIndex );
      case 5:
        return getDistributionPlace( rowIndex );
    }
    return null;
  }

  private void setRequests( int rowIndex, Object obj )
  {
    Student stud = getStudent( rowIndex );
    if( stud != null )
    {
      DistributionService.setRequestsForStudent( getStudent( rowIndex ).getId(), ( List<String> ) obj );
    }
  }

  public String getDistributionPlace( int rowIndex )
  {
    Student student = getStudent( rowIndex );
    if( student != null )
    {
      Company company = DistributionService.getCompanyByDistributedStudent( student.getId() );
      if( company != null )
      {
        return company.getShortName();
      }
    }
    return null;
  }

  public void setDistributionPlace( int rowIndex, Object obj )
  {
    Student stud = getStudent( rowIndex );
    String companyShortName = ( String ) obj;
    if( stud != null && companyShortName != null )
    {
      DistributionService.clearDistributedPlaceForStudent( getStudent( rowIndex ).getId() );
      if( !companyShortName.equals( "" ) )
      {
        DistributionService.setDistributionPlaceForStudent( getStudent( rowIndex ).getId(), companyShortName );
      }
    }
  }

  private void setMark( int rowIndex, Object obj )
  {
    Student stud = getStudent( rowIndex );
    if( stud != null && obj != null )
    {
      double mark = ( Double ) obj;
      if( mark >= 4 && mark <= 10 )
      {
        stud.setAverageMark( mark );
        sortStudents();
      }
    }
  }

  private Integer getNumber( int rowIndex )
  {
    switch( getRowType( rowIndex ) )
    {
      case BUDGET:
        return getBudgetIndex( rowIndex ) + 1;
      case PAID:
        return getPaidIndex( rowIndex ) + 1;
    }
    return null;
  }

  private String getFIO( int rowIndex )
  {
    Student student = getStudent( rowIndex );
    if( student != null )
    {
      return student.getShortName();
    }
    return null;
  }

  private Double getAverageMark( int rowIndex )
  {
    Student student = getStudent( rowIndex );
    if( student != null )
    {
      double avgMark = student.getAverageMark();
      return avgMark == 0 ? null : avgMark;
    }
    return null;
  }

  private String getAddress( int rowIndex )
  {
    Student student = getStudent( rowIndex );
    if( student != null )
    {
      return student.getFromCity();
    }
    return null;
  }

  private String getRequests( int rowIndex )
  {
    Student student = getStudent( rowIndex );
    if( student != null )
    {
      List<Company> companiesList = DistributionService.getCompaniesByRequestedStudent( student.getId() );
      if( companiesList.size() == 0 )
        return null;
      String result = "";
      for( int i = 0; i < companiesList.size(); i++ )
      {
        result += companiesList.get( i ).getShortName();
        if( i != companiesList.size() - 1 )
          result += ", ";
      }
      return result;
    }
    return null;
  }

  @Override
  public Class<?> getColumnClass( int columnIndex )
  {
    switch( columnIndex )
    {
      case 0:
        return Integer.class;
      case 2:
        return Double.class;
      default:
        return String.class;
    }
  }

  @Override
  public boolean isCellEditable( int rowIndex, int columnIndex )
  {
    if( isDelimiterRow( rowIndex ) )
    {
      return false;
    }
    switch( columnIndex )
    {
      case 2:
      case 4:
      case 5:
        return true;
      default:
        return false;
    }
  }

  private boolean isDelimiterRow( int rowIndex )
  {
    RowType type = getRowType( rowIndex );
    if( type == RowType.BUDGET_DELIMITER || type == RowType.PAID_DELIMITER || type == RowType.EMPTY_DELIMITER )
    {
      return true;
    }
    return false;
  }

  private String getDelimiterContent( RowType type, int columnIndex )
  {
    switch( type )
    {
      case EMPTY_DELIMITER:
        return null;
      case BUDGET_DELIMITER:
        return BUDGET_DELIMITER_CONTENT[columnIndex];
      case PAID_DELIMITER:
        return PAID_DELIMITER_CONTENT[columnIndex];
    }
    throw new IllegalArgumentException( "incorrect row type" );
  }

  private int getBudgetStudentsNumber()
  {
    return budgetStudentsList == null ? 0 : budgetStudentsList.size();
  }

  private int getPaidStudentsNumber()
  {
    return paidStudentsList == null ? 0 : paidStudentsList.size();
  }

  private int getBudgetIndex( int rowIndex )
  {
    return rowIndex - 1;
  }

  private int getPaidIndex( int rowIndex )
  {
    return rowIndex - getBudgetStudentsNumber() - 3;
  }

  private RowType getRowType( int rowIndex )
  {
    if( rowIndex == 0 )
      return RowType.BUDGET_DELIMITER;
    else if( rowIndex <= getBudgetStudentsNumber() )
      return RowType.BUDGET;
    else if( rowIndex == getBudgetStudentsNumber() + 1 )
      return RowType.EMPTY_DELIMITER;
    else if( rowIndex == getBudgetStudentsNumber() + 2 )
      return RowType.PAID_DELIMITER;
    else
      return RowType.PAID;
  }

  private Student getStudent( int rowIndex )
  {
    switch( getRowType( rowIndex ) )
    {
      case BUDGET:
        return budgetStudentsList.get( getBudgetIndex( rowIndex ) );
      case PAID:
        return paidStudentsList.get( getPaidIndex( rowIndex ) );
    }
    return null;
  }

  private void sortStudents()
  {
    budgetStudentsList = StudentsService.getStudentsByOuIdAndStudyForm( OU_ID, true );
    paidStudentsList = StudentsService.getStudentsByOuIdAndStudyForm( OU_ID, false );

    Comparator<Student> comparator = new StudentsByMarkComparator();
    Collections.sort( budgetStudentsList, comparator );
    Collections.sort( paidStudentsList, comparator );
  }

  @Override
  public String[] getHeaders()
  {
    return HEADERS;
  }
}
