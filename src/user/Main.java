/**
 * Método Main de todo el proyecto.
 * 
 * Esta clase debe llamar a la ventana de Login, desde allí y solo desde allí
 * se debe pasar al dashboard.
 * 
 * Obviamente este debe ser el único método main que debe existir en el proyecto.
 * Para efectos de probar interfaces es aceptable que se creen otros métodos main
 * donde los necesiten, pero una vez manden sus cambios a upstream, deben eliminar 
 * los métodos main extras que crearon.
 */

package user;

import javax.swing.JFrame;
import javax.swing.UIManager;

import user.register.RegisterGUI;

import java.awt.EventQueue;

public class Main  	{

    public static void main(String[] args) {

        try {
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(className);
        } catch (Exception e) {
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
            	
            	LoginGUI gui = new LoginGUI();
            	gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        });
    }
}
