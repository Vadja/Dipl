package org.iit.dr.view.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPopupMenu;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class PopupMenuButton extends JButton
{
  private static final long serialVersionUID = -8586351182197737093L;

  private JPopupMenu popup;

  public PopupMenuButton( String text, JPopupMenu popup )
  {
    super( text );
    this.popup = popup;
    addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        int x = PopupMenuButton.this.getX();
        int y = PopupMenuButton.this.getY();
        PopupMenuButton.this.popup.show( PopupMenuButton.this.getParent(), x, y
          - ( int ) PopupMenuButton.this.popup.getPreferredSize().getHeight() );
      }
    } );
  }
}
