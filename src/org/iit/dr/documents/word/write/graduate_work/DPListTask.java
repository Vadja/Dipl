package org.iit.dr.documents.word.write.graduate_work;

import org.iit.dr.documents.word.write.common.DocumentGenerator;
//import org.iit.dr.utils.RemoveTrialString;

public class DPListTask extends DocumentGenerator {

	@Override
	protected void init() {
		templateName = "DP_task_list_AI_2010.doc";
	    fieldNames = new String[] {
	    		"SURNAME", 
	    		"NAME", 
	    		"PATRONYMIC",
	    		"THEME",
	    		"YEAR",
	    		"ISSUE",
	    		"FIO"
	    		};
	    
	    fieldValues = new String[] {
	    		"SURNAME", 
	    		"NAME", 
	    		"PATRONYMIC",
	    		"THEME",
	    		"YEAR",
	    		"ISSUE",
	    		"FIO"
	    		};
	}
	
	public void generate( String filePath, Object[] fieldValues )
	  {

	    this.fieldValues = fieldValues;

	    generateDocument( filePath );
//	    RemoveTrialString.removeStringInDocument(filePath);
	  }

}
