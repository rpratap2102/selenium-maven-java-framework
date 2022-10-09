package com.frameworks.selenium.pages;

import org.openqa.selenium.WebDriver;

import com.frameworks.selenium.core.BasePage;
import com.frameworks.selenium.core.locators.WebElementLocator;
import com.frameworks.selenium.core.locators.WebLocatorType;
import com.frameworks.selenium.utils.Constants;
import com.frameworks.selenium.utils.Sleeper;

public class HomePage extends BasePage {

	private final static String PAGENAME = "HomePage";

	public HomePage(WebDriver webDriver) {
		super(webDriver);
	}

	// page factory
	WebElementLocator navBarMobileButton = new WebElementLocator(WebLocatorType.Xpath,
			"//div[@id='nav-xshop']/a[@data-csa-c-content-id='nav_cs_mobiles']", "Nav Bar Mobile Button", PAGENAME);

	WebElementLocator searchInputBox = new WebElementLocator(WebLocatorType.ID, "twotabsearchtextbox", "Search bar",
			PAGENAME);

	WebElementLocator searchButton = new WebElementLocator(WebLocatorType.ID, "nav-search-submit-button",
			"Search Button", PAGENAME);

	WebElementLocator langButton = new WebElementLocator(WebLocatorType.ID, "icp-nav-flyout", "Change Language Button",
			PAGENAME);

	WebElementLocator langOptionButton = new WebElementLocator(WebLocatorType.Xpath,
			"//input[@value='" + Constants.DYNAMICLOCATORIDENTIFIER + "']/following-sibling::i",
			"Language option Button", PAGENAME);

	WebElementLocator saveLanguageChange = new WebElementLocator(WebLocatorType.Xpath,
			"//input[@aria-labelledby='icp-save-button-announce']", "Save Language option Button", PAGENAME);

	WebElementLocator langOptionInputButton = new WebElementLocator(WebLocatorType.Xpath,
			"//input[@value='" + Constants.DYNAMICLOCATORIDENTIFIER + "']", "Language option Button", PAGENAME);

	WebElementLocator socialMediaButton = new WebElementLocator(WebLocatorType.Xpath,
			"//a[text()='" + Constants.DYNAMICLOCATORIDENTIFIER + "']", "Social Media Button", PAGENAME);

	public void goToMobilePage() {
		startNewNode();
		driver.clickOnElement(navBarMobileButton);
		webDriver.getTitle();
	}

	public void searchOnThePage(String text) {
		startNewNode();
		driver.typeOnElement(searchInputBox, text);
		driver.clickOnElement(searchButton);
	}

	public void chooseLanguage(String language) {
		startNewNode();
		driver.clickOnElement(langButton);
		driver.waitForPageLoad();
		driver.checkIfelementIsPresent(langOptionButton.updateDynamicLocator(language));
		driver.clickOnElement(langOptionButton.updateDynamicLocator(language));
		driver.clickOnElement(saveLanguageChange);
		driver.waitForPageLoad();
	}

	public String getPageTitle() {
		startNewNode();
		return driver.getPageTitle();
	}

	public Boolean checkLangIsSelected(String language) {
		startNewNode();
		Sleeper.sleep(5, "Slow down the execution");
		driver.clickOnElement(langButton);
		driver.waitForPageLoad();
		return Boolean.parseBoolean(
				driver.getattributeValue(langOptionInputButton.updateDynamicLocator(language), "selected"));
	}

	public void chooseSocialMedia(String socialMedia) {
		startNewNode();
		driver.waitForPageLoad();
		driver.checkIfelementIsPresent(socialMediaButton.updateDynamicLocator(socialMedia));
		driver.clickOnElement(socialMediaButton.updateDynamicLocator(socialMedia));
		driver.waitForPageLoad();
	}

	public Boolean checkPageUrl(String socialMedia) {
		startNewNode();
		Sleeper.sleep(5, "Slow down the execution");
		String pageURL = driver.getPageURL();
		return pageURL.contains(socialMedia.toLowerCase());
	}
}
