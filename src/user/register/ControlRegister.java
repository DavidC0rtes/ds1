package user.register;

import java.util.*;

public class ControlRegister {
	private User usuario;
	
	public ControlRegister() {
		usuario = new User();
	}
	
	public boolean datosCompletos(HashMap<String, String> datos) {
		System.out.println(datos);
		if ((datos.containsKey("primer_nombre"))
				&& (datos.containsKey("primer_apellido"))
				&& (datos.containsKey("cedula"))
				&& (datos.containsKey("password"))
				&& (datos.containsKey("email"))
				&& datos.containsKey("rol")) {
			
			datos.putIfAbsent("segundo_nombre", null);
			datos.putIfAbsent("segundo_apellido", null);
			
			datos.forEach((k,v) -> setData(k,v));
			return true;
		}
		
		return false;
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
		case "primer_apellido":
			break;
		case "email":
			usuario.setEmail(value);
			break;
		case "rol":
			usuario.setRol(value);
		case "segundo_nombre":
			usuario.setSegundoNombre(value);
			break;
		case "segundo_apellido":
			usuario.setSegundoNombre(value);
		}
	}
}
