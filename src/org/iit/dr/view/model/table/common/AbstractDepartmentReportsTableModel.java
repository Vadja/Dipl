package org.iit.dr.view.model.table.common;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.iit.dr.view.component.ComponentsMediator;
import org.iit.dr.view.component.Updatable;

/**
 * 
 * @author Uladzimir Babkou
 * 
 * @param <T>
 */
public abstract class AbstractDepartmentReportsTableModel<T> extends DefaultTableModel implements TableModelConst,
  Updatable
{
  private static final long serialVersionUID = -7570164071823969467L;

  protected List<T> objectList;

  protected ComponentsMediator mediator;

  public AbstractDepartmentReportsTableModel()
  {
    super();
  }

  public AbstractDepartmentReportsTableModel( ComponentsMediator mediator )
  {
    this();
    this.mediator = mediator;
    mediator.addComponent( this );
  }

  @Override
  public String getColumnName( int column )
  {
    return getHeaders()[column];
  }

  @Override
  public Class<?> getColumnClass( int columnIndex )
  {
    return String.class;
  }

  @Override
  public int getColumnCount()
  {
    return getHeaders().length;
  }

  @Override
  public int getRowCount()
  {
    return objectList != null ? objectList.size() : 0;
  }

  @Override
  public boolean isCellEditable( int rowIndex, int columnIndex )
  {
    return false;
  }

  public void updateData()
  {
    fireTableDataChanged();
  }

  public void updateOtherComponents()
  {
    if( mediator != null )
    {
      mediator.updateOtherComponents( this );
    }
  }

  public abstract String[] getHeaders();
}
