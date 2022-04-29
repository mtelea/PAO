package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConfig {
    private static final String DB_URL = "jdbc:mysql:://localhost:3306/PAO";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static Connection databaseConnection;

    private DataBaseConfig(){
    }

    public static Connection getDatabaseConnection(){
        try{
            if (databaseConnection == null || databaseConnection.isClosed()){
                databaseConnection = DriverManager.getConnection(DB_URL,USER, PASSWORD);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return databaseConnection;
    }

}
