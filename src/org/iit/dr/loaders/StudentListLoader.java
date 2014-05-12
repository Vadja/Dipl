package org.iit.dr.loaders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Sheet;
import org.iit.dr.data_model.role.Student;
import org.iit.dr.data_model.unit.OrganizationUnit;
import org.iit.dr.data_model.unit.UnitType;
import org.iit.dr.services.OrganizationUnitService;
import org.iit.dr.services.StudentsService;
import org.iit.dr.utils.UUIDUtils;

public class StudentListLoader {

	@SuppressWarnings("deprecation")
	public void loadData(String filepath) {
		try {
			InputStream in = new FileInputStream(filepath);
			HSSFWorkbook wb = new HSSFWorkbook(in);
			HSSFSheet sheet = wb.getSheetAt(0);

			Pattern pattern = Pattern.compile("\\d+");
			Matcher matcher = pattern.matcher(sheet.getRow(0).getCell(0)
					.getStringCellValue());
			matcher.find();
			String groupName = matcher.group();

			StringBuffer text = new StringBuffer();
			for (int j = 7; j < sheet.getLastRowNum(); j++) {
				System.out.print(j);
				System.out.print("\n");
				String cellText = sheet.getRow(j).getCell(1)
						.getStringCellValue();
				if (!cellText.equals("")) {
					text.append(cellText);
					text.append("\n");
				} else {
					break;
				}
			}

			String ouId = OrganizationUnitService.getOrganizationUnitByName(
					groupName, UnitType.GROUP);
			if (ouId == null) {
				OrganizationUnit ou = new OrganizationUnit();
				ou.setId(UUIDUtils.getUUID());
				ou.setType(UnitType.GROUP);
				ou.setName(groupName);
				OrganizationUnitService.getOrganizationUnitList().add(ou);
				ouId = ou.getId();
			} else {
				int dialogResult = JOptionPane.showConfirmDialog (null, "Группа " + groupName + " уже существует. Продолжить импорт?", "Warning", JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.NO_OPTION){
					return;
				}
			}
			String[] students = text.toString().split("\n");
			for (int i = 0; i < students.length; i++) {
				String[] fio = students[i].split("\\s");
				if (fio.length == 3) {
					Student student = new Student();
					student.setLastName(fio[0]);
					student.setFirstName(fio[1]);
					student.setPatronymicName(fio[2]);
					student.setGroup(ouId);
					student.setId(UUIDUtils.getUUID());
					StudentsService.getStudentList().add(student);
				} else {
					System.out.println("NOT RUSSIAN NAME!");
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