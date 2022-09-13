package com.ironlogic.core.pages;

import com.ironlogic.base.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//to use variables without class name
import static com.ironlogic.base.UILocatorType.*;

public class LoginPage extends UIAction {

    private WebDriver driver;
    private TestConfiguration testConfig;
    Logger logger = LoggerFactory.getLogger(TestConfiguration.class);

    public LoginPage(TestContext context) {
        super(context);
        this.driver = context.getDriver();
        this.testConfig = context.getTestConfiguration();
    }

    private By inpEmail=By.xpath("//input[@id='Email']");
    private By inpPassword=By.xpath("//input[@id='password']");
    private By btnLogin=By.xpath("//button[text()='LOGIN']");
    private By hdrLogo=By.xpath("//a[./img[contains(@alt,'Logo')]]");


    public void getURL()  {
        logger.info("Started:getURL");
        driver.manage().window().maximize();
        driver.get(testConfig.getUrl());
        waitForPageLoad();
        logger.info("Completed:getURL");
    }

    public void enterCredentials(){
        logger.info("started : enterCredentials");
        waitForVisibilityOfElement(inpEmail);
        setText(inpEmail,testConfig.getAdminUser());
        setText(inpPassword,testConfig.getAdminPwd());
        logger.info("completed : enterCredentials");
    }

    public void clickOnLogin(){
        logger.info("started : clickOnLogin");
        click(btnLogin);
        logger.info("completed : clickOnLogin");
    }

    public void waitForHeaderToLoad(){
        logger.info("started : waitForHeaderToLoad");
        verifyElementDisplayed(hdrLogo,"verify the header displayed");
        logger.info("completed : waitForHeaderToLoad");
    }


}
