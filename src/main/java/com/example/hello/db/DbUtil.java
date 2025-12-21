package com.example.hello.db;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Component
public class DbUtil {

    // Fetch credentials from environment variables
    // private static final String DB_URL = System.getenv().getOrDefault("ADB_TNS_ALIAS", "jdbc:oracle:thin:@dbtest1_low?TNS_ADMIN=/opt/wallets/adb");
    private static final String DB_USER = System.getenv().getOrDefault("ADB_USER", "ADMIN");
    private static final String DB_PASSWORD = System.getenv().getOrDefault("ADB_PASSWORD", "Jheishvali@123#");

    private static final String DB_URL = "jdbc:oracle:thin:@dbtest1_low?TNS_ADMIN=/opt/wallets/adb";
    public String getDbTime() {
        String dbTime = "N/A";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT SYSDATE FROM DUAL")) {

            if (rs.next()) {
                dbTime = rs.getString(1);
            }

        } catch (Exception e) {
            dbTime = "Error fetching DB time: " + DB_URL + " - " + e.getMessage() + " - " + DB_USER + " - " + DB_PASSWORD;
        }
        return dbTime;
    }
}
