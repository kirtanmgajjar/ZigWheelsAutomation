Problem Statement: Identify New Bikes

Display upcoming bikes details like, bike name, its price and expected launch date in India
-Manufacturer should be Honda.
-Bike price should be less than 4Lac


Detailed Description:
-Display "Upcoming" bikes details like bike name, price and expected launch date in India, for manufacturer 'Honda' & Bike price should be less than 4Lac.
-For Used cars in Chennai, extract all the popular models in a List; Display the same
-Try to 'Login' with google, give invalid account details & capture the error message (Suggested site: zigwheels.com however you are free to use any legitimate site)


Key Automation Scope:
-Handling windows & frames
-Filling simple form, Capture warning message
-Extract menu items from frames & store in collections
-Navigating back to home page


About the Project:
src/main/java has the packages baseClass and utilities
-baseClass package has the BaseClass which contains methods to be performed at the start and the end of suite, test and class for all the test classes. 
-utilities package has the java class for driver setup,  excel reading and writing, Listener class which interfaces ITestNGListener, test Report generator for the tests run, locators to be used in the Test Classes, properties file reader and capturing the screen shot. 

src/main/resources folder has the config.proprties file which contains the name of the browser used to run the tests and the link of the main page of the website namely the baseUrl

src/test/java folder has the packages upcomingBikes, usedCars, invalidLogin which contains the test classes UpcomingBikesTest, UsedCarsTest, InvalidLoginTest respectively. 

src/test/resources folder has the Data.xlsx file which contains the test data used for running the tests. 

Reports folder has the Report.html file which is the report generated after running the tests.

Screenshot folder will contain the screenshots for the failed tests. 

Test.xlsx file is the test summary report.
 
pom.xml contains the dependencies required by the project.
 
testng.xml file is for running the test cases.


Tools and Technologies used:
-Selenium with Java in Eclipse IDE
-TestNG
-Maven
-WebDriverManager- Boni García
-Apache POI
-ExtentReports


By: Kirtan Gajjar
