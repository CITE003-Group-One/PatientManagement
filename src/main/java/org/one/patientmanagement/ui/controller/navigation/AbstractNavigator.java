package org.one.patientmanagement.ui.controller.navigation;

import com.google.inject.Provider;
import java.awt.CardLayout;
import java.awt.Component;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.one.patientmanagement.PatientManagement;
import org.one.patientmanagement.ui.controller.AbstractController;

public abstract class AbstractNavigator<
        R extends Enum<R>,
        C extends AbstractController<?, ?>
> {

    private final CardLayout layout;
    private final JPanel container;
    private final Map<R, Provider<? extends C>> registry = new HashMap<>();

    protected AbstractNavigator(CardLayout layout, JPanel container) {
        this.layout = layout;
        this.container = container;
    }

    public void goTo(R route) {
        String key = getRouteKey(route);

        boolean alreadyAdded = Arrays.stream(container.getComponents())
                .anyMatch(c -> key.equals(c.getName()));

        try {
            if (!alreadyAdded) {
                Provider<C> provider = (Provider<C>) registry.get(route);
                if (provider == null) {
                    throw new IllegalArgumentException("No controller registered for route: " + route);
                }
                
                C controller = provider.get();
                var panel = (Component) controller.getView();
                
                panel.setName(key);
                container.add(panel, key);
                
                onControllerCreated(route, controller);
            }
            
            beforeNavigate(route);
            layout.show(container, key);
            afterNavigate(route);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            PatientManagement.showExceptionDialog(exception, "Invalid navigation", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected String getRouteKey(R route) {
        return route.name();
    }

    protected void beforeNavigate(R route) {}
    protected void afterNavigate(R route) {}

    protected void onControllerCreated(R route, C controller) {}

    public void register(R route, Provider<? extends C> provider) {
        registry.put(route, provider);
    }
}
