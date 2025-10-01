package Selenium_Framework.Translink_UI;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonComponents {
	static WebDriver driver;
	static WebDriverWait wait;
	static Actions a;
	public CommonComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		//this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		a = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	


	public static void waitForElementToAppear(WebElement waitforElement) {
		wait.until(ExpectedConditions.visibilityOf(waitforElement));
		
	}
	public static Actions action_movetoelement(WebElement action_gotoelement) {
		
		return a.moveToElement(action_gotoelement);
	}
	
	public static void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", element);
    }



	
	
}
