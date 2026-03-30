package org.one.patientmanagement;

import com.google.inject.Guice;
import java.awt.EventQueue;
import java.sql.Connection;
import javax.swing.JOptionPane;
import org.one.patientmanagement.repository.RepositoryModule;
import org.one.patientmanagement.service.ServiceModule;
import org.one.patientmanagement.storage.DatabaseInitializer;
import org.one.patientmanagement.storage.DatabaseModule;
import org.one.patientmanagement.ui.PresentationModule;

public class PatientManagement {

    public static void main(String[] args) {
    	System.out.println("Hello World!");
        EventQueue.invokeLater(() -> {
            try {
                
                var injector = Guice.createInjector(
                        new DatabaseModule(),
                        new RepositoryModule(),
                        new ServiceModule(),
                        new PresentationModule()
                );

                // TODO: exception handling for the storage
                injector.getInstance(Connection.class);
                injector.getInstance(DatabaseInitializer.class).init();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Failed to start: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}
