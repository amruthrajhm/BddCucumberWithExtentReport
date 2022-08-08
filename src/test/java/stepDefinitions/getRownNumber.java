package stepDefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import managers.TestDataManagerNew;

public class getRownNumber {

	public static void main(String[] args) {
		HashMap<String , String> myTestData=new HashMap<>();
		TestDataManagerNew dataManagerNew=new TestDataManagerNew();
//org.apache.poi.ss.usermodel.CellValue [13.0]
		//org.apache.poi.ss.usermodel.CellValue ["abcaaa13"]
	
		TestDataManagerNew.SetTestDataFile("\\TestScenarios\\AllScenarios");
		System.out.println(TestDataManagerNew.dataFileName);
		System.out.println( dataManagerNew.getTestData( "JsonFiles", "2969"));
		
		// TODO Auto-generated method stub}
	}

}
