package com.online.pages;


import com.commonUtils.enums.WaitStrategy;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;

public class LoginPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(LoginPage.class);



    By usernameby = By.id("txtUsername");
    By passwordby = By.id("txtPassword");
    By submitby = By.id("btnLogin");
    By forgotpasswordby = By.id("forgotPasswordLink");


    public void isPageDisplayed() {
        String title = getPageTitle();
        Assertions.assertThat(title)
                .isNotNull()
                .as("String actually empty").isNotEmpty()
                .as("String actually Not Blank").isNotBlank()
                .isEqualTo("OrangeHRM");
        logger.info(title+" title is verified successfully");
    }

    public void enterUserDetails(String username, String password){
        enterText(usernameby, WaitStrategy.VISIBLE, username, "username");
        enterText(passwordby, WaitStrategy.VISIBLE, password, "password");
    }

    public void clickSubmitBtn(){
        click(submitby, WaitStrategy.CLICKBLE, "SubmitButton");
    }

    public void isDashboardDisplayed() {
        String currurl = getCurrentpageUrl();
        Assertions.assertThat(currurl)
                .isNotNull()
                .as("String actually empty").isNotEmpty()
                .as("String actually Not Blank").isNotBlank()
                .contains("dashboard");
        logger.info(currurl+" Current url is verified successfully");
    }
}




