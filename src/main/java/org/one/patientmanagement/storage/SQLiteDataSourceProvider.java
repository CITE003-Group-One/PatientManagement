package org.one.patientmanagement.storage;

import com.google.inject.Provider;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.sqlite.SQLiteDataSource;

class SQLiteDataSourceProvider implements Provider<DataSource> {
    private static final String DB_URL = "jdbc:sqlite:clinic.db";

    @Override
    public synchronized DataSource get() {
        try {
            var dc = new SQLiteDataSource();
            dc.setUrl(DB_URL);
            try (var conn = dc.getConnection();
                 var stmt = conn.createStatement()) {
                stmt.execute("PRAGMA foreign_keys = ON;");
            }
            return dc;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize DataSource", e);
        }
    }
}