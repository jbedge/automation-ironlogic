package com.ironlogic.core.stepdefination;

import com.ironlogic.base.TestConfiguration;
import com.ironlogic.base.TestContext;
import com.ironlogic.core.pages.LoginPage;
import com.ironlogic.core.pages.UserPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.List;

public class UserPageSteps {

    private UserPage userPage;
    private TestConfiguration config;

    public UserPageSteps(TestContext testContext) {
        config = testContext.getTestConfiguration();
        userPage = new UserPage(testContext);
    }

    @Then("I verify {string} page displayed")
    public void i_verify_home_page_displayed(String arg) {
        userPage.verifyUserHeaderDisplayed(arg);
    }

    @Then("I click on new user button")
    public void i_click_on_new_user_button() {
        userPage.clickOnNewUser();
    }

    @Then("I click on new role button")
    public void i_click_on_new_role_button() {
        userPage.clickOnNewRole();
    }

    @And("I add {string} user details")
    public void iAddUserDetails(String arg0) {
        userPage.addOCSUserDetails(arg0);
    }

    @And("I verify success message")
    public void iverifySuccessMessage() {
        userPage.verifySuccessMessage();
    }

    @Then("I setup order configuration")
    public void iSetupOrderConfiguration(DataTable dataTable) {
        String[] list=dataTable.asList().get(0).split(",");
        userPage.addOrderCongiguring(list);
    }
}