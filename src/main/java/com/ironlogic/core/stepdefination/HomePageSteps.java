package com.ironlogic.core.stepdefination;

import com.ironlogic.base.TestConfiguration;
import com.ironlogic.base.TestContext;
import com.ironlogic.core.pages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class HomePageSteps {

    private HomePage homePage;
    private TestContext testContext;
    private TestConfiguration testConfiguration;

    public HomePageSteps(TestContext testContext){
//      PropertyConfigurator.configure(System.getProperty("user.dir")+"\\resources\\log4j.properties");
        this.testContext=testContext;
        testConfiguration=testContext.getTestConfiguration();
        homePage =new HomePage(testContext);
    }

    @And("I select hamburger {string} and {string}")
    public void iSelectHamburgerAnd(String arg0, String arg1) {
        homePage.navigateToHamburgerMenu(arg0,arg1);
    }


    @And("I select random organization from dropdown")
    public void iSelectRandomOrganizationFromDropdown() {
        homePage.selectOrganizationDropDown();
    }

    @And("I enter {int} digit random number in CROL")
    public void iEnterDigitRandomNumberInCROL(int arg0) {
        homePage.setCROLNumber(arg0);
    }

    @And("I enter random retail email address")
    public void iEnterRandomEmailAddress() {
        homePage.setEmail();
    }

    @And("I click on submit button")
    public void iClickOnSubmitButton() {
        homePage.clickOnSubmit();
    }

    @Then("I verify the success alert message displayed")
    public void iVerifyTheSuccessAlertMessageDisplayed() {
        homePage.verifySuccessMessage();
    }


}
