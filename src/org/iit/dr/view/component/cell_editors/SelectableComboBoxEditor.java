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
public class SelectableComboBoxEditor extends ComboBoxTableCellEditor
{
  private static final long serialVersionUID = -2280575535338922829L;

  public SelectableComboBoxEditor()
  {
    super();
  }

  public SelectableComboBoxEditor( List<Object> items )
  {
    super( items );
  }

  public SelectableComboBoxEditor( List<Object> items, Object alternativeItem,
    AlternativeSelectAction selectAction )
  {
    super( items, alternativeItem, selectAction );
  }

  @Override
  public Component getTableCellEditorComponent( JTable table, Object value, boolean isSelected, int row, int column )
  {
    value = table.getModel().getValueAt( row, column - 1 );
    if( value != null )
    {
      comboBox.setItems( StringUtils.asList( ( String ) value, ", " ) );
      comboBox.setSelectedIndex( 0 );
      System.out.println( comboBox.getItems() );
    }
    else
    {
      comboBox.setItems( null );
    }
    return comboBox;
  }

  @Override
  public Object getCellEditorValue()
  {
    return comboBox.getSelectedItem();
  }
}
