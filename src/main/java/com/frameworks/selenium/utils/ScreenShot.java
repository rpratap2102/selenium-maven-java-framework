package com.frameworks.selenium.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.frameworks.selenium.exceptions.FrameworkException;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ScreenShot {

	public static String captureBase64(File file) {

		try {
			byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
			return new String(encoded, StandardCharsets.US_ASCII);
		} catch (IOException e) {
			throw new FrameworkException("Unable to take screenshot", e);
		}

	}

	public static File captureBase64(WebDriver driver, String name) {
		File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File DestFile = new File(name);
		try {
			FileUtils.copyFile(screen, DestFile);
			return screen;
		} catch (IOException e) {
			throw new FrameworkException("Unable to take screenshot", e);
		}
	}

}
