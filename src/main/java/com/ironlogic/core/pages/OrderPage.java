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

    private By inpSearchProductOrSKU=By.xpath("//*[@id=\"txtSearch\"]");
    private By maxQty=By.xpath("//span[@id='maxQty' and text()>0]");
    private By inpOrderQty=By.xpath("//*[@id='divProdList']//input[@class='Qty']");
//    private By btnAddToCart=By.xpath("//*[@id='btnAddToCart']");
    private By btnCartWithItem=By.xpath("//*[@id=\"btnCartWithItemCount\" and ./span[text()>0]]");
    private By btnCheckOut=By.xpath("//*[@id=\"submitOrderButton\"]");
    private By hdrFlowThroughOrder=By.xpath("//a[contains(text(),'Flow-Through Order')]");
    private By btnSubmitOrder=By.xpath("//*[@id=\"SubmitOrder\"]");
    private By confirmYes=By.xpath("//*[@id=\"modalConfirm\"]//*[@id=\"btnOkConfirm\"]");
    private By orderIDFlowThrough=By.xpath("(//*[@id='userList']//td[1]/a)[1]");
    private By orderIDReplenishment=By.xpath("(//*[@id='userList']//td[1]/a)[2]");
    private By orderID=By.xpath("//span[normalize-space()='ORDER ID:']/following-sibling::span");
    private By orderMenuDropDown=By.xpath("//*[@id='dropdownMenuButton']");

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

    public void verifyOrderPageDisplayed1() {
        verifyPopUp();
        By loc=BTN_CONTINUE_OR_PLACE_ORDER.setValues(BTN_CONTINUE_ORDER.toString(),BTN_PLACE_AN_ORDER.toString()).getLocator();
        verifyElementDisplayed1(loc,"Verify header displayed "+BTN_CONTINUE_ORDER);
    }

    public void clickOnOrderPage() {
        By btnplaceOrder = BTN_CONTINUE_OR_PLACE_ORDER.setValues(BTN_CONTINUE_ORDER.toString(),BTN_PLACE_AN_ORDER.toString()).getLocator();
        By btnViewAllProducts = HYPERLINK_BUTTON.setValue(BTN_VIEW_ALL_PRODUCTS).getLocator();
        click(btnplaceOrder);
        waitForPageToLoad();
        verifyElementDisplayed(btnViewAllProducts, "Verify header displayed " + BTN_VIEW_ALL_PRODUCTS);
    }

    public void clickOnOrderPage1() {
        By btnplaceOrder = BTN_CONTINUE_OR_PLACE_ORDER.setValues(BTN_CONTINUE_ORDER.toString(),BTN_PLACE_AN_ORDER.toString()).getLocator();
        By btnViewAllProducts = HYPERLINK_BUTTON.setValue(BTN_VIEW_ALL_PRODUCTS).getLocator();
        click(btnplaceOrder);
        waitForPageToLoad();
        verifyElementDisplayed1(btnViewAllProducts, "Verify header displayed " + BTN_VIEW_ALL_PRODUCTS);
    }

    public void placeOrderForStockSKu(String sku,int quantity) {
        By clear = HYPERLINK_BUTTON.setValue(BTN_CLEAR_FILTERS).getLocator();
        By msg = SPAN_TEXT.setValue(MSG_PRODUCT_ADDED).getLocator();
        By plusIcon=OrderQuantity.setValue(sku).getLocator();
        By btnAddToCart=AddToCart.setValue(sku).getLocator();

        clickUsingJS(clear);
        clearText(inpSearchProductOrSKU);
        setText(inpSearchProductOrSKU,sku);
        WebElement element=waitForVisibilityOfElement(plusIcon);
        addQuantity(element,quantity);
        click(btnAddToCart);
        verifyElementDisplayed(msg,"Verify header displayed "+MSG_PRODUCT_ADDED);
    }

    public void placeOrderForStockSKu1(String sku,int quantity) {
        By clear = HYPERLINK_BUTTON.setValue(BTN_CLEAR_FILTERS).getLocator();
        By msg = SPAN_TEXT.setValue(MSG_PRODUCT_ADDED).getLocator();
        By plusIcon=OrderQuantity.setValue(sku).getLocator();
        By btnAddToCart=AddToCart.setValue(sku).getLocator();

        clickUsingJS(clear);
        clearText(inpSearchProductOrSKU);
        setText(inpSearchProductOrSKU,sku);
        WebElement element=waitForVisibilityOfElement(plusIcon);
        addQuantity(element,quantity);
        click(btnAddToCart);
        verifyElementDisplayed1(msg,"Verify header displayed "+MSG_PRODUCT_ADDED);
    }

    public void placeOrderForFlowThroughSKU(String sku,int quantity) {
        By clear = HYPERLINK_BUTTON.setValue(BTN_CLEAR_FILTERS).getLocator();
        By plusIcon=OrderQuantity.setValue(sku).getLocator();
        By msg = SPAN_TEXT.setValue(MSG_PRODUCT_ADDED).getLocator();
        By btnAddToCart=AddToCart.setValue(sku).getLocator();
//        By flowThrogh = HYPERLINK_BUTTON.setValue(BTN_FLOW_THROUGH).getLocator();
//        click(flowThrogh);

        clickUsingJS(clear);
        clearText(inpSearchProductOrSKU);
        setText(inpSearchProductOrSKU,sku.trim());
        WebElement element=waitForVisibilityOfElement(plusIcon);
        addQuantity(element,quantity);
        click(btnAddToCart);
        verifyElementDisplayed(msg,"Verify header displayed "+MSG_PRODUCT_ADDED);
    }

    public void placeOrderForFlowThroughSKU1(String sku,int quantity) {
        By clear = HYPERLINK_BUTTON.setValue(BTN_CLEAR_FILTERS).getLocator();
        By plusIcon=OrderQuantity.setValue(sku).getLocator();
        By msg = SPAN_TEXT.setValue(MSG_PRODUCT_ADDED).getLocator();
        By btnAddToCart=AddToCart.setValue(sku).getLocator();

        clickUsingJS(clear);
        clearText(inpSearchProductOrSKU);
        setText(inpSearchProductOrSKU,sku);
        WebElement element=waitForVisibilityOfElement(plusIcon);
        addQuantity(element,quantity);
        click(btnAddToCart);
        verifyElementDisplayed1(msg,"Verify header displayed "+MSG_PRODUCT_ADDED);
    }

    public void addQuantity(WebElement element,int quanity){
        String qty=waitForVisibilityOfElement(inpOrderQty).getAttribute("value");
        if(isElementVisible(maxQty,4)){
            String actualQty=getElement().getText();
            clearText(inpOrderQty);
            setText(inpOrderQty,actualQty);
        }
        else {
            IntStream.range(1,quanity).forEach(i->element.click());
        }

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

    public void clearMyCart1(){
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
            clickOnOrderPage1();
        }

    }

    public void submitOrder(){
        By btnViewMyOrder=BUTTON.setValue(BTN_VIEW_MYORDER).getLocator();
        By btnClearOrder=SPAN_TEXT.setValue(BTN_CLEAR_ORDER).getLocator();
        click(btnCartWithItem);
        click(btnViewMyOrder);
        waitForPageToLoad();
        click(hdrFlowThroughOrder);
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

    public void verifyOrderSubmission1(){
        By loc=DIV_TEXT.setValue(MSG_SUBMIT_ORDER).getLocator();
        By loc2=SPAN_TEXT.setValue(MSG_ORDER_SUBMITTED).getLocator();
        verifyElementDisplayed1(loc,"verify success message displayed after order submission.");
        verifyElementDisplayed1(loc2,"verify success message displayed after order submission.");
    }

    public void clickOnOrderHistory(){
        click(orderMenuDropDown);
        By menuOrderHistory=HYPERLINK_BUTTON.setValue(MENU_ORDER_HISTORY).getLocator();
        click(menuOrderHistory);
    }

    public void clickOnOrderId(){
        click(orderIDFlowThrough);
    }

    public void clickOnOrderIDReplenishment(){
        click(orderIDReplenishment);
    }

    public void clickOnClose(){
        By btnClose=BUTTON.setValue(BTN_CLOSE).getLocator();
        click(btnClose);
    }

    public void clickOnOrderHistoryAndVerifyFlowThroughSKU(String sku,String qty){
        By hdrOrderDetails=H5_Header.setValue(HDR_ORDER_DETAILS).getLocator();
        waitForVisibilityOfElement(hdrOrderDetails);
        By skuLoc=GRID_DATA.setValue(sku).getLocator();
        By skuQty=GRID_DATA.setValue(qty).getLocator();
        if(isElementVisible(skuLoc,4)){
            verifyElementDisplayed(skuLoc,"verified the presence of SKU");
//            verifyElementDisplayed(skuQty,"verified the presence of Quantity");
            String orderId=waitForVisibilityOfElement(orderID).getText();
            assertTrue(orderId.length()>0,"Verify the Order ID displayed");
        }

    }

    public void clickOnOrderHistoryAndVerifyReplenishmentSKU(String sku,String qty){

        By hdrOrderDetails=H5_Header.setValue(HDR_ORDER_DETAILS).getLocator();
        waitForVisibilityOfElement(hdrOrderDetails);
        By skuLoc=GRID_DATA.setValue(sku).getLocator();
        By skuQty=GRID_DATA.setValue(qty).getLocator();
        if(isElementVisible(skuLoc,6)){
            verifyElementDisplayed(skuLoc,"verified the presence of SKU");
//            verifyElementDisplayed(skuQty,"verified the presence of Quantity");
            String orderId=waitForVisibilityOfElement(orderID).getText();
            assertTrue(orderId.length()>0,"Verify the Order ID displayed");
        }
    }

}