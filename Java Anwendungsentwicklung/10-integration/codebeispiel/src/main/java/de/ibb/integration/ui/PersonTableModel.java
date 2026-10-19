package de.ibb.integration.ui;

import de.ibb.integration.entity.Person;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * PersonTableModel - Model für die JTable.
 */
public class PersonTableModel extends AbstractTableModel {
    
    private final String[] columns = {"ID", "Name", "Alter", "E-Mail"};
    private List<Person> personen = new ArrayList<>();
    
    public void setPersonen(List<Person> personen) {
        this.personen = new ArrayList<>(personen);
        fireTableDataChanged();
    }
    
    public Person getPersonAt(int row) {
        return personen.get(row);
    }
    
    @Override
    public int getRowCount() {
        return personen.size();
    }
    
    @Override
    public int getColumnCount() {
        return columns.length;
    }
    
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    @Override
    public Class<?> getColumnClass(int column) {
        return switch (column) {
            case 0 -> Long.class;
            case 2 -> Integer.class;
            default -> String.class;
        };
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Person p = personen.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> p.getId();
            case 1 -> p.getName();
            case 2 -> p.getAlter();
            case 3 -> p.getEmail();
            default -> null;
        };
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;  // Bearbeitung nur über Dialog
    }
}
