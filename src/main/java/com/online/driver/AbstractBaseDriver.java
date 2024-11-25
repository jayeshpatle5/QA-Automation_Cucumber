package com.online.driver;



import java.time.Duration;
import java.util.Objects;

import com.commonUtils.config.ReadPropertyFileUtils;
import com.commonUtils.enums.ConfigProperties;
import com.exceptions.BrowserInvocationFailedException;


public abstract class AbstractBaseDriver {
    /**
     * @param browser value from  values can be chrome and firefox
     */
    public static void initDriver(String browser)  {
        if (Objects.isNull(DriverManager.getDriver())) {
            try {
                DriverManager.setDriver(DriverFactories.getDriver(browser));

            } catch (Exception e) {
                throw new BrowserInvocationFailedException("please check the capabilities of the browser",e.fillInStackTrace());
            }
        }
        DriverManager.getDriver().get(ReadPropertyFileUtils.getValue("url"));
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    /**
     * quit the browser
     */
    public static void quitDriver() {
        if (Objects.nonNull(DriverManager.getDriver())) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }

}
