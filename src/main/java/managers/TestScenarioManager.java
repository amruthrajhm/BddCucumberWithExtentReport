package managers;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

public class TestScenarioManager {
	public static XSSFWorkbook iWorkBook;
	
	public void generateFeatureFiles()
	{
		
		HashMap<String, String> RowItems=new HashMap<String, String>();
		ArrayList<String> executableModuleNames=new ArrayList<String>();
		
		String sDir=System.getProperty("user.dir");
		System.out.println(sDir);
		System.out.println(Paths.get("").toAbsolutePath().toString());
		String filePath=sDir+"\\TestScenarios\\"+"AllScenarios"+".xlsx";
		filePath=filePath.toString();
		File tdObj=new File(filePath);
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(tdObj);
			XSSFWorkbook workBook=new XSSFWorkbook(fis);
			iWorkBook=workBook;
			XSSFSheet tdSheet=workBook.getSheet("Controller");
		int rowNums=tdSheet.getPhysicalNumberOfRows();
		
		for (int iCounter=1;iCounter<rowNums;iCounter++)
		{
			Cell cell=tdSheet.getRow(iCounter).getCell(2);
			if (cell!=null)//only continue if cell is not null or empty
			{
			CellType cellType=tdSheet.getRow(iCounter).getCell(2).getCellType();
			if (String.valueOf(cell.getCellType())!="null"&& String.valueOf(cell.getCellType())!="BLANK")
			{
		
				String moduleName=tdSheet.getRow(iCounter).getCell(2).getStringCellValue().toString();
				String moduleExecute=tdSheet.getRow(iCounter).getCell(3).getStringCellValue().toString();
				if (moduleExecute.equalsIgnoreCase("y") ||moduleExecute.equalsIgnoreCase("yes") )
				{
					//get all the module names which have flag Y or Yes
					executableModuleNames.add(moduleName);
					//System.out.println(moduleName);
				}

			}
			}
			/*
			if (cellType!="null"||cellType!="BLANK")
			{
			String moduleName=tdSheet.getRow(iCounter).getCell(2).getStringCellValue().toString();
			String moduleExecute=tdSheet.getRow(iCounter).getCell(3).getStringCellValue().toString();
			if (moduleExecute.equalsIgnoreCase("y") ||moduleExecute.equalsIgnoreCase("yes") )
			{
				//get all the module names which have flag Y or Yes
				executableModuleNames.add(moduleName);
			}
			}
			*/
		}
		//System.out.println(executableModuleNames);
		getTestCaseList(executableModuleNames);
			
			workBook.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		//workBook.getCreationHelper().createFormulaEvaluator().evaluateAll();//not working,,evaluate all formulas
	
		
		
	}
	
	
	//output: returns module name as key, testcases as array list
	//input:  arraylist as modulenames
	public HashMap<String, ArrayList<String>> getTestCaseList(ArrayList moduleNames)
	{
		HashMap<String, ArrayList<String>> TestListWithModuleNames=new HashMap<String, ArrayList<String>>();
		XSSFSheet tdSheet=iWorkBook.getSheet("Controller");
		int rowNums=tdSheet.getPhysicalNumberOfRows();
		System.out.println(rowNums);
		ArrayList<String> tempTestList=new ArrayList<String>();
		
		for (int mCounter=0;mCounter<moduleNames.size();mCounter++)//module loop
		{

			for (int iCounter=1;iCounter<rowNums;iCounter++)
			{
				String TestCaseName="",TestCaseExecute="";
				Cell c=tdSheet.getRow(iCounter).getCell(5);
				if(c!=null)
				{
				if (String.valueOf(c.getCellType())!="null"||String.valueOf(c.getCellType())!="BLANK")
				{
				 TestCaseName=tdSheet.getRow(iCounter).getCell(5).getStringCellValue().toString();
				 TestCaseExecute=tdSheet.getRow(iCounter).getCell(6).getStringCellValue().toString();
				 if (TestCaseName.startsWith(moduleNames.get(mCounter).toString(), 0) && TestCaseExecute.equalsIgnoreCase("y"))
					{
						//get all the module names which have flag Y or Yes
						//System.out.println(TestCaseName);
						tempTestList.add(TestCaseName);
					}
				}
				}
				
			}
			System.out.println(moduleNames.get(mCounter)+",,,"+tempTestList);
			
			TestListWithModuleNames.put(moduleNames.get(mCounter).toString(), tempTestList);
			//System.out.println(TestListWithModuleNames);
			tempTestList.clear();
		}
		System.out.println(TestListWithModuleNames.get("Claims1"));
		//System.out.println(TestListWithModuleNames.values());
		return TestListWithModuleNames;
	}
	
	public static void main(String[] args) {
		TestScenarioManager testScenarioManager=new TestScenarioManager();
		testScenarioManager.generateFeatureFiles();
	}

}
