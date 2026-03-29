/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.storage;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import java.sql.Connection;

/**
 *
 * @author KAROL JOHN
 */
public class DatabaseModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Connection.class)
            .toProvider(SQLiteConnectionProvider.class)
            .in(Scopes.SINGLETON);
        
    }
}
