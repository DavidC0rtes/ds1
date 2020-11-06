package user.register;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.FontUIResource;

import java.util.*;

public class RegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private Container mainContenedor;
	private JPanel mainPanel, leftPanel, rightPanel;
	
	private JButton registerButton;
	
	private JLabel titleLabel;
	private JLabel nombre1Label, nombre2Label;
	private JLabel apellido1Label, apellido2Label;
	private JLabel emailLabel, cedulaLabel, rolLabel, passwordLabel;
	
	private JTextField primerNombreTxt, segundoNombreTxt;
	private JTextField apellido1Txt, apellido2Txt;
	private JTextField emailTxt, cedulaTxt;
	private JPasswordField passwordTxt;
	
	private JComboBox<String> rolComboBox;
	private String[] roles = {"gerente", "administrador", "operador"};
	private HashMap<String,String> datos = new HashMap<String,String>();
	
	private static final Font FUENTE = new Font("DejaVu Sans", Font.PLAIN, 12);
	
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
		UIManager.put("Label.font", FUENTE);
		UIManager.put("Button.font", FUENTE );
		UIManager.put("ComboBox.font", FUENTE);
		
		titleLabel = new JLabel();
		titleLabel.setText("Registro de nuevos usuarios");
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		titleLabel.setPreferredSize(new Dimension(100,60));
		mainPanel.add(titleLabel, BorderLayout.NORTH);
		
		registerButton = new JButton();
		registerButton.setText("Registrar");
		registerButton.setForeground(Color.white);
		registerButton.setBackground(Color.BLUE);
		
		registerButton.addMouseListener(escuchaM);
		JPanel southPanel = new JPanel(new FlowLayout());
		southPanel.add(registerButton);
		
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		setupForm();
		
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
		
		initLeftPanel();
		initRightPanel();
		
		mainPanel.add(rightPanel, BorderLayout.EAST);
		mainPanel.add(leftPanel, BorderLayout.WEST);
	}
	
	private void initLeftPanel() {
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		nombre1Label = new JLabel("Primer nombre");
		primerNombreTxt = new JTextField();
		primerNombreTxt.setPreferredSize(new Dimension(200,25));
		
		apellido1Txt = new JTextField();
		apellido1Txt.setPreferredSize(new Dimension(200, 25));
		apellido1Label = new JLabel("Primer apellido");
				
		emailLabel = new JLabel("Email");
		emailTxt = new JTextField();
		emailTxt.setPreferredSize(new Dimension(200, 25));
				
		passwordLabel = new JLabel("Contraseña");
		passwordTxt = new JPasswordField();
		passwordTxt.setPreferredSize(new Dimension(200, 25));

		
		leftPanel.add(nombre1Label);
		leftPanel.add(primerNombreTxt);
		leftPanel.add(Box.createRigidArea(new Dimension(0,5)));
		
		leftPanel.add(apellido1Label);
		leftPanel.add(apellido1Txt);
		leftPanel.add(Box.createRigidArea(new Dimension(0,5)));
		
		leftPanel.add(emailLabel);
		leftPanel.add(emailTxt);
		leftPanel.add(Box.createRigidArea(new Dimension(0,5)));
		
		leftPanel.add(passwordLabel);
		leftPanel.add(passwordTxt);
		leftPanel.add(Box.createRigidArea(new Dimension(0,15)));
	}
	
	private void initRightPanel() {
		rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.setFont(new Font("DejaVu Sans", Font.PLAIN, 12));
		
		nombre2Label = new JLabel("Segundo nombre");
		segundoNombreTxt = new JTextField();
		segundoNombreTxt.setPreferredSize(new Dimension(200, 25));

		
		apellido2Txt = new JTextField();
		apellido2Txt.setPreferredSize(new Dimension(200, 25));
		apellido2Label = new JLabel("Segundo apellido");
		
		cedulaLabel = new JLabel("Cédula");
		cedulaLabel.setHorizontalAlignment(SwingConstants.LEFT);
		cedulaTxt = new JTextField();
		cedulaTxt.setPreferredSize(new Dimension(200,25));

		
		rolLabel = new JLabel("Cargo");
		rolComboBox = new JComboBox<String>(roles);
		rolComboBox.setPreferredSize(new Dimension(200,25));
		
		
		rightPanel.add(nombre2Label);
		rightPanel.add(segundoNombreTxt);
		rightPanel.add(Box.createRigidArea(new Dimension(0,5)));
		
		rightPanel.add(apellido2Label);
		rightPanel.add(apellido2Txt);
		rightPanel.add(Box.createRigidArea(new Dimension(0,5)));
		
		rightPanel.add(cedulaLabel);
		rightPanel.add(cedulaTxt);
		rightPanel.add(Box.createRigidArea(new Dimension(0,5)));
		
		rightPanel.add(rolLabel);
		rightPanel.add(rolComboBox);
		rightPanel.add(Box.createRigidArea(new Dimension(0,15)));
		
		rightPanel.setAlignmentY(Component.LEFT_ALIGNMENT);

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

			if (control.datosCompletos(datos) ) {
				
				if (datos.get("password").length() >= 6) {
					JOptionPane.showMessageDialog(mainPanel, "Usuario creado correctamente.");
				} 
				else {
					JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 6 caracteres.",
							"Información", 
							JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
			else {
				JOptionPane.showMessageDialog(mainPanel, "Hay campos sin rellenar.");
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
