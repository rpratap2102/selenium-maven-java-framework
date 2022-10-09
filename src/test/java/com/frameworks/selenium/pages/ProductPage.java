package com.frameworks.selenium.pages;

import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;

import com.frameworks.selenium.core.BasePage;
import com.frameworks.selenium.core.locators.WebElementLocator;
import com.frameworks.selenium.core.locators.WebLocatorType;

public class ProductPage extends BasePage {

	private final static String PAGENAME = "ProductPage";

	public ProductPage(WebDriver webDriver) {
		super(webDriver);
	}

	WebElementLocator addItemToCart = new WebElementLocator(WebLocatorType.Xpath, "//input[@value='Add to Cart']",
			"Add to cart button", PAGENAME);

	WebElementLocator addedItemToCartPopup = new WebElementLocator(WebLocatorType.Xpath,
			"//a[@aria-label='Exit this panel and return to the product page. ']", "Added to cart popup close button", PAGENAME);

	WebElementLocator regularPriceCheckBox = new WebElementLocator(WebLocatorType.Xpath,
			"//span[text()=' Regular Price ']//parent::div//parent::div//parent::h5//parent::a//i",
			"Regular Price button", PAGENAME);

	WebElementLocator itemPriceLabel = new WebElementLocator(WebLocatorType.Xpath,
			"//span[@class='a-price aok-align-center reinventPricePriceToPayMargin priceToPay']//span[@class='a-price-whole']",
			"Item Price Label", PAGENAME);

	WebElementLocator dealPriceLabel = new WebElementLocator(WebLocatorType.Xpath,
			"//span[@class='a-price a-text-price a-size-medium apexPriceToPay']//span[@class='a-offscreen']",
			"Deal Price Label", PAGENAME);

	public void additemToCart() {
		startNewNode();
		if (driver.checkIfelementIsPresent(regularPriceCheckBox)) {
			driver.clickOnElement(regularPriceCheckBox);
		}
		driver.clickOnElement(addItemToCart);
		if (driver.checkIfelementIsPresent(addedItemToCartPopup)) {
			driver.clickOnElement(addedItemToCartPopup);
		}

	}

	public Boolean checkProductPriceRange(String title, String min, String max) {
		startNewNode();
		String price = "";
		if (driver.checkIfelementIsPresent(dealPriceLabel)) {
			price = driver.getTextofElement(dealPriceLabel);
		} else {
			price = driver.getTextofElement(itemPriceLabel);
		}
		price = price.replace(",", "").replace("₹", "");
		float actual = Float.parseFloat(price.split(Pattern.quote("."))[0]);
		float minPrice = Float.parseFloat(min);
		float maxPrice = Float.parseFloat(max);
		return (actual >= minPrice && actual <= maxPrice);
	}
}
