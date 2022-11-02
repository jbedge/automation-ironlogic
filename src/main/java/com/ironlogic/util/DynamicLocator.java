package com.ironlogic.util;

import org.openqa.selenium.By;

public enum DynamicLocator {

    HamBurgerMenu("//div[contains(@class,'sidenav')]//a[normalize-space()='tempValue']"),
    toggleButton("//tr[./td[contains(text(),'tempValue')]]//span[text()='Yes' and not(contains(@class,'color-green'))]"),
    toggleButton1("//tr[./td[contains(text(),'tempValue')]]/following-sibling::tr[@id][1]//span[text()='Yes' and not(contains(@class,'color-green'))]"),
    inpStoreName("//tr[./td[contains(text(),'tempValue')]]//input[normalize-space(@title)='Type Store Name/Store ID/Organization' and not(@disabled)]"),
    inpStoreName1("//tr[./td[contains(text(),'tempValue')]]/following-sibling::tr[@id][1]//input[normalize-space(@title)='Type Store Name/Store ID/Organization' and not(@disabled)]"),
    SCROLL("//tr[./td[contains(text(),'tempValue')]]/following-sibling::tr[@id][1]"),
    DRP_RETAILERS("//ul[not(contains(@style,'display: none;'))]//li[contains(@class,'ui-menu-item')]//div[contains(text(),'tempValue')]"),
    HamBurgerSubMenu("//div[contains(@class,'sidenav')]//a[normalize-space()='tempValue']"),
    OrderQuantity("//*[@id='divProdList']//div[@class='product-lists-pra' and .//p[contains(text(),'tempValue')]]//img[contains(@src,'plus')]"),
    AddToCart("//*[@id='divProdList']//div[@class='product-lists-pra' and .//p[contains(text(),'tempValue')]]//*[@id='btnAddToCart']"),

    H5_Header("//h5[contains(normalize-space(),'tempValue')]"),
    H1_Header("//h1[normalize-space()='tempValue']"),
    H2_Header("//h2[normalize-space(text())='tempValue']"),
    H4_Header("//h4[normalize-space()='tempValue']"),
    H6_Header("//h6[normalize-space()='tempValue']"),

    DIV_TEXT("//div[normalize-space(text())='tempValue']"),
    DropDownVal("//div[contains(@class,'fs-option') and not(contains(@class,'hidden')) and @data-index]"),
    OrganizationList("//div[contains(@class,'options')]//div[not(@data-index='0') and @data-index]"),
//    dont remove backslash in below xpath added intentionaly to overcome signle quote in string
    Email_Header("//td[normalize-space()=\"tempValue\"]"),
    GRID_DATA("//td[normalize-space()=\"tempValue\"]"),
    Assign_Role("//tr[./td[normalize-space()='tempValue']]//span[contains(@class,'checkbox')]"),
    BUTTON("//button[normalize-space()='tempValue']"),
    DropDown("//label[normalize-space()='tempValue']//following-sibling::div//div[text()='Select']"),
    INPUT_BUTTON("//input[normalize-space(@value)='tempValue']"),
    LABEL_TEXT("//*[@id='successTitle']/label[normalize-space()='tempValue']"),
    SPAN_TEXT("//span[normalize-space()='tempValue']"),
    PERMISSION_CATEGORYs("//div[@onclick and normalize-space()='tempValue']//preceding-sibling::label"),
    HYPERLINK_BUTTON("//a[normalize-space()='tempValue']"),
    DIV_CATEGORIES("//form//a[normalize-space()='tempValue']"),

    BTN_CONTINUE_OR_PLACE_ORDER("//a[normalize-space()='<tempValue0>' or normalize-space()='<tempValue1>']"),
    DAY_OPEN_FROM("//tr[.//label[text()='tempValue']]//*[@id='Opening']"),
    DAY_CLOSE_TO("//tr[.//label[text()='tempValue']]//*[@id='Closing']"),
    ONBOARDING_PROCESS("//li[./b[normalize-space()='<tempValue0>'] and ./span[normalize-space()='<tempValue1>'] and ./span[normalize-space()='<tempValue2>']]"),
    EDIT_ROLE("//tr[.//li[normalize-space()='tempValue']]//a[contains(@href,'EditRole')]"),
    DELETE_ROLE("//tr[.//li[normalize-space()='tempValue']]//a[contains(@onclick,'Delete')]"),
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

    public DynamicLocator setValues(String... values) {
        originValue=this.value;
        int index=0;
        for (String val:values){
            this.value=this.value.replace("<tempValue"+index+">",val);
            index++;
        }

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

    @Override
    public String toString() {
        return value;
    }
}
