package testRunner;
import java.io.File;


import org.junit.AfterClass;
import org.junit.runner.RunWith;

//import com.vimalselvam.cucumber.listener.Reporter;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import managers.FileReaderManager;

@RunWith(Cucumber.class)
@CucumberOptions(features= {"Features"},
glue= {"stepDefinitions"},

dryRun = false,
monochrome = true,
//plugin = {"pretty","html:testOutput/output.html"}

//"html:testOutput/output.html",   com.vimalselvam.cucumber.listener.ExtentCucumberFormatter, com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter
plugin = {"pretty","html:testOutput/TestsLog_output.html","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}

)

public class Runner {
	//extent report will be generated, file path ccaptuyre from configfile
	/*
	@AfterClass
	public static void writeExtentReport() {
		//Reporter.loadXMLConfig(new File("C:\\Users\\admin\\eclipse-workspace\\bddCucumber_Keyword_ExtentReport_Framework\\Configs\\extent-config.xml"));
		
		Reporter.loadXMLConfig(new File(FileReaderManager.getInstance().getConfigReader().getReportConfigPath()));
	}
	*/
}
