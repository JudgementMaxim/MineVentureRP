package org.JudgementM.mineVentureRP.database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseManager {
    private static Connection connection;

    public static void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            String url = "jdbc:sqlite:plugins/MineVentureRP/mineventure.db";
            connection = DriverManager.getConnection(url);
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}

