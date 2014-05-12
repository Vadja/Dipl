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
 * DataStorage.
 * 
 * @author Yuriy Karpovich
 */
public class DataStorage implements DataConnector
{
  private final DataConnector dataConnector;

  private static DataStorage instance;

  private DataStorage()
  {
    dataConnector = new XmlConnector();
  }

  public static DataStorage getInstance()
  {
    if( instance == null )
    {
      synchronized( DataStorage.class )
      {
        if( instance == null )
        {
          instance = new DataStorage();
        }
      }
    }

    return instance;
  }

  // public void loadData()
  // {
  // dataConnector.loadData();
  // }

  public void saveDate()
  {
    dataConnector.saveDate();
  }

  public List<Book> getBookList()
  {
    return dataConnector.getBookList();
  }

  public List<Conference> getConferenceList()
  {
    return dataConnector.getConferenceList();
  }

  public List<Student> getStudentList()
  {
    return dataConnector.getStudentList();
  }

  public List<OrganizationUnit> getOrganizationUnitList()
  {
    return dataConnector.getOrganizationUnitList();
  }

  public List<Teacher> getTeacherList()
  {
    return dataConnector.getTeacherList();
  }

  public List<Rate> getRateList()
  {
    return dataConnector.getRateList();
  }
  
  public List<StaffRate> getStaffRateList() {
  	return dataConnector.getStaffRateList();
  }

  public List<GraduateWork> getGraduateWorkList()
  {
    return dataConnector.getGraduateWorkList();
  }

  public List<PracticePlace> getPracticePlaceList()
  {
    return dataConnector.getPracticePlaceList();
  }

  public List<PracticePlace> getProductionPracticePlaceList()
  {
    return dataConnector.getProductionPracticePlaceList();
  }

  public List<DefenceGraduateWork> getDefenceGraduateWorkList()
  {
    return dataConnector.getDefenceGraduateWorkList();
  }

  public Gek getGek()
  {
    return dataConnector.getGek();
  }

  public List<Company> getCompaniesList()
  {
    return dataConnector.getCompaniesList();
  }

  public List<PracticeConsultant> getProductionPracticeConsultantList()
  {
    return dataConnector.getProductionPracticeConsultantList();
  }

  public List<PracticeConsultant> getGraduatePracticeConsultantList()
  {
    return dataConnector.getGraduatePracticeConsultantList();
  }

  public List<DistributionPlace> getDistributionPlaceList()
  {
    return dataConnector.getDistributionPlaceList();
  }
}
