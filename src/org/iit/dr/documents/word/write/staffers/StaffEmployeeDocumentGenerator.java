package org.iit.dr.documents.word.write.staffers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
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
import org.iit.dr.data_model.RateType;
import org.iit.dr.data_model.StaffRate;
import org.iit.dr.services.RateService;
import org.iit.dr.services.StaffRateService;
import org.iit.dr.utils.FileUtils;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;

import com.aspose.words.Document;

public class StaffEmployeeDocumentGenerator {

	public static final String templateName = "Штатная расстановка сотрудников.docx";

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

	public void generate(Date date) {
		try {
			String sDate = dateFormat.format(date);
			String fileName = "Штатная расстановка сотрудников-" + sDate
					+ ".docx";
			String filePath = FileUtils.getCommonDocumentPath(fileName);
			Document doc;
			doc = new Document(FileUtils.getTemplate(templateName));
			doc.getMailMerge().execute(new String[] { "DATE" },
					new String[] { sDate });
			doc.save(filePath);
			InputStream fileStream = new FileInputStream(filePath);
			XWPFDocument document = new XWPFDocument(fileStream);
			document.removeBodyElement(0);
			XWPFTable tableP = document.getTables().get(0);
			XWPFTable tableB = document.getTables().get(1);
			List<StaffRate> staffRateList = StaffRateService.getStaffRateList();

			XWPFTableRow row;
			int i = 1;
			int j = 1;
			double sum2P = 0;
			double sum2B = 0;
			double sum3P = 0;
			double sum3B = 0;
		//	double sum6P = 0;
		//	double sum6B = 0;
			Locale.setDefault(Locale.ENGLISH);
			for (StaffRate staffRateItem : staffRateList) {
				if (staffRateItem.getAmountP() > 0) {
					row = tableP.createRow();
					XWPFParagraph p0 = new XWPFParagraph(
							CTP.Factory.newInstance(), row.getCell(0));

					p0.setAlignment(ParagraphAlignment.RIGHT);
					XWPFRun r0 = p0.createRun();
					r0.setText(Integer.toString(i) + ".");
					row.getCell(0).setParagraph(p0);
					row.getCell(1).setText(
							staffRateItem.getPosition().getStaffScheduleName());
					row.getCell(2).setText(
							String.format("%.2f", staffRateItem.getAmountP()));
					double staffAmountP = RateService.calculateRate(RateType.OFF_BUDGETARY, staffRateItem.getPosition(), date);
					
					row.getCell(3).setText(
							String.format("%.2f", staffAmountP));
					double vakP = ((staffRateItem.getAmountP() - staffAmountP) <= 0) ? 0 : (staffRateItem.getAmountP() - staffAmountP);
					if (vakP != 0){
						row.getCell(6).setText(
								String.format("%.2f", vakP));
						} else {
							row.getCell(6).setText("-");
						}
					sum2P += staffRateItem.getAmountP();
					sum3P += staffAmountP;
					//sum6P += vakP;
					i++;
				}
				if (staffRateItem.getAmountB() > 0) {
					row = tableB.createRow();
					XWPFParagraph p0 = new XWPFParagraph(
							CTP.Factory.newInstance(), row.getCell(0));

					p0.setAlignment(ParagraphAlignment.RIGHT);
					XWPFRun r0 = p0.createRun();
					r0.setText(Integer.toString(j) + ".");
					row.getCell(0).setParagraph(p0);
					row.getCell(1).setText(
							staffRateItem.getPosition().getStaffScheduleName());
					row.getCell(2).setText(
							String.format("%.2f", staffRateItem.getAmountB()));
					double staffAmountB = RateService.calculateRate(
							RateType.BUDGETARY, staffRateItem
							.getPosition(), date);
					row.getCell(3).setText(
							String.format("%.2f", staffAmountB));
					
					double vakB = ((staffRateItem.getAmountB() - staffAmountB) <= 0) ? 0 : (staffRateItem.getAmountB() - staffAmountB);
					if (vakB != 0){
					row.getCell(6).setText(
							String.format("%.2f", vakB));
					} else {
						row.getCell(6).setText("-");
					}
					sum2B += staffRateItem.getAmountB();
					sum3B += staffAmountB;
				//	sum6B += vakB;
					j++;
				}
			}
			row = tableP.createRow();
			row.getCell(1).setText("каф. ИИТ:");
			row.getCell(2).setText(String.format("%.2f", sum2P));
			row.getCell(3).setText(String.format("%.2f", sum3P));
			row.getCell(6).setText(String.format("%.2f", sum2P-sum3P));
			row = tableP.createRow();
			row.getCell(1).setText("профес.-препод.:");
			row.getCell(2).setText(String.format("%.2f", sum2P));
			row.getCell(3).setText(String.format("%.2f", sum3P));
			row.getCell(6).setText(String.format("%.2f", sum2P-sum3P));
			
			row = tableB.createRow();
			row.getCell(1).setText("каф. ИИТ:");
			row.getCell(2).setText(String.format("%.2f", sum2B));
			row.getCell(3).setText(String.format("%.2f", sum3B));
			row.getCell(6).setText(String.format("%.2f", sum2B-sum3B));
			row = tableB.createRow();
			row.getCell(1).setText("профес.-препод.:");
			row.getCell(2).setText(String.format("%.2f", sum2B));
			row.getCell(3).setText(String.format("%.2f", sum3B));
			row.getCell(6).setText(String.format("%.2f", sum2B-sum3B));
			
			fileStream.close();
			FileOutputStream fileOutStream = new FileOutputStream(filePath);
			document.write(fileOutStream);
			fileOutStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ошибка. Возможно файл занят другим процессом.", null, JOptionPane.ERROR_MESSAGE);
		}
	}

}
