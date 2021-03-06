package ru.stqa.pft.mantis.appmanager;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.security.Key;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by elina_000 on 17.03.2018.
 */
public class ApplicationManager {

    private final Properties properties;
    WebDriver wd;

    private String browser;
    private MailHelper mailHelper;
    private JamesHelper jamesHelper;
    private SignInHelper signInHelper;
    private SoapHelper soapHelper;


    public ApplicationManager(String browser)  {
        this.browser = browser;
        properties = new Properties();

    }

    public void init() throws Exception {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));


        if (browser.equals(BrowserType.FIREFOX)){
            wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));}
        else if (browser.equals(BrowserType.CHROME)){
            wd = new ChromeDriver();
        } else {
            throw new Exception("The browser is not supported");
        }
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));

    }

    public void stop() {
        wd.quit();
    }
    public HttpSession newSession(){
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public MailHelper mail(){
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }
    public JamesHelper james(){
        if (jamesHelper ==null){
            jamesHelper = new JamesHelper(this);
        }
        return jamesHelper;
    }
    public SignInHelper signIn(){
        if (signInHelper == null) {
            signInHelper = new SignInHelper(this);
        }
        return signInHelper;}

    public SoapHelper soap(){
        if (soapHelper == null) {
            soapHelper = new SoapHelper(this);
        }
        return soapHelper;
    }
}
