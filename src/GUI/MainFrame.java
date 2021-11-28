package GUI;

import GUI.util.ActionData;
import GUI.util.AgentConfiguratorData;
import GUI.util.ExperimentDataConfig;
import GUI.util.ModelAgentConfigsTable;
import model.environments.twitter.ExperimentTwitter;
import model.util.actions.Action;
import model.util.config.AgentConfig;
import model.util.config.SimulationConfig;
import model.util.factory.ActionFactory;
import model.util.factory.AgentConfigFactory;
import model.util.factory.AgentFactory;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

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
    //private ArrayList<AgentConfiguratorData> auxListAgentConfiguratorData;//Data de los agentConfig
    private ExperimentDataConfig expConfig;

    //Factory

    private ActionFactory actionFactory;
    private AgentFactory agentFactory;
    private AgentConfigFactory agentConfigFactory;

    public MainFrame() {
        expConfig = new ExperimentDataConfig();
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

        /*
        Open the AgentConfigurator Window
         */
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgentConfigurator agentConfigurator = new AgentConfigurator(obviouslyThis);
            }
        });

        /*
        Start Simulation Button ._.
         */
        startSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int periods = Integer.parseInt(PeriodsField.getText());
                    String name = NameExperimentField.getText();
                    String description = DescriptionField.getText();
                    int repetitions = Integer.parseInt(RepetitionsField.getText());
                    int NetworkSize = Integer.parseInt(NetworkSizeField.getText());
                    int SeedSize = Integer.parseInt(SeedSizeField.getText());
                    ArrayList<AgentConfig> agentConfigs = createAgentConfigs();
                    ExperimentTwitter exp = new ExperimentTwitter(repetitions, name, description){
                        @Override
                        public void configure() {
                            this.simulation_config = new SimulationConfig(periods, NetworkSize, SeedSize, agentConfigs);
                        }
                    };
                    exp.run();
                }catch (Exception exp) {
                    System.out.println(exp);
                    //TODO add popup error message
                }
            }
        });

        /*
        Delete Agent Config from table button
        */
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = agentsConfigDataTable.getSelectedRow();
                try {
                    if( model.getDataVector().size() > 0 && row != -1){
                        model.getDataVector().remove(row);
                        expConfig.getAgentConfData().remove(row);
                        agentsConfigDataTable.updateUI();
                    }
                }catch (Exception exp) {
                    //Todo send message in popup
                    System.out.println(exp);
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
        Object[][] data = new Object[this.expConfig.getAgentConfData().size()][headList.length];

        for(int i = 0; i < data.length; i++){
            data[i][0] = this.expConfig.getAgentConfData().get(i).getAgentConfigName();
            data[i][1] = this.expConfig.getAgentConfData().get(i).getQuantityAgent();
            data[i][2] = this.expConfig.getAgentConfData().get(i).isSeed();
        }

        return data;
    }

    public void updateData() {
        this.agentsConfigDataTable.updateUI();
    }

    public void addDataConfig(AgentConfiguratorData agentData){
        expConfig.getAgentConfData().add(agentData);//Agregalo a la lista de configs
        Object[] row = {agentData.getAgentConfigName(), agentData.getQuantityAgent(), agentData.isSeed()};
        this.model.addRow(row);
    }

    public void printAgentConfiguratorData(){
        System.out.println("===========================================");
        System.out.println("Agent Configurator Data in --> AUX ");
        for(int i = 0; i<expConfig.getAgentConfData().size(); i++) {
            System.out.println(expConfig.getAgentConfData().get(i));
        }

        System.out.println("===========================================");
        System.out.println("Agent Configurator Data in --> model ");
        for(int i = 0; i<this.model.getDataVector().size(); i++) {
            System.out.println(this.model.getDataVector().get(i));
        }
    }

    private ArrayList<AgentConfig> createAgentConfigs(){
        fixData();
        ArrayList<AgentConfig> list = new ArrayList<>();
        for (int i = 0; i<expConfig.getAgentConfData().size(); i++) {
            AgentConfiguratorData dataAgent = expConfig.getAgentConfData().get(i);
            ArrayList<Action> actionsAgent = new ArrayList<>();
            for( int j = 0 ; j< dataAgent.getActionsData().size(); j++) {
                ActionData ad = dataAgent.getActionsData().get(j);
                String type = ad.getType();
                if(type.equals("Read")){
                    actionsAgent.add(actionFactory.createReadAction(ad.getName(), ad.getProbability()));
                }else {
                    actionsAgent.add(actionFactory.createShareAction(ad.getName(), ad.getProbability()));
                }
            }
            if(dataAgent.isSeed()){
                list.add(agentConfigFactory.createAgentConfig(agentFactory.createTwitterAgentSeed(actionsAgent), dataAgent.getQuantityAgent(), dataAgent.getFollowers()));
            }else{
                list.add(agentConfigFactory.createAgentConfig(agentFactory.createTwitterAgent(actionsAgent), dataAgent.getQuantityAgent(), dataAgent.getFollowers()));
            }
        }
        return list;
    }

    private void fixData(){
        for(int i = 0; i<this.model.getDataVector().size(); i++){
            Vector aux = this.model.getDataVector().get(i);
            String new_name = (String) aux.get(0);
            int new_quantity = (int) aux.get(1);
            boolean new_seed = (boolean) aux.get(2);

            this.expConfig.getAgentConfData().get(i).setAgentConfigName(new_name);
            this.expConfig.getAgentConfData().get(i).setQuantityAgent(new_quantity);
            this.expConfig.getAgentConfData().get(i).setSeed(new_seed);
        }
    }

    public void saveExperimentConfigFile(){

    }

    public void loadExperimentConfigFile(){
        
    }


}
