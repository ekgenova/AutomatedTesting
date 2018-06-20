package com.qa.quickstart.demoqa;

import static org.junit.Assert.*;

import org.junit.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class actions {

	static ExtentReports extent;
	ChromeDriver driver;
	
	//URLS
	private String droppableURL = "http://demoqa.com/droppable/";
	private String selectableURL = "http://demoqa.com/selectable/";

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
		DemoQAPage page = PageFactory.initElements(driver, DemoQAPage.class);
		page.navigate(driver, droppableURL);
		page.drop(driver);
		assertEquals("Dropped!", page.getDroppable().getText());
		
		ExtentTest test = extent.startTest("Droppable item");
		try {
			test.log(LogStatus.PASS, "Test passed successfully!");
		} catch (AssertionError e) {
			test.log(LogStatus.FAIL, "Test failed");
			fail();
		} finally {
			test.log(LogStatus.INFO, "Automated: move draggable box over another.");
			test.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
			extent.endTest(test);
		}
	}
	
	@Test
	public void selectableTest() {
		DemoQAPage page = PageFactory.initElements(driver, DemoQAPage.class);
		page.navigate(driver, selectableURL);
		page.select(driver);
		assertEquals(page.getSelectable().isSelected(), driver.findElementById("selectable").isSelected());
		
		ExtentTest test2 = extent.startTest("Selectable item");
		try {
			test2.log(LogStatus.PASS, "Test passed successfully!");
		} catch (AssertionError e) {
			test2.log(LogStatus.FAIL, "Test failed");
		} finally {
			test2.log(LogStatus.INFO, "Automated: Selecting a selectable element.");
			test2.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
			extent.endTest(test2);
		}
	}
	
	@After
	public void close() {
		driver.quit();
		extent.flush();
	}
	
	
}
