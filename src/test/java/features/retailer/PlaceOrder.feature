Feature: Invite retailer and add all the information

  Scenario Outline: Ordering Configuration
    Given I launch the URL
    And I enter admin credentials
    And I click on login button
    Then I verify home page displayed
    And I select hamburger "<menu>" and "<submenu>"
    Then I setup order configuration
      | 3123,9234 |

    Examples:
      | menu             | submenu                |
      | Order Management | Ordering Configuration |



  @PlaceOrder
  Scenario Outline: login to the website and verify the homepage
    Given I launch the URL
    And I enter retail credentials from data file
      | <retailusers> |
    And I click on login button
    Then I verify order page displayed
    And I clear my cart
    And I add stock SKU
    And I add flow through SKU
    And I submit the order
    Then I verify the order submit message
    And I click on order history
    Then I verify the order history for flow through
    Then I verify the order history for Replenishment


    Examples:
      | retailusers |
      | user1       |
      | user2       |