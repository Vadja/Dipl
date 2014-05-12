package org.iit.dr.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class RemoveTrialString {
	public static void removeStringInDocument(String path){
		try {
			FileInputStream is = new FileInputStream(path);
//			XWPFDocument document = new XWPFDocument(is);
//			document.removeBodyElement(0);
			HWPFDocument document= new HWPFDocument(is);
			document.delete(0, 80);
			
			FileOutputStream fileOutStream = new FileOutputStream(path);
			document.write(fileOutStream);
			fileOutStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
