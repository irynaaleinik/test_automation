package com.globoforce.mentoring.testautomation.uitesting.scenarios;

import com.globoforce.mentoring.testautomation.uitesting.businessobject.User;
import com.globoforce.mentoring.testautomation.uitesting.pages.LandingPage;
import com.globoforce.mentoring.testautomation.uitesting.pages.LoginPage;
import com.globoforce.mentoring.testautomation.uitesting.pages.mydashboard.MyApprovalPage;
import com.globoforce.mentoring.testautomation.uitesting.pages.nomination.Nomination;
import com.globoforce.mentoring.testautomation.uitesting.services.NominationService;
import com.globoforce.mentoring.testautomation.uitesting.utils.WebDriverUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class ApproveNomination {

    private WebDriver driver;
    private User nominator;
    private User recipient;
    private User approver;
    LoginPage loginPage;
    Nomination nomination;
    LandingPage landingPage;
    MyApprovalPage myApprovalPage;
    private int numberOfPendingNominations;

    private static final String URL = "https://test-web1-17.corp.globoforce.com/microsites/t/home?client=recipientbased&setCAG=true";
    private String NOMINATOR = "nominator";
    private String RECIPIENT = "recipient2";
    private String APPROVER = "nominator2";
    private String AWARD_TITLE1 = "Test for Approval1";
    private String AWARD_TITLE2 = "Test for Approval2";
    private String MESSAGE_TO_APPROVER = "";
    private String AWARD_REASON = "REASON1";
    private String AWARD_ADVISOR = "Optional";
    private String AWARD_NAME = "Award3";
    private String NOMINATE_FROM = "Menu";

    private static final String CONFIRMATION_MESSAGE = "Thanks for taking the time to recognize a colleague.\n" +
            "It's an important part of our culture.";

    @Parameters({"driverName", "path"})
    @BeforeClass
    public void startDriverAndLogin(@Optional("chrome") String driverName, String path) throws InterruptedException {
        WebDriverUtil webDriverUtil = new WebDriverUtil(driverName, path);
        driver = webDriverUtil.setDriver();
        driver.navigate().to(URL);
        loginPage = new LoginPage(driver);
        nominator = new User(NOMINATOR);
        recipient = new User(RECIPIENT);
        loginPage.loginClientSites(nominator.getPersonUserName(), nominator.getPassword());
        nomination = new NominationService.NominationBuilder(driver)
                .setPlaceToStartNomination(NOMINATE_FROM)
                .setRecipient(recipient.getFirstName() + " " + recipient.getLastName())
                .setAwardReason(AWARD_REASON)
                .setAwardAdvisorMandatoryOrOptional(AWARD_ADVISOR)
                .setAwardLevel(AWARD_NAME)
                .setAwardTitle(AWARD_TITLE1)
                .setAwardMessage(AWARD_TITLE1)
                .setMessageToApprover(MESSAGE_TO_APPROVER)
                .setConfirmationMessage(CONFIRMATION_MESSAGE)
                .placeNomination();
        nomination = new NominationService.NominationBuilder(driver)
                .setPlaceToStartNomination(NOMINATE_FROM)
                .setRecipient(recipient.getFirstName() + " " + recipient.getLastName())
                .setAwardReason(AWARD_REASON)
                .setAwardAdvisorMandatoryOrOptional(AWARD_ADVISOR)
                .setAwardLevel(AWARD_NAME)
                .setAwardTitle(AWARD_TITLE2)
                .setAwardMessage(AWARD_TITLE2)
                .setMessageToApprover(MESSAGE_TO_APPROVER)
                .setConfirmationMessage(CONFIRMATION_MESSAGE)
                .placeNomination();
        landingPage = new LandingPage(driver);
        landingPage.logOut();
        approver = new User(APPROVER);
        loginPage.loginAfterLogOut(approver.getPersonUserName(), approver.getPassword());
    }

    @Test(description = "Approve Nomination by clicking Approve button on approval card", priority = 6)
    public void approveNominationOnCard(){
        myApprovalPage = new LandingPage(driver)
                .navigateToMyDashboardSubMenu()
                .openMyApprovalPage();
        numberOfPendingNominations = myApprovalPage.getNumberOfPendingNominations();
        myApprovalPage.approvePendingNominationByName(AWARD_TITLE1);
        Assert.assertEquals(myApprovalPage.getNumberOfPendingNominations(), numberOfPendingNominations-1);
    }

    @Test(description = "Approve Nomination by clicking Approve button in Approval lightbox", priority = 5)
    public void approveNominationFromApprovalLightbox(){
        myApprovalPage = new LandingPage(driver)
                .navigateToMyDashboardSubMenu()
                .openMyApprovalPage();
        numberOfPendingNominations = myApprovalPage.getNumberOfPendingNominations();
        myApprovalPage.openPendingNominationByName(AWARD_TITLE2)
                .approveNomination();
        Assert.assertEquals(myApprovalPage.getNumberOfPendingNominations(), numberOfPendingNominations-1);
    }

    @AfterClass(alwaysRun = true)
    public void closeDriver() {
        if (driver != null)
            driver.close();
    }
}
