package ru.st.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class MoviePage extends DefaultPage {
	private final String url = "/?go=movie&id=";
	
	@FindBy(css="img[title='Remove']")
	private WebElement imgRemove;
	
	public MoviePage(PageManager page) {
		super(page);
	}

	public MoviePage imgRemoveClick(){
		imgRemove.click();
		return this;
	}

	public MoviePage acceptRemove(){
		wait.until(alertIsPresent()).accept();
		return this;
	}
	
	public MoviePage dismissRemove(){
		wait.until(alertIsPresent()).dismiss();
		return this;
	}
}
