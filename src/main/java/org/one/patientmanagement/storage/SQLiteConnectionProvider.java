package org.one.patientmanagement.storage;

import com.google.inject.Provider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class SQLiteConnectionProvider implements Provider<Connection> {

    private static final String DB_URL = "jdbc:sqlite:clinic.db";
    private Connection connection = null;

    @Override
    public Connection get() {
        try {
            connection = DriverManager.getConnection(DB_URL);
            connection.createStatement().execute("PRAGMA foreign_keys = ON;");

            return connection;
        } catch (SQLException e) {
            if (connection != null) try { connection.close(); } catch (SQLException ignored) {}
            throw new RuntimeException("Failed to create DB connection", e);
        }
    }
}