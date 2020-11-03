package user.register;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.util.*;

public class RegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private Container mainContenedor;
	private JPanel mainPanel, leftPanel, rightPanel, topPanel, southPanel, centerPanel;
	
	private JButton registerButton;
	
	private JLabel titleLabel;
	private JLabel nombresLabel, firstname1Label, firstname2Label;
	private JLabel apellidosLabel, lastname1Label, lastname2Label;
	private JLabel emailLabel, cedulaLabel, rolLabel, passwordLabel;
	
	private JTextField primerNombreTxt, segundoNombreTxt;
	private JTextField apellido1Txt, apellido2Txt;
	private JTextField emailTxt, cedulaTxt;
	private JPasswordField passwordTxt;
	
	private JComboBox<String> rolComboBox;
	private String[] roles = {"gerente", "administrador", "operador"};
	private HashMap<String,String> datos = new HashMap<String,String>();
	
	private EscuchaMouse escuchaM = new EscuchaMouse();

	private ControlRegister control;
	
	/**
	 * Constructor de la clase RegisterGUI
	 */
	public RegisterGUI() {
		control = new ControlRegister();
		this.setTitle("Registrar usuario");
		initGUI();
	}
	
	/**
	 * Inicializa la ventana construyendo los elementos básicos
	 */
	private void initGUI() {
		mainContenedor = this.getContentPane();
		mainContenedor.setLayout(new BorderLayout(10,10));
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		titleLabel = new JLabel();
		titleLabel.setText("Registro");
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		mainPanel.add(titleLabel, BorderLayout.NORTH);
		
		registerButton = new JButton();
		registerButton.setText("Registrar");
		registerButton.addMouseListener(escuchaM);
		mainPanel.add(registerButton, BorderLayout.SOUTH);
		
		setupForm();
		
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainContenedor.add(mainPanel);

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	/**
	 * Configura los elementos que irán en la mitad de la interfaz.
	 * Es decir, los campos que recopilan la información del nuevo usuario.
	 */
	private void setupForm() {
		centerPanel = new JPanel(new GridLayout(4,1));
		
		nombresLabel = new JLabel("Nombres");
		primerNombreTxt = new JTextField();
		primerNombreTxt.setPreferredSize(new Dimension(100,20));
		segundoNombreTxt = new JTextField();
		segundoNombreTxt.setPreferredSize(new Dimension(100, 20));
		
		primerNombreTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				datos.put("primer_nombre",primerNombreTxt.getText());
			}
		});
		
		segundoNombreTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				datos.put("segundo_nombre",segundoNombreTxt.getText());
			}
		});
		
		apellidosLabel = new JLabel("Apellidos");
		apellido1Txt = new JTextField();
		apellido1Txt.setPreferredSize(new Dimension(100, 20));
		apellido2Txt = new JTextField();
		apellido2Txt.setPreferredSize(new Dimension(100, 20));
		
		apellido1Txt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				datos.put("primer_apellido", apellido1Txt.getText());
			}
		});
		
		apellido2Txt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				datos.put("segunado_apellido", apellido2Txt.getText());
			}
		});
		
		
		cedulaLabel = new JLabel("Cédula");
		cedulaTxt = new JTextField();
		cedulaTxt.setPreferredSize(new Dimension(100,20));
		
		cedulaTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				datos.put("cedula", cedulaTxt.getText());
			}
		});
		
		emailLabel = new JLabel("Email");
		emailTxt = new JTextField();
		emailTxt.setPreferredSize(new Dimension(100, 20));
		
		emailTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				datos.put("email", emailTxt.getText());
			}
		});
		
		passwordLabel = new JLabel("Contraseña");
		passwordTxt = new JPasswordField();
		passwordTxt.setPreferredSize(new Dimension(100, 20));
		
		passwordTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				datos.put("password", String.valueOf(passwordTxt.getPassword()));
			}
		});
		
		rolLabel = new JLabel("Cargo");
		rolComboBox = new JComboBox<String>(roles);
		rolComboBox.setPreferredSize(new Dimension(100,25));
		
		rolComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				datos.put("rol", rolComboBox.getSelectedItem().toString());
			}
		});
		
		
		JPanel filaNombres = new JPanel(new FlowLayout(5,25,10));
		filaNombres.add(nombresLabel);
		filaNombres.add(primerNombreTxt);
		filaNombres.add(segundoNombreTxt);
		
		centerPanel.add(filaNombres);
		
		JPanel filaApellidos = new JPanel(new FlowLayout(5,25,10));
		filaApellidos.add(apellidosLabel);
		filaApellidos.add(apellido1Txt);
		filaApellidos.add(apellido2Txt);
		centerPanel.add(filaApellidos);
		
		JPanel filaCedula = new JPanel(new FlowLayout(5,25,10));
		filaCedula.add(cedulaLabel);
		filaCedula.add(cedulaTxt);
		filaCedula.add(emailLabel);
		filaCedula.add(emailTxt);
		centerPanel.add(filaCedula);
		
		JPanel filaContraseña = new JPanel(new FlowLayout());
		filaContraseña.add(rolLabel);
		filaContraseña.add(rolComboBox);
		filaContraseña.add(passwordLabel);
		filaContraseña.add(passwordTxt);
		
		centerPanel.add(filaContraseña);
	}
	
	private class EscuchaMouse implements MouseListener {

		public void mouseClicked(MouseEvent arg0) {
			if (control.datosCompletos(datos)) {
				JOptionPane.showMessageDialog(null, "Usuario creado correctamente.");
			}
			else {
				JOptionPane.showMessageDialog(null, "Error creando al usuario.");
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
}
