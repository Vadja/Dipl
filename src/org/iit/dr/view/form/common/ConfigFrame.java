package org.iit.dr.view.form.common;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import org.iit.dr.config.ApplicationConfig;
import org.iit.dr.config.exceptions.ApplicationConfigurationException;
import org.iit.dr.data_model.unit.OrganizationUnit;
import org.iit.dr.services.OrganizationUnitService;
import org.iit.dr.utils.MemoryManager;
import org.iit.dr.view.component.JButtonSelect;
import org.iit.dr.view.component.JInternalFrameExt;
import org.iit.dr.view.form.students.GroupTreeFrame;

public class ConfigFrame extends JInternalFrameExt<Object>
{
  private JButtonSelect pathWord;

  private JButtonSelect pathExcel;

  private JButtonSelect headFaculty;

  ApplicationConfig config;

  GroupTreeFrame groupTreeFrame;

  @Override
  public boolean showFrame( Object parent, Object e )
  {
    try
    {
      pathWord.setUserObject( config.getProperty( "msword.path" ) );

      pathExcel.setUserObject( config.getProperty( "msexcel.path" ) );

      headFaculty.setUserObject( OrganizationUnitService.getOrganizationUnit( config.getProperty( "faculty" ) ) );
    }
    catch( ApplicationConfigurationException e1 )
    {

    }
    setVisible( true );

    return true;
  }

  public ConfigFrame()
  {
    config = ApplicationConfig.getInstance();
    groupTreeFrame = ( GroupTreeFrame ) MemoryManager.getObject( GroupTreeFrame.class, true );
    groupTreeFrame.updateData();
  }

  @Override
  protected void init()
  {
    setTitle( "Настройки" );

    setMinimumSize( new Dimension( 700, 500 ) );

  }

  private void addListners()
  {
    pathExcel.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {
        JFileChooser fileChooser = new JFileChooser( "Выберите файл для загрузки" );
        fileChooser.setAcceptAllFileFilterUsed( false );
        int result = fileChooser.showDialog( null, "Открыть" );
        if( result == JFileChooser.APPROVE_OPTION )
        {
          File file = fileChooser.getSelectedFile();
          pathExcel.setUserObject( file.getAbsolutePath() );
        }
      }
    } );

    pathWord.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {
        JFileChooser fileChooser = new JFileChooser( "Выберите файл для загрузки" );
        fileChooser.setAcceptAllFileFilterUsed( false );
        int result = fileChooser.showDialog( null, "Открыть" );
        if( result == JFileChooser.APPROVE_OPTION )
        {
          File file = fileChooser.getSelectedFile();
          pathWord.setUserObject( file.getAbsolutePath() );
        }
      }
    } );

    headFaculty.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {
        groupTreeFrame.showFrame( ConfigFrame.this, headFaculty );

      }
    } );
  }

  @Override
  protected void generateComponents()
  {
    pathWord = createJButtonSelect();
    pathExcel = createJButtonSelect();
    headFaculty = createJButtonSelect();

    addListners();

    JPanel fieldsPane =
      createVPane( 5, createField( pathWord, "Путь к MSWord: " ), createField( pathExcel, "Путь к MSExcel: " ),
        createField( headFaculty, "Факультет" ) );
    JButton applyButton = new JButton( "Применить" );
    applyButton.addActionListener( new ActionListener()
    {

      public void actionPerformed( ActionEvent e )
      {

        config.setProperty( "msword.path", ( String ) pathWord.getUserObject() );
        config.setProperty( "msexcel.path", ( String ) pathExcel.getUserObject() );
        config.setProperty( "faculty", ( ( OrganizationUnit ) headFaculty.getUserObject() ).getId() );
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

    getContentPane().add( fieldsPane, BorderLayout.CENTER );
    getContentPane().add( createNorthButtonPane( 10, applyButton, cancelButton ), BorderLayout.SOUTH );
  }

}
