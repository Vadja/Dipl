package org.iit.dr.view.form.practice;

import org.iit.dr.data_model.PracticeType;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class ProductionPracticePlacesListFrame extends PracticePlacesListFrame
{
  private static final long serialVersionUID = -26481597879117895L;

  @Override
  protected void init()
  {
    super.init();
    setTitle( "Список мест на производственную практику" );
  }

  @Override
  public PracticeType getPracticeType()
  {
    return PracticeType.PRODUCTION;
  }
}
