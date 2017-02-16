package com.epam.catalog.dao.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Evgeny on 16.02.2017.
 */
public class JDBCResourceManager {
    public static void closeJdbcResources(Statement statement, ResultSet resultSet) throws SQLException {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
}
