package org.iit.dr.view.action;

import java.awt.event.ActionEvent;

import org.iit.dr.utils.FileUtils;
import org.iit.dr.utils.FolderZiper;

public class BackupDataAction extends CustomAction
{

  public BackupDataAction()
  {
    putValue( NAME, "Выполнить копирование данных" );
  }

  public void actionPerformed( ActionEvent e )
  {
    FolderZiper.zipFolder( FileUtils.getDataPath(), FileUtils.getArchivePath() + "data_" + System.currentTimeMillis()
      + ".zip" );

  }

}
