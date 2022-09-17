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
    HDR_DELIVERY_INFO("Delivery Information"),
    GO("GO"),
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
