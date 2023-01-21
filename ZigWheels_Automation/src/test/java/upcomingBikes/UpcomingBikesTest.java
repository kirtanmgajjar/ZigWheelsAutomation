package upcomingBikes;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static utilities.ListenerUtility.setPassMessage;
import static utilities.LocatorUtility.*;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import baseClass.BaseClass;

public class UpcomingBikesTest extends BaseClass
{
	
	//Method to move the cursor to the New Bikes to display the Hover box
	@Test(testName = "Displaying Hover Box",
			description = "To verify if dropdown box appears after hovering the cursor on \"New Bikes\" menu")
	public void hoverBoxMenu() throws Exception
	{
		action.moveToElement(driver.findElement(newbikes)).perform();
		eWait.until(ExpectedConditions.visibilityOf(driver.findElement(hoverBoxPath1)));
		assertTrue(driver.findElement(hoverBoxPath1).isDisplayed(),"Hover box is not displayed");
		setPassMessage("Hover box is displayed successfully");
	}
	
	//Method to click on the Upcoming Bikes link
	@Test(testName = "Navigation to Upcoming Bikes",
			dependsOnMethods = {"hoverBoxMenu"},
			description = "To verify if hypertext link is functioning by clicking on \"Upcoming Bikes\"")
	public void upcomingBikesButton() throws Exception
	{
		driver.findElement(UpcomingBikesButton).click();
		
		check = driver.findElement(checkMsg).getText();
		assertEquals(check,"Upcoming Bikes in India");
		
		setPassMessage("Upcoming Bikes button is clicked and upcoming bikes webpage is opened");
	}
	
	//Method to check if the Manufacturer dropdown is displayed and enable
	@Test(testName = "Manufacturer dropdown menu",
			dependsOnMethods = {"upcomingBikesButton"},
			description = "To verify if dropdown menu appears when user clicks on input field labelled as \"Manufacturers\"")
	public void manufacturerDropdown()
	{
		assertTrue(driver.findElement(Manufacturer).isEnabled(),"Manufacturers dropdown is displayed and enabled");
		setPassMessage("Manufacturers dropdown menu is displayed and working as per requirement");
	}
	
	//Method to select the Honda Manufacturer
	@Test(testName = "Selecting Honda from Manufacturers dropdown",
			dependsOnMethods = {"manufacturerDropdown"},
			description = "To verify if user is able to click \"Honda\" from Manufacturers dropdown list")
	public void selectBrand() throws Exception 
	{
		String brand = "Honda";
		
		eWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(Manufacturer)));
		Select manufacturers = new Select(driver.findElement(Manufacturer));
		manufacturers.selectByVisibleText(brand);
		
		check = driver.findElement(checkMsg).getText();
		
		assertEquals(check,"Honda Upcoming Bikes in India");
		
		setPassMessage("Honda brand is successfully selected for viewing upcoming bike list");
    }
	
	//Method to click on the View More Bikes button
	@Test(testName = "Clicking View More Bikes button",
			dependsOnMethods = {"selectBrand"},
			description = "To validate if user is able to click on \"View More Bikes\" button")
	public void viewMoreButton() throws Exception
	{
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		eWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(ViewMore)));
		jse.executeScript("arguments[0].click();", driver.findElement(ViewMore));
		assertFalse(driver.findElement(ViewMore).isDisplayed());
		
		setPassMessage("View More Bikes button is clicked");

	}
	
	//Method to get the data of upcoming bikes and display them on console 
	//after filtering for price less than 4 Lakh
	@Test(testName = "Displaying Honda Upcoming Bikes",
			dependsOnMethods = {"viewMoreButton"},
			description = "To validate whether the more upcoming Honda bikes are displayed, when user clicks on")
	public void displayUpcomingBikes() 
	{
		List<WebElement> bikeNameElements = driver.findElements(bikeNamePath);
		List<WebElement> bikePriceElements = driver.findElements(bikePricePath);
		List<WebElement> bikeLaunchDateElements = driver.findElements(bikeLaunchDatePath);
		
		List<String[]> upcomingBikes = new ArrayList<>();
		float[] prices = new float[bikeNameElements.size()];
		String name,price,launchDate;
		
		for (int i=0; i<bikeNameElements.size(); i++)
		{
			name = bikeNameElements.get(i).getText().strip();
			price = bikePriceElements.get(i).getText().strip();
			launchDate = bikeLaunchDateElements.get(i).getText().strip();
			String[] bike = {name,price,launchDate};
			upcomingBikes.add(bike);
			
			if(upcomingBikes.get(i)[1].contains(",")) 
				prices[i]=Float.valueOf(upcomingBikes.get(i)[1].replace(",","").replace("Rs. ", ""))/100000;
			else
				prices[i]=Float.valueOf(upcomingBikes.get(i)[1].replace(" Lakh","").replace("Rs. ", ""));
		}
		
		System.out.println("The Upcoming Bikes are:");
		System.out.println("_______________________________________________________________________________________________________");
		for (int i=0; i<upcomingBikes.size() ; i++)
		{
			if(prices[i]<4.0) 
			{
			System.out.println(upcomingBikes.get(i)[0] + " | " + upcomingBikes.get(i)[1] + " | " + upcomingBikes.get(i)[2]);	
			}
		}
		
		
		assertTrue(upcomingBikes.size()>0);
		
		setPassMessage("Upcoming Bikes list is displayed successfully");
	}
	
	//Method to navigate back to homepage by clicking on the ZIGWHEELS image
	@Test(testName = "Navigation to Homepage", 
			description = "To validate if user is able to navigate back to home page from Upcoming Bikes Page upon clicking on \"ZIGWHEELS.COM\" logo",
			priority = 2)
	public void upcomingBikesToHomepage()
	{

		driver.findElement(homePath).click();
		
		check = driver.getTitle();
		
		assertEquals(check, "New Cars & Bikes, Prices, News, Reviews, Buy & Sell Used Cars - ZigWheels.com");
		setPassMessage("Navigation to home is successful");
	}
	
}