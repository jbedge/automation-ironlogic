package com.ironlogic.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironlogic.data.Constants;
import com.ironlogic.data.OrderData;
import io.cucumber.java.Scenario;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.ini4j.Ini;
import org.ini4j.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class TestConfiguration {



//  static class is used to get the singleton objects
//  as static class loads before the class and hecne there will be only one instance
    private static class Holder {
        private static final TestConfiguration INSTANCE = new TestConfiguration();
    }

    public static TestConfiguration getInstance() {
        return Holder.INSTANCE;
    }

    Logger log = LoggerFactory.getLogger(TestConfiguration.class);
    private String env;
    private Scenario scenario;
    private String browser;
    private Ini ini;
    private Profile.Section currentSection;
    private String adminUser;
    private OrderData[] orderData;



    private OrderData order_data;
    private int executioncnt;


    private String adminPwd;
    private String url;
    private String failedMethod;
    private String failedStep;
    private String exception;
    private String locator;
    private String retailEmail;
    private String email;
    private String mailinatorURL;
    private String firstName;
    private String LastName;
    private String contactNumber;
    private String password;
    private String CROL;
    private String rsaNumber;
    private String CROLCertificateName;
    private String organization;
    private String vendorName;
    private String street;
    private String street2;
    private String city;
    private String postalCode;
    private String storeName;
    private String store_street;
    private String store_street2;
    private String store_city;
    private String store_postalCode;
    private String CROLFirstName;
    private String CROLLastName;
    private String CROLContactNumber;
    private String RetailUser1;
    private String RetailUser2;

    @SneakyThrows
    private TestConfiguration()  {
        loadIniConfig().getConfigParameters();
    }

    public OrderData getOrder_data() {
        return orderData[executioncnt];
    }

    public TestConfiguration loadIniConfig() throws IOException {
        ini = new Ini(new File(Constants.INI_PATH));
        Set<String> sections=ini.keySet();
        for (String section:sections){
            Profile.Section s=ini.get(section);
            s.putAll(ini.get("Default"));
        }
        return this;
    }

    public void getConfigParameters(){
        Profile.Section section=this.getSection("Default");
        this.setEnv(section.get("env"));
        section=this.getSection(getEnv());
        this.setBrowser(section.get("browser"));
        this.setCurrentSection(getSection(getEnv()));
        this.setAdminUser(section.get("admin.user"));
        this.setAdminPwd(section.get("admin.pwd"));
        this.setUrl(section.get("url"));
        this.setMailinatorURL(section.get("mailinator.url"));
        this.setRetailUser1(section.get("retail.user1"));
        this.setRetailUser2(section.get("retail.user2"));
        loadJSON(section.get("json.path"));
    }

    @SneakyThrows
    public void loadJSON(String filepath){
        String dir=System.getProperty("user.dir");
        FileInputStream fis = new FileInputStream(dir+filepath);
        String jsonBody = IOUtils.toString(fis, StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        orderData = mapper.readValue(jsonBody, OrderData[].class);
    }

    public Profile.Section getSection(String sectionName){
        return ini.get(sectionName);
    }

    @Override
    public String toString() {
        return  "Environment='" + env + '\'' +
                ", Browser='" + browser + '\'' +
                ", AdminUser='" + adminUser + '\'' +
                ", AdminPwd='" + adminPwd + '\'' +
                ", url='" + url + '\'' +
                ", retailEmail='" + retailEmail + '\'' +
                ", firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", password='" + password + '\'' +
                ", CROL='" + CROL + '\'' +
                ", RsaNumber='" + rsaNumber + '\'' +
                ", CROLCertificateName='" + CROLCertificateName + '\'' +
                ", Organization='" + organization + '\'' +
                ", Street='" + street + '\'' +
                ", Street2='" + street2 + '\'' +
                ", City='" + city + '\'' +
                ", PostalCode='" + postalCode + '\'' +
                ", StoreName='" + storeName + '\'' +
                ", Store_street='" + store_street + '\'' +
                ", Store_street2='" + store_street2 + '\'' +
                ", Store_city='" + store_city + '\'' +
                ", Store_postalCode='" + store_postalCode + '\'' +
                ", CROLFirstName='" + CROLFirstName + '\'' +
                ", CROLLastName='" + CROLLastName + '\'' +
                ", CROLContactNumber='" + CROLContactNumber + '\'';
    }

}