package com.frameworks.selenium.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.frameworks.selenium.config.BaseConfig;
import com.frameworks.selenium.config.BaseConfig.BaseConfigSpec;
import com.frameworks.selenium.core.WebDriverHandler;

@Listeners(com.frameworks.selenium.reporting.ReportListner.class)
public class TestBase {

	private static BaseConfigSpec config = BaseConfig.getInstance();
	private static final Logger log = LoggerFactory.getLogger(TestBase.class);

	protected static WebDriver getBrowser() {
		return WebDriverHandler.getBrowser();
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {
		WebDriverHandler.initialize(config.getBrowser());
		WebDriver driver = WebDriverHandler.getBrowser();
		log.info("Navigatting to the base url '{}'", config.getAppURL());
		driver.get(config.getAppURL());
		log.info("Maximizing the browser");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getWaitSeconds()));
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		WebDriverHandler.getBrowser().quit();
	}

}
