package GUI;

import GUI.util.AgentConfiguratorData;
import GUI.util.ModelAgentConfigsTable;
import model.util.actions.Action;
import model.util.config.AgentConfig;
import model.util.factory.ActionFactory;
import model.util.factory.AgentConfigFactory;
import model.util.factory.AgentFactory;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame{
    //Reference for this ... is obviously...
    private MainFrame obviouslyThis;
    //Panels
    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel startSimulationPanel;
    private JPanel bottomPanel;
    //Buttons
    private JButton addButton;
    private JButton deleteButton;
    private JButton startSimulationButton;

    //FieldsData
    private JComboBox comboBoxSimulation;
    private JTextField NetworkSizeField;
    private JTextArea DescriptionField;
    private JTextField NameExperimentField;
    private JTextField RepetitionsField;
    private JTextField PeriodsField;
    private JTextField SeedSizeField;
    private JCheckBox configurationDataCheckBox;
    private JCheckBox essentialDataCheckBox;
    private JCheckBox detailedDataCheckBox;
    private JTextArea outputTextArea;



    //Variables para manejar las configuraciones de los agentes.
    private ModelAgentConfigsTable model;//Modelo que utilizara el JTABLE
    private JTable agentsConfigDataTable;//El JTABLE OBVIO
    private JScrollPane agentConfigJScrollPane;//Panel para la Jtable
    //private ArrayList<AgentConfig> agentConfigsData;//Data de los agentConfig
    private ArrayList<AgentConfiguratorData> auxListAgentConfiguratorData;//Data de los agentConfig


    //Factory

    private ActionFactory actionFactory;
    private AgentFactory agentFactory;
    private AgentConfigFactory agentConfigFactory;

    public MainFrame() {
        //Factory
        actionFactory = new ActionFactory();
        agentFactory = new AgentFactory();
        agentConfigFactory = new AgentConfigFactory();
        //Factory
        //Panel Config
        obviouslyThis = this;
        setContentPane(mainPanel);
        setTitle("OPEN WOM");
        setSize(650, 480);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //Table Configuration
        configureTable();
        pack();


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //AgentSelectorFrame agF = new AgentSelectorFrame(AgentsList, model);
                AgentConfigurator agentConfigurator = new AgentConfigurator(auxListAgentConfiguratorData, obviouslyThis);
            }
        });

        startSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*int runs = 25;
                String name = "Twitter Manuela Run";
                String description = "Experiment to test influence and saturation of a one message";
                ExperimentTwitter exp = new ExperimentTwitter(runs, name, description);
                //do config
                System.out.println("Starting Experiment in Main with : "+ runs+ "runs");
                System.out.println("Experiment Name: "+name);
                System.out.println("Experiment Description: "+description);*/
                //exp.run();
                System.out.println(PeriodsField.getText());
                System.out.println(NameExperimentField.getText());
                System.out.println(DescriptionField.getText());
            }
        });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int col = agentsConfigDataTable.getSelectedColumn();
                int row = agentsConfigDataTable.getSelectedRow();
                System.out.println("Start to delete element in position: ");
                System.out.println("Row: "+row+ " Col: "+ col);
                System.out.println("The row in position: "+row+ " will be eliminated");
                System.out.println("The data at this row is: ");
                if(row != -1){
                    String data = model.getDataVector().get(row).toString();
                    System.out.println(data);
                    model.getDataVector().remove(row);
                    agentsConfigDataTable.updateUI();
                }

            }
        });




    }

    private void configureTable(){
        agentsConfigDataTable.setBackground(Color.WHITE);
        agentsConfigDataTable.setBorder(new BevelBorder(BevelBorder.RAISED,
                null,
                null,
                null,
                null));
        agentsConfigDataTable.setOpaque(false);
        makeTable();
    }

    private void makeTable(){
        //Todo fix this --> this.agentConfigsData = new ArrayList<>();
        this.auxListAgentConfiguratorData = new ArrayList<>();
        String[] headList = {"name", "Quantity Agent", "isSeed"};
        Object[][] data = getDataMatrix(headList);
        makeTable(headList, data);
    }

    private void makeTable(String[] head, Object[][] data){
        model = new ModelAgentConfigsTable(data, head) {

            @Override
            public Class getColumnClass(int columnIndex) {
                switch (columnIndex){
                    case 0:
                        return String.class;
                    case 1:
                        return Integer.class;
                    case 2:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };
        this.agentsConfigDataTable.setModel(model);
        agentsConfigDataTable.getTableHeader().setReorderingAllowed(false);
        agentsConfigDataTable.setRowHeight(25);
        agentsConfigDataTable.setGridColor(new Color(0,0,0));
        agentsConfigDataTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        agentConfigJScrollPane.setViewportView(agentsConfigDataTable);
    }

    private Object[][] getDataMatrix(String[] headList) {
        Object[][] data = new Object[this.agentConfigsData.size()][headList.length];

        for(int i = 0; i < data.length; i++){
            data[i][0] = this.agentConfigsData.get(i).getName();
            data[i][1] = this.agentConfigsData.get(i).getCantAgent()+"";
            data[i][2] = this.agentConfigsData.get(i).getIsSeed();
        }

        return data;
    }

    public void updateData() {
        System.out.println("UPDATE DATA");
        this.updateModel();
        this.agentsConfigDataTable.updateUI();
    }

    public void updateModel() {
        for(int i = 0; i<auxListAgentConfiguratorData.size(); i++){//Por cada dato que hay en la lista de datos
            AgentConfiguratorData agentConfiguratorData = auxListAgentConfiguratorData.get(i);//
            ArrayList<Action> actions = new ArrayList<>();
            //actions.add(actionFactory.createReadAction(agentConfiguratorData.getPRead()));
            //actions.add(actionFactory.createShareAction(agentConfiguratorData.getPShare()));
            AgentConfig a = new AgentConfig(this.agentFactory.createTwitterAgent(actions), 0, agentConfiguratorData.getFollowers(), agentConfiguratorData.getAgentConfigName());
            this.agentConfigsData.add(a);
            Object[] row = {a.getName(), a.getCantAgent(), a.getIsSeed()};
            this.model.addRow(row);
        }
    }

}
