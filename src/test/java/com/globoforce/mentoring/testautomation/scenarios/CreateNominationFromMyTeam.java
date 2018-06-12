package com.globoforce.mentoring.testautomation.scenarios;

import com.globoforce.mentoring.testautomation.utils.WebDriverUtil;
import com.globoforce.mentoring.testautomation.pages.LandingPage;
import com.globoforce.mentoring.testautomation.pages.LoginPage;
import com.globoforce.mentoring.testautomation.pages.nomination.Nomination;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class CreateNominationFromMyTeam {

    private WebDriver driver;
    private static final String URL = "https://test-web1-17.corp.globoforce.com/microsites/t/home?client=recipientbased&setCAG=true";
    private String userName = "mandy_manager1";
    private String password = "pass";
    private String awardTitle = "Test Award +";
    private String directReportName = "Renee Recipient1";
    private String awardProgram = "Life Events";
    private String awardReason = "New Baby";

    @Parameters({"driverName", "path"})
    @BeforeClass
    public void startDriverAndLogin(@Optional("chrome") String driverName, String path) {
        WebDriverUtil webDriverUtil = new WebDriverUtil(driverName, path);
        driver = webDriverUtil.setDriver();
        driver.navigate().to(URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginClientSites(userName, password);
    }

    @Test(priority = 4)
    public void createNominationFromMyTeam(){
        Nomination nomination = new LandingPage(driver)
                .openMyTeam()
                .openDirectReportIndividualViewByName(directReportName)
                .recognize()
                .navigateToNextStep()
                .chooseAwardProgramByName(awardProgram)
                .chooseAwardReasonByName(awardReason)
                .setTitle(awardTitle)
                .setMessage(awardTitle)
                .createNomination();
        Assert.assertTrue(nomination.checkConfirmationImage(), "Something goes wrong, confirmation image is noe displayed");

    }

    @AfterClass(alwaysRun = true)
    public void closeDriver() {
        if (driver != null)
            driver.close();
    }
}

