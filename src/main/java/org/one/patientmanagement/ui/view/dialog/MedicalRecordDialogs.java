/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.view.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.one.patientmanagement.domain.enums.ConsultationType;
import org.one.patientmanagement.ui.core.dto.ConsultationInput;

/**
 *
 * @author KAROL JOHN
 */
public class MedicalRecordDialogs {

    public static ConsultationInput showConsultationInput(Component parent) {

        JComboBox<ConsultationType> typeBox = new JComboBox<>(new ConsultationType[]{
            ConsultationType.DIAGNOSIS,
            ConsultationType.GENERAL
        });
        JTextField titleField = new JTextField();
        JTextArea descriptionArea = new JTextArea(5, 20);

        titleField.putClientProperty("JTextField.placeholderText", "Enter title");

        JScrollPane descriptionScroll = new JScrollPane(descriptionArea);

        typeBox.setBorder(BorderFactory.createTitledBorder("Type"));
        titleField.setBorder(BorderFactory.createTitledBorder("Title"));
        descriptionScroll.setBorder(BorderFactory.createTitledBorder("Description"));

        JPanel panel = new JPanel(new GridLayout(3, 1, 0, 10));

        panel.add(typeBox);
        panel.add(titleField);
        panel.add(descriptionScroll);

        while (true) {

            int result = JOptionPane.showConfirmDialog(
                    parent,
                    panel,
                    "Create Consultation",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
                return null;
            }

            ConsultationType type = (ConsultationType) typeBox.getSelectedItem();
            String title = titleField.getText();
            String description = descriptionArea.getText();

            if (type != null
                    && title != null && !title.isBlank()
                    && description != null && !description.isBlank()) {

                return new ConsultationInput(type, title, description);
            }

            JOptionPane.showMessageDialog(
                    parent,
                    "All fields are required.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
