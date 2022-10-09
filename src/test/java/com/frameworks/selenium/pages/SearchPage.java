package com.frameworks.selenium.pages;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;

import com.frameworks.selenium.core.BasePage;
import com.frameworks.selenium.core.locators.WebElementLocator;
import com.frameworks.selenium.core.locators.WebLocatorType;

public class SearchPage extends BasePage {

	private final static String PAGENAME = "SearchPage";

	public SearchPage(WebDriver webDriver) {
		super(webDriver);
	}

	WebElementLocator searchPageAddLabel = new WebElementLocator(WebLocatorType.Xpath,
			"//a[contains(@aria-label,'Sponsored ad from')]/parent::div//a[@data-elementid='sbx-headline']//span[text()][1]",
			"Search Page Add Label", PAGENAME);

	WebElementLocator firstSearchResult = new WebElementLocator(WebLocatorType.Xpath,
			"//div[@class='s-main-slot s-result-list s-search-results sg-row']//div[3]//h2//span",
			"Search Page First Result", PAGENAME);

	public String getPageAddTitle() {
		startNewNode();
		return driver.getTextofElement(searchPageAddLabel);
	}

	public String selectFirstOption() {
		startNewNode();
		String title = driver.getTextofElement(firstSearchResult);
		driver.clickOnElement(firstSearchResult);
		return title;
	}

	public String switchToProductPage() {
		startNewNode();
		Set<String> w = webDriver.getWindowHandles();
		Iterator<String> t = w.iterator();
		String current = t.next();
		driver.switchWindow(t.next());
		return current;
	}
		
}
