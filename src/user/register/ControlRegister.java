package user.register;

import java.sql.SQLException;
import java.util.*;

public class ControlRegister {
	private RegisterModel usuario;
	
	public ControlRegister() {
		usuario = new RegisterModel();
	}
	
	public boolean datosCompletos(HashMap<String, String> datos) {
		if (datos.containsValue("")) {
			return false;
		}
		return true;
	}
	
	public int writeData(HashMap<String, String> datos) {
		datos.forEach((k,v) -> setData(k,v));
		
		try {
			return usuario.createUser();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	private void setData(String field, String value) {
		switch(field) {
		
		case "primer_nombre":
			usuario.setPrimerNombre(value);
			break;
		case "cedula":
			usuario.setCedula(Integer.parseInt(value));
			break;
		case "password":
			usuario.setPassword(value);
			break;
		case "primer_apellido":
			usuario.setPrimerApellido(value);
			break;
		case "email":
			usuario.setEmail(value);
			break;
		case "rol":
			usuario.setRol(value);
			break;
		case "segundo_nombre":
			usuario.setSegundoNombre(value);
			break;
		case "segundo_apellido":
			usuario.setSegundoNombre(value);
			break;
		}
	}
}
