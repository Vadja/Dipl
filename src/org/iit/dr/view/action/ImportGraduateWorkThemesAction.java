package org.iit.dr.view.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
//import javax.swing.filechooser.FileNameExtensionFilter;

import org.iit.dr.documents.word.read.graduate_work.GraduateWorkThemesImporter;
import org.iit.dr.utils.OpenFileFilter;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class ImportGraduateWorkThemesAction implements ActionListener
{
  private Component component;

  public ImportGraduateWorkThemesAction( Component component )
  {
    this.component = component;
  }

  public void actionPerformed( ActionEvent arg0 )
  {
    JFileChooser fileChooser = new JFileChooser( "Выберите папку с заявлениями" );
//    FileNameExtensionFilter filter = new FileNameExtensionFilter("Doc files", "doc");
//    fileChooser.setFileFilter(filter);
    fileChooser.setFileFilter(new OpenFileFilter("doc"));
//    fileChooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
//    fileChooser.setAcceptAllFileFilterUsed( false );
    int result = fileChooser.showDialog( component, "Открыть" );
    if( result == JFileChooser.APPROVE_OPTION )
    {
      File file = fileChooser.getSelectedFile();
      new GraduateWorkThemesImporter().importThemes( file.getAbsolutePath() );
    }
  }

}
