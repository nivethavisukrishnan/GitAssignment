package Selenium_Framework.Translink_UI;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;



public class BusSchedulesPage extends CommonComponents{
	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	public BusSchedulesPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	
	@FindBy(id="find-schedule-searchbox")
	WebElement searchbox;
	
	@FindBy(className="searchResultsList")
	WebElement searchresult;
	
	//collective List of routes 
	@FindBy(xpath ="//output[@class='searchResultsList']/article[@class='SearchResultItem']")
	List<WebElement> searchresultsaslist;
	
	@FindBy(css="button.flexContainer")
	WebElement findSchedulebutton;

	public Boolean findScheduleForRoute(String routeSelectionString) throws InterruptedException {
		Boolean isroutePresent = false;
		
		//Assert.assertTrue(driver.getTitle().contains("Bus"),"Page is not loaded as expected");
		action_movetoelement(searchbox).click().sendKeys("99").keyDown(Keys.ENTER).build().perform();
		findSchedulebutton.click();
		waitForElementToAppear(searchresult);
		for(WebElement searchitem: searchresultsaslist) {
			if(searchitem.getText().equals(routeSelectionString)) {
				isroutePresent = true;
				action_movetoelement(searchitem.findElement(By.tagName("a"))).click().build().perform();
				Thread.sleep(3000);
				break;
			}
		}
		return isroutePresent;
	}



}
