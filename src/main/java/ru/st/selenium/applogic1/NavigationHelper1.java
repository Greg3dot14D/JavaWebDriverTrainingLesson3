package ru.st.selenium.applogic1;

import ru.st.selenium.applogic.NavigationHelper;

public class NavigationHelper1 extends DriverBasedHelper implements NavigationHelper {

	private String baseUrl;

	public NavigationHelper1(ApplicationManager1 manager) {
		super(manager.getWebDriver());
		this.baseUrl = manager.getBaseUrl();
	}

	@Override
	public void openMainPage() {
		driver.get(baseUrl);
	}

	@Override
	public void openRelativeUrl(String url) {
		driver.get(baseUrl + url);
	}

	@Override
	public void gotoUserProfilePage() {
		// driver.findElement(By.cssSelector("nav a[href $= '?go=profile']"))
		// .click();
		openRelativeUrl("?go=profile");
	}

	@Override
	public void gotoUserManagementPage() {
		// driver.findElement(By.cssSelector("nav a[href $= '?go=users']"))
		// .click();
		openRelativeUrl("?go=users");
	}

	@Override
	public void gotoHome() {
		if (!pages.homePage.pageIsOpen())
			pages.defaultPage.clickHomeHref();
	}

	@Override
	public void gotoAddNewFilmPage() {
		if (!pages.addFilmPage.pageIsOpen())
			pages.homePage.imgAddMovieClick();
	}
}
