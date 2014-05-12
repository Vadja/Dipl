package org.iit.dr.services;

import java.util.ArrayList;
import java.util.List;

import org.iit.dr.data_model.Company;
import org.iit.dr.data_model.DistributionPlace;
import org.iit.dr.data_model.role.Student;

/**
 * 
 * @author Uladzimir_Babkou
 * 
 */
public class DistributionService extends CommonService
{
  public static List<DistributionPlace> getDistributionPlacesList()
  {
    return dataConnector.getDistributionPlaceList();
  }

  public static DistributionPlace getDistributionPlaceByCompanyId( String companyId )
  {
    for( DistributionPlace place : getDistributionPlacesList() )
    {
      if( place.getCompanyId().equals( companyId ) )
      {
        return place;
      }
    }
    return null;
  }

  public static List<Student> getRequestedStudentsByCompanyId( String companyId )
  {
    List<Student> studList = new ArrayList<Student>();
    DistributionPlace place = getDistributionPlaceByCompanyId( companyId );
    for( String studentId : place.getRequestedStudentsIdList() )
    {
      Student stud = StudentsService.getStudent( studentId );
      if( stud != null )
      {
        studList.add( stud );
      }
    }
    return studList;
  }

  public static List<Student> getDistributedStudentsByCompanyId( String companyId )
  {
    List<Student> studList = new ArrayList<Student>();
    DistributionPlace place = getDistributionPlaceByCompanyId( companyId );
    for( String studentId : place.getDistributedStudentsIdList() )
    {
      Student stud = StudentsService.getStudent( studentId );
      if( stud != null )
      {
        studList.add( stud );
      }
    }
    return studList;
  }

  public static List<Student> getDistributedStudents()
  {
    List<Student> studList = new ArrayList<Student>();
    List<DistributionPlace> placesList = getDistributionPlacesList();
    for( DistributionPlace place : placesList )
    {
      for( String studentId : place.getDistributedStudentsIdList() )
      {
        Student stud = StudentsService.getStudent( studentId );
        if( stud != null )
        {
          studList.add( stud );
        }
      }
    }
    return studList;
  }

  public static List<Company> getCompaniesByRequestedStudent( String studentId )
  {
    List<Company> companiesList = new ArrayList<Company>();
    for( DistributionPlace place : getDistributionPlacesList() )
    {
      if( place.getRequestedStudentsIdList().contains( studentId ) )
      {
        Company company = CompaniesService.getCompany( place.getCompanyId() );
        if( company != null )
        {
          companiesList.add( company );
        }
      }
    }
    return companiesList;
  }

  public static DistributionPlace getDistributionPlaceByDistributedStudent( String studentId )
  {
    for( DistributionPlace place : getDistributionPlacesList() )
    {
      if( place.getDistributedStudentsIdList().contains( studentId ) )
      {
        return place;
      }
    }
    return null;
  }

  public static Company getCompanyByDistributedStudent( String studentId )
  {
    DistributionPlace place = getDistributionPlaceByDistributedStudent( studentId );
    if( place != null )
    {
      return CompaniesService.getCompany( place.getCompanyId() );
    }
    return null;
  }

  public static void clearDistributedPlaceForStudent( String studentId )
  {
    DistributionPlace place = getDistributionPlaceByDistributedStudent( studentId );
    if( place != null )
    {
      place.getDistributedStudentsIdList().remove( studentId );
    }
  }

  public static void setDistributionPlaceForStudent( String studentId, String companyShortName )
  {
    Company company = CompaniesService.getCompanyByShortName( companyShortName );
    DistributionPlace distributionPlace = getDistributionPlaceByCompanyId( company.getId() );
    if( distributionPlace == null )
    {
      distributionPlace = new DistributionPlace( company );
      getDistributionPlacesList().add( distributionPlace );
    }
    if( !distributionPlace.getDistributedStudentsIdList().contains( studentId ) )
    {
      distributionPlace.getDistributedStudentsIdList().add( studentId );
    }
  }

  public static void setRequestsForStudent( String studentId, List<String> companiesShortNamesList )
  {
    for( DistributionPlace distributionPlace : getDistributionPlacesList() )
    {
      if( distributionPlace.getRequestedStudentsIdList().contains( studentId ) )
      {
        if( !companiesShortNamesList.contains( CompaniesService.getCompany( distributionPlace.getCompanyId() )
          .getShortName() ) )
        {
          distributionPlace.getRequestedStudentsIdList().remove( studentId );
        }
      }
    }
    for( String companyShortName : companiesShortNamesList )
    {
      Company company = CompaniesService.getCompanyByShortName( companyShortName );
      DistributionPlace distributionPlace = getDistributionPlaceByCompanyId( company.getId() );
      if( distributionPlace == null )
      {
        distributionPlace = new DistributionPlace( company );
        getDistributionPlacesList().add( distributionPlace );
      }
      if( !distributionPlace.getRequestedStudentsIdList().contains( studentId ) )
      {
        distributionPlace.getRequestedStudentsIdList().add( studentId );
      }
    }
  }

  public static void addDistrbutionPlace( DistributionPlace distributionPlace )
  {
    if( !getDistributionPlacesList().contains( distributionPlace ) )
    {
      getDistributionPlacesList().add( distributionPlace );
    }
  }

  public static void addDistrbutionPlaces( String[] companiesIds )
  {
    if( companiesIds != null )
    {
      for( String id : companiesIds )
      {
        addDistrbutionPlace( new DistributionPlace( id ) );
      }
    }
  }

  public static void removeDistrbutionPlace( String companyId )
  {
    if( companyId != null )
    {
      getDistributionPlacesList().remove( new DistributionPlace( companyId ) );
    }
  }

  public static void removeDistrbutionPlaces( String[] companyIds )
  {
    if( companyIds != null )
    {
      for( String id : companyIds )
        removeDistrbutionPlace( id );
    }
  }
}
