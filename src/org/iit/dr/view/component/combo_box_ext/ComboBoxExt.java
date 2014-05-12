package org.iit.dr.view.component.combo_box_ext;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

/**
 * 
 * @author Uladzimir_Babkou
 * 
 */
public class ComboBoxExt extends JComboBox
{
  private static final long serialVersionUID = -8527918038286671297L;

  protected Object alternativeItem;

  public ComboBoxExt()
  {
    super();
  }

  public ComboBoxExt( List<Object> items )
  {
    this();
    setItems( items );
  }

  public void setItems( List<Object> items )
  {
    removeAllItems();
    if( items != null )
    {
      for( Object item : items )
      {
        addItem( item );
      }
    }
    if( alternativeItem != null )
    {
      setAlternativeItem( alternativeItem );
    }
  }

  public List<Object> getItems()
  {
    List<Object> items = new ArrayList<Object>();
    for( int i = 0; i < getItemCount(); i++ )
    {
      Object item = getItemAt( i );
      if( !item.equals( alternativeItem ) && !item.equals( "" ) )
      {
        items.add( item );
      }
    }
    return items;
  }

  public boolean addItemIfNotExist( Object item )
  {
    for( int i = 0; i < getItemCount(); i++ )
    {
      if( getItemAt( i ).equals( item ) )
      {
        return false;
      }
    }
    addItem( item );
    return true;
  }

  public boolean addItemIfNotExistNotLast( Object item )
  {
    for( int i = 0; i < getItemCount(); i++ )
    {
      if( getItemAt( i ).equals( item ) )
      {
        return false;
      }
    }
    addItem( item );
    setAlternativeItemLast();
    return true;
  }

  protected void setAlternativeItemLast()
  {
    if( getAlternativeItem() != null )
    {
      removeItem( "" );
      removeItem( alternativeItem );
      addItemIfNotExist( "" );
      addItemIfNotExist( alternativeItem );
    }
  }

  public void setAlternativeItem( Object alternativeItem )
  {
    this.alternativeItem = alternativeItem;
    addItemIfNotExist( "" );
    addItemIfNotExist( alternativeItem );
  }

  public boolean isAlternativeItemSelected()
  {
    return getSelectedItem().equals( alternativeItem );
  }

  public boolean isEmptyItemSelected()
  {
    return getSelectedItem().equals( "" );
  }

  public Object getAlternativeItem()
  {
    return alternativeItem;
  }

  public void setAddAlternativeListener( AlternativeSelectAction selectAction )
  {
    addActionListener( new ComboBoxAlternativeAddListener( this, alternativeItem, selectAction ) );
  }

  public void setAddAlternativeListener( Object alternativeItem, AlternativeSelectAction selectAction )
  {
    setAlternativeItem( alternativeItem );
    addActionListener( new ComboBoxAlternativeAddListener( this, alternativeItem, selectAction ) );
  }

  // private AlternativeSelectAction getAlternativeActionListener()
  // {
  // return
  // }
}
