package org.iit.dr.view.component.cell_editors;

import java.awt.Component;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import org.iit.dr.view.component.combo_box_ext.AlternativeSelectAction;
import org.iit.dr.view.component.combo_box_ext.ComboBoxDeleteKeyListener;
import org.iit.dr.view.component.combo_box_ext.ComboBoxExt;

/**
 * 
 * @author Uladzimir_Babkou
 * 
 */
public class ComboBoxTableCellEditor extends AbstractCellEditor implements TableCellEditor
{
  private static final long serialVersionUID = 913774666573909284L;

  protected ComboBoxExt comboBox;

  protected ComboBoxDeleteKeyListener deleteKeyListener;

  public ComboBoxTableCellEditor()
  {
    comboBox = new ComboBoxExt();
    deleteKeyListener = new ComboBoxDeleteKeyListener( comboBox );
  }

  public ComboBoxTableCellEditor( List<Object> items )
  {
    this();
    comboBox.setItems( items );
  }

  public ComboBoxTableCellEditor( List<Object> items, Object alternativeItem, AlternativeSelectAction selectAction )
  {
    this( items );
    comboBox.setAddAlternativeListener( alternativeItem, selectAction );
  }

  public Component getTableCellEditorComponent( JTable table, Object value, boolean isSelected, int row, int column )
  {
    comboBox.setSelectedIndex( 0 );
    return comboBox;
  }

  public Object getCellEditorValue()
  {
    return comboBox.getItems();
  }

  public void setComboBoxItemRemovable( boolean removable )
  {
    if( removable )
    {
      comboBox.addKeyListener( deleteKeyListener );
    }
    else
    {
      comboBox.removeKeyListener( deleteKeyListener );
    }
  }
}
