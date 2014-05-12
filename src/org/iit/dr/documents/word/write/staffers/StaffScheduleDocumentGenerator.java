package org.iit.dr.documents.word.write.staffers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JOptionPane;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.iit.dr.data_model.Rate;
import org.iit.dr.data_model.RateType;
import org.iit.dr.data_model.comparators.RateByFioTeacherComparator;
import org.iit.dr.data_model.role.Teacher;
import org.iit.dr.services.RateService;
import org.iit.dr.services.TeachersService;
import org.iit.dr.utils.FileUtils;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;

import com.aspose.words.Document;

public class StaffScheduleDocumentGenerator {

	public static final String templateName = "Связь со штатным расписанием.docx";

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	
	private String sDate;

	public void generate(Date date) {
		sDate = dateFormat.format(date);
		String fileNameB = "Связь со штатным расписанием(б)-" + sDate + ".docx";
		String fileNameP = "Связь со штатным расписанием(п)-" + sDate + ".docx";
		String filePathB = FileUtils.getCommonDocumentPath(fileNameB);
		String filePathP = FileUtils.getCommonDocumentPath(fileNameP);
		try {
			generate(RateType.BUDGETARY, filePathB, date);
			generate(RateType.OFF_BUDGETARY, filePathP, date);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ошибка. Возможно файл занят другим процессом.", null, JOptionPane.ERROR_MESSAGE);
		}
	}

	private void generate(RateType rateType, String filePath, Date date) throws Exception {
		Document doc;
		doc = new Document(FileUtils.getTemplate(templateName));
		String[] array;
		switch (rateType) {
		case BUDGETARY:
			array = new String[] { sDate, "(бюджет)" };
			break;
		case OFF_BUDGETARY:
			array = new String[] { sDate, "(платн.)" };
			break;

		default:
			throw new Exception();
		}
		doc.getMailMerge().execute(new String[] { "DATE", "TYPE" },
				array);
		doc.save(filePath);
		InputStream fileStream = new FileInputStream(filePath);
		XWPFDocument document = new XWPFDocument(fileStream);
		XWPFTable table = document.getTables().get(0);
		document.removeBodyElement(0);
		List<Rate> rateList = RateService.getRateByDateRateType(date,
				rateType);
		Collections.sort(rateList, new RateByFioTeacherComparator());
		System.out.println(rateList.size());
		XWPFTableRow row;
		Teacher teacher;
		int i = 1;
		Locale.setDefault(Locale.ENGLISH);
		for (Rate rateItem : rateList) {
			teacher = TeachersService.getTeacher(rateItem.getTeacher());
			row = table.createRow();

			XWPFParagraph p0 = new XWPFParagraph(CTP.Factory.newInstance(),
					row.getCell(0));

			p0.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun r0 = p0.createRun();
			r0.setText(Integer.toString(i) + ".");
			row.getCell(0).setParagraph(p0);

			row.getCell(1).setText(teacher.getFullName());
			row.getCell(2).setText(teacher.getPositionName());

			XWPFParagraph p3 = new XWPFParagraph(CTP.Factory.newInstance(),
					row.getCell(3));
			p3.setAlignment(ParagraphAlignment.CENTER);
			String type;
			if (teacher.isContract()) {
				type = "Контракт";
			} else {
				type = "Срочный трудовой договор";
			}

			p3.createRun().setText(
					type + " c "
							+ dateFormat.format(teacher.getRange().getStart())
							+ " по "
							+ dateFormat.format(teacher.getRange().getEnd()));
			row.getCell(3).setParagraph(p3);

			XWPFParagraph p4 = new XWPFParagraph(CTP.Factory.newInstance(),
					row.getCell(4));
			p4.setAlignment(ParagraphAlignment.CENTER);
			
			p4.createRun().setText(String.format("%.2f", rateItem.getAmount()));
			row.getCell(4).setParagraph(p4);
			i++;
		}

		fileStream.close();
		FileOutputStream fileOutStream = new FileOutputStream(filePath);
		document.write(fileOutStream);
		fileOutStream.close();
	}
}
