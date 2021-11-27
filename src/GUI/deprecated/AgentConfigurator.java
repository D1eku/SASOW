package GUI.deprecated;

import GUI.MainFrame;
import GUI.util.AgentConfiguratorData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AgentConfigurator extends JFrame{
    private JFrame obviouslyThis = this;
    private JTextField agentConfigNameField;
    private JTextField followersQuantityField;
    private JTextField followingsQuantityField;
    private JComboBox agentTypeCombo;
    private JPanel agentConfiguratorPanel;
    private JTextField probabilityActionReadTextField;
    private JTable table1;
    private JTextField probabilityActionShareTextField;
    private JButton addConfigButton;
    private MainFrame mainFrame;


    public AgentConfigurator(ArrayList<AgentConfiguratorData> auxListAgentConfiguratorData, MainFrame mainFrame){
        this.mainFrame = mainFrame;
        setContentPane(agentConfiguratorPanel);
        setTitle("Agent Configurator Tool");
        setVisible(true);
        pack();
        addConfigButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("====================================================================");
                System.out.println("agentConfigNameField: "+agentConfigNameField.getText());
                System.out.println("followersQuantityField: "+followersQuantityField.getText());
                System.out.println("followingsQuantityField: "+followingsQuantityField.getText());
                System.out.println("agentTypeCombo: "+agentTypeCombo.getSelectedItem());
                System.out.println("probabilityActionReadTextField: "+probabilityActionReadTextField.getText());
                System.out.println("probabilityActionShareTextField: "+probabilityActionShareTextField.getText());

                double pRead = Double.parseDouble(probabilityActionReadTextField.getText());
                double pShare = Double.parseDouble(probabilityActionShareTextField.getText());
                int followers = Integer.parseInt(followersQuantityField.getText());
                int followings = Integer.parseInt(followingsQuantityField.getText());
                String name = agentConfigNameField.getText();
                String agentTypo = agentTypeCombo.getSelectedItem().toString();

                //AgentConfiguratorData agentConfiguratorData = new AgentConfiguratorData(name, agentTypo, followers, followings, pRead, pShare);
                //auxListAgentConfiguratorData.add(agentConfiguratorData);

                mainFrame.updateData();
                obviouslyThis.dispose();
            }
        });
    }
}
