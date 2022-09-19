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

    private By inpCROLCertificateName = By.xpath("//*[@id='CROLCertificateName']");
    private By drpSelectType = By.xpath("//*[@id='EntityTypeId']");
    private By inpRSANumber = By.xpath("//*[@id='RSANumber']");
    private By inpFirstName = By.xpath("//*[@id='FirstName']");
    private By inpLastName = By.xpath("//*[@id='LastName']");
    private By inpPhoneNumber = By.xpath("//*[@id='PhoneNumber']");

    private By inpStreet = By.xpath("//*[@id='LegalAddress_Street']");
    private By inpStreet2 = By.xpath("//*[@id='LegalAddress_Street2']");
    private By inpCity = By.xpath("//*[@id='LegalAddress_City']");
    private By inpPostalCode = By.xpath("//*[@id='LegalAddress_PostalCode']");
    private By drpProvince = By.xpath("//*[@id='LegalAddress_ProvinceId']");
    private By drpIsFirstNation = By.xpath("//*[@id='IsFirstNation']");

    private By inpStoreAddress_Street = By.xpath("//*[@id='StoreAddress_Street']");
    private By inpStoreName = By.xpath("//*[@id='StoreName']");
    private By inpStoreAddress_Street2 = By.xpath("//*[@id='StoreAddress_Street2']");
    private By inpStoreAddress_City = By.xpath("//*[@id='StoreAddress_City']");
    private By inpStoreAddress_PostalCode = By.xpath("//*[@id='StoreAddress_PostalCode']");


    public CROLInformationPage(TestContext testContext) {
        super(testContext);
        this.driver = driver;
        this.config = testContext.getTestConfiguration();
    }

    public void generateAccountDetails() {
        config.setCROLFirstName(RandomUtil.getRandomString(6));
        config.setCROLLastName(RandomUtil.getRandomString(6));
        config.setCROLContactNumber(RandomUtil.getRandomNumber(10));
        config.setCROLCertificateName(RandomUtil.getRandomString(6));
        config.setRsaNumber(RandomUtil.getRandomNumber(7));

    }

    public void addCROLCreateAccountDetails(String entityType) {
        generateAccountDetails();
        setText(inpCROLCertificateName, config.getCROLCertificateName());
        setText(inpRSANumber, config.getRsaNumber());
        selectDropDown(drpSelectType, entityType);
    }


    public void addCROLContactDetails() {
        setText(inpFirstName, config.getCROLFirstName());
        setText(inpLastName, config.getCROLLastName());
        setText(inpPhoneNumber, config.getCROLContactNumber());
    }

    public void addCROLLegalAddressDetails() {
        config.setStreet(RandomUtil.getRandomNumber(2));
        config.setStreet2(RandomUtil.getRandomNumber(2));
        config.setCity(RandomUtil.getRandomString(6));
        config.setPostalCode(RandomUtil.getRandomPostalCode(6));

        setText(inpStreet, config.getStreet());
        setText(inpStreet2, config.getStreet2());
        setText(inpCity, config.getCity());
        selectDropDown(drpProvince);
        setText(inpPostalCode, config.getPostalCode());
    }

    public void addCROLStoreAddressDetails() {
        config.setStoreName(RandomUtil.getRandomString(5));
        config.setStore_street(RandomUtil.getRandomNumber(2));
        config.setStore_street2(RandomUtil.getRandomNumber(2));
        config.setCity(RandomUtil.getRandomString(6));
        config.setStore_postalCode(RandomUtil.getRandomPostalCode(6));

        setText(inpStoreName, config.getStoreName());
        setText(inpStoreAddress_Street, config.getStore_street());
        setText(inpStoreAddress_Street2, config.getStore_street2());
        setText(inpStoreAddress_City, config.getCity());
        setText(inpStoreAddress_PostalCode, config.getPostalCode());
    }

    public void addCROLFirstNationRetailerInformationAndSelectNext() {


        By btnSave = INPUT_BUTTON.setValue(BTN_SAVE).getLocator();
        By successMsg = LABEL_TEXT.setValue(SUCCESS_MESSAGE).getLocator();
        By btnNext = SPAN_TEXT.setValue(BTN_NEXT_STEP2).getLocator();
        By hdrDeliveryInfo = H4_Header.setValue(HDR_DELIVERY_INFO).getLocator();

        selectDropDown(drpIsFirstNation, "No");
        click(btnSave);
        verifyElementDisplayed(successMsg, "Verify success message displayed.");
        click(btnNext);
        verifyElementDisplayed(hdrDeliveryInfo, "Delivery info header displayed successfully.");
    }


}