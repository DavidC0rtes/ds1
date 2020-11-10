/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activos;

import java.util.ArrayList;
import javax.swing.JTextField;
import tables.ds_bd;
import db.JDBCConnection;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class oper_controlador {
    
    JDBCConnection GestorBD = new JDBCConnection();
    public int valoresVacios(ArrayList<JTextField> Componentes){
        for(int i = 0; i < Componentes.size(); i++){
            if(Componentes.get(i).getText().length() == 0){
                return 1;
            }
        }
        return 0;
    }
    
    public int createNewSubestacion(ArrayList<JTextField> Componentes, String identificacion, String ciudad, String direccion, String state){
        int estadoFinal = 0;
        String estado = "";
        int id = 0;
        String SQL = "INSERT INTO public.subestaciones (id_jefe_subestacion, ciudad, direccion, estado) "
                + "VALUES (?, ?, ?, ?)";
        String[] valoresSQL = new String[4];
        
        if(valoresVacios(Componentes) == 0){
            try{
                switch(state){
                    case "Activo":
                        estado = "true";
                        break;
                    case "Inactivo":
                        estado = "false";
                        break;
                }
                id = Integer.parseInt(identificacion);
                
                valoresSQL[0] = identificacion;
                valoresSQL[1] = ciudad;
                valoresSQL[2] = direccion;
                valoresSQL[3] = estado;
                
                if(GestorBD.updateRecord(SQL, valoresSQL) != 0){
                    return 1;
                }
            }
            catch (NumberFormatException nfe) {
                    System.out.println("Invalido");
                    return 3;
            }
        } else {
            return 2;
        }
        return 0;
    }
}
