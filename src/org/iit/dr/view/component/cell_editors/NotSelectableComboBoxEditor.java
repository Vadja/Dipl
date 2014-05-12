package org.iit.dr.view.component.cell_editors;

import java.awt.Component;
import java.util.List;

import javax.swing.JTable;

import org.iit.dr.utils.StringUtils;
import org.iit.dr.view.component.combo_box_ext.AlternativeSelectAction;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class NotSelectableComboBoxEditor extends ComboBoxTableCellEditor
{
  private static final long serialVersionUID = 4941337928270417117L;

  public NotSelectableComboBoxEditor()
  {
    super();
  }

  public NotSelectableComboBoxEditor( List<Object> items )
  {
    super( items );
  }

  public NotSelectableComboBoxEditor( List<Object> items, Object alternativeItem, AlternativeSelectAction selectAction )
  {
    super( items, alternativeItem, selectAction );
  }

  @Override
  public Component getTableCellEditorComponent( JTable table, Object value, boolean isSelected, int row, int column )
  {
    if( value != null )
    {
      comboBox.setItems( StringUtils.asList( ( String ) value, ", " ) );
      comboBox.setSelectedIndex( 0 );
    }
    else
    {
      comboBox.setItems( null );
    }
    return comboBox;
  }
}
