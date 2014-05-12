package org.iit.dr.utils;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class OpenFileFilter extends FileFilter {

	 public String fileExt = "";
	    String txtExt = ".txt";

	    public OpenFileFilter() {
	        this(".pxml");  //default file type extension.
	    }

	    public OpenFileFilter(String extension) {
	        fileExt = extension;
	    }

	     @Override public boolean accept(File f) {
	        if (f.isDirectory())
	            return true;
	        return  (f.getName().toLowerCase().endsWith(fileExt)); 
	    }

	    public String getDescription() {
	        if(fileExt.equals(txtExt ))
	            return  "Text Files (*" + fileExt + ")";
	        else
	            return ("Other File");
	    }

}
