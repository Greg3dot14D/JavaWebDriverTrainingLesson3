package ru.st.selenium.applogic1;

import org.openqa.selenium.WebDriver;

import ru.st.selenium.applogic.ApplicationManager;
import ru.st.selenium.applogic.FilmHelper;
import ru.st.selenium.applogic.NavigationHelper;
import ru.st.selenium.applogic.UserHelper;
import ru.greg3d.browsers.BrowserDriver;
import ru.greg3d.browsers.TracingWebDriver;
import ru.greg3d.util.*;

public class ApplicationManager1 implements ApplicationManager {

	private UserHelper userHelper;
	private FilmHelper filmHelper;
	private NavigationHelper navHelper;

	private WebDriver driver;
	private String baseUrl;

	public ApplicationManager1() {
		this.baseUrl = PropertyLoader.loadProperty("site.url");
		driver = new TracingWebDriver(BrowserDriver.getEventFiringWebDriver(BrowserDriver.newDriver()))
				.getWrappedDriver();
		userHelper = new UserHelper1(this);
		filmHelper = new FilmHelper1(this);
		navHelper = new NavigationHelper1(this);

		getNavigationHelper().openMainPage();
	}

	@Override
	public UserHelper getUserHelper() {
		return userHelper;
	}

	@Override
	public FilmHelper getFilmHelper() {
		return filmHelper;
	}

	@Override
	public NavigationHelper getNavigationHelper() {
		return navHelper;
	}

	protected WebDriver getWebDriver() {
		return driver;
	}

	protected String getBaseUrl() {
		return baseUrl;
	}

	@Override
	public void stop() {
		if (driver != null) {
			driver.quit();
		}
	}
}
