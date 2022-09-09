package com.ironlogic.core.pages;


import com.ironlogic.base.TestConfiguration;
import com.ironlogic.base.TestContext;
import com.ironlogic.base.UIAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HomePage extends UIAction {

    private WebDriver driver;
    private TestConfiguration testConfig;
    Logger logger = LoggerFactory.getLogger(TestConfiguration.class);

    public HomePage(TestContext testContext) {
        super(testContext);
        this.driver = driver;
        this.testConfig = testContext.getTestConfiguration();
    }


}