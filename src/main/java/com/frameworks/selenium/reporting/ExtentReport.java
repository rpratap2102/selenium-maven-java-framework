package com.frameworks.selenium.reporting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.frameworks.selenium.exceptions.FrameworkException;
import com.frameworks.selenium.utils.Constants;
import com.frameworks.selenium.utils.ReportUtil;

public class ExtentReport {

	private static ExtentReports report;
	private static final Logger log = LoggerFactory.getLogger(ExtentReport.class);

	public static void crateInstance() {
		log.info("Creting Extent Report instance");
		String path = ReportUtil.FILEPATH + Constants.REPORT_FILE_NAME;
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName(Constants.REPORT_NAME);
		reporter.config().setDocumentTitle(Constants.REPORT_TITLE);
		report = new ExtentReports();
		report.attachReporter(reporter);
	}

	public static ExtentReports getInstance() {
		if (report == null) {
			log.error("No report instance creted");
			throw new FrameworkException("Create the Instance first using createInstance");
		}
		return report;
	}

	public static void flushReport() {
		try {
			log.info("Trying to create the extent report.");
			report.flush();
			log.info("Extent report cretated at reports");
		} catch (Exception e) {
			log.error("unable to create the report");
			throw new FrameworkException("Unable to create the report", e);
		}

	}
}
