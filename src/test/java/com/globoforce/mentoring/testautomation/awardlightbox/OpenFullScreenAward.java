package com.globoforce.mentoring.testautomation.awardlightbox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class OpenFullScreenAward {
    private WebDriver driver;
    private static final String URL = "https://test-web1-17.corp.globoforce.com/microsites/t/home?client=recipientbased&setCAG=true";
    private String userName = "mandy_manager1";
    private String password = "pass";
    private String awardTitle = "Test Award +";

    @Parameters({ "driverName", "path" })
    @BeforeClass
    public void startDriver(String driverName, String path) {
        System.setProperty(driverName, path);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().to(URL);
        driver.findElement(By.name("username")).sendKeys(userName);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.id("signIn-button")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='navBar']//a[@href='/microsites/t/home?client=recipientbased']")).getText(), "Home");
    }

    @Test
    public void openAwardPlusFromMyNomination(){
        driver.findElement(By.xpath("//div[@id='navBar']//a[@href='/microsites/t/dashboard/MyActivity?client=recipientbased']")).click();
        driver.findElement(By.xpath("//ul[@id='subNavBar']//a[@href='/microsites/t/dashboard/MyNominations?client=recipientbased']")).click();
        driver.findElement(By.cssSelector("td.col-icons a.detailsLink")).click();
        driver.findElement(By.id("viewAward")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#unifiedAwardDetailsLightbox div#al-messageEditorContainer h2")).getText(),awardTitle,
                "Award title not found or different");
    }

    @Test(priority = 1)
    public void openAwardPlusFromNewsFeed(){
        driver.findElement(By.xpath("//div[@id='navBar']//a[@href='/microsites/t/home?client=recipientbased']")).click();
        driver.findElement(By.xpath("//h3[contains(text(),'" + awardTitle + "')]/following-sibling::div[contains(@class,'buttonStyle1')]//a[@id='viewAwardButton1-button']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#unifiedAwardDetailsLightbox div#al-messageEditorContainer h2")).getText(),awardTitle,
                "Award title not found or different");
    }

    @AfterClass(alwaysRun = true)
    public void closeDriver() {
        if (driver != null)
            driver.quit();
    }
}
