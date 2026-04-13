package org.one.patientmanagement;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.one.patientmanagement.ui.DoctorFrame;
import org.one.patientmanagement.ui.controller.navigation.doctor.DoctorNavigator;
import org.one.patientmanagement.ui.view.DoctorView;

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
