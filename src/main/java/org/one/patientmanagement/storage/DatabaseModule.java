package org.one.patientmanagement.storage;

import com.google.inject.AbstractModule;
import javax.sql.DataSource;

public class DatabaseModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DataSource.class)
            .toProvider(SQLiteDataSourceProvider.class)
            .asEagerSingleton();
        
    }
}
