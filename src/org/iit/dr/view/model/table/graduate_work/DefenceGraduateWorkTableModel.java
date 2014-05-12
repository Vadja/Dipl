package org.iit.dr.view.model.table.graduate_work;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.iit.dr.data_model.DefenceGraduateWork;
import org.iit.dr.data_model.Gek;
import org.iit.dr.data_model.GekDay;
import org.iit.dr.data_model.GraduateWork;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.services.GekService;
import org.iit.dr.services.GraduateWorkService;
import org.iit.dr.services.StudentsService;
import org.iit.dr.services.TeachersService;
import org.iit.dr.utils.DateUtils;
import org.iit.dr.view.model.table.common.AbstractDepartmentReportsTableModel;

/**
 * GraduateWorkTableModel.
 * 
 * @author Yuriy Karpovich, Uladzimir Babkou
 */
public class DefenceGraduateWorkTableModel extends AbstractDepartmentReportsTableModel<DefenceGraduateWork>
{
  private static final long serialVersionUID = -6712867899012829730L;

  List<Date> dateFilterList = null;

  public static final String[] HEADERS = new String[] {"","Дипломник", "Дата", "Тема", "Руководитель"};

  public void updateData()
  {
    if( objectList == null )
    {
      objectList = new ArrayList<DefenceGraduateWork>();
    }
    else
    {
      objectList.clear();
    }
    List<DefenceGraduateWork> defenceGraduateWorkListSource =
      GraduateWorkService.getDefenceGraduateWorkListOrderByStudent();
    if( defenceGraduateWorkListSource != null )
    {
      for( DefenceGraduateWork defenceGraduateWorkItem : defenceGraduateWorkListSource )
      {
        if( dateFilterList != null )
        {
          for( Date dateItem : dateFilterList )
          {
            if( checkDate( defenceGraduateWorkItem.getActualDate(), dateItem ) )
            {
              objectList.add( defenceGraduateWorkItem );
              break;
            }
          }
        }
        else
        {
          objectList.add( defenceGraduateWorkItem );
        }
      }
    }
    fireTableDataChanged();
  }

  private boolean checkDate( Date actualDate, Date filterDate )
  {
    if( actualDate != null && filterDate != null )
    {
      Calendar actualCalendar = Calendar.getInstance();
      actualCalendar.setTime( actualDate );
      Calendar filterCalendar = Calendar.getInstance();
      filterCalendar.setTime( filterDate );
      return actualCalendar.get( Calendar.YEAR ) == filterCalendar.get( Calendar.YEAR )
        && actualCalendar.get( Calendar.MONTH ) == filterCalendar.get( Calendar.MONTH )
        && actualCalendar.get( Calendar.DAY_OF_MONTH ) == filterCalendar.get( Calendar.DAY_OF_MONTH );
    }
    return false;
  }

  @Override
  public Object getValueAt( int rowIndex, int columnIndex )
  {
    if( rowIndex < objectList.size() )
    {
      DefenceGraduateWork defenceGraduateWork = objectList.get( rowIndex );
      GraduateWork graduateWork = null;
      if( defenceGraduateWork.getGraduateWorkId() != null )
      {
        graduateWork = GraduateWorkService.getGraduateWork( defenceGraduateWork.getGraduateWorkId() );
      }
      switch( columnIndex )
      {
	      case 0:
	    	  return rowIndex+1;
        case 1:
          if( graduateWork != null )
          {
            Student student = StudentsService.getStudent( graduateWork.getStudentId() );
            return student != null ? student.getShortName() : null;
          }
          else
          {
            return null;
          }
        case 2:
          return DateUtils.dateToString( defenceGraduateWork.getActualDate() );
        case 3:
          return graduateWork != null ? graduateWork.getTitle() : null;
        case 4:
          if( graduateWork != null )
          {
            Teacher teacher = TeachersService.getTeacher( graduateWork.getManagerId() );
            return teacher != null ? teacher.getShortName() : null;
          }
          else
          {
            return null;
          }
        case ID_COLUMN:
          return defenceGraduateWork.getId();
      }
    }
    return null;
  }

  public void updateData( List<Date> dateFilterList )
  {
    this.dateFilterList = dateFilterList;
    updateData();
  }
  
  
  @SuppressWarnings("deprecation")
public void updateDataTime(List<String> date){
	  if( objectList == null )
	    {
	      objectList = new ArrayList<DefenceGraduateWork>();
	    }
	    else
	    {
	      objectList.clear();
	    }
	    List<DefenceGraduateWork> defenceGraduateWorkListSource =
	      GraduateWorkService.getDefenceGraduateWorkListOrderByStudent();
	    
	    if( defenceGraduateWorkListSource != null )
	    {
	      for( DefenceGraduateWork defenceGraduateWorkItem : defenceGraduateWorkListSource )
	      {
	        if( date != null )
	        {
	          for( String dateItem : date )
	          {
	        	  Boolean complete = false;
	        	  
	        	  String dt = dateItem.split(" / ")[0];
	        	  String hr = dateItem.split(" / ")[1].split(" - ")[0];
	        	  String hr2 = dateItem.split(" / ")[1].split(" - ")[1];
	        	  Date tempDate = DateUtils.dateFromString(dt);
	        	  
	        	  Gek gek = GekService.getGek();
	        	  List<GekDay> days= gek.getGekDayList();
	        	    for(GekDay day:days){
	        	    	if(checkDate(tempDate, day.getDate())){
	        	    		if(checkDate(defenceGraduateWorkItem.getActualDate(), day.getDate())){
	        	    			if(defenceGraduateWorkItem.getStartTime()!=null||defenceGraduateWorkItem.getEndTime()!=null){
	        	    				Date newDate = new Date(defenceGraduateWorkItem.getActualDate().getYear(),
			        	    				defenceGraduateWorkItem.getActualDate().getMonth(),
			        	    				defenceGraduateWorkItem.getActualDate().getDate(),
			        	      			  Integer.parseInt(defenceGraduateWorkItem.getStartTime().split(":")[0]),
			        	      			  Integer.parseInt(defenceGraduateWorkItem.getStartTime().split(":")[1]));
			        	    		
			        	    		Date start = new Date(day.getDate().getYear(),day.getDate().getMonth(),day.getDate().getDate(),
			        	    				Integer.parseInt(hr.split(":")[0]),
			        	    				Integer.parseInt(hr.split(":")[1]));
			        	    		
			        	    		Date end = new Date(day.getDate().getYear(),day.getDate().getMonth(),day.getDate().getDate(),
			        	    				Integer.parseInt(hr2.split(":")[0]),
			        	    				Integer.parseInt(hr2.split(":")[1]));
			        	    		
			        	    		if(newDate.getTime()<end.getTime()&&newDate.getTime()>=start.getTime()){
			        	    			 objectList.add( defenceGraduateWorkItem );
				        	              complete = true;
				        	              break;
			        	    		}
		//	        	    		if( checkDateMy( defenceGraduateWorkItem.getActualDate(), tempDate, day.getStartTime(), hr ) )
		//	        	            {
		//	        	              objectList.add( defenceGraduateWorkItem );
		//	        	              complete = true;
		//	        	              break;
		//	        	            }
	        	    			}
		        	    		
		        	    	}
	        	    	}
			        	    	
	        	    }
	        	    if(complete){
	        	    	break;
	        	    }
	          }
	        }
	        else
	        {
	          objectList.add( defenceGraduateWorkItem );
	        }
	      }
	    }
	    fireTableDataChanged();
  }
  
  private boolean checkDateMy( Date actualDate, Date filterDate, String actualHr, String filterHr )
  {
    if( actualDate != null && filterDate != null )
    {
      Calendar actualCalendar = Calendar.getInstance();
      actualCalendar.setTime( actualDate );
      Calendar filterCalendar = Calendar.getInstance();
      filterCalendar.setTime( filterDate );
      
      return actualCalendar.get( Calendar.YEAR ) == filterCalendar.get( Calendar.YEAR )
        && actualCalendar.get( Calendar.MONTH ) == filterCalendar.get( Calendar.MONTH )
        && actualCalendar.get( Calendar.DAY_OF_MONTH ) == filterCalendar.get( Calendar.DAY_OF_MONTH )
        && actualHr.equals(filterHr);
    }
    return false;
  }

  @Override
  public String[] getHeaders()
  {
    return HEADERS;
  }
}