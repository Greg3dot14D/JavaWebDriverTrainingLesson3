package ru.greg3d;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import ru.st.selenium.applogic.*;
import ru.st.selenium.applogic1.*;

@Listeners({TestBase.LogListener.class})
public class TestBase {
	
	protected ApplicationManager app;

	protected static Logger LOG = LoggerFactory.getLogger(TestBase.class);
	
	//@BeforeSuite
	@BeforeTest
	public void SuiteSetup() {
		app = new ApplicationManager1();
	}
	
	//@AfterSuite(alwaysRun = true)
	@AfterTest(alwaysRun = true)
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
