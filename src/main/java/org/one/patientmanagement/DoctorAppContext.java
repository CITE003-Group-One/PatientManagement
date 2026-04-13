package org.one.patientmanagement;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import org.one.patientmanagement.ui.DoctorFrame;
import org.one.patientmanagement.ui.controller.doctor.DoctorPatientDashboardDialogController;
import org.one.patientmanagement.ui.controller.navigation.doctor.DoctorNavigator;
import org.one.patientmanagement.ui.core.factory.DoctorPatientDashboardDialogControllerFactory;
import org.one.patientmanagement.ui.core.factory.DoctorPatientDashboardDialogFactory;
import org.one.patientmanagement.ui.view.DoctorView;
import org.one.patientmanagement.ui.view.dialog.DoctorPatientDashboardDialog;

@Singleton
public class DoctorAppContext {

    class DoctorAppContextModule extends AbstractModule {

        @Provides
        @Singleton
        DoctorView provideDoctorView() {
            return new DoctorView();
        }

        @Provides
        @Singleton
        DoctorNavigator provideDoctorNavigator(DoctorView view) {
            return new DoctorNavigator(
                    view.getNavigationLayout(),
                    view.getNavigationContainer()
            );
        }

        @Override
        public void configure() {
            install(new FactoryModuleBuilder()
                    .implement(DoctorPatientDashboardDialog.class, DoctorPatientDashboardDialog.class)
                    .build(DoctorPatientDashboardDialogFactory.class));
            
           install(new FactoryModuleBuilder()
                    .implement(DoctorPatientDashboardDialogController.class, DoctorPatientDashboardDialogController.class)
                    .build(DoctorPatientDashboardDialogControllerFactory.class));
        }
    }

    private final Injector injector;

    public DoctorAppContext(Injector injector) {
        this.injector = injector.createChildInjector(
                new DoctorAppContextModule()
        );
    }

    public void start() {
        System.out.println("Started as doctor");
        injector.getInstance(DoctorFrame.class).setVisible(true);
    }
}
