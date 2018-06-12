package com.globoforce.mentoring.testautomation.pages.mydashboard;

import com.globoforce.mentoring.testautomation.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class MyDashboardSubMenuPage extends BasePage {

    @FindBy(xpath = "//ul[@id='subNavBar']//a[@href='/microsites/t/dashboard/MyNominations?client=recipientbased']")
    private HtmlElement myNominationMenu;


    public MyDashboardSubMenuPage(WebDriver driver) {
        super(driver);
    }

}
