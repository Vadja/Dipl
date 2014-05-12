package org.iit.dr.documents.word.write.staffers;

import org.iit.dr.documents.word.write.common.DocumentGenerator;
//import org.iit.dr.utils.RemoveTrialString;

/**
 * PDPDocumentGenerator.
 * 
 * @author Yuriy Karpovich
 */
public class PR9AktDocumentGenerator extends DocumentGenerator
{

  protected void init()
  {
    templateName = "Форма-АПР-09.doc";
    fieldNames = new String[] {"RUK_FULL", "START_DATE", "END_DATE", "H_COUNT"};
    fieldValues = new String[] {"RUK_FULL", "START_DATE", "END_DATE", "H_COUNT"};
  }

  public void generate( String filePath, Object[] fieldValues )
  {

    this.fieldValues = fieldValues;

    generateDocument( filePath );
//    RemoveTrialString.removeStringInDocument(filePath);
  }
}