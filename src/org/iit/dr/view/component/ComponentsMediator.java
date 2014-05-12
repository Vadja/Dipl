package org.iit.dr.view.component;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Uladzimir_Babkou
 * 
 */
public class ComponentsMediator
{
  private List<Updatable> componentsList;

  public ComponentsMediator()
  {
    componentsList = new LinkedList<Updatable>();
  }

  public ComponentsMediator( List<Updatable> components )
  {
    componentsList = components;
  }

  public void addComponent( Updatable component )
  {
    componentsList.add( component );
  }

  public void updateOtherComponents( Updatable current )
  {
    for( Updatable component : componentsList )
    {
      if( component != current )
      {
        component.updateData();
      }
    }
  }

  public void updateComponents()
  {
    for( Updatable component : componentsList )
    {
      component.updateData();
    }
  }
}
