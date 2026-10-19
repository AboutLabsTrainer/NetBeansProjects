package com.tutego.insel.ui.table;

import javax.swing.table.AbstractTableModel;

class SquareTableModelSimple extends AbstractTableModel {

  @Override public int getRowCount() {
    return 100;
  }

  @Override public int getColumnCount() {
    return 3;
  }

  @Override public String getValueAt( int row, int col ) {
    switch ( col ) {
      case 0:  return "" + row;
      case 1:  return "" + (row * row);
      default: return "" + (row * row * row);
    }
  }
}