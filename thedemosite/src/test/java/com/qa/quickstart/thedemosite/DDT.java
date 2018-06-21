package com.qa.quickstart.thedemosite;

import java.io.FileInputStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

import java.io.*;

import org.apache.poi.xssf.usermodel.*;
import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DDT {
	
	ChromeDriver driver;
	static ExtentReports report;
	private static final String path = "C:\\Users\\Admin\\automated testing\\thedemosite\\data\\DemositeDDT.xlsx";
	
	@BeforeClass
	public static void set() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\eclipse-workspace\\chromedriver.exe");
		report = new ExtentReports("C:\\Users\\Admin\\automated testing\\thedemosite\\reports\\reportDemositeDDT.html", true);
	}
	
	@Before
	public void setup() {
		driver = new ChromeDriver();
	}

	@Test
	@Ignore
	public void createUsersWithDDT() {
		driver.manage().window().maximize();
		driver.navigate().to("http://thedemosite.co.uk/index.php");
		DemositeHome home = PageFactory.initElements(driver, DemositeHome.class);

		//home.loadUp(driver);
		home.toAddUser(driver);
		
		DemositeAddUser add = PageFactory.initElements(driver, DemositeAddUser.class);

		
		FileInputStream file = null;
		try {
			file = new FileInputStream(path);
		} catch (FileNotFoundException e) {}
		
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(file);
		} catch (IOException e) {}
		
		ArrayList<XSSFCell> userList = new ArrayList<XSSFCell>();
		ArrayList<XSSFCell> passList = new ArrayList<XSSFCell>();
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		for (int i=1;i<6;i++) {
			XSSFCell cell = sheet.getRow(i).getCell(0);
			userList.add(cell);
			XSSFCell cell2 = sheet.getRow(i).getCell(1);
			passList.add(cell2);
		}
		
		for (int i=0;i<5;i++) {
			add.inputUser(driver, userList.get(i).getStringCellValue());
			add.inputPass(driver, passList.get(i).getStringCellValue());
			add.submit(driver);
			
			assertEquals("The username: " + userList.get(i).getStringCellValue() + "\nThe password: " + passList.get(i).getStringCellValue(), add.getConfirmationElement().getText());
		
			ExtentTest testname = report.startTest("Create user");
			try {
				testname.log(LogStatus.PASS, "Test passed successfully!");
			} catch (AssertionError e) {
				testname.log(LogStatus.FAIL, "Test failed");
				fail();
			} finally {
				testname.log(LogStatus.INFO, "Automated: Created new user from DDT." );
				testname.log(LogStatus.INFO, "User created: " + userList.get(i).getStringCellValue());
				testname.log(LogStatus.INFO, "Pass created: " + passList.get(i).getStringCellValue());
				testname.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
				report.endTest(testname);
			}
		}	
	}
	
	
	@Test
	public void createAndLoginFromDDT() {
		driver.manage().window().maximize();
		driver.navigate().to("http://thedemosite.co.uk/index.php");
		DemositeHome home = PageFactory.initElements(driver, DemositeHome.class);

		//home.loadUp(driver);
		home.toAddUser(driver);
		
		DemositeAddUser add = PageFactory.initElements(driver, DemositeAddUser.class);

		
		FileInputStream file = null;
		try {
			file = new FileInputStream(path);
		} catch (FileNotFoundException e) {}
		
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(file);
		} catch (IOException e) {}
		
		ArrayList<XSSFCell> userList = new ArrayList<XSSFCell>();
		ArrayList<XSSFCell> passList = new ArrayList<XSSFCell>();
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		for (int i=1;i<6;i++) {
			XSSFCell cell = sheet.getRow(i).getCell(0);
			userList.add(cell);
			XSSFCell cell2 = sheet.getRow(i).getCell(1);
			passList.add(cell2);
		}
				
		DemositeLogin login = PageFactory.initElements(driver, DemositeLogin.class);

		for (int i=0;i<5;i++) {
			add.inputUser(driver, userList.get(i).getStringCellValue());
			add.inputPass(driver, passList.get(i).getStringCellValue());
			add.submit(driver);
			home.toLogin(driver);
			login.inputUser(driver, userList.get(i).getStringCellValue());
			login.inputPass(driver, passList.get(i).getStringCellValue());
			login.submit(driver);
			
			assertEquals("**Successful Login**", login.getConfirmationElement().getText());
			if (i<4) {
				home.toAddUser(driver);
			}
		}
	}
	
	
	
	@After
	public void tearDown() {
		driver.quit();
		report.flush();
	}
}
