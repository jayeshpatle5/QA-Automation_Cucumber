package com.reportsconfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {

	private ExtentManager() {
		
	}
	public static ThreadLocal<ExtentReports> extentReports = new ThreadLocal<>();
	public static ThreadLocal<ExtentTest> test=new ThreadLocal<ExtentTest>();
	public  static ExtentTest getExtentTest() {
		return test.get();
	}
	 static void setExtentTest(ExtentTest extentTest) {
		test.set(extentTest);
	}
	 static void unload() {
		test.remove();
		test.set(null);
		
	}
	 public static ExtentTest createTest(String testName) {
        ExtentTest extent = null;
		try {
			extent = ExtentReporter.getExtentReports("").createTest(testName);
			 test.set(extent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Use empty string or other identifier if needed
       
        return extent;
    }

	 public static void logStep(String stepDescription) {
	        if (getTest() != null) {
	            getTest().info(stepDescription);
	        }
	    }

	    public static ExtentTest getTest() {
	        return test.get();
	    }

}
