package pageObjects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
public WebDriver lDriver;
public HomePage(WebDriver rDriver)
{
	lDriver=rDriver;
	PageFactory.initElements(rDriver, this);
}

@FindBy(linkText = "Register")
WebElement registerLink;

//@FindBy(xpath ="//a[@class='ico-login']")
//WebElement loginLink;

@FindBy(id = "Username")
WebElement wEdituserName;

@FindBy(id = "Password")
WebElement wEditpassword;

@FindBy(xpath  = "//input[@value='Log in']")
WebElement btnSubmit;

By abc=By.xpath("aa");

public void ClickLogin()
{
	//loginLink.click();
	lDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
}

public void EnterUserNameNdPassword(String userName,String password)
{
	wEdituserName.sendKeys(userName);
	wEditpassword.sendKeys(password);
	
}
public void clickSubmit()
{
	btnSubmit.click();

}

public void VerifyTitle(String title,boolean expected)
{
	lDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	String eTitle=lDriver.getTitle();
	if(expected==true)
	{
	if (eTitle.equalsIgnoreCase(title)) {
		System.out.println("titles matching");
		Assert.assertTrue(true);
		
	}
	else
	{
		Assert.assertTrue(false);
		System.out.println("titles are not matching");
	}
	}
	if(expected==false){
	if (!(eTitle.equalsIgnoreCase(title))) {
		Assert.assertTrue(true);
		System.out.println("titles are not matching");
	}
	else
	{
		Assert.assertTrue(false);
	}
	}
}
}
