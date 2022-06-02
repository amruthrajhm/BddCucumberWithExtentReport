package managers;

import java.io.File;

import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDataManager {
	
	public static String dataFileName;
	public static void SetTestDataFile(String fileName)
	{
		TestDataManager.dataFileName=fileName;
		
	}
	//returns datafile name
	public String getFileName()
	{
		return dataFileName;
	}
	
	/* Get the test Data from the test data File*/
	//uses absolute row number , row starts from 0
	/*returns hash map with key value pairs, columnName, ColumnValue*/
	public HashMap<String, String> getTestData(String sheetName,int rowNumber)
	{
		HashMap<String, String> RowItems=new HashMap<String, String>();
		String sDir=System.getProperty("user.dir");
		System.out.println(sDir);
		System.out.println(Paths.get("").toAbsolutePath().toString());
		String filePath=sDir+"\\src\\test\\resources\\TestData\\"+getFileName()+".xlsx";
		filePath=filePath.toString();
		File tdObj=new File(filePath);
		//creating Workbook instance that refers to .xlsx file 
		try
		{
		FileInputStream fis=new FileInputStream(tdObj);
		XSSFWorkbook workBook=new XSSFWorkbook(fis);
		//workBook.getCreationHelper().createFormulaEvaluator().evaluateAll();//not working,,evaluate all formulas
		XSSFSheet tdSheet=workBook.getSheet(sheetName);
		//XSSFFormulaEvaluator.evaluateAllFormulaCells(workBook);
		
		
		Row firstRow=tdSheet.getRow(0);
		Row tdRow=tdSheet.getRow(rowNumber);
		System.out.println(tdRow.getPhysicalNumberOfCells());
	/*	this code wont work if blank cell is present
		for (int j = 0; j < tdRow.getPhysicalNumberOfCells(); j++) {
	        final Cell cell = tdRow.getCell(j);
	       
	        // do stuff to each cell here...
	        String	tmpCellValue=tdRow.getCell(j).toString();
	        int columnNumber=cell.getColumnIndex();
	        System.out.println("columnNUm:"+columnNumber);
	        if(!tmpCellValue.isBlank())
			{
				String columnName=firstRow.getCell(columnNumber).toString();
				RowItems.put(columnName, tmpCellValue);
				System.out.println(columnName+","+tmpCellValue);
			}
	    }
	    */
		Iterator<Cell> cellIterator = tdRow.cellIterator();
		while (cellIterator.hasNext()) {
	         Cell iCell = cellIterator.next();
	         int columnNumber=iCell.getColumnIndex();
	         String columnName=firstRow.getCell(columnNumber).toString();
	         
	         //System.out.println(iCell.getCellType());
	         switch (iCell.getCellType()) {
	           case STRING:
	            // System.out.print(iCell.getStringCellValue().toString());
	             
				 RowItems.put(columnName, iCell.getStringCellValue().toString());
	             break;
	           case NUMERIC:
	        	   String s=String.valueOf(iCell.getNumericCellValue());
	        	   RowItems.put(columnName, s);
	             break;
	           case BOOLEAN:
	             //System.out.print(iCell.getBooleanCellValue());
	             String sB=String.valueOf(iCell.getBooleanCellValue());
	        	   RowItems.put(columnName, sB);
	             break;
	           case BLANK:
		          System.out.print(iCell.getBooleanCellValue());
		          RowItems.put(columnName, "EMPTY");
		             break; 
	          
	           default :
	         }
	       }
		//close the work book ,input stream
		workBook.close();
		fis.close();
		
		}
		catch (Exception e)
		{
			System.out.println("exception occured during reading data from XL");
		}
		return RowItems;
		}
	

}
