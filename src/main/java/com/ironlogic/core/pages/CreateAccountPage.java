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


public class CreateAccountPage extends UIAction {

    private WebDriver driver;
    private TestConfiguration config;
    Logger log = LoggerFactory.getLogger(CreateAccountPage.class);

   private  By inpFirstName=By.xpath("//*[@id='FirstName']");
   private  By inpLastName=By.xpath("//*[@id='LastName']");
   private  By inpContactNumber=By.xpath("//*[@id='ContactNumber']");
   private  By inputPassword=By.xpath("//*[@id='inputPassword']");
   private  By inputConfirmPassword=By.xpath("//*[@id='inputConfirmPassword']");
   private  By chkConfirm=By.xpath("//*[@id='accept']");


    public CreateAccountPage(TestContext testContext) {
        super(testContext);
        this.driver = driver;
        this.config = testContext.getTestConfiguration();
    }

    public void generateAccountDetails(){
        config.setFirstName(RandomUtil.getRandomString(5));
        config.setLastName(RandomUtil.getRandomString(5));
        config.setContactNumber(RandomUtil.getRandomNumber(10));
        config.setPassword(RandomUtil.getRandomPassword());
    }

    public void addCreateAccountDetails(){
        generateAccountDetails();
        setText(inpFirstName, config.getFirstName());
        setText(inpLastName, config.getLastName());
        setText(inpContactNumber, config.getContactNumber());
        setText(inputPassword, config.getPassword());
        setText(inputConfirmPassword, config.getPassword());
    }

    public void clickOnConfirmPassword(){
        By btnConfirmPassword=BUTTON.setValue(BTN_CONFIRM_PASSWORD).getLocator();
        click(btnConfirmPassword);
        waitForPageToLoad();
        By userDetails=SPAN_TEXT.setValue(config.getFirstName()+" "+config.getLastName()).getLocator();
        verifyElementDisplayed(userDetails,"User login name verified successfully");
    }

    public void selectTermsAndConditionAndClickNextStep1(){
        selectCheckBox(chkConfirm);
        By btnNextStep1= INPUT_BUTTON.setValue(BTN_NEXT_STEP1).getLocator();
        click(btnNextStep1);
    }






}