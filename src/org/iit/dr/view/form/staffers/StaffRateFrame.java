package org.iit.dr.view.form.staffers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.iit.dr.data_model.StaffRate;
import org.iit.dr.documents.word.write.staffers.StaffEmployeeDocumentGenerator;
import org.iit.dr.services.RateService;
import org.iit.dr.services.StaffRateService;
import org.iit.dr.view.component.JDateField;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.component.JTableExt;
import org.iit.dr.view.model.table.staffers.StaffRateTableModel;

public class StaffRateFrame extends JInternalFrameExt<Object>
{

  JTableExt jTable;
  
  JDateField dateSchedule;

  StaffRateTableModel model;

  @Override
  public boolean showFrame( Object parent, Object e )
  {
	model.updateData();
    setVisible( true );
    return true;
  }

  @Override
  protected void init()
  {
    setTitle( "Справочник: Штатное расписание" );
    setMinimumSize( new Dimension( 700, 300 ) );
  }

  @Override
  protected void generateComponents()
  {
    model = new StaffRateTableModel();
    jTable = new JTableExt( model );
    jTable.getColumnModel().getColumn(0).setMinWidth(100);
    dateSchedule = createJDateField(250);
    dateSchedule.setDate(new Date());
    JButton createDoc = new JButton("Сформировать сведения о штатной расстановке сотрудников");
    createDoc.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			StaffEmployeeDocumentGenerator generator = new StaffEmployeeDocumentGenerator();
			generator.generate(dateSchedule.getDate());
			
		}
	});
    JButton saveButton = new JButton( "Применить" );
    saveButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {
        List<StaffRate> list = StaffRateService.getStaffRateList();
        if( list != null )
        {
          list.clear();
        }
        else
        {
          list = new ArrayList<StaffRate>();
        }
        for( StaffRate item : model.getObjectList() )
        {
          list.add( item.clone() );
        }
      }
    } );
    JScrollPane jScrollPane = new JScrollPane( jTable );
    JPanel jSchedulePanel = createNorthButtonPane(5, createField(dateSchedule, "Дата: "), createDoc, saveButton);
    add(jScrollPane);

    getContentPane().add( createHPane( 0, jSchedulePanel ), BorderLayout.SOUTH );
  }
}
