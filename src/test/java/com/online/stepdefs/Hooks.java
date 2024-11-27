package com.online.stepdefs;

import com.commonUtils.config.BrowserConfigReader;
import com.commonUtils.config.ReadPropertyFileUtils;
import com.online.driver.AbstractBaseDriver;
import com.online.driver.DriverManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import io.cucumber.java.*;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;

public class Hooks extends AbstractBaseDriver{
    private static final Logger logger = LogManager.getLogger(Hooks.class);
    Object[] data;
    String browser;
    public static String screenshot;
   
    
    @Before
    public void testStart(Scenario scenario) {

        logger.info("*****************************************************************************************");
        logger.info(" Scenario: " + scenario.getName());
        logger.info("*****************************************************************************************");


        initDriver(BrowserConfigReader.getBrowserType());
//        initDriver("chrome");
        
//       logger.info(ReadPropertyFileUtils.getValue("browser")+" Driver invoked successfully");
         logger.info(BrowserConfigReader.getBrowserType()+" Driver invoked successfully");

    }
    
//    @BeforeStep
//    public void beforeTest(Scenario scenario) {
//    	
//    	ExtentManager.createTest(scenario.toString());
//    }
//    
//    @AfterStep
//    public void afterTest(Scenario scenario) {
//    	ExtentManager.createTest(scenario.toString());
//    }
    

    @After
    public void endTest(Scenario scenario) {
        if (scenario.isFailed()) {
            try {

                logger.info(scenario.getName() + " ***** is Failed");

                 screenshot= base64Image();

                logger.info("*** Screenshot attached ***");

            } catch (WebDriverException e) {
                e.printStackTrace();
            }
        }else {
//        	ExtentManager.getTest().log(Status.PASS, "Scenario passed");
//        	 ExtentManager.getExtentReports(BrowserConfigReader.getBrowserType()).createTest(scenario.getName())
//             .log(Status.PASS, "Scenario passed: " + scenario.getName());
        }
        AbstractBaseDriver.quitDriver();
    }

    public static String base64Image() {

        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);

    }

}//class