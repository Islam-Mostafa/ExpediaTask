package Utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {


	private Workbook workBook;

	private Workbook getWorkBook(String filePath) throws IOException {

		if (workBook == null) {
			workBook = new XSSFWorkbook(filePath);
		}
		return workBook;
	}

	public ArrayList<String> getData(String filePath, String sheetName,String rowname)
			throws IOException {

		workBook = getWorkBook(filePath);
		Sheet sheet = workBook.getSheet(sheetName);
		ArrayList<String> data = new ArrayList<String>();

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row myrow = sheet.getRow(i);

			for (int j = 1; j < myrow.getLastCellNum(); j++) {
				String x = myrow.getCell(j).getStringCellValue();
				data.add(myrow.getCell(j).getStringCellValue());
			}
		}
		return data;
	}
	public Map<String,String>  getData2(String filePath, String sheetName,String rowname)
			throws IOException {

		workBook = getWorkBook(filePath);
		Sheet sheet = workBook.getSheet(sheetName);
		Map<String,String> data = new HashMap<String,String>();

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row myrow = sheet.getRow(i);
			String name=sheet.getRow(i).getCell(0).getStringCellValue();
			if(name.equals(rowname))
			{
			for (int j = 1; j < myrow.getLastCellNum(); j++) {
				//String x = myrow.getCell(j).getStringCellValue();
				DataFormatter formatter = new DataFormatter();
				//String val = formatter.formatCellValue(sheet.getRow(row).getCell(col));
				data.put(formatter.formatCellValue(sheet.getRow(0).getCell(j)),formatter.formatCellValue(myrow.getCell(j)));
			}
		}
			}
		return data;
	}

	public HashMap<String, ArrayList<String>> getDataAsMap(String filePath,
			String sheetName) throws IOException {
		workBook = getWorkBook(filePath);
		Sheet sheet = workBook.getSheet(sheetName);
		HashMap<String, ArrayList<String>> data = new HashMap<String, ArrayList<String>>();

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row myrow = sheet.getRow(i);
			ArrayList<String> rowRecords = new ArrayList<String>();
			
			for (int j = 1; j <= myrow.getLastCellNum(); j++) {
				rowRecords.add(myrow.getCell(j).getStringCellValue());
			}

			data.put(myrow.getCell(0).getStringCellValue(), rowRecords);

		}

		return data;
	}
}