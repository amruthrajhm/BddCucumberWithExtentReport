package stepDefinitions;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import libraries.DataHandler;

public class BrowserCommon extends DataHandler  {
	public static WebDriver browser;
	String iUrl;
	/*public BrowserCommon(WebDriver driver)
	{
		this.browser=driver;
	} */
	public WebDriver LaunchBrowser()
	{
	DataHandler r=new DataHandler();
	Properties p=r.readPropertyFileData();
	iUrl=r.getUrl();
	
	String dir = System.getProperty("user.dir");
	System.out.println(dir);
	System.setProperty("webdriver.chrome.driver",dir+"\\Drivers\\chromedriver.exe" );  
    
	browser=new ChromeDriver();
	browser.navigate().to(iUrl);
	browser.manage().window().maximize();
	browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	return browser;
	}
	
	public void closeAllBrowsers()//closes all the browsers
	{
		browser.quit();
	}
 
	public static void main(String[] args) 
	{
		DataHandler c=new DataHandler();
		
		//c.LaunchBrowser();
		//System.out.println(c.iUrl);
		//c.closeAllBrowsers(browser);
		HashMap<String, String> myItems=c.getTestData("test", "mySheet", 1);
		System.out.println(myItems.get("RowId"));
		System.out.println(myItems.get("Column3"));
		System.out.println(myItems.get("info"));
		System.out.println(myItems.get("Column2"));
		//System.out.println(ReadConfigData.url);
	}
}
