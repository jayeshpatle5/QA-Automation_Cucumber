package com.online.driver;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import com.commonUtils.config.ReadPropertyFileUtils;
import com.commonUtils.enums.ConfigProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import io.github.bonigarcia.wdm.WebDriverManager;


public final class DriverFactories {
	private DriverFactories() {}
	public static  WebDriver getDriver(String browser) throws MalformedURLException {
		WebDriver driver=null;
		String runmode= ReadPropertyFileUtils.get(ConfigProperties.RUNMODE);
			if (browser.equalsIgnoreCase("chrome")) {
				if(runmode.equals("remote")) {
					DesiredCapabilities cap=new DesiredCapabilities();
					cap.setBrowserName(Browser.CHROME.browserName());
					driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
				}else {
				WebDriverManager.chromedriver().setup();
				WebDriverManager.chromedriver().clearDriverCache().setup();
				WebDriverManager.chromedriver().clearResolutionCache().setup();
				//System.setProperty("webdriver.chrome.driver", FrameworkConstants.getCHROMEDRIVERPATH());
				// DriverManager.setDriver(new ChromeDriver()); one more way.....
                if(ReadPropertyFileUtils.getValue("headless").equals("yes")) {
					//----------To run headless mode---------
					ChromeOptions chromeoptions = new ChromeOptions();
					chromeoptions.addArguments("--headless=new");
					chromeoptions.addArguments("window-size=1920,1080");
					chromeoptions.addArguments("--enable-clipboard");
					chromeoptions.addArguments("--enable-features=ClipboardAPI");
					chromeoptions.addArguments("--start-maximized");


					chromeoptions.addArguments("--no-sandbox"); // Bypass OS security model
					chromeoptions.addArguments("--disable-dev-shm-usage");
					driver = new ChromeDriver(chromeoptions);
				}else{

					driver=new ChromeDriver();
				}
				// ------------\\\\\\\\\\\TO run in non-headless mode ----------
//					driver=new ChromeDriver();
				}

			} else if (browser.equalsIgnoreCase("firefox")) {
				if(runmode.equals("remote")) {
					DesiredCapabilities cap=new DesiredCapabilities();
					cap.setBrowserName(Browser.FIREFOX.browserName());
					driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
				}else {
				WebDriverManager.firefoxdriver().setup();
					if(ReadPropertyFileUtils.getValue("headless").equals("yes")) {
						FirefoxOptions options = new FirefoxOptions();
						options.addArguments("--headless");  // Enables headless mode
						driver = new FirefoxDriver(options);
					}else {
						driver = new FirefoxDriver();
					}

				}

			} else if (browser.equalsIgnoreCase("edge")) {
				if(runmode.equals("remote")) {
					DesiredCapabilities cap=new DesiredCapabilities();
					cap.setBrowserName(Browser.EDGE.browserName());
					driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
				}
				else
				{
				WebDriverManager.edgedriver().setup();
					if(ReadPropertyFileUtils.getValue("headless").equals("yes")) {
						EdgeOptions options = new EdgeOptions();
						options.addArguments("--headless");
						driver = new EdgeDriver(options);
					}else {

				        driver=new EdgeDriver();

					}
				}


			}else if(browser.equalsIgnoreCase("safari")) {
				
				if(runmode.equals("remote")) {
					DesiredCapabilities cap=new DesiredCapabilities();
					cap.setBrowserName(Browser.SAFARI.browserName());
					driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
				}
				else
				{
				    WebDriverManager.safaridriver().setup();
					if(ReadPropertyFileUtils.getValue("headless").equals("yes")) {
						DesiredCapabilities capabilities = new DesiredCapabilities();
						SafariOptions options = new SafariOptions();
						options.setUseTechnologyPreview(true);
						options.setAutomaticInspection(false);
						options = SafariOptions.fromCapabilities(capabilities);
						driver = new SafariDriver(options);
					}else{
						driver = new SafariDriver();
					}
			   }
			}

            driver.manage().window().maximize();
		    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Long.parseLong(ReadPropertyFileUtils.getValue("pageloadtimeout"))));
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(ReadPropertyFileUtils.getValue("implicitWait"))));

		return driver;

	}
}

