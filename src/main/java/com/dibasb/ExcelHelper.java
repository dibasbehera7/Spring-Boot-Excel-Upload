package com.dibasb;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ExcelHelper {

	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "Id", "Title", "Description", "ActiveYN" };
	static String SHEET = "Tutorials";

	public static boolean hasExcelFormat(MultipartFile file) {
		if (!TYPE.equals(file.getContentType())) {
			return false;
		}
		return true;
	}

	public static List<MasterData> excelToMasterData(InputStream is) {

		try {

			Workbook workbook = new XSSFWorkbook(is);
			Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> rows = sheet.iterator();

			List<MasterData> masterDatas = new ArrayList<MasterData>();

			 int rowNumber = 0;
			 
			 while (rows.hasNext()) {
				 Row currentRow = rows.next();
				 
				 	// skip header
			        if (rowNumber == 0) {
			          rowNumber++;
			          continue;
			        }
			        
			        Iterator<Cell> cellsInRow = currentRow.iterator();
			        MasterData masterData = new MasterData();
			        
			        int cellIdx = 0;
			        
			        while (cellsInRow.hasNext()) {
			        	
			        	Cell currentCell = cellsInRow.next();
			        	
			        	switch (cellIdx) {
			            case 0:
			            	masterData.setId((long) currentCell.getNumericCellValue());
			              break;

			            case 1:
			            	masterData.setTitle(currentCell.getStringCellValue());
			              break;

			            case 2:
			            	masterData.setDescription(currentCell.getStringCellValue());
			              break;

			            case 3:
			            	masterData.setActiveYn(currentCell.getStringCellValue());
			              break;

			            default:
			              break;
			            }
			        	
			        	cellIdx++;
			        }
			        
			        masterDatas.add(masterData);
			 }
			
			workbook.close();
			return masterDatas;

		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}

}
