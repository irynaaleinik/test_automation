package com.globoforce.mentoring.testautomation.uitesting.scenarios;

import com.globoforce.mentoring.testautomation.uitesting.businessobject.Client;
import com.globoforce.mentoring.testautomation.uitesting.businessobject.User;
import com.globoforce.mentoring.testautomation.uitesting.services.NominationService;
import com.globoforce.mentoring.testautomation.uitesting.utils.WebDriverUtil;
import com.globoforce.mentoring.testautomation.uitesting.pages.LoginPage;
import com.globoforce.mentoring.testautomation.uitesting.pages.nomination.Nomination;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class CreateNominationFromMyTeam {

    private WebDriver driver;
    private User nominator;
    private User recipient;
    private Nomination nomination;

    private static final String URL = "https://test-web1-17.corp.globoforce.com/microsites/t/home?client=recipientbased&setCAG=true";
    private String NOMINATOR = "nominator";
    private String RECIPIENT = "recipient";
    private String AWARD_TITLE = "Test Award +";
    private String AWARD_PROGRAM = "Life Events";
    private String AWARD_REASON = "New Baby";
    private String NOMINATE_FROM = "My Team";

    private static final String CONFIRMATION_MESSAGE = "Thanks for taking the time to recognize a colleague.\n" +
            "It's an important part of our culture.";

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
    public void createNominationFromMyTeam() throws InterruptedException {
        recipient = new User(RECIPIENT);
        nomination = new NominationService.NominationBuilder(driver)
                .setPlaceToStartNomination(NOMINATE_FROM)
                .setRecipient(recipient.getFirstName() + " " + recipient.getLastName())
                .setAwardProgram(AWARD_PROGRAM)
                .setAwardReason(AWARD_REASON)
                .setAwardTitle(AWARD_TITLE)
                .setAwardMessage(AWARD_TITLE)
                .setConfirmationMessage(CONFIRMATION_MESSAGE)
                .placeNomination();
    }

    @AfterClass(alwaysRun = true)
    public void closeDriver() {
        if (driver != null)
            driver.quit();
    }
}

