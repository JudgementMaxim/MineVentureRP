package org.JudgementM.mineVentureRP.database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseManager {
    private static Connection connection;

    public static void connect() throws SQLException {
        if (connection != null && !connection.isClosed()) return;

        connection = DriverManager.getConnection("jdbc:sqlite:plugins/MineVentureRP/database.db");
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null|| connection.isClosed()){
            connect();
        }
        return connection;
    }

    public static void close(){
        try {
            if(connection != null && !connection.isClosed()) connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
