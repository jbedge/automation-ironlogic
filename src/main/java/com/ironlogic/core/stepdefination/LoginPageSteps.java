package com.ironlogic.core.stepdefination;

import com.ironlogic.base.TestContext;
import com.ironlogic.core.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class LoginPageSteps {

    private LoginPage loginPage;

    public LoginPageSteps(TestContext testContext){
        loginPage =new LoginPage(testContext);
    }

    @Then("I verify home page displayeds")
    public void i_verify_home_page_displayeds() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("I launch the URL")
    public void i_launch_the_url() {
        loginPage.getURL();
    }

    @Given("I enter credentails")
    public void i_enter_credentails() {
        loginPage.enterCredentials();
    }
    @Given("I click on login button")
    public void i_click_on_login_button() {
        loginPage.clickOnLogin();
    }
    @Then("I verify home page displayed")
    public void i_verify_home_page_displayed() {
        loginPage.waitForHeaderToLoad();
    }


}
