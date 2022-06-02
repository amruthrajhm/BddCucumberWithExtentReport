package bddCucumber;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class chromeTest1 {
public static String myUrl;

public String getReportConfigPath() {
	String dir=	System.getProperty("user.dir");
	Properties Prop =new Properties();
	String fileName=dir+"\\src\\test\\resources\\temp\\"+"temp.properties"; //"C:\\Users\\admin\\eclipse-workspace\\bddCucumber\\"
	FileInputStream iFile = null;
	
	try {
		iFile = new FileInputStream(new File(fileName));
		Prop.load(iFile); //load the properties of file
	} catch (Exception e) {
		e.printStackTrace();
	}//get the file
	finally {
		try {
			iFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		String reportConfigPath = Prop.getProperty("login.userName.Xpath");
		System.out.println(reportConfigPath);
		
		
		if(reportConfigPath!= null) return reportConfigPath;
		else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");		
	
	}
	
	public  chromeTest1()
	{
		
		//String dir = System.getProperty("user.dir");
		
		//System.setProperty("webdriver.chrome.driver","C:\\selenium\\drivers\\chromedriver.exe" );  
	    
		//System.setProperty("webdriver.chrome.driver",dir+"\\Drivers\\chromedriver.exe" );  
	    //DesiredCapabilities capabilities = DesiredCapabilities.
	   // capabilities.setCapability("marionette",true);  
	   
	   // WebDriver driver= new ChromeDriver();
	 
	}
	
	public WebDriver  LaunchUrl(String Url)
	{
		 	WebDriver d=new ChromeDriver();
		    d.navigate().to(Url);
		    return d;
	}

	public void googleSerach(String searchContent,WebDriver browserObj)
	{
		browserObj.findElement(By.name("q")).sendKeys(searchContent);
		browserObj.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		browserObj.findElement(By.xpath("//input[@value='Google Search']")).click();
		//browserObj.findElement(By.name("btnK")).click();
	}
	
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			//Setting system properties of ChromeDriver
			//System.setProperty("webdriver.chrome.driver", "C:\\selenium\\drivers\\chromedriver.exe");
			chromeTest1 l=new chromeTest1();
			System.out.println(l.getReportConfigPath());
			//WebDriver myBrowser =l.LaunchUrl("https://google.com");
			//l.googleSerach("pig pics cute",myBrowser);
			
			//Creating an object of ChromeDriver
			


		}


}
