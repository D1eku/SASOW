package experiments.main.test;

import GUI.MainFrame;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerIJTheme;

public class Main {


    public static void main(String[] args) {
        configureLookAndFeel();
        MainFrame myFrame = MainFrame.getInstance();
    }

    private static void configureLookAndFeel() {
        try {
            FlatMaterialDarkerIJTheme.setup();
        } catch (Exception e){
            System.out.println("Error trying to configure look and feel");
            e.printStackTrace();
        }
    }
}
