package com.ironlogic.core.pages;


import com.ironlogic.base.TestConfiguration;
import com.ironlogic.base.TestContext;
import com.ironlogic.base.UIAction;
import com.ironlogic.util.TextMessage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.ironlogic.util.DynamicLocator.*;
import static com.ironlogic.util.TextMessage.*;


public class MailinatorPage extends UIAction {

    private WebDriver driver;
    private TestConfiguration config;
    Logger log = LoggerFactory.getLogger(MailinatorPage.class);

   private  By inpSearch=By.xpath("//*[@id='search']");
   private  By btnStartRegistration=By.xpath("//a[text()='START REGISTRATION']");
   private  By iframeBody=By.xpath("//*[@id='html_msg_body']");


    public MailinatorPage(TestContext testContext) {
        super(testContext);
        this.driver = driver;
        this.config = testContext.getTestConfiguration();
    }


    public void searchEmail() {
        setText(inpSearch,config.getRetailEmail());
        By btnGo=BUTTON.setValue(GO.toString()).getLocator();
        click(btnGo);
    }

    public void verifyNewEmailRecievedInTheInbox() {
        By email=Email_Header.setValue(EMAIL_SUBJECT.toString()).getLocator();
        verifyElementDisplayed(email,"verify the text displayed"+EMAIL_SUBJECT);
    }

    public void clickOnNewEmailReceived() {
        By email=Email_Header.setValue(EMAIL_SUBJECT.toString()).getLocator();
        click(email);
    }

    public void clickOnRegistrationLinkReceivedFromTheEmail() {
        switchToIframe(iframeBody);
        clickUsingAction(btnStartRegistration);
//        clickUsingJS(btnStartRegistration);
    }




}