package org.one.patientmanagement.ui.controller.navigation.doctor;

import com.google.inject.Singleton;
import java.awt.CardLayout;
import javax.swing.JPanel;
import org.one.patientmanagement.ui.controller.AbstractController;
import org.one.patientmanagement.ui.controller.navigation.AbstractNavigator;

@Singleton
public class DoctorNavigator extends AbstractNavigator<DoctorRoute, AbstractController<?, ?>> {

    public DoctorNavigator(CardLayout layout, JPanel container) {
        super(layout, container);
    }
    
}
