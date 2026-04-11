package org.one.patientmanagement.ui.components;

import com.formdev.flatlaf.ui.FlatButtonBorder;
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignF;
import org.kordamp.ikonli.materialdesign2.MaterialDesignH;
import org.kordamp.ikonli.swing.FontIcon;
import org.one.patientmanagement.ui.controller.navigation.doctor.DoctorNavigator;
import org.one.patientmanagement.ui.controller.navigation.doctor.DoctorRoute;

public class NavigationSidebar extends JPanel {

    private JButton selectedButton;
    private final Color activeColor = Color.decode("#FFDBC7");
    private final Color textColor = Color.decode("#504348");
    private final Color sidebarBg = Color.decode("#F4E4E8");

    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private DoctorNavigator navigator;

    public NavigationSidebar() {
        initComponents();
    }

    private void initComponents() {
        // Fixed sidebar size
        Dimension size = new Dimension(250, 800);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setBackground(sidebarBg);

        // Use BorderLayout as outer container
        setLayout(new BorderLayout());

        // Nav button panel with vertical stacking
        JPanel navPanel = new JPanel();
        navPanel.setOpaque(false);
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));

        // Top padding before buttons
        navPanel.add(Box.createVerticalStrut(138));

        jButton1 = createNavButton("Dashboard", MaterialDesignH.HOME, DoctorRoute.DASHBOARD);
        jButton2 = createNavButton("Patient Queue", MaterialDesignA.ACCOUNT_CLOCK, DoctorRoute.QUEUE);
        jButton3 = createNavButton("Patient Records", MaterialDesignF.FILE_ACCOUNT, DoctorRoute.DASHBOARD);

        navPanel.add(jButton1);
        navPanel.add(Box.createVerticalStrut(15));
        navPanel.add(jButton2);
        navPanel.add(Box.createVerticalStrut(15));
        navPanel.add(jButton3);

        // Left + right padding wrapper
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 25));
        wrapper.add(navPanel, BorderLayout.NORTH);

        add(wrapper, BorderLayout.CENTER);

        // Default selection
        setSelected(jButton1);
    }

    private JButton createNavButton(String text, Ikon icon, DoctorRoute route) {
        JButton btn = new JButton(text);

        btn.setBorder(new FlatButtonBorder());

        FontIcon fontIcon = FontIcon.of(icon, 30, textColor);
        btn.setIcon(fontIcon);
        btn.setIconTextGap(20);

        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btn.setPreferredSize(new Dimension(190, 45));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);

        btn.setFont(new Font("Poppins Medium", Font.PLAIN, 20));
        btn.setForeground(textColor);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setVerticalAlignment(SwingConstants.CENTER);
        btn.setVerticalTextPosition(SwingConstants.CENTER);
        btn.setHorizontalTextPosition(SwingConstants.RIGHT);

        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);

        btn.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (c == selectedButton) {
                    g2.setColor(activeColor);
                    g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 10, 10);
                }
                g2.dispose();
                super.paint(g, c);
            }
        });

        btn.addActionListener(e -> {
            navigator.goTo(route);
            setSelected(btn);
        });
        return btn;
    }

    private void setSelected(JButton btn) {
        selectedButton = btn;
        repaint();
    }

    public void setNavigator(DoctorNavigator navigator) {
        this.navigator = navigator;
    }
}
