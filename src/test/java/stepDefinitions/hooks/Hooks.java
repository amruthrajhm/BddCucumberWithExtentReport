package stepDefinitions.hooks;

import java.io.File;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.Report;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;
//import com.vimalselvam.cucumber.listener.Reporter;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import managers.FileReaderManager;
import managers.StepsWritter;
import managers.WebDriverManager;

//hooks intializes all the objects requried for execution

public class Hooks extends WebDriverManager{
	public WebDriver browser;

//	initDriver method will be called before each scenario execution
	@Before
	public void initDriver(Scenario scenario) throws InterruptedException {
		Thread.sleep(2000);
		
		//Reporter.addStepLog("launching the browser");
		browser=getDriver();
		browser.navigate().to(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
		setDriver(browser);
		StepsWritter.currentScenario=scenario;//refer the scenario to stepswritter to write the steps
		
	}
	
	//take screenshot if scenario fails
	@After(order = 1)
	public void afterScenario(Scenario scenario) {
		if (scenario.isFailed()) {
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			try {
				//This takes a screenshot from the driver at save it to the specified location
				File sourcePath = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
				
				//Building up the destination path for the screenshot to save
				//Also make sure to create a folder 'screenshots' with in the cucumber-report folder
				File destinationPath = new File(System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/" + screenshotName + ".png");
				
				//Copy taken screenshot from source location to destination location
				
				Files.copy(sourcePath, destinationPath);   

				//This attach the specified screenshot to the test
				//Reporter.addScreenCaptureFromPath(destinationPath.toString());
			} catch (IOException e) {
			} 
		}
	}
	
	//teardown method will be called after each scenario execution
	@After(order = 0)
	public void teardown() throws InterruptedException {
		//Reporter.addStepLog("closing the browser");
		
		WebDriver driver=getDriver();
		//driver.close();
		Thread.sleep(4000);
		driver.quit();
		driver=null;
		setDriver(driver);
	}
	
}
