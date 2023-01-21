package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

//Class to setup drivers for Edge, Chrome and Firefox
public class DriverSetup 
{
	private static WebDriver driver;
	
	//Method to setup driver as per given choice
	public static WebDriver setupDriver(String choice)
	{
		
		if (choice.equalsIgnoreCase("Chrome"))
		{
			
			WebDriverManager.chromedriver().setup();
			
			ChromeOptions co = new ChromeOptions();
			
			//To start the browser in maximized window
			co.addArguments("start-maximized");
			
			//To disable the notifications
			co.addArguments("--disable-notifications");
				
			//Creating WebDriver class for the Chrome browser
			driver = new ChromeDriver(co);
		}
		
		else if (choice.equalsIgnoreCase("Firefox"))
		{
			
			WebDriverManager.firefoxdriver().setup();
			FirefoxBinary fb = new FirefoxBinary();
			FirefoxOptions fo = new FirefoxOptions();
			fo.setBinary(fb);
			
			//To disable the notifications
			fo.addPreference("dom.webnotifications.enable", false);
			
			//To disable the notification for location
			fo.addPreference("geo.enabled", false);
			
			//Creating WebDriver class for the Firefox browser
			driver = new FirefoxDriver(fo);
			
			//To maximize the browser window
			driver.manage().window().maximize();
		}
		
		else
		{
			WebDriverManager.edgedriver().setup();
			EdgeOptions eo = new EdgeOptions();
			
			//To maximize the browser window
			eo.addArguments("start-maximized");
			
			//To disable the notifications
			eo.addArguments("--disable-notifications");
			
			// Creating WebDriver class for the Edge browser
			driver = new EdgeDriver(eo);
		}
		
		return driver;
	}
	
}
