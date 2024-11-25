package com.reportsconfig;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


import com.aventstack.extentreports.MediaEntityBuilder;
import com.commonUtils.config.ReadPropertyFileUtils;
import com.commonUtils.enums.ConfigProperties;
import com.online.driver.DriverManager;


public final class ExtentLogger {
	private ExtentLogger() {
	}

	public static void pass(String message) {
		ExtentManager.getExtentTest().pass(message);
	}

	public static void fail(String message) {
		ExtentManager.getExtentTest().fail(message);
	}

	public static void skip(String message) {
		ExtentManager.getExtentTest().skip(message);
	}

	public static void pass(String message, boolean isScreenShotNeeded)  {
		if (ReadPropertyFileUtils.get(ConfigProperties.PASSEDSTEPSCREENSHOT).equalsIgnoreCase("yes") && isScreenShotNeeded) {
			ExtentManager.getExtentTest().pass(message,MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image()).build());
		}else {
			pass(message);
		}
	}
	public static void fail(String message, boolean isScreenShotNeeded)  {
		if (ReadPropertyFileUtils.get(ConfigProperties.FAILEDSTEPSCREENSHOT).equalsIgnoreCase("yes") && isScreenShotNeeded) {
			ExtentManager.getExtentTest().fail(message,MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image()).build());
		}else {
			fail(message);
		}
	}
	public static void skip(String message, boolean isScreenShotNeeded)  {
		if (ReadPropertyFileUtils.get(ConfigProperties.SKIPEDSTEPSCREENSHOT).equalsIgnoreCase("yes") && isScreenShotNeeded) {
			ExtentManager.getExtentTest().skip(message,MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image()).build());
		}else {
			skip(message);
		}
	}
	
	
	public static String base64Image() {
		
		return ((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
		 
	}
}
