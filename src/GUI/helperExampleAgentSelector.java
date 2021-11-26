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
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class helperExampleAgentSelector extends JFrame{
    private JButton addButton;
    private JPanel mainPanel;

    //Necesario para hacer la tabla
    private JPanel agentListPanel;
    private JScrollPane scrollPaneTableAgentConfig;
    private JTable agentsConfigDataTable;
    private ArrayList<AgentConfig> agentConfigsData;
    private ModelAgentConfigsTable model;

    public helperExampleAgentSelector(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeComponents();
        setVisible(true);
        //setLocationRelativeTo(null);
        makeTable();
        setContentPane(mainPanel);
    }

    public void initializeComponents(){
        //Configura el panel
        //Mi panel es agentListPanel, esta listo
        agentListPanel = new JPanel();
        agentListPanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(agentListPanel);
        agentListPanel.setLayout(new BorderLayout(0,0));

        scrollPaneTableAgentConfig = new JScrollPane();
        agentListPanel.add(scrollPaneTableAgentConfig,BorderLayout.CENTER);

        agentsConfigDataTable = new JTable();
        agentsConfigDataTable.setBackground(Color.WHITE);
        agentsConfigDataTable.setBorder(new BevelBorder(BevelBorder.RAISED,null,null,null,null));
        agentsConfigDataTable.setOpaque(false);
        scrollPaneTableAgentConfig.setViewportView(agentsConfigDataTable);
    }

    public void makeTable(){
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
        scrollPaneTableAgentConfig.setViewportView(agentsConfigDataTable);
        agentListPanel.add(agentsConfigDataTable);
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
