package com.globoforce.mentoring.testautomation.awardlightbox.scenarios;

import com.globoforce.mentoring.testautomation.awardlightbox.Utils;
import com.globoforce.mentoring.testautomation.awardlightbox.pages.AwardPlusLightboxPage;
import com.globoforce.mentoring.testautomation.awardlightbox.pages.LandingPage;
import com.globoforce.mentoring.testautomation.awardlightbox.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class OpenFullScreenAward {
    private WebDriver driver;
    private static final String URL = "https://test-web1-17.corp.globoforce.com/microsites/t/home?client=recipientbased&setCAG=true";
    private String userName = "mandy_manager1";
    private String password = "pass";
    private String awardTitle = "Test Award +";

    @Parameters({ "driverName", "path" })
    @BeforeClass
    public void startDriverAndLogin(@Optional("chrome") String driverName, String path) {
        Utils utils = new Utils(driverName, path);
        driver = utils.setDriver();
        driver.navigate().to(URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginClientSites(userName, password);
    }

    @Test
    public void openAwardPlusFromNewsFeed() {
        AwardPlusLightboxPage awardPlus = new LandingPage(driver)
                .openHome()
                .openAwardPlusByAwardTitle(awardTitle);
        Assert.assertEquals(awardPlus.getAwardTitle(),awardTitle,"Award title not found or different");
    }

    @Test(priority = 1)
    public void openAwardPlusFromNewMyActivity(){
        AwardPlusLightboxPage awardPlus = new LandingPage(driver)
                .openMyActivity()
                .openAwardPlusByAwardTitle(awardTitle);
        Assert.assertEquals(awardPlus.getAwardTitle(),awardTitle,"Award title not found or different");
    }

    @Test(priority = 2)
    public void openAwardFromNewUserProfile(){
        AwardPlusLightboxPage awardPlus = new LandingPage(driver)
                .openHome()
                .openAwardRecipientProfileByAwardTitle(awardTitle)
                .openAwardPlusByAwardTitle(awardTitle);
        Assert.assertEquals(awardPlus.getAwardTitle(),awardTitle,"Award title not found or different");
    }

    @AfterClass(alwaysRun = true)
    public void closeDriver() {
        if (driver != null)
            driver.close();
    }
}
