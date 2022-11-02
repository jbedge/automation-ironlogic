package com.ironlogic.core.stepdefination;

import com.ironlogic.base.TestConfiguration;
import com.ironlogic.base.TestContext;
import com.ironlogic.core.pages.CreateRolePage;
import com.ironlogic.core.pages.UserPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class CreateRoleSteps {

    private CreateRolePage createRolePage;
    private TestConfiguration config;

    public CreateRoleSteps(TestContext testContext) {
        config = testContext.getTestConfiguration();
        createRolePage = new CreateRolePage(testContext);
    }

    @Then("create new role for the user")
    public void i_verify_home_page_displayed() {
        createRolePage.addGeneralInformation();
    }

    @Then("update the role")
    public void i_update_the_role() {
        createRolePage.updateRole();
    }

    @Then("delete the role")
    public void i_delete_the_role() {
        createRolePage.deleteRole();
    }


}