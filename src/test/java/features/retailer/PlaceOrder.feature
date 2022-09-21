Feature: Invite retailer and add all the information

  Scenario Outline: login to the website and verify the homepage
    Given I launch the URL
    And I enter retail credentials
    And I click on login button
    Then I verify order page displayed

#    And I select hamburger "<menu>" and "<submenu>"
#    And I select random organization from dropdown
#    And I enter 4 digit random number in CROL
#    And I enter random retail email address
#    And I click on submit button
#    Then I verify the success alert message displayed
#
    Examples:
      | menu                | submenu         |
      | Retailer Management | Invite Retailer |



