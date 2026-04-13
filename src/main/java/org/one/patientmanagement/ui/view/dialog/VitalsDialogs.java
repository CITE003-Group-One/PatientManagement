/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.view.dialog;

import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.bridj.util.Pair;
import org.one.patientmanagement.ui.view.DoctorPatientDashboard;

/**
 *
 * @author KAROL JOHN
 */
public class VitalsDialogs {

    public static String showInputSingle(String label, String initial, Component parent) {

        while (true) {

            JTextField field = new JTextField();
            field.putClientProperty("JTextField.placeholderText", label);

            field.setText(initial);

            int result = JOptionPane.showConfirmDialog(
                    parent,
                    field,
                    TITLE + " - " + label,
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
                return initial;
            }

            String value = field.getText();

            if (value != null && !value.isBlank()) {
                return value;
            }

            JOptionPane.showMessageDialog(
                    parent,
                    "This field is required.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    private static final String TITLE = "Set vitals";

    public static Pair<String, String> showInputDual(String label, String initial1, String initial2, Component parent) {

        while (true) {
            JTextField field1 = new JTextField();
            JTextField field2 = new JTextField();

            field1.setText(initial1);
            field2.setText(initial2);

            JPanel panel = new JPanel(new GridLayout(2, 1, 0, 8));
            panel.add(field1);
            panel.add(field2);

            int result = JOptionPane.showConfirmDialog(
                    parent,
                    panel,
                    TITLE + " - " + label,
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
                return new Pair<>(initial1, initial2);
            }

            String value1 = field1.getText();
            String value2 = field2.getText();

            if (value1 != null && !value1.isBlank()
                    && value2 != null && !value2.isBlank()) {

                return new Pair<>(value1, value2);
            }

            JOptionPane.showMessageDialog(
                    parent,
                    "Both fields are required.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void showError(String label, Component parent) {
        JOptionPane.showMessageDialog(
                parent,
                label + " parsing failed. Set a correct value.",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
