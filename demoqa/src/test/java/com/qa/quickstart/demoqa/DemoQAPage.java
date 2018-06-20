package com.qa.quickstart.demoqa;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;

public class DemoQAPage {

	@FindBy(id="draggableview")
	private WebElement draggable;
	
	@FindBy(id="droppableview")
	private WebElement droppable;
	
	
	public WebElement getDroppable() {
		return droppable;
	}
	
	@FindBy(xpath="//*[@id=\"selectable\"]/li[1]")
	private WebElement selectable;
	
	public WebElement getSelectable() {
		return selectable;
	}
	
		
	public void navigate(WebDriver driver, String url) {
		driver.manage().window().maximize();
		driver.navigate().to(url);
	}
	
	public void drop(WebDriver driver) {
		Actions action = new Actions(driver);
		action.dragAndDrop(draggable, droppable).perform();
	}
	
	public void select(WebDriver driver) {
		Actions action = new Actions(driver);
		action.click(selectable).perform();
	}
}
