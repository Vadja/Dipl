package org.iit.dr.data_model;

import org.iit.dr.data_model.role.Person;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 14.12.2010
 * Time: 23:39:14
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "conference")
public class Conference extends PersistenceObject {

  public Conference() {
  }

  public Conference(List<Member> memberList, String title, int year,  String city) {
    this.title = title;
    this.memberList = memberList;
    this.year = year;
    this.city = city;
  }
  @XmlElement(name = "id")
  private String id;

  @XmlElement(name = "title")
  private String title;

  @XmlElement(name = "year")
  private int year;

  @XmlElement(name = "member")
  private List<Member> memberList;

  @XmlElement(name = "city")
  private String city;

  @XmlElement(name = "proxy")
  private Boolean proxy;
 


  public List<Member> getMemberList() {
    if (memberList == null) {

          memberList = new ArrayList<Member>();

        }
    return memberList;
  }

   public void setMemberList(List<Member> memberList) {
    this.memberList = memberList;
  }

  public String getTitle() {
    return title;
  }

   public void setTitle(String title) {
    this.title = title;
  }

  public String getCity() {
    return city;
  }

   public void setCity(String city) {
    this.city = city;
  }

  public int getYear() {
    return year;
  }

   public void setYear(int year) {
    this.year = year;
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

   

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Conference conference = (Conference) o;

    return (getTitle().equals(conference.getTitle())&&(getYear()==conference.getYear()));
  }
}

