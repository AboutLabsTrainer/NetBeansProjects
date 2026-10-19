package de.ibb.integration.ui;

import de.ibb.integration.entity.Person;
import javax.swing.*;
import java.awt.*;

/**
 * PersonDialog - Dialog zum Erstellen/Bearbeiten einer Person.
 */
public class PersonDialog extends JDialog {
    
    private final JTextField nameField;
    private final JSpinner alterSpinner;
    private final JTextField emailField;
    
    private Person person;
    private boolean confirmed = false;
    
    public PersonDialog(Frame owner, String title, Person person) {
        super(owner, title, true);
        
        this.person = person != null ? person : new Person();
        
        setSize(400, 250);
        setLocationRelativeTo(owner);
        setResizable(false);
        
        // Felder
        nameField = new JTextField(25);
        alterSpinner = new JSpinner(new SpinnerNumberModel(25, 0, 150, 1));
        emailField = new JTextField(25);
        
        // Werte setzen wenn vorhanden
        if (person != null) {
            nameField.setText(person.getName());
            if (person.getAlter() != null) {
                alterSpinner.setValue(person.getAlter());
            }
            emailField.setText(person.getEmail());
        }
        
        // Layout
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Name
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Name: *"), gbc);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);
        
        // Alter
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Alter:"), gbc);
        gbc.gridx = 1;
        formPanel.add(alterSpinner, gbc);
        
        // E-Mail
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("E-Mail:"), gbc);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        JButton saveBtn = new JButton("💾 Speichern");
        saveBtn.addActionListener(e -> save());
        buttonPanel.add(saveBtn);
        
        JButton cancelBtn = new JButton("❌ Abbrechen");
        cancelBtn.addActionListener(e -> dispose());
        buttonPanel.add(cancelBtn);
        
        // Hauptlayout
        setLayout(new BorderLayout(10, 10));
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Enter zum Speichern
        getRootPane().setDefaultButton(saveBtn);
        
        // Escape zum Schließen
        getRootPane().registerKeyboardAction(
            e -> dispose(),
            KeyStroke.getKeyStroke("ESCAPE"),
            JComponent.WHEN_IN_FOCUSED_WINDOW
        );
    }
    
    private void save() {
        // Werte übernehmen
        person.setName(nameField.getText().trim());
        person.setAlter((Integer) alterSpinner.getValue());
        
        String email = emailField.getText().trim();
        person.setEmail(email.isEmpty() ? null : email);
        
        confirmed = true;
        dispose();
    }
    
    public boolean isConfirmed() {
        return confirmed;
    }
    
    public Person getPerson() {
        return person;
    }
}
