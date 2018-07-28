package com.globoforce.mentoring.testautomation.uitesting.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LifeDbConnection implements DatabaseConnection {
    Connection connection;
    String url;
    String user;
    String pass;

    public LifeDbConnection() throws ClassNotFoundException,SQLException {
        ResourceBundle resource =
                ResourceBundle.getBundle("database");
        url = resource.getString("live.url");
        String driver = resource.getString("driver");
        user = resource.getString("live.user");
        pass = resource.getString("live.password");
        Class.forName(driver);
    }
    @Override
    public Connection getDbConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}
