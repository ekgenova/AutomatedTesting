package com.qa.quickstart.thedemosite;

import static org.junit.Assert.*;

import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class basic {

	static ExtentReports extent;
	ChromeDriver driver;
	
	@BeforeClass
	public static void set() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\eclipse-workspace\\chromedriver.exe");
		extent = new ExtentReports("C:\\Users\\Admin\\automated testing\\thedemosite\\reports\\reportDemosite.html", true);
	}
	
	@Before
	public void setup() {
		driver = new ChromeDriver();
	}
	
	
	@Test
	public void createAndLogIn () {
		driver.manage().window().maximize();
		String url = "http://thedemosite.co.uk/";
		String addbutton = "/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]";
		String loginButton = "/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]";
		String message = "/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b";
		String username = "automated";
		String password = "password";
		driver.navigate().to(url);
		driver.findElementByXPath(addbutton).click();
		driver.findElementByName("username").sendKeys(username);
		driver.findElementByName("password").sendKeys(password);
		driver.findElementByName("FormsButton2").click();
		driver.findElementByXPath(loginButton).click();
		driver.findElementByName("username").sendKeys(username);
		driver.findElementByName("password").sendKeys(password);
		driver.findElementByName("FormsButton2").click();
		assertEquals("**Successful Login**", driver.findElementByXPath(message).getText());
		
		
		ExtentTest test = extent.startTest("Create/Login user");
		try {
			test.log(LogStatus.PASS, "Test passed successfully!");
		} catch (AssertionError e) {
			test.log(LogStatus.FAIL, "Test failed");
			fail();
		} finally {
			test.log(LogStatus.INFO, "Automated: Create new user, log in as user, confirm user logged in");
			test.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
			extent.endTest(test);
		}
	}
	
	@After
	public void tearDown() {
		driver.quit();
		extent.flush();
	}
}
