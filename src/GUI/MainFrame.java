package GUI;

import GUI.util.config.ActionData;
import GUI.util.config.AgentConfiguratorData;
import GUI.util.config.ExperimentConfigData;
import GUI.util.ModelAgentConfigsTable;
import model.environments.facebook.ExperimentFacebook;
import model.environments.twitter.ExperimentTwitter;
import model.util.actions.Action;
import model.util.config.AgentConfig;
import model.util.config.DataHandlerConfig;
import model.util.config.SimulationConfig;
import model.util.datahandler.DataHandler;
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
    private static MainFrame obviouslyThis;
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
    private JScrollPane jscrollOutPut;
    private JComboBox comboBoxEnvironment;
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
        //obviouslyThis = this;

        configureWindow();


        /*
        Start Simulation Button ._.
         */
        startSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Windod Size: "+getSize());
                //System.out.println("Action listener del combo simulation, selected: "+comboBoxSimulation.getSelectedItem());
                if(comboBoxSimulation.getSelectedItem().equals("FacebookSimulation")){
                    doExperimentFacebook();
                }else if(comboBoxSimulation.getSelectedItem().equals("TwitterSimulation")){
                    //System.out.println("Twitter");
                    doExperimentTwitter();
                }
            }
        });

        /*
        Open the AgentConfigurator Window
         */
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = "";
                if(comboBoxSimulation.getSelectedItem().equals("FacebookSimulation")){
                    type = "Facebook";
                }else if(comboBoxSimulation.getSelectedItem().equals("TwitterSimulation")){
                    //System.out.println("Twitter");
                    type = "Twitter";
                }
                AgentConfigurator agentConfigurator = new AgentConfigurator(obviouslyThis, type);
            }
        });

        /*
        Delete Agent Config from table button
        */
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Window Size: "+mainPanel.getSize());
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
        Other listener when the table has focused, then the data in table is updated.
         */
        agentsConfigDataTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
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
        Button for create a new experiment, this function or button clear all data in experiment.
         */
        newExperimentConfigButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataHandler.clearInstance();
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
                outputTextArea.setText("");
                pack();
            }
        });
        comboBoxSimulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Action listener del combo simulation, selected: "+comboBoxSimulation.getSelectedItem());
                updateCombos();
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

    private ArrayList<AgentConfig> createAgentConfigs(String Type){
        fixData();
        ArrayList<AgentConfig> list = new ArrayList<>();
        if(Type.equals("Twitter")){
            return createTwitter(list);
        }else if(Type.equals("Facebook")){
            return createFacebook(list);
        }else {
            System.out.println("Error maligno en create agent configs privado en mainframe, quiero llorar :c");
            return null;
        }
    }

    private ArrayList<AgentConfig> createFacebook(ArrayList<AgentConfig> list){
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
                        agentFactory.createFacebookAgentSeed(actionsAgent),
                        dataAgent.getQuantityAgent(),
                        dataAgent.getFollowers(),
                        dataAgent.getFollowings()));
            }else{
                list.add(agentConfigFactory.createAgentConfig(
                        agentFactory.createFacebookAgent(actionsAgent),
                        dataAgent.getQuantityAgent(),
                        dataAgent.getFollowers(),
                        dataAgent.getFollowings()));
            }
        }
        return list;
    }

    private ArrayList<AgentConfig> createTwitter(ArrayList<AgentConfig> list){
        //ArrayList<AgentConfig> list = new ArrayList<>();
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
                        dataAgent.getFollowers(),
                        dataAgent.getFollowings()));
            }else{
                list.add(agentConfigFactory.createAgentConfig(
                        agentFactory.createTwitterAgent(actionsAgent),
                        dataAgent.getQuantityAgent(),
                        dataAgent.getFollowers(),
                        dataAgent.getFollowings()));
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
        //Actualizar ComboBox
        comboBoxSimulation.setSelectedItem(expConfig.getExperimentType());
        updateCombos();
        //Actualizar JCheckBoxes.
        detailedDataCheckBox.setSelected(expConfig.isDetailedData());
        essentialDataCheckBox.setSelected(expConfig.isEssentialData());
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
            expConfig.setExperimentType((String)comboBoxSimulation.getSelectedItem());
            expConfig.setName(name);
            expConfig.setDescription(description);
            expConfig.setPeriods(periods);
            expConfig.setRepetitions(repetitions);
            expConfig.setNetworkSize(networkSize);
            expConfig.setSeedSize(seedSize);
            expConfig.setEssentialData(essentialDataCheckBox.isSelected());
            expConfig.setDetailedData(essentialDataCheckBox.isSelected());
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

    public void appendLineToOutput(String line){
        this.outputTextArea.append(line);
        this.outputTextArea.updateUI();
        //obviouslyThis.pack();
    }

    public static MainFrame getInstance(){
        if(obviouslyThis == null) {
            obviouslyThis = new MainFrame();
        }
        return  obviouslyThis;
    }

    public void loadConfigurationOptions(){
        comboBoxSimulation.addItem("TwitterSimulation");
        comboBoxSimulation.addItem("FacebookSimulation");
    }

    public void doExperimentTwitter(){
        try{
            outputTextArea.setText("");
            fixData();
            int periods = Integer.parseInt(PeriodsField.getText());
            String name = NameExperimentField.getText();
            String description = DescriptionField.getText();
            int repetitions = Integer.parseInt(RepetitionsField.getText());
            int networkSize = Integer.parseInt(NetworkSizeField.getText());
            int seedSize = Integer.parseInt(SeedSizeField.getText());
            ArrayList<AgentConfig> agentConfigs = createAgentConfigs("Twitter");
            DataHandlerConfig dataHandlerConfig = new DataHandlerConfig(name);
            dataHandlerConfig.setDetailedData(detailedDataCheckBox.isSelected());
            dataHandlerConfig.setEssentialData(essentialDataCheckBox.isSelected());
            ExperimentTwitter exp = new ExperimentTwitter(repetitions, name, description, dataHandlerConfig){
                @Override
                public void configure() {
                    DataHandler.getInstance().setWithInterface(true);//MMMMMM
                    this.simulationConfig = new SimulationConfig(periods, networkSize, seedSize, agentConfigs);
                }
            };
            exp.run();
        }catch (Exception exp) {
            exp.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error Can't Start Simulation, Verify your Config");
        }
        this.pack();

    }

    public void doExperimentFacebook(){
        try{
            outputTextArea.setText("");
            fixData();
            int periods = Integer.parseInt(PeriodsField.getText());
            String name = NameExperimentField.getText();
            String description = DescriptionField.getText();
            int repetitions = Integer.parseInt(RepetitionsField.getText());
            int networkSize = Integer.parseInt(NetworkSizeField.getText());
            int seedSize = Integer.parseInt(SeedSizeField.getText());
            ArrayList<AgentConfig> agentConfigs = createAgentConfigs("Facebook");
            DataHandlerConfig dataHandlerConfig = new DataHandlerConfig(name);
            dataHandlerConfig.setDetailedData(detailedDataCheckBox.isSelected());
            dataHandlerConfig.setEssentialData(essentialDataCheckBox.isSelected());
            ExperimentFacebook exp = new ExperimentFacebook(repetitions, name, description, dataHandlerConfig){
                @Override
                public void configure() {
                    DataHandler.getInstance().setWithInterface(true);//MMMMMM
                    this.simulationConfig = new SimulationConfig(periods, networkSize, seedSize, agentConfigs);
                }
            };
            exp.run();
        }catch (Exception exp) {
            exp.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error Can't Start Simulation, Verify your Config");
        }
        this.pack();
    }

    public void updateCombos(){
        if(comboBoxSimulation.getSelectedItem().equals("FacebookSimulation")){
            System.out.println("Facebook");
            comboBoxEnvironment.removeAllItems();
            comboBoxEnvironment.addItem("FacebookEnvironment");
            comboBoxEnvironment.setSelectedItem("FacebookEnvironment");
        }else if(comboBoxSimulation.getSelectedItem().equals("TwitterSimulation")){
            System.out.println("Twitter");
            comboBoxEnvironment.removeAllItems();
            comboBoxEnvironment.addItem("TwitterEnvironment");
            comboBoxEnvironment.setSelectedItem("TwitterEnvironment");
        }
    }


    private void configureWindow(){
        setContentPane(mainPanel);

        setTitle("SASOW - Agent Based Modeling System ");
        Dimension dim = new Dimension(1280, 830);


        setSize(dim);
        setMaximumSize(dim);
        setMinimumSize(dim);
        setPreferredSize(dim);
        setResizable(false);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //Charge combo-boxes
        loadConfigurationOptions();

        //Table Configuration
        configureTable();
        jscrollOutPut.setViewportView(outputTextArea);
        pack();

    }
}
