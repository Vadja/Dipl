package org.iit.dr.view.form.practice;

import org.iit.dr.data_model.PracticeType;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class GraduatePracticeWorkListFrame extends PracticeWorkListFrame
{
  private static final long serialVersionUID = 2038590663078112836L;

  @Override
  protected void init()
  {
    super.init();
    setTitle( "Преддипломная практика" );
  }

  @Override
  public PracticeType getPracticeType()
  {
    return PracticeType.GRADUATING;
  }
}
