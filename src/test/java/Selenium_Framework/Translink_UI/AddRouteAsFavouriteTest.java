package Selenium_Framework.Translink_UI;



import translinkUI.TestComponents.BaseTest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;


public class AddRouteAsFavouriteTest extends BaseTest{

	String routeSelectionString = "#99 - UBC B-Line";
	int daystoAdd = 1;
	String starttime ="0730AM";
	String endtime = "0830AM";	
	String desiredstop ="#50913";
	String FavouriteStopName = "99 UBC B-Line – Morning Schedule";

	@Test(description = "Verify bus schedules page is loaded successfully")
	public void selectBusdropdown() throws IOException, InterruptedException {
		//LandingPage landingpage = launchApplication();
		Assert.assertTrue(launchApplication().selectBusPage(), "Bus Schedules page is loaded successfully");
		}
	
	@Test(description = "Verify User is able to select a route and click on it", dependsOnMethods = {"selectBusdropdown"})
	public void searchforStop() throws InterruptedException {
		BusSchedulesPage busschedulespage = new BusSchedulesPage(driver);
		Assert.assertTrue(busschedulespage.findScheduleForRoute(routeSelectionString), "Bus Route is not available for user to seach");
	}
	@Test(description = "Verify search and select a stop in a selected timeline",  dependsOnMethods = {"selectBusdropdown", "searchforStop"})
	public void searchAndSelectStop() throws InterruptedException {
		BusRoutePage busroutepage = new BusRoutePage(driver);
		Assert.assertTrue(busroutepage.searchForStop(daystoAdd, starttime, endtime, desiredstop), "Unable to select the bus stop");
		busroutepage.selectDesiredStop();
	}
	
	@Test(description = "Verify Add the stop to Favourites list",  dependsOnMethods = {"selectBusdropdown", "searchforStop", "searchAndSelectStop"})
	public void addstopasFavourite() throws InterruptedException, IOException {
		AddStopFavourite addstopfavourite = new AddStopFavourite(driver);
		Assert.assertTrue(addstopfavourite.addStopasFavourite(FavouriteStopName), "Stop is not added to My favourites list");
		takesScreenshot("AddRouteAsFavouriteTest");
	}
	
	



}
