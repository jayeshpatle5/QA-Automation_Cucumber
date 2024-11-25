#@chrome
Feature: Login Feature


Background: initial steps
  Scenario: Check Login function
    Given a user is on the Login page
    When  a user pass a username and password
    And   click on submit button
    Then  a user navigates to the homepage


