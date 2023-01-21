package utilities;

import org.openqa.selenium.By;

//Class where all the element locators used in the tests are defined
public class LocatorUtility 
{
	//Locators for Upcoming Bikes Test Scenario
	public static final By hoverBoxPath1 = By.xpath("//*[@href='/newbikes']/following-sibling::*");
	public static final By newbikes = By.xpath("//*[@href='/newbikes']");
	public static final By UpcomingBikesButton = By.xpath("//span[@data-track-label='bikes-upcoming' and text()='Upcoming Bikes']");
	public static final By checkMsg = By.xpath("//div[@class='row']//h1[contains(@class,'pull-left')]");
	public static final By Manufacturer = By.id("makeId");
	public static final By ViewMore = By.xpath("//span[@data-track-label='view-more-models-button']");
	public static final By bikeNamePath = By.xpath("//div[@class='p-15 pt-10 mke-ryt rel']/a");
	public static final By bikePricePath = By.xpath("//div[@class='p-15 pt-10 mke-ryt rel']/div[1]");
	public static final By bikeLaunchDatePath = By.xpath("//div[@class='p-15 pt-10 mke-ryt rel']/div[2]");
	
	//Locator for Homepage button
	public static final By homePath = By.tagName("img");
	
	//Locator for Used Cars Test Scenario
	public static final By usedCars = By.xpath("//*[@href='/used-car']");
	public static final By usedCarsChennai = By.xpath("//*[@city='Chennai']");
	public static final By popularCarsPath = By.xpath("//*[contains(@id,'mmvLi')]/label");
	public static final By hoverBoxPath2 = By.xpath("//*[@href='/used-car']/following-sibling::*");
	public static final By usedCarsChennaiLabel = By.id("usedcarttlID");
	
	//Locator for Invalid Login Test Scenario
	public static final By loginOrSignUpButton = By.id("des_lIcon");
	public static final By loginOrRegisterLabel = By.xpath("//h4[@class='hd-ctr mb-15 zs']/span[2]");
	public static final By signInLabel = By.xpath("//*[@id='headingText']/span");
	public static final By continueWithGoogleButton = By.xpath("//*[@id=\"googleSignIn\"]/span[2]");
	public static final By email = By.xpath("//input[@type='email']");
	public static final By next = By.xpath("//span[text()='Next']");
	public static final By password = By.xpath("//input[@type='password']");
	public static final By emailErrorMsg = By.xpath("//div[@class='o6cuMc']");
	public static final By passwordErrorMsg = By.xpath("//div[@jsname='B34EJ']/span");
}
