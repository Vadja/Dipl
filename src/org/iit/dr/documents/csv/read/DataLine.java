package org.iit.dr.documents.csv.read;

public class DataLine
{
  private final String[] data;

  public DataLine( String[] aData )
  {
    this.data = aData;
  }

  public String[] getDataLine()
  {
    return data;
  }

  public String getField( int index )
  {
    if( data.length > index && index >= 0 )
    {
      String retData = data[index] != null ? data[index].trim() : "";
      return retData;
    }
    return null;
  }
}
