package ru.st.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.greg3d.Lesson2TestComplect;

public class MoviePage extends DefaultPage {
	private final String url = "/?go=movie&id=";

	public static Logger LOG = LoggerFactory.getLogger(MoviePage.class);
	
	@FindBy(css="img[title='Remove']")
	private WebElement imgRemove;
	
	public MoviePage(PageManager page) {
		super(page);
		// TODO Auto-generated constructor stub
	}

	public MoviePage imgRemoveClick(){
		imgRemove.click();
		LOG.debug(closeAlertAndGetItsText());
		return this;
	}

}
