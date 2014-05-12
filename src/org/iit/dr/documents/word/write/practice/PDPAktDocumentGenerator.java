package org.iit.dr.documents.word.write.practice;

import org.iit.dr.documents.word.write.common.DocumentGenerator;

/**
 * PDPDocumentGenerator.
 * 
 * @author Yuriy Karpovich
 */
public class PDPAktDocumentGenerator extends DocumentGenerator
{

  protected void init()
  {
    templateName = "договор-ПДП-акт.doc";
    fieldNames = new String[] {"N_P", "FIO_STUDENT", "FAC_SPEC", "F_B", "F_N"};
    fieldValues = new String[] {"N_P", "FIO_STUDENT", "FAC_SPEC", "F_B", "F_N"};
  }

  public void generate( String filePath, Object[] fieldValues )
  {

    this.fieldValues = fieldValues;

    generateDocument( filePath );
  }
}