package com.globoforce.mentoring.testautomation.awardlightbox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import java.io.File;
<<<<<<< HEAD
import java.util.concurrent.TimeUnit;
=======
>>>>>>> pageobject

public class Utils {
    private String driverName;
    private String path;
    WebDriver driver;

    public Utils(String driverName, String path){
        this.driverName = driverName;
        this.path = path;

    }

    public WebDriver setDriver() {
        switch (this.driverName) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", this.path);
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", this.path);
                driver = new FirefoxDriver();
                break;
            case "opera":
                System.setProperty("webdriver.opera.driver", this.path);
                OperaOptions options = new OperaOptions();
<<<<<<< HEAD
                //options.setBinary(new File("C:\\Users\\Iryna_Aleinik\\AppData\\Local\\Programs\\Opera\\launcher.exe"));
                driver = new OperaDriver(options);
                break;
            default:
                System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
        };
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
=======
                options.setBinary(new File("C:\\Users\\Iryna_Aleinik\\AppData\\Local\\Programs\\Opera\\launcher.exe"));
                driver = new OperaDriver(options);
                break;
            default:
                System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\operadriver.exe");
                driver = new ChromeDriver();
                break;
        };
>>>>>>> pageobject
        return driver;
    }
}
