package com.online.pages;

import com.commonUtils.enums.WaitStrategy;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;

public class UserDashboardPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(UserDashboardPage.class);

    By welcomebtnby = By.id("welcome");
    By logoutby = By.linkText("Logout");
    By myinfoby =By.id("menu_pim_viewMyDetails");

    public void clickonWelcomeicon(){
        click(welcomebtnby, WaitStrategy.VISIBLE, "welcome button");
    }

    public void clickOnlogoutlink(){
        click(logoutby, WaitStrategy.VISIBLE, "logout link");
    }

    public void isLoginpageDisplayed(){

        String title = getPageTitle();
        Assertions.assertThat(title)
                .isNotNull()
                .as("String actually empty").isNotEmpty()
                .as("String actually Not Blank").isNotBlank()
                .isEqualTo("OrangeHRM");
        logger.info(title+" title is verified successfully");
    }

    public void clickOnMyinfoTab(){
        click(myinfoby, WaitStrategy.VISIBLE, "myinfo Tab");
    }
}
