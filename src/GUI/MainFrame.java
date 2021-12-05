package GUI;

import GUI.util.ActionData;
import GUI.util.AgentConfiguratorData;
import GUI.util.ExperimentConfigData;
import GUI.util.ModelAgentConfigsTable;
import model.environments.twitter.ExperimentTwitter;
import model.util.actions.Action;
import model.util.config.AgentConfig;
import model.util.config.DataHandlerConfig;
import model.util.config.SimulationConfig;
import model.util.factory.ActionFactory;
import model.util.factory.AgentConfigFactory;
import model.util.factory.AgentFactory;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
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
    private JTextPane DescriptionField;
    private JTextField NameExperimentField;
    private JTextField RepetitionsField;
    private JTextField PeriodsField;
    private JTextField SeedSizeField;
    private JCheckBox essentialDataCheckBox;
    private JCheckBox detailedDataCheckBox;
    private JTextArea outputTextArea;



    //Variables para manejar las configuraciones de los agentes.
    private ModelAgentConfigsTable model;//Modelo que utilizara el JTABLE
    private JTable agentsConfigDataTable;//El JTABLE OBVIO
    private JScrollPane agentConfigJScrollPane;//Panel para la Jtable
    private JButton loadExperimentConfigFileButton;
    private JButton saveExperimentConfigFileButton;
    private JButton newExperimentConfigButton;
    private JPanel topPanel;
    //private ArrayList<AgentConfiguratorData> auxListAgentConfiguratorData;//Data de los agentConfig
    private ExperimentConfigData expConfig;

    //Factory

    private ActionFactory actionFactory;
    private AgentFactory agentFactory;
    private AgentConfigFactory agentConfigFactory;

    public MainFrame() {
        expConfig = new ExperimentConfigData();
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
                    fixData();
                    int periods = Integer.parseInt(PeriodsField.getText());
                    String name = NameExperimentField.getText();
                    String description = DescriptionField.getText();
                    int repetitions = Integer.parseInt(RepetitionsField.getText());
                    int networkSize = Integer.parseInt(NetworkSizeField.getText());
                    int seedSize = Integer.parseInt(SeedSizeField.getText());
                    ArrayList<AgentConfig> agentConfigs = createAgentConfigs();
                    DataHandlerConfig dataHandlerConfig = new DataHandlerConfig(name);
                    dataHandlerConfig.setDetailedData(detailedDataCheckBox.isSelected());
                    dataHandlerConfig.setEssentialData(essentialDataCheckBox.isSelected());
                    ExperimentTwitter exp = new ExperimentTwitter(repetitions, name, description, dataHandlerConfig){
                        @Override
                        public void configure() {
                            this.simulationConfig = new SimulationConfig(periods, networkSize, seedSize, agentConfigs);
                        }
                    };
                    exp.run();
                }catch (Exception exp) {
                    exp.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error Can't Start Simulation, Verify your Config");
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
                    System.out.println(exp);
                }
            }
        });

        /*
        Listener for JTable when has keyboard focus
         */
        agentsConfigDataTable.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                fixData();
            }
        });
        /*
        Listener for JTable when has keyboard focus
         */
        agentsConfigDataTable.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                fixData();
            }
        });

        /*
        Button for open file configuration for experiment;
         */
        loadExperimentConfigFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int response = fileChooser.showOpenDialog(null);
                if(response == JFileChooser.APPROVE_OPTION) {
                    String route = fileChooser.getSelectedFile().getAbsolutePath();
                    Properties prop = new Properties();
                    try {
                        System.out.println(route);
                        FileInputStream fiStream = new FileInputStream(route);
                        prop.load(fiStream);
                        loadExperimentConfigFile(prop);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        });

        /*
        Button for save file configuration for experiment;
         */
        saveExperimentConfigFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int response = fileChooser.showSaveDialog(null);
                if(response == JFileChooser.APPROVE_OPTION) {
                    try {
                        String route = fileChooser.getSelectedFile().getAbsolutePath()+".cfg";
                        Properties p = saveExperimentConfigFile();
                        p.store(new FileOutputStream(route), null);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });
        /*

         */
        agentsConfigDataTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                fixData();
            }
        });
        newExperimentConfigButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expConfig = new ExperimentConfigData();
                makeTable();
                fixData();
                agentsConfigDataTable.updateUI();
                //Actualizar JCheckBoxes.
                detailedDataCheckBox.setSelected(false);
                essentialDataCheckBox.setSelected(false);
                //Actualizar Labels
                SeedSizeField.setText("");
                NetworkSizeField.setText("");
                DescriptionField.setText("");
                NameExperimentField.setText("");
                PeriodsField.setText("");
                RepetitionsField.setText("");
                pack();
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
        String[] headList = {"name", "Quantity Agent", "isSeed"};
        Object[][] data = getDataMatrix(headList);
        makeTable(headList, data);
    }

    private void makeTable(String[] head, Object[][] data){
        model = new ModelAgentConfigsTable(data, head) {

            @Override
            public Class getColumnClass(int columnIndex) {
                switch (columnIndex){
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
                    actionsAgent.add(actionFactory.createReadAction(ad.getProbability()));
                }else {
                    actionsAgent.add(actionFactory.createShareAction(ad.getProbability()));
                }
            }
            if(dataAgent.isSeed()){
                list.add(agentConfigFactory.createAgentConfig(
                        agentFactory.createTwitterAgentSeed(actionsAgent),
                        dataAgent.getQuantityAgent(),
                        dataAgent.getFollowersByNetwork(getNetworkSize()),
                        dataAgent.getFollowingsByNetwork(getNetworkSize())));
            }else{
                list.add(agentConfigFactory.createAgentConfig(
                        agentFactory.createTwitterAgent(actionsAgent),
                        dataAgent.getQuantityAgent(),
                        dataAgent.getFollowersByNetwork(getNetworkSize()),
                        dataAgent.getFollowingsByNetwork(getNetworkSize())));
            }
        }
        return list;
    }

    private void fixData(){
        SeedSizeField.setText(""+getSeeds());
        NetworkSizeField.setText(""+(getNetworkSize()+getSeeds()));
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

    public Properties saveExperimentConfigFile(){
        fixData();
        saveOnExperimentDataConfigState();
        return expConfig.toCFGFile();
    }

    public void loadExperimentConfigFile(Properties prop){
        expConfig = new ExperimentConfigData();
        makeTable();
        expConfig.loadFromCFG(prop);
        //Actualizar JTable
        ArrayList<AgentConfiguratorData> acd = expConfig.getAgentConfData();
        for(int i = 0; i<acd.size(); i++){
            AgentConfiguratorData dataAgentToAdd = acd.get(i);
            Vector row = new Vector();
            row.add(dataAgentToAdd.getAgentConfigName());
            row.add(dataAgentToAdd.getQuantityAgent());
            row.add(dataAgentToAdd.isSeed());
            this.model.addRow(row);
        }
        agentsConfigDataTable.updateUI();
        //Actualizar JCheckBoxes.
        detailedDataCheckBox.setSelected(expConfig.isDetailedData());
        System.out.println("expConfig.isDetailedData(): "+expConfig.isDetailedData());
        essentialDataCheckBox.setSelected(expConfig.isEssentialData());
        System.out.println("expConfig.isEssentialData(): "+expConfig.isEssentialData());
        //Actualizar Labels
        SeedSizeField.setText(""+getSeeds());
        NetworkSizeField.setText(""+(getNetworkSize()+getSeeds()));
        DescriptionField.setText(expConfig.getDescription());
        NameExperimentField.setText(expConfig.getName());
        PeriodsField.setText(expConfig.getPeriods()+"");
        RepetitionsField.setText(expConfig.getRepetitions()+"");
        fixData();
        this.pack();
    }

    public void saveOnExperimentDataConfigState(){
        try {
            //Todo quizas no deberias convertir a int aca los valores ....
            int periods = Integer.parseInt(PeriodsField.getText());
            String name = NameExperimentField.getText();
            String description = DescriptionField.getText();
            int repetitions = Integer.parseInt(RepetitionsField.getText());
            int networkSize = Integer.parseInt(NetworkSizeField.getText());
            int seedSize = Integer.parseInt(SeedSizeField.getText());
            expConfig.setName(name);
            expConfig.setDescription(description);
            expConfig.setPeriods(periods);
            expConfig.setRepetitions(repetitions);
            expConfig.setNetworkSize(networkSize);
            expConfig.setSeedSize(seedSize);
            expConfig.setEssentialData(Boolean.getBoolean(essentialDataCheckBox.getText()));
            expConfig.setDetailedData(Boolean.getBoolean(detailedDataCheckBox.getText()));
        } catch (Exception exp){
            exp.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error on Save, Verify your data");
        }
    }

    public int getSeeds(){
        int total = 0;
        for(int i = 0; i<model.getDataVector().size(); i++){
            if((boolean) model.getDataVector().get(i).get(2)){
                total +=  (int) model.getDataVector().get(i).get(1);
            }
        }
        return total;
    }

    public int getNetworkSize(){
        int total = 0;
        for(int i = 0; i<model.getDataVector().size(); i++){
            if(!(boolean) model.getDataVector().get(i).get(2)){
                total +=  (int) model.getDataVector().get(i).get(1);
            }
        }
        return total;
    }


}
