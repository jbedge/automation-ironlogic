package com.ironlogic.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface Action {

     WebElement waitForVisibilityOfElement(By loc);
     WebElement waitForVisibilityOfElement(By loc,long timeout);
     WebElement waitForPresenceOfElement(By loc);
     WebElement waitForPresenceOfElement(By loc,long timeout);
     WebElement findElement(By loc);
     void click(By loc);
     void clickUsingJS(By loc);
     void clickUsingAction(By loc);
     void waitForPageLoad();
     void waitFor(double sec);



}
