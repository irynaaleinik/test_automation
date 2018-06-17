package com.globoforce.mentoring.testautomation.pages.approvallightbox;

import com.globoforce.mentoring.testautomation.pages.BasePage;
import com.globoforce.mentoring.testautomation.pages.mydashboard.MyApprovalPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;


public class ApprovalLightbox extends BasePage {

    public ApprovalLightbox(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "approvalPanel-approveButton")
    private Button approveButton;

    public MyApprovalPage approveNomination(){
        waitUntilVisible(approveButton);
        approveButton.click();
        waitUntilInvisible(approveButton);
        return new MyApprovalPage(getWebDriver());
    }
}
