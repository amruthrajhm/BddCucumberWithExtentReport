package managers;

import java.io.IOException;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

//import com.vimalselvam.cucumber.listener.Reporter;

import enums.ActionType;
import enums.LocatorType;
import io.cucumber.java.Scenario;
import managers.ObjectRepositoryManager;

public class BrowserManager extends WebDriverManager{
	
	//public static LocatorType locatorType;
//	public static WebDriver driver;
	
	public  void BrowserNavigation(String objectName,String Action,String textValue) {
		//Scenario scn=StepsWritter.getScenario();
		//WebDriver browserObj=getDriver();//get the driver object from web driver manager
		//LocatorType elementType=getObjectType(objectName);
		//ActionType actionType=getActionType(Action);
		String objPropertyValue=getObjectValue(objectName);
		WebElement iElement=getWebElement(objectName);

	//
		switch (ActionType.valueOf(Action.toUpperCase())) {
		
		case CLICK:
			if(iElement!=null)
			{
				 StepsWritter.writeSteps("click on : "+objectName+" -> "+objPropertyValue);
				iElement.click();
				//Reporter.addStepLog("Clicked on Object+:"+objectName+",propertyValue:"+objPropertyValue);
			}
			break;
		case SETTEXT:
			if(iElement!=null)
			{
				StepsWritter.writeSteps("Set Text: "+textValue+" on :"+objectName+" -> "+objPropertyValue);
				iElement.sendKeys(textValue);
				//Reporter.addStepLog("SetText on Object+:"+objectName+",propertyValue:"+objPropertyValue+",textSet:"+textValue);
			}
			break;
		case SELECT:
			if(iElement!=null)
			{
				StepsWritter.writeSteps("Select : "+textValue+" on :"+objectName+" -> "+objPropertyValue);
				Select sel=new Select(iElement);
				sel.selectByValue(textValue);
				//Reporter.addStepLog("Select on Object+:"+objectName+",propertyValue:"+objPropertyValue+",select:"+textValue);
				
			}
			break;

		default:
			StepsWritter.writeSteps("Select : correct type of Action");	
			break;
		} 
	
	}
	
	
	private String getObjectValue(String objectName)
	{
		String pageObjectValue="";
		try {
			pageObjectValue = ObjectRepositoryManager.getObjectRepository().getProperty(objectName);
		} catch (IOException e) {
			e.printStackTrace();
		}//get the repository file
		
		return pageObjectValue;
	}
	
	public WebElement getWebElement(String objectName)
	{
		String objPropertyValue=getObjectValue(objectName);//get the property of the object from the repository
		WebDriver browserObj=getDriver();
		WebElement iElement = null;
		String[] tempStr= objectName.split("\\.");
		int lenObj=	tempStr.length;
		String objType=tempStr[lenObj-1];
		
		 switch (LocatorType.valueOf(objType.toUpperCase())) {	
		 case XPATH:
			 System.out.println(objPropertyValue);
			 if (!browserObj.findElements(By.xpath(objPropertyValue)).isEmpty())
				{
					iElement=browserObj.findElement(By.xpath(objPropertyValue));
				}
				else
				{		
					
					//Reporter.addStepLog("element Not Found"+objectName);
					iElement=null;
				}
			 break;
		 case ID:
			 if (!browserObj.findElements(By.id(objPropertyValue)).isEmpty())
				{
					iElement=browserObj.findElement(By.id(objPropertyValue));
				}
				else
				{					
					//Reporter.addStepLog("element Not Found"+objectName);
					iElement=null;
				}
			 break;
		 case NAME:
			 if (!browserObj.findElements(By.name(objPropertyValue)).isEmpty())
				{
					iElement=browserObj.findElement(By.name(objPropertyValue));
				}
				else
				{					
					//Reporter.addScenarioLog("element Not Found"+objectName);
					iElement=null;
				}
			 break;
		 case CLASSNAME:
			 if (!browserObj.findElements(By.className (objPropertyValue)).isEmpty())
				{
					iElement=browserObj.findElement(By.className(objPropertyValue));
				}
				else
				{					
					//Reporter.addStepLog("element Not Found"+objectName);
					iElement=null;
				}
			 break;
		 case TAGNAME:
			 if (!browserObj.findElements(By.tagName(objPropertyValue)).isEmpty())
				{
					iElement=browserObj.findElement(By.tagName(objPropertyValue));
				}
				else
				{					
					//Reporter.addStepLog("element Not Found"+objectName);
					iElement=null;
				}
			 break;
		 case CSS:
			 if (!browserObj.findElements(By.cssSelector(objPropertyValue)).isEmpty())
				{
					iElement=browserObj.findElement(By.cssSelector(objPropertyValue));
				}
				else
				{					
					//Reporter.addStepLog("element Not Found"+objectName);
					iElement=null;
				}
			 break;
		 default:
			// Reporter.addStepLog("wrong object type:"+elementType);
				System.out.println("Select correct type of Object Type,xpath,name,tagname,css,id");
				break;
	
		 }
		 return iElement;
	}
	

}
