package com.globoforce.mentoring.testautomation.pages;

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

}
