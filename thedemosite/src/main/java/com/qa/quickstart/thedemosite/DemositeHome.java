package com.qa.quickstart.thedemosite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class DemositeHome {

	
	//private static final String url = "http://thedemosite.co.uk/index.php";
	
	@FindBy(xpath="/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]")
	private WebElement addUserButton;
	
	@FindBy(css="body > div > center > table > tbody > tr:nth-child(2) > td > div > center > table > tbody > tr > td:nth-child(2) > p > small > a:nth-child(7)")
	private WebElement loginButton;
	
	
	public void loadUp(WebDriver driver) {

	}
	
	public void toAddUser(WebDriver driver) {
		Actions action = new Actions(driver);
		action.moveToElement(addUserButton).click().perform();
	}
	
	
	public void toLogin(WebDriver driver) {
		Actions action = new Actions(driver);
		action.click(loginButton).perform();
	}
	
	public WebElement getAddUserButton() {
		return addUserButton;
	}
}
