package com.frameworks.selenium.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.frameworks.selenium.base.TestBase;
import com.frameworks.selenium.data.provider.AmazonTestDataProvider;
import com.frameworks.selenium.pages.HomePage;

public class HomePageTests extends TestBase {

	@Test( groups = {"Smoke"})
	public static void assertPageTitle() {
		WebDriver driver = getBrowser();
		HomePage homePage = new HomePage(driver);
		homePage.goToMobilePage();
		Assert.assertEquals(homePage.getPageTitle(), "Mobile Phones: Buy New Mobiles Online at Best Prices in India | Buy Cell Phones Online - Amazon.in");
	}

	@Test(dataProvider = "AmazonTestData", dataProviderClass = AmazonTestDataProvider.class)
	private static void switchLanguageTest(String language) {
		WebDriver driver = getBrowser();
		HomePage homePage = new HomePage(driver);
		homePage.chooseLanguage(language);
		Assert.assertEquals(homePage.checkLangIsSelected(language), true);

	}
	
	@Test(dataProvider = "AmazonTestData", dataProviderClass = AmazonTestDataProvider.class)
	private static void contactUsRedirectionTests(String socialMedia) {
		WebDriver driver = getBrowser();
		HomePage homePage = new HomePage(driver);
		homePage.chooseSocialMedia(socialMedia);
		Assert.assertEquals(homePage.checkPageUrl(socialMedia),true);
	}
}
