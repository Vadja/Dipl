package org.iit.dr.view.model.table.books;

import org.iit.dr.data_model.Book;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.data_storage.DataStorage;
import org.iit.dr.view.model.table.common.TableModelConst;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 14.12.2010
 * Time: 3:51:11
 * To change this template use File | Settings | File Templates.
 */
public class BookTableModel extends DefaultTableModel implements TableModelConst {

  List<Book> bookList;

  String[] title = new String[]{
      "Название",
      "Авторы",
      "Год"
  };

  public BookTableModel() {
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

  @Override
  public int getRowCount() {
    return bookList != null ? bookList.size() : 0;
  }

  @Override
  public boolean isCellEditable(int rowIndex,
                                int columnIndex) {
    return false;
  }

  @Override
  public Object getValueAt(int rowIndex,
                           int columnIndex) {


    if (rowIndex < bookList.size()) {

      Book book = bookList.get(rowIndex);

      switch (columnIndex) {

        case 0:
          return book.getTitle();
        case 1:
          return book.getAuthor();
        case 2:
          return book.getYear();
        case ID_COLUMN:
          return book.getId();
      }
    }

    return null;
  }

  public void updateData() {

    updateData(null);
  }

  public void updateData(String name) {

    if (bookList == null) {
      bookList = new ArrayList<Book>();
    } else {
      bookList.clear();
    }

    List<Book> bookListSource = DataStorage.getInstance().getBookList();

    if (bookListSource != null) {

      for (Book book : bookListSource) {

        if (applyFilterName(name, book)) {

          bookList.add(book);
        }
      }
    }

    fireTableDataChanged();
  }

//  public void updateData(List<String> stafferIdList) {
//
//    if (bookList == null) {
//      bookList = new ArrayList<Book>();
//    } else {
//      bookList.clear();
//    }
//
//    List<Book> teacherListSource = DataStorage.getInstance().getBookList();
//
//    if (teacherListSource != null) {
//
//      for (Teacher book : teacherListSource) {
//
//        if (stafferIdList.contains(book.getId())) {
//
//          teacherList.add(teacher);
//        }
//      }
//    }
//
//    fireTableDataChanged();
//  }

  private boolean applyFilterName(String name, Book book) {

    return name == null ||
        (book.getAuthor() != null
            && book.checkAuthor(name));
  }


  private boolean applyFilterClass(List<Class> classList, Book book) {


    if (classList != null) {

      for (Class classItem : classList) {

        if (classItem.equals(book.getClass())) {

          return true;
        }
      }
      return false;

    } else {

      return true;
    }
  }
}

