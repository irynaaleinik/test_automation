package com.globoforce.mentoring.testautomation.pages.myteam;

import com.globoforce.mentoring.testautomation.pages.BasePage;
import com.globoforce.mentoring.testautomation.pages.nomination.Nomination;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;

public class MyTeamIndividualView extends BasePage {

    @FindBy(id = "recognizeButton-button")
    private Button recognizeButton;

    public MyTeamIndividualView(WebDriver driver) {
        super(driver);
    }

    public Nomination recognize(){
        recognizeButton.click();
        return new Nomination(getWebDriver());
    }
}
