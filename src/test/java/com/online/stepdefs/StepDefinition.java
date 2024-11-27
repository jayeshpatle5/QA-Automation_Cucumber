package com.online.stepdefs;

import com.online.pages.LoginPage;
import com.online.pages.MyInfoPage;
import com.online.pages.UserDashboardPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;


public class StepDefinition {

    private static final Logger logger = LogManager.getLogger(StepDefinition.class);
    LoginPage loginpage = new LoginPage();
    UserDashboardPage userdashboardpage = new UserDashboardPage();
    MyInfoPage myinfopage = new MyInfoPage();

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

    @When("a user clicks on myinfo tab")
    public void aUserClicksOnMyinfoTab() {
        userdashboardpage.clickOnMyinfoTab();
    }

    @Then("user should be on the myinfo section")
    public void userShouldBeOnTheMyinfoSection() {
        myinfopage.isMyInfopageDisplayed();
    }

    @And("user clicks on contact details link")
    public void userClicksOnContactDetailsLink() {
        myinfopage.clickOnContactDetailsLink();
    }

    @Then("user should see the contact detail form")
    public void userShouldSeeTheContactDetailForm() {
        myinfopage.isContactDetailsPageDisplayed();
    }

    @When("user fill the contact details")
    public void userFillTheContactDetails(DataTable table) {
        myinfopage.clickOnEditBtn();
        List<String> list=table.asList();

        myinfopage.enterContactdetails(list);
    }

    @And("click on the save button")
    public void clickOnTheSaveButton() {
        myinfopage.clickOnSaveBtn();
    }

    @Then("user should see the {string} message")
    public void userShouldSeeTheMessage(String str) {
        myinfopage.isContactSuccessfullySaved(str);
    }
}
