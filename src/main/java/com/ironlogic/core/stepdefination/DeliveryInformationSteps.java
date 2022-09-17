package com.ironlogic.core.stepdefination;

import com.ironlogic.base.TestConfiguration;
import com.ironlogic.base.TestContext;
import com.ironlogic.core.pages.CROLInformationPage;
import com.ironlogic.core.pages.DeliveryInformationPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class DeliveryInformationSteps {

    private DeliveryInformationPage deliveryInformationPage;
    private TestConfiguration config;

    public DeliveryInformationSteps(TestContext testContext){
        config=testContext.getTestConfiguration();
        deliveryInformationPage =new DeliveryInformationPage(testContext);
    }

    @And("I add delivery information")
    public void iAddDeliveryInformation() {
        deliveryInformationPage.addDeliveryInformation();
    }

    @And("I add operating hours")
    public void iAddDeliveryInformation(DataTable dataTable) {
        List<Map<String, String>> data=dataTable.asMaps();
        for (Map<String, String> dt:data){
            Set<String> days=dt.keySet();
            for (String day:days){
                deliveryInformationPage.addOperatingHours(day,dt.get(day));
            }
        }
    }


    @And("I save the information and click on next")
    public void iSaveTheInformationAndClickOnNext() {
        deliveryInformationPage.saveAndClickOnNext();
    }
}
