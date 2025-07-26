package org.JudgementM.mineVentureRP.database;

import java.sql.*;
import java.util.UUID;

public class PlayerDataDAO {
    private final Connection connection;

    public PlayerDataDAO(Connection connection) throws SQLException {
        this.connection = connection;
        createTableIfNotExists();
    }

    private void createTableIfNotExists() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS playerdata (
                uuid TEXT PRIMARY KEY,
                job TEXT NOT NULL,
                town TEXT NOT NULL,
                coins INTEGER NOT NULL
            );
        """;
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }

    public void savePlayerData(UUID uuid, String job, String town, int coins) throws SQLException {
        String sql = "INSERT INTO playerdata(uuid, job, town, coins) VALUES (?, ?, ?, ?) " +
                "ON CONFLICT(uuid) DO UPDATE SET job = excluded.job, town = excluded.town, coins = excluded.coins;";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, uuid.toString());
            pstmt.setString(2, job);
            pstmt.setString(3, town);
            pstmt.setInt(4, coins);
            pstmt.executeUpdate();
        }
    }

    public PlayerData getPlayerData(UUID uuid) throws SQLException {
        String sql = "SELECT job, town, coins FROM playerdata WHERE uuid = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, uuid.toString());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String job = rs.getString("job");
                    String town = rs.getString("town");
                    int coins = rs.getInt("coins");
                    return new PlayerData(uuid, job, town, coins);
                } else {
                    return null;
                }
            }
        }
    }

    public static class PlayerData {
        private final UUID uuid;
        private final String job;
        private final String town;
        private final int coins;

        public PlayerData(UUID uuid, String job, String town, int coins) {
            this.uuid = uuid;
            this.job = job;
            this.town = town;
            this.coins = coins;
        }

        public UUID getUuid() { return uuid; }
        public String getJob() { return job; }
        public String getTown() { return town; }
        public int getCoins() { return coins; }
    }
}
