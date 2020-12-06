/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sige.user.mantenimiento;

import  sige.user.config.ConfigControl;
import  sige.user.LoginGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import java.time.Duration;
import java.util.Date;

/**
 *
 * @author camilo
 */
public class Mantenimiento extends JFrame {
private String mantenimientoHMS;
private JFrame window;
private ConfigControl configControl;
private Timer verificarMantenimiento;
    /**
     * Creates new form Mantenimiento
     */
    public Mantenimiento(String mantenimientoHMS) {

       this.mantenimientoHMS = mantenimientoHMS;
        initComponents();
        setTitle("SiGe | Mantenimiento en progreso");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window = this;
        configControl = new ConfigControl();
        updateUIEverySec();

    }
    private void updateUIEverySec(){
        Timer timer = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigControl.uptimeTime();
                mantenimientoHMS = ConfigControl.getHMS();
                updateUI();
            }
        });

         verificarMantenimiento = new Timer(30000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                configControl.updateData();
                if (!configControl.isActive()){
                    JOptionPane.showMessageDialog(null, "El mantenimiento se ha terminado");
                    timer.stop();
                    new LoginGUI().setVisible(true);
                    killMySelf();

                }
                jTextArea1.setText(ConfigControl.getSms());
                updateUI();
            }
        });

        timer.start();
        verificarMantenimiento.start();

    }

    private void killMySelf(){
        verificarMantenimiento.stop();
        window.dispose();

    }
    private void updateUI(){
        jLabel1.setText("Tiempo restante \n" + mantenimientoHMS);
        this.revalidate();
        this.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new JPanel();
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        jPanel2 = new JPanel();
        jLabel1 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel1.setLayout(new java.awt.GridLayout(2, 1));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("sansserif", 0, 36)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText(ConfigControl.getSms());
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setOpaque(false);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1);

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 36)); // NOI18N
        jLabel1.setText("Tiempo restante \n" + mantenimientoHMS );

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel1)
                .addContainerGap(85, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);

        getContentPane().add(jPanel1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public JLabel jLabel1;
    private JPanel jPanel1;
    public JPanel jPanel2;
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}