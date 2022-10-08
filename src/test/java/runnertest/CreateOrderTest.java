package runnertest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironlogic.base.TestConfiguration;
import com.ironlogic.base.TestContext;
import com.ironlogic.core.pages.LoginPage;
import com.ironlogic.core.pages.OrderPage;
import com.ironlogic.data.OrderData;
import io.cucumber.core.options.RuntimeOptions;
import io.cucumber.datatable.DataTable;
import io.cucumber.tagexpressions.Expression;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import lombok.SneakyThrows;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateOrderTest extends TestContext {
    private OrderData[] orderData;
    private TestConfiguration config;
    private OrderPage orderPage;
    private LoginPage loginPage;
    private TestContext testContext;

    public CreateOrderTest() throws Exception {
        testContext=this;
        config = testContext.getTestConfiguration();
        orderPage = new OrderPage(this);
        loginPage = new LoginPage(this);
    }

    @Test(dataProvider="testData")
    public void createOrder(OrderData orderData){
        DataTable dataTable=DataTable.emptyDataTable();
        loginPage.getURL();
        loginPage.enterRetailCredentials(dataTable);
        loginPage.clickOnLogin();
        orderPage.verifyOrderPageDisplayed1();
        orderPage.clearMyCart1();
        ArrayList<String> stockSKU = config.getOrder_data().getStockSKU();
        for (String order : stockSKU) {
            String[] skuqty = order.split("\\|");
            orderPage.placeOrderForStockSKu1(skuqty[0], Integer.parseInt(skuqty[1]));
        }
        stockSKU = config.getOrder_data().getFlowThroghSKU();
        for (String order : stockSKU) {
            String[] skuqty = order.split("\\|");
            orderPage.placeOrderForFlowThroughSKU1(skuqty[0], Integer.parseInt(skuqty[1]));
        }
        orderPage.submitOrder();
        orderPage.verifyOrderSubmission1();

    }


    @SneakyThrows
    @DataProvider(name = "testData")
    public Object[] loadTestData(){
        String dir=System.getProperty("user.dir");
        String filepath= config.getCurrentSection().get("json.path");
        FileInputStream fis = new FileInputStream(dir+filepath);
        String jsonBody = IOUtils.toString(fis, StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        orderData = mapper.readValue(jsonBody, OrderData[].class);
        return orderData;
    }

    @AfterTest
    public void quitDriver(){
//        testContext.getWebDriverManager().quitDriver();
    }

}