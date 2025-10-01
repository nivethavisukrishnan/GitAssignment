package Selenium_Framework.Translink_UI;



import translinkUI.TestComponents.BaseTest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;


public class SearchBusScheduleAndClickTest extends BaseTest{

	String routeSelectionString = "#99 - UBC B-Line";
	int daystoAdd = 1;
	String starttime ="0730AM";
	String endtime = "0830AM";	
	String desiredstop ="#50913";

	@Test(description = "Verify bus schedules page is loaded successfully")
	public void selectBusdropdown() throws IOException, InterruptedException {
		//LandingPage landingpage = launchApplication();
		Assert.assertTrue(launchApplication().selectBusPage(), "Bus Schedules page is loaded successfully");
		}
	
	@Test(description = "Verify User is able to select a route and click on it", dependsOnMethods = {"selectBusdropdown"})
	public void searchforStop() throws InterruptedException, IOException {
		BusSchedulesPage busschedulespage = new BusSchedulesPage(driver);
		Assert.assertTrue(busschedulespage.findScheduleForRoute(routeSelectionString), "Bus Route is not available for user to seach");
		takesScreenshot("SearchBusScheduleAndClickTest");
	}
	



}
