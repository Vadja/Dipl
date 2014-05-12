package org.iit.dr.view.form.practice;

import org.iit.dr.data_model.PracticeType;

public class ProductionPracticeConsultantsListFrame extends PracticeConsultantsListFrame
{
  private static final long serialVersionUID = -2899341181506586511L;

  @Override
  protected void init()
  {
    super.init();
    setTitle( "Список руководителей производственной практики от университета" );
  }

  @Override
  public PracticeType getPracticeType()
  {
    return PracticeType.PRODUCTION;
  }

}
