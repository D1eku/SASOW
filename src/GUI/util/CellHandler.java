package GUI.util;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CellHandler extends DefaultTableCellRenderer {
    private String type;

    private Font normal = new Font("Verdana", Font.PLAIN, 12);
    private Font bold = new Font("Verdana", Font.BOLD, 12);

    public CellHandler() {
        type = "text";
    }

    public CellHandler(String type) {
        this.type = type;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //Definicion de colores, no lo necesito

        Color backgroundColor = null;
        Color backgroundColorDefault = new Color(192,192,192);
        Color backgroundColorSelection = new Color(140, 140, 140);

        if(isSelected){
            this.setBackground(backgroundColorDefault);
        }else{
            this.setBackground(Color.white);
        }

        if(hasFocus){
            backgroundColor = backgroundColorSelection;
        }else {
            backgroundColor = backgroundColorDefault;
        }


        switch (type){
            case "text":
                this.setHorizontalAlignment(JLabel.LEFT);
                this.setText((String) value);
                this.setBackground((isSelected) ? backgroundColor : Color.WHITE);
                this.setFont(normal);
                return this;
            case "int":
                this.setHorizontalAlignment(JLabel.CENTER);
                this.setText((String) value);
                //Color newColor = new Color(255, 255,255)
                //this.setBackground((isSelected) ? backgroundColor : Color.WHITE);
                this.setFont(bold);
                return this;
        }



        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }


}
