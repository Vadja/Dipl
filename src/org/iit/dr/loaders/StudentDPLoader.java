package org.iit.dr.loaders;

import org.iit.dr.data_model.role.Student;
import org.iit.dr.documents.csv.read.CsvReader;
import org.iit.dr.documents.csv.read.DataLine;

public class StudentDPLoader extends DataLoader
{
  public StudentDPLoader( String filePath )
  {
    this.filePath = filePath;
  }

  @Override
  public void loadData()
  {
    CsvReader reader = new CsvReader( filePath );
    this.titles = reader.getTitles();

    for( DataLine item : reader.getData() )
    {
      Student student = loadStudent( item );

      if( student == null )
        break;
      String ou = loadOrganizationUnit( item );
      student.setGroup( ou );
      loadGraduateWork( item, student );
    }
  }
}
