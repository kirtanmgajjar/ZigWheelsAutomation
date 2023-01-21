package baseClass;

import java.io.InputStream;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import utilities.DriverSetup;
import utilities.ListenerUtility;
import utilities.PropertiesFileReader;

//Base class to be extended in the test classes
@Listeners(ListenerUtility.class)
public class BaseClass 
{
	//Declaring static variable to be used throughout all the test cases
	public static WebDriver driver;
	public static String browser;
	public static String baseUrl;
	public static Actions action;
	public static PropertiesFileReader reader;
	public static WebDriverWait eWait;
	public static FluentWait<WebDriver> fWait;
	public static JavascriptExecutor jse;
	public static String check;
	public static InputStream configFile;
	
	//Method to setup driver and to define the variables
	@BeforeSuite
	public void configuration()
	{
		try
		{
			//Getting config.properties file from the resources folder as stream
			configFile = this.getClass().getResourceAsStream("/config.properties");
			reader = new PropertiesFileReader(configFile);
			browser = reader.getBrowserName();
			baseUrl = reader.getBaseUrl();
			
			//Starting the browser
			driver = DriverSetup.setupDriver(browser);
			
			//Instantiating Action class object
			action = new Actions(driver);
			
			//Instantiating JavascriptExecutor class object
			jse = (JavascriptExecutor) driver;
			
			//Specifying the different waits to be used in the test automation
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
			eWait = new WebDriverWait(driver, Duration.ofSeconds(10));
			fWait = new FluentWait<>(driver);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	//Method to open the Homepage of the website
	@BeforeSuite(dependsOnMethods = {"configuration"})
	public void openUrl()
	{
		try
		{
			driver.get(baseUrl);
			System.out.println("_______________________________________________________________________________________________________");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	//Method to close the driver after the suite
	@AfterSuite
	public void closeDriver() 
	{
		try 
		{
			driver.quit();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	//Method to separate the output printed in the console
	@AfterClass
	public void differentiatingNewTest()
	{
		System.out.println("_______________________________________________________________________________________________________");

	}
}
