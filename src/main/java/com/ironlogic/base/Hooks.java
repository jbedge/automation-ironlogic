package com.ironlogic.base;

import io.cucumber.core.gherkin.Step;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;

public class Hooks {

    WebDriver driver;
    TestContext testContext;

    public Hooks(TestContext context){
        testContext=context;
        driver=context.getDriver();
    }

    @Before
    public void setup(Scenario scenario){
        testContext.getTestConfiguration().setScenario(scenario);
    }

    @AfterStep("@config")
    public void setScenarios(Scenario scenario){
        testContext.getTestConfiguration().setScenario(scenario);
    }

    @After
    public void closeDriver(Scenario scenario){
        try {
            testContext.getWebDriverManager().getScreenshot(scenario,scenario.getId());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try {
            testContext.getWebDriverManager().quitDriver();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @AfterSuite
    public void generateReport(){
        System.out.println("insdie after suite");
    }

}
