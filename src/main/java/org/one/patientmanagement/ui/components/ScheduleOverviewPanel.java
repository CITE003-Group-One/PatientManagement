package org.one.patientmanagement.ui.components;

import java.awt.*;
import javax.swing.*;

public class ScheduleOverviewPanel extends JPanel {

    private ScheduleBlock morningBlock;
    private ScheduleBlock afternoonBlock;

    public ScheduleOverviewPanel() {
        initComponents();
    }

    private void initComponents() {
        // Use GridBagLayout for better control over the middle divider
        setLayout(new GridBagLayout());
        setBackground(Color.decode("#FFF8F8"));
        GridBagConstraints gbc = new GridBagConstraints();

        // 1. Create the Blocks
        morningBlock = new ScheduleBlock();
        morningBlock.setBlockInfo("Morning Block", "7:00 AM - 12:00 PM");

        afternoonBlock = new ScheduleBlock();
        afternoonBlock.setBlockInfo("Afternoon Block", "1:00 PM - 5:00 PM");

        // 2. Add Morning Block (Left side)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Give it 50% width
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(morningBlock, gbc);

        // 3. Add Divider Line (Middle)
        JPanel divider = new JPanel();
        divider.setBackground(Color.decode("#E0E0E0"));
        divider.setPreferredSize(new Dimension(1, 0));
        gbc.gridx = 1;
        gbc.weightx = 0; // Divider doesn't grow
        gbc.insets = new Insets(50, 0, 50, 0); // Padding for the line
        add(divider, gbc);

        // 4. Add Afternoon Block (Right side)
        gbc.gridx = 2;
        gbc.weightx = 1.0; // Give it 50% width
        gbc.insets = new Insets(10, 10, 10, 10);
        add(afternoonBlock, gbc);
    }
}