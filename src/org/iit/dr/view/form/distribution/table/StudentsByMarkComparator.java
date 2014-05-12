package org.iit.dr.view.form.distribution.table;

import java.util.Comparator;

import org.iit.dr.data_model.role.Student;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class StudentsByMarkComparator implements Comparator<Student>
{
  public int compare( Student o1, Student o2 )
  {
    double mark1 = o1.getAverageMark();
    double mark2 = o2.getAverageMark();
    if( mark1 - mark2 > 0 )
    {
      return -1;
    }
    else if( mark1 - mark2 < 0 )
    {
      return 1;
    }
    return o1.getFullName().compareTo( o2.getFullName() );
  }
}
