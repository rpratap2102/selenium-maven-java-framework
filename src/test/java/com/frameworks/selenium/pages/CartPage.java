package com.frameworks.selenium.pages;

import org.openqa.selenium.WebDriver;

import com.frameworks.selenium.core.BasePage;
import com.frameworks.selenium.core.locators.WebElementLocator;
import com.frameworks.selenium.core.locators.WebLocatorType;
import com.frameworks.selenium.utils.Constants;
import com.frameworks.selenium.utils.Sleeper;

public class CartPage extends BasePage {

	private final static String PAGENAME = "CartPage";

	public CartPage(WebDriver webDriver) {
		super(webDriver);
	}

	// page factory
	WebElementLocator cartPageButton = new WebElementLocator(WebLocatorType.ID, "nav-cart", "Cart Page Button",
			PAGENAME);

	WebElementLocator addedProductTitle = new WebElementLocator(WebLocatorType.Xpath,
			"//div[@id='sc-active-cart']//li[1]//span[@class='a-truncate-full a-offscreen']", "Product title",
			PAGENAME);
	WebElementLocator removeProductButton = new WebElementLocator(WebLocatorType.Xpath,
			"//input[@aria-label='Delete " + Constants.DYNAMICLOCATORIDENTIFIER + "' and @data-action='delete']",
			"Remove product button", PAGENAME);
	WebElementLocator productTitle = new WebElementLocator(WebLocatorType.Xpath,
			"//span[text()='" + Constants.DYNAMICLOCATORIDENTIFIER + " and @class='a-truncate-full a-offscreen']",
			"Product title", PAGENAME);
	WebElementLocator productQtyInput = new WebElementLocator(WebLocatorType.Xpath,
			"//input[@aria-label='Delete " + Constants.DYNAMICLOCATORIDENTIFIER
					+ "']//parent::span//parent::span//parent::div//select",
			"Product quantity select Button", PAGENAME);

	WebElementLocator productQtyDropdownValue = new WebElementLocator(WebLocatorType.Xpath,
			"//span[//li[@aria-labelledby='quantity_" + Constants.DYNAMICLOCATORIDENTIFIER + "']",
			"Product Quantity value", PAGENAME);

	public void switchToCartPage() {
		startNewNode();
		Sleeper.sleep(5, "Wait for transision of page to happen");
		driver.clickOnElement(cartPageButton);
	}

	public String getProductTitle() {
		return driver.getTextofElement(addedProductTitle);
	}

	public void removeitem(String title) {
		driver.clickOnElement(removeProductButton.updateDynamicLocator(title));
	}

	public Boolean checkItemisRemoved(String title) {
		return !driver.checkIfelementIsPresent(productTitle.updateDynamicLocator(title));
	}

	public int updateQantity(String title, int value) {
		int initialQuantity = Integer
				.parseInt(driver.getSelectedOption(productQtyInput.updateDynamicLocator(title), "value"));
		driver.selectAnOptionByIndex(productQtyInput.updateDynamicLocator(title), value);
		return initialQuantity;
	}

	public int getQantity(String title) {
		return Integer.parseInt(driver.getSelectedOption(productQtyInput.updateDynamicLocator(title), "value"));
	}
}
