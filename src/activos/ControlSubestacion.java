package activos;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTextField;

import db.JDBCConnection;
import deprecated.ds_bd;

/**
 *
 * @author USER
 */
public class ControlSubestacion {
	
	private JDBCConnection DB = new JDBCConnection();
    
    ds_bd GestorBD = new ds_bd();
    public int valoresVacios(ArrayList<JTextField> Componentes){
        for(int i = 0; i < Componentes.size(); i++){
            if(Componentes.get(i).getText().length() == 0){
                return 1;
            }
        }
        return 0;
    }
    
    public int createNewSubestacion(ArrayList<JTextField> Componentes, String[] datos){
        int estadoFinal = 0;
        boolean estado = false;
        int id = 0;
       
        if(valoresVacios(Componentes) == 0){
            try{
                switch(datos[3]) {
                    case "Activo":
                        datos[3] = "true";
                        break;
                    case "Inactivo":
                        datos[3] = "false";
                        break;
                }
                
                String sql = 
                		 "insert into subestaciones(id_jefe_subestacion,ciudad,direccion,estado,nombre) "
                		 + "values(?,?,?,?,?)";
                
                
                Integer.parseInt(datos[0]);
                if (DB.updateRecord(sql, datos) == 1) {
                	return 1;
                }
                
            }
            catch (NumberFormatException nfe) {
                    System.out.println(datos[0]);
                    return 3;
            }
        } else {
            return 2;
        }
        return 0;
    }
}
