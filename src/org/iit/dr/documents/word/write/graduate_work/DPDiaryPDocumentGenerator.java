package org.iit.dr.documents.word.write.graduate_work;

import org.iit.dr.documents.word.write.common.DocumentGenerator;
//import org.iit.dr.utils.RemoveTrialString;

/**
 * PDPDocumentGenerator.
 * 
 * @author Yuriy Karpovich
 */
public class DPDiaryPDocumentGenerator extends DocumentGenerator
{

  protected void init()
  {
    templateName = "ДНЕВНИК-ДП.doc";
    fieldNames = new String[] {"RUK", "KON", "FIO_STUDENT", "DP_THEME"};
    fieldValues = new String[] {"RUK", "KON", "FIO_STUDENT", "DP_THEME"};
  }

  public void generate( String filePath, Object[] fieldValues )
  {

    this.fieldValues = fieldValues;

    generateDocument( filePath );
//    RemoveTrialString.removeStringInDocument(filePath);
  }
}