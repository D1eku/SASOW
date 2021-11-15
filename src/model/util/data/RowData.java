package model.util.data;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RowData {
    private ArrayList<String> head;
    private ArrayList<String> rows;

    public RowData(){
        rows = new ArrayList<>();
        head = new ArrayList<>();
    }

    public void addRow(String new_row, String name) {
        rows.add(new_row);
        head.add(name);
    }

    public void addRow(Double new_row, String name) {
        rows.add(""+new_row);
        head.add(name);
    }

    public void addRow(int new_row, String name) {
        rows.add(""+new_row);
        head.add(name);
    }

    public void addRow(boolean new_row, String name) {
        rows.add(""+new_row);
        head.add(name);
    }

    public RowData addRows(RowData rowsData) {
        ArrayList<String> rows = rowsData.getRows();
        ArrayList<String> heads = rowsData.getHead();
        for(int i = 0; i < rows.size(); i++){
            this.head.add(heads.get(i));
            this.rows.add(rows.get(i));
        }
        return this;
    }

    public ArrayList<String> getRows(){
        return this.rows;
    }

    public ArrayList<String> getHead() {
        return this.head;
    }

    public String toCSVFormat() {
        return getStringed(rows);
    }

    public String getHeadCSVFormat() {
        return getStringed(head);
    }

    @NotNull
    private String getStringed(ArrayList<String> head) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< head.size(); i++) {
            sb.append(head.get(i));
            if(i+1 != head.size()){
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
