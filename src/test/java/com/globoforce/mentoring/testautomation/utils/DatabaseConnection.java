package com.globoforce.mentoring.testautomation.utils;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnection {

    Connection getDbConnection() throws SQLException;

}
