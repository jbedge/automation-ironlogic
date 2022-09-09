package com.ironlogic.base;

import io.cucumber.java.Scenario;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.Duration;
import java.util.Map;
import com.ironlogic.data.Constants;

import javax.imageio.ImageIO;


public class DriverManager {
    public WebDriver driver;
    public ChromeDriver chromeDriver;
    public Boolean serverStarted  = false;
    public String dummyText;
    private String LOCAL_DIRECTORY_SOURCE = System.getProperty("user.dir") + File.separator + "browserDrivers" + File.separator;
    private String session_details = System.getProperty("user.dir") + File.separator + "tempFiles" + File.separator+"sessionDetails.tmp";
    TestConfiguration configuration;


    public DriverManager(TestConfiguration testConfiguration) {
        configuration= testConfiguration;
    }



    public WebDriver initDriver() throws Exception {
        String browser = configuration.getBrowser();
        switch (browser.toLowerCase()) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver",Constants.DRIVER_PATH);
//                WebDriverManager.chromedriver().setup();
                ChromeOptions options1 = new ChromeOptions();
                options1.addArguments("--disable-extensions");
                options1.addArguments("disable-infobars");
                options1.addArguments("start-maximized");
                options1.addArguments("--disable-gpu");
                chromeDriver= new ChromeDriver(options1);
                String debugPort=(String)((Map)chromeDriver.getCapabilities().getCapability("goog:chromeOptions")).get("debuggerAddress");
                writeFile(session_details,debugPort);
                driver=(WebDriver)chromeDriver;
                driver.manage().deleteAllCookies();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT));
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Constants.PAGE_TIME));
                break;
            case "debugchrome":
                System.setProperty("webdriver.chrome.driver","browserDrivers/chromedriver.exe");
                options1= new ChromeOptions();
                String port=reafFile(session_details);
                options1.setExperimentalOption("debuggerAddress",port);
                chromeDriver=new ChromeDriver(options1);
                driver=(WebDriver)chromeDriver;
                chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT));
                chromeDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Constants.PAGE_TIME));
                break;
        }
        return driver;
    }

    public void quitDriver() {
        driver.quit();
    }

    public void closeDriver() {
        driver.close();
    }

    public void getScreenshot(Scenario scenario, String udid) throws Exception {
        if (scenario.isFailed()) {
            try {
                byte[] img = (byte[]) ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                File file = new File(Constants.SCREENSHOT_PATH+configuration.getFailedMethod()+".jpeg");
                saveFile(file,img);
                addException(file);
                scenario.attach(img, "image/jpeg",udid);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String reafFile(String filePath) throws Exception{
        FileInputStream fileInputStream=new FileInputStream(filePath);
        String fileString= IOUtils.toString(fileInputStream,"UTF-8");
        return fileString;
    }

    public void writeFile(String filePath,String dataToWrite) throws Exception{
        FileWriter fileWriter=new FileWriter(filePath);
        fileWriter.write(dataToWrite);
        fileWriter.close();
    }

    public void addException(File file) throws IOException {
        final BufferedImage image = ImageIO.read(file);
        Graphics g = image.getGraphics();
        g.setColor(Color.RED);
        g.setFont(g.getFont().deriveFont(30f));
        g.drawString(wrapString(configuration.getException()), 100, 100);
        g.drawString(wrapString(configuration.getLocator()), 100, 140);
        g.drawString(wrapString(configuration.getFailedMethod()), 100, 180);
        g.drawString(wrapString(configuration.getFailedStep()), 100, 220);

        g.dispose();
        ImageIO.write(image, "png", file);
    }

    public void saveFile(File file,byte[] img){
        try {
            OutputStream os = new FileOutputStream(file);
            os.write(img);
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String wrapString(String s){
        StringBuilder sb = new StringBuilder(s);
        int i = 0;
        while (i + 80 < sb.length()) {
            sb.replace(i, i + 1, "\n");
            i=i+80;
        }
        return sb.toString();
    }

//    public static void main(String[] args) {
//        System.out.println(wrapString("org.openqa.selenium.NoSuchElementException: no such element: Unable to locate element: {\"method\":\"xpath\",\"selector\":\"//a[./img[contains(@alt,'Logo..')]]\"}"));
//    }

}