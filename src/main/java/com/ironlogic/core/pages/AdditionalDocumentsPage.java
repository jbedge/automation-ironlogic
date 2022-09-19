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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ironlogic.util.DynamicLocator.*;
import static com.ironlogic.util.TextMessage.*;


public class AdditionalDocumentsPage extends UIAction {

    private WebDriver driver;
    private TestConfiguration config;
    Logger log = LoggerFactory.getLogger(AdditionalDocumentsPage.class);

   private  By inpPADForm=By.xpath("//input[@id='uploads_0__Document' and @type='file']");
   private  By inpInsuranceDocument=By.xpath("//input[@id='uploads_1__Document' and @type='file']");
   private  By inpRSACertificate=By.xpath("//input[@id='uploads_2__Document' and @type='file']");
   private  By inpCROLLicense=By.xpath("//input[@id='uploads_3__Document' and @type='file']");
   private  By inpVoidCheque=By.xpath("//input[@id='uploads_4__Document' and @type='file']");
   private  By inpStoreLogo=By.xpath("//input[@id='uploads_5__Document' and @type='file']");
   private By allText=By.xpath("//*[@id='task_tabsbar']//p[string-length(text())>1]");


    public AdditionalDocumentsPage(TestContext testContext) {
        super(testContext);
        this.driver = driver;
        this.config = testContext.getTestConfiguration();
    }

    public void uploadFiles(){
        String pdf=FILE_PATH_PDF.toString();
        String png=FILE_PATH_PNG.toString();
        waitForPresenceOfElement(inpPADForm).sendKeys(pdf);
        waitForPresenceOfElement(inpInsuranceDocument).sendKeys(pdf);
        waitForPresenceOfElement(inpRSACertificate).sendKeys(pdf);
        waitForPresenceOfElement(inpCROLLicense).sendKeys(pdf);
        waitForPresenceOfElement(inpVoidCheque).sendKeys(pdf);
        waitForPresenceOfElement(inpStoreLogo).sendKeys(png);

    }


    public void saveAndClickOnReviewAndConfirm(){
        By btnSave=BUTTON.setValue(BTN_SAVE).getLocator();
        By successMsg=LABEL_TEXT.setValue(SUCCESS_MESSAGE).getLocator();
        By review_confirm=SPAN_TEXT.setValue(BTN_REVIEW_CONFIRM).getLocator();
        waitFor(10);
        click(btnSave);
        waitFor(8);
        verifyElementDisplayed(successMsg,"Verify success message displayed.");
        click(review_confirm);
    }

    public void verifyCROLHolderheaderAndCLickonSubmit(){
        By submit= INPUT_BUTTON.setValue(BTN_SUBMIT_APPLICATION).getLocator();
        By hdrReviewConfirm=H1_Header.setValue(HDR_REVIEW_CONFIRM).getLocator();
        By hdrCROLInfo=H2_Header.setValue(HDR_CROL_INFO).getLocator();
        By finalReview=H5_Header.setValue(FINAL_REVIEW_MSG).getLocator();

        verifyElementDisplayed(hdrReviewConfirm,"Verify Review Confirm header displayed.");
        verifyElementDisplayed(hdrCROLInfo,"Verify CROL Info header displayed.");
        click(submit);
        verifyElementDisplayed(finalReview,"Verify final review message displayed.");
        List<String> st = new ArrayList<>();
        for (WebElement i : findElements(allText)) {
            String text = i.getText();
            st.add(text);
        }
    }


    public void verifyOnBoardingProcessDisplayed(String step, String process) {
        String[] stat= process.split(",");
        By processdetails=ONBOARDING_PROCESS.setValues(step,stat[0].trim(),stat[1].trim()).getLocator();
        verifyElementDisplayed(processdetails,"verify onboaring process displayed :"+step+" "+process);
    }
}