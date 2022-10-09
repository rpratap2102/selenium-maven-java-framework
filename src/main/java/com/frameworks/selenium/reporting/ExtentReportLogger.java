package com.frameworks.selenium.reporting;

import java.util.concurrent.ConcurrentHashMap;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.frameworks.selenium.exceptions.UnsportedException;
import com.frameworks.selenium.utils.StringUtils;

public class ExtentReportLogger {

	protected static ConcurrentHashMap<String, ExtentTest> tests = new ConcurrentHashMap<String, ExtentTest>();
	protected static ConcurrentHashMap<String, ExtentTest> testClasses = new ConcurrentHashMap<String, ExtentTest>();

	public static ExtentTest createTest(String classPath, String testPath, String name) {
		ExtentTest test = testClasses.get(classPath).createNode(StringUtils.camelToWords(name));
		tests.putIfAbsent(testPath, test);
		return test;
	}

	public static ExtentTest createTestClass(String classPath, String name) {
		ExtentTest test = ExtentReport.getInstance().createTest(StringUtils.camelToWords(name));
		testClasses.putIfAbsent(classPath, test);
		return test;
	}

	public static void removeTest(String testPath) {
		tests.remove(testPath);
	}
	public static ExtentTest createNode(String name, ExtentTest test) {
		return test.createNode(StringUtils.camelToWords(name));
	}

	public static ExtentTest getTest(String testPath) {
		return tests.get(testPath);
	}

	public static void addInfo(ExtentTest test, String message) {
		test.log(Status.INFO, message);
	}

	public static void addSkip(ExtentTest test, String message) {
		test.log(Status.SKIP, message);
	}

	public static void addFail(ExtentTest test, String message) {
		test.log(Status.FAIL, message);
	}

	public static void addPass(ExtentTest test, String message) {
		test.log(Status.PASS, message);
	}

	public static void addScreenShot(ExtentTest test, String base64Image, String title) {
		test.addScreenCaptureFromBase64String(base64Image, title);
	}

	public static void addTestStatus(String testPath, int result, String screenShot, Throwable throwable) {
		ExtentTest test = createNode("testResult", tests.get(testPath));
		if (screenShot != null) {
			addScreenShot(test, screenShot, "Screen Capture");
		}
		switch (result) {
		case ITestResult.SUCCESS:
			test.pass("Test Passed ");
			break;
		case ITestResult.FAILURE:
			if (throwable == null) {
				test.fail("Test Failed");
			} else {
				test.fail(throwable);
			}
			break;
		case ITestResult.SKIP:
			if (throwable == null) {
				test.skip("Test Skipped");
			} else {
				test.skip(throwable);
			}
			break;
		case ITestResult.CREATED:
			addInfo(test, "Test Created");
			break;
		case ITestResult.STARTED:
			addInfo(test, "Test Started");
			break;
		case ITestResult.SUCCESS_PERCENTAGE_FAILURE:
			if (throwable == null) {
				test.fail("Test Failed within success rate");
			} else {
				test.fail(throwable);
			}
			break;
		default:
			throw new UnsportedException("Unsoported Status");
		}
	}

	public static void addTestStatus(String testPath, int result, Throwable throwable) {
		addTestStatus(testPath, result, null, throwable);
	}

	public static void addTestStatus(String testPath, int result) {
		addTestStatus(testPath, result, null, null);
	}

	public static void addTestStatus(String testPath, int result, String screenShot) {
		addTestStatus(testPath, result, screenShot, null);
	}

}
