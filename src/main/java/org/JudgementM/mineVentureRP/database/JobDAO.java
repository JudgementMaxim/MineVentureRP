package org.JudgementM.mineVentureRP.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class JobDAO {
    public static void createTable() throws SQLException{
        String sql = "CREATE TABLE IF NOT EXISTS jobs (uuid TEXT PRIMARY KEY, job TEXT, level INTEGER)";
        try(Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.execute();
        }
    }
}
