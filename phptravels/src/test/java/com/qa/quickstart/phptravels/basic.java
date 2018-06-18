package com.qa.quickstart.phptravels;
import static org.junit.Assert.*;

import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;

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
	
}
