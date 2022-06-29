package managers;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import enums.DriverType;

public class WebDriverManager {
	private static WebDriver driver;
	private static DriverType driverType;
	private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
	private static final String FIREFOX_DRIVER_PROPERTY = "webdriver.gecko.driver";

	public WebDriverManager() {
		driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
	}
	
	public  WebDriver getDriver() {
		if(driver == null) driver = createDriver();//if driver not created it will create
		return driver;
	}
	
	public WebDriver setDriver(WebDriver idriver) {
		WebDriverManager.driver=idriver;
		//if(driver == null) driver = createDriver();
		return driver;
	}
	
	private WebDriver createDriver() {
		String dir=	System.getProperty("user.dir");
		switch (driverType) {	
        case FIREFOX : 
        	
        	System.setProperty(FIREFOX_DRIVER_PROPERTY, dir+FileReaderManager.getInstance().getConfigReader().getDriverPath());
        	driver = new FirefoxDriver();
        break;
        case CHROME : 
        	System.out.println( FileReaderManager.getInstance().getConfigReader().getDriverPath());
        	System.setProperty(CHROME_DRIVER_PROPERTY, dir+FileReaderManager.getInstance().getConfigReader().getDriverPath());
        	driver = new ChromeDriver();
    		break;
	}
	//maximize the window if set true, and wait for given period of time
		if(FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize()) driver.manage().window().maximize();
		System.out.println(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait()));
		return driver;
	}
	
}
