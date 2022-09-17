package com.ironlogic.core.stepdefination;

import com.ironlogic.base.TestConfiguration;
import com.ironlogic.base.TestContext;
import com.ironlogic.core.pages.CROLInformationPage;
import com.ironlogic.core.pages.CreateAccountPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class CROLInformationSteps {

    private CROLInformationPage crolInformationPage;
    private TestConfiguration config;

    public CROLInformationSteps(TestContext testContext){
        config=testContext.getTestConfiguration();
        crolInformationPage =new CROLInformationPage(testContext);
    }

    @And("I add CROL general information")
    public void iAddCROLGeneralInformation(DataTable dataTable) {
        String entity_type=dataTable.asMaps().get(0).get("Entity Type");
        crolInformationPage.addCROLCreateAccountDetails(entity_type);
    }


    @And("I add CROL contact details")
    public void iAddContactDetails() {
        crolInformationPage.addCROLContactDetails();
    }

    @And("I add CROL legal address details")
    public void iAddCROLLegalAddressDetails() {
        crolInformationPage.addCROLLegalAddressDetails();
    }

    @And("I add CROL store address details")
    public void iAddCROLStoreAddressDetails() {
        crolInformationPage.addCROLStoreAddressDetails();
    }

    @Then("I add CROL firstnation retailer Information and navigate to next")
    public void iAddCROLFirstnationRetailerInformationAndNavigateToNext() {
        crolInformationPage.addCROLFirstNationRetailerInformationAndSelectNext();
    }
}
