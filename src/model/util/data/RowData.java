package model.util.data;

import java.util.ArrayList;

public class RowData {
    ArrayList<String> rows = new ArrayList<>();

    public RowData(){

    }

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

    public RowData addLeft(RowData toAdd) {
        for(String row: rows) {
            toAdd.addRow(row);
        }

        rows = toAdd.getRows();
        return  this;
    }

    public RowData addRight(RowData toAdd) {
        ArrayList<String> rows = toAdd.getRows();
        for (String row: rows ) {
            this.rows.add(row);
        }
        return  toAdd;
    }

    public RowData addRows(RowData rowsData) {
        ArrayList<String> rows = rowsData.getRows();
        for(String row: rows) {
            this.rows.add(row);
        }
        return this;
    }

    public ArrayList<String> getRows(){
        return this.rows;
    }

    public String toCSVFormat() {
        String toOut = "";
        for(String row: rows) {
            toOut += row+",";
        }
        return toOut;
    }
}
