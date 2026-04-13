package org.one.patientmanagement;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.one.patientmanagement.ui.PatientFrame;
import org.one.patientmanagement.ui.controller.navigation.doctor.DoctorNavigator;
import org.one.patientmanagement.ui.controller.navigation.patient.flow.FlowState;
import org.one.patientmanagement.ui.controller.navigation.patient.PatientFlowNavigator;
import org.one.patientmanagement.ui.model.PatientViewModel;
import org.one.patientmanagement.ui.view.PatientView;

public class PatientAppContext {

    class PatientAppContextModule extends AbstractModule {

        @Provides
        @Singleton
        PatientView providePatientView() {
            return new PatientView();
        }

        @Provides
        @Singleton
        FlowState<PatientViewModel> provideFlowState(PatientViewModel model) {
            return new FlowState(model);
        }

        @Provides
        @Singleton
        PatientFlowNavigator providePatientNavigator(PatientView view, FlowState<PatientViewModel> flowState) {
            return new PatientFlowNavigator(
                    view.getNavigationLayout(),
                    view.getNavigationContainer(),
                    flowState
            );
        }
    }

    private final Injector injector;

    public PatientAppContext(Injector injector) {
        this.injector = injector.createChildInjector(
                new PatientAppContextModule()
        );
    }

    public void start() {
        System.out.println("Started as patient");
        injector.getInstance(PatientFrame.class).setVisible(true);
    }
}
