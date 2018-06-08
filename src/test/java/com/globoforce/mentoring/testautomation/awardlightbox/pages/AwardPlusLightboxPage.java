package com.globoforce.mentoring.testautomation.awardlightbox.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class AwardPlusLightboxPage extends BasePage {

    @FindBy(css = "div#unifiedAwardDetailsLightbox div#al-messageEditorContainer h2")
    private HtmlElement awardTitle;

    public AwardPlusLightboxPage(WebDriver driver) {
        super(driver);
    }

    public String getAwardTitle(){
        return awardTitle.getText();
    }
}
