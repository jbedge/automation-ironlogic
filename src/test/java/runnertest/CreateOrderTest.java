package runnertest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironlogic.base.TestConfiguration;
import com.ironlogic.base.TestContext;
import com.ironlogic.core.pages.LoginPage;
import com.ironlogic.data.OrderData;
import io.cucumber.core.options.RuntimeOptions;
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
    Logger logger = LoggerFactory.getLogger(TestConfiguration.class);
    private String dateString;
    private OrderData[] orderData;
    private TestConfiguration config;

    public CreateOrderTest() throws Exception {
        config = ((TestContext)this).getTestConfiguration();
    }

    @Test(dataProvider="testData")
    public void createOrder(OrderData orderData){
        orderData.getStockSKU().forEach(System.out::println);
        orderData.getFlowThroghSKU().forEach(System.out::println);
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

}