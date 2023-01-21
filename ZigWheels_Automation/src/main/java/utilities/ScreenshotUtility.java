package utilities;

import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

//Class to capture screenshot
public class ScreenshotUtility 
{
	public static void takeScreenShot(WebDriver driver,String filename) throws Exception
	{
		//Screensot is captured and saved as File
		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		//Screenshot is saved in the required directory
		Files.copy(f,new File("./Screenshot/" + filename + ".png"));
	}
}
