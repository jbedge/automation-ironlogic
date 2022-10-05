package com.ironlogic.core.stepdefination;

import com.ironlogic.base.TestConfiguration;
import com.ironlogic.base.TestContext;
import com.ironlogic.core.pages.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class LoginPageSteps {

    private LoginPage loginPage;
    private TestConfiguration config;

    public LoginPageSteps(TestContext testContext) {
        config = testContext.getTestConfiguration();
        loginPage = new LoginPage(testContext);
    }



    @Given("I launch the URL")
    public void i_launch_the_url() {
        loginPage.getURL();
    }



    @Given("I enter admin credentials")
    public void i_enter_credentails() {
        loginPage.enterCredentials();
    }

    @Given("I enter random generated credentials")
    public void i_enterRandomGeneratedCredentails() {
        loginPage.enterRandomGeneratedCredentials();
    }



    @Given("I enter retail credentials")
    public void i_enter_retailt_credentails() {
        loginPage.enterRetailCredentials();
    }

    @Given("I click on login button")
    public void i_click_on_login_button() {
        loginPage.clickOnLogin();
    }

    @Then("I verify home page displayed")
    public void i_verify_home_page_displayed() {
        loginPage.waitForHeaderToLoad();
    }


    @And("I enter retail credentials from data file")
    public void iEnterRetailCredentialsFromDataFile(DataTable dataTable) {
        loginPage.enterRetailCredentials(dataTable);
    }
}
