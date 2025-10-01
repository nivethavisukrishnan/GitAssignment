package Selenium_Framework.Translink_UI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class LandingPage extends CommonComponents {
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		//initialize driver
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); //this refers to current class driver
	}
	//PageFactory
	@FindBy(xpath = "//nav[@class='useAccordionInfinitely'] //li/a[text()='Schedules and Maps']")
	WebElement Schedules_n_Maps;
	
	@FindBy(xpath = "//li/a[text()='Bus']")
	WebElement dropdownselect;
	
	@FindBy(css = "#bus-schedules")
	WebElement BusSchedules;
	
	public void goTo() {
		
		driver.get("https://www.translink.ca/");
	}
	
	public Boolean selectBusPage() {
		//commonComponents waitforBus = new commonComponents(driver);
		action_movetoelement(Schedules_n_Maps).build().perform();
		waitForElementToAppear(dropdownselect);
		dropdownselect.click();
		waitForElementToAppear(BusSchedules);
		return BusSchedules.isDisplayed();
	}
	
	
}
