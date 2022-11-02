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

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.ironlogic.util.RandomUtil.*;
import static com.ironlogic.util.DynamicLocator.*;
import static com.ironlogic.util.TextMessage.*;


public class UserPage extends UIAction {

    private WebDriver driver;
    private TestConfiguration config;
    Logger logger = LoggerFactory.getLogger(UserPage.class);

    private By drpUserType=By.xpath("//select[@id=\"UserTypeId\"]");
    private  By inpFirstName=By.xpath("//*[@id='FirstName']");
    private  By inpLastName=By.xpath("//*[@id='LastName']");
//    private  By toggleButton=By.xpath("//tr[./td[contains(text(),'Monday')]]//span[text()='Yes' and not(contains(@class,'color-green'))]");
//    private  By inpStoreName=By.xpath("//tr[./td[contains(text(),'Monday')]]//input[normalize-space(@title)='Type Store Name/Store ID/Organization']");
    private  By inpPhoneNumber=By.xpath("//*[@id='PhoneNumber']");
    private  By inpEmail=By.xpath("//*[@id='Email']");
    private  By drpRole=By.xpath("//*[@id='UserRoleId']");
    private  By inpTitle=By.xpath("//*[@id='Title']");
    private  By chkLPPermissions=By.xpath("//input[@name='PermissionCategoryIds']/following-sibling::span");
    private  By inpVendor=By.xpath("//input[@type='search']");
    private  By inpStatus=By.xpath("//*[@id='StatusMessage']");
    private By alert=By.xpath("//div[text()='User Successfully Updated.']");
    private By alertClose=By.xpath("//*[@id='modalAlert' and contains(@style,'display: block;')]//button[@class='close']");

    public UserPage(TestContext testContext) {
        super(testContext);
        this.driver = driver;
        this.config = testContext.getTestConfiguration();
    }

    public void verifySuccessMessage(){
        verifyElementDisplayed(alert,"User created successfully.");
    }

    public void verifyUserHeaderDisplayed(String hdr){
        By userHdr=H2_Header.setValue(hdr).getLocator();
        verifyElementDisplayed(userHdr,"Verify user header displayed");
    }


    public void clickOnNewUser(){
        By btnNewUser=HYPERLINK_BUTTON.setValue(BTN_NEW_USER).getLocator();
        click(btnNewUser);
    }

    public void clickOnNewRole(){
        By btnNewUser=HYPERLINK_BUTTON.setValue(BTN_NEW_ROLE).getLocator();
        click(btnNewUser);
    }

    public void generateUserData(){
        config.setFirstName(RandomUtil.getRandomString(5));
        config.setLastName(RandomUtil.getRandomString(5));
        config.setContactNumber(RandomUtil.getRandomNumber(10));
        config.setPassword(RandomUtil.getRandomPassword());
    }

    public void addOCSUserDetails(String userType){
        generateUserData();
        if(userType.toLowerCase().equals("ocs")) {
            waitForVisibilityOfElement(drpUserType);
            selectDropDown(drpUserType, userType);
            waitForPageToLoad();
            setText(inpStatus, RandomUtil.getRandomString(10));
            selectDropDown(drpRole);
        }
        else {
            waitForVisibilityOfElement(drpUserType);
            selectDropDown(drpUserType, userType);
            waitForPageToLoad();
            setText(inpTitle,RandomUtil.getRandomString(10));
            selectVendorNumber();
            clickUsingAction(chkLPPermissions);
        }
        setText(inpFirstName, config.getFirstName());
        setText(inpLastName, config.getLastName());
        setText(inpPhoneNumber, config.getContactNumber());
        String tempEmail = RandomUtil.getRandomAlpahumeric(5);
        config.setEmail(tempEmail + "@mailinator.com");
        setText(inpEmail, config.getEmail());
    }

    public void selectVendorNumber(){
        By drpVendor=DIV_TEXT.setValue(LABEL_VENDOR_NUMBER).getLocator();
        click(drpVendor);
        By vendorList=DropDownVal.getLocator();
        List<WebElement> elements=findElements(vendorList);
        int size=elements.size();
        int act=RandomUtil.getRandomNumberFrom(size);
        config.setVendorName(elements.get(act).getText());
        setText(inpVendor,config.getVendorName());
        click(vendorList);
    }

    public void addLicenseProducerUser(String userType){
        waitForVisibilityOfElement(drpUserType);
        selectDropDown(drpUserType,userType);
        waitForPageToLoad();
        setText(inpFirstName, config.getFirstName());
        setText(inpLastName, config.getLastName());
        setText(inpPhoneNumber, config.getContactNumber());
        setText(inpEmail, config.getRetailEmail());
        String tempEmail= RandomUtil.getRandomAlpahumeric(5);
        config.setRetailEmail(tempEmail+"@mailinator.com");
        setText(inpEmail,config.getRetailEmail());
        setText(inpTitle,RandomUtil.getRandomString(10));
        selectDropDown(drpRole);
    }

    public void clickOnSubmit(){
        By btnSubmit=BUTTON.setValue(BTN_SUBMIT).getLocator();
        click(btnSubmit);
    }

    public void addOrderCongiguring(String[] retailerIDs){
        String day=getDayOfTheWeek();
        By btnToggle=toggleButton.setValue(day).getLocator();
        By btnToggle1=toggleButton1.setValue(day).getLocator();
        By inpStoreNameLoc=inpStoreName.setValue(day).getLocator();
        By inpStoreNameLoc1=inpStoreName1.setValue(day).getLocator();
        By scroll=SCROLL.setValue(day).getLocator();

        scrollElementJS(scroll);

        if(isElementPresence(btnToggle,12)&&!(getElement().isEnabled())){
            clickUsingAction(btnToggle);
        }
        if(isElementPresence(btnToggle1,12)&&!(getElement().isEnabled())){
            clickUsingAction(btnToggle1);
        }
        if(isElementVisible(inpStoreNameLoc,10)){
            for (String retailerID:retailerIDs){
                scrollToElement(inpStoreNameLoc);
                clearText(inpStoreNameLoc);
                setText(inpStoreNameLoc,retailerID);
                By drpValue=DRP_RETAILERS.setValue(retailerID).getLocator();
                retryClick(drpValue);
                waitFor(1);
                retryClick(alertClose);
                waitFor(1);
            }
        }
        if(isElementVisible(inpStoreNameLoc1,10)){
            for (String retailerID:retailerIDs){
                clearText(inpStoreNameLoc1);
                setText(inpStoreNameLoc1,retailerID);
                By drpValue=DRP_RETAILERS.setValue(retailerID).getLocator();
                retryClick(drpValue);
                waitFor(1);
                retryClick(alertClose);
                waitFor(1);
            }
        }
        By btnSave=BUTTON.setValue(BTN_SAVE).getLocator();
        click(btnSave);
        waitFor(1);
        clickUsingAction(alertClose);
    }

    public static void main(String[] args) {
        DateFormatSymbols dfs = new DateFormatSymbols(Locale.getDefault());
        String weekdays[] = dfs.getWeekdays();
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK);
        String nameOfDay = weekdays[day];
        System.out.println(nameOfDay);
    }

}