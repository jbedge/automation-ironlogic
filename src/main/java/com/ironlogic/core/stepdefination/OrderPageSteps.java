package com.ironlogic.core.stepdefination;

import com.ironlogic.base.TestConfiguration;
import com.ironlogic.base.TestContext;
import com.ironlogic.core.pages.LoginPage;
import com.ironlogic.core.pages.OrderPage;
import io.cucumber.java.en.And;
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
    }

    @Then("I add stock SKU")
    public void addStockSKU() {
        orderPage.placeOrderForStockSKu("300025_0.5g___",1);
        orderPage.placeOrderForStockSKu("300084_0.5g___",2);
    }

    @Then("I add flow through SKU")
    public void addFlowSKU() {
        orderPage.placeOrderForFlowThroughSKU("102693_3.5g___",2);
        orderPage.placeOrderForFlowThroughSKU("103075_3.5g___",1);
    }

    @Then("I clear my cart")
    public void clearMyCart() {
        orderPage.clearMyCart();
    }


    @And("I submit the order")
    public void iSubmitTheOrder() {
        orderPage.submitOrder();
    }

    @Then("I verify the order submit message")
    public void iVerifyTheOrderSubmitMessage() {
        orderPage.verifyOrderSubmission();
    }
}
