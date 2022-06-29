package managers;

import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

//import com.vimalselvam.cucumber.listener.Reporter;

import enums.ActionType;
import enums.LocatorType;
import io.cucumber.java.Scenario;
import managers.ObjectRepositoryManager;

public class BrowserManager extends WebDriverManager {

	// public static LocatorType locatorType;
//	public static WebDriver driver;

	public   void BrowserNavigation(String objectName, String Action, String textValue) {
		String objPropertyValue = getObjectValue(objectName);
		WebElement iElement = getWebElement(objectName);
		perFormAction(iElement, Action, textValue, objectName, objPropertyValue);

	}
//returns a string with object value 
	public static String getObjectValue(String objectName) {
		String pageObjectValue = "";
		try {
			pageObjectValue = ObjectRepositoryManager.getObjectRepository().getProperty(objectName);
		} catch (IOException e) {
			e.printStackTrace();
		} // get the repository file

		return pageObjectValue;
	}

	// returns the webelement of the given object
	public  WebElement getWebElement(String objectName) {
		String objPropertyValue = getObjectValue(objectName);// get the property of the object from the repository
		WebDriver browserObj = getDriver();
		WebElement iElement = null;
		String[] tempStr = objectName.split("\\.");
		int lenObj = tempStr.length;
		String objType = tempStr[lenObj - 1];

		switch (LocatorType.valueOf(objType.toUpperCase())) {
		case XPATH:
			System.out.println(objPropertyValue);
			if (!browserObj.findElements(By.xpath(objPropertyValue)).isEmpty()) {
				iElement = browserObj.findElement(By.xpath(objPropertyValue));
			} else {
				iElement = null;
			}
			break;
		case ID:
			if (!browserObj.findElements(By.id(objPropertyValue)).isEmpty()) {
				iElement = browserObj.findElement(By.id(objPropertyValue));
			} else {
				// Reporter.addStepLog("element Not Found"+objectName);
				iElement = null;
			}
			break;
		case NAME:
			if (!browserObj.findElements(By.name(objPropertyValue)).isEmpty()) {
				iElement = browserObj.findElement(By.name(objPropertyValue));
			} else {
				// Reporter.addScenarioLog("element Not Found"+objectName);
				iElement = null;
			}
			break;
		case CLASSNAME:
			if (!browserObj.findElements(By.className(objPropertyValue)).isEmpty()) {
				iElement = browserObj.findElement(By.className(objPropertyValue));
			} else {
				// Reporter.addStepLog("element Not Found"+objectName);
				iElement = null;
			}
			break;
		case TAGNAME:
			if (!browserObj.findElements(By.tagName(objPropertyValue)).isEmpty()) {
				iElement = browserObj.findElement(By.tagName(objPropertyValue));
			} else {
				// Reporter.addStepLog("element Not Found"+objectName);
				iElement = null;
			}
			break;
		case CSS:
			if (!browserObj.findElements(By.cssSelector(objPropertyValue)).isEmpty()) {
				iElement = browserObj.findElement(By.cssSelector(objPropertyValue));
			} else {
				// Reporter.addStepLog("element Not Found"+objectName);
				iElement = null;
			}
			break;
		default:
			// Reporter.addStepLog("wrong object type:"+elementType);
			System.out.println("Select correct type of Object Type,xpath,name,tagname,css,id");
			break;

		}
		return iElement;
	}

	// object name and objPropertyValue used for reporting , if passed "","" then in
	// log object propeties wont be logged
	public  void  perFormAction(WebElement iElement, String Action, String textValue, String objectName,
			String objPropertyValue) {
		switch (ActionType.valueOf(Action.toUpperCase())) {

		case CLICK:
			if (iElement != null) {
				StepsWritter.writeSteps("click on : " + objectName + " -> " + objPropertyValue);
				iElement.click();
				
			}
			break;
		case SETTEXT:
			if (iElement != null) {
				StepsWritter.writeSteps("Set Text: " + textValue + " on :" + objectName + " -> " + objPropertyValue);
				iElement.sendKeys(textValue);
			}
			break;
		case SELECT:
			if (iElement != null) {
				StepsWritter.writeSteps("Select : " + textValue + " on :" + objectName + " -> " + objPropertyValue);
				Select sel = new Select(iElement);
				sel.selectByValue(textValue);
			}
			break;
		case CLICKALERT:
			StepsWritter.writeSteps("click on alert :" + textValue);
			clickAlert(textValue);
			
		case HOVERMOUSE:
		//	Actions myAction=new Actions(getDriver());
			new Actions(getDriver()).moveToElement(iElement);
			break;
		case MOUSECLICK:
			//Actions myAction=new Actions(getDriver());
			new Actions(getDriver()).click(iElement);
			break;
		default:
			StepsWritter.writeSteps("Select : correct type of Action");
			break;
		}
	}

	public void closeBrowserWindowByUrl(String PartialUrl) {

		WebDriver driver = getDriver();
		String mainWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (Iterator<String> iterator = allWindowHandles.iterator(); iterator.hasNext();) {
			String TempWindowHandle = iterator.next();
			driver = driver.switchTo().window(TempWindowHandle);
			if (driver.getCurrentUrl().toLowerCase().contains(PartialUrl)) {
				driver.close();
				break;
			}

		}
		setDriver(driver.switchTo().window(mainWindowHandle));

	}

	public  void clickAlert(String alertButton) {
		if (alertButton.equalsIgnoreCase("ok")) {
			getDriver().switchTo().alert().accept();

		} else if (alertButton.equalsIgnoreCase("cancel")) {
			getDriver().switchTo().alert().dismiss();
		}

	}

	 
}
