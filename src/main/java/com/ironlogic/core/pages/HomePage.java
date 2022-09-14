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
import static com.ironlogic.data.RunTimeData.*;
import static com.ironlogic.util.TextMessage.*;


public class HomePage extends UIAction {

    private WebDriver driver;
    private TestConfiguration config;
    Logger logger = LoggerFactory.getLogger(TestConfiguration.class);

    private By inpCROLNumber=By.xpath("//*[@id='CROLNumber']");
    private By inpEmail=By.xpath("//*[@id='Email']");
    private By btnSubmit=By.xpath("//button[text()='Submit']");
    private By successMsg=By.xpath("//h4[text()='Invitation Successfully Sent.']");
    private By inviteUrl=By.xpath("//*[@id='modalSuccess']//a");


    public HomePage(TestContext testContext) {
        super(testContext);
        this.driver = driver;
        this.config = testContext.getTestConfiguration();
    }

    public void navigateToHamburgerMenu(String menu,String submenu){
        By loc=HamBurgerMenu.setValue(menu).getLocator();
        By subloc=HamBurgerSubMenu.setValue(submenu).getLocator();
        click(loc);
        waitFor(0.2);
        click(subloc);
        verifyElementDisplayed(H5_Header.setValue(NAV_HEADER_INVITE_RETAILER.toString()).getLocator(),"verify the navigation header displayed");
    }


    public void selectOrganizationDropDown() {
        By loc=DropDown.setValue(Drop_Down_Value.getValue()).getLocator();
        click(loc);
        click(DropDownVal.getLocator());
    }

    public void setCROLNumber(int size){
        String crolNum=RandomUtil.getRandomNumber(size);
        setText(inpCROLNumber,crolNum);
    }

    public void setEmail(){
        String tempEmail=RandomUtil.getRandomAlpahumeric(5);
        config.setEmail(tempEmail+"@mailinator.com");
        setText(inpEmail,config.getEmail());
    }

    public void clickOnSubmit(){
        click(btnSubmit);
    }

    public void verifySuccessMessage(){
        verifyElementDisplayed(successMsg,"verify the success alert displayed");
        waitForVisibilityOfElement(inviteUrl).getAttribute("href");
        click(inviteUrl);
        verifyNewTabDisplayedWithCreateAccountForm();
    }
}