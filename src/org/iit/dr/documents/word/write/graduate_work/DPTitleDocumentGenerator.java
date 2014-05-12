package org.iit.dr.documents.word.write.graduate_work;

import org.iit.dr.documents.word.write.common.DocumentGenerator;
//import org.iit.dr.utils.RemoveTrialString;

/**
 * DPTitleDocumentGenerator.
 * 
 * @author Yuriy Karpovich
 */
public class DPTitleDocumentGenerator extends DocumentGenerator
{

  protected void init()
  {
    templateName = "DP_title_AI_2010.doc";
    fieldNames = new String[] {"NN", "TITLE", "STUDENT", "MANAGER", "SPEC", "ECONOM", "OHR", "NORMCONTR", "REV", "YEAR"};
    fieldValues = new String[] {"NN", "TITLE", "STUDENT", "MANAGER", "SPEC", "ECONOM", "OHR", "NORMCONTR", "REV", "YEAR"};
  }

  public void generate( String filePath, Object[] fieldValues )
  {

    this.fieldValues = fieldValues;

    generateDocument( filePath );
//    RemoveTrialString.removeStringInDocument(filePath);
  }
}