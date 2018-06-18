package com.qa.quickstart.autopractice;

import static org.junit.Assert.*;

import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class basic {

	ChromeDriver driver;

	@BeforeClass
	public static void set() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\eclipse-workspace\\chromedriver.exe");
	}
	
	@Before
	public void setup() {
		driver = new ChromeDriver();
	}
	
	@After
	public void close() {
		driver.quit();
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
	}
}
