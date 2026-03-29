/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.one.patientmanagement;

import com.google.inject.Guice;
import java.awt.EventQueue;
import java.sql.Connection;
import org.one.patientmanagement.storage.DatabaseInitializer;
import org.one.patientmanagement.storage.DatabaseModule;

/**
 *
 * @author KAROL JOHN
 */
public class PatientManagement {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        var injector = Guice.createInjector(
                new DatabaseModule()
        );
        
        // TODO: exception handling for the storage
        injector.getInstance(Connection.class);
        injector.getInstance(DatabaseInitializer.class).init(); 
        
        EventQueue.invokeLater(() -> {
            try {
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
