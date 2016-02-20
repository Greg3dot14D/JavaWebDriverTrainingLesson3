package ru.st.selenium.applogic1;

import org.openqa.selenium.WebDriver;

import ru.st.selenium.applogic.ApplicationManager;
import ru.st.selenium.applogic.FilmHelper;
import ru.st.selenium.applogic.NavigationHelper;
import ru.st.selenium.applogic.UserHelper;
import ru.greg3d.TracingWebDriver;
import ru.greg3d.browsers.BrowserDriver;
import ru.greg3d.util.*;

public class ApplicationManager1 implements ApplicationManager {

	private UserHelper userHelper;
	private FilmHelper filmHelper;
	private NavigationHelper navHelper;

	private WebDriver driver;
	private String baseUrl;

	public ApplicationManager1() {
		// baseUrl = PropertyLoader.loadProperty("site.url");
		// String gridHubUrl = PropertyLoader.loadProperty("grid2.hub");
		//
		// Browser browser = new Browser();
		// browser.setName(PropertyLoader.loadProperty("browser.name"));
		// browser.setVersion(PropertyLoader.loadProperty("browser.version"));
		// browser.setPlatform(PropertyLoader.loadProperty("browser.platform"));
		//
		// String username = PropertyLoader.loadProperty("user.username");
		// String password = PropertyLoader.loadProperty("user.password");
		//
		// driver = WebDriverFactory.getInstance(gridHubUrl, browser, username,
		// password);

		this.baseUrl = PropertyLoader.loadProperty("site.url");
		// this.driver =
		// BrowserDriver.getEventFiringWebDriver(BrowserDriver.newDriver());
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
