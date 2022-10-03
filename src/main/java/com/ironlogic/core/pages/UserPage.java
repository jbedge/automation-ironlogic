package com.ironlogic.core.pages;


import com.ironlogic.base.TestConfiguration;
import com.ironlogic.base.TestContext;
import com.ironlogic.base.UIAction;
import com.ironlogic.util.RandomUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.ironlogic.util.DynamicLocator.*;
import static com.ironlogic.util.TextMessage.*;


public class UserPage extends UIAction {

    private WebDriver driver;
    private TestConfiguration config;
    Logger logger = LoggerFactory.getLogger(UserPage.class);

    private By drpUserType=By.xpath("//*[@id=\"UserTypeId\"]");
    private  By inpFirstName=By.xpath("//*[@id='FirstName']");
    private  By inpLastName=By.xpath("//*[@id='LastName']");
    private  By inpPhoneNumber=By.xpath("//*[@id='PhoneNumber']");
    private  By inpEmail=By.xpath("//*[@id='Email']");
    private  By drpRole=By.xpath("//*[@id='UserRoleId']");
    private  By inpTitle=By.xpath("//*[@id='Title']");
    private  By inpStatus=By.xpath("//*[@id='StatusMessage']");


    public UserPage(TestContext testContext) {
        super(testContext);
        this.driver = driver;
        this.config = testContext.getTestConfiguration();
    }

    public void verifyUserHeaderDisplayed(String hdr){
        By userHdr=H2_Header.setValue(hdr).getLocator();
        verifyElementDisplayed(userHdr,"Verify user header displayed");
    }


    public void clickOnNewUser(){
        By btnNewUser=HYPERLINK_BUTTON.setValue(BTN_NEW_USER).getLocator();
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
        waitForVisibilityOfElement(drpUserType);
        selectDropDown(drpUserType,userType);
        waitForPageToLoad();
        setText(inpFirstName, config.getFirstName());
        setText(inpLastName, config.getLastName());
        setText(inpPhoneNumber, config.getContactNumber());
        String tempEmail= RandomUtil.getRandomAlpahumeric(5);
        config.setRetailEmail(tempEmail+"@mailinator.com");
        setText(inpEmail,config.getRetailEmail());
        setText(inpStatus,RandomUtil.getRandomString(10));
        selectDropDown(drpRole);
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

}