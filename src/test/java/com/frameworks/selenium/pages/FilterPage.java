package com.frameworks.selenium.pages;

import org.openqa.selenium.WebDriver;

import com.frameworks.selenium.core.BasePage;
import com.frameworks.selenium.core.locators.WebElementLocator;
import com.frameworks.selenium.core.locators.WebLocatorType;

public class FilterPage extends BasePage {

	private final static String PAGENAME = "FilterPage";

	public FilterPage(WebDriver webDriver) {
		super(webDriver);
	}

	// page factory
	WebElementLocator minRangeInputBox = new WebElementLocator(WebLocatorType.Name, "low-price", "Min Range Input Box",
			PAGENAME);
	WebElementLocator maxRangeInputBox = new WebElementLocator(WebLocatorType.Name, "high-price", "Min Range Input Box",
			PAGENAME);
	WebElementLocator priceRangeSubmitButton = new WebElementLocator(WebLocatorType.Xpath,
			"//input[@name='high-price']//parent::form//input[@type='submit']", "Price range submit button", PAGENAME);
	WebElementLocator madeForAmazonButton = new WebElementLocator(WebLocatorType.Xpath,
			"//span[text()='Made for Amazon']//parent::a//i", "Made for amazon button", PAGENAME);

	public void addPriceRange(String minPrice, String maxPrice) {
		startNewNode();
		driver.typeOnElement(maxRangeInputBox, maxPrice);
		driver.typeOnElement(minRangeInputBox, minPrice);
		driver.clickOnElement(priceRangeSubmitButton);
	}

	public void selectMadeForAmazon() {
		startNewNode();
		driver.clickOnElement(madeForAmazonButton);
	}

}
