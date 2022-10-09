package com.frameworks.selenium.reporting;

import java.io.File;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IClassListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.frameworks.selenium.core.WebDriverHandler;
import com.frameworks.selenium.exceptions.FrameworkException;
import com.frameworks.selenium.utils.ReportUtil;
import com.frameworks.selenium.utils.ScreenShot;

public class ReportListner implements ISuiteListener, ITestListener, IClassListener {

	private static final Logger log = LoggerFactory.getLogger(ReportListner.class);

	@Override
	public void onFinish(ISuite suite) {
		log.info("Creating the extent report ....");
		ExtentReport.flushReport();
	}

	@Override
	public void onStart(ISuite suite) {
		ReportUtil.cleanOldReport();
		ExtentReport.crateInstance();
	}

	@Override
	public void onStart(ITestContext arg0) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		log.info("Closing the test in extent report");
		String path = result.getMethod().getRealClass().getName() + '.' + result.getMethod().getMethodName();
		ExtentReportLogger.addTestStatus(path, result.getStatus());
		ExtentReportLogger.removeTest(path);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		log.info("Closing the test in extent report");
		String path = result.getMethod().getRealClass().getName() + '.' + result.getMethod().getMethodName();
		try {
			if (WebDriverHandler.getBrowser() != null) {

				String name = ReportUtil.FILEPATH.toString() + "/screenshot/"
						+ result.getMethod().getRealClass().getSimpleName() + '_' + result.getMethod().getMethodName()
						+ ".png";
				File file = ScreenShot.captureBase64(WebDriverHandler.getBrowser(), name);
				String screenShot = ScreenShot.captureBase64(file);
				ExtentReportLogger.addTestStatus(path, result.getStatus(), screenShot, result.getThrowable());
			} else {
				ExtentReportLogger.addTestStatus(path, result.getStatus(), result.getThrowable());
			}
		} catch (Exception e) {
			ExtentReportLogger.addTestStatus(path, result.getStatus(),
					new FrameworkException(e.getMessage(), result.getThrowable()));
		}

		ExtentReportLogger.removeTest(path);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		log.info("Closing the test in extent report");
		String path = result.getMethod().getRealClass().getName() + '.' + result.getMethod().getMethodName();
		ExtentReportLogger.addTestStatus(path, result.getStatus());
		ExtentReportLogger.removeTest(path);
	}

	@Override
	public void onTestStart(ITestResult result) {
		log.info("Creating test in the extent report");
		ITestNGMethod method = result.getMethod();
		String testPath = method.getRealClass().getName() + '.' + method.getMethodName();
		String name = method.getMethodName();
		Object[] params = result.getParameters();
		if (params.length > 0) {
			name += " - Params :";
			name += Arrays.toString(params);
		}
		ExtentReportLogger.createTest(method.getRealClass().getName(), testPath, name);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		log.info("Closing the test in extent report");
		String path = result.getMethod().getRealClass().getName() + '.' + result.getMethod().getMethodName();
		ExtentReportLogger.addTestStatus(path, result.getStatus());
		ExtentReportLogger.removeTest(path);
	}

	@Override
	public void onBeforeClass(ITestClass testClass) {
		ExtentReportLogger.createTestClass(testClass.getName(), testClass.getRealClass().getSimpleName());
	}

	@Override
	public void onAfterClass(ITestClass testClass) {
	}
}
