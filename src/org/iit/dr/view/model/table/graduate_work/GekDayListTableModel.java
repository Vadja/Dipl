package org.iit.dr.view.model.table.graduate_work;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.iit.dr.data_model.Gek;
import org.iit.dr.data_model.GekDay;
import org.iit.dr.services.GekService;
import org.iit.dr.utils.DateUtils;
import org.iit.dr.view.model.table.common.TableModelConst;
import org.iit.dr.view.model.table.common.UpdateDateTableModel;

/**
 * GekDayListTableModel.
 * 
 * @author Yuriy Karpovich
 */
public class GekDayListTableModel extends DefaultTableModel implements UpdateDateTableModel, TableModelConst
{

  Gek gek;

  String[] title = new String[] {"День", "Начало защиты", "Конец защиты", "Количество студентов",
    "Продолжительность защиты"};

  Class[] classType = new Class[] {Date.class, String.class, String.class, Integer.class, String.class};

  public GekDayListTableModel()
  {

    gek = GekService.getGek();
  }

  @Override
  public String getColumnName( int column )
  {
    return title[column];
  }

  @Override
  public Class<?> getColumnClass( int column )
  {
    return classType[column];
  }

  @Override
  public int getColumnCount()
  {

    return title.length;
  }

  @Override
  public int getRowCount()
  {
    return gek != null && gek.getGekDayList() != null ? gek.getGekDayList().size() : 0;
  }

  @Override
  public boolean isCellEditable( int rowIndex, int columnIndex )
  {

    return columnIndex < 4;
  }

  @Override
  public Object getValueAt( int rowIndex, int columnIndex )
  {

    if( rowIndex < gek.getGekDayList().size() )
    {

      GekDay gekDay = gek.getGekDayList().get( rowIndex );

      switch( columnIndex )
      {

        case 0:
          return gekDay.getDate();
        case 1:
          return gekDay.getStartTime();
        case 2:
          return gekDay.getEndTime();
        case 3:
          return gekDay.getStudentsCount();
        case 4:
          return gekDay.getTimeForStudent();
        case ID_COLUMN:
          return gekDay.getId();
      }
    }

    return null;
  }

  @Override
  public void setValueAt( Object aValue, int rowIndex, int columnIndex )
  {

    if( rowIndex < gek.getGekDayList().size() )
    {

      GekDay gekDay = gek.getGekDayList().get( rowIndex );

      switch( columnIndex )
      {

        case 0:
          gekDay.setDate( ( Date ) aValue );
          break;
        case 1:
          gekDay.setStartTime( ( String ) aValue );
          gekDay.setTimeForStudent( DateUtils.calculateTimeForStudent( gekDay.getStartTime(), gekDay.getEndTime(),
            gekDay.getStudentsCount() ) );
          break;
        case 2:
          gekDay.setEndTime( ( String ) aValue );
          gekDay.setTimeForStudent( DateUtils.calculateTimeForStudent( gekDay.getStartTime(), gekDay.getEndTime(),
            gekDay.getStudentsCount() ) );
          break;
        case 3:
          gekDay.setStudentsCount( ( Integer ) aValue );
          gekDay.setTimeForStudent( DateUtils.calculateTimeForStudent( gekDay.getStartTime(), gekDay.getEndTime(),
            gekDay.getStudentsCount() ) );
          break;
      }
    }
  }

  public void updateData( List<String> studentIdFilterList )
  {

    Collections.sort( gek.getGekDayList(), new Comparator<GekDay>()
    {
      public int compare( GekDay gekDay1, GekDay gekDay2 )
      {

        Date gekDayDate1 = gekDay1.getDate();
        Date gekDayDate2 = gekDay2.getDate();

        if( gekDayDate1 != null && gekDayDate2 != null )
        {

          return gekDayDate1.compareTo( gekDayDate2 );
        }
        else if( gekDayDate1 == null )
        {

          return -1;
        }
        else
        {

          return 1;
        }
      }
    } );

    fireTableDataChanged();
  }
}
