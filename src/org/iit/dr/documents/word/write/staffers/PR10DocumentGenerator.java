package org.iit.dr.documents.word.write.staffers;

import org.iit.dr.documents.word.write.common.DocumentGenerator;

/**
 * PR10DocumentGenerator.
 * <p/>
 * $Id: PR10DocumentGenerator.java, v 1.0 11.06.2010 15:37:06 ykarpovich Exp $ Created on 11.06.2010 15:37:06
 */
public class PR10DocumentGenerator extends DocumentGenerator
{

  protected void init()
  {
    templateName = "Форма-ПР-АПР-10.doc";
    fieldNames =
      new String[] {"RUK_FULL", "RUK_FIO", "RUK_DOB", "RUK_PASSPORT_NUM", "RUK_PASS_DATE", "RUK_PASS_ISSUE",
        "RUK_PRIV_NUM", "RUK_INS", "RUK_ADDRESS", "RUK_P_H", "RUK_P_W", "RUK_P_M", "RUK_EDU", "RUK_SPEC", "RUK_DEGREE",
        "RUK_RANK", "CH", "WORK_PLACE", "UNP", "RUK_POS", "PROREKTOR_1", "PROREKTOR_2", "RUK_FULL_TOP", "RUK_DATA",
        "PENSION_BOOK", "PENSION_AGENCY"};
    fieldValues =
      new String[] {"RUK_FULL", "RUK_FIO", "RUK_DOB", "RUK_PASSPORT_NUM", "RUK_PASS_DATE", "RUK_PASS_ISSUE",
        "RUK_PRIV_NUM", "RUK_INS", "RUK_ADDRESS", "RUK_P_H", "RUK_P_W", "RUK_P_M", "RUK_EDU", "RUK_SPEC", "RUK_DEGREE",
        "RUK_RANK", "CH", "WORK_PLACE", "UNP", "RUK_POS", "PROREKTOR_1", "PROREKTOR_2", "RUK_FULL_TOP", "RUK_DATA",
        "PENSION_BOOK", "PENSION_AGENCY"};
  }

  public void generate( String filePath, Object[] fieldValues )
  {

    this.fieldValues = fieldValues;

    generateDocument( filePath );
  }
}
