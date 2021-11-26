package GUI;

import javax.swing.*;

public class AgentConfigurator extends JFrame{
    private JTextField agentConfigNameField;
    private JTextField agentQuantityField;
    private JTextField followersQuantityField;
    private JComboBox agentTypeCombo;
    private JCheckBox isSeedCheckBox;
    private JPanel agentConfiguratorPanel;
    private JTextField probabilityActionReadTextField;
    private JTextField probabilityActionShareTextField;
    private JTextField followingsQuantityField;

    public AgentConfigurator(){
        setContentPane(agentConfiguratorPanel);
        setTitle("Agent Configurator Tool");
        setVisible(true);
        pack();
    }
}
