package com.ironlogic.core.stepdefination;

import com.ironlogic.base.TestConfiguration;
import com.ironlogic.base.TestContext;
import com.ironlogic.core.pages.HomePage;

public class HomePageSteps {

    private HomePage homePage;
    private TestContext testContext;
    private TestConfiguration testConfiguration;

    public HomePageSteps(TestContext testContext){
//        PropertyConfigurator.configure(System.getProperty("user.dir")+"\\resources\\log4j.properties");
        this.testContext=testContext;
        testConfiguration=testContext.getTestConfiguration();
        homePage =new HomePage(testContext);
    }




}
