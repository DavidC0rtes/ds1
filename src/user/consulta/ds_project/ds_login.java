/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class ds_login extends javax.swing.JFrame {

    /**
     * Creates new form ds_login
     */
    public ds_login() {
        initComponents();
        changeComponents();
    }
    
    public void exit(){
        this.dispose();
    }
    
    public void wrongUserPassword(){
        JOptionPane.showMessageDialog(this, "Usuario y/o contraseña incorrectas");
    }
    
    public void changeComponents(){
        // ---------------------------------------------
        jTextField2.setBorder(BorderFactory.createCompoundBorder(
        jTextField2.getBorder(), 
        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        // ----------------------------------------------
        jPasswordField1.setBorder(BorderFactory.createCompoundBorder(
        jPasswordField1.getBorder(), 
        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        // ----------------------------------------------
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            ds_bd BDGestor = new ds_bd();
            if(jTextField2.getText().length() != 0 && String.valueOf(jPasswordField1.getPassword()).length() != 0){
                try {
                    // intentional error
                    String s = jTextField2.getText();
                    int rol = BDGestor.loginDatabase(Integer.parseInt(jTextField2.getText()), String.valueOf(jPasswordField1.getPassword()));
                    switch(rol){
                        case 0:
                            System.out.println("Not found");
                            wrongUserPassword();
                            break;
                        case 1:
                            ds_admin_user adminInterface = new ds_admin_user();
                            adminInterface.setVisible(true);
                            System.out.println("Welcome back admin");
                            exit();
                            break;
                        case 2:
                            System.out.println("Welcome back operator");
                            break;
                        case 3:
                            System.out.println("Welcome back 3rd role");
                            break;
                        case 4:
                            System.out.println("Couldn't find a proper window");
                            break;
                    }

                }
                catch (NumberFormatException nfe) {
                    System.out.println("Invalido");
                    wrongUserPassword();
                }
                
            } else {
                System.out.println("Vacio");
                wrongUserPassword();
            }

          }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ds_ims/login_bg.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 410, 620));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 600));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setText("Bienvenido nuevamente");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, -1, -1));

        jTextField2.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 0, 11)); // NOI18N
        jTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(228, 228, 228)));
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 400, 40));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ingresar");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 330, -1, 20));

        jButton1.setBackground(new java.awt.Color(3, 255, 157));
        jButton1.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ds_ims/button.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, 160, 40));

        jPasswordField1.setToolTipText("Ingresa tu clave");
        jPasswordField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(228, 228, 228)));
        jPanel2.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 400, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 490, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ds_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ds_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ds_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ds_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ds_login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
