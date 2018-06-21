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
	public void createUsersWithDDT() {
		
		//Maximise and direct driver to the correct home page
		
		driver.manage().window().maximize();
		driver.navigate().to("http://thedemosite.co.uk/index.php");
		
		//Initialise home page elements
		DemositeHome home = PageFactory.initElements(driver, DemositeHome.class);

		//Direct driver to add user page through Home page
		home.toAddUser(driver);
		
		//Initialise add user page elements
		DemositeAddUser add = PageFactory.initElements(driver, DemositeAddUser.class);

		//Initialise a FileInputStream
		FileInputStream file = null;
		try {
			file = new FileInputStream(path);
		} catch (FileNotFoundException e) {}
		
		//Initialise Excel Spreadsheet workbook through Apache POI and connect it to the FileInputStream
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(file);
		} catch (IOException e) {}
		
		
		//Initialise two arrayLists of excel cells to hold each input from the spreadsheet
		ArrayList<XSSFCell> userList = new ArrayList<XSSFCell>();
		ArrayList<XSSFCell> passList = new ArrayList<XSSFCell>();
		
		//Initialise the sheet that is currently being worked with
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		//Loop through entries in that sheet to populate array lists
		for (int i=1;i<6;i++) {
			XSSFCell cell = sheet.getRow(i).getCell(0);
			userList.add(cell);
			XSSFCell cell2 = sheet.getRow(i).getCell(1);
			passList.add(cell2);
		}
		
		//Loop through entries in the array list to send the inputs on the add user website, 
		//submit them and check whether it has been done successfully.
		for (int i=0;i<5;i++) {
			add.inputUser(driver, userList.get(i).getStringCellValue());
			add.inputPass(driver, passList.get(i).getStringCellValue());
			add.submit(driver);
			
			assertEquals("The username: " + userList.get(i).getStringCellValue() + "\nThe password: " + passList.get(i).getStringCellValue(), add.getConfirmationElement().getText());
		
			//HTML report generator by ExtentReports
			//Shows whether the test passed/failed and some info such as:
			//What the test is, the username used for the test, the password used for the test, the URL for the website
			//Finally, test ends
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
		
		//Maximise and direct driver to the correct home page
		driver.manage().window().maximize();
		driver.navigate().to("http://thedemosite.co.uk/index.php");
		
		//Initialise home page elements
		DemositeHome home = PageFactory.initElements(driver, DemositeHome.class);

		//Direct driver to add user page through Home page
		home.toAddUser(driver);
		
		//Initialise add user page elements
		DemositeAddUser add = PageFactory.initElements(driver, DemositeAddUser.class);

		//Initialise a FileInputStream
		FileInputStream file = null;
		try {
			file = new FileInputStream(path);
		} catch (FileNotFoundException e) {}
		
		
		//Initialise Excel Spreadsheet workbook through Apache POI and connect it to the FileInputStream
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(file);
		} catch (IOException e) {}
		
		//Initialise two arrayLists of excel cells to hold each input from the spreadsheet
		ArrayList<XSSFCell> userList = new ArrayList<XSSFCell>();
		ArrayList<XSSFCell> passList = new ArrayList<XSSFCell>();
		
		//Initialise the sheet that is currently being worked with
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		//Loop through entries in that sheet to populate array lists
		for (int i=1;i<6;i++) {
			XSSFCell cell = sheet.getRow(i).getCell(0);
			userList.add(cell);
			XSSFCell cell2 = sheet.getRow(i).getCell(1);
			passList.add(cell2);
		}
		
		//Initialise the elements for the login page
		DemositeLogin login = PageFactory.initElements(driver, DemositeLogin.class);
		
		/* Need to complete FileOutputStream to output results to second file.
		 *  Best practice not to use same file.
		 */
		
//		FileOutputStream fileOut = null;
//		try {
//			fileOut = new FileOutputStream(path);
//		} catch (IOException e) {e.printStackTrace();}

		
		//Loop through entries to create user then login as user 
		//Returns to the add user page on every entry except the last
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
			
			//HTML report generator by ExtentReports
			//Shows whether the test passed/failed and some info such as:
			//What the test is, the username used for the test, the password used for the test, the URL for the website
			//Finally, test ends
			ExtentTest testname = report.startTest("Create user then log in as that user");
			try {
				testname.log(LogStatus.PASS, "Test passed successfully!");
			} catch (AssertionError e) {
				testname.log(LogStatus.FAIL, "Test failed");
				fail();
			} finally {
				testname.log(LogStatus.INFO, "Automated: Created new user from DDT." );
				testname.log(LogStatus.INFO, "Username: " + userList.get(i).getStringCellValue());
				testname.log(LogStatus.INFO, "Password: " + passList.get(i).getStringCellValue());
				testname.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
				report.endTest(testname);
			}
		}
	}
	
	
	@After
	public void tearDown() {
		driver.quit();
		report.flush();
	}
}
