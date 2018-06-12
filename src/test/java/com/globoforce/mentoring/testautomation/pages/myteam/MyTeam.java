package com.globoforce.mentoring.testautomation.pages.myteam;

import com.globoforce.mentoring.testautomation.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyTeam extends BasePage {

    public MyTeam(WebDriver driver) {
        super(driver);
    }

    public MyTeamIndividualView openDirectReportIndividualViewByName(String directReportName){
        webdriver.findElement(By.xpath("//div[@id='teamItems']//h3[contains(.,'" + directReportName + "')]")).click();
        return new MyTeamIndividualView(getWebDriver());
    }
}
