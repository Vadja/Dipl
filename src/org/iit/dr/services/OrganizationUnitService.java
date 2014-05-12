package org.iit.dr.services;

import java.util.ArrayList;
import java.util.List;

import org.iit.dr.config.ApplicationConfig;
import org.iit.dr.data_model.unit.Course;
import org.iit.dr.data_model.unit.OrganizationUnit;
import org.iit.dr.data_model.unit.UnitType;

public class OrganizationUnitService extends CommonService
{
  public static List<OrganizationUnit> getOrganizationUnitList()
  {
    return dataConnector.getOrganizationUnitList();
  }

  public static boolean removeOrganizationUnit( String organizationUnitId )
  {
    if( getSubOUListByParent( organizationUnitId ) != null && !getSubOUListByParent( organizationUnitId ).isEmpty() )
    {
      dataConnector.getOrganizationUnitList().removeAll( getSubOUListByParent( organizationUnitId ) );
      return true;
    }
    return false;
  }

  public static OrganizationUnit getOrganizationUnit( String organizationUnitId )
  {
    if( organizationUnitId == null || organizationUnitId.length() == 0 )
    {
      return null;
    }
    for( OrganizationUnit organizationUnitItem : dataConnector.getOrganizationUnitList() )
    {
      if( organizationUnitItem.getId().equals( organizationUnitId ) )
      {
        return organizationUnitItem;
      }
    }
    return null;
  }

  public static List<OrganizationUnit> getSubOUListByParent( String ouID )
  {
    List<OrganizationUnit> list = new ArrayList<OrganizationUnit>();
    list.add( getOrganizationUnit( ouID ) );
    for( OrganizationUnit item : getOrganizationUnitList() )
    {
      if( item.getParentOuId() != null && item.getParentOuId().equals( ouID ) )
      {
        list.addAll( getSubOUListByParent( item.getId() ) );
      }
    }
    return list;
  }

  public static List<String> getSubOrganizationUnitListByParent( String organizationUnitParentId )
  {
    List<String> organizationUnitIdList = new ArrayList<String>();
    organizationUnitIdList.add( organizationUnitParentId );
    for( OrganizationUnit organizationUnitItem : dataConnector.getOrganizationUnitList() )
    {
      if( organizationUnitItem.getParentOuId() != null
        && organizationUnitItem.getParentOuId().equals( organizationUnitParentId ) )
      {
        organizationUnitIdList.addAll( getSubOrganizationUnitListByParent( organizationUnitItem.getId() ) );
      }
    }
    return organizationUnitIdList;
  }

  public static List<OrganizationUnit> getOrganizationUnitListByRoot( String organizationUnitRootId )
  {
    List<OrganizationUnit> organizationUnitList = new ArrayList<OrganizationUnit>();
    for( OrganizationUnit organizationUnitItem : dataConnector.getOrganizationUnitList() )
    {
      if( organizationUnitItem.getParentOuId() != null
        && organizationUnitItem.getParentOuId().equals( organizationUnitRootId ) )
      {
        organizationUnitList.add( organizationUnitItem );
      }
      else if( organizationUnitRootId == null && organizationUnitItem.getParentOuId() == null )
      {
        organizationUnitList.add( organizationUnitItem );
      }
    }
    return organizationUnitList;
  }

  public static String getOrganizationUnitByName( String name, UnitType type )
  {
    if( name == null )
      return null;
    for( OrganizationUnit organizationUnitItem : getOrganizationUnitByType( type ) )
    {

      if( organizationUnitItem.getName().compareTo( name ) == 0 )
      {
        return organizationUnitItem.getId();
      }
    }
    return null;
  }

  public static List<OrganizationUnit> getOrganizationUnitByType( UnitType type )
  {
    String facultyId = null;
    try
    {
      facultyId = ApplicationConfig.getInstance().getProperty( "faculty" );
    }
    catch( Exception e )
    {

    }
    List<OrganizationUnit> listForSearch;
    if( facultyId == null )
    {
      listForSearch = dataConnector.getOrganizationUnitList();
    }
    else
    {
      listForSearch = getOrganizationUnitListByRoot( facultyId );
    }
    List<OrganizationUnit> list = new ArrayList<OrganizationUnit>();
    for( OrganizationUnit organizationUnitItem : listForSearch )
    {
      if( organizationUnitItem.getType() == type )
      {
        list.add( organizationUnitItem );
      }
    }
    return list;
  }

  public static String getCourseId( Course course )
  {
    for( OrganizationUnit item : getOrganizationUnitByType( UnitType.STREAM ) )
    {
      if( item.getName().compareTo( course.toString() ) == 0 )
      {
        return item.getId();
      }
    }
    return null;
  }

  public static List<OrganizationUnit> getListParentOU( String ouID )
  {
    OrganizationUnit ou = getOrganizationUnit( ouID );
    List<OrganizationUnit> organizationUnitIdList = new ArrayList<OrganizationUnit>();
    if( ou != null )
    {
      organizationUnitIdList.add( ou );
      for( OrganizationUnit organizationUnitItem : dataConnector.getOrganizationUnitList() )
      {
        if( organizationUnitItem.getId() != null && organizationUnitItem.getId().equals( ou.getParentOuId() ) )
        {
          organizationUnitIdList.addAll( getListParentOU( organizationUnitItem.getId() ) );
        }
      }
    }
    return organizationUnitIdList;
  }

  public static OrganizationUnit getRootOrganizationUnit( OrganizationUnit ou )
  {
    if( ou.getParentOuId() != null )
    {
      return getRootOrganizationUnit( OrganizationUnitService.getOrganizationUnit( ou.getParentOuId() ) );
    }

    return ou;

  }
}
