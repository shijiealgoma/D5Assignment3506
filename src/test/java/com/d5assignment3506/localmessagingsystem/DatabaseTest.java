package com.d5assignment3506.localmessagingsystem;

import java.sql.*;
/**
 * A class that tests the connectivity to a PostgreSQL database using JDBC driver.
 */

public class DatabaseTest {

    /**
     * The main method that tests the connectivity to the PostgreSQL database.
     *
     * @param args the command line arguments (not used).
     */
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/loms";
        String user = "loms";
        String password = "loms";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("Connected to the PostgreSQL server successfully.");
                conn.close();
            } else {
                System.out.println("Failed to connect to the PostgreSQL server.");
            }
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}
