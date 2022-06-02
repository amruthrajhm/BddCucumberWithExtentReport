package stepDefinitions;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.model.Report;

import io.cucumber.java.en.*;
import io.cucumber.java.en.Then;

import managers.BrowserManager;
import managers.ObjectRepositoryManager;
import managers.StepsWritter;
import managers.WebDriverManager;
import pageObjects.HomePage;

public class LoginPage extends WebDriverManager{
	//WebDriver browserObj;
	//public HomePage homePage;
	@Given("User launch Chrome browser with given URL")
	public void user_launch_chrome_browser_with_given_url() throws InterruptedException
	{
		
		ObjectRepositoryManager.setObjectRepository("common.properties");
		BrowserManager browserManager=new BrowserManager();
		//StepsWritter.writeSteps("set mugilu@gmail.com to obj login.userNameTxt.Xpath");
		browserManager.BrowserNavigation("login.userNameTxt.Xpath", "settext", "mugilu@gmail.com");
		Thread.sleep(5000);
		browserManager.BrowserNavigation("login.AtlassianBtn.Xpath", "click", "");
		Thread.sleep(5000);
		//StepsWritter.writeSteps("set testUser to obj login.passwordTxt.Xpath");
		browserManager.BrowserNavigation("login.passwordTxt.Xpath", "settext", "testUser");
		browserManager.BrowserNavigation("login.SubmitBtn.Xpath", "click", "");
		
		
	}
	

	@Given("user Enter username as {string} and Password 	as {string}")
	public void user_enter_username_as_and_password_as(String uName,String pwd)
	{
		StepsWritter.writeSteps("inside username and password function");
	}
	@Given("Click Submit Btn")
	public void click_submit_btn() 
	{
		StepsWritter.writeSteps("inside click submit Btn");
	System.out.println("passed");
	}
	@Then("UserShouldNot  Login Tilte ShouldNot be {string}")
	public void user_shouldnot_login_tilte_shouldnot_be(String string)
	{
		//homePage.VerifyTitle(string,false);
	}
	
	@Then("UserShould Login Tilte Should be {string}")
	public void user_should_login_tilte_should_be(String title)
	{
		WebDriver ldriver=getDriver();
		//homePage.VerifyTitle(string,true);
		ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String eTitle=ldriver.getTitle();
	
		if (eTitle.contentEquals(title)) {
			System.out.println("titles matching");
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertFalse("expected:"+title+"actual:"+eTitle, false);
		}
	}
	/*
	@Then("quit the Browser")
	public void quit_the_browser() {
	    browserObj.quit();
	}
	*/
}
