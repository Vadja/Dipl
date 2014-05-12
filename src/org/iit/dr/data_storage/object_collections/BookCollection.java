package org.iit.dr.data_storage.object_collections;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.iit.dr.data_model.Book;

/**
 * Created by IntelliJ IDEA. User: Admin Date: 14.12.2010 Time: 0:22:05 To change this template use File | Settings |
 * File Templates.
 */
@XmlRootElement( name = "bookList" )
@XmlAccessorType( XmlAccessType.FIELD )
public class BookCollection extends ListCollection<Book>
{
  @XmlElement( name = "book" )
  protected List<Book> list;

  public BookCollection()
  {
  }

  public BookCollection( List<Book> list )
  {
    this.list = list;
  }

  @Override
  public void setList( List<Book> list )
  {
    this.list = list;
  }

  @Override
  public List<Book> getList()
  {
    return list;
  }
}
