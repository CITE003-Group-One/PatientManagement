package org.one.patientmanagement.ui.components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public final class DayCardComponent extends JPanel {

    private JLabel monthLbl, dateNumLbl, dayNameLbl;
    private boolean isSelected;

    private final Color secondaryColor = Color.decode("#894A69");
    private final Color defaultLabelColor = Color.decode("#21191C");
    private final Color todayCircleColor = Color.decode("#894a69");
    private final Color defaultCardLabel = Color.decode("#F4E4E8");
    private boolean isToday;

    public DayCardComponent(String month, String date, String day, boolean isSelected) {
        this.isSelected = isSelected;

        // Scaled size for 1200x800 JFrame (Original 100x123 -> 84x105)
        this.setPreferredSize(new Dimension(84, 105));
        this.setOpaque(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));

        setupUI(month, date, day);
//        setSelected(isSelected); 
        setToday(isSelected);
    }

    private void setupUI(String m, String d, String dy) {
        // Vertical stack with padding to center text like Figma
        this.setLayout(new GridLayout(3, 1));
        this.setBorder(new EmptyBorder(10, 0, 10, 0));

        monthLbl = new JLabel(m, SwingConstants.CENTER);
        dateNumLbl = new JLabel(d, SwingConstants.CENTER);
        dayNameLbl = new JLabel(dy, SwingConstants.CENTER);

        monthLbl.setFont(new Font("Manrope Medium", Font.PLAIN, 16));
        dateNumLbl.setFont(new Font("Manrope Semibold", Font.PLAIN, 20));
        dayNameLbl.setFont(new Font("Manrope Medium", Font.PLAIN, 16));

        colorAllLabel(defaultLabelColor);

        this.add(monthLbl);
        this.add(dateNumLbl);
        this.add(dayNameLbl);
    }

    @Override
    public void setEnabled(boolean enabled) {

        // TODO daycardcomponent change color when setenabled
        super.setEnabled(enabled);

        setCursor(enabled
                ? Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
                : Cursor.getDefaultCursor()
        );

        updateTextColors();
        repaint();
    }

    public void setToday(boolean isToday) {
        if (isToday) {
            setSelected(false);
        }
        this.isToday = isToday;

        colorAllLabel(isToday ? secondaryColor : defaultLabelColor);
        dateNumLbl.setForeground(isToday ? Color.WHITE : defaultLabelColor);

        updateTextColors();
        repaint();
    }

    public void setSelected(boolean selected) {
        if (isSelected) {
            setToday(false);
        }
        this.isSelected = selected;

        colorAllLabel(Color.WHITE);

        updateTextColors();
        repaint();
    }

    private void updateTextColors() {
        if (!isEnabled()) {
            Color muted = Color.decode("#CBBCC1");

            colorAllLabel(muted);

            return;
        }

        if (isToday) {
            colorAllLabel(secondaryColor);
            dateNumLbl.setForeground(Color.WHITE);
        } else if (isSelected) {
            colorAllLabel(Color.WHITE);
        } else {
            colorAllLabel(defaultLabelColor);
        }
    }

    private void colorAllLabel(Color color) {
        monthLbl.setForeground(color);
        dateNumLbl.setForeground(color);
        dayNameLbl.setForeground(color);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (isToday) {
            g2.setColor(Color.decode("#F1DEE4"));
        } else if (isSelected) {
            g2.setColor(Color.decode("#7F5539"));
        } else if (!isEnabled()) {
            g2.setColor(Color.decode("#F7E8EC"));
        } else {
            g2.setColor(defaultCardLabel);
        }

        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);

        // Draw today circle behind the date label
        if (isToday) {
            // Calculate position of the middle row (dateNumLbl)
            int rowHeight = getHeight() / 3;
            int circleDiameter = 30;
            int circleX = (getWidth() - circleDiameter) / 2;
            int circleY = rowHeight + (rowHeight - circleDiameter) / 2; // vertically centered in middle row

            g2.setColor(todayCircleColor);
            g2.fillOval(circleX, circleY, circleDiameter, circleDiameter);
        }

        g2.dispose();
    }
}
