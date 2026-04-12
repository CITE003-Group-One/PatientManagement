package org.one.patientmanagement.ui.components;

import java.awt.*;
import javax.swing.*;
import org.one.patientmanagement.domain.enums.AppointmentStatus;

public final class StatusBadgePanel extends JPanel {

    private final JLabel statusText = new JLabel();

    public StatusBadgePanel() {
        initUI();
        setStatus(AppointmentStatus.WAITING);
    }

    private void initUI() {
        setLayout(new GridBagLayout());
        setMaximumSize(new Dimension(90, 20));
        setMinimumSize(new Dimension(90, 20));
        setPreferredSize(new Dimension(90, 20));

        statusText.setFont(new Font("Manrope SemiBold", Font.PLAIN, 11));

        add(statusText);
    }

    // ✅ MAIN API
    public void setStatus(AppointmentStatus status) {
        switch (status) {
            case WAITING -> apply(
                "Waiting",
                "#FFDBC7",
                "#643E24"
            );

            case DONE -> apply(
                "Done",
                "#B7F1BA",
                "#1B4D1E"
            );
        }
    }

    private void apply(String text, String bgHex, String fgHex) {
        statusText.setText(text);
        statusText.setForeground(Color.decode(fgHex));
        setBackground(Color.decode(bgHex));

        setOpaque(true);
        repaint();
    }
}