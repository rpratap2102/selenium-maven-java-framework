package com.frameworks.selenium.core;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.frameworks.selenium.reporting.ExtentReportLogger;

public class BasePage {
	protected WebDriver webDriver;
	protected ExtentTest reportTest;
	protected ExtentTest testNode;
	protected SeleniumWrapper driver;

	protected BasePage(WebDriver webDriver) {
		this.webDriver = webDriver;
		StackTraceElement el = Thread.currentThread().getStackTrace()[3];
		String path = el.getClassName() + '.' + el.getMethodName();
		this.reportTest = ExtentReportLogger.getTest(path);
		this.testNode = this.reportTest;
		this.driver = new SeleniumWrapper(webDriver, testNode);

	}

	protected void startNewNode() {
		StackTraceElement el = Thread.currentThread().getStackTrace()[2];
		String name = el.getMethodName();
		this.testNode = ExtentReportLogger.createNode(name, reportTest);
		this.driver = new SeleniumWrapper(webDriver, testNode);
	}
}
