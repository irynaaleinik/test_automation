package com.globoforce.mentoring.testautomation.uitesting.pages.mydashboard;

import com.globoforce.mentoring.testautomation.uitesting.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;

public class MyDashboardSubMenuPage extends BasePage {

    @FindBy(xpath = "//ul[@id='subNavBar']//a[@href='/microsites/t/dashboard/MyNominations?client=recipientbased']")
    private HtmlElement myNominationMenu;

    @FindBy(xpath = "//ul[@id='subNavBar']//a[@href='/microsites/t/dashboard/MyApprovals?client=recipientbased']")
    private Link myApprovalMenu;

    public MyDashboardSubMenuPage(WebDriver driver) {
        super(driver);
    }

    public MyApprovalPage openMyApprovalPage(){
        myApprovalMenu.click();
        return new MyApprovalPage(getWebDriver());
    }

}
