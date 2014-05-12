package org.iit.dr.view.action;

import org.iit.dr.utils.MemoryManager;
import org.iit.dr.view.MainWindow;
import org.iit.dr.view.component.JInternalFrameExt;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * OpenFrameAction.
 * 
 * @author Yuriy Karpovich
 */
public class OpenFrameAction extends CustomAction
{

  private String name;

  private MainWindow mainWindow;

  private JInternalFrameExt<Object> jInternalFrameExt;

  private Class clazz;

  public OpenFrameAction( String name, MainWindow mainWindow, JInternalFrameExt<Object> jInternalFrameExt )
  {
    this.name = name;
    this.mainWindow = mainWindow;
    this.jInternalFrameExt = jInternalFrameExt;

    putValue( NAME, name );
  }

  public OpenFrameAction( String name, MainWindow mainWindow, Class clazz )
  {
    this.name = name;
    this.mainWindow = mainWindow;
    this.clazz = clazz;

    putValue( NAME, name );
  }

  @SuppressWarnings( "unchecked" )
  public void actionPerformed( ActionEvent e )
  {

    if( jInternalFrameExt == null && clazz != null )
    {

      jInternalFrameExt = ( JInternalFrameExt<Object> ) MemoryManager.getObject( clazz );

    }

    if( jInternalFrameExt != null )
    {

      MainWindow.activeComponent = jInternalFrameExt;
      jInternalFrameExt.setModal( true );
      jInternalFrameExt.showFrame( e.getSource(), e );
    }
  }
}
