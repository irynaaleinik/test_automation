package com.globoforce.mentoring.testautomation.pages.mydashboard;

import com.globoforce.mentoring.testautomation.pages.BasePage;
import com.globoforce.mentoring.testautomation.pages.approvallightbox.ApprovalLightbox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyApprovalPage extends BasePage {

    public MyApprovalPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='pendingAwards-3192878']/div")
    private List<WebElement> listOfPendingNominations;

    public ApprovalLightbox openPendingNominationByName(String awardName){
        webdriver.findElement(By.xpath("//bdi[text()='" + awardName + "']")).click();
        return new ApprovalLightbox(getWebDriver());
    }

    public MyApprovalPage approvePendingNominationByName(String awardName){
        webdriver.findElement(By.xpath("//bdi[text()='" + awardName + "']/ancestor::div[contains(@class,'award-card')]//button[contains(@class,'js-btn-approve')]")).click();
        waitUntilInvisible(webdriver.findElement(By.xpath("//bdi[text()='" + awardName + "']/ancestor::div[contains(@class,'award-card')]//button[contains(@class,'js-btn-approve')]")));
        return new MyApprovalPage(getWebDriver());
    }

    public int getNumberOfPendingNominations(){
        return listOfPendingNominations.size();
    }
}
