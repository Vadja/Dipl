package org.iit.dr.data_model.comparators;

import java.util.Comparator;

import org.iit.dr.data_model.Rate;
import org.iit.dr.services.TeachersService;

public class RateByFioTeacherComparator implements Comparator <Rate>{
	public int compare(Rate o1, Rate o2) {
		return TeachersService
				.getTeacher(o1.getTeacher())
				.getFullName()
				.compareTo(
						TeachersService.getTeacher(o2.getTeacher())
								.getFullName());
	}
}
