package ru.greg3d;

import java.util.logging.Level;

import org.apache.commons.logging.impl.SimpleLog;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import ru.greg3d.asserts.Assert;
import ru.greg3d.browsers.BrowserDriver;
import ru.greg3d.util.PropertyLoader;
import ru.st.selenium.applogic.*;
import ru.st.selenium.applogic1.*;
import ru.st.selenium.model.User;
import ru.stqa.selenium.factory.WebDriverFactory;

@Listeners({TestBase.LogListener.class})
public class TestBase {
	
	protected ApplicationManager app;
	
	protected WebDriver driver;

	protected String gridHubUrl;

	protected String baseUrl;

	protected static Logger LOG = LoggerFactory.getLogger(TestBase.class);
	
	@BeforeSuite
	public void SuiteSetup() {
		LOG.error("test");
		//baseUrl = PropertyLoader.loadProperty("site.url");	
		//driver = new TracingWebDriver(BrowserDriver.newDriver()).getWrappedDriver();
		app = new ApplicationManager1();
	}
	
	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		app.stop();
	}


	
	
	public static class LogListener implements IInvokedMethodListener {

		@Override
		public void afterInvocation(IInvokedMethod m, ITestResult res) {
			LOG.info("<<< @Test " + m.getTestMethod().getMethodName());
		}

		@Override
		public void beforeInvocation(IInvokedMethod m, ITestResult res) {
			LOG.info(">>> @Test " + m.getTestMethod().getMethodName());
		}
	}
}
