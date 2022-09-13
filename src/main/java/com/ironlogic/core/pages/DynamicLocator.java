package com.ironlogic.core.pages;

import org.openqa.selenium.By;

public enum DynamicLocator {

    HamBurgerMenu("//div[contains(@class,'sidenav')]//a[normalize-space()='tempValue']"),
    HamBurgerSubMenu("//div[contains(@class,'sidenav')]//a[normalize-space()='tempValue']"),
    Nav_Header("//h5[normalize-space()='Retailer Management > Invite Retailers']"),
    DropDown("//label[normalize-space()='tempValue']//following-sibling::div//div[text()='Select']"),
    DropDownVal("//div[contains(@class,'options')]//div[@data-index][2]"),
    ;


    private String value;

    DynamicLocator(String value) {
        this.value=value;
    }

    public DynamicLocator setValue(String value) {
        this.value=this.value.replace("tempValue",value);
        return this;
    }

    public By getLocator(){
        By locator=By.xpath(value);
        return locator;
    }

    @Override
    public String toString() {
        return value;
    }
}
