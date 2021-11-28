package GUI;

import GUI.util.ActionData;
import GUI.util.AgentConfiguratorData;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class AgentConfigurator extends JFrame {
    //Panels
    private JPanel agentConfiguratorPanel;
    //Reference for this ... is obviously...
    private JFrame obviouslyThis = this;
    //Reference to mainFrame xd
    private MainFrame mainFrame;

    //Fields Data.
    private JTextField agentConfigNameField;
    private JTextField followersQuantityField;
    private JTextField followingsQuantityField;
    private JComboBox agentTypeCombo;
    private JComboBox comboActions;

    //Buttons
    private JButton addButtonAction;
    private JButton deleteButtonAction;
    private JButton addConfigButton;

    //Class to save data and add to mainFrame only if you click en addConfigButton
    private AgentConfiguratorData agentData;

    //Actions Vars
    private JTable actionsTable;
    private JScrollPane actionsJScroll;
    private DefaultTableModel modelActions;

    public AgentConfigurator(MainFrame mainFrame) {
        //Initialize and configure windows
        this.mainFrame = mainFrame;
        setContentPane(agentConfiguratorPanel);
        setTitle("Agent Configurator Tool");
        setVisible(true);
        configureComboActions();
        configureActionsList();

        //Pack XD
        pack();
        /*
        When you want to add a new Config Agent to Experiment Config
         */
        addConfigButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("====================================================================");
                System.out.println("agentConfigNameField: " + agentConfigNameField.getText());
                System.out.println("followersQuantityField: " + followersQuantityField.getText());
                System.out.println("followingsQuantityField: " + followingsQuantityField.getText());
                System.out.println("agentTypeCombo: " + agentTypeCombo.getSelectedItem());
                try {
                    if(itsAllOk()){
                        int followers = Integer.parseInt(followersQuantityField.getText());
                        int followings = Integer.parseInt(followingsQuantityField.getText());
                        String name = agentConfigNameField.getText();
                        String agentTypo = agentTypeCombo.getSelectedItem().toString();
                        agentData.setAgentTypo(agentTypo);
                        agentData.setAgentConfigName(name);
                        agentData.setFollowers(followers);
                        agentData.setFollowings(followings);
                        agentData.setSeed(false);
                        agentData.setQuantityAgent(0);
                        //auxListAgentConfiguratorData.add(agentData);
                        fixData();
                        mainFrame.addDataConfig(agentData);
                        mainFrame.updateData();
                        obviouslyThis.dispose();
                    }else{
                        //todo Popup verify your data !!
                    }
                }catch (Exception exp){
                    System.out.println(exp);
                    //todo Send message
                }
            }
        });

        /*

        For add new actions

         */
        addButtonAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = comboActions.getSelectedItem().toString();
                String type = comboActions.getSelectedItem().toString();
                Double defProb = 0.0;
                System.out.println("Combo Actions: "+ comboActions.getSelectedItem().toString());
                ActionData a = new ActionData(name, defProb, type);
                addElementActionData(a);
                //comboActions.remove(0);
                //comboActions.updateUI();
            }
        });

        /*
        For delete one action from a list
         */
        deleteButtonAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = actionsTable.getSelectedRow();
                try {
                    if(modelActions.getDataVector().size() > 0 && row != -1){
                        ActionData a = deleteAction(row);
                        //comboActions.addItem(a.getType());
                        //comboActions.updateUI();
                    }

                    System.out.println("Selected Row: " + row);
                }catch (Exception exp){
                    System.out.println(exp.toString());
                }

            }
        });
    }

    private void configureActionsList() {
        //We need to create an initialized model
        actionsTable.setBackground(Color.white);
        actionsTable.setBorder(new BevelBorder(BevelBorder.RAISED,null,null,null,null));
        actionsTable.setOpaque(false);
        makeTable();
    }

    private void makeTable(){
        agentData = new AgentConfiguratorData();
        ArrayList<ActionData> dataActionsRef = agentData.getActionsData();
        String[] headList = { "Action Name", "Probability Action"};
        Object[][] data = getDataMatrix(headList, dataActionsRef);
        makeTable(headList, data);
    }

    private void makeTable(String[] head, Object[][] data){
        modelActions = new DefaultTableModel(data, head){

            @Override
            public Class getColumnClass(int columnIndex) {
                switch (columnIndex){
                    case 1:
                        return Double.class;
                    default:
                        return String.class;
                }
            }
        };

        actionsTable.setModel(modelActions);
        actionsTable.getTableHeader().setReorderingAllowed(false);
        actionsTable.setRowHeight(25);
        actionsTable.setGridColor(new Color(0,0,0));
        actionsTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        actionsJScroll.setViewportView(actionsTable);
    }

    private Object[][] getDataMatrix(String[] headList,ArrayList<ActionData> dataActions) {
        Object[][] data = new Object[dataActions.size()][headList.length];

        for(int i = 0; i< data.length; i++){
            data[i][0] = dataActions.get(i).getName();
            data[i][1] = dataActions.get(i).getProbability();
        }

        return data;
    }

    private void updateActionsList() {
        //For update the model al the gui after and update
        this.actionsTable.updateUI();
    }

    private ActionData deleteAction(int row){
        ActionData a = this.agentData.getActionsData().remove(row);
        this.modelActions.getDataVector().remove(row);
        updateActionsList();
        return  a;
    }
    private void addElementActionData(ActionData a){
        this.agentData.getActionsData().add(a);
        Object[] row = {a.getName(), a.getProbability()};
        this.modelActions.addRow(row);
        updateActionsList();
    }

    private void configureComboActions(){
        this.comboActions.addItem("Read");
        this.comboActions.addItem("Share");
    }

    private boolean itsAllOk() {
        if(agentConfigNameField.getText() == "") {
            return false;
        }

        if(agentConfigNameField.getText() == ""){
            return false;
        }

        if(followersQuantityField.getText() == "" ) {
            return false;
        } else {
            try {
                int aux = Integer.parseInt(followersQuantityField.getText());
            }catch (Exception exception){
                return false;
            }
        }

        if(followingsQuantityField.getText() == "") {
            return false;
        }else {
            try {
                int aux = Integer.parseInt(followingsQuantityField.getText());
            }catch (Exception exception){
                return false;
            }
        }

        return  true;
    }

    public void fixData() {
        for (int i = 0; i< this.modelActions.getDataVector().size(); i++) {
            Vector a = this.modelActions.getDataVector().get(i);
            ActionData ad = this.agentData.getActionsData().get(i);
            ad.setName((String) a.get(0));
            ad.setProbability((double) a.get(1));
        }
    }
}
