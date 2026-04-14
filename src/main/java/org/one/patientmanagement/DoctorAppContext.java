package org.one.patientmanagement;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.one.patientmanagement.service.AccountManager;
import org.one.patientmanagement.service.DoctorManager;
import org.one.patientmanagement.ui.DoctorFrame;
import org.one.patientmanagement.ui.controller.doctor.DoctorPatientDashboardDialogController;
import org.one.patientmanagement.ui.controller.navigation.doctor.DoctorNavigator;
import org.one.patientmanagement.ui.core.factory.DoctorPatientDashboardDialogControllerFactory;
import org.one.patientmanagement.ui.core.factory.DoctorPatientDashboardDialogFactory;
import org.one.patientmanagement.ui.model.DoctorViewModel;
import org.one.patientmanagement.ui.model.PatientViewModel;
import org.one.patientmanagement.ui.view.DoctorLogin;
import org.one.patientmanagement.ui.view.DoctorView;
import org.one.patientmanagement.ui.view.dialog.DoctorPatientDashboardDialog;

@Singleton
public class DoctorAppContext {

    class DoctorAppContextModule extends AbstractModule {

        @Provides
        @Singleton
        DoctorView provideDoctorView(DoctorViewModel model) {
            return new DoctorView(model);
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

        var accountManager = injector.getInstance(AccountManager.class);

        var panel = injector.getInstance(DoctorLogin.class);
        var loginFrame = new JFrame("Doctor Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setContentPane(panel);
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);

        panel.setSubmitListener(password -> {
            accountManager.authenticate(password)
                    .ifPresentOrElse(
                            account -> {
                                var model = injector.getInstance(DoctorViewModel.class);
                                var doctorManager = injector.getInstance(DoctorManager.class);
                                var doctor = doctorManager.getDoctorByAccountId(account.id());

                                doctor.ifPresent(d -> {
                                    model.setDoctor(d);
                                    loginFrame.dispose();
                                    injector.getInstance(DoctorFrame.class).setVisible(true);
                                });
                            },
                            () -> JOptionPane.showMessageDialog(loginFrame, "Invalid password.", "Login Failed", JOptionPane.ERROR_MESSAGE)
                    );
        });
    }
}
