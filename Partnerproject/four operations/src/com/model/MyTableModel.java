package com.model;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
    // 表头
    private String[] columnNames;
    // 数据
    private Object[][] tableValues;

    public MyTableModel(){
    }

    public MyTableModel(String[] columnNames,Object[][] tableValues) {
        this.columnNames = columnNames;
        this.tableValues = tableValues;
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public int getRowCount() {
        return this.tableValues.length;
    }

    @Override
    public String getColumnName(int column) {
        return this.columnNames[column];
    }

    @Override
    public Object getValueAt(int row, int column) {
        return this.tableValues[row][column];
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (column == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        tableValues[row][column] = value;
        fireTableCellUpdated(row, column);
    }
}
