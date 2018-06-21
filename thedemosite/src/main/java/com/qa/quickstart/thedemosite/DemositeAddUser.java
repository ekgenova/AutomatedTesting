package com.qa.quickstart.thedemosite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class DemositeAddUser {

	
	@FindBy(name="username")
	private WebElement usernameInput;
	
	@FindBy(name="password")
	private WebElement passwordInput;
	
	@FindBy(name="FormsButton2")
	private WebElement submitButton;
	
	@FindBy(css="body > table > tbody > tr > td.auto-style1 > blockquote > blockquote:nth-child(2) > blockquote")
	private WebElement confirmation;
	
	/*confirmation style   "The username: <username>
	 * 						The password: <password>"
	 */
	
	public void inputUser(WebDriver driver, String username) {
		Actions action = new Actions(driver);
		action.moveToElement(usernameInput).click().sendKeys(username).perform();
	}
	
	public void inputPass(WebDriver driver, String password) {
		Actions action = new Actions(driver);
		action.moveToElement(passwordInput).click().sendKeys(password).perform();
	}
	
	public void submit(WebDriver driver) {
		Actions action = new Actions(driver);
		action.click(submitButton).perform();
	}
	
	public WebElement getConfirmationElement() {
		return confirmation;
	}
}
