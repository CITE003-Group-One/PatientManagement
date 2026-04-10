package org.one.patientmanagement.ui.components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DayCardComponent extends JPanel {
    private JLabel monthLbl, dateNumLbl, dayNameLbl;
    private boolean isSelected;
    
    private final Color activeColor = Color.decode("#894A69"); 
    private final Color inactiveColor = Color.decode("#F4E4E8"); 

    public DayCardComponent(String month, String date, String day, boolean isSelected) {
        this.isSelected = isSelected;
        
        // Scaled size for 1200x800 JFrame (Original 100x123 -> 84x105)
        this.setPreferredSize(new Dimension(84, 105));
        this.setOpaque(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        setupUI(month, date, day);
        setSelected(isSelected); 
    }

    private void setupUI(String m, String d, String dy) {
        // Vertical stack with padding to center text like Figma
        this.setLayout(new GridLayout(3, 1));
        this.setBorder(new EmptyBorder(10, 0, 10, 0));
        
        monthLbl = new JLabel(m, SwingConstants.CENTER);
        dateNumLbl = new JLabel(d, SwingConstants.CENTER);
        dayNameLbl = new JLabel(dy, SwingConstants.CENTER);

        // Standardized font sizes for 1200x800
        monthLbl.setFont(new Font("Poppins", Font.PLAIN, 11));
        dateNumLbl.setFont(new Font("Poppins", Font.BOLD, 22));
        dayNameLbl.setFont(new Font("Poppins", Font.PLAIN, 11));

        this.add(monthLbl);
        this.add(dateNumLbl);
        this.add(dayNameLbl);
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
        Color textColor = selected ? Color.WHITE : activeColor;
        
        monthLbl.setForeground(textColor);
        dateNumLbl.setForeground(textColor);
        dayNameLbl.setForeground(textColor);
        
        repaint(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setColor(isSelected ? activeColor : inactiveColor);
        // Using 12px border-radius as requested
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
        
        g2.dispose();
    }
}