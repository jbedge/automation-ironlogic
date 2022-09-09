
Feature: Login to the website and verify the home page

  Scenario: login to the website and verify the homepage
    Given I launch the URL
    And I enter credentails
    And I click on login button
    Then I verify home page displayed



