package com.qa.quickstart.demoqa;

import static org.junit.Assert.*;

import org.junit.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class actions {

	static ExtentReports extent;
	ChromeDriver driver;

	@BeforeClass
	public static void set() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\eclipse-workspace\\chromedriver.exe");
		extent = new ExtentReports("C:\\Users\\Admin\\automated testing\\demoqa\\reports\\reportActions.html", true);
	}
	
	@Before
	public void setup() {
		driver = new ChromeDriver();
	}
	
	@Test
	public void droppableTest() {
		driver.manage().window().maximize();
		String url = "http://demoqa.com/droppable/";
		driver.navigate().to(url);
		WebElement draggable = driver.findElementById("draggableview");
		WebElement droppable = driver.findElementById("droppableview");
		Actions action = new Actions(driver);
		action.dragAndDrop(draggable,droppable).perform();
		assertEquals("Dropped!", droppable.getText());
		
		ExtentTest test = extent.startTest("Droppable item");
		try {
			test.log(LogStatus.PASS, "Test passed successfully!");
		} catch (AssertionError e) {
			test.log(LogStatus.FAIL, "Test failed");
			fail();
		} finally {
			test.log(LogStatus.INFO, "Automated: move draggable box over another."
					+ "");
			test.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
			extent.endTest(test);
		}
	}
	
	@Test
	
	
	@After
	public void close() {
		driver.quit();
		extent.flush();
	}
	
	
}
