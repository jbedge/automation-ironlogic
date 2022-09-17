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


public class CROLInformationPage extends UIAction {

    private WebDriver driver;
    private TestConfiguration config;
    Logger log = LoggerFactory.getLogger(CROLInformationPage.class);

   private  By inpCROLCertificateName=By.xpath("//*[@id='CROLCertificateName']");
   private  By drpSelectType=By.xpath("//*[@id='EntityTypeId']");
   private  By inpRSANumber=By.xpath("//*[@id='RSANumber']");
   private  By inpFirstName=By.xpath("//*[@id='FirstName']");
   private  By inpLastName=By.xpath("//*[@id='LastName']");
   private  By inpPhoneNumber=By.xpath("//*[@id='PhoneNumber']");

   private  By inpStreet=By.xpath("//*[@id='LegalAddress_Street']");
   private  By inpStreet2=By.xpath("//*[@id='LegalAddress_Street2']");
   private  By inpCity=By.xpath("//*[@id='LegalAddress_City']");
   private  By inpPostalCode=By.xpath("//*[@id='LegalAddress_PostalCode']");
   private  By drpProvince=By.xpath("//*[@id='LegalAddress_ProvinceId']");
   private  By drpIsFirstNation=By.xpath("//*[@id='IsFirstNation']");

    private  By inpStoreAddress_Street=By.xpath("//*[@id='StoreAddress_Street']");
    private  By inpStoreName=By.xpath("//*[@id='StoreName']");
    private  By inpStoreAddress_Street2=By.xpath("//*[@id='StoreAddress_Street2']");
    private  By inpStoreAddress_City=By.xpath("//*[@id='StoreAddress_City']");
    private  By inpStoreAddress_PostalCode=By.xpath("//*[@id='StoreAddress_PostalCode']");


    public CROLInformationPage(TestContext testContext) {
        super(testContext);
        this.driver = driver;
        this.config = testContext.getTestConfiguration();
    }

    public void generateAccountDetails(){
        config.setCROLCertificateName(RandomUtil.getRandomString(6));
        config.setRsaNumber(RandomUtil.getRandomNumber(7));
        config.setFirstName(RandomUtil.getRandomString(6));
        config.setLastName(RandomUtil.getRandomString(6));
        config.setContactNumber(RandomUtil.getRandomNumber(10));
    }

    public void addCROLCreateAccountDetails(String entityType){
        generateAccountDetails();
        setText(inpCROLCertificateName, config.getCROLCertificateName());
        setText(inpRSANumber, config.getRsaNumber());
        selectDropDown(drpSelectType,entityType);
    }


    public void addCROLContactDetails() {
        setText(inpFirstName, config.getFirstName());
        setText(inpLastName, config.getLastName());
        setText(inpPhoneNumber, config.getContactNumber());
    }

    public void addCROLLegalAddressDetails() {
        setText(inpStreet, RandomUtil.getRandomNumber(2));
        setText(inpStreet2, RandomUtil.getRandomNumber(2));
        setText(inpCity, RandomUtil.getRandomString(6));
        selectDropDown(drpProvince);
        setText(inpPostalCode, RandomUtil.getRandomPostalCode(6));

    }

    public void addCROLStoreAddressDetails() {
        setText(inpStoreName, RandomUtil.getRandomString(5));
        setText(inpStoreAddress_Street, RandomUtil.getRandomNumber(2));
        setText(inpStoreAddress_Street2, RandomUtil.getRandomNumber(2));
        setText(inpStoreAddress_City, RandomUtil.getRandomString(6));
        setText(inpStoreAddress_PostalCode, RandomUtil.getRandomPostalCode(6));
    }

    public void addCROLFirstNationRetailerInformationAndSelectNext() {
        selectDropDown(drpIsFirstNation,"No");
        By btnNext=SPAN_TEXT.setValue(BTN_NEXT_STEP2).getLocator();
        click(btnNext);
        By hdrDeliveryInfo=H4_Header.setValue(HDR_DELIVERY_INFO).getLocator();
        verifyElementDisplayed(hdrDeliveryInfo,"Delivery info header displayed successfully.");
    }


}