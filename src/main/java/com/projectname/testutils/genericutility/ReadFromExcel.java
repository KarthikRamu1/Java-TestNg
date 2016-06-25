package com.projectname.testutils.genericutility;

/**
 *  		 ReadFromExcel
 * ReadFromExcel class will read the data from an excel sheet 
 * @Category 	 global 
 * @since  	     April 04, 2010
 * @Licence		 Aspire
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ReadFromExcel {
	
	/**
	 * @function 	 initiateExcelConnection
	 *   function to establish an initial connection with a work sheet
	 * @param		 workSheetName (String)
	 * @param		 sectionName (String)	
	 * @param		 workBookName (String)
	 * @return		 HSSFSheet (Work sheet) 
	 * @since  	     April 04, 2010
	 */

	public HSSFSheet initiateExcelConnection(String workSheetName, String sectionName, String workBookName){
		
		String strBasePath = null;
		String file = null;
		HSSFSheet sheet = null;
			    					
		try {
			File dir1 = new File (".");
			strBasePath=dir1.getCanonicalPath();
			file=strBasePath+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"clinical"+File.separator+"testdata"+File.separator+sectionName + File.separator + workBookName;
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			sheet = wb.getSheet(workSheetName);			
		} catch (RuntimeException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sheet;
		
	}
	
	
	/**
	 * Function To Write Data To Excel
	 * @param workBookName
	 * @param workSheetName
	 * @param sectionName
	 * @param rowPossition
	 * @param colomnPossition
	 * @param valueToOverRide
	 * @return Boolean value
	 */
	public Boolean writeDataToExcel(String sectionName,String workBookName, String workSheetName, int rowPossition, int colomnPossition, String valueToOverRide){
	
		try {
			File dir1 = new File (".");
			String strBasePath=dir1.getCanonicalPath();
			String filePath=strBasePath+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"nexia"+File.separator+"testdata"+File.separator+sectionName + File.separator + workBookName;
			 
		    FileInputStream file = new FileInputStream(new File(filePath));
		 
		    HSSFWorkbook workbook = new HSSFWorkbook(file);
		    HSSFSheet sheet = workbook.getSheet(workSheetName);
		    HSSFCell cell = null;
		 
		    //Update the value of cell
		    cell = sheet.getRow(rowPossition).getCell(colomnPossition);
		    cell.setCellValue(valueToOverRide);
		    file.close();
		     
		    FileOutputStream outFile =new FileOutputStream(new File(filePath));
		    workbook.write(outFile);
		    outFile.close();
		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return true;
	}
	
	
	
	
	
	
	
	
	/**
	 * @function 	 initiateExcelConnectionNexia
	 *   function to establish an initial connection with a work sheet
	 * @param		 workSheetName (String)
	 * @param		 sectionName (String)	
	 * @param		 workBookName (String)
	 * @return		 HSSFSheet (Work sheet) 
	 * @since  	     April 04, 2010 
	 */

	public HSSFSheet initiateExcelConnectionNexia(String workSheetName, String sectionName, String workBookName){
		
		String strBasePath = null;
		String file = null;
		HSSFSheet sheet = null;
			    					
		try {
			File dir1 = new File (".");
			strBasePath=dir1.getCanonicalPath();
			file=strBasePath+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"nexia"+File.separator+"testdata"+File.separator+sectionName + File.separator + workBookName;
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			sheet = wb.getSheet(workSheetName);			
		} catch (RuntimeException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sheet;
		
	}

	/**
	 * findRowColumnCount
	 * function to establish an initial connection with a work sheet
	 * @param		 sheet 
	 * @param		 rowColumnCount (Hashtable)	 
	 * @return		 Hashtable (returns row count and column count) 
	 * @since  	     April 04, 2010
	 */
	
public Hashtable<String, Integer> findRowColumnCount(HSSFSheet sheet, Hashtable<String, Integer> rowColumnCount){
		
		HSSFRow row = null;				
		int rows; 		
		rows = sheet.getPhysicalNumberOfRows();
		int cols = 0;
		int tmp = 0;
		int counter = 0;
		String temp = null;
		
		for(int i = 0; i < 10 || i < rows; i++) {
			row = sheet.getRow(i);
			if(row != null) {
				temp = convertHSSFCellToString(row.getCell(0));
				if(!temp.equals("")){
				counter++;
				}
				tmp = sheet.getRow(i).getPhysicalNumberOfCells();
				if(tmp > cols) cols = tmp;
			}
		}		
		
		rowColumnCount.put("RowCount", counter);
		rowColumnCount.put("ColumnCount", cols);		
		
		
		return rowColumnCount;
	}

/**
 * readExcelHeaders
 *  function to establish an initial connection with a work sheet
 * @param		 sheet 
 * @param		 excelHeaders (Hashtable)
 * @param		 rowColumnCount (Hashtable)	 
 * @return		 Hashtable (Having Header column values) 
 * @since  	     April 04, 2010
 */
	public Hashtable<String, Integer> readExcelHeaders(HSSFSheet sheet,Hashtable<String, Integer> excelHeaders,Hashtable<String, Integer> rowColumnCount){
		
		HSSFRow row = null;
		HSSFCell cell = null;
		for(int r = 0; r < rowColumnCount.get("RowCount"); r++) {
			row = sheet.getRow(r);    	        

			if(row != null) {
				for(int c = 0; c < rowColumnCount.get("ColumnCount"); c++) {	                
					cell = row.getCell(c);             
					if(cell != null) {						
						excelHeaders.put(cell.toString(), c);
					}
				}
				break;
			}	        
		}			
		return excelHeaders; 
	}
	
	/**
	 *convertHSSFCellToString
	 *   function will convert the HSSFCell type value to its equivalent string value
	 * @param		 cell
	 * @return		 String  
	 * @since  	     April 04, 2010
	 */
	public String convertHSSFCellToString(HSSFCell cell){
		String cellValue = null;	
		if(cell != null){
			cellValue = cell.toString();	
			cellValue = cellValue.trim();
		}else {
			cellValue = "";
		}		
		return cellValue;
	}
}

