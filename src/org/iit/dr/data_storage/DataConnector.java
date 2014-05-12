package org.iit.dr.data_storage;

import java.util.List;

import org.iit.dr.data_model.Book;
import org.iit.dr.data_model.Company;
import org.iit.dr.data_model.Conference;
import org.iit.dr.data_model.DefenceGraduateWork;
import org.iit.dr.data_model.DistributionPlace;
import org.iit.dr.data_model.Gek;
import org.iit.dr.data_model.GraduateWork;
import org.iit.dr.data_model.PracticeConsultant;
import org.iit.dr.data_model.PracticePlace;
import org.iit.dr.data_model.Rate;
import org.iit.dr.data_model.StaffRate;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.data_model.unit.OrganizationUnit;

/**
 * DataConnector.
 * 
 * @author Yuriy Karpovich
 */
public interface DataConnector
{
  // void loadData();

  void saveDate();

  List<Conference> getConferenceList();

  List<Book> getBookList();

  List<Student> getStudentList();

  List<OrganizationUnit> getOrganizationUnitList();

  List<Teacher> getTeacherList();

  List<Rate> getRateList();
  
  List<StaffRate> getStaffRateList();

  List<GraduateWork> getGraduateWorkList();

  List<PracticePlace> getPracticePlaceList();

  List<PracticePlace> getProductionPracticePlaceList();

  List<DistributionPlace> getDistributionPlaceList();

  List<DefenceGraduateWork> getDefenceGraduateWorkList();

  Gek getGek();

  List<Company> getCompaniesList();

  List<PracticeConsultant> getProductionPracticeConsultantList();

  List<PracticeConsultant> getGraduatePracticeConsultantList();

}
