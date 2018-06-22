package com.globoforce.mentoring.testautomation.services;

import com.globoforce.mentoring.testautomation.utils.DatabaseConnection;
import com.globoforce.mentoring.testautomation.utils.LifeDbConnection;
import com.globoforce.mentoring.testautomation.utils.SandboxDbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class DbConnectionFactory {
    DatabaseConnection connection;

    public Connection getDbConnection(String dbName) throws SQLException, ClassNotFoundException {
        switch (dbName){
            case "sandbox" :
                connection = new SandboxDbConnection();
                return connection.getDbConnection();
            case "life" :
                connection = new LifeDbConnection();
                return connection.getDbConnection();
            default : throw new IllegalArgumentException("Db with such name does not exist");
        }
    }

}
