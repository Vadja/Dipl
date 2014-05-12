package org.iit.dr.data_model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "staffRate" )
public class StaffRate extends PersistenceObject
{

  @XmlElement( name = "id" )
  String id;

  @XmlElement( name = "position" )
  Position position;

  @XmlElement( name = "amountB" )
  Double amountB;

  @XmlElement( name = "amountP" )
  Double amountP;

  @XmlElement( name = "amountDO" )
  Double amountDO;

  public Double getAmountB()
  {
    return amountB != null ? amountB : 0;
  }

  public void setAmountB( Double amountB )
  {
    this.amountB = amountB;
  }

  public Double getAmountP()
  {
    return amountP != null ? amountP : 0;
  }

  public void setAmountP( Double amountP )
  {
    this.amountP = amountP;
  }

  public Double getAmountDO()
  {
    return amountDO != null ? amountDO : 0;
  }

  public void setAmountDO( Double amountDO )
  {
    this.amountDO = amountDO;
  }

  @Override
  public String getId()
  {
    return id;
  }

  @Override
  public Boolean isProxy()
  {
    return false;
  }

  public Position getPosition()
  {
    return position;
  }

  public void setPosition( Position position )
  {
    this.position = position;
  }

  @Override
  public StaffRate clone()
  {
    StaffRate clon = new StaffRate();
    clon.setPosition( position );
    clon.setAmountB( amountB );
    clon.setAmountDO( amountDO );
    clon.setAmountP( amountP );

    return clon;
  }
}
