/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.register;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author camilo
 */
public class RegisterPanel extends javax.swing.JPanel {
	private static final long serialVersionUID = 1L;
	private HashMap<String, String> datos = new HashMap<String, String>();
	private ControlRegister control;
	
	private EscuchaMouse escuchaM = new EscuchaMouse();
    /**
     * Creates new form RegisterUI
     */
    public RegisterPanel() {
        control = new ControlRegister();
        initComponents();
    }

    public void valoresVacios(){
        JOptionPane.showMessageDialog(this, "Todos los campos deben ser llenados correctamente");
    }
    
    public void wrongCedula(){
        JOptionPane.showMessageDialog(this, "El campo identificacion debe ser llenado unicamente con numeros");
    }
    public void lightMode() {
        FlatLightLaf.install();
        FlatLaf.updateUI();
        this.revalidate();
        this.repaint();
    }
    public void darkMode(){
        FlatDarkLaf.install();
        FlatLaf.updateUI();
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

        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        registerBtn = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        passwordTxt = new javax.swing.JPasswordField();

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 640, -1, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel8.setText("Registro de nuevos usuarios");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, -1, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Primer apellido del usuario");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, -1, -1));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Correo");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, -1, -1));

        jTextField1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 240, 30));

        jTextField2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 120, 240, 30));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("Numero de documento");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, -1, -1));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("Primer nombre del usuario");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, -1, -1));

        jTextField3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 240, 30));

        jTextField4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 240, 30));

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("Rol");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 180, -1, -1));

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setText("Contraseña");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, -1, -1));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText("Registrar usuario");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 430, -1, -1));

        registerBtn.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        registerBtn.setForeground(java.awt.Color.darkGray);
        registerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBtnActionPerformed(evt);
            }
        });
        jPanel3.add(registerBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 420, 160, 40));
        registerBtn.addMouseListener(escuchaM);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Gerente", "Operador" }));
        jPanel3.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 240, 30));
        jPanel3.add(passwordTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 280, 240, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 870, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 670, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void registerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBtnActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_registerBtnActionPerformed

    private void getFields() {
    	datos.put("cedula", jTextField4.getText());
    	datos.put("primer_nombre", jTextField3.getText());
    	datos.put("primer_apellido", jTextField1.getText());
    	datos.put("password", String.valueOf(passwordTxt.getPassword()));
    	datos.put("email", jTextField2.getText() );
    	datos.put("rol", String.valueOf(jComboBox1.getSelectedItem()));
    }
    
    private class EscuchaMouse implements MouseListener {

		public void mouseClicked(MouseEvent arg0) {
			getFields();
	        if (control.datosCompletos(datos)) {
	        	if (datos.get("password").length() >= 6) {
	        		if (control.writeData(datos) > 0) {
	        			JOptionPane.showMessageDialog(null, "Usuario nuevo creado exitosamente.");
	        		}
	        		else {
	        			JOptionPane.showMessageDialog(
	        					null, "Error guardando la información, es probable que ya exista un usuario con esa cédula.", 
	        					"Error", JOptionPane.ERROR_MESSAGE);
	        		}
	        	}
	        	else {
	        		JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 6 caracteres.",
							"Atención", 
							JOptionPane.WARNING_MESSAGE);
	        	}
	        } else {
	        	valoresVacios();
	        }
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JPasswordField passwordTxt;
    private javax.swing.JButton registerBtn;
    // End of variables declaration//GEN-END:variables
}
