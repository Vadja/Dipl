package org.iit.dr.documents.csv.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.iit.dr.data_model.role.Student;
import org.iit.dr.data_model.test.DPData;

/**
 * InputDataReader.
 * 
 * @author Yuriy Karpovich
 */
public class InputDataReader
{

  public static List<DPData> readDPDataList( String filePath )
  {

    List<DPData> dpDataList = new ArrayList<DPData>();

    try
    {
      BufferedReader input = new BufferedReader( new FileReader( filePath ) );

      try
      {
        String line = null;
        while( ( line = input.readLine() ) != null )
        {
          String[] lineParts = line.split( ";" );

          if( !lineParts[0].equals( "№" ) )
          {

            DPData dpData = new DPData();

            Student student = new Student();
            // student.setGroup(lineParts[1]);
            String[] fio = lineParts[2].split( "[ ]" );
            student.setLastName( fio[0] );
            student.setFirstName( fio[1] );
            student.setPatronymicName( fio[2] );
            dpData.setStudent( student );

            dpData.setTheme( lineParts[3] );

            String[] ruk = lineParts[4].split( "," );
            dpData.setManager( ruk[0] );
            if( ruk.length == 2 )
            {
              dpData.setManagerData( ruk[1] );
            }
            else
            {
              dpData.setManagerData( ruk[1] + ", " + ruk[2] );
            }

            String[] kon = lineParts[5].split( "," );
            dpData.setConsultant( kon[0] );
            if( kon.length == 2 )
            {
              dpData.setConsultantData( kon[1] );
            }
            else
            {
              dpData.setConsultantData( kon[1] + ", " + kon[2] );
            }

            dpDataList.add( dpData );
          }
        }
      }
      finally
      {
        input.close();
      }
    }
    catch( IOException ex )
    {
      ex.printStackTrace();
    }

    return dpDataList;
  }

}
