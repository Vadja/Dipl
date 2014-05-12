package org.iit.dr.view.action;

import java.awt.event.ActionEvent;

import org.iit.dr.data_storage.DataStorage;

public class SaveDataAction extends CustomAction
{

  public SaveDataAction()
  {
    putValue( NAME, "Сохранить данные на диск" );
  }

  public void actionPerformed( ActionEvent arg0 )
  {
    DataStorage.getInstance().saveDate();

  }

}
