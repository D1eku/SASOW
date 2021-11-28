package GUI.deprecated;

import javax.swing.*;

public class AgentConfiguratorWithActionTool extends JFrame{
    private JTextField agentConfigName;
    private JTextField agentQuantity;
    private JTextField followersQuantity;
    private JComboBox agentTypeCombo;
    private JCheckBox isSeedCheckBox;
    private JComboBox comboBox1;
    private JButton addButton;
    private JList actionsList;
    private JButton deleteButton;
    private JTextField nameActionTextField;
    private JButton saveButton;
    private JPanel agentConfiguratorPanel;
    private JTextField probabilityActionTextField;

    public AgentConfiguratorWithActionTool(){
        setContentPane(agentConfiguratorPanel);
        setTitle("Agent Configurator Tool");
        setVisible(true);
        pack();
    }
}
