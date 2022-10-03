Feature: Create Users

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
    And I add "OCS" user details
    Then I click on submit button

    Examples:
      | menu            | submenu |
      | User Management | Users   |
