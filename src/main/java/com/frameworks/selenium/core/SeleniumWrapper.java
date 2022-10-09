package com.frameworks.selenium.core;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentTest;
import com.frameworks.selenium.core.locators.WebElementLocator;
import com.frameworks.selenium.exceptions.FrameworkException;
import com.frameworks.selenium.reporting.ExtentReportLogger;

public class SeleniumWrapper {

	private static final Logger log = LoggerFactory.getLogger(SeleniumWrapper.class);
	private WebDriver driver;
	private ExtentTest reporterTest;

	public SeleniumWrapper(WebDriver webDriver, ExtentTest reporterTest) {
		this.driver = webDriver;
		this.reporterTest = reporterTest;
	}

	public WebElement getElement(WebElementLocator locator) {
		return getElement(locator, 30);
	}

	public WebElement getElement(WebElementLocator locator, int seconds) {
		try {
			log.info("Trying to find the element '{}' by '{}' using '{}'", locator.getName(), locator.getType(),
					locator.getLocator());
			ExtentReportLogger.addInfo(reporterTest, "Trying to find the element '" + locator.getName() + "' by '"
					+ locator.getType() + "' using '" + locator.getLocator() + "'");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
			wait.until(ExpectedConditions.presenceOfElementLocated(locator.getBy()));
			WebElement element = driver.findElement(locator.getBy());
			ExtentReportLogger.addPass(reporterTest, "Element '" + locator.getName() + "' found'");
			return element;
		} catch (Exception e) {
			log.info("Element not found'" + locator.getName() + "' By '" + locator.getType() + "' Using '"
					+ locator.getLocator() + "'", e);
			ExtentReportLogger.addInfo(reporterTest, "Element not found '" + locator.getName() + "' by '"
					+ locator.getType() + "' using '" + locator.getLocator() + "'");
			throw new FrameworkException(e.getMessage(), e);
		}

	}

	public String getTextofElement(WebElementLocator locator) {
		try {
			log.info("Trying to get '{}' on '{}'", locator.getName(), locator.getPage());
			ExtentReportLogger.addInfo(reporterTest,
					"Trying to get '" + locator.getName() + "' on '" + locator.getPage() + "'");
			String text = getElement(locator).getAttribute("textContent");
			ExtentReportLogger.addPass(reporterTest, "Text of '" + locator.getName() + "' is '" + text + "'");
			return text;
		} catch (Exception e) {
			log.error("Unable to get '" + locator.getName() + "' on '" + locator.getPage() + "'", e);
			ExtentReportLogger.addFail(reporterTest,
					"Unable to get '" + locator.getName() + "' on '" + locator.getPage() + "'");
			throw new FrameworkException(e.getMessage(), e);
		}
	}

	public void clickOnElement(WebElementLocator locator) {
		try {
			log.info("Trying to click at '{} on '{}'", locator.getName(), locator.getPage());
			ExtentReportLogger.addInfo(reporterTest,
					"Trying to click at '" + locator.getName() + "' on '" + locator.getPage() + "'");
			getElement(locator).click();
			log.info("Clicked at'{}'on '{}'", locator.getName(), locator.getPage());
			ExtentReportLogger.addPass(reporterTest,
					"Clicked at '" + locator.getName() + "' on '" + locator.getPage() + "'");
		} catch (Exception e) {
			log.error("Unable to click at '" + locator.getName() + "' on '" + locator.getPage() + "'", e);
			ExtentReportLogger.addFail(reporterTest,
					"Unable to click at '" + locator.getName() + "' on '" + locator.getPage() + "'");
			throw new FrameworkException(e.getMessage(), e);
		}

	}

	public void typeOnElement(WebElementLocator locator, String text) {
		try {
			log.info("Trying to type '{}' at '{} on '{}'", text, locator.getName(), locator.getPage());
			ExtentReportLogger.addInfo(reporterTest,
					"Trying to type '" + text + "' at '" + locator.getName() + "' on '" + locator.getPage() + "'");
			getElement(locator).sendKeys(text);
			log.info("Typed '{}' at '{}'on '{}'", text, locator.getName(), locator.getPage());
			ExtentReportLogger.addPass(reporterTest,
					"Typed '" + text + "' at '" + locator.getName() + "' on '" + locator.getPage() + "'");
		} catch (Exception e) {
			log.error("Unable to type at '" + locator.getName() + "' on '" + locator.getPage() + "'", e);
			ExtentReportLogger.addFail(reporterTest,
					"Unable to type at '" + locator.getName() + "' on '" + locator.getPage() + "'");
			throw new FrameworkException(e.getMessage(), e);
		}

	}

	public String getattributeValue(WebElementLocator locator, String attribute) {
		try {
			log.info("Trying to get '{}' of '{} on '{}'", attribute, locator.getName(), locator.getPage());
			ExtentReportLogger.addInfo(reporterTest,
					"Trying to get '" + attribute + "' of '" + locator.getName() + "' on '" + locator.getPage() + "'");
			String attr = getElement(locator).getAttribute(attribute);
			log.info("Value  of '{}' of '{}'on '{}' is '{}'", attribute, locator.getName(), locator.getPage(), attr);
			ExtentReportLogger.addPass(reporterTest, "Value  of '" + attribute + "' of '" + locator.getName() + "' on '"
					+ locator.getPage() + "' is '" + attr + "'");
			return attr;
		} catch (Exception e) {
			log.error("Unable to get attribute for '" + locator.getName() + "' on '" + locator.getPage() + "'", e);
			ExtentReportLogger.addFail(reporterTest,
					"Unable to get attribute for '" + locator.getName() + "' on '" + locator.getPage() + "'");
			throw new FrameworkException(e.getMessage(), e);
		}
	}

	public void switchWindow(String window) {
		try {
			log.info("Trying to Switch window to handle '" + window + "'");
			ExtentReportLogger.addInfo(reporterTest, "Trying to Switch window to handle '" + window + "'");
			driver.switchTo().window(window);
			log.info("Switched to '" + window + "'");
			ExtentReportLogger.addPass(reporterTest, "Switched to '" + window + "'");
		} catch (Exception e) {
			log.error("Unable to Switch to '" + window + "'", e);
			ExtentReportLogger.addFail(reporterTest, "Unable to Switch to '" + window + "'");
			throw new FrameworkException(e.getMessage(), e);
		}
	}

	public boolean checkIfelementIsPresent(WebElementLocator locator) {
		try {
			log.info("Trying to see if element '{}' is present on the page'{}'", locator.getName(), locator.getPage());
			ExtentReportLogger.addInfo(reporterTest, "Trying to see if the element '" + locator.getName()
					+ "' is present on  '" + locator.getPage() + "'");
			getElement(locator, 2);
			log.info("'{}' is present on the page'{}'", locator.getName(), locator.getPage());
			ExtentReportLogger.addInfo(reporterTest,
					"'" + locator.getName() + "' is present on  '" + locator.getPage() + "'");

			return true;
		} catch (Exception e) {
			log.info("'{}' is not present on the page'{}'", locator.getName(), locator.getPage());
			ExtentReportLogger.addInfo(reporterTest,
					"'" + locator.getName() + "' is not present on  '" + locator.getPage() + "'");
			return false;
		}

	}

	public String getSelectedOption(WebElementLocator locator, String string) {
		try {
			log.info("Trying to get selected option for '{}' on the page'{}'", locator.getName(), locator.getPage());
			ExtentReportLogger.addInfo(reporterTest, "Trying to get selected option for '" + locator.getName()
					+ "' is present on  '" + locator.getPage() + "'");
			WebElement element = getElement(locator);
			Select select = new Select(element);
			WebElement option = select.getFirstSelectedOption();
			String defaultItem = option.getText();
			log.info("'{}' is the value of option of '{}'", defaultItem, locator.getName());
			ExtentReportLogger.addInfo(reporterTest,
					"'" + defaultItem + "' is the value of option of '" + locator.getName() + "'");
			return defaultItem;
		} catch (Exception e) {
			log.info("Unable to get selected value of option '{}' on '{}'", locator.getName(), locator.getPage());
			ExtentReportLogger.addInfo(reporterTest, "Unable to get selected value of option '" + locator.getName()
					+ "' on '" + locator.getPage() + "'");
			throw new FrameworkException("No option selected", e);
		}
	}

	public void selectAnOptionByIndex(WebElementLocator locator, int value) {
		try {
			log.info("Trying to select an option for '{}' on the page'{}'", locator.getName(), locator.getPage());
			ExtentReportLogger.addInfo(reporterTest, "Trying to get select an option for '" + locator.getName()
					+ "' is present on  '" + locator.getPage() + "'");
			WebElement element = getElement(locator);
			Select select = new Select(element);
			select.selectByIndex(value);
			WebElement option = select.getFirstSelectedOption();
			System.out.println(option.getText());
			log.info("Option selected of '{}'", locator.getName());
			ExtentReportLogger.addInfo(reporterTest, "Option selected of '" + locator.getName() + "'");
		} catch (Exception e) {
			log.info("Unable to select option for '{}' on '{}'", locator.getName(), locator.getPage());
			ExtentReportLogger.addInfo(reporterTest,
					"Unable to select option for '" + locator.getName() + "' on '" + locator.getPage() + "'");
			throw new FrameworkException("Unable to select option", e);
		}
	}

	public String getPageTitle() {
		try {
			log.info("Trying to get title of the page'");
			ExtentReportLogger.addInfo(reporterTest, "Trying to get title of the page");
			String title = driver.getTitle();
			log.info("Title of the page is '" + title + "'");
			ExtentReportLogger.addPass(reporterTest, "Title of the page is '" + title + "'");
			return title;
		} catch (Exception e) {
			log.error("Unable to get title of the page", e);
			ExtentReportLogger.addFail(reporterTest, "Unable to get title of the page");
			throw new FrameworkException(e.getMessage(), e);
		}
	}

	public String getPageURL() {
		try {
			log.info("Trying to get URL of the page'");
			ExtentReportLogger.addInfo(reporterTest, "Trying to get URL of the page");
			String url = driver.getCurrentUrl();
			log.info("Title of the page is '" + url + "'");
			ExtentReportLogger.addPass(reporterTest, "URL of the page is '" + url + "'");
			return url;
		} catch (Exception e) {
			log.error("Unable to get URL of the page", e);
			ExtentReportLogger.addFail(reporterTest, "Unable to get URL of the page");
			throw new FrameworkException(e.getMessage(), e);
		}
	}

	public void waitForPageLoad() {
		try {
			log.info("Waiting for page to load'");
			ExtentReportLogger.addInfo(reporterTest, "Waiting for page to load");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

			wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
					.executeScript("return document.readyState").equals("complete"));
			log.info("Page loaded successfully");
			ExtentReportLogger.addPass(reporterTest, "Page loaded successfully");
		} catch (Exception e) {
			log.error("Unable to wait for the page to load", e);
			ExtentReportLogger.addFail(reporterTest, "Unable to wait for the page to load");
			throw new FrameworkException(e.getMessage(), e);
		}
	}
}
