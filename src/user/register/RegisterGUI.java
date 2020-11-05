package user.register;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.util.*;

public class RegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private Container mainContenedor;
	private JPanel mainPanel, centerPanel;
	
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
		titleLabel.setText("Registro de nuevos usuarios");
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		mainPanel.add(titleLabel, BorderLayout.NORTH);
		
		registerButton = new JButton();
		registerButton.setText("Registrar");
		registerButton.addMouseListener(escuchaM);
		JPanel southPanel = new JPanel(new FlowLayout());
		southPanel.add(registerButton);
		
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		
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
		primerNombreTxt.setPreferredSize(new Dimension(200,25));
		segundoNombreTxt = new JTextField();
		segundoNombreTxt.setPreferredSize(new Dimension(200, 25));
		
		apellidosLabel = new JLabel("Apellidos");
		apellido1Txt = new JTextField();
		apellido1Txt.setPreferredSize(new Dimension(200, 25));
		apellido2Txt = new JTextField();
		apellido2Txt.setPreferredSize(new Dimension(200, 25));
		
		cedulaLabel = new JLabel("Cédula");
		cedulaTxt = new JTextField();
		cedulaTxt.setPreferredSize(new Dimension(200,25));
		
		emailLabel = new JLabel("Email");
		emailTxt = new JTextField();
		emailTxt.setPreferredSize(new Dimension(200, 25));
		
		passwordLabel = new JLabel("Contraseña");
		passwordTxt = new JPasswordField();
		passwordTxt.setPreferredSize(new Dimension(200, 25));
		
		rolLabel = new JLabel("Cargo");
		rolComboBox = new JComboBox<String>(roles);
		rolComboBox.setPreferredSize(new Dimension(200,25));
		
		
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
	
	/**
	 * Obtiene los valores de todos los JTextFields de la interfaz
	 * y los añade al HashMap para futuros calculos.
	 */
	private void getFields() {
		datos.put("primer_nombre", primerNombreTxt.getText());
		datos.put("segundo_nombre", segundoNombreTxt.getText());
		datos.put("primer_apellido", apellido1Txt.getText());
		datos.put("segundo_apellido", apellido2Txt.getText());
		datos.put("cedula", cedulaTxt.getText());
		datos.put("email", emailTxt.getText());
		datos.put("password", String.valueOf(passwordTxt.getPassword()));
		datos.put("rol", rolComboBox.getSelectedItem().toString());
	}
	
	private class EscuchaMouse implements MouseListener {

		public void mouseClicked(MouseEvent arg0) {
			getFields();
			if (control.datosCompletos(datos)) {
				
				JOptionPane.showMessageDialog(mainPanel, "Usuario creado correctamente.");
			}
			else {
				JOptionPane.showMessageDialog(mainPanel, "Error creando al usuario.");
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
