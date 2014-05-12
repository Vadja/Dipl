package org.iit.dr.documents.word.write.staffers;

import org.iit.dr.documents.word.write.common.DocumentGenerator;
//import org.iit.dr.utils.RemoveTrialString;

/**
 * PDPDocumentGenerator.
 * 
 * @author Yuriy Karpovich
 */
public class PR9DocumentGenerator extends DocumentGenerator
{

  protected void init()
  {
    templateName = "Форма-ПР-09.doc";
    fieldNames =
      new String[] {"RUK_FULL", "H_COUNT", "RUK_FIO", "RUK_DOB", "RUK_PASSPORT_NUM", "RUK_PASS_DATE", "RUK_PASS_ISSUE",
        "RUK_PRIV_NUM", "RUK_INS", "RUK_ADDRESS", "RUK_P_H", "RUK_P_W", "RUK_P_M", "RUK_EDU", "RUK_SPEC", "RUK_DEGREE",
        "RUK_RANK", "CH", "WORK_PLACE", "UNP", "RUK_POS"};
    fieldValues =
      new String[] {"RUK_FULL", "H_COUNT", "RUK_FIO", "RUK_DOB", "RUK_PASSPORT_NUM", "RUK_PASS_DATE", "RUK_PASS_ISSUE",
        "RUK_PRIV_NUM", "RUK_INS", "RUK_ADDRESS", "RUK_P_H", "RUK_P_W", "RUK_P_M", "RUK_EDU", "RUK_SPEC", "RUK_DEGREE",
        "RUK_RANK", "CH", "WORK_PLACE", "UNP", "RUK_POS"};
  }

  public void generate( String filePath, Object[] fieldValues )
  {

    this.fieldValues = fieldValues;

    generateDocument( filePath );
//    RemoveTrialString.removeStringInDocument(filePath);
  }
}