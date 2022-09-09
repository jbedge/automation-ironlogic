package com.ironlogic.base;

import lombok.SneakyThrows;
import org.openqa.selenium.By;

public class UIElement {

    private By element;

    public UILocatorType getLocatorType() {
        return locatorType;
    }

    private UILocatorType locatorType;
    private String locator;
    private String name;

    @SneakyThrows
    public UIElement(String name,UILocatorType locatorType, String locator) {
        this.locatorType = locatorType;
        this.locator = locator;
        this.name=name;
        switch (locatorType){
            case id:
                element=By.id(locator);
                break;
            case xpath:
                element=By.xpath(locator);
                break;
            case css:
                element=By.cssSelector(locator);
                break;
            default:
                throw new Exception("Element type is incorrect");
        }
    }

    public String getValue() {
        return locator;
    }

    public String getName() {
        return name;
    }

    public By getElement() {
        return element;
    }

    public UIElement setLocator(String tempValue,String runTimeValue){
        this.locator=this.locator.replace(tempValue,runTimeValue);
        return this;
    }

    public UIElement setOriginalLocator(String originalLocator){
        this.locator=originalLocator;
        return this;
    }

}

