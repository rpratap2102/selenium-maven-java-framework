package com.frameworks.selenium.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frameworks.selenium.exceptions.FrameworkException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverHandler {
	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private static final Logger log = LoggerFactory.getLogger(WebDriverHandler.class);

	public static void initialize(String browser) {
		log.info("Initializing the Driver for browser '{}'", browser.toUpperCase());
		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
			return;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
			return;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver());
			return;
		case "safari":
			WebDriverManager.safaridriver().setup();
			driver.set(new SafariDriver());
			return;
		default:
			log.error("Unable to initialize the browser, '{}' Not supported by framework", browser.toUpperCase());
			throw new FrameworkException(browser + "browser not supported by framework");
		}
	}

	public static WebDriver getBrowser() {
		return driver.get();
	}

	public static void close() {
		if (driver.get() != null) {
			log.info("Closing the browser for current thread");
			driver.get().close();
		}
	}
}
