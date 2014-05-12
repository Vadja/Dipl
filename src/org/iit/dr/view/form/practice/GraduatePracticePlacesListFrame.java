package org.iit.dr.view.form.practice;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import org.iit.dr.data_model.PracticeType;
import org.iit.dr.services.PracticeService;
import org.iit.dr.view.component.PopupMenuButton;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class GraduatePracticePlacesListFrame extends PracticePlacesListFrame
{
  private static final long serialVersionUID = 5977307934000557598L;

  @Override
  protected void init()
  {
    super.init();
    setTitle( "Список мест на преддипломную практику" );
  }

  @Override
  public PracticeType getPracticeType()
  {
    return PracticeType.GRADUATING;
  }

  @Override
  protected JPanel createLeftPanel()
  {
    JPanel panel =
      createVPane( 1, new JScrollPane( groupTreeSimplePanel ), new JScrollPane( studentsLeftTable ),
        createHPane( 1, createImportButton(), addStudentButton ) );
    panel.setPreferredSize( new Dimension( getSize().width / 4, getSize().height ) );
    return panel;
  }

  private PopupMenuButton createImportButton()
  {
    JPopupMenu importPopup = new JPopupMenu();
    JMenuItem item = new JMenuItem( "Из результатов распределения" );
    item.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        PracticeService.importGraduatePracticePlacesFromDistribution();
        practicePlacesTableModel.updateData();
        studentsRightTableModel.updateData();
        studentsLeftTableModel.updateData();
      }
    } );
    importPopup.add( item );
    PopupMenuButton importButton = new PopupMenuButton( "Импорт", importPopup );
    return importButton;
  }
}
