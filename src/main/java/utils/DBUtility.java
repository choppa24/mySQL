package utils;

import enums.Resources;

import java.sql.*;

public class DBUtility {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static ResultSet DBRequest(String query)  {
        try {
            connection = DriverManager.getConnection(PropertiesUtility.getStringValue(Resources.CONFIG.toString(), "host"),
                    PropertiesUtility.getStringValue(Resources.CONFIG.toString(), "user"),
                    PropertiesUtility.getStringValue(Resources.CONFIG.toString(), "password"));
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultSet;
    }
}
