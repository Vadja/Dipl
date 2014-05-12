package org.iit.dr.data_storage.object_collections;

import java.util.List;

import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * 
 * @author Uladzimir Babkou
 * 
 * @param <T>
 */
// @XmlSeeAlso( {BookCollection.class, CompanyCollection.class, ConferenceCollection.class,
// DefenceGraduateWorkCollection.class, GraduateWorkCollection.class, GraduatingPracticeConsultantCollection.class,
// GraduatingPracticePlaceCollection.class, OrganizationUnitCollection.class,
// ProductionPracticeConsultantCollection.class, ProductionPracticePlaceCollection.class, RateCollection.class,
// StudentCollection.class, TeacherCollection.class} )
@XmlSeeAlso ({StaffRateCollection.class})
public abstract class ListCollection<T>
{
  public abstract void setList( List<T> list );

  public abstract List<T> getList();

  public String getName()
  {
    return this.getClass().getSimpleName();
  }
}
