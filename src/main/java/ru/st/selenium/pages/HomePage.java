package ru.st.selenium.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ru.greg3d.util.WaitUtils;

/*
 * Sample page
 * 
 * @author Sebastiano Armeli-Battana
 */
public class HomePage extends DefaultPage {

	private final String url = "/#!/";
	private final String H1_TAG = "h1";

	@FindBy(how = How.TAG_NAME, using = H1_TAG)
	// @CacheLookup
	private WebElement h1Element;

	@FindBy(xpath = "//div[@id='results']/a//div[@class='title']")
	private List<WebElement> filmTitleList;

	@FindBy(id = "q")
	private WebElement searhInput;

	// кнопка Добавить фильм
	@FindBy(css = "img[title='Add movie']")
	private WebElement imgAddMovie;

	@FindBy(id="results")
	WebElement results;
	
	public HomePage(PageManager page) {
		super(page);
	}

	public HomePage ensurePageLoaded() {
		super.ensurePageLoaded();
		wait.until(presenceOfElementLocated(By.cssSelector("#search")));
		return this;
	}

	public List<WebElement> getFilmTitleWebElementList() {
		return filmTitleList;
	}

	public String getH1() {
		return h1Element.getText();
	}

	// выбираем запись в гриде по индексу
	public void selectRecordByIndex(int index){
		results.findElement(By.xpath(String.format("a[%d]/div",index))).click();
	}
	
	public HomePage searhInputClear() {
		if (searhInput.getAttribute("value").toString().equals("Search for movies...")) {
			return this;
		}
		searhInput.sendKeys(Keys.CONTROL + "a");
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		searhInput.sendKeys(Keys.DELETE + "" + Keys.RETURN);
		
		// этот способ ожидания мне не нравится (не нраивтся так же, что иногда вставляю его явно в коде в нужных местах), но он работает :-(
		WaitUtils.WaitPageIsNotActive(driver);
		WaitUtils.WaitPageIsActive(driver);
		
		return this;
	}

	public HomePage searhInputSendKeys(String text) {
		searhInput.sendKeys(text + Keys.RETURN);
		return this;
	}
	
	// нажимаем на кнопку Добавить фильм
	public HomePage imgAddMovieClick(){
		imgAddMovie.click();
		return this;
	}
}
