package com.ironlogic.core.stepdefination;

import com.ironlogic.base.TestConfiguration;
import com.ironlogic.base.TestContext;
import com.ironlogic.core.pages.LoginPage;
import com.ironlogic.core.pages.MailinatorPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class MailinatorSteps {

    private MailinatorPage mailinatorPage;
    private TestConfiguration config;

    public MailinatorSteps(TestContext testContext){
        config=testContext.getTestConfiguration();
        mailinatorPage =new MailinatorPage(testContext);
    }

    @Given("I navigate to {string} url")
    public void i_navigate_the_url(String url) {
        if (url.equals("mailinator")) {
            url = config.getMailinatorURL();
        }
        mailinatorPage.getURL(url);
    }


    @And("I search email in mailinator")
    public void iSearchEmailInMailinator() {
        mailinatorPage.searchEmail();
    }

    @And("I search user email in mailinator")
    public void iSearchUserEmailInMailinator() {
        mailinatorPage.searchUserEmail();
    }

    @And("I verify the email new email recived in the inbox")
    public void iVerifyTheEmailNewEmailRecivedInTheInbox() {
        mailinatorPage.verifyNewEmailRecievedInTheInbox();
    }

    @And("I verify new email recived for new user")
    public void iVerifyTheNewEmailRecivedForTheUser() {
        mailinatorPage.verifyNewEmailRecievedForUserCreation();
    }

    @And("I click on email and login")
    public void clickOnEmail() {
        mailinatorPage.verifyEmailRecivedAndCLickOnLOgin();
    }

    @And("I click on the email receievd")
    public void iClickOnTheEmailReceievd() {
        mailinatorPage.clickOnNewEmailReceived();
    }

    @And("I click on registration link from the email")
    public void iClickOnRegistrationLinkFromTheEmail() {
        mailinatorPage.clickOnRegistrationLinkReceivedFromTheEmail();
    }

    @Then("I verify the new tab displayed with create account form")
    public void iVerifyTheNewTabDisplayedWithCreateAccountForm() {
        mailinatorPage.verifyNewTabDisplayedWithCreateAccountForm();
    }

    @Then("I verify new tab displayed")
    public void iVerifyTheNewTabDisplayed() {
        mailinatorPage.verifyNewTabDisplayed();
    }



}
