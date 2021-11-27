package GUI.util;

import javax.swing.table.DefaultTableModel;

public class ModelAgentConfigsTable extends DefaultTableModel {
    String [] header;
    Object[][] data;

    public ModelAgentConfigsTable(Object[][] data, String [] header){
        super();
        this.header = header;
        this.data = data;
        setDataVector(this.data, this.header);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return super.isCellEditable(row, column);
    }
}
