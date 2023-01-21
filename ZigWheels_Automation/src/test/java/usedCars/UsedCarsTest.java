package usedCars;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utilities.ListenerUtility.setPassMessage;
import static utilities.LocatorUtility.*;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import baseClass.BaseClass;

public class UsedCarsTest extends BaseClass
{
	
	//Method to move the cursor to the Used Cars to display the Hover box
	@Test(testName = "Displaying Hover Box",
			description = "To validate if the dropdown box apperars when the cursor is hovered on \"Used Cars\"")
	public void hoverTest() throws Exception
	{
		action.moveToElement(driver.findElement(usedCars)).perform();
		eWait.until(ExpectedConditions.visibilityOf(driver.findElement(hoverBoxPath2)));
		assertTrue(driver.findElement(hoverBoxPath2).isDisplayed(),"Hover box is not displayed");
		setPassMessage("Hover box is displayed successfully");
	}
	
	//Method to click the Used Cars in Chennai link
	@Test(testName = "Navigation to Used Cars in Chennai",
			dependsOnMethods = {"hoverTest"}, 
			description = "To validate if the page is redirected to required page when clicked on \"Chennai\" under used cars option")
	public void usedCarsChennaiClick()
	{
		eWait.until(ExpectedConditions.elementToBeClickable(usedCarsChennai));
		driver.findElement(usedCarsChennai).click();
		check = driver.findElement(usedCarsChennaiLabel).getText();
		assertEquals(check, "Used Cars in Chennai");
		setPassMessage("Used Cars in Chennai Page is opened");
	}
	
	//Method to get the Popular Models and display them on the console
	@Test(testName = "Displaying Popular Models",
			dependsOnMethods = {"usedCarsChennaiClick"},
			description = "To validate if the list of popular models of the cars are displayed in filters division")
	public void popularCarsDisplayed() throws Exception
	{
		jse.executeScript("window.scrollBy(0,600);");
		
		List<WebElement> popularCars = driver.findElements(popularCarsPath);
		System.out.println("The popular used cars in Chennai are:");
		System.out.println("_______________________________________________________________________________________________________");
		for (WebElement car : popularCars)
		{
			System.out.println(car.getText());
		}
		
		assertTrue(popularCars.size()>0);
		setPassMessage("Popular Car Models in Chennai are displayed");
	}
	
	//Method to navigate back to homepage by clicking on the ZIGWHEELS image
	@Test(testName = "Back to the Homepage",
			description = "To validate if user is able to navigate back to home page from Used Cars page upon clicking on \"ZIGWHEELS.COM\" logo",
			priority = 2)
	public void usedCarsToHomepage()
	{
		driver.findElement(homePath).click();
		
		check = driver.getTitle();	
		assertEquals(check, "New Cars & Bikes, Prices, News, Reviews, Buy & Sell Used Cars - ZigWheels.com");
		setPassMessage("Navigation to home is successful");
	}
	
}
