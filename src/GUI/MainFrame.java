package GUI;

import GUI.util.CellHandler;
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
    private JComboBox comboBoxSimulation;
    private JButton addButton;
    private JButton deleteButton;
    private JPanel mainPanel;
    private JButton startSimulationButton;
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
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel startSimulationPanel;
    private JPanel bottomPanel;

    //Variables para manejar las configuraciones de los agentes.
    private ModelAgentConfigsTable model;//Modelo que utilizara el JTABLE
    private JTable agentsConfigDataTable;//El JTABLE OBVIO
    private JScrollPane agentConfigJScrollPane;//Panel para la Jtable
    private ArrayList<AgentConfig> agentConfigsData;//Data de los agentConfig

    public MainFrame() {
        setContentPane(mainPanel);
        setTitle("OPEN WOM");
        setSize(650, 480);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        configureTable();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //AgentSelectorFrame agF = new AgentSelectorFrame(AgentsList, model);
                AgentConfigurator agentConfigurator = new AgentConfigurator();
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

        pack();
    }

    private void configureTable(){
        agentsConfigDataTable.setBackground(Color.WHITE);
        agentsConfigDataTable.setBorder(new BevelBorder(BevelBorder.RAISED,null,null,null,null));
        agentsConfigDataTable.setOpaque(false);
        makeTable();
    }

    private void makeTable(){
        this.agentConfigsData = testData();
        String[] headList = {"name", "Quantity Agent", "isSeed"};
        Object[][] data = getDataMatrix(headList);
        makeTable(headList, data);
    }

    private void makeTable(String[] head, Object[][] data){
        model = new ModelAgentConfigsTable(data, head);
        this.agentsConfigDataTable.setModel(model);
        agentsConfigDataTable.getColumnModel().getColumn(0).setCellRenderer(new CellHandler("text"));
        agentsConfigDataTable.getColumnModel().getColumn(1).setCellRenderer(new CellHandler("int"));
        agentsConfigDataTable.getColumnModel().getColumn(2).setCellRenderer(new CellHandler("text"));
        agentsConfigDataTable.getTableHeader().setReorderingAllowed(false);
        agentsConfigDataTable.setRowHeight(25);
        agentsConfigDataTable.setGridColor(new Color(0,0,0));
        agentsConfigDataTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        agentConfigJScrollPane.setViewportView(agentsConfigDataTable);
    }

    private Object[][] getDataMatrix(String[] headList) {
        String[][] data = new String[this.agentConfigsData.size()][headList.length];

        for(int i = 0; i < data.length; i++){
            data[i][0] = this.agentConfigsData.get(i).getName();
            data[i][1] = this.agentConfigsData.get(i).getCantAgent()+"";
            data[i][2] = this.agentConfigsData.get(i).getIsSeed()+"";
        }

        return data;
    }

    public ArrayList<AgentConfig> testData() {
        //Se que esto se puede convertir en una funcion
        ActionFactory actionFactory = new ActionFactory();
        AgentFactory agentFactory = new AgentFactory();
        AgentConfigFactory agentConfigFactory = new AgentConfigFactory();

        //Agente Average
        ArrayList<model.util.actions.Action> actionsAverage = new ArrayList<>();
        actionsAverage.add(actionFactory.createReadAction(0.5));
        actionsAverage.add(actionFactory.createShareAction(0.5));

        //Agente HUB
        ArrayList<model.util.actions.Action> actionsHub = new ArrayList<>();
        actionsHub.add(actionFactory.createReadAction(0.9));
        actionsHub.add(actionFactory.createShareAction(0.8));


        //Agente Leader
        ArrayList<Action> actionsLeader = new ArrayList<>();
        actionsLeader.add(actionFactory.createReadAction(0.1));
        actionsLeader.add(actionFactory.createShareAction(0.3));


        //Configuracion de agentes
        agentConfigFactory.addAgentConfig(agentFactory.createAgentSeed(actionsLeader), 100, 5);
        agentConfigFactory.addAgentConfig(agentFactory.createAgent(actionsAverage), 50, 5);

        return agentConfigFactory.createAgentConfig();
    }
}
