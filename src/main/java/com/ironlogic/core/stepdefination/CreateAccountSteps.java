package com.ironlogic.core.stepdefination;

import com.ironlogic.base.TestConfiguration;
import com.ironlogic.base.TestContext;
import com.ironlogic.core.pages.CreateAccountPage;
import com.ironlogic.core.pages.MailinatorPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CreateAccountSteps {

    private CreateAccountPage createAccountPage;
    private TestConfiguration config;

    public CreateAccountSteps(TestContext testContext){
        config=testContext.getTestConfiguration();
        createAccountPage =new CreateAccountPage(testContext);
    }

    @And("I add account details")
    public void iAddAccountDetails() {
        createAccountPage.addCreateAccountDetails();
    }

    @And("I click on confirm password button")
    public void iClickOnConfirmPasswordButton() {
        createAccountPage.clickOnConfirmPassword();
    }

    @And("I accept terms and condition and clicks on next step")
    public void iAcceptTermsAndConditionAndClicksOnNextStep() {
        createAccountPage.selectTermsAndConditionAndClickNextStep1();
    }
}
