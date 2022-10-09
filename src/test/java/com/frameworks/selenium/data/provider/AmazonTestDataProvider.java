package com.frameworks.selenium.data.provider;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.frameworks.selenium.utils.ExcelReader;

public class AmazonTestDataProvider {

	@DataProvider(name = "AmazonTestData")
	public static Object[][] credentials(Method method) {
		String testDataFileName = "amazonTestData";
		String className = method.getDeclaringClass().getSimpleName();
		String methodName = method.getName();
		return ExcelReader.readExcelSheet(testDataFileName, className, methodName);
	}

}
