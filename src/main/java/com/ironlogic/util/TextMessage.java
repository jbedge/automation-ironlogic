package com.ironlogic.util;

import org.openqa.selenium.By;

public enum TextMessage {

    EMAIL_SUBJECT("You are Invited to Register on OCS's B2B Portal"),
    CREATE_ACCOUNT_TITLE("Create a Password - OCS Wholesale"),
    HEADER_CREATE_ACCT("Create Account"),
    NAV_HEADER_INVITE_RETAILER("Retailer Management > Invite Retailers"),
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
