package com.practicetest.apiTestAutomation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	public static String getData(String scenario,String field){
		String returnValue="";
		try
        {
			String localDir = System.getProperty("user.dir");
            FileInputStream file = new FileInputStream(new File(localDir + "\\TestData.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowToSelect=0, colToSelect=0;
            for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
            	if(sheet.getRow(0).getCell(j).getStringCellValue().equalsIgnoreCase(field)) {
            		colToSelect=j;
            		break;
            	}
        	}	
            for(int i=0;i<sheet.getLastRowNum();i++) {
            	if(sheet.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase(scenario)) {
            		rowToSelect=i;
            		break;
            	}
            }	
            
            Cell cell=sheet.getRow(rowToSelect).getCell(colToSelect);
            switch (cell.getCellType()) 
            {
                case Cell.CELL_TYPE_NUMERIC:
                    double s=cell.getNumericCellValue();
                    returnValue= String.valueOf(s); 
                    break;
                case Cell.CELL_TYPE_STRING:
                	returnValue=cell.getStringCellValue();
                    break;
            }
            file.close();
            
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
		return returnValue;
	}
}
