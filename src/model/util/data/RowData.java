package model.util.data;

import java.util.ArrayList;

public class RowData {
    ArrayList<String> rows = new ArrayList<>();

    public RowData(String new_row) {
        rows.add(new_row);
    }

    public RowData(boolean new_row) {
        rows.add(""+new_row);
    }

    public RowData(Double new_row) {
        rows.add(""+new_row);
    }

    public RowData(int new_row) {
        rows.add(""+new_row);
    }

    public void addRow(String new_row) {
        rows.add(new_row);
    }

    public void addRow(Double new_row) {
        rows.add(""+new_row);
    }

    public void addRow(int new_row) {
        rows.add(""+new_row);
    }

    public void addRow(boolean new_row) {
        rows.add(""+new_row);
    }
}
