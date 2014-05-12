package org.iit.dr.data_model;

import org.iit.dr.data_model.role.Person;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 14.12.2010
 * Time: 23:45:46
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "member")
public class Member extends PersistenceObject{

  public Member() {
  }

  public Member(List<Person> personList, String theme) {
    this.theme = theme;
    this.personList = personList;
  }
  @XmlElement(name = "id")
  private String id;

  @XmlElement(name = "person")
  private List<Person> personList;
    
  @XmlElement(name = "theme")
  private String theme;
  @XmlElement(name = "publication")
  private String publication;

  @XmlElement(name = "prize")
  private String prize;


  @XmlElement(name = "proxy")
  private Boolean proxy;

  public List<Person> getPersonList() {
    return personList;
  }

   public void setPersonList(List<Person> personList) {
    this.personList = personList;
  }

  public String getTheme() {
    return theme;
  }

   public void setTheme(String theme) {
    this.theme = theme;
  }

  public String getPublication() {
    return publication;
  }

   public void setPublication(String publication) {
    this.publication = publication;
  }

  public String getPrize() {
    return prize;
  }

   public void setPrize(String prize) {
    this.prize = prize;
  }

    public Boolean isProxy() {
        return proxy;
      }

      public void setProxy(Boolean proxy) {
        this.proxy = proxy;
      }
    public String getId() {
    return id;
  }

   public void setId(String id) {
    this.id = id;
  }

  public String getAuthor() {
      String author= new String("");
      if (personList!=null){
      for (Person p : personList)
      {
          author = author + p.getShortName()+", ";
      }
      if (author!="")author = author.substring(0, author.length()-2); }
    return author;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Member member = (Member) o;

    return getTheme().equals(member.getTheme());
  }
}
