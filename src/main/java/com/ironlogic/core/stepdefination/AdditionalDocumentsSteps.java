package com.ironlogic.core.stepdefination;

import com.ironlogic.base.TestConfiguration;
import com.ironlogic.base.TestContext;
import com.ironlogic.core.pages.AdditionalDocumentsPage;
import com.ironlogic.core.pages.DeliveryInformationPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdditionalDocumentsSteps {

    private AdditionalDocumentsPage additionalDocumentsPage;
    private TestConfiguration config;

    public AdditionalDocumentsSteps(TestContext testContext){
        config=testContext.getTestConfiguration();
        additionalDocumentsPage =new AdditionalDocumentsPage(testContext);
    }

    @And("I upload all additional documents")
    public void iUploadAllAdditionalDocuments() {
        additionalDocumentsPage.uploadFiles();
    }


    @And("I save the information and click on review and confirm")
    public void iSaveTheInformationAndClickOnNext() {
        additionalDocumentsPage.saveAndClickOnReviewAndConfirm();
    }

    @Then("I verify CROL information header displayed and clicks on submit")
    public void iVerifyCROLInformationHeaderDisplayedAndClicksOnSubmit() {
        additionalDocumentsPage.verifyCROLHolderheaderAndCLickonSubmit();
    }

    @Then("I verify onboarding process details displayed")
    public void iVerifyOnboardingProcessDetailsDisplayed(DataTable dataTable) {
        List<Map<String, String>> onboarding=dataTable.asMaps();
        for (Map<String, String> onProcessDetails:onboarding){
            Set<String> steps=onProcessDetails.keySet();
            for (String step:steps){
                additionalDocumentsPage.verifyOnBoardingProcessDisplayed(step,onProcessDetails.get(step));
            }
        }
    }
}
