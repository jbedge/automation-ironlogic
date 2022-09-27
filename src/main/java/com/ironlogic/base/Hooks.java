package com.ironlogic.base;

import com.ironlogic.data.RunTimeData;
import com.ironlogic.util.RandomUtil;
import io.cucumber.core.gherkin.Step;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;

public class Hooks {

    WebDriver driver;
    TestContext testContext;
    private TestConfiguration config;
    private static int cnt=0;

    public Hooks(TestContext context) {
        testContext = context;
        driver = context.getDriver();
        config=testContext.getTestConfiguration();
    }

    @Before
    public void setup(Scenario scenario) {
        config.setScenario(scenario);
    }

    @Before("@PlaceOrder")
    public void executionCount() {
        config.setExecutioncnt(cnt);
        cnt++;
    }

    @AfterStep("@config")
    public void setScenarios(Scenario scenario) {
        config.setScenario(scenario);
    }

    @After
    public void closeDriver(Scenario scenario) {
        boolean isfailed = scenario.isFailed();
        boolean isPass=!isfailed;
        try {
            if (isfailed) {
                testContext.getWebDriverManager().getScreenshot(scenario, scenario.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isPass) {
            testContext.getWebDriverManager().quitDriver();
        }
//        testContext.getWebDriverManager().quitDriver();
        RandomUtil.dumpRuntimeData(config);
    }

    @AfterSuite
    public void generateReport() {
        System.out.println("insdie after suite");
    }

    @AfterAll
    public static void afterAll(){
        System.out.println();
    }

}
