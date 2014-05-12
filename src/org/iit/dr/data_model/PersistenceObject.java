package org.iit.dr.data_model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlType;

/**
 * PersistenceObject.
 * 
 * @author Yuriy Karpovich
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "persistenceObject" )
public abstract class PersistenceObject implements Persistence
{

  public abstract String getId();

  public abstract Boolean isProxy();
}
