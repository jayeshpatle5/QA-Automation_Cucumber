package com.online.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.online.stepdefs.Hooks;


public class ExtentManager {
    private static ThreadLocal<ExtentReports> extentReports = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static ExtentReports getExtentReports(String browser) {
        if (extentReports.get() == null) {
        	String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
    
        	ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/Allreports/reports/report_"+browser+ timeStamp + ".html");
        	
//        	 try {
// 				sparkReporter.loadXMLConfig("src/main/resources/extent-config.xml");
// 			} catch (IOException e) {
// 				// TODO Auto-generated catch block
// 				e.printStackTrace();
// 			}
//        	 sparkReporter.config().

        	 sparkReporter.config().setTheme(Theme.DARK);
             sparkReporter.config().setDocumentTitle("One Body Execution");
             sparkReporter.config().setReportName("Extent Report - " + browser);
             sparkReporter.config().setTimelineEnabled(true);
             sparkReporter.config().enableOfflineMode(true); 
//             sparkReporter.config().
            
             ExtentReports extent = new ExtentReports();
             
             extent.attachReporter(sparkReporter);
            
             extent.setSystemInfo("Browser", browser);
             extent.setSystemInfo("Environment", "QA");
             extent.setSystemInfo("Tester", "Peter R");
             extent.setSystemInfo("OS", System.getProperty("os.name"));
             
             
//             ExtentPDFCucumberReporter pdfReporter = new ExtentPDFCucumberReporter("target/reports/" + reportName + ".pdf");
//             pdfReporter.loadXMLConfig("src/main/resources/extent-config.xml");
             
//             ExtentPDFCucumberReporter pdfReporter = new ExtentPDFCucumberReporter("target/Allreports/reports/report_"+browser+ timeStamp + ".pdf", timeStamp);
              
//             pdfReporter.config().setTheme(Theme.STANDARD);
//             pdfReporter.config().setDocumentTitle("Automation Test Report");
//             pdfReporter.config().setReportName("Extent Report - " + reportName);

       
//             extent.attachReporter(sparkReporter, pdfReporter);
//             extentReports.set(extent);
             extent.attachReporter(sparkReporter);
             extentReports.set(extent);
//            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/ExtentReport_" + browser + ".html");
//            ExtentReports extent = new ExtentReports();
//            extent.attachReporter(sparkReporter);
//            extentReports.set(extent);
        }
        return extentReports.get();
    }
    
    public static ExtentTest createTest(String testName) {
        ExtentTest test = getExtentReports("").createTest(testName); // Use empty string or other identifier if needed
        extentTest.set(test);
        return test;
    }
    
    public static void logStep(String stepDescription) {
        if (getTest() != null) {
            getTest().info(stepDescription);
        }
    }
    public static void logStepfail(String stepDescription) {
        if (getTest() != null) {
//            getTest().fail(stepDescription );
            getTest().fail(stepDescription, MediaEntityBuilder.createScreenCaptureFromBase64String(Hooks.screenshot).build());
//       getTest().fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image()).build());
        }
    }
    
    public static void logStepPass(String stepDescription) {
        if (getTest() != null) {
            getTest().pass(stepDescription);
        }
    }
    public static void logStepSkip(String stepDescription) {
        if (getTest() != null) {
            getTest().skip(stepDescription);
        }
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }


//    public static ExtentTest getExtentTest() {
//        return extentTest.get();
//    }

//    public static void setExtentTest(ExtentTest test) {
//        extentTest.set(test);
//    }

    public static void flushReports() {
        if (extentReports.get() != null) {
            extentReports.get().flush();
            extentReports.set(null); 
           
        }
    }
    
   
}
