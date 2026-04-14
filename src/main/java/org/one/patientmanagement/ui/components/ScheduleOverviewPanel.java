package org.one.patientmanagement.ui.components;

import java.awt.*;
import javax.swing.*;
import org.one.patientmanagement.ui.core.dto.QueueData;
import org.one.patientmanagement.domain.enums.AppointmentBlock;
import org.one.patientmanagement.domain.models.Schedule;

public class ScheduleOverviewPanel extends JPanel {
    
    private ClickablePanel.ClickListenerObj<QueueData> clickListener;

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
        gbc.weighty = 1;
        gbc.weightx = 0; 
        add(divider, gbc);

        // 4. Add Afternoon Block (Right side)
        gbc.gridx = 2;
        gbc.weightx = 1.0; // Give it 50% width
        gbc.insets = new Insets(10, 10, 10, 10);
        add(afternoonBlock, gbc);
    }
    
    public void setListModel(java.util.List<QueueData> morning, java.util.List<QueueData> afternoon, Schedule schedule) {
        morningBlock.setRowClickListener(clickListener);
        afternoonBlock.setRowClickListener(clickListener);
        
        morningBlock.loadQueue(morning, AppointmentBlock.MORNING, schedule);
        afternoonBlock.loadQueue(afternoon, AppointmentBlock.AFTERNOON, schedule);
        
    }
    
    public void setRowClickListener(ClickablePanel.ClickListenerObj<QueueData> clickListener) {
        this.clickListener = clickListener;
    }
    
}