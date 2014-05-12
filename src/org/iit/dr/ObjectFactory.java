package org.iit.dr;

import javax.xml.bind.annotation.XmlRegistry;

import org.iit.dr.data_model.Book;
import org.iit.dr.data_model.Conference;
import org.iit.dr.data_model.DefenceGraduateWork;
import org.iit.dr.data_model.Gek;
import org.iit.dr.data_model.GraduateWork;
import org.iit.dr.data_model.Member;
import org.iit.dr.data_model.PracticeWork;
import org.iit.dr.data_model.Rate;
import org.iit.dr.data_model.role.ExternalSecondJobStaffer;
import org.iit.dr.data_model.role.InternalSecondJobStaffer;
import org.iit.dr.data_model.role.PartTimeStaffer;
import org.iit.dr.data_model.role.Person;
import org.iit.dr.data_model.role.Staffer;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.data_model.unit.OrganizationUnit;
import org.iit.dr.data_storage.object_collections.BookCollection;
import org.iit.dr.data_storage.object_collections.CompanyCollection;
import org.iit.dr.data_storage.object_collections.ConferenceCollection;
import org.iit.dr.data_storage.object_collections.DefenceGraduateWorkCollection;
import org.iit.dr.data_storage.object_collections.DistributionPlaceCollection;
import org.iit.dr.data_storage.object_collections.GraduateWorkCollection;
import org.iit.dr.data_storage.object_collections.GraduatingPracticeConsultantCollection;
import org.iit.dr.data_storage.object_collections.GraduatingPracticePlaceCollection;
import org.iit.dr.data_storage.object_collections.OrganizationUnitCollection;
import org.iit.dr.data_storage.object_collections.ProductionPracticeConsultantCollection;
import org.iit.dr.data_storage.object_collections.ProductionPracticePlaceCollection;
import org.iit.dr.data_storage.object_collections.RateCollection;
import org.iit.dr.data_storage.object_collections.StudentCollection;
import org.iit.dr.data_storage.object_collections.TeacherCollection;

/**
 * ObjectFactory.
 * 
 * @author Yuriy Karpovich
 */
@XmlRegistry
public class ObjectFactory
{

  public ObjectFactory()
  {
  }

  public Conference createConference()
  {
    return new Conference();
  }

  public ConferenceCollection createConferenceCollection()
  {
    return new ConferenceCollection();
  }

  public Member createMember()
  {
    return new Member();
  }

  public Person createPerson()
  {
    return new Person();
  }

  public Book createBook()
  {
    return new Book();
  }

  public BookCollection createBookCollection()
  {
    return new BookCollection();
  }

  public Student createStudent()
  {
    return new Student();
  }

  public StudentCollection createStudentCollection()
  {
    return new StudentCollection();
  }

  public OrganizationUnitCollection createOrganizationUnitCollection()
  {
    return new OrganizationUnitCollection();
  }

  public TeacherCollection createTeacherCollection()
  {
    return new TeacherCollection();
  }

  public OrganizationUnit createOrganizationUnit()
  {
    return new OrganizationUnit();
  }

  public Staffer createStaffer()
  {
    return new Staffer();
  }

  public ExternalSecondJobStaffer createExternalSecondJobStaffer()
  {
    return new ExternalSecondJobStaffer();
  }

  public InternalSecondJobStaffer createInternalSecondJobStaffer()
  {
    return new InternalSecondJobStaffer();
  }

  public PartTimeStaffer createPartTimeStaffer()
  {
    return new PartTimeStaffer();
  }

  public RateCollection createRateCollection()
  {
    return new RateCollection();
  }

  public Rate createRate()
  {
    return new Rate();
  }

  public GraduateWork createGraduateWork()
  {
    return new GraduateWork();
  }

  public GraduateWorkCollection createGraduateWorkCollection()
  {
    return new GraduateWorkCollection();
  }

  public PracticeWork createPracticeWork()
  {
    return new PracticeWork();
  }

  public ProductionPracticePlaceCollection createPracticePlaceCollection()
  {
    return new ProductionPracticePlaceCollection();
  }

  public GraduatingPracticePlaceCollection createGraduatingPracticePlaceCollection()
  {
    return new GraduatingPracticePlaceCollection();
  }

  public DefenceGraduateWork createDefenceGraduateWork()
  {
    return new DefenceGraduateWork();
  }

  public DefenceGraduateWorkCollection createDefenceGraduateWorkCollection()
  {
    return new DefenceGraduateWorkCollection();
  }

  public Gek createGek()
  {
    return new Gek();
  }

  public CompanyCollection createCompanyCollection()
  {
    return new CompanyCollection();
  }

  public ProductionPracticeConsultantCollection createProductionPracticeConsultantCollection()
  {
    return new ProductionPracticeConsultantCollection();
  }

  public GraduatingPracticeConsultantCollection createGraduatingPracticeConsultantCollection()
  {
    return new GraduatingPracticeConsultantCollection();
  }

  public DistributionPlaceCollection createDistributionPlaceCollection()
  {
    return new DistributionPlaceCollection();
  }

}