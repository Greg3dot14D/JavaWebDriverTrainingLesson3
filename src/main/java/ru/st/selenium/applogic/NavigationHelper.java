package ru.st.selenium.applogic;

public interface NavigationHelper {

	void openMainPage();

	void openRelativeUrl(String url);

	void gotoUserProfilePage();

	void gotoUserManagementPage();

	void gotoHome();

	void gotoAddNewFilmForm();
	
	boolean addFilmPageIsOpen();
}
