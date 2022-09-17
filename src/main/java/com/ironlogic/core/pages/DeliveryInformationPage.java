package com.ironlogic.core.pages;


import com.ironlogic.base.TestConfiguration;
import com.ironlogic.base.TestContext;
import com.ironlogic.base.UIAction;
import com.ironlogic.util.RandomUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.ironlogic.util.DynamicLocator.*;
import static com.ironlogic.util.TextMessage.*;


public class DeliveryInformationPage extends UIAction {

    private WebDriver driver;
    private TestConfiguration config;
    Logger log = LoggerFactory.getLogger(DeliveryInformationPage.class);

   private  By inpPrimaryDeliveryContactFirstName=By.xpath("//*[@id='PrimaryDeliveryContactFirstName']");
   private  By inpPrimaryDeliveryContactLastName=By.xpath("//*[@id='PrimaryDeliveryContactLastName']");
   private  By inpPrimaryDeliveryContactNumber=By.xpath("//*[@id='PrimaryDeliveryContactNumber']");
   private  By inpSecondaryDeliveryContactFirstName=By.xpath("//*[@id='SecondaryDeliveryContactFirstName']");
   private  By inpSecondaryDeliveryContactLastName=By.xpath("//*[@id='SecondaryDeliveryContactLastName']");
   private  By inpSecondaryDeliveryContactNumber=By.xpath("//*[@id='SecondaryDeliveryContactNumber']");
   private  By inpAdditionalShiipingInfo=By.xpath("//*[@id='AdditionalShiipingInfo']");


    public DeliveryInformationPage(TestContext testContext) {
        super(testContext);
        this.driver = driver;
        this.config = testContext.getTestConfiguration();
    }

    public void addDeliveryInformation(){
        setText(inpPrimaryDeliveryContactFirstName,RandomUtil.getRandomString(6));
        setText(inpPrimaryDeliveryContactLastName,RandomUtil.getRandomString(5));
        setText(inpPrimaryDeliveryContactNumber,RandomUtil.getRandomNumber(10));
        setText(inpSecondaryDeliveryContactFirstName,RandomUtil.getRandomString(6));
        setText(inpSecondaryDeliveryContactLastName,RandomUtil.getRandomString(5));
        setText(inpSecondaryDeliveryContactNumber,RandomUtil.getRandomNumber(10));
        setText(inpAdditionalShiipingInfo,RandomUtil.getRandomString(15));

    }

    public void addOperatingHours(String day,String workinghour){
        String[] workingHoours=workinghour.split("-");
        String workingFrom=workingHoours[0];
        String workingTill=workingHoours[1];
        By dayfrom=DAY_OPEN_FROM.setValue(day).getLocator();
        By dayclose=DAY_CLOSE_TO.setValue(day).getLocator();
        setText(dayfrom, Keys.ARROW_DOWN);
        selectDropDown(dayfrom,workingFrom);
        selectDropDown(dayclose,workingTill);
    }

    public void saveAndClickOnNext(){
        By btnSave=INPUT_BUTTON.setValue(BTN_SAVE).getLocator();
        By successMsg=LABEL_TEXT.setValue(SUCCESS_MESSAGE).getLocator();
        By btnNext=SPAN_TEXT.setValue(BTN_NEXT_STEP3).getLocator();
        By hdruploadDoc=H5_Header.setValue(HDR_UPLOAD_DOCUMENTS).getLocator();

        click(btnSave);
        verifyElementDisplayed(successMsg,"Verify success message displayed.");
        click(btnNext);
        verifyElementDisplayed(hdruploadDoc,"Verify success message displayed.");
    }




}