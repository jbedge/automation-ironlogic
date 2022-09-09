package com.ironlogic.base;


import com.ironlogic.data.Constants;
import lombok.SneakyThrows;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;


public class UIAction implements Action {

    Logger logger = LoggerFactory.getLogger(TestConfiguration.class);
    public WebDriver driver;
    private final TestConfiguration testConfiguration;
    private WebDriverWait wait;

    public UIAction(TestContext context) {
        this.driver = context.getDriver();
        this.testConfiguration = context.getTestConfiguration();
        wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.PAGE_TIME), Duration.ofSeconds(Constants.POLL_TIME));
    }

    public void highlight(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='solid 2px orange'", element);
    }

    public void setText(By locator, CharSequence... value) {
        waitForVisibilityOfElement(locator).sendKeys(value);
    }

    public void setText(By locator, Keys value) {
        waitForVisibilityOfElement(locator).sendKeys(value);
    }

    @Override
    public WebElement waitForVisibilityOfElement(By loc) {
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

    public WebElement waitForVisibilityOfElement(UIElement loc) {
        WebElement element;
        try {
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(loc.getElement()));
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

    @Override
    public WebElement findElement(By loc) {
        try {
            testConfiguration.setLocator(loc.toString());
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            return driver.findElement(loc);
        } catch (Exception e) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT));
            testConfiguration.getScenario().log("Locator value:" + loc);
            commonExceptions(e);
            throw e;
        }
    }

    public WebElement findElement(UIElement loc) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            return driver.findElement(loc.getElement());
        } catch (Exception e) {
            testConfiguration.getScenario().log("Locator value:" + loc.getValue());
            commonExceptions(e);
            throw e;
        }
    }

    public void verifyElementDisplayed(By loc, String log) {
        waitForVisibilityOfElement(loc);
        testConfiguration.getScenario().log(log);
    }


    @Override
    public void click(By loc) {
        waitForVisibilityOfElement(loc).click();
    }

    @Override
    public void clickUsingJS(By loc) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", waitForPresenceOfElement(loc));
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

    public void waitForPageLoad() {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
        try {
            logger.info("waiting for page load...");
            wait.until(expectation);
        } catch (Exception e) {
            throw e;
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



}