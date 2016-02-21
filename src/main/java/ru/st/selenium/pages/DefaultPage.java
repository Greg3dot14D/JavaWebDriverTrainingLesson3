package ru.st.selenium.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DefaultPage extends AnyPage {

	@FindBy(xpath = "//a[contains(text(),'Home')]")
	private WebElement homeHref;

	public DefaultPage(PageManager page) {
		super(page);
	}

	public DefaultPage clickHomeHref() {
		homeHref.click();
		return this;
	}

	protected void anyInputClear(WebElement element) {
		if (!element.getAttribute("value").toString().equals("")) {
			element.sendKeys(Keys.CONTROL + "a");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			element.sendKeys(Keys.DELETE);
		}
	}
}
