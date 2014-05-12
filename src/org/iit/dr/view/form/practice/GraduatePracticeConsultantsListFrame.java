package org.iit.dr.view.form.practice;

import org.iit.dr.data_model.PracticeType;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class GraduatePracticeConsultantsListFrame extends PracticeConsultantsListFrame
{
  private static final long serialVersionUID = -8940943780284157653L;

  @Override
  protected void init()
  {
    super.init();
    setTitle( "Список руководителей преддипломной практики от университета" );
  }

  @Override
  public PracticeType getPracticeType()
  {
    return PracticeType.GRADUATING;
  }

}
