package org.iit.dr.view.component.combo_box_ext;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * @author Uladzimir_Babkou
 * 
 */
public class ComboBoxAlternativeAddListener implements ActionListener
{
  protected Object alternativeItem;

  protected AlternativeSelectAction selectAction;

  protected ComboBoxExt comboBox;

  public ComboBoxAlternativeAddListener( ComboBoxExt comboBox, Object alternativeItem,
    AlternativeSelectAction selectAction )
  {
    this.comboBox = comboBox;
    this.alternativeItem = alternativeItem;
    this.selectAction = selectAction;
  }

  public void actionPerformed( ActionEvent e )
  {
    if( comboBox.getSelectedItem() != null && comboBox.getSelectedItem().equals( alternativeItem ) )
    {
      Object[] items = selectAction.selectAlternatives( comboBox );
      if( items != null )
      {
        for( Object item : items )
        {
          comboBox.addItemIfNotExistNotLast( item );
        }
      }
      comboBox.setSelectedIndex( 0 );
    }
  }
}
