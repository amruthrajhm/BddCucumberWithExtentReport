package libraries;

import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import enums.LocatorType;
import managers.BrowserManager;
import managers.FileReaderManager;
import managers.StepsWritter;
import managers.WebDriverManager;

public class BrowserFunctions  {
	
	public static List<WebElement> BrowserGetElements(String objectName)
	{
		return new WebDriverManager().getDriver().findElements(getByObject(objectName));
	}
	
	
	public static  void BrowserWait(String WaitType, int Seconds)
	{
		//int iwait =FileReaderManager.getInstance().getConfigReader().getWaitSeconds(WaitType);
		WebDriverManager driverManager=new WebDriverManager();
		if (WaitType.toLowerCase().contains("implicitwait"))
		{
			driverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Seconds));

		}
		else if(WaitType.toLowerCase().contains("hardwait"))
		{
			
			try {
				Thread.sleep(Seconds*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	//returns the by object of given objectname
	
	public static By getByObject(String objectName)
	{
		//BrowserManager browserManager=new BrowserManager();
		String objPropertyValue= BrowserManager.getObjectValue(objectName);//get the property of the object from the repository
		//WebDriver browserObj= new WebDriverManager().getDriver(); 
		String[] tempStr= objectName.split("\\.");
		int lenObj=	tempStr.length;
		String objType=tempStr[lenObj-1];
		By elem_dynamic = null;
		
		switch (LocatorType.valueOf(objType.toUpperCase())) {	
		 case XPATH:
			  elem_dynamic = By.xpath(objPropertyValue);
			 break;
		 case ID:
			 elem_dynamic = By.id(objPropertyValue); 
			 break;
		 case NAME:
			 elem_dynamic = By.name(objPropertyValue);
			 break;
		 case CLASSNAME:
			 elem_dynamic = By.className(objPropertyValue);
			 break;
		 case TAGNAME:
			 elem_dynamic = By.tagName(objPropertyValue);
			 break;
		 case CSS:
			 elem_dynamic = By.cssSelector(objPropertyValue);
			 break;
		 default:
			// Reporter.addStepLog("wrong object type:"+elementType);
				System.out.println("Select correct type of Object Type,xpath,name,tagname,css,id");
				break;
	
		 }
		return elem_dynamic;
	}
	
	public static void ExplicitImplicitWaits(String objectName,String waitType,int Seconds)
	{
		By elem_dynamic=null;
		if (!objectName.equals("")) {
			 elem_dynamic=BrowserFunctions.getByObject(objectName);
		}
		
		WebDriver browserObj= new WebDriverManager().getDriver(); 
		WebDriverWait driverWait=new WebDriverWait(browserObj, Duration.ofSeconds(Seconds));
		
	
		 if(waitType.equalsIgnoreCase("presenceofelement")) {//waits until element is present in dom
		 driverWait.until(ExpectedConditions.presenceOfElementLocated(elem_dynamic));
		 }
		 else if(waitType.equalsIgnoreCase("visibilityofelement")) {//wiats until elemet is visible means heighNdwidthe is not zero
			 driverWait.until(ExpectedConditions.visibilityOf(browserObj.findElement(elem_dynamic)));
		 }
		 else if(waitType.equalsIgnoreCase("invisibilityofelement")) {//wiats until elemet is not visible means heighNdwidthe is zero
			 driverWait.until(ExpectedConditions.invisibilityOf(browserObj.findElement(elem_dynamic)));
		 }
		 else if(waitType.equalsIgnoreCase("implicitwait")) {//wiats till all elemts loads
			 browserObj.manage().timeouts().implicitlyWait(Duration.ofSeconds(Seconds));	
		 }
		 else if(waitType.equalsIgnoreCase("browserwait")) {//execution halts for give period of seconds
			try {
				Thread.sleep(Seconds*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		 }
		 
	}
	
	//isselected,isenabled,isdisplayed
	public static void verifyElementStatus(String ObjectName,String ObjectStatus,boolean objectExist)
	{
		By elem_dynamic=BrowserFunctions.getByObject( ObjectName);
		WebDriver browserObj =new WebDriverManager().getDriver();
		
		//String objPropertyValue=browserManager.getObjectValue(ObjectName);//get the property of the object from the repository
		//WebDriver browserObj= new WebDriverManager().getDriver(); 
		if(objectExist==true)
		{
			if(ObjectStatus.equalsIgnoreCase("isslected"))
			{
				if(browserObj.findElement(elem_dynamic).isSelected())
				{
					StepsWritter.writeSteps("object is Selected:"+ObjectName);
				}
				else
				{
					StepsWritter.writeSteps("object is not Selected:"+ObjectName);
					
				}
			}
			else if(ObjectStatus.equalsIgnoreCase("isdisplayed"))
			{
				if(browserObj.findElement(elem_dynamic).isDisplayed())
				{
					StepsWritter.writeSteps("object is isdisplayed:"+ObjectName);
				}
				else
				{
					StepsWritter.writeSteps("object is not isdisplayed:"+ObjectName);
					
				}
			}
			else if(ObjectStatus.equalsIgnoreCase("isenabled"))
			{
				if(browserObj.findElement(elem_dynamic).isEnabled())
				{
					StepsWritter.writeSteps("object is isenabled:"+ObjectName);
				}
				else
				{
					StepsWritter.writeSteps("object is not isenabled:"+ObjectName);
					
				}
			}
		}
		else if(objectExist==false)
		{

			if(ObjectStatus.equalsIgnoreCase("isslected"))
			{
				if(!browserObj.findElement(elem_dynamic).isSelected())
				{
					StepsWritter.writeSteps("object is not Selected:"+ObjectName);
				}
				else
				{
					StepsWritter.writeSteps("object is Selected:"+ObjectName);
					
				}
			}
			else if(ObjectStatus.equalsIgnoreCase("isdisplayed"))
			{
				if(!browserObj.findElement(elem_dynamic).isDisplayed())
				{
					StepsWritter.writeSteps("object is not displayed:"+ObjectName);
				}
				else
				{
					StepsWritter.writeSteps("object is displayed:"+ObjectName);
					
				}
			}
			else if(ObjectStatus.equalsIgnoreCase("isenabled"))
			{
				if(!browserObj.findElement(elem_dynamic).isEnabled())
				{
					StepsWritter.writeSteps("object is not enabled:"+ObjectName);
				}
				else
				{
					StepsWritter.writeSteps("object is enabled:"+ObjectName);
					
				}
			}
		}
	}

	
	public static void verifyElementTextContains(String objectName,String expectedText)
	{
		By elem_dynamic=BrowserFunctions.getByObject(objectName);
		String actualText= new WebDriverManager().getDriver().findElement(elem_dynamic).getText();
		if (actualText.toLowerCase().contains(actualText)) {
			assertTrue(objectName+"element contains text:"+expectedText, true);
					
		}
		else 
		{
			assertTrue(objectName+"element not contains text:"+expectedText, false);
			}
		
	}
}


