package com.ironlogic.base;

import com.ironlogic.data.Constants;
import io.cucumber.java.Scenario;
import lombok.Data;
import org.ini4j.Ini;
import org.ini4j.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.util.Set;

@Data
public class TestConfiguration {

    Logger log = LoggerFactory.getLogger(TestConfiguration.class);
    private String env;
    private Scenario scenario;
    private String browser;
    private Ini ini;
    private Profile.Section currentSection;
    private String adminUser;
    private String adminPwd;
    private String retailUser;
    private String retailPwd;
    private String url;
    private String failedMethod;
    private String failedStep;
    private String exception;
    private String locator;

    public TestConfiguration() throws IOException {
        loadIniConfig().getConfigParameters();
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
        this.setRetailUser(section.get("retailer.user"));
        this.setRetailPwd(section.get("retailer.pwd"));
        this.setUrl(section.get("url"));
    }

    public Profile.Section getSection(String sectionName){
        return ini.get(sectionName);
    }

}