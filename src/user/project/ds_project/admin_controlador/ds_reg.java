/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_project.admin_controlador;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author USER
 */
public class ds_reg {
    public int valoresVacios(ArrayList<JTextField> Componentes){
        for(int i = 0; i < Componentes.size(); i++){
            if(Componentes.get(i).getText().length() == 0){
                return 1;
            }
        }
        return 0;
    }
    
    
    
    
}
