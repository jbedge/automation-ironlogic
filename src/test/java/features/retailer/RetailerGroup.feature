@createretailer
Feature: Create Retailer group

  Scenario Outline:Create Retailer group
    Given I launch the URL
    And I enter admin credentials
    And I click on login button
    Then I verify home page displayed
    And I select hamburger "<menu>" and "<submenu>"
    And Create retailer group

    Examples:
      | menu                | submenu         |
      | Retailer Management | Retailer Groups |