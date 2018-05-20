package com.globoforce.mentoring.testautomation.awardlightbox.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends BasePage {

    @FindBy(xpath = "//div[@id='navBar']//a[@href='/microsites/t/dashboard/MyActivity?client=recipientbased']")
    private WebElement homeMenu;

    @FindBy(xpath = "//div[@id='navBar']/descendant::a[@href='/microsites/t/home?client=recipientbased']")
    private WebElement myDashboardMenu;

    public LandingPage (WebDriver driver){
        super(driver);
    }

    public void openMyDashboard(){
        this.myDashboardMenu.click();
    }



}
