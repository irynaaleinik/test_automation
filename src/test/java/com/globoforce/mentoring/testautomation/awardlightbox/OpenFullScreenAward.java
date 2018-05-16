package com.globoforce.mentoring.testautomation.awardlightbox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class OpenFullScreenAward {
    private WebDriver driver;
    private static final String URL = "https://test-web1-17.corp.globoforce.com/microsites/t/home?client=recipientbased&setCAG=true";
    private String userName = "mandy_manager1";
    private String password = "pass";
    private String awardTitle = "Test Award +";

    private String myDashboardMenuLocator = "//div[@id='navBar']//a[@href='/microsites/t/dashboard/MyActivity?client=recipientbased']";
    private String homeMenuLocator = "//div[@id='navBar']//a[@href='/microsites/t/home?client=recipientbased']";

    @Parameters({ "driverName", "path" })
    @BeforeClass
    public void startDriverAndLogin(@Optional("chrome") String driverName, String path) {
        Utils utils = new Utils(driverName, path);
        driver = utils.setDriver();
        driver.manage().window().maximize();
        driver.navigate().to(URL);
        driver.findElement(By.name("username")).sendKeys(userName);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.id("signIn-button")).click();
    }

    @Test
    public void openAwardPlusFromMyNomination(){
        driver.findElement(By.xpath(myDashboardMenuLocator)).click();
        driver.findElement(By.xpath("//ul[@id='subNavBar']//a[@href='/microsites/t/dashboard/MyNominations?client=recipientbased']")).click();
        driver.findElement(By.cssSelector("div#my_nomination tbody tr:last-child td.col-icons a.detailsLink")).click();
        driver.findElement(By.id("viewAward")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#unifiedAwardDetailsLightbox div#al-messageEditorContainer h2")).getText(),awardTitle,
                "Award title not found or different");
    }

    @Test(priority = 1)
    public void openAwardPlusFromNewsFeed(){
        driver.findElement(By.xpath(homeMenuLocator)).click();
        driver.findElement(By.xpath("//h3[contains(text(),'" + awardTitle + "')]/following-sibling::div[contains(@class,'buttonStyle1')]//a[@id='viewAwardButton1-button']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#unifiedAwardDetailsLightbox div#al-messageEditorContainer h2")).getText(),awardTitle,
                "Award title not found or different");
    }

    @Test(priority = 2)
    public void openAwardPlusFromNewMyActivity(){
        driver.findElement(By.xpath(myDashboardMenuLocator)).click();
        driver.findElement(By.xpath("//p[contains(text(),'" + awardTitle + "')]/following-sibling::a")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#unifiedAwardDetailsLightbox div#al-messageEditorContainer h2")).getText(),awardTitle,
                "Award title not found or different");
    }

    @Test(priority = 3)
    public void openAwardFromNewUserProfile(){
        driver.findElement(By.xpath(homeMenuLocator)).click();
        driver.findElement(By.xpath("//h3[contains(.,'" + awardTitle + "')]/ancestor::div[contains(@class, 'awardNewsItem')]/descendant::h3/child::a")).click();
        driver.findElement(By.xpath("//p[contains(text(),'" + awardTitle + "')]/following-sibling::a")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#unifiedAwardDetailsLightbox div#al-messageEditorContainer h2")).getText(),awardTitle,
                "Award title not found or different");
    }

    @AfterClass(alwaysRun = true)
    public void closeDriver() {
        if (driver != null)
            driver.quit();
    }

}
