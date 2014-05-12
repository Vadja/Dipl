package org.iit.dr.view.component;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.iit.dr.view.model.table.common.TableModelConst;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class JTableExt extends JTable
{
  private static final long serialVersionUID = -5314075675995311795L;

  public JTableExt()
  {
    super();
  }

  public JTableExt( AbstractTableModel tableModel )
  {
    super( tableModel );
    setColumnsSizeByHeaders();
  }

  public JTableExt( AbstractTableModel tableModel, int... proportions )
  {
    super( tableModel );
    setColumnsSizeByProportions( proportions );
  }

  public String[] getSelectedIds()
  {
    int rows[] = getSelectedRows();
    String result[] = new String[rows.length];
    for( int i = 0; i < rows.length; i++ )
    {
      result[i] = ( String ) getModel().getValueAt( rows[i], TableModelConst.ID_COLUMN );
    }
    return result;
  }

  protected void setColumnsSizeByHeaders()
  {
    int length = 0;
    for( int i = 0; i < getColumnCount(); i++ )
    {
      length += getColumnName( i ).length();
    }
    for( int i = 0; i < getColumnCount(); i++ )
    {
      float width = ( float ) getPreferredSize().width * getColumnName( i ).length() / length;
      if( width < 1 )
        width = 1;
      getColumnModel().getColumn( i ).setPreferredWidth( Math.round( width ) );
    }
  }

  protected void setColumnsSizeByProportions( int... proportions )
  {
    if( proportions.length != getColumnCount() )
    {
      throw new IllegalArgumentException( "proportions.length must be equals table columns number" );
    }
    int length = 0;
    for( int i = 0; i < getColumnCount(); i++ )
    {
      length += proportions[i];
    }
    for( int i = 0; i < getColumnCount(); i++ )
    {
      float width = ( float ) getPreferredSize().width * proportions[i] / length;
      if( width < 1 )
        width = 1;
      getColumnModel().getColumn( i ).setPreferredWidth( Math.round( width ) );
    }
  }

}
