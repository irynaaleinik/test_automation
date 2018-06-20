package com.globoforce.mentoring.testautomation.scenarios;

import com.globoforce.mentoring.testautomation.businessobject.Client;
import com.globoforce.mentoring.testautomation.businessobject.User;
import com.globoforce.mentoring.testautomation.utils.DataBaseUtil;
import com.globoforce.mentoring.testautomation.utils.WebDriverUtil;
import com.globoforce.mentoring.testautomation.pages.LandingPage;
import com.globoforce.mentoring.testautomation.pages.LoginPage;
import com.globoforce.mentoring.testautomation.pages.nomination.Nomination;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ResourceBundle;

public class CreateNominationFromMyTeam {

    private WebDriver driver;
    private User nominator;
    private User recipient;

    private static final String URL = "https://test-web1-17.corp.globoforce.com/microsites/t/home?client=recipientbased&setCAG=true";
    private String NOMINATOR = "nominator";
    private String RECIPIENT = "recipient";
    private String AWARD_TITLE = "Test Award +";
    private String AWARD_PROGRAM = "Life Events";
    private String AWARD_REASON = "New Baby";

    private static final String CONFIRMATION_MESSAGE = "Thanks for taking the time to recognize a colleague.\n" +
            "It's an important part of our culture. ";

    @Parameters({"driverName", "path"})
    @BeforeClass
    public void startDriverAndLogin(@Optional("chrome") String driverName, String path) {
        WebDriverUtil webDriverUtil = new WebDriverUtil(driverName, path);
        driver = webDriverUtil.setDriver();
        driver.navigate().to(URL);
        LoginPage loginPage = new LoginPage(driver);
        nominator = new User(NOMINATOR);
        loginPage.loginClientSites(nominator.getPersonUserName(), nominator.getPassword());
    }

    @Test(description = "Verify that nomination successfully created from My Team/ Individual view with preselected recipient", priority = 1)
    public void createNominationFromMyTeam(){
        recipient = new User(RECIPIENT);
        Nomination nomination = new LandingPage(driver)
                .openMyTeam()
                .openDirectReportIndividualViewByName(recipient.getFirstName() + " " + recipient.getLastName())
                .recognize()
                .navigateToNextStep()
                .chooseAwardProgramByName(AWARD_PROGRAM)
                .chooseAwardReasonByName(AWARD_REASON)
                .setTitle(AWARD_TITLE)
                .setMessage(AWARD_TITLE)
                .createNomination();
        Assert.assertEquals(nomination.getConfirmationMessage(), CONFIRMATION_MESSAGE, "Something goes wrong, confirmation message is not displayed");
        nomination.closeNominationConfirmation();
    }

    @AfterClass(alwaysRun = true)
    public void closeDriver() {
        if (driver != null)
            driver.quit();
    }
}

