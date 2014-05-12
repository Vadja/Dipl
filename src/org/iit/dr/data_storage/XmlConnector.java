package org.iit.dr.data_storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

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
import org.iit.dr.data_storage.object_collections.StaffRateCollection;
import org.iit.dr.data_storage.object_collections.StudentCollection;
import org.iit.dr.data_storage.object_collections.TeacherCollection;
import org.iit.dr.utils.FileUtils;
import org.iit.dr.utils.UUIDUtils;

/**
 * XmlConnector.
 * 
 * @author Yuriy Karpovich
 */
public class XmlConnector implements DataConnector
{
  private final CollectionLoader<Book> bookLoader = new CollectionLoader<Book>( new BookCollection() );

  private final CollectionLoader<Conference> conferenceLoader = new CollectionLoader<Conference>(
    new ConferenceCollection() );

  private final CollectionLoader<Student> studentLoader = new CollectionLoader<Student>( new StudentCollection() );

  private final CollectionLoader<Teacher> teacherLoader = new CollectionLoader<Teacher>( new TeacherCollection() );

  private final CollectionLoader<OrganizationUnit> organizationUnitLoader = new CollectionLoader<OrganizationUnit>(
    new OrganizationUnitCollection() );

  private final CollectionLoader<Rate> rateLoader = new CollectionLoader<Rate>( new RateCollection() );
  
  private final CollectionLoader<StaffRate> staffRateLoader = new CollectionLoader<StaffRate>( new StaffRateCollection() );

  private final CollectionLoader<DefenceGraduateWork> defenceGraduateWorkLoader =
    new CollectionLoader<DefenceGraduateWork>( new DefenceGraduateWorkCollection() );

  private final CollectionLoader<GraduateWork> graduateWorkLoader = new CollectionLoader<GraduateWork>(
    new GraduateWorkCollection() );

  private final CollectionLoader<PracticePlace> graduatingPracticePlaceLoader = new CollectionLoader<PracticePlace>(
    new GraduatingPracticePlaceCollection() );

  private final CollectionLoader<PracticePlace> productionPracticePlaceLoader = new CollectionLoader<PracticePlace>(
    new ProductionPracticePlaceCollection() );

  private final CollectionLoader<DistributionPlace> distributionPlaceLoader = new CollectionLoader<DistributionPlace>(
    new DistributionPlaceCollection() );

  private final CollectionLoader<Company> companyLoader = new CollectionLoader<Company>( new CompanyCollection() );

  private final CollectionLoader<PracticeConsultant> productionPracticeConsultantLoader =
    new CollectionLoader<PracticeConsultant>( new ProductionPracticeConsultantCollection() );

  private final CollectionLoader<PracticeConsultant> graduatingPracticeConsultantLoader =
    new CollectionLoader<PracticeConsultant>( new GraduatingPracticeConsultantCollection() );

  Gek gek;

  public void saveDate()
  {
    bookLoader.save();
    conferenceLoader.save();
    studentLoader.save();
    organizationUnitLoader.save();
    teacherLoader.save();
    staffRateLoader.save();
    rateLoader.save();
    graduateWorkLoader.save();
    graduatingPracticePlaceLoader.save();
    productionPracticePlaceLoader.save();
    defenceGraduateWorkLoader.save();
    companyLoader.save();
    if( gek != null )
    {
      saveGek();
    }
    productionPracticeConsultantLoader.save();
    graduatingPracticeConsultantLoader.save();
    distributionPlaceLoader.save();
  }

  public List<Conference> getConferenceList()
  {
    return conferenceLoader.getList();
  }

  public List<Book> getBookList()
  {
    return bookLoader.getList();
  }

  public List<Student> getStudentList()
  {
    return studentLoader.getList();
  }

  public List<OrganizationUnit> getOrganizationUnitList()
  {
    return organizationUnitLoader.getList();
  }

  public List<Teacher> getTeacherList()
  {
    return teacherLoader.getList();
  }

  public List<Rate> getRateList()
  {
    return rateLoader.getList();
  }
  
  public List<StaffRate> getStaffRateList()
  {
    return staffRateLoader.getList();
  }

  public List<GraduateWork> getGraduateWorkList()
  {
    return graduateWorkLoader.getList();
  }

  public List<PracticePlace> getPracticePlaceList()
  {
    return graduatingPracticePlaceLoader.getList();
  }

  public List<PracticePlace> getProductionPracticePlaceList()
  {
    return productionPracticePlaceLoader.getList();
  }

  public List<DistributionPlace> getDistributionPlaceList()
  {
    return distributionPlaceLoader.getList();
  }

  public List<DefenceGraduateWork> getDefenceGraduateWorkList()
  {
    return defenceGraduateWorkLoader.getList();
  }

  public Gek getGek()
  {
    if( gek == null )
    {
      loadGek();
    }
    return gek;
  }

  private boolean loadGek()
  {
    try
    {
      JAXBContext jc = JAXBContext.newInstance( "org.iit.dr" );
      Unmarshaller u = jc.createUnmarshaller();

      File file = FileUtils.getGekData();
      if( file.exists() )
      {

        gek = ( Gek ) u.unmarshal( new FileInputStream( file ) );

        if( gek == null )
        {
          gek = new Gek( UUIDUtils.getUUID() );
        }

      }
      else
      {

        gek = new Gek( UUIDUtils.getUUID() );
        return false;
      }

    }
    catch( Exception e )
    {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  private boolean saveGek()
  {

    try
    {
      JAXBContext jc = JAXBContext.newInstance( "org.iit.dr" );
      Marshaller m = jc.createMarshaller();
      m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
      OutputStream outputStream = new FileOutputStream( FileUtils.getGekData() );
      m.marshal( gek, outputStream );
      return true;
    }
    catch( Exception e )
    {
      return false;
    }
  }

  public List<Company> getCompaniesList()
  {
    return companyLoader.getList();
  }

  public List<PracticeConsultant> getProductionPracticeConsultantList()
  {
    return productionPracticeConsultantLoader.getList();
  }

  public List<PracticeConsultant> getGraduatePracticeConsultantList()
  {
    return graduatingPracticeConsultantLoader.getList();
  }
}
