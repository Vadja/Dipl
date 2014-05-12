package org.iit.dr.view.form.practice;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import org.iit.dr.data_model.test.DPData;
import org.iit.dr.documents.csv.read.InputDataReader;
import org.iit.dr.documents.word.write.practice.DPReportComplex;
import org.iit.dr.documents.word.write.practice.ProgressListener;
import org.iit.dr.utils.FileUtils;
import org.iit.dr.view.component.JInternalFrameExt;

/**
 * PrepareReportsFrame.
 * 
 * @author Yuriy Karpovich
 */
public class PrepareReportsFrame extends JInternalFrameExt<Object> implements ProgressListener
{
  private static final long serialVersionUID = -7961797982861214187L;

  JTextField importFileNameField;

  JProgressBar progressBar;

  List<DPData> dpDataList;

  @Override
  public boolean showFrame( Object parent, Object o )
  {

    clearFields();
    setVisible( true );

    return true;
  }

  @Override
  protected void init()
  {

    setTitle( "Формирования документов: Преддипломная практика, Диплом" );

    setMinimumSize( new Dimension( 600, 150 ) );
    setResizable( false );
  }

  @Override
  protected void generateComponents()
  {

    progressBar = new JProgressBar();

    JButton loginButton = new JButton( "Выполнить" );
    loginButton.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        dpDataList = InputDataReader.readDPDataList( importFileNameField.getText() );

        if( dpDataList != null )
        {

          progressBar.setMaximum( dpDataList.size() * 7 );
          progressBar.setMinimum( 0 );

          Thread thread = new Thread()
          {
            @Override
            public void run()
            {
              DPReportComplex dpReportComplex = new DPReportComplex();
              dpReportComplex.addProgressListener( PrepareReportsFrame.this );
              dpReportComplex.generateReports( dpDataList );
            }
          };

          thread.start();

        }

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

    importFileNameField = new JTextField();
    importFileNameField.setMaximumSize( new Dimension( 350, 22 ) );
    importFileNameField.setPreferredSize( new Dimension( 350, 22 ) );

    JButton fileChooserButton = new JButton( "Выбрать файл" );
    fileChooserButton.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {

        JFileChooser fileChooser = new JFileChooser( FileUtils.getLocationPath() );

        if( fileChooser.showDialog( PrepareReportsFrame.this, "Выбрать" ) == JFileChooser.APPROVE_OPTION )
        {

          importFileNameField.setText( fileChooser.getSelectedFile().getAbsolutePath() );
        }
      }
    } );

    JPanel centerJPanePart = createHPane( 5, createField( importFileNameField, "Путь к файлу" ), fileChooserButton );

    JPanel centerJPane = createVPane( 20, centerJPanePart, progressBar );

    getContentPane().add( centerJPane, BorderLayout.CENTER );
    getContentPane().add( createButtonPane( 10, loginButton, cancelButton ), BorderLayout.SOUTH );

  }

  private void clearFields()
  {

    importFileNameField.setText( "" );
    progressBar.setValue( 0 );
  }

  public void progressUpdate( ProgressEvent progressEvent )
  {

    progressBar.setValue( progressEvent.getCurrentPosition() );

    if( progressBar.getValue() == progressBar.getMaximum() )
    {

      JOptionPane.showMessageDialog( this, "Формирование документов завершено.", "Формирование документов",
        JOptionPane.INFORMATION_MESSAGE );
    }
  }
}
