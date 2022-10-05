Feature: Invite retailer and add all the information

  @PlaceOrder
  Scenario Outline: login to the website and verify the homepage
    Given I launch the URL
    And I enter retail credentials from data file
    |<retailusers>|
    And I click on login button
    Then I verify order page displayed
    And I clear my cart
    And I add stock SKU
    And I add flow through SKU
    And I submit the order
    Then I verify the order submit message


    Examples:
      | retailusers|
      | user1 |
      | user2 |