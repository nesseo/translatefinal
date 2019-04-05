package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
//This class attempts to establish a database connection
public class DBConnector {
    public static Connection getConnection (){
        try {
            return DriverManager.getConnection("jdbc:sqlite:translate.db");

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection(Connection con) {
    }

    public static void closeStatement(Statement stmt) {
    }
}
