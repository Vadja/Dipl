package org.iit.dr.view.form.practice;

import org.iit.dr.data_model.PracticeType;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class ProductionPracticeWorkListFrame extends PracticeWorkListFrame
{
  private static final long serialVersionUID = -1112150423942632653L;

  @Override
  protected void init()
  {
    super.init();
    setTitle( "Производственная практика" );
  }

  @Override
  public PracticeType getPracticeType()
  {
    return PracticeType.PRODUCTION;
  }

}