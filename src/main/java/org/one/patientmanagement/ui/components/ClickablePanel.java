/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.components;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public abstract class ClickablePanel extends JPanel {
    
    public interface ClickListenerObj<T> {
        void onClick(T obj);
    }

    public interface ClickListener {
        void onClick(ClickablePanel panel);
    }

    private ClickListener clickListener;

    public ClickablePanel() {
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                onClicked();
                if (clickListener != null) {
                    clickListener.onClick(ClickablePanel.this);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                onHover(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                onHover(false);
            }
        });
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    // Override to handle click internally (e.g. highlight)
    protected void onClicked() {}

    // Override to handle hover state
    protected void onHover(boolean hovered) {}
}