package com.ironlogic.core.pages;


import com.ironlogic.base.TestConfiguration;
import com.ironlogic.base.TestContext;
import com.ironlogic.base.UIAction;
import com.ironlogic.util.RandomUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.IntStream;

import static com.ironlogic.data.RunTimeData.Drop_Down_Value;
import static com.ironlogic.util.DynamicLocator.*;
import static com.ironlogic.util.TextMessage.*;


public class OrderPage extends UIAction {

    private WebDriver driver;
    private TestConfiguration config;
    Logger logger = LoggerFactory.getLogger(TestConfiguration.class);

    private By inpCROLNumber=By.xpath("//*[@id='CROLNumber']");
    private By inpSearchProductOrSKU=By.xpath("//*[@id=\"txtSearch\"]");
    private By plusIcon=By.xpath("//*[@id='divProdList']//div[@class='product-lists-pra']//img[contains(@src,'plus')]");
    private By btnAddToCart=By.xpath("//*[@id=\"btnAddToCart\"]");
    private By btnCartWithItem=By.xpath("//*[@id=\"btnCartWithItemCount\" and ./span[text()>0]]");
    private By btnCheckOut=By.xpath("//*[@id=\"submitOrderButton\"]");
    private By btnSubmitOrder=By.xpath("//*[@id=\"SubmitOrder\"]");
    private By confirmYes=By.xpath("//*[@id=\"modalConfirm\"]//*[@id=\"btnOkConfirm\"]");


    public OrderPage(TestContext testContext) {
        super(testContext);
        this.driver = driver;
        this.config = testContext.getTestConfiguration();
    }



    public void navigateToHamburgerMenu(String menu,String submenu){
        By loc=HamBurgerMenu.setValue(menu).getLocator();
        By subloc=HamBurgerSubMenu.setValue(submenu).getLocator();
        click(loc);
        waitFor(0.2);
        click(subloc);
        verifyElementDisplayed(H5_Header.setValue(NAV_HEADER_INVITE_RETAILER.toString()).getLocator(),"verify the navigation header displayed");
    }

    public void verifyOrderPageDisplayed() {
        verifyPopUp();
        By loc=BTN_CONTINUE_OR_PLACE_ORDER.setValues(BTN_CONTINUE_ORDER.toString(),BTN_PLACE_AN_ORDER.toString()).getLocator();
        verifyElementDisplayed(loc,"Verify header displayed "+BTN_CONTINUE_ORDER);
    }

    public void clickOnOrderPage() {
        By btnplaceOrder = BTN_CONTINUE_OR_PLACE_ORDER.setValues(BTN_CONTINUE_ORDER.toString(),BTN_PLACE_AN_ORDER.toString()).getLocator();
        By btnViewAllProducts = HYPERLINK_BUTTON.setValue(BTN_VIEW_ALL_PRODUCTS).getLocator();
        click(btnplaceOrder);
        waitForPageToLoad();
        verifyElementDisplayed(btnViewAllProducts, "Verify header displayed " + BTN_VIEW_ALL_PRODUCTS);
    }

    public void placeOrderForStockSKu(String sku,int quantity) {
        By clear = HYPERLINK_BUTTON.setValue(BTN_CLEAR_FILTERS).getLocator();
        By loc3 = SPAN_TEXT.setValue(MSG_PRODUCT_ADDED).getLocator();
        clickUsingJS(clear);
        clearText(inpSearchProductOrSKU);
        setText(inpSearchProductOrSKU,sku);
        addQuantity(quantity);
        click(btnAddToCart);
        verifyElementDisplayed(loc3,"Verify header displayed "+MSG_PRODUCT_ADDED);
    }

    public void placeOrderForFlowThroughSKU(String sku,int quantity) {
        By clear = HYPERLINK_BUTTON.setValue(BTN_CLEAR_FILTERS).getLocator();
//        By flowThrogh = HYPERLINK_BUTTON.setValue(BTN_FLOW_THROUGH).getLocator();
        By loc3 = SPAN_TEXT.setValue(MSG_PRODUCT_ADDED).getLocator();
//        click(flowThrogh);
        click(clear);
        clearText(inpSearchProductOrSKU);
        setText(inpSearchProductOrSKU,sku);
        addQuantity(quantity);
        click(btnAddToCart);
        verifyElementDisplayed(loc3,"Verify header displayed "+MSG_PRODUCT_ADDED);
    }

    public void addQuantity(int quanity){
        IntStream.range(1,quanity).forEach(i->click(plusIcon));
    }

    public void clearMyCart(){
        By btnViewMyOrder=BUTTON.setValue(BTN_VIEW_MYORDER).getLocator();
        By btnClearOrder=SPAN_TEXT.setValue(BTN_CLEAR_ORDER).getLocator();
        if(isElementVisible(btnCartWithItem,10)){
            getElement().click();
            click(btnViewMyOrder);
            click(btnClearOrder);
            click(confirmYes);
            waitForPageToLoad();
        }
        else {
            clickOnOrderPage();
        }

    }

    public void submitOrder(){
        By btnViewMyOrder=BUTTON.setValue(BTN_VIEW_MYORDER).getLocator();
        By btnClearOrder=SPAN_TEXT.setValue(BTN_CLEAR_ORDER).getLocator();
        click(btnCartWithItem);
        click(btnViewMyOrder);
        waitForPageToLoad();
        click(btnCheckOut);
        waitForPageToLoad();
        click(btnSubmitOrder);
    }

    public void verifyOrderSubmission(){
        By loc=DIV_TEXT.setValue(MSG_SUBMIT_ORDER).getLocator();
        By loc2=SPAN_TEXT.setValue(MSG_ORDER_SUBMITTED).getLocator();
        verifyElementDisplayed(loc,"verify success message displayed after order submission.");
        verifyElementDisplayed(loc2,"verify success message displayed after order submission.");
    }

}