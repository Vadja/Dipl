package org.iit.dr.view.action;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

/**
 * AboutAction.
 * 
 * @author Yuriy Karpovich
 */
public class AboutAction extends CustomAction
{

  public AboutAction()
  {

    putValue( NAME, "О программе" );
    // putValue(LARGE_ICON_KEY, new ImageIcon("icons/l/" + iconName));
    // putValue(SMALL_ICON, new ImageIcon("icons/s/" + iconName));
  }

  public void actionPerformed( ActionEvent e )
  {

    JOptionPane
      .showMessageDialog(
        null,
        "ИИТ - БГУИР\n\"Система планирования и генерации отчетной документации\"\n(c) 2010 г., Юрий Карпович, Бобков Владимир",
        "О программе", JOptionPane.INFORMATION_MESSAGE );
  }
}
