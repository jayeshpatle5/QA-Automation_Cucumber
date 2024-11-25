package com.commonUtils.config;

public class BrowserConfigReader {

    private static String browserType = null;

    public static void setBrowserType(String browser) {

        browserType = browser;

    }

    public static String getBrowserType() {

        try {
            if (browserType != null)

                return browserType;

            else

                throw new RuntimeException("browser not specified in the testng.xml");
        }catch (Exception e){
            e.printStackTrace();
        }

        return browserType;
    }
}
