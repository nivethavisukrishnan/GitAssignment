package Selenium_Framework.Translink_UI;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BusRoutePage extends CommonComponents{
	WebDriver driver;
	JavascriptExecutor js;
	String inputdate;
	WebElement stoplink;
	ArrayList<String> stop_times = new ArrayList<>();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
	
	public BusRoutePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
		
	}

	
	 
	@FindBy(id="schedules")
	WebElement checkbusRoutepagelocator;
	
	@FindBy(xpath="//input[@name='startTime']")
	WebElement starttimeInput;
	
	@FindBy(xpath="//input[@name='endTime']")
	WebElement endtimeInput;
	
	@FindBy(xpath="//button[@class='flexContainer' and contains(.,'Search')]")
	WebElement searchSchedules;

	String stoprowstr ="//table[@id ='DesktopSchedulesTable']/tbody/tr";
	
	@FindBy(xpath="//table[@id ='DesktopSchedulesTable']/tbody/tr")
	List<WebElement>  stopsList;
	
	@FindBy(xpath = "//button[@data-infowindow='Add to Favourites' and contains(., 'Add to favourites')]")
	WebElement AddtoFavButton;

	public Boolean searchForStop(int daystoAdd, String starttime, String endtime, String desiredstop) throws InterruptedException {
		Boolean isStopavailable =false;
		waitForElementToAppear(checkbusRoutepagelocator);
		inputdate = LocalDate.now().plusDays(daystoAdd).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		js.executeScript("document.querySelector('input[name=\"startDate\"]').value = arguments[0]", inputdate);
		Thread.sleep(3000);
		//a.moveToElement(driver.findElement(By.xpath("//input[@name='startTime']"))).click().keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(starttime).build().perform();	
		//a.moveToElement(driver.findElement(By.xpath("//input[@name='startTime']"))).sendKeys(Keys.chord(Keys.CONTROL, "a")).sendKeys(starttime).sendKeys(Keys.TAB).build().perform();
		starttimeInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));  // Select all
		starttimeInput.sendKeys(starttime); 
		endtimeInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));  // Select all
		endtimeInput.sendKeys(endtime);
		searchSchedules.click();
		Thread.sleep(3000);	
		js.executeScript("window.scrollBy(0,700)");
		for(WebElement stop : stopsList) {
			if(stop.getText().contains(desiredstop)){
				isStopavailable =true;
				stoplink = stop;
			}
		}
		return isStopavailable;
	}
	
	public void selectDesiredStop() {
		action_movetoelement(stoplink).click().build().perform();
		stoplink.click();
		//waitForElementToAppear(AddtoFavButton);
	}
	
	public void getTimesForBusStop() {
		for(int i =0; i < stopsList.size(); i++) {
			if(stopsList.get(i).getText().contains("#50913")) {
				List<WebElement> timelist = driver.findElements(By.xpath(stoprowstr+"["+(i+1)+"]/td"));
				for(WebElement tlist: timelist) {
					Boolean isVisible = (Boolean) js.executeScript(
					        "return arguments[0].offsetHeight > 0 && arguments[0].offsetWidth > 0;", tlist
					    );
					if(isVisible) {
						highlightElement(driver, tlist);
						if(stop_times.size() <=4) {
				        	stop_times.add(tlist.getText());
				        	}
					}
				}
					
			}
		}		
	}
	
	public ArrayList<String> verifyTimeIntervals() {
		ArrayList<String> failingTimeIntervals = new ArrayList<String>();
		long diff;
		for(int i=0; i< stop_times.size(); i++) {
        	for (int j = i+1; j <stop_times.size(); j++) {

        		//System.out.println(LocalTime.parse(stop_times.get(i).trim().toUpperCase(),formatter));
        		//System.out.println(LocalTime.parse(stop_times.get(j).trim().toUpperCase(),formatter));
        		//System.out.println();
        		diff = ChronoUnit.MINUTES.between(LocalTime.parse(stop_times.get(i).trim().toUpperCase(),formatter), LocalTime.parse(stop_times.get(j).trim().toUpperCase(),formatter));
        		if (diff  > 60 ) {
                    System.out.println("Diff between "+stop_times.get(i)+ " and "+ stop_times.get(j) + "is beyond 60 minutes.");
                    failingTimeIntervals.add("Diff between "+stop_times.get(i)+ " and "+ stop_times.get(j) + "is beyond 60 minutes.");
        		}
        	}
		}
		return failingTimeIntervals;}
	
	public Boolean verifyTimeDisplayOrder() {
		ArrayList<LocalTime> sortedtimes = new ArrayList<LocalTime>();
		ArrayList<LocalTime> originaltimes = new ArrayList<LocalTime>();
		Boolean issorted = false;
		for(int i=0; i < stop_times.size(); i++) {
			originaltimes.add(LocalTime.parse(stop_times.get(i).trim().toUpperCase(),formatter));
			sortedtimes.add(LocalTime.parse(stop_times.get(i).trim().toUpperCase(),formatter));
		}
		Collections.sort(sortedtimes);
		if(originaltimes.equals(sortedtimes)) {
			issorted =  true;
			System.out.println(originaltimes);
		}
		return issorted;

	}


}
