package com.globoforce.mentoring.testautomation.awardlightbox.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class LandingPage extends BasePage {

    @FindBy(xpath = "//div[@id='navBar']/descendant::a[@href='/microsites/t/home?client=recipientbased']")
    private HtmlElement homeMenu;

    @FindBy(xpath = "//div[@id='navBar']//a[@href='/microsites/t/dashboard/MyActivity?client=recipientbased']")
    private HtmlElement myDashboardMenu;

    @FindBy(xpath = "//div[@id='navBar']//a[@href='/microsites/t/myteam/MyTeamHome']")
    private HtmlElement myTeamMenu;

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

    public MyDashboardSubMenuPage navigateToMyDashboardSubmenu(){
        myDashboardMenu.click();
        return new MyDashboardSubMenuPage(getWebDriver());
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


}
