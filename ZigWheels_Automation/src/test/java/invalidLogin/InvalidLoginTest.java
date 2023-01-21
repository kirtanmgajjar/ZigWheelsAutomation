package invalidLogin;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utilities.ListenerUtility.setPassMessage;
import static utilities.LocatorUtility.continueWithGoogleButton;
import static utilities.LocatorUtility.email;
import static utilities.LocatorUtility.emailErrorMsg;
import static utilities.LocatorUtility.loginOrRegisterLabel;
import static utilities.LocatorUtility.loginOrSignUpButton;
import static utilities.LocatorUtility.next;
import static utilities.LocatorUtility.password;
import static utilities.LocatorUtility.passwordErrorMsg;
import static utilities.LocatorUtility.signInLabel;

import java.time.Duration;
import java.util.Iterator;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import utilities.ExcelUtility;

public class InvalidLoginTest extends BaseClass
{
	
	String mainWindow;
	String signInWindow;
	private static ExcelUtility dataExcel;
	
	//Opens the Data Excel file from the resources folder
	@BeforeClass
	public void openDataExcel() throws Exception
	{
		dataExcel = new ExcelUtility(this.getClass().getClassLoader().getResource("Data.xlsx").openStream());
		System.out.println("Error messages for invalid credentials:");
		System.out.println("_______________________________________________________________________________________________________");
	}
	
	//Method to click on the Login/Signup button
	@Test(testName = "Login/Sigup Button",
			description = "To validate the functionality of \"Login/Signup\" button")
	public void loginButton() throws Exception
	{
		driver.findElement(loginOrSignUpButton).click();
		check = driver.findElement(loginOrRegisterLabel).getAttribute("innerHTML");
		assertEquals(check, "Login/Register to <br>ZigWheels");
		setPassMessage("Login/Register page is opened");
	}

	//Method to click on the Continue with Google button to login/signup using google account
	@Test(testName = "Continue with Google button",
			dependsOnMethods = {"loginButton"},
			description = "To validate the functionality of \"Continue with Google\" button")
	public void continueWithGoogle() throws Exception
	{
		fWait.withTimeout(Duration.ofSeconds(10))
		.pollingEvery(Duration.ofSeconds(2))
		.until(driver -> {
			driver.findElement(continueWithGoogleButton).click();
			return driver.getWindowHandles().size() > 1;
		});

		Iterator<String> iter = driver.getWindowHandles().iterator();
		mainWindow = iter.next();
		signInWindow = iter.next();
		driver.switchTo().window(signInWindow);
		check = driver.findElement(signInLabel).getText();
		assertEquals(check, "Sign in");
		setPassMessage("Sign in with Google window is opened");

	}

	//Method to check if the Email box is present and enabled
	@Test(testName = "Email or phone textbox",
			dependsOnMethods = {"continueWithGoogle"}, 
			description = "To verify if email text field is present in the window user is redirected into")
	public void emailTextBox() throws Exception 
	{
		
		driver.findElement(email).click();
		assertTrue(driver.findElement(email).isEnabled());
		setPassMessage("EmailorPhone textbox is present and working as per requirement");
	}
	 
	//Method to input invalid email ID by getting data from the data provider
	@Test(testName = "Login using invalid Email or Phone",
			dependsOnMethods = {"emailTextBox"},
			dataProvider = "invalidEmailOrPhone", 
			description = "To verify functionality of \"Next\" button when user enters invalid e-mail")
	public void invalidEmailOrPhone(String data) 
	{

		driver.findElement(email).sendKeys(data);
		driver.findElement(next).click();
		System.out.println("Error Message : " + driver.findElement(emailErrorMsg).getText());
		driver.findElement(email).clear();
		setPassMessage("Error message is successfully displayed for test data: "+ data);
	}

	//Method to input valid email ID by getting data from the data provider
	@Test(testName = "Login using valid Email",
			dataProvider = "validEmail",
			dependsOnMethods = {"invalidEmailOrPhone"},
			description = "To verify functionality of \"Next\" button when user enters a valid e-mail")
	public void validEmail(String data) 
	{
		
		driver.findElement(email).clear();
		driver.findElement(email).sendKeys(data);
		driver.findElement(next).click();
		setPassMessage("User is successfully redirected to password webpage for test data: " + data);
	}
	
	//Method to check if the Password box is present and enabled
	@Test(testName = "Password textbox",
			dependsOnMethods = {"validEmail"},
			description = "To verify if the Password textbox is present and is able to accept inputs")
	public void passwordTextBox() throws Exception 
	{
		eWait.until(ExpectedConditions.elementToBeClickable(password));
		driver.findElement(password).click();
		assertTrue(driver.findElement(password).isEnabled());
		setPassMessage("Password textbox is present and working as per requirement");
	}

	//Method to input invalid password by getting data from the data provider
	@Test(testName = "Login using invalid Password",
			dataProvider = "invalidPassword",
			dependsOnMethods = {"passwordTextBox"},
			description = "To validate whether the error message is displayed on entering invalid password")
	public void invalidPassword(String Password) 
	{
		driver.findElement(password).sendKeys(Password);
		driver.findElement(next).click();
		System.out.println("Error Message : " + driver.findElement(passwordErrorMsg).getText());
		setPassMessage("Error message is displayed successfully for test data: " + Password);
	}
	
	//Closes the excel
	@AfterClass
	public void closeDataExcel() throws Exception
	{
		dataExcel.closeFile();
	}
	
	//Gets the data from the excel file for invalid Email ID
	@DataProvider(name = "invalidEmailOrPhone")
	public String[] getInvalidEmailOrPhone() throws Exception
	{
		dataExcel.openSheet("Invalid Email or Phone");
		String[] data = new String[dataExcel.getRowCount()];
		for (int i=0; i<dataExcel.getRowCount(); i++)
		{
			data[i] = dataExcel.getCellData(i+1, 0);
		}
		return data;
	}
	
	//Gets the data from the excel file for valid Email ID
	@DataProvider(name = "validEmail")
	public String[] getvalidEmail() throws Exception
	{
		dataExcel.openSheet("Invalid Password");
		String[] data = new String[dataExcel.getRowCount()];
		for (int i=0; i<dataExcel.getRowCount(); i++)
		{
			data[i] = dataExcel.getCellData(i+1, 0);
		}
		return data;
	}
	
	//Gets the data from the excel file for invalid Password
	@DataProvider(name = "invalidPassword")
	public String[] getInvalidPassword() throws Exception
	{
		dataExcel.openSheet("Invalid Password");
		String[] data = new String[dataExcel.getRowCount()];
		for (int i=0; i<dataExcel.getRowCount(); i++)
		{
			data[i] = dataExcel.getCellData(i+1, 1);
		}
		return data;
	}

}
