Feature: Invite ratailer and add all the information

  Scenario Outline: login to the website and verify the homepage
    Given I launch the URL
    And I enter admin credentials
    And I click on login button
    Then I verify home page displayed
    And I select hamburger "<menu>" and "<submenu>"
    And I select random organization from dropdown
    And I enter 4 digit random number in CROL
    And I enter random retail email address
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
    And I add account details
    And I click on confirm password button
    And I accept terms and condition and clicks on next step
    And I add CROL general information
      | Entity Type         |
      | Limited Partnership |
    And I add CROL contact details
    And I add CROL legal address details
    And I add CROL store address details
    Then I add CROL firstnation retailer Information and navigate to next
    And I add delivery information
    And I add operating hours
      | Sunday             | Monday             | Tuesday            | Wednesday          | Thursday           | Friday             | Saturday           |
      | 09:00 AM -10:00 AM | 09:00 AM -09:00 PM | 09:00 AM -09:00 PM | 09:00 AM -09:00 PM | 09:00 AM -09:00 PM | 09:00 AM -01:00 PM | 12:00 AM -01:30 AM |
    And I save the information and click on next
    And I upload all additional documents
    Then I save the information and click on review and confirm
    Then I verify CROL information header displayed and clicks on submit
    Then I verify onboarding process details displayed
      | Step 1 :                 | Step 2 :                   | Step 3 :                       | Step 4 :                       |
      | NDA Acceptance,Submitted | CROL Information,Submitted | Delivery Information,Submitted | Additional Documents,Submitted |



