package com.globoforce.mentoring.testautomation.utils;

import java.sql.*;
import java.util.ResourceBundle;

public class DataBaseUtil {

    private Connection connection = null;
    private String url = null;
    private String user = null;
    private String pass = null;
    private ResultSet result = null;

    public DataBaseUtil() throws ClassNotFoundException,SQLException {
        ResourceBundle resource =
                ResourceBundle.getBundle("database");
        this.url = resource.getString("url");
        String driver = resource.getString("driver");
        this.user = resource.getString("user");
        this.pass = resource.getString("password");
        Class.forName(driver);
    }

    public void updateClientProperty(long clientId, String propName, String value)throws SQLException {
        try {
            connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            result = statement.executeQuery("select pk_owner from gg_ms_props where pk_owner = '" + clientId + "' and name = '" + propName + "'");
            if (result.next()){
                statement.executeUpdate("update gg_ms_props set value = '" + value + "' where pk_owner = '" + clientId + "' and name = '" + propName + "'");
            }else {
                statement.executeUpdate("INSERT INTO gg_ms_props (pk_prop, country_code, pk_owner, owner_id, lang, name, value) " +
                        "VALUES (gg_ms_props_seq.NEXTVAL, '--', " + clientId + ", 1, '---', '" + propName + "', '" + value + "')");
            }
        } finally {
            if(connection != null) connection.close();
        }
    }

}
