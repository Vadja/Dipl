package org.iit.dr.documents.word.write.common;

import org.iit.dr.utils.FileUtils;

import com.aspose.words.Document;

/**
 * DocumentGenerator.
 * 
 * @author Yuriy Karpovich
 */
public abstract class DocumentGenerator
{

  protected Object[] fieldValues;

  protected String[] fieldNames;

  protected String templateName;

  protected DocumentGenerator()
  {

    init();
  }

  protected abstract void init();

  public void generateDocument( String filepathOutput )
  {
	System.out.println(filepathOutput);
    filepathOutput = FileUtils.checkDir( filepathOutput ).getAbsolutePath();
    System.out.println(filepathOutput);
    try
    {
      Document doc = new Document( FileUtils.getTemplate( templateName ) );
      System.out.println(fieldNames.length + " " + fieldValues.length);
      doc.getMailMerge().execute( fieldNames, fieldValues );
      // Save the finished document.
      doc.save( filepathOutput );
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }
}
