package com.qa.quickstart.autopractice;

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
		extent = new ExtentReports("C:\\Users\\Admin\\automated testing\\autopractice\\reports\\reportAutopractice.html", true);
	}
	
	@Before
	public void setup() {
		driver = new ChromeDriver();
	}
	
	
	@Test
	public void findDress() {
		driver.manage().window().maximize();
		String url = "http://automationpractice.com/index.php";
		String dress = "#center_column > ul > li:nth-child(1) > div > div.right-block > h5 > a";
		String url2 = "http://automationpractice.com/index.php?id_product=5&controller=product&search_query=dress&results=7";
		driver.navigate().to(url);
		driver.findElementById("search_query_top").sendKeys("dress");
		driver.findElementByName("submit_search").click();
		driver.findElementByCssSelector(dress).click();
		assertEquals(url2, driver.getCurrentUrl());
		
		ExtentTest test = extent.startTest("Create/Login user");
		try {
			test.log(LogStatus.PASS, "Test passed successfully!");
		} catch (AssertionError e) {
			test.log(LogStatus.FAIL, "Test failed");
			fail();
		} finally {
			test.log(LogStatus.INFO, "Automated: Search for 'dress' in search bar, select dress object, confirm selection");
			test.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
			extent.endTest(test);
		}
	}
	
	@After
	public void close() {
		driver.quit();
		extent.flush();
	}
}
