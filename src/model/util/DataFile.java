package model.util;

import model.util.data.MatrixData;
import model.util.data.RowData;

public abstract class DataFile {
    protected String name;
    protected String value;
    protected MatrixData dataToWrite;


    public DataFile(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public void addLine(RowData rd){
        dataToWrite.addRow(rd);
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public abstract void createLine();

    public MatrixData getDataToWrite() {
        return this.dataToWrite;
    }

}
