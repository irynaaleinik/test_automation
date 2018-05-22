package com.globoforce.mentoring.testautomation.awardlightbox.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserProfilePage extends BasePage {

    public UserProfilePage(WebDriver driver) {
        super(driver);
    }

    public AwardPlusLightboxPage openAwardPlusByAwardTitle(String awardTitle){
        webdriver.findElement(By.xpath("//p[contains(text(),'" + awardTitle + "')]/following-sibling::a")).click();
        return new AwardPlusLightboxPage(getWebDriver());
    }
}
