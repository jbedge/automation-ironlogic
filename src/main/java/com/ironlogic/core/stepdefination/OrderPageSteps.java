package com.ironlogic.core.stepdefination;

import com.ironlogic.base.TestConfiguration;
import com.ironlogic.base.TestContext;
import com.ironlogic.core.pages.LoginPage;
import com.ironlogic.core.pages.OrderPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.ArrayList;

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
        ArrayList<String> stockSKU = config.getOrder_data().getStockSKU();
        for (String order : stockSKU) {
            String[] skuqty = order.split("\\|");
            orderPage.placeOrderForStockSKu(skuqty[0], Integer.parseInt(skuqty[1]));
        }
    }

    @Then("I add flow through SKU")
    public void addFlowSKU() {
        ArrayList<String> stockSKU = config.getOrder_data().getFlowThroghSKU();
        for (String order : stockSKU) {
            String[] skuqty = order.split("\\|");
            orderPage.placeOrderForFlowThroughSKU(skuqty[0], Integer.parseInt(skuqty[1]));
        }
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

    @Then("I verify the order history for flow through")
    public void iverifytheorderhistoryforflowthrough() {
        ArrayList<String> stockSKU = config.getOrder_data().getFlowThroghSKU();
        for (String order : stockSKU) {
            String[] skuqty = order.split("\\|");
            orderPage.clickOnOrderHistoryAndVerifyFlowThroughSKU(skuqty[0], skuqty[1]);
        }
    }

    @Then("I click on order history")
    public void iclickOnOrderHistory() {
        orderPage.clickOnOrderHistory();
    }

    @Then("I verify the order history for Replenishment")
    public void iverifytheorderhistoryforReplenishment() {
        ArrayList<String> stockSKU = config.getOrder_data().getFlowThroghSKU();
        for (String order : stockSKU) {
            String[] skuqty = order.split("\\|");
            orderPage.clickOnOrderHistoryAndVerifyReplenishmentSKU(skuqty[0], skuqty[1]);
        }
    }

}
