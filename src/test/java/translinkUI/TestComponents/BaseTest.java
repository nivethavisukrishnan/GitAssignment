package translinkUI.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import Selenium_Framework.Translink_UI.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;

	
	@BeforeTest
	public WebDriver initializeDriver() throws IOException {
		//Initialize chrome driver
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\translinkUI\\DataResources\\data.properties");
		prop.load(file);
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			
		    WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		/*else if(browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")){
			driver = new EdgeDriver();
		}
		*/
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	public LandingPage launchApplication() throws IOException {
		//driver = initializeDriver();
		LandingPage landingpage =new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
		
	}
	@AfterTest
	public void teardown() {
		driver.close();
	}
	
	public void takesScreenshot(String testCaseName) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		String filename = System.getProperty("user.dir")+"//TestArtifacts//" + testCaseName + "_" + timestamp + ".png";
		File saveschrronshotfile = new File(filename);
		FileUtils.copyFile(source, saveschrronshotfile);
		
	}
	

}
