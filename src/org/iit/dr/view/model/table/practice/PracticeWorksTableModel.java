package org.iit.dr.view.model.table.practice;

import java.util.ArrayList;

import org.iit.dr.data_model.PracticeType;
import org.iit.dr.data_model.PracticeWork;
import org.iit.dr.services.CompaniesService;
import org.iit.dr.services.PracticeService;
import org.iit.dr.services.StudentsService;
import org.iit.dr.view.model.table.common.AbstractDepartmentReportsTableModel;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class PracticeWorksTableModel extends AbstractDepartmentReportsTableModel<PracticeWork>
{
  private static final long serialVersionUID = -4124277572824348114L;

  public static final String[] HEADERS = new String[] {"Предприятие", "Студент"};

  private PracticeType practiceType;

  public PracticeWorksTableModel( PracticeType practiceType )
  {
    this.practiceType = practiceType;
    objectList = new ArrayList<PracticeWork>();
  }

  @Override
  public String[] getHeaders()
  {
    return HEADERS;
  }

  @Override
  public Object getValueAt( int rowIndex, int columnIndex )
  {
    if( rowIndex < objectList.size() )
    {
      PracticeWork practiceWork = objectList.get( rowIndex );
      switch( columnIndex )
      {
        case 0:
          return CompaniesService.getCompany(
            PracticeService.getPracticePlaceByPracticeWork( practiceWork, practiceType ).getCompanyId() ).getName();
        case 1:
          return StudentsService.getStudent( practiceWork.getStudentId() );
        case ID_COLUMN:
          return practiceWork.getStudentId();
      }
    }
    return null;
  }

  public void updateData()
  {
    objectList = PracticeService.getPracticeWorksWithoutConsultantList( practiceType );
    fireTableDataChanged();
  }

}
