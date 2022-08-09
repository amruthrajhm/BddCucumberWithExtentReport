package managers;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDataManagerNew {
	public static String dataFileName;
	public static void SetTestDataFile(String fileName)
	{
		TestDataManagerNew.dataFileName=fileName;
	}
	//returns datafile name
	public String getFileName()
	{
		return dataFileName;
	}
	public XSSFWorkbook getWorkBook()
	{
		XSSFWorkbook workBook=null;
		HashMap<String, String> RowItems=new HashMap<String, String>();
		String sDir=System.getProperty("user.dir");
		System.out.println(sDir);
		System.out.println(Paths.get("").toAbsolutePath().toString());
		String filePath=sDir+getFileName()+".xlsx";
		filePath=filePath.toString();
		File tdObj=new File(filePath);
		//creating Workbook instance that refers to .xlsx file  
		try
		{
		FileInputStream fis=new FileInputStream(tdObj);
		 workBook=new XSSFWorkbook(fis);
		 System.out.println("get workbook done");
// fis.close();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return workBook;
	}
	
	/* Get the test Data from the test data File*/
	//uses absolute row number , row starts from 0
	/*returns hash map with key value pairs, columnName, ColumnValue*/
	
	public int getRowIndex(XSSFWorkbook workBook , String sheetName,String RowNumber)
	{
		//XSSFWorkbook workBook=getWorkBook();
		int iRowNumber=0;
		XSSFSheet tdSheet=workBook.getSheet(sheetName);
		int rowTotal=tdSheet.getLastRowNum()+1;
		System.out.println(rowTotal);
		for (int i = 0; i < rowTotal; i++) {
				String temp=tdSheet.getRow(i).getCell(0).getStringCellValue();
				System.out.println(temp);
				if (temp.equals(RowNumber))
				{
					iRowNumber=tdSheet.getRow(i).getRowNum();
					break;
				}
				
			
		}
		return iRowNumber;
	}
	public HashMap<String, String> getTestData(String sheetName,String rowNumber)
	{
		int iRowIndex;
		HashMap<String, String> RowItems=new HashMap<String, String>();
		
		//creating Workbook instance that refers to .xlsx file 
		try
		{
		
		XSSFWorkbook workBook=getWorkBook();
		 iRowIndex=getRowIndex( workBook ,  sheetName, rowNumber);
		 XSSFFormulaEvaluator formulaEvaluator =   workBook.getCreationHelper().createFormulaEvaluator();
//workBook.getCreationHelper().createFormulaEvaluator().evaluateAll();//not working,,evaluate all formulas
		XSSFSheet tdSheet=workBook.getSheet(sheetName);
		//XSSFFormulaEvaluator.evaluateAllFormulaCells(workBook);
		
		
		Row firstRow=tdSheet.getRow(0);
		Row tdRow=tdSheet.getRow(iRowIndex);

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
	         System.out.println(iCell.getCellType());
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
	             
	           case FORMULA:
	   //   	   String  sf =String.valueOf(formulaEvaluator.evaluate(iCell));
	        	   String  sf =evaluateCellAndRetrunValue(workBook, iCell);
	        	   System.out.println(sf);
	        	   ;
	        	  //tring s=String.valueOf(iCell.getNumericCellValue());
	        	   RowItems.put(columnName, sf);
	        	   
	        	   System.out.println("i was here");
	        	   break;
	           case BLANK:
		        //System.out.print(iCell.getBooleanCellValue());
		          System.out.println("i was here blank");
		          RowItems.put(columnName, "EMPTY");
		             break; 
	          
	           default :
	         }
	       }
		
		//close the work book ,input stream
		workBook.close();
//fis.close();
		
		}
		catch (Exception e)
		{
			System.out.println("exception occured during reading data from XL"+e.getMessage());
		}
		return RowItems;
		}
	
	
	//evaluate the formula in the cell and return the value in String format
	public String evaluateCellAndRetrunValue(Workbook workbook,Cell cell)
	{
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
		
		CellValue cellValue = evaluator.evaluate(cell);
		 String actaulVal="";
		switch (cellValue.getCellType())
				{
		    case STRING:
		        System.out.print(cellValue.getStringValue());
		        actaulVal=cellValue.getStringValue();
		        break;
		    case BOOLEAN:
		        System.out.print(cellValue.getBooleanValue());
		        actaulVal=String.valueOf(cellValue.getBooleanValue());
		        break;
		    case NUMERIC:
		        System.out.print(cellValue.getNumberValue());
		        actaulVal=String.valueOf(cellValue.getNumberValue());
		        break;
		}
		return actaulVal;
	}
}





