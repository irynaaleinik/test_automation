package com.globoforce.mentoring.testautomation.pages;

import com.globoforce.mentoring.testautomation.pages.awardlightbox.AwardPlusLightboxPage;
import com.globoforce.mentoring.testautomation.pages.mydashboard.MyActivityPage;
import com.globoforce.mentoring.testautomation.pages.mydashboard.MyApprovalPage;
import com.globoforce.mentoring.testautomation.pages.mydashboard.MyDashboardSubMenuPage;
import com.globoforce.mentoring.testautomation.pages.myteam.MyTeam;
import com.globoforce.mentoring.testautomation.pages.nomination.Nomination;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.w3c.dom.html.HTMLElement;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;

public class LandingPage extends BasePage {

    @FindBy(xpath = "//div[@id='navBar']/descendant::a[@href='/microsites/t/home?client=recipientbased']")
    private HtmlElement homeMenu;

    @FindBy(xpath = "//div[@id='navBar']//a[@href='/microsites/t/dashboard/MyActivity?client=recipientbased']")
    private HtmlElement myDashboardMenu;

    @FindBy(xpath = "//div[@id='navBar']//a[@href='/microsites/t/myteam/MyTeamHome']")
    private HtmlElement myTeamMenu;

    @FindBy(id = "np_start")
    private Button recognizeMenu;

    @FindBy(linkText = "Log Out")
    private Link logOutLink;

    public LandingPage (WebDriver driver){
        super(driver);
    }

    public LandingPage openHome(){
        homeMenu.click();
        return this;
    }

    public MyActivityPage openMyActivity(){
        myDashboardMenu.click();
        return new MyActivityPage(getWebDriver());
    }

    public MyDashboardSubMenuPage navigateToMyDashboardSubMenu(){
        myDashboardMenu.click();
        return new MyDashboardSubMenuPage(getWebDriver());
    }

    public Nomination startNomination(){
        waitUntilClickable(recognizeMenu);
        recognizeMenu.click();
        return new Nomination(getWebDriver());
    }

    public MyTeam openMyTeam(){
        myTeamMenu.click();
        return new MyTeam(getWebDriver());
    }

    public AwardPlusLightboxPage openAwardPlusByAwardTitle(String awardTitle){
        webdriver.findElement(By.xpath("//h3[contains(text(),'" + awardTitle + "')]/following-sibling::div[contains(@class,'buttonStyle1')]//a")).click();
        return new AwardPlusLightboxPage(getWebDriver());
    }

    public UserProfilePage openAwardRecipientProfileByAwardTitle(String awardTitle){
        webdriver.findElement(By.xpath("//h3[contains(.,'Test Award +')]/ancestor::div[contains(@class, 'awardNewsItem')]/descendant::h3/child::a")).click();
        return new UserProfilePage(getWebDriver());
    }

    public LoginPage logOut(){
        waitUntilClickable(logOutLink);
        logOutLink.click();
        return new LoginPage(getWebDriver());
    }
}
