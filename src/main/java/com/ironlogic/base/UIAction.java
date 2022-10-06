package com.ironlogic.base;


import com.ironlogic.data.Constants;
import lombok.SneakyThrows;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static com.ironlogic.util.DynamicLocator.*;
import static com.ironlogic.util.TextMessage.*;


public class UIAction implements Action {

    Logger log = LoggerFactory.getLogger(UIAction.class);
    public WebDriver driver;
    private final TestConfiguration testConfiguration;
    private WebDriverWait wait;
    private WebElement webElement;

    public synchronized JavascriptExecutor getJs() {
        return js;
    }

    public void setJs(WebDriver webDriver) {
        this.js =  (JavascriptExecutor) webDriver;;
    }

    private JavascriptExecutor js;


    public WebElement getElement() {
        return webElement;
    }

    public void setElement(WebElement element) {
        this.webElement = element;
    }


    public UIAction(TestContext context) {
        this.driver = context.getDriver();
        this.setJs(driver);
        this.testConfiguration = context.getTestConfiguration();
        wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.PAGE_TIME), Duration.ofSeconds(Constants.POLL_TIME));
    }

    public void highlight(WebElement element) {
        getJs().executeScript("arguments[0].style.border='solid 2px orange'", element);
    }

    public void getURL(String url){
        try {
            driver.manage().window().maximize();
            driver.get(url);
            this.waitForPageToLoad();
        }
        catch (TimeoutException e){
            driver.get(url);
            waitForPageToLoad();
        }

    }


    public void setText(By locator, CharSequence... value) {
        waitForVisibilityOfElement(locator).sendKeys(value);
    }

    public void clearText(By locator) {
        WebElement element=waitForVisibilityOfElement(locator);
        element.clear();
        element.sendKeys(Keys.CONTROL+"a"+Keys.BACK_SPACE);
    }

    public void selectDropDown(By locator, String  visibleText) {
        WebElement element=waitForVisibilityOfElement(locator);
        Select select=new Select(element);
        select.selectByVisibleText(visibleText);
    }

    public void selectDropDown(By locator) {
        Random random=new Random();
        WebElement element=waitForVisibilityOfElement(locator);
        Select select=new Select(element);
        int size=select.getOptions().size();
        int index=random.nextInt(size);
        select.selectByIndex(index);
    }

    public void setTextUsingJS(By locator, CharSequence... value) {
        getJs().executeScript("arguments[0].value='"+value+"';", waitForPresenceOfElement(locator));
    }

    public void setText(By locator, Keys value) {
        waitForVisibilityOfElement(locator).sendKeys(value);
    }
    static int cnt=0;
    @Override
    public WebElement waitForVisibilityOfElement(By loc) {
        WebElement element;
        try {
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
            highlight(element);
            return element;
        } catch (TimeoutException e) {
//            try {
//                    if(selectPopUp()) {
//                        element = wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
//                        highlight(element);
//                        return element;
//                    }
//                }
//            catch (Exception ex){
//            }
            element = findElement(loc);
            return element;
        } catch (Exception e) {
            testConfiguration.setLocator(loc.toString());
            commonExceptions(e);
            return null;
        }
    }


    @Override
    public WebElement waitForVisibilityOfElement(By loc, long timeoutInSec) {
        WebElement element;
        try {
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
            highlight(element);
            return element;
        } catch (TimeoutException e) {
            element = findElement(loc);
            return element;
        } catch (Exception e) {
            testConfiguration.setLocator(loc.toString());
            commonExceptions(e);
            return null;
        }
    }

    @Override
    public WebElement waitForPresenceOfElement(By loc) {
        WebElement element;
        try {
            element = wait.until(ExpectedConditions.presenceOfElementLocated(loc));
            highlight(element);
            return element;
        } catch (TimeoutException e) {
            element = findElement(loc);
            return element;
        } catch (Exception e) {
            testConfiguration.setLocator(loc.toString());
            commonExceptions(e);
            return null;
        }
    }

    @Override
    public WebElement waitForPresenceOfElement(By loc, long timeoutInSec) {
        WebElement element;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSec), Duration.ofSeconds(Constants.POLL_TIME));
            element = wait.until(ExpectedConditions.presenceOfElementLocated(loc));
            highlight(element);
            return element;
        } catch (TimeoutException e) {
            element = findElement(loc);
            return element;
        } catch (Exception e) {
            testConfiguration.setLocator(loc.toString());
            commonExceptions(e);
            return null;
        }
    }

    public boolean isElementVisible(By loc, long timeoutInSec) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSec), Duration.ofSeconds(Constants.POLL_TIME));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
            setElement(element);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    WebElement popup;
    public boolean isElementPresenct1(By loc, long timeoutInSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSec), Duration.ofSeconds(Constants.POLL_TIME));
        try {
            popup = wait.until(ExpectedConditions.presenceOfElementLocated(loc));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public WebElement findElement(By loc) {
        try {
            testConfiguration.setLocator(loc.toString());
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
            return driver.findElement(loc);
        } catch (Exception e) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT));
            testConfiguration.getScenario().log("Locator value:" + loc);
            commonExceptions(e);
            throw e;
        }
    }

    @Override
    public List<WebElement> findElements(By loc) {
        try {
            testConfiguration.setLocator(loc.toString());
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
            return driver.findElements(loc);
        } catch (Exception e) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT));
            testConfiguration.getScenario().log("Locator value:" + loc);
            commonExceptions(e);
            throw e;
        }
    }


    public void verifyElementDisplayed(By loc, String log) {
        waitForVisibilityOfElement(loc);
        testConfiguration.getScenario().log(log);
    }

    public void assertTrue(boolean isDisplayed,String msg){
        try {
            Assert.assertTrue(isDisplayed);
            testConfiguration.getScenario().log(msg);
        }
        catch (Exception e){
            testConfiguration.getScenario().log(e.toString());
        }

    }


    @Override
    public void click(By loc) {
        waitForVisibilityOfElement(loc).click();
    }

    public void selectCheckBox(By loc) {
        WebElement element=waitForVisibilityOfElement(loc);
        if(!element.isSelected()){
            element.click();
        }
    }

    @Override
    public void clickUsingJS(By loc) {
        getJs().executeScript("arguments[0].click();", waitForPresenceOfElement(loc));
    }

    public void executeScript(String script) {
        getJs().executeScript(script);
    }

    @Override
    public void clickUsingAction(By loc) {
        Actions actions = new Actions(driver);
        actions.moveToElement(waitForVisibilityOfElement(loc)).click().build().perform();
    }

    public void quitDriver() {
        driver.quit();
    }

    public void closeDriver() {
        driver.close();
    }

    @Override
    public void waitForPageToLoad() {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
        try {
            log.info("waiting for page load...");
            wait.until(expectation);
        } catch (Exception e) {
            throw e;
        }
    }


    @Override
    public  void waitFor(double sec) {
        try {
            if (sec < 0 || sec > 500) {
                throw new IllegalArgumentException("Wait is specified is greater than 500 sec.");
            }
            log.info("waiting for "+(long) (sec*1000)+" sec...");
            Thread.sleep((long) (sec*1000));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getViewMethodNameInFailedTestScripts() {
        try {
            StackTraceElement[] viewMethodName = Thread.currentThread().getStackTrace();
            for (int k = 0; k < viewMethodName.length - 1; k++) {
                String method = Thread.currentThread().getStackTrace()[k].getMethodName();
                if (method.startsWith("RMID")) {
                    method = Thread.currentThread().getStackTrace()[k - 1].getMethodName();
                    return method;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Failed method not found.";
    }

    public void switchToIframe(By locator) {
        driver.switchTo().frame(waitForPresenceOfElement(locator));
    }

    public void switchToDefault() {
        driver.switchTo().defaultContent();
    }

    @SneakyThrows
    public void commonExceptions(Exception e) {
        int target=0;
        try {
            StackTraceElement[] viewMethodName = Thread.currentThread().getStackTrace();
            for (int i=0;i<viewMethodName.length;i++){
                if(viewMethodName[i].getClassName().contains("step")){
                    target=i;
                    break;
                }
            }
            testConfiguration.setFailedMethod(viewMethodName[target-1].getMethodName());
            testConfiguration.setFailedStep(viewMethodName[target].getMethodName());
            testConfiguration.getScenario().log("Failed methods :" + viewMethodName[target].getFileName() +" "+ viewMethodName[target].getMethodName());
            testConfiguration.getScenario().log(viewMethodName[target-1].getFileName() + " " + viewMethodName[target-1].getMethodName());
            testConfiguration.getScenario().log(viewMethodName[target-2].getFileName() + " " + viewMethodName[target-2].getMethodName());
            testConfiguration.getScenario().log(viewMethodName[target-3].getFileName() + " " + viewMethodName[target-3].getMethodName());
            String exception=e.toString().substring(0, 250).split(":")[0];
            testConfiguration.getScenario().log("Exception :" + exception);
            testConfiguration.setException(exception);
            throw e;
        } catch (Exception exception) {
            throw exception;
        }
    }

    public void switchToNewWindow(String title) {
        waitForPageToLoad();
        if(driver.getTitle().equals(title)){
            return;
        }
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> winids=driver.getWindowHandles();
        for (String winid:winids){
            if(getTitle(winid).equals(title)){
                return;
            }
        }
    }

    public String getTitle(String windID){
        driver.switchTo().window(windID);
        return driver.getTitle();
    }

    public void verifyNewTabDisplayedWithCreateAccountForm() {
        switchToNewWindow(CREATE_ACCOUNT_TITLE.toString());
        By hdrCreateAccount=H6_Header.setValue(HEADER_CREATE_ACCT.toString()).getLocator();
        verifyElementDisplayed(hdrCreateAccount,"Header displayed successfully :"+HEADER_CREATE_ACCT);
    }

    public void verifyNewTabDisplayedWithSignIn(String title) {
        switchToNewWindow(title);
    }


    public void verifyPopUp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    getJs().executeScript("async function getLocator(text){\n" +
                            "    while(true){\n" +
                            "    var expLoc;\n" +
                            "    await new Promise(r=>setTimeout(r,200));\n" +
                            "    var loc=document.querySelectorAll('button');\n" +
                            "    for (let index = 0; index < loc.length ; index++) {\n" +
                            "//    console.log(loc[index].getAttribute('id'))\n" +
                            "       if(loc[index].getAttribute('id')===text){\n" +
                            "           expLoc=loc[index];\n" +
                            "           expLoc.click();\n" +
                            "           console.log(loc[index].getAttribute('id'))\n" +
                            "           break;\n" +
                            "    }\n" +
                            "  }\n" +
                            " }\n" +
                            "};\n" +
                            "\n" +
                            "getLocator('ip-no');");
                }
            }
        }).start();
        System.out.println("5.....");
    }

    private By popupYes=By.xpath("//*[@id=\"ip-no\"]");
    private By iframe=By.xpath("//*[@id=\"iPerceptionsFrame\"]");
    public boolean selectPopUp(){
        if(isElementPresenct1(popupYes,2)){
            popup.click();
            return true;
        }
        return false;
    }

}