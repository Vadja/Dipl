package org.iit.dr.loaders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.iit.dr.data_model.GraduateWork;
import org.iit.dr.data_model.role.PartTimeStaffer;
import org.iit.dr.data_model.role.Staffer;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.data_model.unit.OrganizationUnit;
import org.iit.dr.data_model.unit.UnitType;
import org.iit.dr.services.GraduateWorkService;
import org.iit.dr.services.OrganizationUnitService;
import org.iit.dr.services.StudentsService;
import org.iit.dr.services.TeachersService;
import org.iit.dr.utils.UUIDUtils;
import org.iit.dr.view.model.table.graduate_work.GraduateWorkTableModel;

public class ThemeGraduationProjectLoader {

	GraduateWorkTableModel model;
	
	public void loadData(String filepath, Object parent) {
		
		try {
			model = ( GraduateWorkTableModel ) parent;
			
			
			
			InputStream in = new FileInputStream(filepath);
			POIFSFileSystem fs = new POIFSFileSystem(in);  
		    HWPFDocument doc = new HWPFDocument(fs);  
			
		    Range range = doc.getRange();  
		    Paragraph tablePar;
		    
		    for(int i=0; i<range.numParagraphs();i++){
          	  System.out.print(range.numParagraphs());
		    	tablePar = range.getParagraph(i);
		    	
			    if (tablePar.isInTable()) {
			    	  boolean endTable = false;
			          Table table = range.getTable(tablePar);
			          i=i+table.numParagraphs()-4;
			          for (int rowIdx=1; rowIdx<table.numRows(); rowIdx++) {
			              TableRow row = table.getRow(rowIdx);   
			              
			              String numPrikaz = null;
			              String[] fioStudent;
			              String theme = null;
			              String[] fioManger;
			              String workManger = null;
			              String positionManger = null;
			              String[] fioConsultant;
			              String positionConsultant;
			              Student stud = null;
			              String mangerID="";
			              String consultantID="";
			              
			              for (int colIdx=0; colIdx<row.numCells(); colIdx++) {
			                  TableCell cell = row.getCell(colIdx);
			                  if(colIdx==0){
			                	  numPrikaz = String.valueOf(rowIdx);
			                  }
			                  if(colIdx==1){
			                	  fioStudent = cell.getParagraph(0).text().split("\\s");
			                	  if (fioStudent.length == 3) {
			      					stud = StudentsService.getStudentLastname(fioStudent[0]);
			      					if(fioStudent[0].equals("Русецкий")){
			      						 System.out.println("NOT RUSSIAN NAME!");
			      					}
			                	  } else {
			                		  System.out.println("NOT RUSSIAN NAME!");
			                	  }
			                  }
			                  if(colIdx==2){
			                	  String str =cell.getParagraph(0).text();
			                	  theme = str.substring(0, str.length()-1);
			                  }
			                  if(colIdx==3&&row.numCells()!=4){
				                	  String[] temp = cell.getParagraph(0).text().split(",");
				                	  String[] tempFio = temp[0].split("\\s");
				                	  String[] tempInitial = tempFio[1].split("\\p{Punct}");
				                	  fioManger = new String[3];
				                	  fioManger[0] = tempFio[0];
				                	  fioManger[1] = tempInitial[0];
				                	  fioManger[2] = tempInitial[1];
				                	  if(temp.length>1){
					                	  workManger = temp[1];
					                	  if(temp.length<3){
					                		  workManger = workManger.substring(0, workManger.length()-1);
					                	  }
				                	  } else workManger = "";
				                	  if(temp.length>2){
					                	  positionManger = temp[2];
					                	  positionManger = positionManger.substring(0, positionManger.length()-1);
				                	  }else positionManger="";
				                	  
				                	  if(TeachersService.getTeacherLastname(fioManger[0])!=null){
				                		  mangerID = TeachersService.getTeacherLastname(fioManger[0]).getId();
				                	  }else{
				                		  PartTimeStaffer manger = new PartTimeStaffer();
				                		  manger.setId(UUIDUtils.getUUID());
				                		  manger.setLastName(fioManger[0]);
				                		  manger.setFirstName(fioManger[1]);
				                		  manger.setPatronymicName(fioManger[2]);
				                		  manger.setOriginalPosition(positionManger);
				                		  manger.setWorkPlace(workManger);
				                		  mangerID = manger.getId();
				                		  TeachersService.getTeacherList().add(manger);
				                	  }
			                	  }
			                  
				                  if(colIdx==4||(colIdx==3&&row.numCells()==4)){
				                	  String[] temp = cell.getParagraph(0).text().split(",");
				                	  String[] tempFio = temp[0].split("\\s");
				                	  String[] tempInitial = tempFio[1].split("\\p{Punct}");
				                	  fioConsultant = new String[3];
				                	  fioConsultant[0] = tempFio[0];
				                	  fioConsultant[1] = tempInitial[0];
				                	  fioConsultant[2] = tempInitial[1];
				                	  if(temp.length>1){
					                	  positionConsultant = temp[1];
					                	  positionConsultant = positionConsultant.substring(0, positionConsultant.length()-1);
				                	  } else positionConsultant="";
					                  if(TeachersService.getTeacherLastname(fioConsultant[0])!=null){
					                	  consultantID = TeachersService.getTeacherLastname(fioConsultant[0]).getId();
				                	  }else{
				                		  Staffer consultant = new Staffer();
				                		  consultant.setId(UUIDUtils.getUUID());
				                		  consultant.setLastName(fioConsultant[0]);
				                		  consultant.setFirstName(fioConsultant[1]);
				                		  consultant.setPatronymicName(fioConsultant[2]);
				                		  consultantID = consultant.getId();
				                		  TeachersService.getTeacherList().add(consultant);
				                	  }
					                  if(mangerID==""){
					                	  mangerID = TeachersService.getTeacherLastname(fioConsultant[0]).getId();
					                  }
				                  }
			                  }
  
			          		GraduateWork graduateWork = new GraduateWork();
							graduateWork.setNumber(numPrikaz);
							graduateWork.setStudentId(stud.getId());
							graduateWork.setTitle(theme);
							graduateWork.setDescription("");
							graduateWork.setManagerId( mangerID);
							graduateWork.setConsultantIdBySpeciality(consultantID);
							
							graduateWork.setConsultantIdByEconomics("");
					        graduateWork.setConsultantIdByProtectionOfLabor("");
					        graduateWork.setConsultantIdByNormalInspection("");
					        graduateWork.setReviewerId("");
					        graduateWork.setDateOfIssue( new Date() );
							
							if( graduateWork.getId() == null )
							{
							  graduateWork.setId( UUIDUtils.getUUID() );
							  GraduateWorkService.getGraduateWorkList().add( graduateWork );
							}
			              if(rowIdx==table.numRows()-1){
			            	  endTable = true;
			            	  break;
			              }
				    	if (tablePar.isTableRowEnd()){
				    		System.out.print("blablabla");
				    	}
			          }
			          if(endTable){
			        	 break; 
			          }
			      }  
		    }
		} catch (FileNotFoundException e) {
			System.out.println("file not found.");
		} catch (IOException e) {
			System.out.println("IO exception.");
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Ошибка. Возможно вы выбрали файл неправильного формата.", null, JOptionPane.ERROR_MESSAGE);
		}
	}
}
