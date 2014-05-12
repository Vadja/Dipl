package org.iit.dr.data_model;

import org.iit.dr.data_model.role.Person;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 14.12.2010
 * Time: 0:21:13
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "book")
public class Book extends PersistenceObject{

  public Book() {
  }

  public Book(String title, String author, String type) {
    this.title = title;
  }
  @XmlElement(name = "id")
  private String id;
  @XmlElement(name = "title")
  private String title;
  @XmlElement(name = "person")
  private List<Person> author;

  @XmlElement(name = "type")
  private String type;

  @XmlElement(name = "year")
  private int year;

  @XmlElement(name = "editor")
  private String editor;

  @XmlElement(name = "info")
  private String info;

  @XmlElement(name = "grif")
  private String grif;

  @XmlElement(name = "state")
  private String state;

  @XmlElement(name = "page")
  private String page;

  @XmlElement(name = "proxy")
  private Boolean proxy;

  public String getEditor() {
    return editor;
  }

   public void setEditor(String editor) {
    this.editor = editor;
  }

  public String getInfo() {
    return info;
  }

   public void setInfo(String info) {
    this.info = info;
  }

  public String getGrif() {
    return grif;
  }

   public void setGrif(String grif) {
    this.grif = grif;
  }

  public String getState() {
    return state;
  }

   public void setState(String state) {
    this.state = state;
  }

  public String getPage() {
    return page;
  }

   public void setPage(String page) {
    this.page = page;
  }  
    public Boolean isProxy() {
        return proxy;
      }

      public void setProxy(Boolean proxy) {
        this.proxy = proxy;
      }
  public int getYear() {
    return year;
  }

   public void setYear(int year) {
    this.year = year;
  }
  public String getId() {
    return id;
  }

   public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<Person> getAuthor() {
    return author;
  }

  public void setAuthor(List<Person> author) {
    this.author = author;
  }

  public String getAuthorString() {
      String s="";
      if (author!=null)
      {
          for (Person pers : author)
          {
              if (!s.equals("")) s=s+", "+pers.getShortName();
              else s=pers.getShortName();
          }
      }
    return s;
  }

  public boolean checkAuthor(String name) {
      String s="";
      if (author!=null)
      {
          for (Person pers : author)
          {
              if (pers.getLastName().toLowerCase().startsWith(name.toLowerCase())) return true;
          }
      }
    return false;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Book book = (Book) o;

    return getTitle().equals(book.getTitle());
  }
}

