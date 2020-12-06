package sige.activos.transformadores;

import sige.db.JDBCConnection;
import java.util.*;

import javax.swing.JTextField;

public class ControlTransformador {
	private ModeloTransformador modelo;
	
	public ControlTransformador(JDBCConnection conn) {
		modelo = new ModeloTransformador(conn);
	}
	
	public boolean checkFields(ArrayList<JTextField> components) {
		for(int i = 0; i < components.size(); i++){
            if(components.get(i).getText().length() == 0){
                return false;
            }
        }
		return true;
	}
	
	public int attemptCrear(HashMap<String,String> datos) {
		
		
		try {
			int numCapacidad = Integer.parseInt(datos.get("capacidad"));
			if (numCapacidad <= 0) {
				return -1;
			}
		} catch (NumberFormatException e) {
			return -1;
		}
		
		if (modelo.writeData(datos)) {
			return 0;
		} else {
			return -2;
		}
	}
}
