package org.iit.dr.services;

import java.util.List;

import org.iit.dr.data_model.Book;

public class BookService extends CommonService
{
  public static List<Book> getBookList()
  {
    return dataConnector.getBookList();
  }

  public static Book getBook( String bookId )
  {
    if( bookId == null || bookId.length() == 0 )
    {
      return null;
    }

    for( Book bookItem : dataConnector.getBookList() )
    {
      if( bookItem.getId().equals( bookId ) )
      {

        return bookItem;
      }
    }
    return null;
  }

  public static boolean removeBook( String bookId )
  {
    for( Book bookItem : dataConnector.getBookList() )
    {
      if( bookItem.getId().equals( bookId ) )
      {
        dataConnector.getBookList().remove( bookItem );
        return true;
      }
    }
    return false;
  }
}
