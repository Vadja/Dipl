package org.iit.dr.view.model.table.conferences;

import org.iit.dr.data_model.Book;
import org.iit.dr.data_model.Conference;
import org.iit.dr.data_model.Member;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.data_model.unit.OrganizationUnit;
import org.iit.dr.data_storage.DataStorage;
import org.iit.dr.view.model.table.common.TableModelConst;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 15.12.2010
 * Time: 0:40:28
 * To change this template use File | Settings | File Templates.
 */
public class ConferenceTableModel extends DefaultTableModel implements TableModelConst {

  List<Member> memberList;

  String confIdFilterList = null;
  String yearFilterText = null;
//  List<String> yearFilterList = null;

  String[] title = new String[]{
      "Тема",
      "Авторы"
  };

  public ConferenceTableModel() {
  }

  @Override
  public String getColumnName(int column) {
    return title[column];
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    return String.class;
  }

  @Override
  public int getColumnCount() {

    return title.length;
  }

  public void updateData() {

    if (memberList == null) {
      memberList = new ArrayList<Member>();
    } else {
      memberList.clear();
    }

    List<Conference> confListSource = DataStorage.getInstance().getConferenceList();
    ////////////////////////////////////////////////////////////////////////////
//      List<Book> bookListSource = DataStorage.getInstance().getBookList();


    if (confListSource != null) {

      for (Conference conf : confListSource)
        {


        if (confIdFilterList != null) {

          if ((confIdFilterList.contains(conf.getId()))) {
            for (Member m : conf.getMemberList())
            memberList.add(m);
          }


        }

        else {

          for (Member m : conf.getMemberList())
            memberList.add(m);
        }
      }
    }

    fireTableDataChanged();
  }



  @Override
  public int getRowCount() {
    return memberList != null ? memberList.size() : 0;
  }

  @Override
  public Object getValueAt(int rowIndex,
                           int columnIndex) {


    if (rowIndex < memberList.size()) {

      Member member = memberList.get(rowIndex);

      String s="";

      switch (columnIndex) {


        case 0:
          return member.getTheme();
        case 1:
          return member.getAuthor();
        case ID_COLUMN:
          return member.getId();
      }
    }

    return null;
  }

  @Override
  public boolean isCellEditable(int rowIndex,
                                int columnIndex) {
    return false;
  }

  public void updateData(String confIdFilterList, int i) {

    if (i==0) this.confIdFilterList = confIdFilterList;
      else  this.yearFilterText = confIdFilterList;

    updateData();
  }
}
