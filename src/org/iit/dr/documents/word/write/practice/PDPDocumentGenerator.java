package org.iit.dr.documents.word.write.practice;

import org.iit.dr.documents.word.write.common.DocumentGenerator;

/**
 * PDPDocumentGenerator.
 * 
 * @author Yuriy Karpovich
 */
public class PDPDocumentGenerator extends DocumentGenerator
{

  protected void init()
  {
    templateName = "договор-ПДП-выполнение.doc";
    fieldNames =
      new String[] {"AGREEMENT_DATE", "FIRST_SUBRECTOR", "COMP_RUK", "N_P", "FIO_STUDENT", "FAC_SPEC", "F_B", "F_N"};
    fieldValues =
      new String[] {"AGREEMENT_DATE", "FIRST_SUBRECTOR", "COMP_RUK", "N_P", "FIO_STUDENT", "FAC_SPEC", "F_B", "F_N"};
  }

  public void generate( String filePath, Object[] fieldValues )
  {

    this.fieldValues = fieldValues;

    generateDocument( filePath );
  }
}
