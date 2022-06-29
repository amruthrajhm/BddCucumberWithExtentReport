package stepDefinitions;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.model.Report;

import io.cucumber.java.en.*;
import io.cucumber.java.en.Then;
import libraries.BrowserFunctions;
import managers.BrowserManager;
import managers.FileReaderManager;
import managers.ObjectRepositoryManager;
import managers.StepsWritter;
import managers.WebDriverManager;
import pageObjects.HomePage;

public class FlipKart_Home extends WebDriverManager{
	//WebDriver browserObj;
	//public HomePage homePage;
	BrowserManager browserManager=new BrowserManager();
	
	

@Given("User launch browser with given URL")
public void user_launch_browser_with_given_url() {
  StepsWritter.writeSteps("launch browser with url:"+FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
}

@Given("Enter username as {string} and Password 	as {string} and click login")
public void enter_username_as_and_password_as_and_click_login(String userName, String password) {
	ObjectRepositoryManager.setObjectRepository("FlipKart.properties");
	BrowserFunctions.ExplicitImplicitWaits("", "browserwait", 5);
	browserManager.BrowserNavigation("HomePage.LoginLnk.Xpath", "hovermouse", "");
	browserManager.BrowserNavigation("HomePage.LoginLnk.Xpath", "mouseclick", "");
	BrowserFunctions.ExplicitImplicitWaits("", "browserwait", 5);
	browserManager.BrowserNavigation("HomePage.userNameTxt.Xpath", "settext", userName);
	browserManager.BrowserNavigation("HomePage.PasswordTxt.Xpath", "settext", password);
	browserManager.BrowserNavigation("HomePage.LoginBtn.Xpath", "click", "");
	BrowserFunctions.ExplicitImplicitWaits("HomePage.invalindEmailError.Xpath", "visibilityofelement", 10);
	BrowserFunctions.verifyElementTextContains("HomePage.invalindEmailError.Xpath", "this is wrong text");
	
}
	
	

@Given("Search {string}  and click on it")
public void search_and_click_on_it(String SearchText) {
	ObjectRepositoryManager.setObjectRepository("FlipKart.properties");
	browserManager.BrowserNavigation("HomePage.SearchInputTxt.Xpath", "mouseclick", "");
	browserManager.BrowserNavigation("HomePage.SearchInputTxt.Xpath", "settext", SearchText);
	BrowserFunctions.ExplicitImplicitWaits("", "browserwait", 5);
	browserManager.BrowserNavigation("HomePage.SamsungMobilesElemt.Xpath", "hovermouse", "");
	browserManager.BrowserNavigation("HomePage.SamsungMobilesElemt.Xpath", "click", "");
    
}
@Then("click on {string}")
public void click_on(String string) {
    // Write code here that turns the phrase above into concrete actions
   // throw new io.cucumber.java.PendingException();
}



	
	
	
	
	
	
	
	
	
	
	@Given("User launch Chrome browser with given URL")
	public void user_launch_chrome_browser_with_given_url() throws InterruptedException
	{
		
		ObjectRepositoryManager.setObjectRepository("common.properties");
		
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
