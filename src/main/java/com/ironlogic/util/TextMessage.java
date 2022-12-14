package com.ironlogic.util;

import org.openqa.selenium.By;

public enum TextMessage {

    EMAIL_SUBJECT("You are Invited to Register on OCS's B2B Portal"),
    PERMISSION_CATEGORY("Onboarding,User Management,Product Managment,Communication Management,Retailer Management,Order Management,System Administration"),
    EMAIL_SUBJECT_NEW_USER("Your Account has Been Created"),
    MENU_ORDER_HISTORY("Order History"),
    EMAIL_SUBJECT_BODY("Your Account has been Created"),
    CREATE_ACCOUNT_TITLE("Create a Password - OCS Wholesale"),
    HEADER_CREATE_ACCT("Create Account"),
    LABEL_VENDOR_NUMBER("Select Vendor Numbers"),
    HDR_SIGNIN_TO_YOUR_ACCOUNT("Sign in to your account"),
    HDR_EDIT_ROLE("EDIT ROLE"),
    NAV_HEADER_INVITE_RETAILER("Retailer Management > Invite Retailers"),
    BTN_CONFIRM_PASSWORD("Confirm Password"),
    BTN_NEXT_STEP1("NEXT STEP 1: CROL HOLDER INFORMATION"),
    BTN_NEW_USER("New User"),
    BTN_UPDATE_ROLE("UPDATE ROLE"),
    COLOR("LIME"),
    BTN_NEW_ROLE("New Role"),
    GENERAL("General"),
    PERMISSION("Permission"),
    BTN_CLOSE("CLOSE"),
    BTN_NEXT_STEP2("Next: Step 2: Delivery Information"),
    BTN_LOGIN("LOGIN"),
    BTN_CREATE_PWD("CREATE PASSWORD"),
    BTN_NEXT_STEP3("Next: Step 3: Additional Documents"),
    BTN_REVIEW_CONFIRM("Review & Confirm"),
    BTN_PLACE_AN_ORDER("PLACE AN ORDER"),
    BTN_CONTINUE_ORDER("CONTINUE ORDER"),
    BTN_VIEW_ALL_PRODUCTS("View All Products"),
    BTN_CLEAR_FILTERS("CLEAR FILTERS"),
    BTN_FLOW_THROUGH("Flow-Through"),
    BTN_SUBMIT_APPLICATION("SUBMIT APPLICATION"),
    BTN_SUBMIT("Submit"),
    HDR_DELIVERY_INFO("Delivery Information"),
    GO("GO"),
    BTN_SAVE("Save"),
    SUCCESS_MESSAGE("Your information has been successfully saved"),
    MSG_PRODUCT_ADDED("Product Added To Your Order"),
    MSG_SUBMIT_ORDER("Your Order Has Been Placed!"),
    MSG_ORDER_SUBMITTED("ORDER SUBMITTED"),
    BTN_VIEW_MYORDER("VIEW MY ORDER"),
    BTN_CLEAR_ORDER("CLEAR ORDER"),
    BTN_CREATE_ROLE("CREATE ROLE"),
    ALERT_MSG("Role Saved Successfully."),
    ALERT_DELETE_MSG("Role deleted successfully."),
    DIV_USERS("Users"),
    HDR_UPLOAD_DOCUMENTS("Upload Documents"),
    HDR_ORDER_DETAILS("ORDER DETAILS"),
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
