package com.ironlogic.core.pages;

import com.ironlogic.base.*;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//to use variables without class name
import static com.ironlogic.base.UILocatorType.*;

public class LoginPage extends UIAction {

    private WebDriver driver;
    private TestConfiguration config;
    Logger logger = LoggerFactory.getLogger(TestConfiguration.class);

    public LoginPage(TestContext context) {
        super(context);
        this.driver = context.getDriver();
        this.config = context.getTestConfiguration();
    }

    private By inpEmail=By.xpath("//input[@id='Email']");
    private By inpPassword=By.xpath("//input[@id='password']");
    private By btnLogin=By.xpath("//button[text()='LOGIN']");
    private By hdrLogo=By.xpath("//a[./img[contains(@alt,'Logo')]]");


    public void getURL()  {
        logger.info("Started:getURL");
        getURL(config.getUrl());
        logger.info("Completed:getURL");
    }

    public void getURL(String url)  {
        logger.info("Started:getURL");
        super.getURL(url);
        logger.info("Completed:getURL");
    }

    public void enterCredentials(){
        logger.info("started : enterCredentials");
        waitForVisibilityOfElement(inpEmail);
        setText(inpEmail,config.getAdminUser());
        setText(inpPassword,config.getAdminPwd());
        logger.info("completed : enterCredentials");
    }

    public void enterRandomGeneratedCredentials(){
        logger.info("started : enterCredentials");
        waitForVisibilityOfElement(inpEmail);
        setText(inpEmail,config.getEmail());
        setText(inpPassword,config.getPassword());
        logger.info("completed : enterCredentials");
    }

    public void enterRetailCredentials(DataTable dt){
        logger.info("started : enterCredentials");
        String retailrUser=config.getOrder_data().getUserid();
        String retailpwd=config.getOrder_data().getPassword();
        waitForVisibilityOfElement(inpEmail);
        setText(inpEmail,retailrUser);
        setText(inpPassword,retailpwd);
        logger.info("completed : enterCredentials");
    }


    public void enterRetailCredentials(){
        logger.info("started : enterCredentials");
        config.getOrder_data().getUserid();
        config.getOrder_data().getUserid();
        String user[]=config.getRetailUser1().split("\\|");
        waitForVisibilityOfElement(inpEmail);
        setText(inpEmail,user[0]);
        setText(inpPassword,user[1]);
        logger.info("completed : enterCredentials");
    }

    public void clickOnLogin(){
        logger.info("started : clickOnLogin");
        click(btnLogin);
        waitForPageToLoad();
        logger.info("completed : clickOnLogin");
    }

    public void waitForHeaderToLoad(){
        logger.info("started : waitForHeaderToLoad");
        verifyElementDisplayed(hdrLogo,"verify the header displayed");
        logger.info("completed : waitForHeaderToLoad");
    }


}
