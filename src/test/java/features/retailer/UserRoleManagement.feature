Feature: Create OCS and License Producer Users

  @role
  Scenario Outline: Create OCS user details
    Given I launch the URL
    And I enter admin credentials
    And I click on login button
    Then I verify home page displayed
    And I select hamburger "<menu>" and "<submenu>"
    And I click on new role button
    Then create new role for the user
    Then update the role
    Then delete the role

    Examples:
      | menu            | submenu |
      | User Management | Roles   |

