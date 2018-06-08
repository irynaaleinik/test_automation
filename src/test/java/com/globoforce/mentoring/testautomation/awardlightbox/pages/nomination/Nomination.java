package com.globoforce.mentoring.testautomation.awardlightbox.pages.nomination;

import com.globoforce.mentoring.testautomation.awardlightbox.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Image;
import ru.yandex.qatools.htmlelements.element.TextInput;


public class Nomination extends BasePage {

    @FindBy(className = "js-np-next")
    private Button nextButton;

    @FindBy(id = "np_awardMessage")
    private TextInput awardTitleField;

    @FindBy(id = "np_awardTitle")
    private TextInput awardMessageField;

    @FindBy(className = "js-np-send-award")
    private Button sendAwardButton;

    @FindBy(id = "np-confirmation-image")
    private Image confirmationImage;

    public Nomination(WebDriver driver) {
        super(driver);
    }

    public Nomination navigateToNextStep(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        nextButton.click();
        return this;
    }

    public Nomination chooseAwardProgramByName(String awardProgramName){
        webdriver.findElement(By.xpath("//h3[contains(,.'" + awardProgramName + "')]")).click();
        return this;
    }

    public Nomination chooseAwardReasonByName(String awardReason){
        webdriver.findElement(By.cssSelector("div[title='" + awardReason + "']")).click();
        return this;
    }

    public Nomination setTitle(String awardTitle){
        setText(awardTitleField, awardTitle);
        return this;
    }

    public Nomination setMessage(String awardTitle){
        setText(awardMessageField, awardTitle + " message");
        return this;
    }

    public Nomination createNomination(){
        sendAwardButton.click();
        return this;
    }

    public boolean checkConfirmationImage(){
        return confirmationImage.isDisplayed();
    }
}
