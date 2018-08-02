package com.globoforce.mentoring.testautomation.uitesting.scenarios;

import com.globoforce.mentoring.testautomation.uitesting.businessobject.Client;
import com.globoforce.mentoring.testautomation.uitesting.businessobject.User;
import com.globoforce.mentoring.testautomation.uitesting.utils.DataBaseUtil;
import com.globoforce.mentoring.testautomation.uitesting.utils.ScreenshotUtil;
import com.globoforce.mentoring.testautomation.uitesting.utils.TestListener;
import com.globoforce.mentoring.testautomation.uitesting.utils.WebDriverUtil;
import com.globoforce.mentoring.testautomation.uitesting.pages.awardlightbox.AwardPlusLightboxPage;
import com.globoforce.mentoring.testautomation.uitesting.pages.LandingPage;
import com.globoforce.mentoring.testautomation.uitesting.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.sql.SQLException;

@Listeners(TestListener.class)
public class OpenFullScreenAward {
    private WebDriver driver;
    private DataBaseUtil dataBaseUtil;
    private User user;
    private Client client;

    private static final String URL = "https://test-web1-17.corp.globoforce.com/microsites/t/home?client=recipientbased&setCAG=true";
    private static final String FLUSH_URL = "https://test-web1-17.corp.globoforce.com/cache-manager-app/cache/CacheFlushServlet";
    private static final long CLIENT_ID = 901;
    private static final String PROP_NAME = "g_cfg_aurora_profile_enabled";
    private static final String AWARD_TITLE = "Test Award +";
    private static final String USER_ROLE = "nominator";


    @Parameters({ "driverName", "path" })
    @BeforeClass
    public void startDriverAndLogin(@Optional("chrome") String driverName, String path) throws ClassNotFoundException,SQLException {
        WebDriverUtil webDriverUtil = new WebDriverUtil(driverName, path);
        driver = webDriverUtil.setDriver();
        dataBaseUtil = new DataBaseUtil();
        dataBaseUtil.updateClientProperty(CLIENT_ID,PROP_NAME,"true");
        driver.navigate().to(FLUSH_URL);
        driver.navigate().to(URL);
        LoginPage loginPage = new LoginPage(driver);
        user = new User(USER_ROLE);
        loginPage.loginClientSites(user.getPersonUserName(), user.getPassword());
    }

    @Test(priority = 2)
    public void openAwardPlusFromNewsFeed() {
        AwardPlusLightboxPage awardPlus = new LandingPage(driver)
                .openHome()
                .openAwardPlusByAwardTitle(AWARD_TITLE);
        Assert.assertEquals(awardPlus.getAwardTitle(), AWARD_TITLE,"Award title not found or different");
    }

    @Test(priority = 3)
    public void openAwardPlusFromNewMyActivity(){
        AwardPlusLightboxPage awardPlus = new LandingPage(driver)
                .openMyActivity()
                .openAwardPlusByAwardTitle(AWARD_TITLE);
        Assert.assertEquals(awardPlus.getAwardTitle(), AWARD_TITLE,"Award title not found or different");
    }

    @Test(priority = 4)
    public void openAwardFromNewUserProfile(){
        AwardPlusLightboxPage awardPlus = new LandingPage(driver)
                .openHome()
                .openAwardRecipientProfileByAwardTitle(AWARD_TITLE)
                .openAwardPlusByAwardTitle(AWARD_TITLE);
        Assert.assertEquals(awardPlus.getAwardTitle(), AWARD_TITLE,"Award title not found or different");
    }

    @AfterMethod
    public void takeScreenshot(ITestResult result) {
        ScreenshotUtil.captureScreenshot(driver, result);
    }

    @AfterClass(alwaysRun = true)
    public void closeDriver() throws SQLException, ClassNotFoundException {
        dataBaseUtil = new DataBaseUtil();
        dataBaseUtil.updateClientProperty(CLIENT_ID,PROP_NAME,"false");
        if (driver != null)
            driver.close();
    }
}
