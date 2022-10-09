package com.frameworks.selenium.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.frameworks.selenium.base.TestBase;
import com.frameworks.selenium.data.provider.AmazonTestDataProvider;
import com.frameworks.selenium.pages.FilterPage;
import com.frameworks.selenium.pages.HomePage;
import com.frameworks.selenium.pages.ProductPage;
import com.frameworks.selenium.pages.SearchPage;

public class FilterPageTests extends TestBase {

	@Test(dataProvider = "AmazonTestData", dataProviderClass = AmazonTestDataProvider.class, groups = {"Smoke"})
	public static void priceRangeFilterTestOnMobile(String minPrice, String maxPrice) {
		WebDriver driver = getBrowser();
		HomePage homePage = new HomePage(driver);
		FilterPage filterPage = new FilterPage(driver);
		SearchPage searchPage = new SearchPage(driver);
		ProductPage productPage = new ProductPage(driver);
		homePage.goToMobilePage();
		filterPage.selectMadeForAmazon();
		filterPage.addPriceRange(minPrice, maxPrice);
		String title = searchPage.selectFirstOption();
		searchPage.switchToProductPage();
		Assert.assertEquals(productPage.checkProductPriceRange(title, minPrice, maxPrice), true);
	}

	
}
