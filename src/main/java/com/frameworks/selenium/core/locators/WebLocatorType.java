package com.frameworks.selenium.core.locators;

public enum WebLocatorType {
	ID, Name, ClassName, TagName, LinkTest, PartialLinkText, CssSelector, Xpath;

	@Override
	public String toString() {
		return this.name();
	}
}
