package com.globoforce.mentoring.testautomation.scenarios;

import com.globoforce.mentoring.testautomation.businessobject.User;
import com.globoforce.mentoring.testautomation.pages.LandingPage;
import com.globoforce.mentoring.testautomation.pages.LoginPage;
import com.globoforce.mentoring.testautomation.pages.mydashboard.MyApprovalPage;
import com.globoforce.mentoring.testautomation.pages.nomination.Nomination;
import com.globoforce.mentoring.testautomation.utils.WebDriverUtil;
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
    private String AWARD_NAME = "Award3";

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
        loginPage.loginClientSites(nominator.getPersonUserName(), nominator.getPassword());
        placeNomination(AWARD_TITLE1);
        placeNomination(AWARD_TITLE2);
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

    private void placeNomination(String awardTitle) throws InterruptedException {
        recipient = new User(RECIPIENT);
        nomination = new LandingPage(driver)
                .startNomination()
                .selectRecipientBySearch(recipient.getFirstName() + " " + recipient.getLastName())
                .navigateToNextStep()
                .chooseAwardReasonByName(AWARD_REASON)
                .skipAwardAdvisor()
                .chooseAwardLevel(AWARD_NAME)
                .setTitle(awardTitle)
                .setMessage(awardTitle)
                .setMessageToApprover(MESSAGE_TO_APPROVER)
                .createNomination();
        Assert.assertEquals(nomination.getConfirmationMessage(), CONFIRMATION_MESSAGE, "Something goes wrong, confirmation message is not displayed");
        nomination.closeNominationConfirmation();
    }

    @AfterClass(alwaysRun = true)
    public void closeDriver() {
        if (driver != null)
            driver.close();
    }
}
