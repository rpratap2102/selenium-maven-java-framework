package com.frameworks.selenium.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.frameworks.selenium.base.TestBase;
import com.frameworks.selenium.data.provider.AmazonTestDataProvider;
import com.frameworks.selenium.pages.CartPage;
import com.frameworks.selenium.pages.HomePage;
import com.frameworks.selenium.pages.ProductPage;
import com.frameworks.selenium.pages.SearchPage;

public class CartPageTests extends TestBase {


	@Test(dataProvider = "AmazonTestData", dataProviderClass = AmazonTestDataProvider.class, groups = {"Smoke"})
	public static void addItemToCart(String product) {
		WebDriver driver = getBrowser();
		HomePage homePage = new HomePage(driver);
		SearchPage searchPage = new SearchPage(driver);
		CartPage cartPage = new CartPage(driver);
		ProductPage productPage = new ProductPage(driver);
		homePage.searchOnThePage(product);
		String title = searchPage.selectFirstOption();
		searchPage.switchToProductPage();
		productPage.additemToCart();
		cartPage.switchToCartPage();
		Assert.assertEquals(cartPage.getProductTitle(), title);
	}

	@Test(dataProvider = "AmazonTestData", dataProviderClass = AmazonTestDataProvider.class)
	public static void removeItemfromCart(String product) {
		WebDriver driver = getBrowser();
		HomePage homePage = new HomePage(driver);
		SearchPage searchPage = new SearchPage(driver);
		CartPage cartPage = new CartPage(driver);
		ProductPage productPage = new ProductPage(driver);
		homePage.searchOnThePage(product);
		String title = searchPage.selectFirstOption();
		searchPage.switchToProductPage();
		productPage.additemToCart();
		cartPage.switchToCartPage();
		cartPage.removeitem(title);
		Assert.assertEquals(cartPage.checkItemisRemoved(title), true);
	}

	@Test(dataProvider = "AmazonTestData", dataProviderClass = AmazonTestDataProvider.class)
	public static void updateItemQuantity(String product) {
		WebDriver driver = getBrowser();
		HomePage homePage = new HomePage(driver);
		SearchPage searchPage = new SearchPage(driver);
		CartPage cartPage = new CartPage(driver);
		ProductPage productPage = new ProductPage(driver);
		homePage.searchOnThePage(product);
		String title = searchPage.selectFirstOption();
		System.out.println(title);
		searchPage.switchToProductPage();
		productPage.additemToCart();
		cartPage.switchToCartPage();
		int updateQuantity = 2;
		cartPage.updateQantity(title, updateQuantity);
		Assert.assertEquals(cartPage.getQantity(title), updateQuantity);
	}

}
