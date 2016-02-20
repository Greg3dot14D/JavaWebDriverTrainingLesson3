package ru.st.selenium.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.greg3d.util.Fields;
import ru.greg3d.util.WaitUtils;

/*
 * Abstract class representation of a Page in the UI. Page object pattern
 * 
 * @author Sebastiano Armeli-Battana
 */
public abstract class Page {

	protected String url = "/";
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected PageManager pages;

	private boolean acceptNextAlert = true;
	
	/*
	 * Constructor injecting the WebDriver interface
	 * 
	 * @param webDriver
	 */
	public Page(PageManager pages) {
		this.pages = pages;
		driver = pages.getWebDriver();
		wait = new WebDriverWait(driver, 10);
	}

	public WebDriver getWebDriver() {
		return driver;
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public Page ensurePageLoaded() {
		return this;
	}
	
	public boolean pageIsOpen(){
		return driver.getCurrentUrl().contains(this.getUrl());
	}
	
	private String getUrl(){
		return (Fields.getValueOfString(this, "url")); 
	}

	public boolean waitPageLoaded() {
		try {
			ensurePageLoaded();
			return true;
		} catch (TimeoutException to) {
			return false;
		}
	}
	
	protected String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} 
		finally {
			acceptNextAlert = true;
			// дожидаемся обновления страницы
			WaitUtils.WaitPageIsNotActive(driver);
			WaitUtils.WaitPageIsActive(driver);
		}
	}
	
}
