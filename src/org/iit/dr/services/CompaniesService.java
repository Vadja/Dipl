package org.iit.dr.services;

import java.util.List;

import org.iit.dr.data_model.Company;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class CompaniesService extends CommonService
{
  public static List<Company> getCompaniesList()
  {
    return dataConnector.getCompaniesList();
  }

  public static Company getCompany( String companyId )
  {
    for( Company companyItem : getCompaniesList() )
    {
      if( companyItem.getId().equals( companyId ) )
      {
        return companyItem;
      }
    }
    return null;
  }

  public static Company getCompanyByName( String companyName )
  {
    for( Company companyItem : getCompaniesList() )
    {
      if( companyItem.getName().equals( companyName ) )
      {
        return companyItem;
      }
    }
    return null;
  }

  public static Company getCompanyByShortName( String companyShortName )
  {
    for( Company companyItem : getCompaniesList() )
    {
      if( companyItem.getShortName().equals( companyShortName ) )
      {
        return companyItem;
      }
    }
    return null;
  }

  public static boolean removeCompany( String companyId )
  {
    for( Company companyItem : getCompaniesList() )
    {
      if( companyItem.getId().equals( companyId ) )
      {
        dataConnector.getCompaniesList().remove( companyItem );
        return true;
      }
    }
    return false;
  }

  public static void addCompany( Company company )
  {
    if( !getCompaniesList().contains( company ) )
    {
      getCompaniesList().add( company );
    }
  }
}
