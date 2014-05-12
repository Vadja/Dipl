package org.iit.dr.view.form.students;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
//import javax.swing.filechooser.FileNameExtensionFilter;

import org.iit.dr.data_model.role.Student;
import org.iit.dr.loaders.StudentListLoader;
import org.iit.dr.services.StudentsService;
import org.iit.dr.services.TeachersService;
import org.iit.dr.utils.OpenFileFilter;
import org.iit.dr.view.component.GroupTreeSimplePanel;
import org.iit.dr.view.component.JButtonSelect;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.model.table.common.TableModelConst;
import org.iit.dr.view.model.table.students.StudentsTableModel;

/**
 * StudentsFrame.
 * 
 * @author Yuriy Karpovich
 */
public class StudentListFrame extends JInternalFrameExt<Object>
{

  StudentsTableModel model;

  JTable jTable;

  StudentFrame studentFrame;

  GroupTreeSimplePanel groupTreeSimplePanel;

  // ---

  JButtonSelect selectedJButtonSelect;

  public StudentListFrame( Boolean selectDialog ) throws HeadlessException
  {

    if( selectDialog )
    {
      addComponents();
    }
  }

  public StudentListFrame() throws HeadlessException
  {
  }

  @Override
  protected void init()
  {

    setTitle( "Справочник: Студенты" );

    setMinimumSize( new Dimension( 600, 300 ) );
    studentFrame = new StudentFrame();
  }

  @Override
  protected void generateComponents()
  {

    model = new StudentsTableModel();
    jTable = new JTable( model );
    groupTreeSimplePanel = new GroupTreeSimplePanel( model );

    jTable.addMouseListener( new MouseAdapter()
    {
      @Override
      public void mouseClicked( MouseEvent e )
      {
        if( e.getButton() == MouseEvent.BUTTON3 )
        {
          if( jTable.getSelectedRow() >= 0 )
          {
            String guid = ( String ) model.getValueAt( jTable.getSelectedRow(), TableModelConst.ID_COLUMN );
            Student student = StudentsService.getStudent( guid );
            studentFrame.showFrame( model, student );
          }
        }
      }
    } );

    JScrollPane jScrollPane = new JScrollPane( jTable );
    JScrollPane jScrollPaneLeft = new JScrollPane( groupTreeSimplePanel );
    jScrollPaneLeft.setMinimumSize( new Dimension( 180, 200 ) );

    add( new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, jScrollPaneLeft, jScrollPane ), BorderLayout.CENTER );
    
    JButton importButton = new JButton( "Импорт студентов" );
    importButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        JFileChooser fileChooser = new JFileChooser();
//        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files", "xls");
        fileChooser.setFileFilter(new OpenFileFilter("xls"));
//        fileChooser.setFileFilter(filter);
        int result = fileChooser.showDialog(null, "Открыть");
        if (result == JFileChooser.APPROVE_OPTION){
        	StudentListLoader loader = new StudentListLoader();
        	loader.loadData(fileChooser.getSelectedFile().getAbsolutePath());
        	groupTreeSimplePanel.updateData();
        	model.updateData();
        }

      }
    } );

    JButton addButton = new JButton( "Добавить" );
    addButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        studentFrame.showFrame( model, null );

      }
    } );

    JButton removeButton = new JButton( "Удалить" );
    removeButton.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        if( jTable.getSelectedRows().length > 0 )
        {
            for( int idRow : jTable.getSelectedRows() )
            {
              String guid = (String) model.getValueAt( idRow, TableModelConst.ID_COLUMN );
              StudentsService.removeStudent( guid );
            }
        }
        model.updateData();
      }
    } );

    getContentPane().add( createNorthButtonPane( 5, importButton, addButton, removeButton ), BorderLayout.NORTH );
  }

  @Override
  public boolean showFrame( Object parent, Object jButtonSelectObject )
  {

    if( jButtonSelectObject != null )
    {

      if( jButtonSelectObject instanceof JButtonSelect )
      {

        selectedJButtonSelect = ( JButtonSelect ) jButtonSelectObject;
      }
    }

    if( groupTreeSimplePanel != null )
    {

      groupTreeSimplePanel.updateData();
    }

    model.updateData();
    setVisible( true );

    return true;
  }

  private void addComponents()
  {

    JButton applyButton = new JButton( "Применить" );
    applyButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {
        if( selectedJButtonSelect != null )
        {
          int selectedRow = jTable.getSelectedRow();
          if( selectedRow != -1 )
          {
            String guid = ( String ) model.getValueAt( selectedRow, TableModelConst.ID_COLUMN );
            if( guid != null )
            {
              Student student = StudentsService.getStudent( guid );
              selectedJButtonSelect.setUserObject( student );
              setVisible( false );
            }
          }
        }
      }
    } );

    JButton removeButton = new JButton( "Удалить выбор" );
    removeButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        if( selectedJButtonSelect != null )
        {

          selectedJButtonSelect.setUserObject( null );
        }

        setVisible( false );
      }
    } );

    JButton cancelButton = new JButton( "Отмена" );
    cancelButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        setVisible( false );
      }
    } );

    getContentPane().add( createNorthButtonPane( 10, applyButton, removeButton, cancelButton ), BorderLayout.SOUTH );
  }

}
