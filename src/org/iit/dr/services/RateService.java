package org.iit.dr.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.iit.dr.data_model.Position;
import org.iit.dr.data_model.Rate;
import org.iit.dr.data_model.RateType;

public class RateService extends CommonService {
	public static List<Rate> getRateList() {
		return dataConnector.getRateList();
	}

	public static Rate getRate(String rateId) {
		if (rateId == null || rateId.length() == 0) {
			return null;
		}
		for (Rate rateItem : dataConnector.getRateList()) {
			if (rateItem.getId().equals(rateId)) {
				return rateItem;
			}
		}
		return null;
	}

	public static List<Rate> getRateByTeacher(String teacherId) {
		List<Rate> rateList = new ArrayList<Rate>();
		if (teacherId == null || teacherId.length() == 0) {
			return null;
		}
		for (Rate rateItem : dataConnector.getRateList()) {
			if (rateItem.getTeacher() != null
					&& rateItem.getTeacher().equals(teacherId)) {
				rateList.add(rateItem);
			}
		}
		return rateList;
	}

	public static List<Rate> getRateByTeacherRateType(String teacherId,
			RateType rateType) {
		List<Rate> rateList = new ArrayList<Rate>();
		if (teacherId == null || teacherId.length() == 0) {
			return null;
		}
		for (Rate rateItem : dataConnector.getRateList()) {
			if (rateItem.getTeacher().equals(teacherId)
					&& rateItem.getRateType().equals(rateType)) {
				rateList.add(rateItem);
			}
		}
		return rateList;
	}

	public static List<Rate> getRateByDateRateType(Date date, RateType rateType) {
		List<Rate> rateList = new ArrayList<Rate>();
		for (Rate rateItem : getRateByDate(date)) {
			if (rateType.equals(rateItem.getRateType())) {
				rateList.add(rateItem);
			}
		}
		return rateList;
	}

	public static List<Rate> getRateByDate(Date date) {
		List<Rate> rateList = new ArrayList<Rate>();
		Date start;
		Date end;
		for (Rate rateItem : dataConnector.getRateList()) {
			start = rateItem.getRange().getStart();
			end = rateItem.getRange().getEnd();
			if (start != null && end != null && date != null) {
				if (date.after(start) && date.before(end)) {
					rateList.add(rateItem);
				}
			}
		}
		return rateList;
	}

	public static boolean removeRate(String rateId) {
		for (Rate rateItem : dataConnector.getRateList()) {
			if (rateItem.getId().equals(rateId)) {
				dataConnector.getRateList().remove(rateItem);
				return true;
			}
		}
		return false;
	}

	public static void removeRateByTeacher(String teacherId) {
		for (Iterator iterator = dataConnector.getRateList().iterator(); iterator
				.hasNext();) {
			Rate rateItem = (Rate) iterator.next();
			if (rateItem.getTeacher().equals(teacherId)) {
			}
			iterator.remove();
		}
	}

	public static List<Rate> getRateByPositionRateType(RateType rateType,
			Position position, Date date) {
		List<Rate> rateList = new ArrayList<Rate>();
		for (Rate rateItem : getRateByDate(date)) {
			if (rateType.equals(rateItem.getRateType())) {
				
				Position positionName = TeachersService.getTeacher(
						rateItem.getTeacher()).getPosition();
				
				if (position.equals(positionName))
				
					rateList.add(rateItem);
			}
		}
		return rateList;
	}
	
	public static double calculateRate(RateType rateType,
			Position position, Date date){
		double result = 0;
		for (Rate rateItem: getRateByPositionRateType(rateType, position, date)){
			result+=rateItem.getAmount();
		}
		return result;
	}
}
