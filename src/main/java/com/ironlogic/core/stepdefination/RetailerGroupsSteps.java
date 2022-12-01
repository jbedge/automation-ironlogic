package com.ironlogic.core.stepdefination;

import com.ironlogic.base.TestConfiguration;
import com.ironlogic.base.TestContext;
import com.ironlogic.core.pages.CreateRolePage;
import com.ironlogic.core.pages.RetailerGroupsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class RetailerGroupsSteps {

    private RetailerGroupsPage retailerGroupsPage;
    private TestConfiguration config;

    public RetailerGroupsSteps(TestContext testContext) {
        config = testContext.getTestConfiguration();
        retailerGroupsPage = new RetailerGroupsPage(testContext);
    }

    @And("Create retailer group")
    public void iClickOnCreateRetailerGroupButton() {
        retailerGroupsPage.createRetailerGroup();
    }


}