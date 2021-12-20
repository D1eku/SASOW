package model.util.data;

import java.util.ArrayList;

public class MatrixData {
    private ArrayList<ArrayList<String>> columns;
    private ArrayList<String> head;
    //Todo hay un problema porque tendremos repetidas las cabeceras por cada row data

    public MatrixData(){
        this.columns = new ArrayList<>();
    }

    public void addRow(RowData row) {
        //System.out.println("Adding rows, this is the row: "+row.getRows());
        if(head == null) {
            head = row.getHead();
            columns.add(head);
        }
        columns.add(row.getRows());
    }


    public void setHead(ArrayList<String> head) {
        this.head = head;
    }

    private StringBuilder getStringedArray(ArrayList<String> x) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< x.size(); i++) {
            sb.append(x.get(i));
            if(i+1 != x.size()){
                sb.append(",");
            }
        }
        return sb;
    }



    public String getCSVFormat() {
        StringBuilder sb = new StringBuilder();
        //sb.append(getStringedArray(this.head)+"\n");
        for(int i = 0; i<columns.size(); i++) {
            sb.append(getStringedArray(columns.get(i)));
            //sb.insert();

            if(i+1 != columns.size()) {
                sb.append("\n");
            }
        }
        //System.out.println("sb.length(): "+sb.length());
        //System.out.println("SIZE OF CSV Format: "+ columns.size());
        return sb.toString();
    }
}
