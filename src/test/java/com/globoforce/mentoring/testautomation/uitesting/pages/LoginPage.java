package com.globoforce.mentoring.testautomation.uitesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class LoginPage extends BasePage {

    @FindBy(name = "username")
    private TextInput loginField;

    @FindBy(name = "password")
    private TextInput passwordField;

    @FindBy(id = "signIn-button")
    private Button loginButton;

    public LoginPage (WebDriver driver){
        super(driver);
    }

    public void loginClientSites(String login, String password){
        setText(loginField, login);
        setText(passwordField, password);
        loginButton.click();
    }

    public void loginAfterLogOut(String login, String password){
        loginButton.click();
        this.loginClientSites(login, password);
    }

}
