package de.ibb.integration.ui;

import de.ibb.integration.entity.Person;
import de.ibb.integration.service.PersonService;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

/**
 * PersonenFrame - Das Hauptfenster der Anwendung.
 */
public class PersonenFrame extends JFrame {
    
    private final PersonService service;
    private final PersonTableModel tableModel;
    private final JTable table;
    private final JTextField searchField;
    
    public PersonenFrame(PersonService service) {
        this.service = service;
        
        setTitle("👥 Personenverwaltung - Java Fleet Systems");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        
        // TableModel erstellen
        tableModel = new PersonTableModel();
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(25);
        table.getTableHeader().setReorderingAllowed(false);
        
        // Spaltenbreiten
        table.getColumnModel().getColumn(0).setPreferredWidth(50);   // ID
        table.getColumnModel().getColumn(1).setPreferredWidth(200);  // Name
        table.getColumnModel().getColumn(2).setPreferredWidth(60);   // Alter
        table.getColumnModel().getColumn(3).setPreferredWidth(200);  // E-Mail
        
        // Toolbar
        JToolBar toolbar = createToolbar();
        
        // Suchfeld
        searchField = new JTextField(20);
        searchField.putClientProperty("JTextField.placeholderText", "🔍 Suchen...");
        searchField.addActionListener(e -> search());
        
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Suche: "));
        searchPanel.add(searchField);
        JButton searchBtn = new JButton("Suchen");
        searchBtn.addActionListener(e -> search());
        searchPanel.add(searchBtn);
        JButton resetBtn = new JButton("Alle anzeigen");
        resetBtn.addActionListener(e -> {
            searchField.setText("");
            loadData();
        });
        searchPanel.add(resetBtn);
        
        // Status
        JLabel statusLabel = new JLabel("Bereit");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        // Layout
        setLayout(new BorderLayout());
        add(toolbar, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(searchPanel, BorderLayout.SOUTH);
        
        // Daten laden
        loadData();
        
        // Doppelklick zum Bearbeiten
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    editSelected();
                }
            }
        });
    }
    
    private JToolBar createToolbar() {
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        
        JButton addBtn = new JButton("➕ Neu");
        addBtn.addActionListener(e -> addNew());
        toolbar.add(addBtn);
        
        toolbar.addSeparator();
        
        JButton editBtn = new JButton("✏️ Bearbeiten");
        editBtn.addActionListener(e -> editSelected());
        toolbar.add(editBtn);
        
        JButton deleteBtn = new JButton("🗑️ Löschen");
        deleteBtn.addActionListener(e -> deleteSelected());
        toolbar.add(deleteBtn);
        
        toolbar.addSeparator();
        
        JButton refreshBtn = new JButton("🔄 Aktualisieren");
        refreshBtn.addActionListener(e -> loadData());
        toolbar.add(refreshBtn);
        
        return toolbar;
    }
    
    private void loadData() {
        List<Person> personen = service.findAll();
        tableModel.setPersonen(personen);
        setTitle("👥 Personenverwaltung - " + personen.size() + " Einträge");
    }
    
    private void search() {
        String term = searchField.getText().trim();
        List<Person> result = service.search(term);
        tableModel.setPersonen(result);
        setTitle("👥 Personenverwaltung - " + result.size() + " Treffer");
    }
    
    private void addNew() {
        PersonDialog dialog = new PersonDialog(this, "Neue Person", null);
        dialog.setVisible(true);
        
        if (dialog.isConfirmed()) {
            try {
                service.save(dialog.getPerson());
                loadData();
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), 
                    "Validierungsfehler", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void editSelected() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Bitte eine Person auswählen!",
                "Hinweis", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        Person person = tableModel.getPersonAt(row);
        PersonDialog dialog = new PersonDialog(this, "Person bearbeiten", person);
        dialog.setVisible(true);
        
        if (dialog.isConfirmed()) {
            try {
                service.save(dialog.getPerson());
                loadData();
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Validierungsfehler", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void deleteSelected() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Bitte eine Person auswählen!",
                "Hinweis", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        Person person = tableModel.getPersonAt(row);
        int confirm = JOptionPane.showConfirmDialog(this,
            "Soll '" + person.getName() + "' wirklich gelöscht werden?",
            "Löschen bestätigen", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            service.delete(person.getId());
            loadData();
        }
    }
}
