package user.register;

import java.sql.SQLException;
import java.util.*;

public class ControlRegister {
	private User usuario;
	
	public ControlRegister() {
		usuario = new User();
	}
	
	public boolean datosCompletos(HashMap<String, String> datos) {
		if (datos.containsValue("")) {
			return false;
		}
		
		datos.forEach((k,v) -> setData(k,v));
			
		try {
			usuario.createUser();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
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
