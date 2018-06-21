package com.globoforce.mentoring.testautomation.pages.nomination;

import com.globoforce.mentoring.testautomation.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.w3c.dom.html.HTMLElement;
import ru.yandex.qatools.htmlelements.element.*;


public class Nomination extends BasePage {

    @FindBy(id = "np-recipient-search-field")
    private TextInput searchField;

    @FindBy(className = "np-recipient-list")
    private HtmlElement recipientSearchResult;

    @FindBy(className = "np-recipient-search-back")
    private Link recipientSearchBackButton;

    @FindBy(className = "js-np-next")
    private Button nextButton;

    @FindBy(id = "np_awardTitle")
    private TextInput awardTitleTextField;

    @FindBy(id = "np_awardMessage")
    private TextInput awardMessageTextField;

    @FindBy(className = "js-np-send-award")
    private Button sendAwardButton;

    @FindBy(className = "np-confirmation-message")
    private Image confirmationMessage;

    @FindBy(id = "np_messageForApproval")
    private TextInput messageToApproverTextField;

    @FindBy(className = "np-advisor-skip-link")
    private Button awardAdvisorSkipLink;

    @FindBy(className = "js-np-close")
    private Button closeButton;

    public Nomination(WebDriver driver) {
        super(driver);
    }

    public Nomination selectRecipientBySearch(String recipientFullName){
        waitUntilVisible(searchField);
        searchField.clear();
        searchField.sendKeys(recipientFullName);
        waitUntilClickable(recipientSearchBackButton);
        waitUntilClickable(webdriver.findElement(By.xpath("//h4[@data-title='" + recipientFullName + "']")));
        webdriver.findElement(By.xpath("//h4[@data-title='" + recipientFullName + "']")).click();
        return new Nomination(getWebDriver());
    }

    public Nomination navigateToNextStep(){
        waitUntilVisible(nextButton);
        nextButton.click();
        return this;
    }

    public Nomination chooseAwardProgramByName(String awardProgramName){
        waitUntilVisible(webdriver.findElement(By.xpath("//h3[contains(.,'" + awardProgramName + "')]")));
        webdriver.findElement(By.xpath("//h3[contains(.,'" + awardProgramName + "')]")).click();
        return this;
    }

    public Nomination chooseAwardReasonByName(String awardReason){
        waitUntilVisible(webdriver.findElement(By.cssSelector("div[title='" + awardReason + "']")));
        webdriver.findElement(By.cssSelector("div[title='" + awardReason + "']")).click();
        return this;
    }

    public Nomination skipAwardAdvisor(){
        waitUntilVisible(awardAdvisorSkipLink);
        awardAdvisorSkipLink.click();
        return this;
    }

    public Nomination chooseAwardLevel(String awardName){
        waitUntilVisible(webdriver.findElement(By.xpath("//bdi[contains(text(),'" + awardName + "')]/ancestor::div[@class='np-award-value-wrapper']")));
        webdriver.findElement(By.xpath("//bdi[contains(text(),'" + awardName + "')]/ancestor::div[@class='np-award-value-wrapper']")).click();
        return this;
    }

    public Nomination setTitle(String awardTitle){
        waitUntilVisible(awardTitleTextField);
        awardTitleTextField.click();
        awardTitleTextField.sendKeys(awardTitle);
        return this;
    }

    public Nomination setMessage(String awardTitle){
        waitUntilVisible(awardMessageTextField);
        awardMessageTextField.click();
        awardMessageTextField.sendKeys(awardTitle + " message");
        return this;
    }

    public Nomination setMessageToApprover(String messageToApprover){
        waitUntilVisible(messageToApproverTextField);
        messageToApproverTextField.clear();
        messageToApproverTextField.sendKeys("Text");
        return this;
    }

    public Nomination createNomination(){
        waitUntilVisible(sendAwardButton);
        sendAwardButton.click();
        return this;
    }

    public String getConfirmationMessage(){
        waitUntilVisible(confirmationMessage);
        return confirmationMessage.getText().trim();
    }

    public void closeNominationConfirmation(){
        waitUntilClickable(closeButton);
        closeButton.click();
        waitUntilInvisible(closeButton);
    }
}
