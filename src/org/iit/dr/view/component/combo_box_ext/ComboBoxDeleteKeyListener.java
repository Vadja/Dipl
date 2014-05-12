package org.iit.dr.view.component.combo_box_ext;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 
 * @author Uladzimir_Babkou
 * 
 */
public class ComboBoxDeleteKeyListener extends KeyAdapter
{
  protected ComboBoxExt comboBox;

  public ComboBoxDeleteKeyListener( ComboBoxExt comboBox )
  {
    this.comboBox = comboBox;
  }

  @Override
  public void keyPressed( KeyEvent e )
  {
    if( e.getKeyCode() == KeyEvent.VK_DELETE && !comboBox.isAlternativeItemSelected()
      && !comboBox.isEmptyItemSelected() )
    {
      comboBox.removeItem( comboBox.getSelectedItem() );
    }
  }
}
