package org.agileboard.appdriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.agileboard.common.CommonManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 1/29/2017.
 */

public class DriverProvider {
    private  Logger logger = Logger.getLogger(DriverProvider.class);

    private  AndroidDriver driver;
    private  WebDriverWait wait;
    private  DesiredCapabilities caps;

    public  void init(){
        try {
            caps = DesiredCapabilities.android();
            caps.setCapability(MobileCapabilityType.APP, "D://KnowledgeCentre//KCentre//TestApks//com.sauce.agile.apk");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME,"MyEmulator2");
            caps.setCapability("platformName", "Android");
            caps.setCapability("VERSION", "6.0.0");
            caps.setCapability("newCommandTimeout", 600);
            caps.setCapability("autoAcceptAlerts", true);
            caps.setCapability("session-override", true);
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
            wait= new WebDriverWait(driver,5);
            logger.info("Initiating drivers and lounching App");
            CommonManager.waitSeconds(1);
        } catch (MalformedURLException e) {
            logger.warn(e.getMessage()+"Can't open App");
        }
    }

    public  AndroidDriver getDriver() {
        return driver;
    }

    public  WebDriverWait getWaitDriver(){
        return wait;
    }

    public  boolean saveScreenshotTo(String path) {
        File screen = (File) driver.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screen, new File(path));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public  void end() {
        logger.info("Closing Driver");
        driver.quit();
    }

}
