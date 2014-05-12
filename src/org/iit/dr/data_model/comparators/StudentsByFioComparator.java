package org.iit.dr.data_model.comparators;

import java.util.Comparator;

import org.iit.dr.data_model.role.Student;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class StudentsByFioComparator implements Comparator<Student>
{
  public int compare( Student o1, Student o2 )
  {
    return o1.getFullName().compareTo( o2.getFullName() );
  }
}