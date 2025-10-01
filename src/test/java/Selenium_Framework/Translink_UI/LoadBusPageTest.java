package Selenium_Framework.Translink_UI;



import translinkUI.TestComponents.BaseTest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;


public class LoadBusPageTest extends BaseTest{
	@Test(description = "Verify bus schedules page is loaded successfully")
	public void selectBusdropdown() throws IOException, InterruptedException {
		
		LandingPage landingpage = launchApplication();
		Assert.assertTrue(landingpage.selectBusPage(), "Bus Schedules page is loaded successfully");
		takesScreenshot("LoadBusPageTest");
		}

}
