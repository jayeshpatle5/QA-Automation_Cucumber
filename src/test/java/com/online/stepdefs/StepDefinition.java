package com.online.stepdefs;

import com.online.pages.LoginPage;
import com.online.pages.UserDashboardPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;



public class StepDefinition {

    private static final Logger logger = LogManager.getLogger(StepDefinition.class);
    LoginPage loginpage = new LoginPage();
    UserDashboardPage userdashboardpage = new UserDashboardPage();

    @Given("a user is on the Login page")
    public void aUserIsOnTheLoginPage() {
        loginpage.isPageDisplayed();
    }

    @When("a user enters username {string} and password {string}")
    public void aUserEntersUsernameAndPassword(String username, String password) {
        loginpage.enterUserDetails(username, password);
    }

    @And("click on submit button")
    public void clickOnSubmitButton() {
        loginpage.clickSubmitBtn();
    }


    @Then("a user navigates to the user dashboard")
    public void aUserNavigatesToTheUserDashboard() {
        loginpage.isDashboardDisplayed();
    }


    @When("a user clicks on welcome icon")
    public void aUserClicksOnWelcomeIcon() {
        userdashboardpage.clickonWelcomeicon();
    }

    @And("clicks on logout link")
    public void clicksOnLogoutLink() {
        userdashboardpage.clickOnlogoutlink();
    }
}
