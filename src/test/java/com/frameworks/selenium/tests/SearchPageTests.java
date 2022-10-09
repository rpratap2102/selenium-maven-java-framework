package com.frameworks.selenium.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.frameworks.selenium.base.TestBase;
import com.frameworks.selenium.data.provider.AmazonTestDataProvider;
import com.frameworks.selenium.pages.HomePage;
import com.frameworks.selenium.pages.SearchPage;

public class SearchPageTests extends TestBase {

	@Test(dataProvider = "AmazonTestData", dataProviderClass = AmazonTestDataProvider.class, groups = {"Smoke"})
	public static void searchProducts(String searchText, String assertTitle) {
		WebDriver driver = getBrowser();
		HomePage homePage = new HomePage(driver);
		SearchPage searchPage = new SearchPage(driver);
		homePage.searchOnThePage(searchText);
		Assert.assertEquals(searchPage.getPageAddTitle(), assertTitle);
	}

}
