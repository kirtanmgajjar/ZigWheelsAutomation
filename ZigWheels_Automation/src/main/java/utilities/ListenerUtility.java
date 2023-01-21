package utilities;

import static utilities.ScreenshotUtility.takeScreenShot;

import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;


public class ListenerUtility implements ITestListener
{
	private static final String reportFilePath = "./Report/Report.html";
	private static ExtentReports report = ExtentReportUtility.getReportInstance(reportFilePath); 
	private static ExtentTest test;
	private static String passMessage;
	private static int[] count = {0,0,0};
	private static WebDriver driver;
	private static ExcelUtility excel;
	private static String testExcelPath = "./Test.xlsx";
	
	//Opens the Test.excel file on the start of the Test
	@Override
	public void onStart(ITestContext context)
	{
		try 
		{
			excel = new ExcelUtility(testExcelPath);
			excel.openSheet("TEST CASES");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	//Starts the test and assigining it specific category in the report
	@Override
	public void onTestStart(ITestResult result)
	{
		Method method = result.getMethod().getConstructorOrMethod().getMethod();
		String testName = method.getAnnotation(Test.class).testName();
		
		switch(result.getMethod().getRealClass().getSimpleName())
		{
			case "UpcomingBikesTest":
				count[0]++;
				test = report.createTest(String.format("TC_01_%02d: ", count[0])+ testName);
				test.assignCategory("ZW_TS_01: &nbsp Display &nbsp Upcoming &nbsp Bikes &nbsp List");
				break;
			case "UsedCarsTest":
				count[1]++;
				test = report.createTest(String.format("TC_02_%02d: ", count[1])+ testName);
				test.assignCategory("ZW_TS_02: &nbsp Get &nbsp Popular &nbsp Models &nbsp List &nbsp for &nbsp Used &nbsp Cars &nbsp in &nbsp Chennai");
				break;
			case "InvalidLoginTest":
				count[2]++;
				test = report.createTest(String.format("TC_03_%02d: ", count[2])+ testName);
				test.assignCategory("ZW_TS_03: &nbsp Get &nbsp Error &nbsp Message &nbsp by &nbsp giving &nbsp Invalid &nbsp Account &nbsp Details");
		}
		test.info(result.getMethod().getDescription());
	}
	
	//Prints the pass message in the HTML report
	//Updates the test status and actual results in the Test excel
	@Override
	public void onTestSuccess(ITestResult result)
	{
		test.pass(passMessage);
		String testClass = result.getMethod().getTestClass().getRealClass().getSimpleName();
		try 
		{
			setTestStatusData(testClass,"Pass");
			setTestResultsData(testClass,passMessage);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		passMessage = "";
	}
	
	//Prints the failure result in the HTML report along with a sceenshot
	//Updates the test status and actual results in the Test excel
	@Override
	public void onTestFailure(ITestResult result)
	{
		String testClass = result.getMethod().getTestClass().getRealClass().getSimpleName();
		try 
		{
			setTestStatusData(testClass,"Fail");
			setTestResultsData(testClass,result.getThrowable().getMessage());
			driver = (WebDriver) result.getMethod().getTestClass().getRealClass().getSuperclass().getDeclaredField("driver").get(result.getInstance());
			takeScreenShot(driver, result.getMethod().getConstructorOrMethod().getMethod().getName());
			test.fail(result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromPath("../Screenshot/"+result.getMethod().getConstructorOrMethod().getMethod().getName()+".png").build());
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//Prints the skip message in the HTML report
	//Updates the test status and actual results in the Test excel
	@Override
	public void onTestSkipped(ITestResult result)
	{
		test.skip(result.getThrowable().getMessage());
		String testClass = result.getMethod().getTestClass().getRealClass().getSimpleName();
		try 
		{
			setTestResultsData(testClass,result.getThrowable().getMessage());
			setTestStatusData(testClass,"Skip");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	//Close the excel file
	@Override
	public void onFinish(ITestContext context)
	{
		try 
		{
			excel.closeFile();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		report.flush();
	}
	
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result){}
	
	//Method to get pass message from the test methods
	public static void setPassMessage(String message)
	{
		passMessage = message;
	}
	
	//Method to set the test status in the Test excel
	public static void setTestStatusData(String className,String value) throws Exception
	{
		int[] rowPos = {0,7,11};
		if (className.equals("UpcomingBikesTest"))
			excel.setCellData(rowPos[0]+count[0], 7, value);
		else if (className.equals("UsedCarsTest"))
			excel.setCellData(rowPos[1]+count[1], 7, value);
		else
			excel.setCellData(rowPos[2]+count[2], 7, value);
	}
	
	//Method to set the actual results in the Test excel
	public static void setTestResultsData(String className,String value) throws Exception
	{
		int[] rowPos = {0,7,11};
		if (className.equals("UpcomingBikesTest"))
			excel.setCellData(rowPos[0]+count[0], 6, value);
		else if (className.equals("UsedCarsTest"))
			excel.setCellData(rowPos[1]+count[1], 6, value);
		else
			excel.setCellData(rowPos[2]+count[2], 6, value);
	}
}
