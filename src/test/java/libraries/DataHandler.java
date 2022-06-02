package libraries;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.select.Evaluator;
import org.apache.poi.ss.formula.BaseFormulaEvaluator;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.usermodel.*;  
 

public class DataHandler {
public static  String url;

public Properties readPropertyFileData() 
{
	Properties Prop =new Properties();
	String dir=System.getProperty("user.dir");
	String fileName="config.properties";//"C:\\Users\\admin\\eclipse-workspace\\bddCucumber\\"
	FileInputStream iFile = null;
	try {
		iFile = new FileInputStream(new File(fileName));//get the file
		Prop.load(iFile); //load the properties of file
		url=Prop.getProperty("url"); //get the url assign it to public variable
			
	} 
	catch(FileNotFoundException fnfe) {
        fnfe.printStackTrace();
     }
	catch(IOException ioe) {
        ioe.printStackTrace();
     }
	finally {
			try {
				iFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}	
     }

	return Prop;
}
	//get url
	public String getUrl()
	{
		return url;
	}
	/* Get the test Data from the test data File*/
	public HashMap<String, String> getTestData(String fileName,String sheetName,int rowNumber)
	{
		HashMap<String, String> RowItems=new HashMap<String, String>();
		String sDir=System.getProperty("user.dir");
		System.out.println(sDir);
		System.out.println(Paths.get("").toAbsolutePath().toString());
		String filePath=sDir+"\\TestData\\"+fileName+".xlsx";
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

