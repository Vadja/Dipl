package org.iit.dr.documents.word.write.graduate_work;

import org.iit.dr.documents.word.write.common.DocumentGenerator;
//import org.iit.dr.utils.RemoveTrialString;

public class DPApplicationApprovalTopics extends DocumentGenerator {

	@Override
	protected void init() {
		templateName = "Заявление на утверждение темы ДП.doc";
	    fieldNames = new String[] {
	    		"GROUP",
	    		"SURNAME", 
	    		"NAME", 
	    		"PATRONYMIC", 
	    		"ADRESS",
	    		"PHONE", 
	    		"MOBILEPHONE", 
	    		"EMAIL", 
	    		"THEME",
	    		"DAY",
	    		"MONTH",
	    		"YEAR",
	    		"LEADSURNAME",
	    		"LEADERNAME",
	    		"LEADERPATRONYMIC",
	    		"POSITION",
	    		"DEGREE",
	    		"RANK",
	    		"LEADERADRESS",
	    		"LEADERPHONE"
	    		};
	    
	    fieldValues = new String[] {
	    		"GROUP",
	    		"SURNAME", 
	    		"NAME", 
	    		"PATRONYMIC", 
	    		"ADRESS",
	    		"PHONE", 
	    		"MOBILEPHONE", 
	    		"EMAIL", 
	    		"THEME",
	    		"DAY",
	    		"MONTH",
	    		"YEAR",
	    		"LEADSURNAME",
	    		"LEADERNAME",
	    		"LEADERPATRONYMIC",
	    		"POSITION",
	    		"DEGREE",
	    		"RANK",
	    		"LEADERADRESS",
	    		"LEADERPHONE"
	    		};
	}
	
	public void generate( String filePath, Object[] fieldValues )
	  {

	    this.fieldValues = fieldValues;

	    generateDocument( filePath );
//	    RemoveTrialString.removeStringInDocument(filePath);
	  }

}
