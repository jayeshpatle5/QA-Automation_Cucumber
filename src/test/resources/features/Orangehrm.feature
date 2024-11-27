#@chrome
Feature: Orangehrm myinfo

  Background: Login to the application
    Given a user is on the Login page
    When  a user enters username "Admin" and password "Qedge123!@#"
    And   click on submit button
    Then  a user navigates to the user dashboard

  Scenario: Check Login function
    Then  a user navigates to the user dashboard

  Scenario: Fill contact details
    When a user clicks on myinfo tab
    Then user should be on the myinfo section
    And  user clicks on contact details link
    Then user should see the contact detail form
    When user fill the contact details
#         |Address Street 1|Address Street 2|City     |State/Province|Zip/Postal Code|Country|Home Telephone|Mobile    |Work Telephone|Work Email          |Other Email     |
         |street 1        |street 2        |Hyderabad|Telangana     |500081         |India  |7874657856    |9347564577|7563457867    |ram123@qedgetech.com|ram456@gmail.com|
    And  click on the save button
    Then user should see the "Successfully Saved" message


  Scenario: Logout From the application
    When a user clicks on welcome icon
    And  clicks on logout link
    Then a user is on the Login page
