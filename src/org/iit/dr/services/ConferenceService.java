package org.iit.dr.services;

import java.util.List;

import org.iit.dr.data_model.Conference;
import org.iit.dr.data_model.Member;

public class ConferenceService extends CommonService
{
  public static List<Conference> getConferenceList()
  {
    return dataConnector.getConferenceList();
  }

  public static Conference getConference( String confId )
  {
    if( confId == null || confId.length() == 0 )
    {
      return null;
    }

    for( Conference confItem : dataConnector.getConferenceList() )
    {
      if( confItem.getId().equals( confId ) )
      {
        return confItem;
      }
    }
    return null;
  }

  public static Conference getConferenceByTitle( String title )
  {

    if( title == null || title.length() == 0 )
    {

      return null;
    }

    for( Conference confItem : dataConnector.getConferenceList() )
    {

      if( confItem.getTitle().equals( title ) )
      {

        return confItem;
      }
    }

    return null;
  }

  public static boolean removeConference( String confId )
  {

    for( Conference confItem : dataConnector.getConferenceList() )
    {

      if( confItem.getId().equals( confId ) )
      {

        dataConnector.getConferenceList().remove( confItem );

        return true;
      }
    }

    return false;
  }

  public static boolean removeConferenceMember( String memId )
  {

    List<Conference> confList = getConferenceList();
    if( ( confList != null ) && ( memId != null ) )
    {
      for( Conference conf : confList )
      {
        List<Member> memList = conf.getMemberList();
        if( memList != null )
        {
          for( Member mem : memList )
          {
            if( mem.getId().equals( memId ) )
            {
              memList.remove( mem );
              return true;
            }
          }
        }
      }

    }
    return false;

  }

}
