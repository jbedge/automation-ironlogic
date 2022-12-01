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

import java.util.ArrayList;
import java.util.List;

import static com.ironlogic.util.DynamicLocator.*;
import static com.ironlogic.util.TextMessage.*;


public class RetailerGroupsPage extends UIAction {

    private WebDriver driver;
    private TestConfiguration config;
    Logger log = LoggerFactory.getLogger(RetailerGroupsPage.class);

   private  By btnCreateRetilerGroup=By.xpath("//a[normalize-space()='+ CREATE RETAILER GROUP']");
   private  By hdrCreateRetailer=By.xpath("//h4[normalize-space()='CREATE RETAILER GROUP']");
   private  By inpEnterGroupName=By.xpath("//input[@placeholder='Enter Group Name']");
   private  By inpDescription=By.xpath("//textarea[@placeholder='Enter Description']");
   private  By drpColor=By.xpath("//*[@id='btnColorPicker']");
   private  By btnCreateRetailerGroup=By.xpath("//button[normalize-space()='CREATE RETAILER GROUP']");
   private  By alertSuccessMsg=By.xpath("//div[normalize-space()='Retailer Group successfully created.']");
//   private  By drpColor=By.xpath("//a[normalize-space()='LEMON']");


    public RetailerGroupsPage(TestContext testContext) {
        super(testContext);
        this.driver = driver;
        this.config = testContext.getTestConfiguration();
    }

    public void createRetailerGroup(){
        click(btnCreateRetilerGroup);
        verifyElementDisplayed(hdrCreateRetailer,"Verify Create Retailer header displayed");
        setText(inpEnterGroupName, RandomUtil.getRandomString(8));
        setText(inpDescription, RandomUtil.getRandomString(10));
        scrollToElement(drpColor);
        clickUsingJS(drpColor);
        int index=RandomUtil.getRandomNumberFrom(12)+1;
        By drpColor=By.xpath("//*[@id='divColorDrop']/a["+index+"]");
        clickUsingJS(drpColor);
        click(btnCreateRetailerGroup);
        verifyElementDisplayed(alertSuccessMsg,"Sucess alert message verified successfully.");
        waitFor(10);
    }


}