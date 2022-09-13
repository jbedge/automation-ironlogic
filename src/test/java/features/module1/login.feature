Feature: Login to the website and verify the home page

  Scenario Outline: login to the website and verify the homepage
    Given I launch the URL
    And I enter credentails
    And I click on login button
    Then I verify home page displayed
    And I select hamburger "<menu>" and "<submenu>"
    And I select random organization from dropdown
    And I enter 4 digit random number in CROL
    And I enter random email address
    And I click on submit button
    Then I verify the success alert message displayed

    Examples:
      | menu                | submenu         |
      | Retailer Management | Invite Retailer |