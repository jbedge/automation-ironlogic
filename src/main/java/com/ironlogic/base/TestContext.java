package com.ironlogic.base;


import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestContext {
    private DriverManager webDriverManager;
    private TestConfiguration testConfiguration;
    private WebDriver driver;
    public ScenarioContext scenarioContext;
    Logger logger = LoggerFactory.getLogger(TestConfiguration.class);

    public TestContext() throws Exception {
        testConfiguration = TestConfiguration.getInstance();
        webDriverManager = new DriverManager(testConfiguration);
        driver=webDriverManager.initDriver();
    }

    public TestConfiguration  getTestConfiguration() {
        return testConfiguration;
    }
    public DriverManager getWebDriverManager() {
        return webDriverManager;
    }
    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
    public Logger getLogger() {
        return logger;
    }
    public WebDriver getDriver(){return driver;}

}