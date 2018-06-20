package com.qa.quickstart.demoqa;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class actions {

	static ExtentReports extent;
	ChromeDriver driver;
	DemoQAPage page = PageFactory.initElements(driver, DemoQAPage.class);
	
	//URLS
	private String droppableURL = "http://demoqa.com/droppable/";
	private String selectableURL = "http://demoqa.com/selectable/";
	private String accordionURL = "http://demoqa.com/accordion/";
	private String autocompleteURL = "http://demoqa.com/autocomplete/";

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
	@Ignore
	public void droppableTest() {
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
	@Ignore
	public void selectableTest() {
		page.navigate(driver, selectableURL);
		page.select(driver, page.getSelectable());
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
	
	@Test
	@Ignore
	public void accordionTest() {
		page.navigate(driver, accordionURL);
		page.select(driver, page.getAccordion());
		assertEquals(page.getAccordion().isSelected(), driver.findElementById("accordion").isSelected());
		
		ExtentTest test3 = extent.startTest("Accordion expansion");
		try {
			test3.log(LogStatus.PASS, "Test passed successfully!");
		} catch (AssertionError e) {
			test3.log(LogStatus.FAIL, "Test failed");
		} finally {
			test3.log(LogStatus.INFO, "Automated: Expanding an accordion item.");
			test3.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
			extent.endTest(test3);
		}
	}
	
	@Test
	@Ignore
	public void autocompleteTest() {
		page.navigate(driver, autocompleteURL);
		page.autocomplete(driver);
//		ArrayList<WebElement> autoSuggest = new ArrayList<WebElement> (driver.findElements(By.className("ui-menu-item")));
//		for (int i =0;i<autoSuggest.size();i++)
//		{
//			if(autoSuggest.get(i).getText().equals("Haskell"))
//			{
//			autoSuggest.get(i).click(); 
//			}
//		}
		System.out.println("length of autocorrect list is "+autoSuggest.size());
		//assertEquals("Haskell", page.getAutoSearch().getText());
	}
	
//	@After
//	public void close() {
//		driver.quit();
//		extent.flush();
//	}
	
	
}
