package org.iit.dr.view.component;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * JInternalFrameExt.
 * 
 * @author Yuriy Karpovich
 */
public abstract class JInternalFrameExt<E> extends JDialogExt
{

  protected JInternalFrameExt() throws HeadlessException
  {

    init();
    setModal( true );
    generateComponents();
    applyBoundsSettings();
  }

  public abstract boolean showFrame( Object parent, E e );

}
