package org.iit.dr.data_model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author Uladzimir_Babkou
 * 
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "distributionPlace" )
public class DistributionPlace
{
  @XmlElement( name = "companyId" )
  private String companyId;

  @XmlElement( name = "commonRequest" )
  private int commonRequestsNumber;

  @XmlElement( name = "requestedStudentsIdList" )
  private List<String> requestedStudentsIdList;

  @XmlElement( name = "distributedStudentsIdList" )
  private List<String> distributedStudentsIdList;

  public DistributionPlace()
  {
    requestedStudentsIdList = new ArrayList<String>();
    distributedStudentsIdList = new ArrayList<String>();
  }

  public DistributionPlace( Company company )
  {
    this();
    this.companyId = company.getId();
  }

  public DistributionPlace( String companyId )
  {
    this();
    this.companyId = companyId;
  }

  public String getCompanyId()
  {
    return companyId;
  }

  public void setCompanyId( String companyId )
  {
    this.companyId = companyId;
  }

  public int getCommonRequestsNumber()
  {
    return commonRequestsNumber;
  }

  public void setCommonRequestsNumber( int commonRequest )
  {
    this.commonRequestsNumber = commonRequest;
  }

  public List<String> getRequestedStudentsIdList()
  {
    return requestedStudentsIdList;
  }

  public void setRequestedStudentsIdList( List<String> requestedStudentsIdList )
  {
    this.requestedStudentsIdList = requestedStudentsIdList;
  }

  public List<String> getDistributedStudentsIdList()
  {
    return distributedStudentsIdList;
  }

  public void setDistributedStudentsIdList( List<String> distributedStudentsIdList )
  {
    this.distributedStudentsIdList = distributedStudentsIdList;
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + commonRequestsNumber;
    result = prime * result + ( ( companyId == null ) ? 0 : companyId.hashCode() );
    result = prime * result + ( ( distributedStudentsIdList == null ) ? 0 : distributedStudentsIdList.hashCode() );
    result = prime * result + ( ( requestedStudentsIdList == null ) ? 0 : requestedStudentsIdList.hashCode() );
    return result;
  }

  @Override
  public boolean equals( Object obj )
  {
    if( this == obj )
      return true;
    if( obj == null )
      return false;
    if( getClass() != obj.getClass() )
      return false;
    DistributionPlace other = ( DistributionPlace ) obj;
    return companyId.equals( other.getCompanyId() );
  }

  public int getRequestsSumm()
  {
    return commonRequestsNumber + requestedStudentsIdList.size();
  }
}
