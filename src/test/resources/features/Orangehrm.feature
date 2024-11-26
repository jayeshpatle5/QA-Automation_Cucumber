#@chrome
Feature: Login Feature

  Background: Login to the application
    Given a user is on the Login page
    When  a user enters username "Admin" and password "Qedge123!@#"
    And   click on submit button
    Then  a user navigates to the user dashboard

  Scenario: Check Login function
    Then  a user navigates to the user dashboard

  Scenario: Logout From the application
    When a user clicks on welcome icon
    And  clicks on logout link
    Then a user is on the Login page
