package com.globoforce.mentoring.testautomation.uitesting.utils;

import com.globoforce.mentoring.testautomation.uitesting.businessobject.User;
import com.globoforce.mentoring.testautomation.uitesting.services.DbConnectionFactory;

import java.sql.*;

public class DataBaseUtil {

    private Connection connection = null;
    private ResultSet result = null;
    private String DATABASE_LIVE = "live";

    public void updateClientProperty(long clientId, String propName, String value) throws SQLException, ClassNotFoundException {
        try {
            DbConnectionFactory connectionFactory = new DbConnectionFactory();
            connection = connectionFactory.getDbConnection(DATABASE_LIVE);
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
