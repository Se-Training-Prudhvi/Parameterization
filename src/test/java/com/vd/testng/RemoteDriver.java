package com.vd.testng;

import junit.framework.TestCase; //removes need for annotations by extending
import org.openqa.selenium.*;
import org.openqa.selenium.remote.*;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteDriver extends TestCase {
    private WebDriver driver;
    
    public void setUp() throws Exception {
        DesiredCapabilities abilities = DesiredCapabilities.firefox();
        abilities.setCapability("platform", Platform.MAC);
        abilities.setCapability("webdriver.gecko.driver", "/Users/z013th3/Downloads/geckodriver");
        abilities.setCapability("name", "Testing Selenium-3 Remote WebDriver");
        driver = new RemoteWebDriver( new URL("http://10.92.245.98:4444/wd/hub"), abilities);
    }

    public void testSimple() throws Exception {
        this.driver.get("http://www.google.com");
        assertEquals("Google", this.driver.getTitle());
    }

    public void tearDown() throws Exception {
        this.driver.quit();
    }
}
