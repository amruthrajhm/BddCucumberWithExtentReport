package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	public WebDriver lDriver;
	public RegisterPage(WebDriver rDriver)
	{
		lDriver=rDriver;
		PageFactory.initElements(rDriver, this);
	}
	
	@FindBy(linkText = "Register")
	WebElement lnkRegister;
	
	@FindBy(id = "FirstName")
	WebElement firstName;

	@FindBy(id = "LastName")
	WebElement lastName;

}
