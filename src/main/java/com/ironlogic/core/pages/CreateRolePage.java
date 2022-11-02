package com.ironlogic.core.pages;


import com.ironlogic.base.TestConfiguration;
import com.ironlogic.base.TestContext;
import com.ironlogic.base.UIAction;
import com.ironlogic.util.RandomUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.ironlogic.util.DynamicLocator.*;
import static com.ironlogic.util.RandomUtil.getDayOfTheWeek;
import static com.ironlogic.util.TextMessage.*;


public class CreateRolePage extends UIAction {

    private WebDriver driver;
    private TestConfiguration config;
    Logger logger = LoggerFactory.getLogger(CreateRolePage.class);

    private By inpRoleName=By.xpath("//*[@id=\"Name\"]");
    private  By drpColor=By.xpath("//*[@id=\"btnColorPicker\"]");
    private  By inpAdminNotes=By.xpath("//*[@id=\"Description\"]");
    private  By userEmail=By.xpath("//*[@id='UserListRow']/div[1]//tbody/tr[1]/td[last()]");
    private  By inpNameOrEmail=By.xpath("//*[@id='UserRoleUsers_SearchWord']");
    private  By inpSearchByRule=By.xpath("//*[@id='SearchWord']");
    private  By msgConfirmPopUp=By.xpath("//*[@id=\"lblMsgConfirm\"]");
    private  By btnYes=By.xpath("//*[@id=\"btnOkConfirm\"]");

    public CreateRolePage(TestContext testContext) {
        super(testContext);
        this.driver = driver;
        this.config = testContext.getTestConfiguration();
    }

   public void addGeneralInformation(){
       By btnNewUser=HYPERLINK_BUTTON.setValue(GENERAL).getLocator();
       click(btnNewUser);
       this.config.setRoleName(RandomUtil.getRandomString(6));
       setText(inpRoleName,this.config.getRoleName());
       By color=HYPERLINK_BUTTON.setValue(COLOR).getLocator();
       click(drpColor);
       click(color);
       setText(inpAdminNotes,RandomUtil.getRandomString(10));
       By btnPermission=HYPERLINK_BUTTON.setValue(PERMISSION).getLocator();
       click(btnPermission);
       String[] categories=PERMISSION_CATEGORY.toString().split(",");
       for (String category:categories){
           By catgory=PERMISSION_CATEGORYs.setValue(category).getLocator();
           setText(catgory, Keys.ARROW_DOWN);
           click(catgory);
           waitFor(0.2);
       }

       By divUsers=DIV_CATEGORIES.setValue(DIV_USERS).getLocator();
       setText(divUsers,Keys.HOME);
       waitFor(0.1);
       click(divUsers);
       String email=waitForVisibilityOfElement(userEmail).getText();
       config.setEmail(email);
       setText(inpNameOrEmail,config.getEmail());
       waitFor(1);
       By user=Assign_Role.setValue(config.getEmail()).getLocator();
       click(user);
       By btnCreateRole=BUTTON.setValue(BTN_CREATE_ROLE).getLocator();
       click(btnCreateRole);
       By alert=DIV_TEXT.setValue(ALERT_MSG).getLocator();
       verifyElementDisplayed(alert,"Verify alert displayed for rule creation");
   }

   public void updateRole(){
       setText(inpSearchByRule,config.getRoleName());
       By editIcon=EDIT_ROLE.setValue(config.getRoleName()).getLocator();
       click(editIcon);
       By hdrEditRole=H4_Header.setValue(HDR_EDIT_ROLE).getLocator();
       verifyElementDisplayed(hdrEditRole,"Verify edit header displayed.");
       setText(inpAdminNotes,RandomUtil.getRandomString(10));
       click(BUTTON.setValue(BTN_UPDATE_ROLE).getLocator());
       By alert=DIV_TEXT.setValue(ALERT_MSG).getLocator();
       verifyElementDisplayed(alert,"Verify alert displayed for rule creation");
   }

    public void deleteRole(){
        clearText(inpSearchByRule);
        setText(inpSearchByRule,config.getRoleName());
        waitFor(1);
        By deleteIcon=DELETE_ROLE.setValue(config.getRoleName()).getLocator();
        click(deleteIcon);
        verifyElementDisplayed(msgConfirmPopUp,"Verify edit header displayed.");
        click(btnYes);
        By alert=DIV_TEXT.setValue(ALERT_DELETE_MSG).getLocator();
        verifyElementDisplayed(alert,"Verify alert displayed for rule creation");
    }

}