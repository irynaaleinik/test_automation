package com.globoforce.mentoring.testautomation.uitesting.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SandboxDbConnection implements DatabaseConnection {
    Connection connection;
    String url;
    String user;
    String pass;

    public SandboxDbConnection() throws ClassNotFoundException,SQLException {
        ResourceBundle resource =
                ResourceBundle.getBundle("database");
        url = resource.getString("sandbox.url");
        String driver = resource.getString("driver");
        user = resource.getString("sandbox.user");
        pass = resource.getString("sandbox.password");
        Class.forName(driver);
    }
    @Override
    public Connection getDbConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

}
