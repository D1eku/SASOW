package GUI.util;

import javax.swing.table.DefaultTableModel;

public class ModelAgentConfigsTable extends DefaultTableModel {
    private static final int NAME_ROW = 0;
    private static final int QUANTITY_AGENT_ROW = 1;
    private static final int SEED_ROW = 2;

    String [] header;
    Object[][] data;

    public ModelAgentConfigsTable(Object[][] data, String [] header){
        //String[] header = {"name", "Quantity Agent", "isSeed"};
        super();
        this.header = header;
        this.data = data;
        setDataVector(this.data, this.header);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if( column != QUANTITY_AGENT_ROW){
            return false;
        } else {
            return true;
        }
    }

    public void test() {

    }
}
