package org.iit.dr.services;

import org.iit.dr.data_model.role.Person;
import org.iit.dr.data_storage.DataConnector;
import org.iit.dr.data_storage.DataStorage;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class CommonService
{
  protected static DataConnector dataConnector = DataStorage.getInstance();

  protected static int comparePersons( Person person1, Person person2 )
  {
    if( person1 == null && person2 == null )
    {
      return 0;
    }
    if( person1 != null && person2 != null )
    {
      if( person1.getLastName() != null && person2.getLastName() != null )
      {
        return person1.getLastName().compareTo( person2.getLastName() );
      }
    }
    else if( person1 == null )
    {
      return 1;
    }
    else
    {
      return -1;
    }
    return 0;
  }

}
