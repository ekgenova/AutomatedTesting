package com.qa.quickstart.thedemosite;

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
	}
}
