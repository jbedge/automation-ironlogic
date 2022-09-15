package com.ironlogic.util;

import org.openqa.selenium.By;

public enum DynamicLocator {

    HamBurgerMenu("//div[contains(@class,'sidenav')]//a[normalize-space()='tempValue']"),
    HamBurgerSubMenu("//div[contains(@class,'sidenav')]//a[normalize-space()='tempValue']"),
    H5_Header("//h5[normalize-space()='tempValue']"),
    H6_Header("//h6[normalize-space()='tempValue']"),
    DropDown("//label[normalize-space()='tempValue']//following-sibling::div//div[text()='Select']"),
    DropDownVal("//div[contains(@class,'options')]//div[@data-index][2]"),
//    dont remove backslash in below xpath added intentionaly to overcome signle quote in string
    Email_Header("//td[normalize-space()=\"tempValue\"]"),
    BUTTON("//button[normalize-space()='tempValue']"),
    INPUT_BUTTON("//input[normalize-space(@value)='tempValue']"),
    SPAN_TEXT("//span[normalize-space()='tempValue']"),
    ;


    private String value;
    private String originValue;

    DynamicLocator(String value) {
        this.value=value;
    }

    public DynamicLocator setValue(String value) {
        originValue=this.value;
        this.value=this.value.replace("tempValue",value);
        return this;
    }

    public DynamicLocator setValue(TextMessage value) {
        originValue=this.value;
        this.value=this.value.replace("tempValue",value.toString());
        return this;
    }

    public By getLocator(){
        By locator=By.xpath(value);
        this.value=originValue;
        return locator;
    }

    public String getOrigin(){
        return originValue;
    }

    public void setDefault(){
        this.value=originValue;
    }

    @Override
    public String toString() {
        return value;
    }
}
