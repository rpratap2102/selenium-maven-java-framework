package com.frameworks.selenium.core.locators;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import com.frameworks.selenium.exceptions.FrameworkException;
import com.frameworks.selenium.exceptions.UnsportedException;
import com.frameworks.selenium.utils.Constants;

public class WebElementLocator {

	private WebLocatorType type;
	private String name;
	private String locator;
	private String page;

	public WebElementLocator(WebLocatorType type, String locator, String name, String page) {
		this.type = type;
		this.name = name;
		this.locator = locator;
		this.page = page;
	}

	public String getType() {
		return type.toString();
	}

	@Override
	public String toString() {
		return this.name.isEmpty() ? this.type + this.locator : this.name;
	}

	public String getName() {
		return this.name;
	}

	public String getLocator() {
		return this.locator;
	}

	public WebElementLocator updateDynamicLocator(String... values) {

		String locatorFormat = this.locator;
		int dynamicValuesCount = StringUtils.countMatches(locatorFormat, Constants.DYNAMICLOCATORIDENTIFIER);
		if (dynamicValuesCount != values.length) {
			throw new FrameworkException("Locator does not support the given ammount of values");
		}
		for (String value : values) {
			locatorFormat = locatorFormat.replaceFirst(Pattern.quote(Constants.DYNAMICLOCATORIDENTIFIER), value);
		}

		return new WebElementLocator(this.type, locatorFormat, this.name, this.page);
	}

	public By getBy() {
		switch (this.type.toString().toLowerCase()) {
		case "id":
			return new By.ById(this.locator);
		case "name":
			return new By.ByName(this.locator);
		case "classname":
			return new By.ByClassName(this.locator);
		case "tagname":
			return new By.ByTagName(this.locator);
		case "linktext":
			return new By.ByLinkText(this.locator);
		case "partiallinktext":
			return new By.ByPartialLinkText(this.locator);
		case "cssselector":
			return new By.ByCssSelector(this.locator);
		case "xpath":
			return new By.ByXPath(this.locator);
		default:
			throw new UnsportedException("Can't create locators for " + this.locator);
		}
	}

	public String getPage() {
		return page;
	}
}
