package stepDefinitions.yatraFlights;

import org.openqa.selenium.By;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import libraries.BrowserFunctions;
import managers.BrowserManager;
import managers.FileReaderManager;
import managers.ObjectRepositoryManager;
import managers.StepsWritter;
import managers.WebDriverManager;

public class YatraFlightSearch extends WebDriverManager {
	

	BrowserManager browserManager=new BrowserManager();
	@When("enter {string} and {string} in fromToSection")
	public void enter_and_in_from_to_section(String Departure, String Arrival) {
		StepsWritter.writeSteps("launch browser with url:"+FileReaderManager.getInstance().getConfigReader().getApplicationUrl());

		
		ObjectRepositoryManager.setObjectRepository("yatraFlightSearch.properties");
		BrowserFunctions.ExplicitImplicitWaits("", "browserwait", 5);
		browserManager.BrowserNavigation("yatraFlightSearch.Lnk.Xpath", "click", "");
		browserManager.BrowserNavigation("yatraFlightSearch.DepartureEdit.Xpath", "settext", Departure);
		BrowserFunctions.ExplicitImplicitWaits("yatraFlightSearch.BlrLink.Xpath","visibilityofelement",30);
		browserManager.BrowserNavigation("yatraFlightSearch.BlrLink.Xpath", "click", "");
		BrowserFunctions.ExplicitImplicitWaits("", "browserwait", 5);
		browserManager.BrowserNavigation("yatraFlightSearch.ArrivalEdit.Xpath", "settext", Arrival);
		BrowserFunctions.ExplicitImplicitWaits("yatraFlightSearch.DelLink.Xpath","visibilityofelement",30);
		browserManager.BrowserNavigation("yatraFlightSearch.DelLink.Xpath", "click", "");
		
	}
	
	@Then("enter departure date month as {string} and day {string}")
	public void enter_departure_date_month_as_and_day(String Month, String idate) {
		browserManager.BrowserNavigation("yatraFlightSearch.DepartureDateEle.Xpath", "click", "");
		BrowserFunctions.ExplicitImplicitWaits("", "browserwait", 5);
		String xpath="//div[@id='monthWrapper']//div[contains(text(),'"+Month+"')]//following-sibling::div//table//td[@data-date='"+idate+"']";
		getDriver().findElement(By.xpath(xpath)).click();
		//'MOUSECLICK
	}
	@Then("select Travels  details {string} and {string} and {string} and {string}")
	public void select_travels_details_and_and_and(String string, String string2, String string3, String string4) {
		browserManager.BrowserNavigation("yatraFlightSearch.seatTypeList.Xpath", "select", "Economy");
		
	}


	@Then("select seat {string}")
	public void select_seat(String seatType) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Then("Search Filghts")
	public void search_filghts() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}




}
