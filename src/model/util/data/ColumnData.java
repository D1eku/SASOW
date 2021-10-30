package model.util.data;

import java.util.ArrayList;

public class ColumnData {
    private ArrayList<RowData> columns;

    public ColumnData(){
        this.columns = new ArrayList<>();
    }

    public void addRow(RowData row) {
        columns.add(row);
    }

    public ArrayList<RowData> getColumns(){
        return this.columns;
    }

    public String getCSVFormat() {
        String toOut = "";
        for(RowData row: columns){
            toOut += row.toCSVFormat()+"\n";
        }
        return toOut;
    }
}
