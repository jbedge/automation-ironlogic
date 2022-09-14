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

  Scenario: verify the email sent to mailinator and proceed from the email link
    Given I navigate to "mailinator" url
    And I search email in mailinator
    And I verify the email new email recived in the inbox
    And I click on the email receievd
    And I click on registration link from the email
    Then I verify the new tab displayed with create account form
