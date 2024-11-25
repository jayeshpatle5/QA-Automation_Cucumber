package com.reportsconfig;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.commonUtils.FrameworkConstants;
import com.commonUtils.enums.AuthorNames;
import com.commonUtils.enums.CategoryType;

public final class ExtentReporter {
	private ExtentReporter() {

	}

	private static ExtentReports report;
	public static ExtentTest test;

	public static ExtentReports getExtentReports(String browser) throws Exception {
		if (Objects.isNull(report)) {
			report = new ExtentReports();
			 if (ExtentManager.extentReports.get() == null) {
		        	String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
		    
		        	ExtentSparkReporter sparkReporter = new ExtentSparkReporter("Allreports/reports/report_"+browser+ timeStamp + ".html");
		        	
		        	 try {
		        		 sparkReporter.loadXMLConfig("src/main/resources/extent-config.xml");
		 			} catch (IOException e) {
		 				// TODO Auto-generated catch block
		 				e.printStackTrace();
		 			}
		        	 sparkReporter.config().setTheme(Theme.DARK);
		             sparkReporter.config().setDocumentTitle("One Body Execution");
		             sparkReporter.config().setReportName("Extent Report - " + browser);
		             sparkReporter.config().setTimelineEnabled(true);
		             sparkReporter.config().enableOfflineMode(true); 
			
			report.attachReporter(sparkReporter);
			sparkReporter.config().setTheme(Theme.DARK);
			sparkReporter.config().setReportName("Peter R");
			sparkReporter.config().setDocumentTitle("Automation Test");
		}
	}
		 return ExtentManager.extentReports.get();
	}

	public static void tearDownReports() throws Exception {
		if (Objects.nonNull(report)) {
			report.flush();
			ExtentManager.unload();
			
		}
		Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentReportFilePath()).toURI());// Open the file on the desktop default browser

	}

	 
	public static void addAuthors(AuthorNames[] authors) {
		for(AuthorNames temp:authors) {
			ExtentManager.getExtentTest().assignAuthor(temp.toString());
		}
	}
	public static void addCatogeries(CategoryType[] catogeries) {
		for(CategoryType temp:catogeries) {
			ExtentManager.getExtentTest().assignCategory(temp.toString());
		}
	}
}
