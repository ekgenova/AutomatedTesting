package com.qa.quickstart.thedemosite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class DemositeLogin {

	
	@FindBy(name="username")
	private WebElement usernameInputL;
	
	@FindBy(name="password")
	private WebElement passwordInputL;
	
	@FindBy(name="FormsButton2")
	private WebElement submitButtonL;
	
	@FindBy(css="body > table > tbody > tr > td.auto-style1 > big > blockquote > blockquote > font > center > b")
	private WebElement confirmationL;
	
	/*Confirmation style "**Successful Login**"   */
	
	public void inputUser(WebDriver driver, String username) {
		Actions action = new Actions(driver);
		action.moveToElement(usernameInputL).click().sendKeys(username).perform();
	}
	
	public void inputPass(WebDriver driver, String password) {
		Actions action = new Actions(driver);
		action.moveToElement(passwordInputL).click().sendKeys(password).perform();
	}
	
	public void submit(WebDriver driver) {
		Actions action = new Actions(driver);
		action.click(submitButtonL).perform();
	}
	
	public WebElement getConfirmationElement() {
		return confirmationL;
	}
	
	
}
