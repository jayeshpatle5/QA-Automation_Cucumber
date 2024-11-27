package com.online.pages;

import com.commonUtils.enums.WaitStrategy;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;

import java.util.List;

public class MyInfoPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(MyInfoPage.class);

    By contactdetailslinkby =By.linkText("Contact Details");
    By Addressstreet1by = By.id("contact_street1");
    By Addressstreet2by = By.id("contact_street2");
    By cityby = By.id("contact_city");
    By stateby = By.id("contact_province");
    By postalcodeby = By.id("contact_emp_zipcode");
    By countryby = By.id("contact_country");
    By hometelephoneby = By.id("contact_emp_hm_telephone");
    By mobileby = By.id("contact_emp_mobile");
    By worktelephoneby = By.id("contact_emp_work_telephone");
    By workemailby = By.id("contact_emp_work_email");
    By otheremailby = By.id("contact_emp_oth_email");
    By editbuttonby = By.xpath("//input[@value='Edit']");
    By savebuttonby = By.xpath("//input[@value='Save']");
    By successfullysavedby =By.xpath("//div[@class='message success fadable']");

    public void isMyInfopageDisplayed(){
        String currurl = getCurrentpageUrl();
        Assertions.assertThat(currurl)
                .isNotNull()
                .as("String actually empty").isNotEmpty()
                .as("String actually Not Blank").isNotBlank()
                .contains("viewMyDetails");
        logger.info(currurl+" for MyInfo page is verified successfully");
    }

    public void clickOnContactDetailsLink(){
        click(contactdetailslinkby, WaitStrategy.VISIBLE, "Contact Details link");
    }

    public void isContactDetailsPageDisplayed(){
        String currurl = getCurrentpageUrl();
        Assertions.assertThat(currurl)
                .isNotNull()
                .as("String actually empty").isNotEmpty()
                .as("String actually Not Blank").isNotBlank()
                .contains("contactDetails");
        logger.info(currurl+" for Contact Details page is verified successfully");
    }
    public void clickOnEditBtn(){
        click(editbuttonby, WaitStrategy.CLICKBLE, "EditButton");
    }
    public void enterContactdetails(List<String> list){

        enterText(Addressstreet1by, WaitStrategy.VISIBLE, list.get(0), "Address Street 1");
        enterText(Addressstreet2by, WaitStrategy.VISIBLE, list.get(1), "Address Street 2");
        enterText(cityby, WaitStrategy.VISIBLE, list.get(2), "city");
        enterText(stateby, WaitStrategy.VISIBLE, list.get(3), "state");
        enterText(postalcodeby, WaitStrategy.VISIBLE, list.get(4), "Postal code");
//        enterText(countryby, WaitStrategy.VISIBLE, list.get(5), "Country");
        selectValuefromdropdownByVisibleText(countryby, WaitStrategy.VISIBLE, list.get(5), "Country");
        enterText(hometelephoneby, WaitStrategy.VISIBLE, list.get(6), "HomeTelephone");
        enterText(mobileby, WaitStrategy.VISIBLE, list.get(7), "Mobile");
        enterText(worktelephoneby, WaitStrategy.VISIBLE, list.get(8), "Work Telephone");
        enterText(workemailby, WaitStrategy.VISIBLE, list.get(9), "Work email");
        enterText(otheremailby, WaitStrategy.VISIBLE, list.get(10), "Other Email");

    }

    public void clickOnSaveBtn(){
        click(savebuttonby, WaitStrategy.CLICKBLE, "Save Button");
    }

    public void isContactSuccessfullySaved(String str){

        String message = getTextofElement(successfullysavedby, WaitStrategy.VISIBLE, "Successfully Saved message");
       System.out.println(message);
        Assertions.assertThat(message)
                .isNotNull()
                .as("String actually empty").isNotEmpty()
                .as("String actually Not Blank").isNotBlank()
                .isEqualTo(str);
        logger.info(message+" message is verified successfully");
    }
}
