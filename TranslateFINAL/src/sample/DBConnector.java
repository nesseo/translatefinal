package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    public static Connection getConnection (){
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:translate.db");
            return connection;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
