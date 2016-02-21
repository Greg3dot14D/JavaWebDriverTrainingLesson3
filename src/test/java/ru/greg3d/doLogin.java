package ru.greg3d;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import ru.greg3d.asserts.Assert;
import ru.st.selenium.model.User;

public class doLogin extends TestBase{
	
	@BeforeClass
	public void DoLogin() {
		app.getNavigationHelper().openMainPage();
		User admin = new User().setLogin("admin").setPassword("admin");
		app.getUserHelper().loginAs(admin);
		Assert.assertTrue(app.getUserHelper().isLoggedIn());
	}
	
	@Parameters({"user","password"})
	//@BeforeClass
	public void DoLogin(String user, String password) {
		app.getNavigationHelper().openMainPage();
		User admin = new User().setLogin(user).setPassword(password);
		app.getUserHelper().loginAs(admin);
		Assert.assertTrue(app.getUserHelper().isLoggedIn());
	}
}
