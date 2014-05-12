package org.iit.dr.view;

import javax.swing.SwingUtilities;

import org.iit.dr.data_storage.DataStorage;
import org.iit.dr.utils.FileUtils;
import org.iit.dr.utils.FolderZiper;
import org.iit.dr.utils.MemoryManager;

/**
 * StartApp.
 * 
 * @author Yuriy Karpovich
 */
public class StartApp
{

  static final String BACKUP = "backup";

  public static void main(String args[])
  {

    if( args != null && args.length > 0 && args[0].equalsIgnoreCase( BACKUP ) )
    {

      FolderZiper.zipFolder( FileUtils.getDataPath(), FileUtils.getArchivePath() + "data_" + System.currentTimeMillis()
        + ".zip" );
    }

    // try {
    //
    // UserManager.loadUserManagerService();
    // } catch (DataIOException e) {
    //
    // log.error("Failed start application.", e);
    // closeApplication(true, false);
    // }
    // loginUser();
    createAndShowUI();
  }

  public static void createAndShowUI()
  {

    SwingUtilities.invokeLater( new Runnable()
    {
      public void run()
      {
        MainWindow window = new MainWindow();
        window.openAndRestore();
        window.setVisible( true );

        MemoryManager.getInstance().init();
      }
    } );
  }

  public static void closeApplication( Boolean isCrashed, Boolean isSaveData )
  {

    if( isSaveData )
    {

      DataStorage.getInstance().saveDate();
    }

    if( isCrashed )
    {

      System.exit( -1 );
    }
    else
    {

      System.exit( 0 );
    }

  }
}
