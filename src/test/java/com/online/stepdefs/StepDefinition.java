package com.online.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;


public class StepDefinition {

    private static final Logger logger = LogManager.getLogger(StepDefinition.class);

    @Given("a user is on the Login page")
    public void a_user_is_on_the_Login_page()
    {
        System.out.println("This is login page.");
        logger.info("STEP : a user is on the Activity Feeds widget: PASSED");
    }

    @When("a user pass a username and password")
    public void aUserPassAUsernameAndPassword() {
        Assert.assertTrue(false);
    }

    @And("click on submit button")
    public void clickOnSubmitButton() {
    }

    @Then("a user navigates to the homepage")
    public void aUserNavigatesToTheHomepage() {
    }
}
