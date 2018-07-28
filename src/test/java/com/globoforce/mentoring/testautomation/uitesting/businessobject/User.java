package com.globoforce.mentoring.testautomation.uitesting.businessobject;

import java.util.ResourceBundle;

public class User {
    private String firstName;
    private String lastName;
    private String personUserName;
    private String password;

    public User(String userRole) {
        ResourceBundle resource = ResourceBundle.getBundle("user");
        this.firstName = resource.getString(userRole + ".firstname");
        this.lastName = resource.getString(userRole + ".lastname");
        this.personUserName = resource.getString(userRole + ".username");
        this.password = resource.getString(userRole + ".password");
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPersonUserName() {
        return personUserName;
    }

    public String getPassword() {
        return password;
    }
}
