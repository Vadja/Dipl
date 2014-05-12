package org.iit.dr.view.model.table.students;


/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class SimpleBudgetStudentsTableModel extends SimpleStudentsTableModel
{
  private static final long serialVersionUID = -2340496603294860189L;

  public SimpleBudgetStudentsTableModel()
  {

  }

  @Override
  public void updateData()
  {
    // if( groupIdFilterList != null )
    // {
    // objectList = StudentsService.getStudentsWithoutPracticePlace( groupIdFilterList, practiceType );
    fireTableDataChanged();
    // }
  }
}
