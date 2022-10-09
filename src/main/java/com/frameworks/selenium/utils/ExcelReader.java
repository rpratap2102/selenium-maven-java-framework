package com.frameworks.selenium.utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.frameworks.selenium.exceptions.FrameworkException;

public class ExcelReader {
	public static Object[][] readExcelSheet(String fileName, String className, String testName) {
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		try (FileInputStream inputstream = new FileInputStream(
				(Constants.TEST_DATA_PATH + fileName + Constants.EXCEL_FILE_EXTENSION));
				XSSFWorkbook excelsheet = new XSSFWorkbook(inputstream);) {
			Sheet testClassSheet = excelsheet.getSheet(className);
			testClassSheet.forEach(row -> {
				ArrayList<String> iteration = new ArrayList<String>();
				Iterator<Cell> cells = row.cellIterator();
				if (cells.next().getStringCellValue().equals(testName)) {
					while (cells.hasNext()) {
						iteration.add(readCellAsString(cells.next()));
					}
					data.add(iteration);
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return convertArrayToObj(data);
	}

	public static String readCellAsString(Cell cell) {
		switch (cell.getCellTypeEnum()) {
		case NUMERIC:
			return String.valueOf(cell.getNumericCellValue());
		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		case STRING:
			return cell.getStringCellValue();
		default:
			throw new FrameworkException("Unsopported type data in excell");
		}
	}
	
	private static Object[][] convertArrayToObj ( ArrayList<ArrayList<String>> data) {
		Object[][] params = new Object[data.size()][data.get(0).size()];
		for (int row = 0; row < data.size(); row++) {
			for (int coll = 0; coll < data.get(row).size(); coll++) {
				params[row][coll] = data.get(row).get(coll);
			}
		}
		return params;

	}
}
