package com.globoforce.mentoring.testautomation.uitesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.util.Strings;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import java.util.concurrent.TimeUnit;

public abstract class BasePage {

    protected final WebDriver webdriver;

    protected final int PAGE_LOAD_TIMEOUT = 50;
    protected final int IMPLICITLY_WAIT_TIMEOUT = 50;

    public BasePage(WebDriver driver) {
        this.webdriver = driver;
        this.webdriver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        this.webdriver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        HtmlElementLoader.populatePageObject(this, this.webdriver);
    }

    public WebDriver getWebDriver() {
        return this.webdriver;
    }

    public void setText(WebElement field, String text){
        field.clear();
        if (!Strings.isNullOrEmpty(text))
            field.sendKeys(text);
    }

    public void waitUntilVisible(WebElement element){
        WebDriverWait wait = new WebDriverWait(webdriver, 10);
        element = wait.until(ExpectedConditions.visibilityOf(element));
    }

    public Boolean waitUntilInvisible(WebElement element){
        WebDriverWait wait = new WebDriverWait(webdriver, 10);
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitUntilClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(webdriver, 20);
        element = wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
