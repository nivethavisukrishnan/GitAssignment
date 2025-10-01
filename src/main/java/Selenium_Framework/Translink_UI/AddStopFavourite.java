package Selenium_Framework.Translink_UI;


import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddStopFavourite extends CommonComponents{
	WebDriver driver;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	String inputdate;
	
	public AddStopFavourite(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(xpath = "//button[@data-infowindow='Add to Favourites' and contains(., 'Add to favourites')]")
	WebElement AddtoFavButton;
	
	@FindBy(id ="add-to-favourites_dialog")
	WebElement AddtoFavDialog;
	
	@FindBy(name = "gtfsFavouriteKey")
	WebElement AddfavText;
	
	@FindBy(css = "button[form='addGTFSFavourite']")
	WebElement  clickaddtoFav;
	
	@FindBy(xpath = "//a[@title=\"Link to 'My Favourites' page on this site\" and contains(.,\"Manage my favourites\")]")
	WebElement ManageFavButton;
	
	@FindBy(css = ".GTFSFavouritesList .useButton .verticallyCenteredItem")
	List<WebElement> MyFavList;


	public Boolean addStopasFavourite(String FavouriteStopName) throws InterruptedException {
		Boolean matchfound =false;
		waitForElementToAppear(AddtoFavButton);
		AddtoFavButton.click();
		waitForElementToAppear(AddtoFavDialog);
		AddfavText.clear();
		AddfavText.sendKeys(FavouriteStopName);
		Thread.sleep(1000);
		clickaddtoFav.click();
		waitForElementToAppear(ManageFavButton);
		ManageFavButton.click();
		Thread.sleep(2000);
		for(WebElement fav: MyFavList) {
			if(fav.getText().equals(FavouriteStopName)) {
				matchfound =true;
				break;
			}
		}
		return matchfound;
		
	}
	
}
