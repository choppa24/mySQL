import utils.DBUtility;
import utils.JsonUtility;
import utils.LoggerUtility;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DBSendRequest {
    private static ResultSet resultSet;

    public static void getMinWorkingTime(){
        LoggerUtility.info("Step getMinWorkingTime!");
    }
    public static void minTimeTestsQueryPrintToLog() {
        LoggerUtility.info("For each test, print the minimum running time. Sort by projects, and by tests within projects\n");
        printResponseFromDB(DBUtility.DBRequest((String) JsonUtility.getJsonObject().get("minTimeTestsQuery")));
    }

    public static void uniqTestsOnProjectQueryPrintToLog() {
        LoggerUtility.info("Log all projects indicating the number of unique tests on the project\n");
        printResponseFromDB(DBUtility.DBRequest((String) JsonUtility.getJsonObject().get("uniqTestsOnProjectQuery")));
    }

    public static void listTestsStartsOnTimePrintToLog() {
        LoggerUtility.info("List tests for each project that ran on or after November 7, 2015. Sort by project, and by tests within projects\n");
        printResponseFromDB(DBUtility.DBRequest((String) JsonUtility.getJsonObject().get("listTestsStartsOnTimeQuery")));
    }

    public static void countTestsOnBrowserQueryPrintToLog() {
        LoggerUtility.info("Print the number of tests run on Firefox and Chrome\n");
        printResponseFromDB(DBUtility.DBRequest((String) JsonUtility.getJsonObject().get("countTestsOnBrowserQuery")));
    }

    public static void printResponseFromDB(ResultSet rs) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    String columnValue = rs.getString(i);
                    LoggerUtility.info(rsmd.getColumnName(i) + ": " + columnValue);
                }
                LoggerUtility.info(" ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
