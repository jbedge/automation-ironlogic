Feature: Create OCS and License Producer Users

  Scenario Outline: Create OCS user details
    Given I launch the URL
    And I enter admin credentials
    And I click on login button
    Then I verify home page displayed
    And I select hamburger "<menu>" and "<submenu>"
    And I verify "<submenu>" page displayed
    And I click on new user button
    And I add "OCS" user details
    Then I click on submit button
    And I verify success message
    Given I navigate to "mailinator" url
    And I search user email in mailinator
    And I verify new email recived for new user
    And I click on email and login
    Then I verify new tab displayed

    Examples:
      | menu            | submenu |
      | User Management | Users   |

  Scenario Outline: Create License Producer user details
    Given I launch the URL
    And I enter admin credentials
    And I click on login button
    Then I verify home page displayed
    And I select hamburger "<menu>" and "<submenu>"
    And I verify "<submenu>" page displayed
    And I click on new user button
    And I add "License Producer" user details
    Then I click on submit button
    And I verify success message
    Given I navigate to "mailinator" url
    And I search user email in mailinator
    And I verify new email recived for licenced producer
    And verify Email Received For LicensedProducer
    Then I verify new tab displayed for License Producer
    And I create new password
    And I click on confirm password
    Then I enter random generated credentials
    And I click on login button
    Then I verify home page displayed

    Examples:
      | menu            | submenu |
      | User Management | Users   |
