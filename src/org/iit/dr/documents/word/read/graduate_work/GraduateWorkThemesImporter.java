package org.iit.dr.documents.word.read.graduate_work;

import java.io.File;
import java.io.FileFilter;

import org.iit.dr.data_model.GraduateWork;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.services.GraduateWorkService;
import org.iit.dr.services.StudentsService;

/**
 * 
 * @author Uladzimir Babkou
 * 
 */
public class GraduateWorkThemesImporter
{
  public void importThemes( String directoryPath )
  {
    File[] docsList = getDocsList( directoryPath );
    if( docsList != null )
    {
      for( File file : docsList )
      {
        if( !processDoc( file.getAbsolutePath() ) )
        {
        }
      }
    }
  }

  private boolean processDoc( String filePath )
  {
    GraduateWorkThemeReader reader = new GraduateWorkThemeReader();
    try
    {
      reader.processDocument( filePath );
      Student student = StudentsService.getStudentByFullName( reader.getStudentFullName() );
      if( student == null )
        return false;
      GraduateWork graduateWork = GraduateWorkService.getGraduateWorkByStudentId( student.getId() );
      if( graduateWork == null )
        return false;
      if( graduateWork.getTitle() == null || graduateWork.getTitle().equals( "" ) )
      {
        graduateWork.setTitle( reader.getTheme() );
      }
    }
    catch( Exception e )
    {
      return false;
    }
    return true;
  }

  private File[] getDocsList( String directoryPath )
  {
    File dir = new File( directoryPath );
    if( dir.isDirectory() )
    {
      return dir.listFiles( new FileFilter()
      {
        public boolean accept( File pathname )
        {
          return pathname.getName().endsWith( ".doc" );
        }
      } );
    }
    return null;
  }

}
