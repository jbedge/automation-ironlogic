package com.ironlogic.util;

import org.openqa.selenium.By;

public enum TextMessage {

    EMAIL_SUBJECT("You are Invited to Register on OCS's B2B Portal"),
    CREATE_ACCOUNT_TITLE("Create a Password - OCS Wholesale"),
    HEADER_CREATE_ACCT("Create Account"),
    NAV_HEADER_INVITE_RETAILER("Retailer Management > Invite Retailers"),
    BTN_CONFIRM_PASSWORD("Confirm Password"),
    BTN_NEXT_STEP1("NEXT STEP 1: CROL HOLDER INFORMATION"),
    BTN_NEXT_STEP2("Next: Step 2: Delivery Information"),
    BTN_NEXT_STEP3("Next: Step 3: Additional Documents"),
    BTN_REVIEW_CONFIRM("Review & Confirm"),
    BTN_PLACE_AN_ORDER("PLACE AN ORDER"),
    BTN_CONTINUE_ORDER("CONTINUE ORDER"),
    BTN_VIEW_ALL_PRODUCTS("View All Products"),
    BTN_CLEAR_FILTERS("CLEAR FILTERS"),
    BTN_FLOW_THROUGH("Flow-Through"),
    BTN_SUBMIT_APPLICATION("SUBMIT APPLICATION"),
    HDR_DELIVERY_INFO("Delivery Information"),
    GO("GO"),
    BTN_SAVE("Save"),
    SUCCESS_MESSAGE("Your information has been successfully saved"),
    MSG_PRODUCT_ADDED("Product Added To Your Order"),
    BTN_VIEW_MYORDER("VIEW MY ORDER"),
    BTN_CLEAR_ORDER("CLEAR ORDER"),
    HDR_UPLOAD_DOCUMENTS("Upload Documents"),
    HDR_REVIEW_CONFIRM("Review & Confirm"),
    HDR_CROL_INFO("CROL Holder Information"),
    FINAL_REVIEW_MSG("Your Registration was successfully submitted and is being reviewed."),
    FILE_PATH_PDF("C:\\Users\\Admin\\IdeaProjects\\Automation-IronLogic\\testdata\\Credentials - Jagadeesh Bedge.pdf"),
    FILE_PATH_PNG("C:\\Users\\Admin\\IdeaProjects\\Automation-IronLogic\\testdata\\logo.png"),
    ;


    private String value;

    TextMessage(String value) {
        this.value=value;
    }

    @Override
    public String toString() {
        return value;
    }
}
