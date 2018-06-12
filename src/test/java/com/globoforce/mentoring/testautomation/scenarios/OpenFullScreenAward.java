package com.globoforce.mentoring.testautomation.scenarios;

import com.globoforce.mentoring.testautomation.businessobject.User;
import com.globoforce.mentoring.testautomation.utils.DataBaseUtil;
import com.globoforce.mentoring.testautomation.utils.WebDriverUtil;
import com.globoforce.mentoring.testautomation.pages.awardlightbox.AwardPlusLightboxPage;
import com.globoforce.mentoring.testautomation.pages.LandingPage;
import com.globoforce.mentoring.testautomation.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.SQLException;
import java.util.ResourceBundle;

public class OpenFullScreenAward {
    private WebDriver driver;
    private DataBaseUtil dataBaseUtil;
    private ResourceBundle resource;
    private User user;

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

    @Test
    public void openAwardPlusFromNewsFeed() {
        AwardPlusLightboxPage awardPlus = new LandingPage(driver)
                .openHome()
                .openAwardPlusByAwardTitle(AWARD_TITLE);
        Assert.assertEquals(awardPlus.getAwardTitle(), AWARD_TITLE,"Award title not found or different");
    }

    @Test(priority = 1)
    public void openAwardPlusFromNewMyActivity(){
        AwardPlusLightboxPage awardPlus = new LandingPage(driver)
                .openMyActivity()
                .openAwardPlusByAwardTitle(AWARD_TITLE);
        Assert.assertEquals(awardPlus.getAwardTitle(), AWARD_TITLE,"Award title not found or different");
    }

    @Test(priority = 2)
    public void openAwardFromNewUserProfile(){
        AwardPlusLightboxPage awardPlus = new LandingPage(driver)
                .openHome()
                .openAwardRecipientProfileByAwardTitle(AWARD_TITLE)
                .openAwardPlusByAwardTitle(AWARD_TITLE);
        Assert.assertEquals(awardPlus.getAwardTitle(), AWARD_TITLE,"Award title not found or different");
    }

    @AfterClass(alwaysRun = true)
    public void closeDriver() throws SQLException {
        dataBaseUtil.updateClientProperty(CLIENT_ID,PROP_NAME,"false");
        if (driver != null)
            driver.close();
    }
}
