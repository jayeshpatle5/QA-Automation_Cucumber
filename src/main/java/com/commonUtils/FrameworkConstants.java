package com.commonUtils;


import com.commonUtils.config.ReadPropertyFileUtils;
import com.commonUtils.enums.ConfigProperties;
import java.io.File;

public final class FrameworkConstants {

	private FrameworkConstants() {
	}
	private static final String RESOURCEPATH = System.getProperty("user.dir") + "\\src\\test\\resources";
	private static final String CHROMEDRIVERPATH = RESOURCEPATH + "\\drivers\\chromedriver.exe";
	private static final String FIREFOXDRIVERPATH = RESOURCEPATH + "\\drivers\\geckodriver.exe";
	private static final String EDGEDRIVERPATH = RESOURCEPATH + "\\drivers\\msedgedriver.exe";
	private static final String CHROMEDRIVERSOURCEPATH = RESOURCEPATH + "\\drivers\\chrome-win64\\chrome.exe";
	private static final String CONFIGFILEPATH = RESOURCEPATH + File.separator + "config.property";
	private static final Integer EXPLICITWAIT = 10;
	private static final String EXTENTREPORTFOLDERPATH = System.getProperty("user.dir") + "/extent-test-output/";
	private static String extentReportFilePath = "";
	private static final String TESTEXCELFILEPATH = RESOURCEPATH+"\\ExcelFiles\\TestData.xlsx";
	private static final String RUNNERSHEET="runManager";
	private static final String ITERATIONDATASHEET="Data";
	public static int getExplicitwait() {
		return EXPLICITWAIT;
	}
	public static String getTestexcelfilepath() {
		return TESTEXCELFILEPATH;
	}
	public static String getCHROMEDRIVERPATH() {
		return CHROMEDRIVERPATH;
	}
	public static String getCONFIGFILEPATH() {
		return CONFIGFILEPATH;
	}
	public static String getExtentReportFilePath()  {
		if (extentReportFilePath.isEmpty()) {
		extentReportFilePath=creatReporterPath();
		}
		return extentReportFilePath;
	}

	public static String creatReporterPath()  {
		if(ReadPropertyFileUtils.get(ConfigProperties.OVERRIDEREPORTS).equalsIgnoreCase("no")){
		return	EXTENTREPORTFOLDERPATH+System.currentTimeMillis()+"index.html";
		}
		else {
		return	EXTENTREPORTFOLDERPATH+"/index.html";
		}
	}

	public static String getRunnersheet() {
		return RUNNERSHEET;
	}

	public static String getIterationdatasheet() {
		return ITERATIONDATASHEET;
	}

	public static String getChromedriversourcepath() {
		return CHROMEDRIVERSOURCEPATH;
	}

	public static String getFirefoxdriverpath() {
		return FIREFOXDRIVERPATH;
	}

	public static String getEdgedriverpath() {
		return EDGEDRIVERPATH;
	}}
