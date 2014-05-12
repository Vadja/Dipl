package org.iit.dr.view.form.practice;

/**
 * ProgressEvent.
 * 
 * @author Yuriy Karpovich
 */
public class ProgressEvent
{

  private Integer currentPosition;

  public ProgressEvent( Integer currentPosition )
  {
    this.currentPosition = currentPosition;
  }

  public Integer getCurrentPosition()
  {
    return currentPosition;
  }

  public void setCurrentPosition( Integer currentPosition )
  {
    this.currentPosition = currentPosition;
  }
}
