package com.ironlogic.core.stepdefination;

import com.ironlogic.base.TestConfiguration;
import com.ironlogic.base.TestContext;
import com.ironlogic.core.pages.LoginPage;
import com.ironlogic.core.pages.OrderPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class OrderPageSteps {

    private OrderPage orderPage;
    private TestConfiguration config;

    public OrderPageSteps(TestContext testContext) {
        config = testContext.getTestConfiguration();
        orderPage = new OrderPage(testContext);
    }

    @Then("I verify order page displayed")
    public void i_verify_home_page_displayed() {
        orderPage.verifyOrderPageDisplayed();
        orderPage.clickOnOrderPage();
        orderPage.placeOrderForStockSKu("300025_0.5g___",2);
        orderPage.placeOrderForStockSKu("300084_0.5g___",3);
        orderPage.placeOrderForFlowThroughSKU("102693_3.5g___",2);
        orderPage.placeOrderForFlowThroughSKU("103075_3.5g___",3);
        orderPage.clearMyCart();
    }



}
