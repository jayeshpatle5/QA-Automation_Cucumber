package com.online;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

//@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty:target/cucumber/cucumber.txt",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"html:target/cucumber/report",
				"json:target/cucumber/cucumber.json"
//				"com.online.listeners.CustomCucumberEventListener"
				//"com.api.utils.MyTestListener"
		}
		,features= {"src/test/resources/features/Sample.feature"}
		,glue = "com.online.stepdefs"
		//,dryRun = true
		,monochrome = true
		,snippets = SnippetType.CAMELCASE
		//,tags = "@bookerAPI"
//		,tags = "@chrome or @firefox or @edge or @Smoke"
		//,publish = true

)

public class TestRunnerOnline extends AbstractTestNGCucumberTests {

	private static final Logger logger = LogManager.getLogger(TestRunnerOnline.class);


//	@DataProvider(parallel = false)
//	public Object[][] scenarios() {
//
//		return super.scenarios();
//
//	}

	
	
//	@BeforeTest
//	@Parameters("browser")
//	public void defineBrowser(@Optional("chrome") String browser) throws Throwable {
//
//
//		BrowserConfigReader.setBrowserType(browser);
//
//		ExtentManager.getExtentReports(browser);
//
//
//
//	}
//
//	@AfterTest
//    public void tearDown() {
//
//        ExtentManager.flushReports();
//
//
//    }




}