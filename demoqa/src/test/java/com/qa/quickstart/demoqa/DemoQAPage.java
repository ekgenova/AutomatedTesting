package com.qa.quickstart.demoqa;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	
	@FindBy(css="#ui-id-6")
	private WebElement accordion;
	
	public WebElement getAccordion() {
		return accordion;
	}
	
	@FindBy(id="ui-id-7")
	private WebElement haskellAuto;
	
	@FindBy(id="tagss")
	private WebElement autoSearch;
	
	public WebElement getAutoSearch() {
		return autoSearch;
	}
	
	@FindBy(id="datepicker1")
	private WebElement datepicker;
	
	public WebElement getDatepicker() {
		return datepicker;
	}
	
	@FindBy(css="#ui-datepicker-div > div > a.ui-datepicker-next.ui-corner-all > span")
	private WebElement nextMonth;
		
	public void navigate(WebDriver driver, String url) {
		driver.manage().window().maximize();
		driver.navigate().to(url);
	}
	
	public void drop(WebDriver driver) {
		Actions action = new Actions(driver);
		action.dragAndDrop(draggable, droppable).perform();
	}
	
	public void select(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.click(element).perform();
	}
	
	public void autocomplete(WebDriver driver) {
		
//		WebElement dynamic = (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.id("tagss")));
		
		WebElement newElement = driver.findElement(By.id("tagss"));
		
		
		try{Thread.sleep(3000);}catch(Exception e) {};
		
		
		Actions action = new Actions(driver);
		
		action.moveToElement(newElement).click().sendKeys("h").perform();
		
		action.moveToElement(haskellAuto).click().perform();
		
		ArrayList<WebElement> autoSuggest = new ArrayList<WebElement> (driver.findElements(By.className("ui-menu-item")));
		for (int i =0; i<autoSuggest.size(); i++)
		{
			if(autoSuggest.get(i).getText().equals("Haskell"))
			{
			action.moveToElement(autoSuggest.get(i)).click().perform(); 
			}
		}
		System.out.println("length of autocorrect list is "+autoSuggest.size());
		
	}
	
	public void datePick(WebDriver driver) {
		WebElement dynamic = (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.id("datepicker1")));
		Actions action = new Actions(driver);
		action.moveToElement(dynamic).click().perform();
		action.click(nextMonth);
	}
}
