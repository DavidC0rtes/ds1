package user;

import javax.swing.JFrame;
import javax.swing.UIManager;

import user.register.RegisterGUI;

import java.awt.EventQueue;

public class Main {

    public static void main(String[] args) {

        try {
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(className);
        } catch (Exception e) {
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
            	
            	RegisterGUI gui = new RegisterGUI();
            	gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        });
    }
}
