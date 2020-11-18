/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activos;

import java.util.ArrayList;
import javax.swing.JTextField;

import deprecated.ds_bd;

/**
 *
 * @author USER
 */
public class ControlSubestacion {
    
    ds_bd GestorBD = new ds_bd();
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
        boolean estado = false;
        int id = 0;
        if(valoresVacios(Componentes) == 0){
            try{
                switch(state){
                    case "Activo":
                        estado = true;
                        break;
                    case "Inactivo":
                        estado = false;
                        break;
                }
                
                if(GestorBD.createNewSubE(Integer.parseInt(identificacion), ciudad, direccion, estado) != 0){
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
